/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  com.uslc.po.jpa.entity.Carton
 *  com.uslc.po.jpa.entity.Color
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
import com.uslc.po.jpa.entity.PackingDetail;
import com.uslc.po.jpa.entity.PurchaseOrder;
import com.uslc.po.jpa.entity.PurchaseOrderDetail;
import com.uslc.po.jpa.entity.ScanDetail;
import com.uslc.po.jpa.entity.Size;
import com.uslc.po.jpa.entity.Upc;
import java.util.List;

public class ClientPackingDetailTable {
    private PackingDetail pd = null;
    private String color = "";
    private String size = "";
    private int sku = 0;
    private int qty = 0;
    private int scanned = 0;

    public String getColor() {
        if (this.color == null || this.color.compareTo("") == 0) {
            this.color = this.pd.getPurchaseOrderDetail().getUpc().getColor().getName();
        }
        return this.color;
    }

    public String getSize() {
        if (this.size == null || this.size.compareTo("") == 0) {
            Size s = this.pd.getPurchaseOrderDetail().getUpc().getSize();
            this.size = this.pd.getPurchaseOrderDetail().getPurchaseOrder().getReferenceNumber().endsWith("11") ? String.valueOf(s.getWaist()) : String.valueOf(String.valueOf(s.getWaist()) + "x" + s.getInseam());
        }
        return this.size;
    }

    public ClientPackingDetailTable(PackingDetail pd) {
        this.pd = pd;
    }

    public PackingDetail getPd() {
        return this.pd;
    }

    public int getSku() {
        if (this.sku == 0) {
            this.sku = this.pd.getSku();
        }
        return this.sku;
    }

    public int getQty() {
        if (this.qty == 0) {
            this.qty = this.pd.getQuantity();
        }
        return this.qty;
    }

    public int getScanned() {
        if (this.scanned == 0 && this.pd.getCarton() != null && this.pd.getCarton().getScanDetails() != null) {
            for (ScanDetail sd : this.pd.getCarton().getScanDetails()) {
                if (sd.getDeleted()) continue;
                ++this.scanned;
            }
        }
        return this.scanned;
    }

    public String[] getColumnValues() {
        return new String[]{this.getSize(), this.getColor(), String.valueOf(this.getSku()), String.valueOf(this.getQty()), String.valueOf(this.getScanned())};
    }
}

