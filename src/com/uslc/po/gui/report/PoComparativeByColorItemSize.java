/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  com.uslc.po.jpa.comparator.SizeComparator
 *  com.uslc.po.jpa.entity.Carton
 *  com.uslc.po.jpa.entity.Color
 *  com.uslc.po.jpa.entity.Item
 *  com.uslc.po.jpa.entity.PackingDetail
 *  com.uslc.po.jpa.entity.PurchaseOrder
 *  com.uslc.po.jpa.entity.PurchaseOrderDetail
 *  com.uslc.po.jpa.entity.ScanDetail
 *  com.uslc.po.jpa.entity.Size
 *  com.uslc.po.jpa.entity.Upc
 *  org.apache.log4j.Logger
 *  org.apache.log4j.PropertyConfigurator
 */
package com.uslc.po.gui.report;

import com.uslc.po.gui.util.Client;
import com.uslc.po.jpa.comparator.SizeComparator;
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
import java.util.Collections;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class PoComparativeByColorItemSize {
    private PurchaseOrder po = null;
    private List<Color> colorList = null;
    private List<Size> sizeList = null;
    private List<ScanDetail> packedItems = null;
    private List<ItemColorSizeCount> itemColorSizeCountList = null;
    private List<TotalBySizes> totalBySizes = null;
    private Logger log = null;

    public PoComparativeByColorItemSize(PurchaseOrder po) {
        Upc upc;
        this.po = po;
        for (PurchaseOrderDetail pod2 : po.getPurchaseOrderDetails()) {
            upc = pod2.getUpc();
            if (!this.getColorList().contains((Object)upc.getColor())) {
                this.getColorList().add(upc.getColor());
            }
            if (!this.getSizeList().contains((Object)upc.getSize())) {
                this.getSizeList().add(upc.getSize());
            }
            Collections.sort(this.getSizeList(), new SizeComparator());
            if (pod2.getDeleted()) continue;
            for (PackingDetail pd : pod2.getPackingDetails()) {
                if (pd.getDeleted() || pd.getCarton() == null || pd.getCarton().getDeleted()) continue;
                for (ScanDetail sd : pd.getCarton().getScanDetails()) {
                    if (sd.getDeleted()) continue;
                    this.getPackedItems().add(sd);
                }
            }
        }
        for (PurchaseOrderDetail pod2 : po.getPurchaseOrderDetails()) {
            upc = pod2.getUpc();
            if (this.containsItemColorSizeCount(upc)) continue;
            this.getItemColorSizeCountList().add(new ItemColorSizeCount(upc));
        }
        for (Size s : this.getSizeList()) {
            this.getTotalBySizes().add(new TotalBySizes(this, s, null));
        }
    }

    public PurchaseOrder getPo() {
        return this.po;
    }

    public List<ItemColorSizeCount> getItemColorSizeCountList() {
        if (this.itemColorSizeCountList == null) {
            this.itemColorSizeCountList = new ArrayList<ItemColorSizeCount>();
        }
        return this.itemColorSizeCountList;
    }

    public List<TotalBySizes> getTotalBySizes() {
        if (this.totalBySizes == null) {
            this.totalBySizes = new ArrayList<TotalBySizes>();
        }
        return this.totalBySizes;
    }

    public boolean containsItemColorSizeCount(Upc upc) {
        boolean contains = false;
        for (ItemColorSizeCount icsc : this.getItemColorSizeCountList()) {
            Color color = upc.getColor();
            Item item = upc.getItem();
            if (color.getId() != icsc.getColor().getId() || item.getId() != icsc.getItem().getId()) continue;
            contains = true;
            break;
        }
        return contains;
    }

    public List<Color> getColorList() {
        if (this.colorList == null) {
            this.colorList = new ArrayList<Color>();
        }
        return this.colorList;
    }

    public List<Size> getSizeList() {
        if (this.sizeList == null) {
            this.sizeList = new ArrayList<Size>();
        }
        return this.sizeList;
    }

    public List<ScanDetail> getPackedItems() {
        if (this.packedItems == null) {
            this.packedItems = new ArrayList<ScanDetail>();
        }
        return this.packedItems;
    }

    private Logger getLog() {
        if (this.log == null) {
            this.log = Logger.getLogger((Class)PoComparativeByColorItemSize.class);
            PropertyConfigurator.configure((String)"log4j.properties");
        }
        return this.log;
    }

    public class ItemColorSizeCount {
        private Color color;
        private Item item;
        private int totalOrdered;
        private int totalShipped;
        private int totalDifference;
        private double totalPercentage;
        private List<SizeCounts> sizeCountsList;

        public ItemColorSizeCount(Upc upc) {
            this.color = null;
            this.item = null;
            this.totalOrdered = 0;
            this.totalShipped = 0;
            this.totalDifference = 0;
            this.totalPercentage = 0.0;
            this.sizeCountsList = null;
            this.color = upc.getColor();
            this.item = upc.getItem();
            PoComparativeByColorItemSize.this.getLog().info((Object)("there are " + PoComparativeByColorItemSize.this.getSizeList().size() + " sizes in the po."));
            for (Size size : PoComparativeByColorItemSize.this.getSizeList()) {
                SizeCounts sc = new SizeCounts(size);
                this.getSizeCountsList().add(sc);
                this.totalOrdered += sc.getOrdered();
                this.totalShipped += sc.getShipped();
            }
            this.totalDifference = this.totalOrdered - this.totalShipped;
            this.totalPercentage = new Double(this.totalShipped) / new Double(this.totalOrdered);
        }

        public int getTotalDifference() {
            return this.totalDifference;
        }

        public double getTotalPercentage() {
            return this.totalPercentage;
        }

        public int getTotalOrdered() {
            return this.totalOrdered;
        }

        public int getTotalShipped() {
            return this.totalShipped;
        }

        public Color getColor() {
            return this.color;
        }

        public Item getItem() {
            return this.item;
        }

        public List<SizeCounts> getSizeCountsList() {
            if (this.sizeCountsList == null) {
                this.sizeCountsList = new ArrayList<SizeCounts>();
            }
            return this.sizeCountsList;
        }

        public class SizeCounts {
            private Size size;
            private int ordered;
            private int shipped;
            private int difference;
            private double percentage;

            public SizeCounts(Size size) {
                this.size = null;
                this.ordered = 0;
                this.shipped = 0;
                this.difference = 0;
                this.percentage = 0.0;
                this.size = size;
                for (PurchaseOrderDetail pod : ItemColorSizeCount.this.PoComparativeByColorItemSize.this.getPo().getPurchaseOrderDetails()) {
                    Upc upc = pod.getUpc();
                    if (ItemColorSizeCount.this.getColor().getId() != upc.getColor().getId() || ItemColorSizeCount.this.getItem().getId() != upc.getItem().getId() || size.getId() != upc.getSize().getId()) continue;
                    this.ordered += pod.getTotal();
                    if (pod.getDeleted()) continue;
                    for (PackingDetail pd : pod.getPackingDetails()) {
                        this.shipped += Client.getNumberOfScannedItems(pd);
                    }
                }
                this.difference = this.ordered - this.shipped;
                this.percentage = new Double(this.shipped) / new Double(this.ordered);
                ItemColorSizeCount.this.PoComparativeByColorItemSize.this.getLog().info((Object)("shipped[" + this.shipped + "]/ordered[" + this.ordered + "]=percentage[" + this.percentage + "]"));
            }

            public Size getSize() {
                return this.size;
            }

            public int getOrdered() {
                return this.ordered;
            }

            public int getShipped() {
                return this.shipped;
            }

            public int getDifference() {
                return this.difference;
            }

            public double getPercentage() {
                return this.percentage;
            }
        }

    }

    public class TotalBySizes {
        private Size size;
        private int ordered;
        private int shipped;
        private int difference;
        private double percentage;
        final /* synthetic */ PoComparativeByColorItemSize this$0;

        private TotalBySizes(PoComparativeByColorItemSize poComparativeByColorItemSize, Size size) {
            this.this$0 = poComparativeByColorItemSize;
            this.size = null;
            this.ordered = 0;
            this.shipped = 0;
            this.difference = 0;
            this.percentage = 0.0;
            this.size = size;
            for (ItemColorSizeCount icsc : poComparativeByColorItemSize.getItemColorSizeCountList()) {
                for (ItemColorSizeCount.SizeCounts sc : icsc.getSizeCountsList()) {
                    if (size.getId() != sc.getSize().getId()) continue;
                    this.ordered += sc.getOrdered();
                    this.shipped += sc.getShipped();
                    this.difference += sc.getDifference();
                }
            }
            this.percentage = new Double(this.shipped) / new Double(this.ordered);
        }

        public Size getSize() {
            return this.size;
        }

        public int getOrdered() {
            return this.ordered;
        }

        public int getShipped() {
            return this.shipped;
        }

        public int getDifference() {
            return this.difference;
        }

        public double getPercentage() {
            return this.percentage;
        }

        /* synthetic */ TotalBySizes(PoComparativeByColorItemSize poComparativeByColorItemSize, Size size, TotalBySizes totalBySizes) {
            TotalBySizes totalBySizes2;
            totalBySizes2(poComparativeByColorItemSize, size);
        }
    }

}

