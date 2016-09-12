/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  com.uslc.po.jpa.entity.Carton
 *  com.uslc.po.jpa.entity.PackingDetail
 *  com.uslc.po.jpa.entity.PurchaseOrderDetail
 *  com.uslc.po.jpa.entity.Upc
 */
package com.uslc.po.gui.client;

import com.uslc.po.gui.util.Client;
import com.uslc.po.jpa.entity.Carton;
import com.uslc.po.jpa.entity.PackingDetail;
import com.uslc.po.jpa.entity.PurchaseOrderDetail;
import com.uslc.po.jpa.entity.Upc;
import java.util.List;

public class ClientPurchaseOrderDetailTable {
    private PurchaseOrderDetail pod = null;
    private String upc = "";
    private int qty = 0;
    private int carton = 0;
    private int ready = 0;
    private int row = 0;

    public ClientPurchaseOrderDetailTable(PurchaseOrderDetail pod, int row) {
        this.pod = pod;
        this.row = row;
    }

    public int getRow() {
        return this.row;
    }

    public PurchaseOrderDetail getPod() {
        return this.pod;
    }

    public String getUpc() {
        if (this.upc == null || this.upc.compareTo("") == 0) {
            this.upc = this.getPod().getUpc().getUpcCode();
        }
        return this.upc;
    }

    public int getQty() {
        if (this.qty == 0) {
            for (PackingDetail pd : this.getPod().getPackingDetails()) {
                if (pd.getDeleted()) continue;
                this.qty += pd.getQuantity();
            }
        }
        return this.qty;
    }

    public int getCarton() {
        if (this.carton == 0) {
            for (PackingDetail pd : this.getPod().getPackingDetails()) {
                if (pd.getDeleted()) continue;
                ++this.carton;
            }
        }
        return this.carton;
    }

    public int getReady() {
        if (this.ready == 0) {
            for (PackingDetail pd : this.getPod().getPackingDetails()) {
                if (pd.getDeleted() || pd.getCarton() == null || Client.getNumberOfScannedItems(pd.getCarton()) <= 0) continue;
                ++this.ready;
            }
        }
        return this.ready;
    }

    public String[] getColumnValues() {
        return new String[]{String.valueOf(this.row), this.getUpc(), String.valueOf(this.getQty()), String.valueOf(this.getCarton()), String.valueOf(this.getReady())};
    }
}

