/*
 * Decompiled with CFR 0_115.
 */
package com.uslc.po.gui.util;

public class PurchaseOrderDetailUpload {
    private String upcCode = "";
    private String deptNumber = "";
    private String itemCode = "";
    private String poNumber = "";
    private String colorName = "";
    private String colorNumber = "";
    private String size = "";
    private int items = 0;
    private int itemsPerCarton = 0;

    public PurchaseOrderDetailUpload(String upcCode, String deptNumber, String itemCode, String poNumber, String colorName, String colorNumber, String size, int items, int itemsPerCarton) {
        this.upcCode = upcCode;
        this.deptNumber = deptNumber;
        this.itemCode = itemCode;
        this.poNumber = poNumber;
        this.colorName = colorName;
        this.colorNumber = colorNumber;
        this.size = size;
        this.items = items;
        this.itemsPerCarton = itemsPerCarton;
    }

    public String getUpcCode() {
        return this.upcCode;
    }

    public String getDeptNumber() {
        return this.deptNumber;
    }

    public String getItemCode() {
        return this.itemCode;
    }

    public String getPoNumber() {
        return this.poNumber;
    }

    public String getColorName() {
        return this.colorName;
    }

    public String getColorNumber() {
        return this.colorNumber;
    }

    public String getSize() {
        return this.size;
    }

    public int getItems() {
        return this.items;
    }

    public int getItemsPerCarton() {
        return this.itemsPerCarton;
    }
}

