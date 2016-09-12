/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  com.uslc.po.jpa.entity.Color
 *  com.uslc.po.jpa.entity.Item
 *  com.uslc.po.jpa.entity.PackingDetail
 *  com.uslc.po.jpa.entity.PurchaseOrder
 *  com.uslc.po.jpa.entity.PurchaseOrderDetail
 *  com.uslc.po.jpa.entity.Upc
 */
package com.uslc.po.gui.report;

import com.uslc.po.jpa.entity.Color;
import com.uslc.po.jpa.entity.Item;
import com.uslc.po.jpa.entity.PackingDetail;
import com.uslc.po.jpa.entity.PurchaseOrder;
import com.uslc.po.jpa.entity.PurchaseOrderDetail;
import com.uslc.po.jpa.entity.Upc;

public class EBShippingList {
    private PackingDetail packingDetail = null;

    public EBShippingList(PackingDetail packingDetail) {
        this.packingDetail = packingDetail;
    }

    public String getUpc() {
        return this.packingDetail.getPurchaseOrderDetail().getUpc().getUpcCode();
    }

    public String getDept() {
        return this.packingDetail.getPurchaseOrderDetail().getPurchaseOrder().getDepartmentNumber();
    }

    public String getItemCode() {
        return this.packingDetail.getPurchaseOrderDetail().getUpc().getItem().getCode();
    }

    public String getPoReference() {
        return this.packingDetail.getPurchaseOrderDetail().getPurchaseOrder().getReferenceNumber();
    }

    public String getColorName() {
        return this.packingDetail.getPurchaseOrderDetail().getUpc().getColor().getName();
    }

    public String getColorItemCode() {
        return this.packingDetail.getPurchaseOrderDetail().getUpc().getColorItemCode();
    }

    public String getColorNumber() {
        return this.packingDetail.getPurchaseOrderDetail().getUpc().getColor().getNumber();
    }

    public String getSku() {
        return String.valueOf(this.packingDetail.getSku());
    }

    public String getQty() {
        return String.valueOf(this.packingDetail.getQuantity());
    }

    public class SizesPackingSummary {
        public String getSize() {
            return "";
        }

        public String getQty() {
            return "";
        }
    }

}

