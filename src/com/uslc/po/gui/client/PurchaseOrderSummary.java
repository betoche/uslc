/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  com.uslc.po.jpa.entity.Carton
 *  com.uslc.po.jpa.entity.Color
 *  com.uslc.po.jpa.entity.Item
 *  com.uslc.po.jpa.entity.PackingDetail
 *  com.uslc.po.jpa.entity.PurchaseOrder
 *  com.uslc.po.jpa.entity.PurchaseOrderDetail
 *  com.uslc.po.jpa.entity.ScanDetail
 *  com.uslc.po.jpa.entity.Size
 *  com.uslc.po.jpa.entity.Upc
 */
package com.uslc.po.gui.client;

import com.uslc.po.jpa.entity.Carton;
import com.uslc.po.jpa.entity.Color;
import com.uslc.po.jpa.entity.Item;
import com.uslc.po.jpa.entity.PackingDetail;
import com.uslc.po.jpa.entity.PurchaseOrder;
import com.uslc.po.jpa.entity.PurchaseOrderDetail;
import com.uslc.po.jpa.entity.ScanDetail;
import com.uslc.po.jpa.entity.Size;
import com.uslc.po.jpa.entity.Upc;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public class PurchaseOrderSummary {
    private PurchaseOrder po = null;
    private OriginalPurchaseOrder original = null;
    private WorkedPurchaseOrder worked = null;
    private List<Item> itemList = null;
    private List<Color> colorList = null;
    private List<Size> sizeList = null;
    private List<Upc> upcList = null;
    private List<PackingDetail> packingDetailList = null;
    private List<Carton> cartonList = null;

    public PurchaseOrderSummary(PurchaseOrder po) {
        this.po = po;
        this.upcList = new ArrayList<Upc>();
        this.itemList = new ArrayList<Item>();
        this.colorList = new ArrayList<Color>();
        this.sizeList = new ArrayList<Size>();
        this.packingDetailList = new ArrayList<PackingDetail>();
        this.cartonList = new ArrayList<Carton>();
        for (PurchaseOrderDetail pod : po.getPurchaseOrderDetails()) {
            Upc upc = pod.getUpc();
            if (!this.upcList.contains((Object)upc)) {
                this.upcList.add(upc);
            }
            if (!this.itemList.contains((Object)upc.getItem())) {
                this.itemList.add(upc.getItem());
            }
            if (!this.colorList.contains((Object)upc.getColor())) {
                this.colorList.add(upc.getColor());
            }
            if (!this.sizeList.contains((Object)upc.getSize())) {
                this.sizeList.add(upc.getSize());
            }
            for (PackingDetail pd : pod.getPackingDetails()) {
                if (!this.packingDetailList.contains((Object)pd)) {
                    this.packingDetailList.add(pd);
                }
                if (pd.getCarton() == null || pd.getCarton().getDeleted() || this.cartonList.contains((Object)pd.getCarton())) continue;
                this.cartonList.add(pd.getCarton());
            }
        }
    }

    public PurchaseOrder getPo() {
        return this.po;
    }

    public OriginalPurchaseOrder getOriginal() {
        if (this.original == null) {
            this.original = new OriginalPurchaseOrder();
        }
        return this.original;
    }

    public WorkedPurchaseOrder getWorked() {
        if (this.worked == null) {
            this.worked = new WorkedPurchaseOrder();
        }
        return this.worked;
    }

    public List<PackingDetail> getPackingDetailList() {
        return this.packingDetailList;
    }

    public List<Item> getItemList() {
        return this.itemList;
    }

    public List<Color> getColorList() {
        return this.colorList;
    }

    public List<Size> getSizeList() {
        return this.sizeList;
    }

    public List<Upc> getUpcList() {
        return this.upcList;
    }

    public List<Carton> getCartonList() {
        return this.cartonList;
    }

    public int getRemainingUpcs() {
        return this.getOriginal().getUpcCount() - this.getWorked().getUpcCount();
    }

    public int getRemainingItems() {
        return this.getOriginal().getItemsCount() - this.getWorked().getItemsCount();
    }

    public int getRemainingCartons() {
        return this.getOriginal().getCartonsCount() - this.getWorked().getCartonsCount();
    }

    public int getRemainingColors() {
        return this.getOriginal().getColorsCount() - this.getWorked().getColorsCount();
    }

    public int getRemainingSizes() {
        return this.getOriginal().getSizesCount() - this.getWorked().getSizesCount();
    }

    public class OriginalPurchaseOrder {
        private int upcCount;
        private int itemsCount;
        private int cartonsCount;
        private int colorsCount;
        private int sizesCount;

        public OriginalPurchaseOrder() {
            this.upcCount = 0;
            this.itemsCount = 0;
            this.cartonsCount = 0;
            this.colorsCount = 0;
            this.sizesCount = 0;
            this.upcCount = PurchaseOrderSummary.this.getUpcList().size();
            for (PurchaseOrderDetail pod : PurchaseOrderSummary.this.getPo().getPurchaseOrderDetails()) {
                this.itemsCount += pod.getTotal();
            }
            this.cartonsCount = PurchaseOrderSummary.this.getPackingDetailList().size();
            this.colorsCount = PurchaseOrderSummary.this.getColorList().size();
            this.sizesCount = PurchaseOrderSummary.this.getSizeList().size();
        }

        public int getUpcCount() {
            return this.upcCount;
        }

        public int getItemsCount() {
            return this.itemsCount;
        }

        public int getCartonsCount() {
            return this.cartonsCount;
        }

        public int getColorsCount() {
            return this.colorsCount;
        }

        public int getSizesCount() {
            return this.sizesCount;
        }
    }

    public class WorkedPurchaseOrder {
        private int upcCount;
        private int itemsCount;
        private int cartonsCount;
        private int colorsCount;
        private int sizesCount;

        public WorkedPurchaseOrder() {
            this.upcCount = 0;
            this.itemsCount = 0;
            this.cartonsCount = 0;
            this.colorsCount = 0;
            this.sizesCount = 0;
            boolean isPodComplete = true;
            for (PurchaseOrderDetail pod : PurchaseOrderSummary.this.getPo().getPurchaseOrderDetails()) {
                for (PackingDetail pd : pod.getPackingDetails()) {
                    if (pd.getCarton() != null && pd.getCarton().getCompleted()) {
                        ++this.cartonsCount;
                        for (ScanDetail sd : pd.getCarton().getScanDetails()) {
                            if (sd.getDeleted()) continue;
                            ++this.itemsCount;
                        }
                        continue;
                    }
                    isPodComplete = false;
                }
                if (!isPodComplete) continue;
                ++this.upcCount;
            }
            for (Color color : PurchaseOrderSummary.this.getColorList()) {
                boolean isColorCompleted = true;
                block4 : for (PurchaseOrderDetail pod2 : PurchaseOrderSummary.this.getPo().getPurchaseOrderDetails()) {
                    Upc upc = pod2.getUpc();
                    if (upc.getColor().getId() != color.getId()) continue;
                    for (PackingDetail pd : pod2.getPackingDetails()) {
                        if (pd.getCarton() != null && pd.getCarton().getCompleted()) continue;
                        isColorCompleted = false;
                        break block4;
                    }
                }
                if (!isColorCompleted) continue;
                ++this.colorsCount;
            }
            for (Size size : PurchaseOrderSummary.this.getSizeList()) {
                boolean isSizeCompleted = true;
                block7 : for (PurchaseOrderDetail pod2 : PurchaseOrderSummary.this.getPo().getPurchaseOrderDetails()) {
                    if (pod2.getUpc().getSize().getId() != size.getId()) continue;
                    for (PackingDetail pd : pod2.getPackingDetails()) {
                        if (pd.getCarton() != null && pd.getCarton().getCompleted()) continue;
                        isSizeCompleted = false;
                        break block7;
                    }
                }
                if (!isSizeCompleted) continue;
                ++this.sizesCount;
            }
        }

        public int getUpcCount() {
            return this.upcCount;
        }

        public int getItemsCount() {
            return this.itemsCount;
        }

        public int getCartonsCount() {
            return this.cartonsCount;
        }

        public int getColorsCount() {
            return this.colorsCount;
        }

        public int getSizesCount() {
            return this.sizesCount;
        }
    }

}

