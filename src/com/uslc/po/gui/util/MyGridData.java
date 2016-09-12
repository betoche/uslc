/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  org.eclipse.swt.layout.GridData
 */
package com.uslc.po.gui.util;

import org.eclipse.swt.layout.GridData;

public class MyGridData {
    private static GridData dgHorizontalDoubleSpan = null;

    public static GridData getDgHorizontalDoubleSpan() {
        if (dgHorizontalDoubleSpan == null) {
            dgHorizontalDoubleSpan = new GridData(768);
            MyGridData.dgHorizontalDoubleSpan.horizontalSpan = 2;
        }
        return dgHorizontalDoubleSpan;
    }
}

