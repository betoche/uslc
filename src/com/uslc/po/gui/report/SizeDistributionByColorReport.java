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
 *  com.uslc.po.jpa.entity.Size
 *  com.uslc.po.jpa.entity.Upc
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
import com.uslc.po.jpa.entity.Size;
import com.uslc.po.jpa.entity.Upc;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class SizeDistributionByColorReport {
    private PurchaseOrder po = null;
    private HashMap<String, ColorScanDistribution> colorSizeScanDistribution = null;

    public SizeDistributionByColorReport(PurchaseOrder po) {
        this.po = po;
        HashSet<String> colorsItems = new HashSet<String>();
        for (PurchaseOrderDetail pod : po.getPurchaseOrderDetails()) {
            colorsItems.add(String.valueOf(pod.getUpc().getColor().getName()) + "-" + pod.getUpc().getItem().getCode());
        }
        for (String colorItem : colorsItems) {
            this.getColorSizeScanDistribution().put(colorItem, new ColorScanDistribution(colorItem));
        }
    }

    public PurchaseOrder getPurchaseOrder() {
        return this.po;
    }

    public HashMap<String, ColorScanDistribution> getColorSizeScanDistribution() {
        if (this.colorSizeScanDistribution == null) {
            this.colorSizeScanDistribution = new HashMap();
        }
        return this.colorSizeScanDistribution;
    }

    public class ColorScanDistribution {
        private String colorItem;
        private List<SizeScanDistribution> sizeScanDistribution;

        public ColorScanDistribution(String colorItem) {
            this.colorItem = "";
            this.sizeScanDistribution = null;
            this.colorItem = colorItem;
            ArrayList<Size> sizes = new ArrayList<Size>();
            for (PurchaseOrderDetail pod : SizeDistributionByColorReport.this.getPurchaseOrder().getPurchaseOrderDetails()) {
                Upc upc = pod.getUpc();
                String cName = upc.getColor().getName();
                String iCode = upc.getItem().getCode();
                String tmpColorItem = String.valueOf(cName) + "-" + iCode;
                if (tmpColorItem.compareTo(colorItem) != 0) continue;
                sizes.add(upc.getSize());
            }
            Collections.sort(sizes, new SizeComparator());
            for (Size size : sizes) {
                this.getSizeScanDistribution().add(new SizeScanDistribution(this.getColorItem(), size));
            }
        }

        public String getColorItem() {
            return this.colorItem;
        }

        public List<SizeScanDistribution> getSizeScanDistribution() {
            if (this.sizeScanDistribution == null) {
                this.sizeScanDistribution = new ArrayList<SizeScanDistribution>();
            }
            return this.sizeScanDistribution;
        }
    }

    public class SizeScanDistribution {
        private Size size;
        private int poQty;
        private int packedQty;

        public SizeScanDistribution(String colorItem, Size size) {
            this.size = null;
            this.poQty = 0;
            this.packedQty = 0;
            this.size = size;
            for (PurchaseOrderDetail pod : SizeDistributionByColorReport.this.getPurchaseOrder().getPurchaseOrderDetails()) {
                Upc upc = pod.getUpc();
                Color tmpColor = upc.getColor();
                Item tmpItem = upc.getItem();
                Size tmpSize = upc.getSize();
                String tmpColorItem = String.valueOf(tmpColor.getName()) + "-" + tmpItem.getCode();
                if (tmpColorItem.compareTo(colorItem) != 0 || size.getId() != tmpSize.getId()) continue;
                this.poQty += pod.getTotal();
                for (PackingDetail pd : pod.getPackingDetails()) {
                    if (pd.getDeleted() || pd.getCarton() == null || pd.getCarton().getDeleted()) continue;
                    this.packedQty += Client.getNumberOfScannedItems(pd.getCarton());
                }
            }
        }

        public Size getSize() {
            return this.size;
        }

        public int getPoQty() {
            return this.poQty;
        }

        public int getPackedQty() {
            return this.packedQty;
        }
    }

}

