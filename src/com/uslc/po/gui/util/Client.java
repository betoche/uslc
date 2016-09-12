/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  com.ibm.icu.util.Calendar
 *  com.jasperassistant.designer.viewer.IReportViewer
 *  com.jasperassistant.designer.viewer.ViewerComposite
 *  com.uslc.po.jpa.comparator.PackageDetailComparator
 *  com.uslc.po.jpa.comparator.PurchaseOrderDetailComparator
 *  com.uslc.po.jpa.comparator.SizeComparator
 *  com.uslc.po.jpa.entity.Carton
 *  com.uslc.po.jpa.entity.Color
 *  com.uslc.po.jpa.entity.Item
 *  com.uslc.po.jpa.entity.PackingDetail
 *  com.uslc.po.jpa.entity.PurchaseOrder
 *  com.uslc.po.jpa.entity.PurchaseOrderByUser
 *  com.uslc.po.jpa.entity.PurchaseOrderDetail
 *  com.uslc.po.jpa.entity.ScanDetail
 *  com.uslc.po.jpa.entity.Size
 *  com.uslc.po.jpa.entity.Upc
 *  com.uslc.po.jpa.entity.User
 *  com.uslc.po.jpa.logic.UpcRepo
 *  com.uslc.po.jpa.util.Constants
 *  com.uslc.po.jpa.util.UslcJpa
 *  net.sf.jasperreports.engine.JRException
 *  net.sf.jasperreports.engine.JasperPrint
 *  org.apache.log4j.Logger
 *  org.apache.log4j.PropertyConfigurator
 *  org.eclipse.swt.events.SelectionAdapter
 *  org.eclipse.swt.events.SelectionEvent
 *  org.eclipse.swt.events.SelectionListener
 *  org.eclipse.swt.graphics.Image
 *  org.eclipse.swt.graphics.Rectangle
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Button
 *  org.eclipse.swt.widgets.Combo
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.Group
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.MessageBox
 *  org.eclipse.swt.widgets.Monitor
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Table
 *  org.eclipse.swt.widgets.TableItem
 *  org.eclipse.swt.widgets.Text
 */
package com.uslc.po.gui.util;

import com.ibm.icu.util.Calendar;
import com.jasperassistant.designer.viewer.IReportViewer;
import com.jasperassistant.designer.viewer.ViewerComposite;
import com.uslc.po.gui.client.ClientPackingDetailTable;
import com.uslc.po.gui.client.ClientPurchaseOrderDetailTable;
import com.uslc.po.gui.client.ClientPurchaseOrderSumary;
import com.uslc.po.gui.client.ClientScannedItemsTable;
import com.uslc.po.gui.client.POClient;
import com.uslc.po.gui.util.ImageUtils;
import com.uslc.po.jpa.comparator.PackageDetailComparator;
import com.uslc.po.jpa.comparator.PurchaseOrderDetailComparator;
import com.uslc.po.jpa.comparator.SizeComparator;
import com.uslc.po.jpa.entity.Carton;
import com.uslc.po.jpa.entity.Color;
import com.uslc.po.jpa.entity.Item;
import com.uslc.po.jpa.entity.PackingDetail;
import com.uslc.po.jpa.entity.PurchaseOrder;
import com.uslc.po.jpa.entity.PurchaseOrderByUser;
import com.uslc.po.jpa.entity.PurchaseOrderDetail;
import com.uslc.po.jpa.entity.ScanDetail;
import com.uslc.po.jpa.entity.Size;
import com.uslc.po.jpa.entity.Upc;
import com.uslc.po.jpa.entity.User;
import com.uslc.po.jpa.logic.UpcRepo;
import com.uslc.po.jpa.util.Constants;
import com.uslc.po.jpa.util.UslcJpa;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class Client {
    private POClient client = null;
    private UslcJpa jpa = null;
    private Logger log = null;
    private User user = null;
    private MessageBox errorBox = null;
    private MessageBox informationBox = null;
    private MessageBox questionBox = null;
    private static SimpleDateFormat sdf = null;
    private int cartonQty = 0;

    public MessageBox getErrorBox(String message) {
        if (this.errorBox == null) {
            this.errorBox = new MessageBox(this.getClient().getShell(), 1);
            this.errorBox.setText(Constants.MESSAGE_BOX_DIAG_TITLE.toString());
        }
        this.errorBox.setMessage(message);
        this.errorBox.open();
        return this.errorBox;
    }

    public MessageBox getInformationBox(String message) {
        if (this.informationBox == null) {
            this.informationBox = new MessageBox(this.getClient().getShell(), 2);
        }
        this.informationBox.setMessage(message);
        this.informationBox.open();
        return this.informationBox;
    }

    public int getQuestionBox(String message) {
        if (this.questionBox == null) {
            this.questionBox = new MessageBox(this.getClient().getShell(), 196);
        }
        this.questionBox.setMessage(message);
        return this.questionBox.open();
    }

    public Client(POClient client, User user) {
        this.client = client;
        this.user = user;
    }

    public void selectingPurchaseOrder() throws JRException {
        this.cleanTicketsView();
        this.cleanScanningArea();
        this.cleanScaningInfo();
        this.fillFiltersFromPurchaseOrder();
        this.showPurchaseOrderDetail();
        this.showPackingDetail();
        this.showPurchaseOrderSummary();
    }

    public void selectingFilter() throws JRException {
        this.cleanTicketsView();
        this.cleanScanningArea();
        this.cleanScaningInfo();
        this.showPurchaseOrderDetail();
        this.showPackingDetail();
    }

    public void selectingPurchaseOrderDetail() throws JRException {
        this.cleanScanningArea();
        this.cleanScaningInfo();
        this.cleanTicketsView();
        this.showPackingDetail();
    }

    public void selectingPackingDetail() throws JRException {
        if (this.getSelectedPackingDetail() != null) {
            this.loadScannedItems(null);
            this.showTickets(this.getSelectedPackingDetail().getPd());
        }
    }

    public void selectingScanDetail() {
        this.showScanningInfo();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void addCarton() {
        if (this.getSelectedPurchaseOrderDetail() != null) {
            try {
                final Shell shell = new Shell(this.getClient().getShell(), 83968);
                shell.setData("qty", (Object)0);
                shell.setSize(102, 100);
                shell.setText(this.getClient().getText());
                Rectangle bounds = Display.getDefault().getPrimaryMonitor().getBounds();
                Rectangle rect = shell.getBounds();
                int x = bounds.x + (bounds.width - rect.width) / 2;
                int y = bounds.y + (bounds.height - rect.height) / 2;
                shell.setLocation(x, y);
                shell.setLayout((Layout)new GridLayout(2, false));
                Label qtyLbl = new Label((Composite)shell, 0);
                qtyLbl.setLayoutData((Object)new GridData(4, 16777216, false, false, 2, 1));
                qtyLbl.setText("quantity:");
                final Text qtyTxt = new Text((Composite)shell, 0);
                qtyTxt.setLayoutData((Object)new GridData(4, 16777216, false, false, 2, 1));
                qtyTxt.setText("");
                Button cancelBtn = new Button((Composite)shell, 8);
                cancelBtn.setLayoutData((Object)new GridData(131072, 16777216, false, false, 1, 1));
                cancelBtn.setText("cancel");
                cancelBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

                    public void widgetSelected(SelectionEvent arg0) {
                        shell.close();
                    }
                });
                Button okBtn = new Button((Composite)shell, 8);
                okBtn.setLayoutData((Object)new GridData(131072, 16777216, false, false, 1, 1));
                okBtn.setText("ok");
                okBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

                    public void widgetSelected(SelectionEvent arg0) {
                        try {
                            Client.access$0(Client.this, Integer.parseInt(qtyTxt.getText()));
                            shell.close();
                        }
                        catch (Exception e) {
                            Client.this.getLog().error((Object)"error on adding a new carton", (Throwable)e);
                            Client.this.getErrorBox(e.getMessage());
                        }
                    }
                });
                shell.open();
                shell.layout();
                Display display = shell.getDisplay();
                while (!shell.isDisposed()) {
                    if (display.readAndDispatch()) continue;
                    display.sleep();
                }
                if (this.cartonQty <= 0) return;
                PurchaseOrderDetail pod = this.getSelectedPurchaseOrderDetail().getPod();
                PackingDetail pd = new PackingDetail();
                pd.setDeleted(false);
                pd.setPurchaseOrderDetail(pod);
                pd.setQuantity(this.cartonQty);
                int newSku = this.getLastSku(pod.getPurchaseOrder()) + 1;
                pd.setSku(newSku);
                pd = (PackingDetail)this.getJpa().merge((Object)pd);
                if (pd.getId() <= 0) throw new Exception("the packingdetail { purchase_order_detail_id:" + pod.getId() + ", quantity:" + this.cartonQty + ", sku:" + newSku + " }");
                pod.getPackingDetails().add(pd);
                this.showPackingDetail();
                this.cleanScanningArea();
                this.showScanningInfo();
                TableItem[] items = this.getClient().getPackingDetailTbl().getItems();
                int rowIndex = 0;
                TableItem[] arrtableItem = items;
                int n = arrtableItem.length;
                int n2 = 0;
                while (n2 < n) {
                    TableItem item = arrtableItem[n2];
                    ClientPackingDetailTable cpdt = (ClientPackingDetailTable)item.getData();
                    if (cpdt.getPd().getId() == pd.getId()) {
                        this.getClient().getPackingDetailTbl().select(rowIndex);
                        return;
                    }
                    ++rowIndex;
                    ++n2;
                }
                return;
            }
            catch (Exception e2) {
                this.getLog().error((Object)"error", (Throwable)e2);
                return;
            }
        } else {
            this.getInformationBox("please select a purchase order detail first");
        }
    }

    public void scanning() throws Exception {
        boolean success = false;
        String upcCode = this.getClient().getScannedBarTxt().getText().trim();
        Upc scannedUpc = UpcRepo.findByCode((String)upcCode);
        this.getClient().getScannedBarTxt().setText("");
        Carton carton = null;
        ScanDetail sd = null;
        if (this.getSelectedPackingDetail() == null) {
            throw new Exception("please select a packing detail first");
        }
        if (scannedUpc == null) {
            throw new Exception("the passed upc does not match with anyone in the database, please contact the sysadmin");
        }
        if (scannedUpc.getId() != this.getSelectedPackingDetail().getPd().getPurchaseOrderDetail().getUpc().getId()) {
            throw new Exception("the packing details requires upc [" + this.getSelectedPackingDetail().getPd().getPurchaseOrderDetail().getUpc().getUpcCode() + "], not the one scanned [" + scannedUpc.getUpcCode() + "]");
        }
        carton = this.getSelectedPackingDetail().getPd().getCarton();
        if (carton == null) {
            carton = new Carton();
            carton.setCompleted(false);
            carton.setDeleted(false);
            carton.setPackingDetail(this.getSelectedPackingDetail().getPd());
            carton.setReferenceNumber(this.getSelectedPackingDetail().getPd().getPurchaseOrderDetail().getPurchaseOrder().getReferenceNumber());
            carton.setUpcCode(this.getSelectedPackingDetail().getPd().getPurchaseOrderDetail().getUpc().getUpcCode());
            carton.setUser(this.getUser());
            carton = (Carton)this.getJpa().merge((Object)carton);
            if (carton.getId() > 0) {
                this.getSelectedPackingDetail().getPd().setCarton(carton);
            }
        }
        if (carton != null && carton.getId() > 0) {
            if (this.getSelectedPackingDetail().getPd().getQuantity() <= Client.getNumberOfScannedItems(carton) && this.getQuestionBox("the carton is already completed, do you want to add an extra item?") != 64) {
                return;
            }
            sd = new ScanDetail();
            sd.setCarton(carton);
            sd.setDeleted(false);
            sd.setTimestamp(Calendar.getInstance().getTime());
            sd.setUpc(scannedUpc);
            sd.setUpcReferenceNumber(scannedUpc.getUpcCode());
            sd = (ScanDetail)this.getJpa().merge((Object)sd);
            if (sd.getId() > 0) {
                carton.addScanDetail(sd);
                success = true;
            } else {
                throw new Exception("there was a problem adding the scanned upc to the carton # " + carton.getPackingDetail().getSku());
            }
        }
        if (success) {
            if (Client.getNumberOfScannedItems(carton) >= this.getSelectedPackingDetail().getQty()) {
                carton.setCompleted(true);
                if (!this.getJpa().persist((Object)carton)) {
                    this.getErrorBox("there's an error trying to mark the carton # " + carton.getPackingDetail().getSku() + " as completed");
                }
            }
            this.loadScannedItems(sd);
            this.showPurchaseOrderSummary();
            this.updatePackingDetailTableItem();
            this.updatePurchaseOrderDetailTableItem();
            this.showScanningInfo();
            this.showTickets(this.getSelectedPackingDetail().getPd());
            if (Client.getNumberOfScannedItems(carton) >= this.getSelectedPackingDetail().getQty() && this.getClient().getAutoPrintingChk().getSelection()) {
                this.getClient().printCartonTicket(carton);
            }
            this.getClient().getScannedBarTxt().setFocus();
        }
    }

    public void deleteScan() throws Exception {
        if (this.getSelectedScannedItem() != null) {
            this.getSelectedScannedItem().getSd().setDeleted(true);
            if (this.getJpa().persist((Object)this.getSelectedScannedItem().getSd())) {
                if (Client.getNumberOfScannedItems(this.getSelectedScannedItem().getSd().getCarton()) < this.getSelectedPackingDetail().getQty()) {
                    this.getSelectedPackingDetail().getPd().getCarton().setCompleted(false);
                    if (!this.getJpa().persist((Object)this.getSelectedPackingDetail().getPd().getCarton())) {
                        throw new Exception("there was a problem setting the carton #" + this.getSelectedPackingDetail().getPd().getSku() + " deleted");
                    }
                }
                this.loadScannedItems(null);
                this.cleanScaningInfo();
                this.showPurchaseOrderSummary();
                this.updatePackingDetailTableItem();
                this.updatePurchaseOrderDetailTableItem();
            } else {
                this.getErrorBox("there was an error while deleting the selected scan");
            }
        } else {
            this.getInformationBox("please select a scanned item first");
        }
    }

    public void cleanCarton() throws Exception {
        if (this.getSelectedPackingDetail() != null) {
            while (this.getClient().getScannedItemsTbl().getItems().length > 0) {
                this.getClient().getScannedItemsTbl().setSelection(this.getClient().getScannedItemsTbl().getItems().length - 1);
                this.deleteScan();
            }
            this.getInformationBox("carton #" + this.getSelectedPackingDetail().getSku() + " cleaned.");
        } else {
            this.getInformationBox("please select a packing detail first");
        }
    }

    public void deleteCarton() throws Exception {
        if (this.getSelectedPackingDetail() != null) {
            if (this.getQuestionBox("do you want to delete the carton #" + this.getSelectedPackingDetail().getSku() + "?") == 64) {
                this.getSelectedPackingDetail().getPd().setDeleted(true);
                if (this.getJpa().persist((Object)this.getSelectedPackingDetail().getPd())) {
                    this.getInformationBox("carton #" + this.getSelectedPackingDetail().getSku() + " deleted correctly.");
                    this.showPurchaseOrderSummary();
                    this.cleanTicketsView();
                    this.cleanScaningInfo();
                    this.showPurchaseOrderDetail();
                    this.showPackingDetail();
                    this.cleanScanningArea();
                } else {
                    this.getErrorBox("there was an error trying to delete the carton #" + this.getSelectedPackingDetail().getSku());
                }
            }
        } else {
            this.getInformationBox("please select a packing detail first");
        }
    }

    public void completeCarton() throws Exception {
        if (this.getSelectedPackingDetail() != null && this.getSelectedPackingDetail().getPd() != null && this.getSelectedPackingDetail().getPd().getCarton() != null) {
            this.getSelectedPackingDetail().getPd().getCarton().setCompleted(true);
            if (!this.getJpa().persist((Object)this.getSelectedPackingDetail().getPd().getCarton())) {
                throw new Exception("there was a problem trying to mark the carton as completed.");
            }
            this.getInformationBox("carton #" + this.getSelectedPackingDetail().getPd().getSku() + " was marked as completed.");
            this.getClient().getBtnCompleted().setSelection(this.getSelectedPackingDetail().getPd().getCarton().getCompleted());
        }
    }

    public void loadAvailablePurchaseOrders() {
        List purchaseOrdersByUser = this.getUser().getPurchaseOrders();
        for (PurchaseOrderByUser poByUser : purchaseOrdersByUser) {
            if (poByUser.getDeleted() || poByUser.getPurchaseOrder().getDeleted()) continue;
            PurchaseOrder po = poByUser.getPurchaseOrder();
            this.getClient().getPurchaseOrderCbx().add(po.getReferenceNumber());
            this.getClient().getPurchaseOrderCbx().setData(po.getReferenceNumber(), (Object)po);
        }
    }

    public void cleanTicketsView() throws JRException {
        this.getClient().getTicketViewer().getReportViewer().setDocument(this.getClient().getJasperPrintPolybag(null));
        this.getClient().getPolybagViewer().getReportViewer().setDocument(this.getClient().getJasperPrintPolybag(null));
    }

    public void cleanScanningArea() {
        this.loadScannedItems(null);
    }

    public void cleanScaningInfo() {
        this.showScanningInfo();
    }

    public void fillFiltersFromPurchaseOrder() {
        if (this.getSelectedPurchaseOrder() != null) {
            ArrayList<Item> itemList = new ArrayList<Item>();
            ArrayList<Color> colorList = new ArrayList<Color>();
            ArrayList<Size> sizeList = new ArrayList<Size>();
            for (PurchaseOrderDetail pod : this.getSelectedPurchaseOrder().getPurchaseOrderDetails()) {
                Upc upc = pod.getUpc();
                if (!itemList.contains((Object)upc.getItem())) {
                    itemList.add(upc.getItem());
                }
                if (!colorList.contains((Object)upc.getColor())) {
                    colorList.add(upc.getColor());
                }
                if (sizeList.contains((Object)upc.getSize())) continue;
                sizeList.add(upc.getSize());
            }
            this.getClient().getItemCbx().removeAll();
            for (Item item : itemList) {
                this.getClient().getItemCbx().add(String.valueOf(item.getCode()));
                this.getClient().getItemCbx().setData(String.valueOf(item.getCode()), (Object)item);
            }
            this.getClient().getColorCbx().removeAll();
            for (Color color : colorList) {
                this.getClient().getColorCbx().add(color.getName());
                this.getClient().getColorCbx().setData(color.getName(), (Object)color);
            }
            this.getClient().getSizeCbx().removeAll();
            Collections.sort(sizeList, new SizeComparator());
            for (Size size : sizeList) {
                String sizeStr = "";
                sizeStr = this.getSelectedPurchaseOrder().getReferenceNumber().endsWith("11") ? String.valueOf(size.getWaist()) : String.valueOf(String.valueOf(size.getWaist())) + " x " + String.valueOf(size.getInseam());
                this.getClient().getSizeCbx().add(sizeStr);
                this.getClient().getSizeCbx().setData(sizeStr, (Object)size);
            }
        }
    }

    public void showPurchaseOrderDetail() {
        ArrayList<PurchaseOrderDetail> purchaseOrderDetailList = new ArrayList<PurchaseOrderDetail>();
        for (PurchaseOrderDetail pod : this.getSelectedPurchaseOrder().getPurchaseOrderDetails()) {
            if (pod.getDeleted()) continue;
            Upc upc = pod.getUpc();
            boolean add = true;
            if (this.getSelectedItem() != null && upc.getItem().getId() != this.getSelectedItem().getId()) {
                add = false;
            }
            if (this.getSelectedColor() != null && upc.getColor().getId() != this.getSelectedColor().getId()) {
                add = false;
            }
            if (this.getSelectedSize() != null && upc.getSize().getId() != this.getSelectedSize().getId()) {
                add = false;
            }
            if (!add) continue;
            purchaseOrderDetailList.add(pod);
        }
        this.getClient().getPoDetailTbl().removeAll();
        int row = 0;
        Collections.sort(purchaseOrderDetailList, new PurchaseOrderDetailComparator());
        for (PurchaseOrderDetail pod2 : purchaseOrderDetailList) {
            ClientPurchaseOrderDetailTable clientPurchaseOrder = new ClientPurchaseOrderDetailTable(pod2, ++row);
            TableItem item = new TableItem(this.getClient().getPoDetailTbl(), 8);
            item.setText(clientPurchaseOrder.getColumnValues());
            item.setData((Object)clientPurchaseOrder);
        }
        if (row > 0) {
            this.getClient().getPoDetailLbl().setText("po detail: " + row);
        } else {
            this.getClient().getPoDetailLbl().setText("po detail: ");
        }
    }

    public void showPackingDetail() {
        this.getClient().getPackingDetailTbl().removeAll();
        TableItem[] items = this.getClient().getPoDetailTbl().getItems();
        if (items != null && items.length > 0) {
            ArrayList<PackingDetail> packingDetailList = new ArrayList<PackingDetail>();
            TableItem[] arrtableItem = items;
            int n = arrtableItem.length;
            int n2 = 0;
            while (n2 < n) {
                TableItem ti = arrtableItem[n2];
                ClientPurchaseOrderDetailTable cpodt = (ClientPurchaseOrderDetailTable)ti.getData();
                PurchaseOrderDetail pod = cpodt.getPod();
                if (!pod.getDeleted()) {
                    for (PackingDetail pd : pod.getPackingDetails()) {
                        if (pd.getDeleted()) continue;
                        packingDetailList.add(pd);
                    }
                }
                ++n2;
            }
            Collections.sort(packingDetailList, new PackageDetailComparator());
            for (PackingDetail pd : packingDetailList) {
                boolean add = true;
                if (this.getSelectedPurchaseOrderDetail() != null) {
                    add = false;
                    for (PackingDetail pd2 : this.getSelectedPurchaseOrderDetail().getPod().getPackingDetails()) {
                        if (pd.getId() != pd2.getId()) continue;
                        add = true;
                    }
                }
                if (!add) continue;
                ClientPackingDetailTable clientPackingDetail = new ClientPackingDetailTable(pd);
                TableItem item = new TableItem(this.getClient().getPackingDetailTbl(), 8);
                item.setText(clientPackingDetail.getColumnValues());
                item.setData((Object)clientPackingDetail);
            }
            if (packingDetailList.size() > 0) {
                this.getClient().getPackingDetailLbl().setText("packing detail: " + this.getClient().getPackingDetailTbl().getItems().length);
            } else {
                this.getClient().getPackingDetailLbl().setText("packing detail: ");
            }
        }
    }

    public void showPurchaseOrderSummary() {
        if (this.getSelectedPurchaseOrder() != null) {
            ClientPurchaseOrderSumary cpos = new ClientPurchaseOrderSumary(this.getSelectedPurchaseOrder());
            cpos.addInfoToComposites(this.getClient().getColorsComposite(), this.getClient().getGrpPoDetails(), this.getClient().getGrpPackingDetails());
        }
    }

    public void showScanningInfo() {
        if (this.getSelectedScannedItem() != null) {
            Upc upc = this.getSelectedScannedItem().getUpc();
            this.getClient().getInfoUpcLbl().setText("upc: " + upc.getUpcCode());
            this.getClient().getBarcodeLbl().setImage(ImageUtils.resize(ImageUtils.getBarcodeImage(this.getClient().getDisplay(), upc.getUpcCode()), 150, 75));
            this.getClient().getInfoSizeLbl().setText(this.getSelectedScannedItem().getSize());
            this.getClient().getInfoColorLbl().setText(this.getSelectedScannedItem().getColor());
            this.getClient().getInfoItemLbl().setText(String.valueOf(upc.getItem().getCode()));
            this.getClient().getInfoSkuLabel().setText(String.valueOf(this.getSelectedScannedItem().getSd().getCarton().getPackingDetail().getSku()));
            this.getClient().getInfoPreticketedCbx().setSelection(this.getSelectedScannedItem().getSd().getCarton().getPackingDetail().getPurchaseOrderDetail().getPreticketed());
        } else {
            this.getClient().getInfoUpcLbl().setText("upc:");
            this.getClient().getBarcodeLbl().setImage(null);
            this.getClient().getInfoSizeLbl().setText("");
            this.getClient().getInfoColorLbl().setText("");
            this.getClient().getInfoItemLbl().setText("");
            this.getClient().getInfoSkuLabel().setText("");
            this.getClient().getInfoPreticketedCbx().setSelection(false);
        }
    }

    public void loadScannedItems(ScanDetail scannedDetail) {
        Table table = this.getClient().getScannedItemsTbl();
        table.removeAll();
        if (this.getSelectedPackingDetail() != null && this.getSelectedPackingDetail().getPd().getCarton() != null) {
            int order = 0;
            for (ScanDetail sd : this.getSelectedPackingDetail().getPd().getCarton().getScanDetails()) {
                if (!sd.getDeleted()) {
                    ClientScannedItemsTable clientScanned = new ClientScannedItemsTable(++order, sd);
                    TableItem item = new TableItem(this.getClient().getScannedItemsTbl(), 8);
                    item.setText(clientScanned.getColumnValues());
                    item.setData((Object)clientScanned);
                }
                if (scannedDetail == null || sd.getId() != scannedDetail.getId()) continue;
                this.getClient().getScannedItemsTbl().setSelection(order - 1);
            }
            this.getClient().getBtnCompleted().setSelection(this.getSelectedPackingDetail().getPd().getCarton().getCompleted());
        }
    }

    public void showTickets(PackingDetail pd) throws JRException {
        this.getClient().getTicketViewer().getReportViewer().setDocument(this.getClient().getJasperPrintTicket(pd));
        this.getClient().getPolybagViewer().getReportViewer().setDocument(this.getClient().getJasperPrintPolybag(pd));
        this.getClient().getTicketViewer().getReportViewer().setZoomMode(2);
        this.getClient().getPolybagViewer().getReportViewer().setZoomMode(2);
    }

    public void updatePackingDetailTableItem() {
        int i = 0;
        while (i < this.getClient().getPackingDetailTbl().getItems().length) {
            ClientPackingDetailTable cpdt = (ClientPackingDetailTable)this.getClient().getPackingDetailTbl().getItems()[i].getData();
            if (cpdt.getPd().getId() == this.getSelectedPackingDetail().getPd().getId()) {
                cpdt = new ClientPackingDetailTable(cpdt.getPd());
                this.getClient().getPackingDetailTbl().getItems()[i].setText(cpdt.getColumnValues());
                this.getClient().getPackingDetailTbl().getItems()[i].setData((Object)cpdt);
                break;
            }
            ++i;
        }
    }

    public void updatePurchaseOrderDetailTableItem() {
        int i = 0;
        while (i < this.getClient().getPoDetailTbl().getItems().length) {
            ClientPurchaseOrderDetailTable cpodt = (ClientPurchaseOrderDetailTable)this.getClient().getPoDetailTbl().getItems()[i].getData();
            if (cpodt.getPod().getId() == this.getSelectedPackingDetail().getPd().getPurchaseOrderDetail().getId()) {
                cpodt = new ClientPurchaseOrderDetailTable(cpodt.getPod(), cpodt.getRow());
                this.getClient().getPoDetailTbl().getItems()[i].setText(cpodt.getColumnValues());
                this.getClient().getPoDetailTbl().getItems()[i].setData((Object)cpodt);
                this.getLog().info((Object)cpodt.getColumnValues());
                break;
            }
            ++i;
        }
        TableItem[] arrtableItem = this.getClient().getPoDetailTbl().getItems();
        int n = arrtableItem.length;
        int cpodt = 0;
        while (cpodt < n) {
            TableItem item = arrtableItem[cpodt];
            ClientPurchaseOrderDetailTable cpodt2 = (ClientPurchaseOrderDetailTable)item.getData();
            if (cpodt2.getPod().getId() == this.getSelectedPackingDetail().getPd().getPurchaseOrderDetail().getId()) {
                cpodt2 = new ClientPurchaseOrderDetailTable(cpodt2.getPod(), cpodt2.getRow());
                item.setText(cpodt2.getColumnValues());
                break;
            }
            ++cpodt;
        }
    }

    private User getUser() {
        return this.user;
    }

    private UslcJpa getJpa() {
        if (this.jpa == null) {
            this.jpa = new UslcJpa();
        }
        return this.jpa;
    }

    private Logger getLog() {
        if (this.log == null) {
            this.log = Logger.getLogger((Class)Client.class);
            PropertyConfigurator.configure((String)"log4j.properties");
        }
        return this.log;
    }

    private POClient getClient() {
        return this.client;
    }

    private PurchaseOrder getSelectedPurchaseOrder() {
        PurchaseOrder po = null;
        try {
            Combo c = this.getClient().getPurchaseOrderCbx();
            po = (PurchaseOrder)c.getData(c.getItem(c.getSelectionIndex()));
        }
        catch (Exception e) {
            this.getLog().error((Object)"error on retrieving the selected purchase order", (Throwable)e);
        }
        return po;
    }

    private Item getSelectedItem() {
        Item item = null;
        try {
            Combo c = this.getClient().getItemCbx();
            item = (Item)c.getData(c.getItem(c.getSelectionIndex()));
        }
        catch (Exception e) {
            this.getLog().info((Object)"error on retrieving the selected item", (Throwable)e);
        }
        return item;
    }

    private Color getSelectedColor() {
        Color color = null;
        try {
            Combo c = this.getClient().getColorCbx();
            color = (Color)c.getData(c.getItem(c.getSelectionIndex()));
        }
        catch (Exception e) {
            this.getLog().info((Object)"error on retrieving the selected color", (Throwable)e);
        }
        return color;
    }

    private Size getSelectedSize() {
        Size size = null;
        try {
            Combo c = this.getClient().getSizeCbx();
            size = (Size)c.getData(c.getItem(c.getSelectionIndex()));
        }
        catch (Exception e) {
            this.getLog().info((Object)"error on retrieving the selected size", (Throwable)e);
        }
        return size;
    }

    private ClientPurchaseOrderDetailTable getSelectedPurchaseOrderDetail() {
        ClientPurchaseOrderDetailTable cpodt = null;
        try {
            TableItem[] items = this.getClient().getPoDetailTbl().getSelection();
            if (items != null) {
                TableItem[] arrtableItem = items;
                int n = arrtableItem.length;
                int n2 = 0;
                while (n2 < n) {
                    TableItem item = arrtableItem[n2];
                    cpodt = (ClientPurchaseOrderDetailTable)item.getData();
                    ++n2;
                }
            }
        }
        catch (Exception e) {
            this.getLog().error((Object)"error on retrieving the selected purchase order detail", (Throwable)e);
        }
        return cpodt;
    }

    private ClientPackingDetailTable getSelectedPackingDetail() {
        ClientPackingDetailTable cpdt = null;
        try {
            TableItem[] items = this.getClient().getPackingDetailTbl().getSelection();
            if (items != null) {
                TableItem[] arrtableItem = items;
                int n = arrtableItem.length;
                int n2 = 0;
                while (n2 < n) {
                    TableItem item = arrtableItem[n2];
                    cpdt = (ClientPackingDetailTable)item.getData();
                    ++n2;
                }
            }
        }
        catch (Exception e) {
            this.getLog().error((Object)"error on retrieving the selected packing detail", (Throwable)e);
        }
        return cpdt;
    }

    private ClientScannedItemsTable getSelectedScannedItem() {
        ClientScannedItemsTable csit = null;
        try {
            TableItem[] items = this.getClient().getScannedItemsTbl().getSelection();
            if (items != null) {
                TableItem[] arrtableItem = items;
                int n = arrtableItem.length;
                int n2 = 0;
                while (n2 < n) {
                    TableItem item = arrtableItem[n2];
                    csit = (ClientScannedItemsTable)item.getData();
                    ++n2;
                }
            }
        }
        catch (Exception e) {
            this.getLog().error((Object)"error on retrievint the selected scanned item", (Throwable)e);
        }
        return csit;
    }

    public int getLastSku(PurchaseOrder po) {
        int lastSku = 0;
        ArrayList<PackingDetail> pdList = new ArrayList<PackingDetail>();
        for (PurchaseOrderDetail pod : po.getPurchaseOrderDetails()) {
            if (pod.getDeleted()) continue;
            for (PackingDetail pd : pod.getPackingDetails()) {
                pdList.add(pd);
            }
        }
        Collections.sort(pdList, new PackageDetailComparator());
        lastSku = ((PackingDetail)pdList.get(pdList.size() - 1)).getSku();
        return lastSku;
    }

    public void printTicket() {
        if (this.getSelectedPackingDetail() != null) {
            this.getClient().printCartonTicket(this.getSelectedPackingDetail().getPd().getCarton());
        } else {
            this.getInformationBox("please select a packing detail first");
        }
    }

    public static int getNumberOfScannedItems(Carton carton) {
        int scanned = 0;
        if (carton != null) {
            for (ScanDetail scan : carton.getScanDetails()) {
                if (scan.getDeleted()) continue;
                ++scanned;
            }
        }
        return scanned;
    }

    public static int getNumberOfScannedItems(PackingDetail pd) {
        int scanned = 0;
        if (!pd.getDeleted() && pd.getCarton() != null) {
            scanned = Client.getNumberOfScannedItems(pd.getCarton());
        }
        return scanned;
    }

    public static SimpleDateFormat getSimpleDateFormat() {
        if (sdf == null) {
            sdf = new SimpleDateFormat("mm/dd/yy");
        }
        return sdf;
    }

    static /* synthetic */ void access$0(Client client, int n) {
        client.cartonQty = n;
    }

}

