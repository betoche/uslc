/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  org.apache.log4j.Logger
 *  org.apache.log4j.PropertyConfigurator
 *  org.eclipse.swt.widgets.Table
 *  org.eclipse.swt.widgets.TableColumn
 */
package com.uslc.po.gui.util;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class WidgetsUtils {
    private static Logger log = null;

    public static void ColumnsDetailsExplorer(Table t, String tName) {
        TableColumn[] arrtableColumn = t.getColumns();
        int n = arrtableColumn.length;
        int n2 = 0;
        while (n2 < n) {
            TableColumn tc = arrtableColumn[n2];
            WidgetsUtils.getLog().info((Object)(String.valueOf(tName) + ": " + tc.getText() + "{width:" + tc.getWidth() + "}"));
            ++n2;
        }
    }

    private static Logger getLog() {
        if (log == null) {
            log = Logger.getLogger((Class)WidgetsUtils.class);
            PropertyConfigurator.configure((String)"log4j.properties");
        }
        return log;
    }
}

