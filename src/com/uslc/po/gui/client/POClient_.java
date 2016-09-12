/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  com.uslc.po.jpa.entity.PackingDetail
 *  com.uslc.po.jpa.entity.PurchaseOrder
 *  com.uslc.po.jpa.entity.PurchaseOrderDetail
 *  com.uslc.po.jpa.entity.Size
 *  com.uslc.po.jpa.entity.Upc
 *  com.uslc.po.jpa.logic.PurchaseOrderRepo
 *  com.uslc.po.jpa.logic.UpcRepo
 *  org.apache.log4j.Logger
 *  org.apache.log4j.PropertyConfigurator
 *  org.eclipse.swt.events.SelectionAdapter
 *  org.eclipse.swt.events.SelectionEvent
 *  org.eclipse.swt.events.SelectionListener
 *  org.eclipse.swt.events.TraverseEvent
 *  org.eclipse.swt.events.TraverseListener
 *  org.eclipse.swt.graphics.Font
 *  org.eclipse.swt.widgets.Combo
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.Group
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Table
 *  org.eclipse.swt.widgets.TableColumn
 *  org.eclipse.swt.widgets.TableItem
 *  org.eclipse.swt.widgets.Text
 */
package com.uslc.po.gui.client;

import com.uslc.po.jpa.entity.PackingDetail;
import com.uslc.po.jpa.entity.PurchaseOrder;
import com.uslc.po.jpa.entity.PurchaseOrderDetail;
import com.uslc.po.jpa.entity.Size;
import com.uslc.po.jpa.entity.Upc;
import com.uslc.po.jpa.logic.PurchaseOrderRepo;
import com.uslc.po.jpa.logic.UpcRepo;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class POClient_
extends Shell {
    private Table table;
    private Table table_1;
    private Table table_2;
    private Logger log = null;
    private Text text;

    public static void main(String[] args) {
        try {
            Display display = Display.getDefault();
            POClient_ shell = new POClient_(display);
            shell.open();
            shell.layout();
            while (!shell.isDisposed()) {
                if (display.readAndDispatch()) continue;
                display.sleep();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public POClient_(Display display) {
        super(display, 1264);
        this.addTraverseListener(new TraverseListener(){

            public void keyTraversed(TraverseEvent event) {
                if (event.detail == 4) {
                    POClient_.this.getLog().info((Object)"Enter Pressed: ");
                    POClient_.this.text.setFocus();
                }
            }
        });
        Composite composite = new Composite((Composite)this, 2048);
        composite.setBounds(10, 10, 220, 582);
        Label lblPo = new Label(composite, 0);
        lblPo.setBounds(10, 10, 38, 21);
        lblPo.setText("PO #:");
        final Combo combo = new Combo(composite, 8);
        combo.setBounds(50, 10, 160, 33);
        Label lblPoDetail = new Label(composite, 0);
        lblPoDetail.setBounds(10, 49, 71, 21);
        lblPoDetail.setText("PO Detail:");
        this.table = new Table(composite, 2060);
        this.table.setBounds(10, 82, 200, 314);
        this.table.setHeaderVisible(true);
        this.table.setLinesVisible(true);
        TableColumn upc = new TableColumn(this.table, 0);
        TableColumn items = new TableColumn(this.table, 0);
        upc.setText("upc");
        items.setText("items");
        upc.setWidth(140);
        items.setWidth(50);
        Label lblPackingDetail = new Label(composite, 0);
        lblPackingDetail.setBounds(10, 402, 105, 20);
        lblPackingDetail.setText("Packing Detail");
        this.table_2 = new Table(composite, 67584);
        this.table_2.setBounds(10, 424, 200, 144);
        this.table_2.setHeaderVisible(true);
        this.table_2.setLinesVisible(true);
        TableColumn sku = new TableColumn(this.table_2, 0);
        TableColumn qty = new TableColumn(this.table_2, 0);
        TableColumn packed = new TableColumn(this.table_2, 0);
        sku.setText("upc");
        qty.setText("items");
        packed.setText("packed");
        sku.setWidth(50);
        qty.setWidth(50);
        packed.setWidth(50);
        combo.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                POClient_.this.loadPoDetail(combo, POClient_.this.table);
            }
        });
        this.table.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                POClient_.this.loadPackingDetail(POClient_.this.table, POClient_.this.table_2);
            }
        });
        Composite composite_1 = new Composite((Composite)this, 2048);
        composite_1.setBounds(236, 10, 362, 582);
        Label lblScanList = new Label(composite_1, 0);
        lblScanList.setBounds(10, 10, 71, 21);
        lblScanList.setText("Scan List:");
        this.table_1 = new Table(composite_1, 67584);
        this.table_1.setBounds(10, 37, 342, 416);
        this.table_1.setHeaderVisible(true);
        this.table_1.setLinesVisible(true);
        TableColumn upcCol = new TableColumn(this.table_1, 0);
        TableColumn itemCol = new TableColumn(this.table_1, 0);
        TableColumn sizeCol = new TableColumn(this.table_1, 0);
        upcCol.setText("upc");
        itemCol.setText("item-code");
        sizeCol.setText("size");
        upcCol.setWidth(130);
        itemCol.setWidth(100);
        sizeCol.setWidth(100);
        Group grpLastItemScanned = new Group(composite_1, 0);
        grpLastItemScanned.setText("Last Item Scanned");
        grpLastItemScanned.setBounds(10, 459, 340, 109);
        Group grpBarcode_1 = new Group((Composite)grpLastItemScanned, 0);
        grpBarcode_1.setText("barcode");
        grpBarcode_1.setBounds(10, 20, 160, 79);
        Label lblUpcCode = new Label((Composite)grpLastItemScanned, 0);
        lblUpcCode.setFont(SWTResourceManager.getFont("Segoe UI", 9, 0));
        lblUpcCode.setBounds(176, 20, 164, 20);
        lblUpcCode.setText("upc code: 410013940956");
        Label lblSizex = new Label((Composite)grpLastItemScanned, 0);
        lblSizex.setBounds(176, 40, 96, 20);
        lblSizex.setText("Size: 23x30");
        Label lblColorOnyx = new Label((Composite)grpLastItemScanned, 0);
        lblColorOnyx.setBounds(176, 60, 96, 20);
        lblColorOnyx.setText("Color: ONYX");
        Label lblItem = new Label((Composite)grpLastItemScanned, 0);
        lblItem.setOrientation(67108864);
        lblItem.setBounds(226, 79, 104, 20);
        lblItem.setText("Item: 4772");
        this.text = new Text(composite_1, 2048);
        this.text.addTraverseListener(new TraverseListener(){

            public void keyTraversed(TraverseEvent event) {
                if (event.detail == 4) {
                    String str = ((Text)event.getSource()).getText();
                    POClient_.this.getLog().info((Object)("Enter Pressed: " + POClient_.this.text.getText()));
                    if (str.length() > 10 && str.length() < 13) {
                        Upc upc = UpcRepo.findByCode((String)str);
                        TableItem item = new TableItem(POClient_.this.table_1, 0);
                        String[] texts = new String[]{str, upc.getColorItemCode(), String.valueOf(upc.getSize().getWaist()) + "x" + upc.getSize().getInseam()};
                        item.setData((Object)upc);
                        item.setText(texts);
                    }
                }
                POClient_.this.text.setText("");
                POClient_.this.text.setFocus();
            }
        });
        this.text.setBounds(87, 5, 142, 26);
        Label lblTotal = new Label(composite_1, 0);
        lblTotal.setOrientation(67108864);
        lblTotal.setBounds(278, 10, 70, 20);
        lblTotal.setText("Total: 10");
        Composite composite_2 = new Composite((Composite)this, 2048);
        composite_2.setBounds(604, 10, 209, 582);
        Group grpPoInfo = new Group(composite_2, 0);
        grpPoInfo.setText("PO Info");
        grpPoInfo.setBounds(10, 10, 187, 236);
        Label lblFromUslcApparel = new Label((Composite)grpPoInfo, 64);
        lblFromUslcApparel.setBounds(10, 31, 167, 45);
        lblFromUslcApparel.setText("From: \nUSLC Apparel Nicaragua");
        Label lblToOtherLocation = new Label((Composite)grpPoInfo, 64);
        lblToOtherLocation.setText("To: \nOther location in the world");
        lblToOtherLocation.setBounds(10, 82, 167, 45);
        Label lblCreated = new Label((Composite)grpPoInfo, 0);
        lblCreated.setBounds(10, 154, 70, 20);
        lblCreated.setText("Created:");
        Label lblSatthOf = new Label((Composite)grpPoInfo, 0);
        lblSatthOf.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        lblSatthOf.setBounds(10, 180, 167, 20);
        lblSatthOf.setText("Sat 18th of January of 2014");
        Group grpDetailInfo = new Group(composite_2, 0);
        grpDetailInfo.setText("Detail Info");
        grpDetailInfo.setBounds(10, 252, 187, 188);
        Group grpBarcode = new Group((Composite)grpDetailInfo, 0);
        grpBarcode.setText("barcode");
        grpBarcode.setBounds(10, 22, 167, 87);
        Label lblTotalItems_1 = new Label((Composite)grpDetailInfo, 0);
        lblTotalItems_1.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        lblTotalItems_1.setBounds(10, 115, 109, 20);
        lblTotalItems_1.setText("Total Items: 15");
        Label lblScannedItems = new Label((Composite)grpDetailInfo, 0);
        lblScannedItems.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        lblScannedItems.setText("Scanned Items: 3");
        lblScannedItems.setBounds(10, 137, 124, 20);
        Label lblPendingItems = new Label((Composite)grpDetailInfo, 0);
        lblPendingItems.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        lblPendingItems.setText("Pending Items: 12");
        lblPendingItems.setBounds(10, 160, 124, 20);
        Label lblCartons = new Label((Composite)grpDetailInfo, 0);
        lblCartons.setOrientation(67108864);
        lblCartons.setBounds(101, 115, 76, 20);
        lblCartons.setText("Cartons");
        Label label = new Label((Composite)grpDetailInfo, 0);
        label.setOrientation(67108864);
        label.setBounds(141, 137, 36, 20);
        label.setText("2");
        Group grpUserInfo = new Group(composite_2, 0);
        grpUserInfo.setText("User Info");
        grpUserInfo.setBounds(10, 446, 187, 122);
        Label lblTotalPo = new Label((Composite)grpUserInfo, 0);
        lblTotalPo.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        lblTotalPo.setBounds(100, 100, 85, 20);
        lblTotalPo.setText("Total PO: 43");
        Label lblTotayPo = new Label((Composite)grpUserInfo, 0);
        lblTotayPo.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        lblTotayPo.setBounds(10, 20, 95, 20);
        lblTotayPo.setText("Totay' PO: 12");
        Label lblTotalItems = new Label((Composite)grpUserInfo, 0);
        lblTotalItems.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        lblTotalItems.setBounds(10, 38, 113, 20);
        lblTotalItems.setText("Total Items: 1023");
        Label lblTotalCartons = new Label((Composite)grpUserInfo, 0);
        lblTotalCartons.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        lblTotalCartons.setBounds(10, 55, 113, 20);
        lblTotalCartons.setText("Total Cartons: 96");
        this.createContents();
        List pos = PurchaseOrderRepo.findAll();
        if (pos != null) {
            this.getLog().info((Object)("pos.size()[" + pos.size() + "]"));
            for (PurchaseOrder po : pos) {
                combo.add(po.getReferenceNumber());
                combo.setData(po.getReferenceNumber(), (Object)po);
            }
        }
        this.text.setFocus();
    }

    protected void loadPackingDetail(Table table2, Table table_22) {
        TableItem[] selection = table2.getSelection();
        PurchaseOrderDetail pod = null;
        if (selection != null) {
            TableItem[] arrtableItem = selection;
            int n = arrtableItem.length;
            int n2 = 0;
            while (n2 < n) {
                TableItem item = arrtableItem[n2];
                pod = (PurchaseOrderDetail)item.getData();
                ++n2;
            }
        }
        if (pod != null) {
            table_22.removeAll();
            for (PackingDetail pd : pod.getPackingDetails()) {
                TableItem item = new TableItem(table_22, 0);
                String[] texts = new String[]{String.valueOf(pd.getSku()), String.valueOf(pd.getQuantity()), "0"};
                item.setData((Object)pod);
                item.setText(texts);
            }
        }
    }

    protected void createContents() {
        this.setText("SWT Application");
        this.setSize(838, 649);
    }

    public void loadPoDetail(Combo combo, Table table) {
        PurchaseOrder po = (PurchaseOrder)combo.getData(combo.getItem(combo.getSelectionIndex()));
        if (po != null) {
            table.removeAll();
            for (PurchaseOrderDetail pod : po.getPurchaseOrderDetails()) {
                TableItem item = new TableItem(table, 0);
                String[] texts = new String[]{pod.getUpc().getUpcCode(), String.valueOf(pod.getTotal())};
                item.setData((Object)pod);
                item.setText(texts);
            }
        }
    }

    protected void checkSubclass() {
    }

    private Logger getLog() {
        if (this.log == null) {
            this.log = Logger.getLogger((Class)POClient_.class);
            PropertyConfigurator.configure((String)"log4j.properties");
        }
        return this.log;
    }

}

