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

public class ClientScannedItemsTable {
    private int order = 0;
    private ScanDetail sd;
    private Upc upc = null;
    private String size = "";
    private String color = "";
    private PurchaseOrder po = null;

    public ClientScannedItemsTable(int order, ScanDetail sd) {
        this.order = order;
        this.sd = sd;
    }

    public ScanDetail getSd() {
        return this.sd;
    }

    public int getOrder() {
        return this.order;
    }

    public Upc getUpc() {
        if (this.upc == null) {
            this.upc = this.sd.getUpc();
        }
        return this.upc;
    }

    public String getSize() {
        if (this.size == null || this.size.compareTo("") == 0) {
            this.size = this.getPo().getReferenceNumber().endsWith("11") ? String.valueOf(this.getUpc().getSize().getWaist()) : String.valueOf(this.getUpc().getSize().getWaist()) + "x" + this.getUpc().getSize().getInseam();
        }
        return this.size;
    }

    public String getColor() {
        if (this.color == null || this.color.compareTo("") == 0) {
            this.color = this.getUpc().getColor().getName();
        }
        return this.color;
    }

    public PurchaseOrder getPo() {
        if (this.po == null) {
            this.po = this.sd.getCarton().getPackingDetail().getPurchaseOrderDetail().getPurchaseOrder();
        }
        return this.po;
    }

    public String[] getColumnValues() {
        return new String[]{String.valueOf(this.getOrder()), this.getUpc().getUpcCode(), this.getSize(), this.getColor()};
    }
}

