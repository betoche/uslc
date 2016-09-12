/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  com.uslc.po.jpa.entity.Item
 *  com.uslc.po.jpa.logic.ItemRepo
 *  com.uslc.po.jpa.util.Constants
 *  com.uslc.po.jpa.util.UslcJpa
 *  org.apache.log4j.Logger
 *  org.apache.log4j.PropertyConfigurator
 *  org.eclipse.swt.events.MouseAdapter
 *  org.eclipse.swt.events.MouseEvent
 *  org.eclipse.swt.events.MouseListener
 *  org.eclipse.swt.events.SelectionAdapter
 *  org.eclipse.swt.events.SelectionEvent
 *  org.eclipse.swt.events.SelectionListener
 *  org.eclipse.swt.layout.FormData
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Button
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.MessageBox
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Table
 *  org.eclipse.swt.widgets.TableColumn
 *  org.eclipse.swt.widgets.TableItem
 *  org.eclipse.swt.widgets.Text
 */
package com.uslc.po.gui.master.catalog;

import com.uslc.po.gui.master.MasterCenterComposite;
import com.uslc.po.gui.master.POMaster;
import com.uslc.po.gui.util.MyGridData;
import com.uslc.po.jpa.entity.Item;
import com.uslc.po.jpa.logic.ItemRepo;
import com.uslc.po.jpa.util.Constants;
import com.uslc.po.jpa.util.UslcJpa;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class ItemManagerComposite
extends Composite {
    private MasterCenterComposite parent = null;
    private Label titleLbl = null;
    private Table itemsTbl = null;
    private Label infoLbl = null;
    private Label itemLbl = null;
    private Text itemTxt = null;
    private Button action = null;
    private Button cancel = null;
    private Logger log = null;
    private Item selectedItem = null;
    private boolean editing = false;
    private final String infoAddText = "Info: Add a new Item Number";

    public ItemManagerComposite(MasterCenterComposite composite) {
        super((Composite)composite.getMaster().getHiddenShell(), 0);
        this.parent = composite;
        this.initComposite();
    }

    private void initComposite() {
        GridLayout layout = new GridLayout(4, false);
        this.setLayout((Layout)layout);
        FormData data = new FormData(400, 220);
        this.setLayoutData((Object)data);
        this.getTitleLbl();
        this.getItemsTbl();
        this.getInfoLbl();
        this.getItemLbl();
        this.getItemTxt();
        this.getAction();
        this.getCancel();
    }

    public Label getTitleLbl() {
        if (this.titleLbl == null) {
            this.titleLbl = new Label((Composite)this, 0);
            this.titleLbl.setText("Item Manager");
            this.titleLbl.setAlignment(16384);
            GridData data = new GridData(4, 4, true, false);
            data.horizontalSpan = 4;
            this.titleLbl.setLayoutData((Object)data);
            Label horizontalLine = new Label((Composite)this, 258);
            horizontalLine.setLayoutData((Object)data);
        }
        return this.titleLbl;
    }

    public Table getItemsTbl() {
        if (this.itemsTbl == null) {
            this.itemsTbl = new Table((Composite)this, 4);
            TableColumn id = new TableColumn(this.itemsTbl, 0);
            id.setText("id");
            TableColumn size = new TableColumn(this.itemsTbl, 0);
            size.setText("number");
            id.setWidth(30);
            size.setWidth(70);
            this.itemsTbl.setHeaderVisible(true);
            GridData data = new GridData(1040);
            data.verticalSpan = 5;
            this.itemsTbl.setLayoutData((Object)data);
            this.itemsTbl.addMouseListener((MouseListener)new MouseAdapter(){

                public void mouseDoubleClick(MouseEvent arg0) {
                    ItemManagerComposite.this.setEditMode();
                }
            });
            this.loadItems();
            GridData data1 = new GridData(4, 4, false, false);
            data1.verticalSpan = 5;
            data1.widthHint = 15;
            Label horizontalLine = new Label((Composite)this, 514);
            horizontalLine.setLayoutData((Object)data1);
        }
        return this.itemsTbl;
    }

    public Label getInfoLbl() {
        if (this.infoLbl == null) {
            this.infoLbl = new Label((Composite)this, 0);
            this.infoLbl.setText("Info: Add a new Item Number");
            this.infoLbl.setLayoutData((Object)MyGridData.getDgHorizontalDoubleSpan());
            Label horizontalLine = new Label((Composite)this, 258);
            horizontalLine.setLayoutData((Object)MyGridData.getDgHorizontalDoubleSpan());
        }
        return this.infoLbl;
    }

    public Label getItemLbl() {
        if (this.itemLbl == null) {
            this.itemLbl = new Label((Composite)this, 0);
            this.itemLbl.setText("item:");
            this.itemLbl.setAlignment(131072);
            GridData labelGd = new GridData(70, 23);
            this.itemLbl.setLayoutData((Object)labelGd);
        }
        return this.itemLbl;
    }

    public Text getItemTxt() {
        if (this.itemTxt == null) {
            this.itemTxt = new Text((Composite)this, 2048);
            GridData textGd = new GridData(100, 23);
            this.itemTxt.setLayoutData((Object)textGd);
            Label horizontalLine = new Label((Composite)this, 258);
            horizontalLine.setLayoutData((Object)MyGridData.getDgHorizontalDoubleSpan());
        }
        return this.itemTxt;
    }

    public Button getAction() {
        if (this.action == null) {
            this.action = new Button((Composite)this, 8);
            this.action.setText("add");
            GridData gd = new GridData();
            gd.widthHint = 70;
            gd.horizontalAlignment = 131072;
            this.action.setLayoutData((Object)gd);
            this.action.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent e) {
                    try {
                        ItemManagerComposite.this.performAction();
                    }
                    catch (Exception e1) {
                        ItemManagerComposite.this.getLog().error((Object)"error", (Throwable)e1);
                    }
                }
            });
        }
        return this.action;
    }

    public Button getCancel() {
        if (this.cancel == null) {
            this.cancel = new Button((Composite)this, 8);
            this.cancel.setText("cancel");
            GridData gd = new GridData();
            gd.horizontalAlignment = 16777216;
            gd.widthHint = 70;
            this.cancel.setLayoutData((Object)gd);
            this.cancel.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent e) {
                    if (ItemManagerComposite.this.editing) {
                        ItemManagerComposite.this.clean();
                    } else {
                        ItemManagerComposite.this.hide();
                    }
                }
            });
            GridData data = new GridData(4, 4, true, false);
            data.horizontalSpan = 4;
            Label horizontalLine = new Label((Composite)this, 258);
            horizontalLine.setLayoutData((Object)data);
        }
        return this.cancel;
    }

    public Logger getLog() {
        if (this.log == null) {
            this.log = Logger.getLogger((Class)ItemManagerComposite.class);
            PropertyConfigurator.configure((String)"log4j.properties");
        }
        return this.log;
    }

    public MasterCenterComposite getParent() {
        return this.parent;
    }

    private void setEditMode() {
        this.editing = true;
        TableItem[] selection = this.getItemsTbl().getSelection();
        Item item = null;
        if (selection != null) {
            TableItem[] arrtableItem = selection;
            int n = arrtableItem.length;
            int n2 = 0;
            while (n2 < n) {
                TableItem it = arrtableItem[n2];
                item = (Item)it.getData();
                ++n2;
            }
        }
        if (item != null) {
            this.editing = true;
            this.selectedItem = item;
            this.getItemTxt().setText(String.valueOf(item.getCode()));
            this.getInfoLbl().setText("Item[" + item.getId() + "] - UPDATE");
            this.getInfoLbl().setAlignment(131072);
            this.getAction().setText("update");
        } else {
            this.editing = false;
            this.selectedItem = null;
        }
    }

    private void performAction() throws Exception {
        Item item = null;
        String code = this.getItemTxt().getText().trim();
        String successMsg = "";
        String errorMsg = "";
        if (this.editing) {
            item = this.selectedItem;
            successMsg = "Item updated correctly.";
            errorMsg = "There was a problem while updating the item.";
        } else {
            item = new Item();
            successMsg = "Item added correctly.";
            errorMsg = "There was a problem adding the item.";
        }
        item.setCode(code);
        UslcJpa jpa = new UslcJpa();
        int style = 2;
        MessageBox diag = new MessageBox(this.getShell(), style);
        diag.setText(Constants.MESSAGE_BOX_DIAG_TITLE.toString());
        if (jpa.persist((Object)item)) {
            diag.setMessage(successMsg);
            this.clean();
            this.loadItems();
        } else {
            style = 1;
            diag.setMessage(errorMsg);
        }
        diag.open();
    }

    private void clean() {
        this.editing = false;
        this.getItemTxt().setText("");
        this.getAction().setText("add");
        this.getInfoLbl().setText("Info: Add a new Item Number");
        this.getInfoLbl().setAlignment(16384);
    }

    public void hide() {
        this.clean();
        this.setParent((Composite)this.getParent().getMaster().getHiddenShell());
        this.setVisible(false);
    }

    private void loadItems() {
        List items = ItemRepo.findAll();
        this.getItemsTbl().removeAll();
        for (Item item : items) {
            TableItem it = new TableItem(this.getItemsTbl(), 0);
            String[] texts = new String[]{String.valueOf(item.getId()), String.valueOf(item.getCode())};
            it.setData((Object)item);
            it.setText(texts);
        }
    }

}

