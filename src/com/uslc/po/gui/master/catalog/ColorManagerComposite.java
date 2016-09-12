/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  com.uslc.po.jpa.entity.Color
 *  com.uslc.po.jpa.logic.ColorRepo
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
import com.uslc.po.jpa.entity.Color;
import com.uslc.po.jpa.logic.ColorRepo;
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

public class ColorManagerComposite
extends Composite {
    private MasterCenterComposite parent = null;
    private Label title = null;
    private Label nameTitle = null;
    private Label numberTitle = null;
    private Text nameInput = null;
    private Text numberInput = null;
    private Table table = null;
    private Label currentAction = null;
    private Button action = null;
    private Button cancel = null;
    private boolean editing = false;
    private Color currentColor = null;
    private Logger log = null;

    public ColorManagerComposite(MasterCenterComposite composite) {
        super((Composite)composite.getMaster().getHiddenShell(), 0);
        this.parent = composite;
        this.initComposite();
    }

    public void initComposite() {
        FormData data = new FormData(400, 250);
        this.setLayoutData((Object)data);
        this.setLayout((Layout)new GridLayout(3, false));
        this.getTitle();
        this.getTable();
        this.getCurrentAction();
        this.getNameTitle();
        this.getNameInput();
        this.getNumberTitle();
        this.getNumberInput();
        this.getAction();
        this.getCancel();
    }

    public Label getTitle() {
        if (this.title == null) {
            this.title = new Label((Composite)this, 0);
            this.title.setText("Colors");
            this.title.setAlignment(16384);
            GridData data = new GridData(4, 4, true, false);
            data.horizontalSpan = 3;
            this.title.setLayoutData((Object)data);
            Label horizontalLine = new Label((Composite)this, 258);
            horizontalLine.setLayoutData((Object)data);
        }
        return this.title;
    }

    public Table getTable() {
        if (this.table == null) {
            this.table = new Table((Composite)this, 4);
            TableColumn id = new TableColumn(this.table, 16777216);
            id.setText("id");
            TableColumn name = new TableColumn(this.table, 16777216);
            name.setText("name");
            TableColumn number = new TableColumn(this.table, 16777216);
            number.setText("number");
            id.setWidth(30);
            name.setWidth(70);
            number.setWidth(70);
            this.table.setHeaderVisible(true);
            GridData data = new GridData();
            data.grabExcessVerticalSpace = true;
            data.verticalAlignment = 2;
            data.verticalSpan = 5;
            this.table.setLayoutData((Object)data);
            this.table.addMouseListener((MouseListener)new MouseAdapter(){

                public void mouseDoubleClick(MouseEvent arg0) {
                    ColorManagerComposite.this.getLog().info((Object)"double clicked");
                    ColorManagerComposite.this.setEditMode();
                }
            });
            this.table.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent arg0) {
                    TableItem[] selection = ColorManagerComposite.this.table.getSelection();
                    Color color = null;
                    if (selection != null) {
                        TableItem[] arrtableItem = selection;
                        int n = arrtableItem.length;
                        int n2 = 0;
                        while (n2 < n) {
                            TableItem item = arrtableItem[n2];
                            color = (Color)item.getData();
                            ++n2;
                        }
                        ColorManagerComposite.this.getLog().info((Object)(String.valueOf(color.getId()) + "[" + color.getName() + " - " + color.getNumber() + "]"));
                    }
                }
            });
            this.loadColors();
        }
        return this.table;
    }

    public Label getCurrentAction() {
        if (this.currentAction == null) {
            this.currentAction = new Label((Composite)this, 0);
            this.currentAction.setText("Action: Add a new color");
            this.currentAction.setLayoutData((Object)MyGridData.getDgHorizontalDoubleSpan());
            Label horizontalLine = new Label((Composite)this, 258);
            horizontalLine.setLayoutData((Object)MyGridData.getDgHorizontalDoubleSpan());
        }
        return this.currentAction;
    }

    public Label getNameTitle() {
        if (this.nameTitle == null) {
            this.nameTitle = new Label((Composite)this, 0);
            this.nameTitle.setAlignment(131072);
            this.nameTitle.setText("Name:");
            GridData gd = new GridData(768);
            gd.grabExcessHorizontalSpace = true;
            this.nameTitle.setLayoutData((Object)gd);
        }
        return this.nameTitle;
    }

    public Label getNumberTitle() {
        if (this.numberTitle == null) {
            this.numberTitle = new Label((Composite)this, 0);
            this.numberTitle.setText("Number:");
            this.numberTitle.setAlignment(131072);
            GridData gd = new GridData(768);
            gd.grabExcessHorizontalSpace = true;
            this.numberTitle.setLayoutData((Object)gd);
        }
        return this.numberTitle;
    }

    public Text getNameInput() {
        if (this.nameInput == null) {
            this.nameInput = new Text((Composite)this, 2048);
            this.nameInput.setLayoutData((Object)new GridData(768));
        }
        return this.nameInput;
    }

    public Text getNumberInput() {
        if (this.numberInput == null) {
            this.numberInput = new Text((Composite)this, 2048);
            this.numberInput.setLayoutData((Object)new GridData(768));
        }
        return this.numberInput;
    }

    public MasterCenterComposite getParentComposite() {
        return this.parent;
    }

    public MasterCenterComposite getParent() {
        return this.parent;
    }

    public void setEditMode() {
        TableItem[] selection = this.getTable().getSelection();
        Color color = null;
        if (selection != null) {
            TableItem[] arrtableItem = selection;
            int n = arrtableItem.length;
            int n2 = 0;
            while (n2 < n) {
                TableItem item = arrtableItem[n2];
                color = (Color)item.getData();
                ++n2;
            }
        }
        if (color != null) {
            this.getLog().info((Object)(String.valueOf(color.getId()) + "[" + color.getName() + " - " + color.getNumber() + "]"));
            this.currentColor = color;
            this.editing = true;
            this.getNameInput().setText(color.getName());
            this.getNumberInput().setText(color.getNumber());
            this.getAction().setText("update");
            this.getCurrentAction().setText("Color[" + color.getId() + "] - UPDATE");
            this.getCurrentAction().setAlignment(131072);
        } else {
            this.editing = false;
            this.currentColor = null;
        }
    }

    public Button getAction() {
        if (this.action == null) {
            this.action = new Button((Composite)this, 16777224);
            this.action.setText("add");
            GridData gd = new GridData();
            gd.horizontalAlignment = 16777216;
            gd.widthHint = 70;
            this.action.setLayoutData((Object)gd);
            this.action.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent e) {
                    try {
                        ColorManagerComposite.this.performAction();
                    }
                    catch (Exception e1) {
                        ColorManagerComposite.this.getLog().error((Object)"error", (Throwable)e1);
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
                    if (!ColorManagerComposite.this.editing) {
                        ColorManagerComposite.this.hide();
                    } else {
                        ColorManagerComposite.this.clean();
                    }
                }
            });
            GridData data = new GridData(4, 4, true, false);
            data.horizontalSpan = 3;
            Label horizontalLine = new Label((Composite)this, 258);
            horizontalLine.setLayoutData((Object)data);
        }
        return this.cancel;
    }

    public void hide() {
        this.clean();
        this.setParent((Composite)this.getParent().getMaster().getHiddenShell());
        this.setVisible(false);
    }

    private void loadColors() {
        List colors = ColorRepo.findAll();
        this.getTable().removeAll();
        for (Color color : colors) {
            TableItem item = new TableItem(this.getTable(), 0);
            String[] texts = new String[]{String.valueOf(color.getId()), color.getName(), color.getNumber()};
            item.setData((Object)color);
            item.setText(texts);
        }
    }

    private void clean() {
        this.editing = false;
        this.getNameInput().setText("");
        this.getNumberInput().setText("");
        this.getAction().setText("add");
        this.getCurrentAction().setText("Action: Add a new color");
        this.getCurrentAction().setAlignment(16384);
    }

    private void performAction() throws Exception {
        Color color = null;
        String name = this.getNameInput().getText();
        String number = this.getNumberInput().getText();
        String successMsg = "";
        String errorMsg = "";
        if (this.editing) {
            color = this.currentColor;
            successMsg = "Color updated correctly.";
            errorMsg = "There was a problem while updating the color.";
        } else {
            color = new Color();
            successMsg = "Color added correctly.";
            errorMsg = "There was a problem while adding the color.";
        }
        UslcJpa jpa = new UslcJpa();
        color.setName(name);
        color.setNumber(number);
        int style = 2;
        MessageBox diag = new MessageBox(this.getShell(), style);
        diag.setText(Constants.MESSAGE_BOX_DIAG_TITLE.toString());
        if (jpa.persist((Object)color)) {
            diag.setMessage(successMsg);
            this.clean();
            this.loadColors();
        } else {
            style = 1;
            diag.setMessage(errorMsg);
        }
        diag.open();
    }

    private Logger getLog() {
        if (this.log == null) {
            this.log = Logger.getLogger((Class)ColorManagerComposite.class);
            PropertyConfigurator.configure((String)"log4j.properties");
        }
        return this.log;
    }

}

