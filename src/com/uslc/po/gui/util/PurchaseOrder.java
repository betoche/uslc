/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  com.ibm.icu.util.Calendar
 *  com.uslc.po.jpa.entity.Carton
 *  com.uslc.po.jpa.entity.Color
 *  com.uslc.po.jpa.entity.PackingDetail
 *  com.uslc.po.jpa.entity.PurchaseOrder
 *  com.uslc.po.jpa.entity.PurchaseOrderDetail
 *  com.uslc.po.jpa.entity.Upc
 *  com.uslc.po.jpa.logic.PackingDetailRepo
 *  com.uslc.po.jpa.logic.PurchaseOrderDetailRepo
 *  com.uslc.po.jpa.logic.PurchaseOrderRepo
 *  com.uslc.po.jpa.logic.UpcRepo
 *  org.apache.log4j.Logger
 *  org.apache.log4j.PropertyConfigurator
 */
package com.uslc.po.gui.util;

import com.ibm.icu.util.Calendar;
import com.uslc.po.gui.master.NewPurchaseOrderComposite;
import com.uslc.po.jpa.entity.Carton;
import com.uslc.po.jpa.entity.Color;
import com.uslc.po.jpa.entity.PackingDetail;
import com.uslc.po.jpa.entity.PurchaseOrderDetail;
import com.uslc.po.jpa.entity.Upc;
import com.uslc.po.jpa.logic.PackingDetailRepo;
import com.uslc.po.jpa.logic.PurchaseOrderDetailRepo;
import com.uslc.po.jpa.logic.PurchaseOrderRepo;
import com.uslc.po.jpa.logic.UpcRepo;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class PurchaseOrder {
    private com.uslc.po.jpa.entity.PurchaseOrder po = null;
    private PurchaseOrderDetail[] details = null;
    private HashMap<Integer, PackingDetail[]> packingDetails = null;
    private String status = "";
    private Logger log = null;
    private HashMap<String, PoStatistics> poStats = null;

    public PurchaseOrder(com.uslc.po.jpa.entity.PurchaseOrder po) {
        this.po = po;
        this.init();
    }

    private void init() {
    }

    public static PurchaseOrder[] getPurchaseOrders() {
        PurchaseOrder[] pos = new PurchaseOrder[]{};
        List list = PurchaseOrderRepo.findAll();
        if (list != null) {
            pos = new PurchaseOrder[list.size()];
            int i = 0;
            while (i < list.size()) {
                pos[i] = new PurchaseOrder((com.uslc.po.jpa.entity.PurchaseOrder)list.get(i));
                ++i;
            }
        }
        return pos;
    }

    public com.uslc.po.jpa.entity.PurchaseOrder getPo() {
        return this.po;
    }

    private PurchaseOrderDetail[] getDetails() {
        if (this.details == null) {
            this.details = new PurchaseOrderDetail[0];
            List dts = this.po.getPurchaseOrderDetails();
            if (dts != null) {
                this.details = new PurchaseOrderDetail[dts.size()];
                int i = 0;
                while (i < dts.size()) {
                    this.details[i] = (PurchaseOrderDetail)dts.get(i);
                    ++i;
                }
            }
        }
        return this.details;
    }

    private HashMap<Integer, PackingDetail[]> getPackingDetail() throws Exception {
        if (this.packingDetails == null) {
            this.packingDetails = new HashMap();
            PurchaseOrderDetail[] arrpurchaseOrderDetail = this.getDetails();
            int n = arrpurchaseOrderDetail.length;
            int n2 = 0;
            while (n2 < n) {
                PurchaseOrderDetail pod = arrpurchaseOrderDetail[n2];
                PackingDetail[] pds = new PackingDetail[]{};
                List pdList = PackingDetailRepo.findByPurchaseOrderDetail((PurchaseOrderDetail)pod);
                if (pdList != null) {
                    pds = new PackingDetail[pdList.size()];
                    int i = 0;
                    while (i < pdList.size()) {
                        pds[i] = (PackingDetail)pdList.get(i);
                        ++i;
                    }
                }
                this.packingDetails.put(pod.getId(), pds);
                ++n2;
            }
        }
        return this.packingDetails;
    }

    public String getCode() {
        return this.getPo().getReferenceNumber();
    }

    public String getStatus() throws Exception {
        if (this.status == null || this.status.compareTo("") == 0) {
            int finished = 0;
            int missing = 0;
            int total = 0;
            PurchaseOrderDetail[] arrpurchaseOrderDetail = this.getDetails();
            int n = arrpurchaseOrderDetail.length;
            int n2 = 0;
            while (n2 < n) {
                PurchaseOrderDetail pod = arrpurchaseOrderDetail[n2];
                PackingDetail[] arrpackingDetail = this.getPackingDetail().get(pod.getId());
                int n3 = arrpackingDetail.length;
                int n4 = 0;
                while (n4 < n3) {
                    PackingDetail pd = arrpackingDetail[n4];
                    boolean completed = true;
                    if (pd.getCarton() == null) {
                        completed = false;
                        break;
                    }
                    if (completed) {
                        ++finished;
                    } else {
                        ++missing;
                    }
                    ++total;
                    ++n4;
                }
                ++n2;
            }
            this.status = finished == this.getDetails().length ? "compl [" + total + "]" : "pend [ " + finished + "/" + total + " ]";
            this.getLog().info((Object)("total[" + total + "]{finished:" + finished + ",pending:" + missing + "}"));
        }
        return this.status;
    }

    private Logger getLog() {
        if (this.log == null) {
            this.log = Logger.getLogger((Class)PurchaseOrder.class);
            PropertyConfigurator.configure((String)"log4j.properties");
        }
        return this.log;
    }

    public static int getNextId() {
        return PurchaseOrderRepo.getNextId();
    }

    public static String getNextReferenceNumber() {
        String lastRef = PurchaseOrderRepo.getLastReferenceNumber();
        String newRef = "E";
        if (lastRef.compareTo("") == 0) {
            newRef = "E0000001-0001";
        } else {
            int n = Integer.parseInt(lastRef.substring(1, lastRef.indexOf("-")));
            System.out.println(String.valueOf(n) + " has " + String.valueOf(n).length() + " digits");
            int i = 0;
            while (i < 7 - String.valueOf(++n).length()) {
                newRef = String.valueOf(newRef) + "0";
                ++i;
            }
            newRef = String.valueOf(newRef) + n + "-0001";
        }
        return newRef;
    }

    public static com.uslc.po.jpa.entity.PurchaseOrder createPurchaseOrderCascade(String departmentNumber, String referenceNumber, String shipFrom, String shipTo, int totalCartons, int totalItems, List<NewPurchaseOrderComposite.PODetailData> poDetails) throws Exception {
        com.uslc.po.jpa.entity.PurchaseOrder po;
        block6 : {
            po = null;
            try {
                if (PurchaseOrderRepo.findPOByRefNumber((String)referenceNumber) == null) {
                    po = new com.uslc.po.jpa.entity.PurchaseOrder();
                    ArrayList<PurchaseOrderDetail> purchaseOrderDetailList = new ArrayList<PurchaseOrderDetail>();
                    Date timestamp = Calendar.getInstance().getTime();
                    po.setDeleted(false);
                    po.setDepartmentNumber(departmentNumber);
                    po.setPurchaseOrderDetails(purchaseOrderDetailList);
                    po.setReferenceNumber(referenceNumber);
                    po.setShipFrom(shipFrom);
                    po.setShipTo(shipTo);
                    po.setTotalCartons(totalCartons);
                    po.setTotalItems(totalItems);
                    po.setTimestamp(timestamp);
                    int sku = 0;
                    for (NewPurchaseOrderComposite.PODetailData ob : poDetails) {
                        PurchaseOrderDetail pod = new PurchaseOrderDetail();
                        Upc upc = ob.getUpc();
                        int qty = ob.getQty();
                        int itemsPerCarton = ob.getItemsPerCarton();
                        ArrayList<PackingDetail> packingDetailList = new ArrayList<PackingDetail>();
                        boolean preticketed = ob.isPreticketed();
                        pod.setDeleted(false);
                        pod.setPackingDetails(packingDetailList);
                        pod.setPreticketed(preticketed);
                        pod.setPurchaseOrder(po);
                        pod.setTotal(qty);
                        pod.setUpc(upc);
                        pod.setTimestamp(timestamp);
                        int cartons = (int)Math.ceil(new Double(qty) / new Double(itemsPerCarton));
                        int qtyControl = qty;
                        int i = 0;
                        while (i < cartons) {
                            int itemsInCarton = 0;
                            itemsInCarton = qtyControl < itemsPerCarton ? qtyControl : itemsPerCarton;
                            qtyControl -= itemsInCarton;
                            PackingDetail pd = new PackingDetail();
                            pd.setDeleted(false);
                            pd.setPurchaseOrderDetail(pod);
                            pd.setQuantity(itemsInCarton);
                            pd.setSku(++sku);
                            packingDetailList.add(pd);
                            ++i;
                        }
                        purchaseOrderDetailList.add(pod);
                    }
                    if (!PurchaseOrderRepo.getJpa().persist((Object)po)) {
                        throw new Exception("there was a problem persisting the purchase order");
                    }
                    break block6;
                }
                throw new Exception("purchase order reference number already exists in the system.");
            }
            catch (Exception e) {
                e.printStackTrace();
                throw new Exception(e.getMessage());
            }
        }
        return po;
    }

    public static com.uslc.po.jpa.entity.PurchaseOrder createPurchaseOrder(int departmentNumber, String referenceNumber, String shipFrom, String shipTo, int totalCartons, int totalItems, List<Object[]> poDetails) throws Exception {
        com.uslc.po.jpa.entity.PurchaseOrder po = null;
        po.setReferenceNumber(referenceNumber);
        po.setShipFrom(shipFrom);
        po.setShipTo(shipTo);
        po.setTotalCartons(totalCartons);
        po.setTotalItems(totalItems);
        po.setDeleted(true);
        po = PurchaseOrderRepo.createPurchaseOrder((com.uslc.po.jpa.entity.PurchaseOrder)po);
        if (po.getId() > 0) {
            ArrayList<PurchaseOrderDetail> purchaseOrderDetailList = new ArrayList<PurchaseOrderDetail>();
            int sku = 0;
            for (Object[] ob : poDetails) {
                Upc upc = (Upc)ob[0];
                int qty = Integer.parseInt(String.valueOf(ob[1]));
                int itemsPerCarton = Integer.parseInt(String.valueOf(ob[2]));
                boolean preticketed = Boolean.parseBoolean(String.valueOf(ob[3]));
                PurchaseOrderDetail pod = PurchaseOrderDetailRepo.createPurchaseOrderDetail((boolean)preticketed, (int)qty, (com.uslc.po.jpa.entity.PurchaseOrder)po, (Upc)upc);
                int cartons = (int)Math.ceil(new Double(totalItems) / new Double(itemsPerCarton));
                ArrayList<PackingDetail> packingDetailList = new ArrayList<PackingDetail>();
                int i = 0;
                while (i < cartons) {
                    int itemsInCarton = 0;
                    itemsInCarton = qty < itemsPerCarton ? qty : itemsPerCarton;
                    qty -= itemsInCarton;
                    PackingDetail pd = PackingDetailRepo.createPackingDetail((int)itemsInCarton, (int)(++sku), (PurchaseOrderDetail)pod);
                    packingDetailList.add(pd);
                    ++i;
                }
                PurchaseOrderDetailRepo.setDeleted((PurchaseOrderDetail)pod, (boolean)false);
                purchaseOrderDetailList.add(pod);
            }
        } else {
            return null;
        }
        return po;
    }

    public static void main(String[] args) {
        ArrayList<PurchaseOrderDetail> podList = new ArrayList<PurchaseOrderDetail>();
        Upc upc = (Upc)UpcRepo.findAll().get(0);
        com.uslc.po.jpa.entity.PurchaseOrder po = new com.uslc.po.jpa.entity.PurchaseOrder();
        po.setDepartmentNumber("1234");
        po.setPurchaseOrderDetails(podList);
        po.setReferenceNumber("98789798");
        po.setShipFrom("from");
        po.setShipTo("to");
        po.setTotalCartons(4);
        po.setTotalItems(40);
        PurchaseOrderDetail pod1 = new PurchaseOrderDetail();
        pod1.setPreticketed(false);
        pod1.setPurchaseOrder(po);
        pod1.setTotal(27);
        pod1.setUpc(upc);
        pod1.setDeleted(false);
        PurchaseOrderDetail pod2 = new PurchaseOrderDetail();
        pod2.setPreticketed(false);
        pod2.setPurchaseOrder(po);
        pod2.setTotal(13);
        pod2.setUpc(upc);
        pod2.setDeleted(false);
        podList.add(pod1);
        podList.add(pod2);
        try {
            PurchaseOrderRepo.getJpa().persist((Object)po);
            System.out.println(String.valueOf(po.getId()) + ", " + pod1.getId() + ", " + pod2.getId());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, PoStatistics> getPoStatistics() {
        if (this.poStats == null) {
            this.poStats = new HashMap();
            PurchaseOrderDetail[] arrpurchaseOrderDetail = this.getDetails();
            int n = arrpurchaseOrderDetail.length;
            int n2 = 0;
            while (n2 < n) {
                int boxes;
                Upc upc;
                PurchaseOrderDetail pod = arrpurchaseOrderDetail[n2];
                if (this.poStats.get(pod.getUpc().getColor().getNumber()) == null) {
                    upc = pod.getUpc();
                    boxes = 0;
                    for (PackingDetail pd : pod.getPackingDetails()) {
                        if (pd.getDeleted()) continue;
                        ++boxes;
                    }
                    this.poStats.put(upc.getColor().getNumber(), new PoStatistics(upc.getColor().getName(), pod.getTotal(), boxes));
                } else {
                    upc = pod.getUpc();
                    boxes = 0;
                    for (PackingDetail pd : pod.getPackingDetails()) {
                        if (pd.getDeleted()) continue;
                        ++boxes;
                    }
                    PoStatistics pos = this.poStats.get(pod.getUpc().getColor().getNumber());
                    this.poStats.put(upc.getColor().getNumber(), new PoStatistics(pos.getColor(), pod.getTotal() + pos.getUnits(), boxes += pos.getBoxes()));
                }
                ++n2;
            }
        }
        return this.poStats;
    }

    public class PoStatistics {
        private String color;
        private int units;
        private int boxes;

        public PoStatistics(String color, int units, int boxes) {
            this.color = "";
            this.units = 0;
            this.boxes = 0;
            this.color = color;
            this.units = units;
            this.boxes = boxes;
        }

        public String getColor() {
            return this.color;
        }

        public int getUnits() {
            return this.units;
        }

        public int getBoxes() {
            return this.boxes;
        }
    }

}

