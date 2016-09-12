/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  com.uslc.po.jpa.entity.Color
 *  com.uslc.po.jpa.entity.Item
 *  com.uslc.po.jpa.entity.PurchaseOrder
 *  com.uslc.po.jpa.entity.PurchaseOrderDetail
 *  com.uslc.po.jpa.entity.Size
 *  com.uslc.po.jpa.entity.Upc
 *  org.apache.log4j.Logger
 *  org.apache.log4j.PropertyConfigurator
 *  org.eclipse.swt.graphics.Device
 *  org.eclipse.swt.graphics.Font
 *  org.eclipse.swt.layout.FormData
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.Group
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Table
 *  org.eclipse.swt.widgets.TableColumn
 *  org.eclipse.swt.widgets.TableItem
 */
package com.uslc.po.gui.master;

import com.uslc.po.gui.master.MasterCenterComposite;
import com.uslc.po.gui.master.NewPurchaseOrderComposite;
import com.uslc.po.gui.master.POMaster;
import com.uslc.po.gui.util.PurchaseOrder;
import com.uslc.po.jpa.entity.Color;
import com.uslc.po.jpa.entity.Item;
import com.uslc.po.jpa.entity.PurchaseOrderDetail;
import com.uslc.po.jpa.entity.Size;
import com.uslc.po.jpa.entity.Upc;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class PurchaseOrderDetailComposite
extends Composite {
    private MasterCenterComposite parent = null;
    private Label titleLbl = null;
    private Table detailsTbl = null;
    private Group statsGrp = null;
    private GridData gdFill = null;
    private Table statsTbl = null;
    private Logger log = null;

    public PurchaseOrderDetailComposite(MasterCenterComposite composite) {
        super((Composite)composite.getMaster().getHiddenShell(), 2048);
        this.parent = composite;
        this.initComposite();
    }

    private void initComposite() {
        FormData data = new FormData(550, 550);
        this.setLayoutData((Object)data);
        this.setLayout((Layout)new GridLayout(1, true));
        this.getTitleLbl();
        this.getDetailsTbl();
        this.getStatsGrp();
    }

    public Label getTitleLbl() {
        if (this.titleLbl == null) {
            this.titleLbl = new Label((Composite)this, 0);
            this.titleLbl.setText("Purchase Detail");
            this.titleLbl.setLayoutData((Object)this.getFillGridData());
        }
        return this.titleLbl;
    }

    public Table getDetailsTbl() {
        if (this.detailsTbl == null) {
            this.detailsTbl = new Table((Composite)this, 4);
            this.detailsTbl.setHeaderVisible(true);
            this.detailsTbl.setFont(new Font((Device)this.getDisplay(), "Segoe UI", 6, 0));
            TableColumn upc = new TableColumn(this.detailsTbl, 8);
            TableColumn dept = new TableColumn(this.detailsTbl, 8);
            TableColumn itemCode = new TableColumn(this.detailsTbl, 8);
            TableColumn poNumber = new TableColumn(this.detailsTbl, 8);
            TableColumn colorName = new TableColumn(this.detailsTbl, 8);
            TableColumn colorItem = new TableColumn(this.detailsTbl, 8);
            TableColumn colorNumber = new TableColumn(this.detailsTbl, 8);
            TableColumn size = new TableColumn(this.detailsTbl, 8);
            TableColumn qty = new TableColumn(this.detailsTbl, 8);
            upc.setText("upc");
            dept.setText("dept");
            itemCode.setText("item code");
            poNumber.setText("po #");
            colorName.setText("color name");
            colorItem.setText("");
            colorNumber.setText("color #");
            size.setText("size");
            qty.setText("qty");
            upc.setWidth(79);
            dept.setWidth(35);
            itemCode.setWidth(56);
            poNumber.setWidth(83);
            colorName.setWidth(64);
            colorItem.setWidth(67);
            colorNumber.setWidth(49);
            size.setWidth(45);
            qty.setWidth(41);
            GridData gd = new GridData(768);
            gd.heightHint = 300;
            this.detailsTbl.setLayoutData((Object)gd);
        }
        return this.detailsTbl;
    }

    public Group getStatsGrp() {
        if (this.statsGrp == null) {
            this.statsGrp = new Group((Composite)this, 16);
            this.statsGrp.setText("Summary");
            GridData data = new GridData(128);
            data.widthHint = 260;
            data.heightHint = 150;
            data.grabExcessHorizontalSpace = true;
            this.statsGrp.setLayoutData((Object)data);
            GridLayout lay = new GridLayout(1, false);
            this.statsGrp.setLayout((Layout)lay);
            this.getStatsTbl();
        }
        return this.statsGrp;
    }

    public Table getStatsTbl() {
        if (this.statsTbl == null) {
            this.statsTbl = new Table((Composite)this.getStatsGrp(), 0);
            this.statsTbl.setHeaderVisible(true);
            this.statsTbl.setFont(new Font((Device)this.getDisplay(), "Segoe UI", 8, 0));
            TableColumn color = new TableColumn(this.statsTbl, 8);
            TableColumn units = new TableColumn(this.statsTbl, 8);
            TableColumn boxes = new TableColumn(this.statsTbl, 8);
            color.setText("color");
            units.setText("units");
            boxes.setText("boxes");
            color.setWidth(139);
            units.setWidth(56);
            boxes.setWidth(51);
            GridData gd = new GridData(1808);
            this.statsTbl.setLayoutData((Object)gd);
        }
        return this.statsTbl;
    }

    public GridData getFillGridData() {
        if (this.gdFill == null) {
            this.gdFill = new GridData(768);
        }
        return this.gdFill;
    }

    public void hide() {
        this.clean();
        this.setParent((Composite)this.getParent().getMaster().getHiddenShell());
        this.setVisible(false);
    }

    private void clean() {
    }

    private Logger getLog() {
        if (this.log == null) {
            this.log = Logger.getLogger((Class)NewPurchaseOrderComposite.class);
            PropertyConfigurator.configure((String)"log4j.properties");
        }
        return this.log;
    }

    public MasterCenterComposite getParent() {
        return this.parent;
    }

    public void displayPurchaseDetails(PurchaseOrder po) {
        Collections.sort(po.getPo().getPurchaseOrderDetails(), new PurchaseOrderDetailComparator());
        for (PurchaseOrderDetail pod : po.getPo().getPurchaseOrderDetails()) {
            TableItem row = new TableItem(this.getDetailsTbl(), 8);
            String[] arrstring = new String[9];
            arrstring[0] = pod.getUpc().getUpcCode();
            arrstring[1] = po.getPo().getDepartmentNumber();
            arrstring[2] = String.valueOf(pod.getUpc().getItem().getCode());
            arrstring[3] = po.getPo().getReferenceNumber();
            arrstring[4] = pod.getUpc().getColor().getName();
            arrstring[5] = pod.getUpc().getColorItemCode();
            arrstring[6] = pod.getUpc().getColor().getNumber();
            arrstring[7] = po.getPo().getReferenceNumber().endsWith("11") ? String.valueOf(pod.getUpc().getSize().getWaist()) : String.valueOf(String.valueOf(pod.getUpc().getSize().getWaist()) + "x" + pod.getUpc().getSize().getInseam());
            arrstring[8] = String.valueOf(pod.getTotal());
            String[] texts = arrstring;
            row.setText(texts);
            row.setData((Object)pod);
        }
        Iterator<Map.Entry<String, PurchaseOrder.PoStatistics>> it = po.getPoStatistics().entrySet().iterator();
        int items = 0;
        int cartons = 0;
        while (it.hasNext()) {
            Map.Entry<String, PurchaseOrder.PoStatistics> entry = it.next();
            TableItem row = new TableItem(this.getStatsTbl(), 8);
            String[] texts = new String[]{entry.getValue().getColor(), String.valueOf(entry.getValue().getUnits()), String.valueOf(entry.getValue().getBoxes())};
            row.setText(texts);
            row.setData((Object)entry.getValue());
            items += entry.getValue().getUnits();
            cartons += entry.getValue().getBoxes();
        }
        TableItem row = new TableItem(this.getStatsTbl(), 8);
        String[] texts = new String[]{"total", String.valueOf(items), String.valueOf(cartons)};
        row.setText(texts);
        row.setData((Object)null);
        row.setFont(new Font((Device)this.getDisplay(), "Segoe UI", 8, 1));
    }

    public class PurchaseOrderDetailComparator
    implements Comparator<PurchaseOrderDetail> {
        @Override
        public int compare(PurchaseOrderDetail o1, PurchaseOrderDetail o2) {
            return o1.getUpc().getColor().getNumber().compareTo(o2.getUpc().getColor().getNumber());
        }
    }

}

