/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  com.uslc.po.jpa.entity.Color
 *  com.uslc.po.jpa.entity.Item
 *  com.uslc.po.jpa.entity.Size
 *  com.uslc.po.jpa.entity.Upc
 *  com.uslc.po.jpa.logic.ColorRepo
 *  com.uslc.po.jpa.logic.ItemRepo
 *  com.uslc.po.jpa.logic.SizeRepo
 *  com.uslc.po.jpa.logic.UpcRepo
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
 *  org.eclipse.swt.graphics.Color
 *  org.eclipse.swt.graphics.Device
 *  org.eclipse.swt.graphics.Font
 *  org.eclipse.swt.graphics.Image
 *  org.eclipse.swt.layout.FormData
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Button
 *  org.eclipse.swt.widgets.Combo
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Display
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
import com.uslc.po.gui.util.ImageUtils;
import com.uslc.po.gui.util.MyGridData;
import com.uslc.po.jpa.entity.Item;
import com.uslc.po.jpa.entity.Size;
import com.uslc.po.jpa.entity.Upc;
import com.uslc.po.jpa.logic.ColorRepo;
import com.uslc.po.jpa.logic.ItemRepo;
import com.uslc.po.jpa.logic.SizeRepo;
import com.uslc.po.jpa.logic.UpcRepo;
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
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class UpcManagerComposite
extends Composite {
    private MasterCenterComposite parent = null;
    private Label titleLbl = null;
    private Table upcsTbl = null;
    private Label infoLbl = null;
    private Label imageLbl = null;
    private Label itemLbl = null;
    private Combo itemsCmb = null;
    private Label colorLbl = null;
    private Combo colorsCmb = null;
    private Label colorItemLbl = null;
    private Label sizeLbl = null;
    private Combo sizesCmb = null;
    private Label upcLbl = null;
    private Text upcTextTxt = null;
    private Label enabledLbl = null;
    private Button enabledCheck = null;
    private Button actionBtn = null;
    private Button cancelBtn = null;
    private Logger log = null;
    private Upc selectedUpc = null;
    private boolean editing = false;
    private final String infoAddText = "Info: Add a new UPC";
    private GridData labelGd = null;

    public UpcManagerComposite(MasterCenterComposite composite) {
        super((Composite)composite.getMaster().getHiddenShell(), 0);
        this.parent = composite;
        this.initComposite();
    }

    private void initComposite() {
        FormData data = new FormData(500, 440);
        this.setLayoutData((Object)data);
        this.setLayout((Layout)new GridLayout(4, false));
        this.getTitleLbl();
        this.getUpcsTbl();
        this.getInfoLbl();
        this.getImageLbl();
        this.getItemLbl();
        this.getItemsCmb();
        this.getColorLbl();
        this.getColorsCmb();
        this.getColorItemLbl();
        this.getSizeLbl();
        this.getSizesCmb();
        this.getUpcLbl();
        this.getUpcTextTxt();
        this.getEnabledLbl();
        this.getEnabledCheck();
        this.getActionBtn();
        this.getCancelBtn();
        this.loadValues();
    }

    public MasterCenterComposite getParent() {
        return this.parent;
    }

    public Label getTitleLbl() {
        if (this.titleLbl == null) {
            this.titleLbl = new Label((Composite)this, 0);
            this.titleLbl.setText("UPCs");
            GridData gd = new GridData(768);
            gd.grabExcessHorizontalSpace = true;
            gd.horizontalSpan = 4;
            this.titleLbl.setLayoutData((Object)gd);
            Label horizontalLine = new Label((Composite)this, 258);
            horizontalLine.setLayoutData((Object)gd);
        }
        return this.titleLbl;
    }

    public Table getUpcsTbl() {
        if (this.upcsTbl == null) {
            this.upcsTbl = new Table((Composite)this, 4);
            TableColumn id = new TableColumn(this.upcsTbl, 0);
            TableColumn code = new TableColumn(this.upcsTbl, 0);
            TableColumn colorItem = new TableColumn(this.upcsTbl, 0);
            id.setText("id");
            code.setText("code");
            colorItem.setText("color-item");
            id.setWidth(30);
            code.setWidth(100);
            colorItem.setWidth(100);
            this.upcsTbl.setHeaderVisible(true);
            GridData gd = new GridData(1040);
            gd.grabExcessVerticalSpace = true;
            gd.verticalSpan = 11;
            this.upcsTbl.setLayoutData((Object)gd);
            this.upcsTbl.addMouseListener((MouseListener)new MouseAdapter(){

                public void mouseDoubleClick(MouseEvent arg0) {
                    UpcManagerComposite.this.setEditMode();
                }
            });
            GridData data1 = new GridData(1040);
            data1.verticalSpan = 11;
            data1.widthHint = 15;
            Label verticalLine = new Label((Composite)this, 514);
            verticalLine.setLayoutData((Object)data1);
        }
        return this.upcsTbl;
    }

    public Label getInfoLbl() {
        if (this.infoLbl == null) {
            this.infoLbl = new Label((Composite)this, 0);
            this.infoLbl.setText("Info: Add a new UPC");
            this.infoLbl.setLayoutData((Object)MyGridData.getDgHorizontalDoubleSpan());
            Label horizontalLine = new Label((Composite)this, 258);
            horizontalLine.setLayoutData((Object)MyGridData.getDgHorizontalDoubleSpan());
        }
        return this.infoLbl;
    }

    public Label getImageLbl() {
        if (this.imageLbl == null) {
            this.imageLbl = new Label((Composite)this, 0);
            GridData gd = new GridData(150, 60);
            gd.horizontalSpan = 2;
            gd.horizontalAlignment = 16777216;
            this.imageLbl.setLayoutData((Object)gd);
            Label horizontalLine = new Label((Composite)this, 258);
            horizontalLine.setLayoutData((Object)MyGridData.getDgHorizontalDoubleSpan());
        }
        return this.imageLbl;
    }

    public Label getItemLbl() {
        if (this.itemLbl == null) {
            this.itemLbl = new Label((Composite)this, 0);
            this.itemLbl.setText("item:");
            this.itemLbl.setAlignment(131072);
            this.itemLbl.setLayoutData((Object)this.getLabelGd());
        }
        return this.itemLbl;
    }

    public Combo getItemsCmb() {
        if (this.itemsCmb == null) {
            this.itemsCmb = new Combo((Composite)this, 12);
            GridData gd = new GridData(768);
            this.itemsCmb.setLayoutData((Object)gd);
            this.itemsCmb.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent arg0) {
                    UpcManagerComposite.this.updateColorItemLabel();
                }
            });
        }
        return this.itemsCmb;
    }

    public Label getColorLbl() {
        if (this.colorLbl == null) {
            this.colorLbl = new Label((Composite)this, 0);
            this.colorLbl.setText("color:");
            this.colorLbl.setLayoutData((Object)this.getLabelGd());
            this.colorLbl.setAlignment(131072);
        }
        return this.colorLbl;
    }

    public Combo getColorsCmb() {
        if (this.colorsCmb == null) {
            this.colorsCmb = new Combo((Composite)this, 12);
            GridData gd = new GridData(768);
            this.colorsCmb.setLayoutData((Object)gd);
            this.colorsCmb.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent arg0) {
                    UpcManagerComposite.this.updateColorItemLabel();
                }
            });
        }
        return this.colorsCmb;
    }

    private void updateColorItemLabel() {
        com.uslc.po.jpa.entity.Color color = null;
        try {
            color = (com.uslc.po.jpa.entity.Color)this.getColorsCmb().getData(this.getColorsCmb().getItem(this.getColorsCmb().getSelectionIndex()));
        }
        catch (IllegalArgumentException var2_2) {
            // empty catch block
        }
        Item item = null;
        try {
            item = (Item)this.getItemsCmb().getData(this.getItemsCmb().getItem(this.getItemsCmb().getSelectionIndex()));
        }
        catch (IllegalArgumentException var3_4) {
            // empty catch block
        }
        try {
            this.getColorItemLbl().setText(String.valueOf(color.getName()) + "-" + item.getCode());
        }
        catch (NullPointerException var3_5) {
            // empty catch block
        }
    }

    public Label getColorItemLbl() {
        if (this.colorItemLbl == null) {
            this.colorItemLbl = new Label((Composite)this, 0);
            this.colorItemLbl.setText("[ color-item ]");
            this.colorItemLbl.setAlignment(16777216);
            GridData gd = new GridData(768);
            gd.grabExcessHorizontalSpace = true;
            gd.horizontalSpan = 2;
            gd.horizontalAlignment = 16777216;
            gd.widthHint = 150;
            this.colorItemLbl.setLayoutData((Object)gd);
        }
        return this.colorItemLbl;
    }

    public Label getSizeLbl() {
        if (this.sizeLbl == null) {
            this.sizeLbl = new Label((Composite)this, 0);
            this.sizeLbl.setText("size:");
            this.sizeLbl.setLayoutData((Object)this.getLabelGd());
            this.sizeLbl.setAlignment(131072);
        }
        return this.sizeLbl;
    }

    public Combo getSizesCmb() {
        if (this.sizesCmb == null) {
            this.sizesCmb = new Combo((Composite)this, 12);
            GridData gd = new GridData(768);
            this.sizesCmb.setLayoutData((Object)gd);
        }
        return this.sizesCmb;
    }

    public Label getUpcLbl() {
        if (this.upcLbl == null) {
            this.upcLbl = new Label((Composite)this, 0);
            this.upcLbl.setText("upc:");
            this.upcLbl.setLayoutData((Object)this.getLabelGd());
            this.upcLbl.setAlignment(131072);
        }
        return this.upcLbl;
    }

    public Text getUpcTextTxt() {
        if (this.upcTextTxt == null) {
            this.upcTextTxt = new Text((Composite)this, 0);
            GridData gd = new GridData(768);
            this.upcTextTxt.setLayoutData((Object)gd);
        }
        return this.upcTextTxt;
    }

    public Label getEnabledLbl() {
        if (this.enabledLbl == null) {
            this.enabledLbl = new Label((Composite)this, 0);
            this.enabledLbl.setText("enabled:");
            this.enabledLbl.setLayoutData((Object)this.getLabelGd());
            this.enabledLbl.setAlignment(131072);
        }
        return this.enabledLbl;
    }

    public Button getEnabledCheck() {
        if (this.enabledCheck == null) {
            this.enabledCheck = new Button((Composite)this, 32);
        }
        return this.enabledCheck;
    }

    public Button getActionBtn() {
        if (this.actionBtn == null) {
            this.actionBtn = new Button((Composite)this, 8);
            this.actionBtn.setText("add");
            GridData gd = new GridData();
            gd.horizontalAlignment = 131072;
            gd.widthHint = 70;
            this.actionBtn.setLayoutData((Object)gd);
            this.actionBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent e) {
                    try {
                        UpcManagerComposite.this.performAction();
                    }
                    catch (Exception e1) {
                        UpcManagerComposite.this.getLog().error((Object)"error", (Throwable)e1);
                    }
                }
            });
        }
        return this.actionBtn;
    }

    public Button getCancelBtn() {
        if (this.cancelBtn == null) {
            this.cancelBtn = new Button((Composite)this, 8);
            this.cancelBtn.setText("cancel");
            GridData gd = new GridData();
            gd.widthHint = 70;
            gd.horizontalAlignment = 16777216;
            this.cancelBtn.setLayoutData((Object)gd);
            this.cancelBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent e) {
                    if (UpcManagerComposite.this.editing) {
                        UpcManagerComposite.this.clean();
                    } else {
                        UpcManagerComposite.this.hide();
                    }
                }
            });
            GridData gd2 = new GridData(768);
            gd2.grabExcessHorizontalSpace = true;
            gd2.horizontalSpan = 4;
            Label horizontalLine = new Label((Composite)this, 258);
            horizontalLine.setLayoutData((Object)gd2);
        }
        return this.cancelBtn;
    }

    public Logger getLog() {
        if (this.log == null) {
            this.log = Logger.getLogger((Class)UpcManagerComposite.class);
            PropertyConfigurator.configure((String)"log4j.properties");
        }
        return this.log;
    }

    private GridData getLabelGd() {
        if (this.labelGd == null) {
            this.labelGd = new GridData(70, 23);
        }
        return this.labelGd;
    }

    private void performAction() throws Exception {
        Upc upc = null;
        boolean deleted = !this.getEnabledCheck().getSelection();
        String upcCode = this.getUpcTextTxt().getText();
        com.uslc.po.jpa.entity.Color color = (com.uslc.po.jpa.entity.Color)this.getColorsCmb().getData(this.getColorsCmb().getItem(this.getColorsCmb().getSelectionIndex()));
        Item item = (Item)this.getItemsCmb().getData(this.getItemsCmb().getItem(this.getItemsCmb().getSelectionIndex()));
        String colorItem = String.valueOf(color.getName()) + "-" + item.getCode();
        Size size = (Size)this.getSizesCmb().getData(this.getSizesCmb().getItem(this.getSizesCmb().getSelectionIndex()));
        String successMsg = "";
        String errorMsg = "";
        if (this.editing) {
            upc = this.selectedUpc;
            successMsg = "Upc edited correctly";
            errorMsg = "There was a problem editing the selected Upc";
        } else {
            upc = new Upc();
            successMsg = "Upc added correctly.";
            errorMsg = "There was a problem adding the Upc.";
        }
        upc.setColorItemCode(colorItem);
        upc.setDeleted(deleted);
        upc.setUpcCode(upcCode);
        upc.setColor(color);
        upc.setItem(item);
        upc.setSize(size);
        UslcJpa jpa = new UslcJpa();
        int style = 2;
        MessageBox diag = new MessageBox(this.getShell(), style);
        diag.setText(Constants.MESSAGE_BOX_DIAG_TITLE.toString());
        if (jpa.persist((Object)upc)) {
            diag.setMessage(successMsg);
            this.clean();
            this.loadValues();
        } else {
            style = 1;
            diag.setMessage(errorMsg);
        }
        diag.open();
    }

    private void setEditMode() {
        TableItem[] items = this.getUpcsTbl().getSelection();
        Upc upc = null;
        TableItem[] arrtableItem = items;
        int n = arrtableItem.length;
        int n2 = 0;
        while (n2 < n) {
            TableItem item = arrtableItem[n2];
            upc = (Upc)item.getData();
            ++n2;
        }
        if (upc != null) {
            this.editing = true;
            this.selectedUpc = upc;
            this.getItemsCmb().setText(String.valueOf(upc.getItem().getCode()));
            this.getColorsCmb().setText(upc.getColor().getName());
            this.getSizesCmb().setText(String.valueOf(upc.getSize().getWaist()) + " x " + upc.getSize().getInseam());
            this.getUpcTextTxt().setText(upc.getUpcCode());
            this.getEnabledCheck().setSelection(!upc.getDeleted());
            Image ucpImage = ImageUtils.getBarcodeImage(this.getDisplay(), upc.getUpcCode());
            this.getImageLbl().setImage(ucpImage);
            this.getActionBtn().setText("update");
            this.getInfoLbl().setText("UPC[" + upc.getUpcCode() + "] - UPDATE");
            this.updateColorItemLabel();
        } else {
            this.editing = false;
            this.getLog().info((Object)("upc[" + (Object)upc + "]"));
        }
    }

    private void clean() {
        this.editing = false;
        this.selectedUpc = null;
        this.getInfoLbl().setText("Info: Add a new UPC");
        this.getImageLbl().setImage(null);
        this.loadValues();
        this.getColorItemLbl().setText("[ color-item ]");
        this.getUpcTextTxt().setText("");
        this.getEnabledCheck().setSelection(false);
        this.getActionBtn().setText("add");
    }

    public void hide() {
        this.clean();
        this.setParent((Composite)this.getParent().getMaster().getHiddenShell());
        this.setVisible(false);
    }

    public void loadValues() {
        this.getUpcsTbl().removeAll();
        this.getItemsCmb().removeAll();
        this.getColorsCmb().removeAll();
        this.getSizesCmb().removeAll();
        List upcs = UpcRepo.findAll();
        Font font = new Font((Device)this.getDisplay(), "Arial", 8, 0);
        for (Upc upc : upcs) {
            TableItem item = new TableItem(this.getUpcsTbl(), 0);
            if (upc.getDeleted()) {
                item.setBackground(new Color((Device)this.getDisplay(), 255, 224, 237));
            }
            String[] texts = new String[]{String.valueOf(upc.getId()), upc.getUpcCode(), upc.getColorItemCode()};
            item.setData((Object)upc);
            item.setText(texts);
            item.setFont(font);
        }
        List items = ItemRepo.findAll();
        for (Item item : items) {
            this.getItemsCmb().add(String.valueOf(item.getCode()));
            this.getItemsCmb().setData(String.valueOf(item.getCode()), (Object)item);
        }
        List colors = ColorRepo.findAll();
        for (com.uslc.po.jpa.entity.Color color : colors) {
            this.getColorsCmb().add(String.valueOf(color.getName()));
            this.getColorsCmb().setData(String.valueOf(color.getName()), (Object)color);
        }
        List sizes = SizeRepo.findAll();
        for (Size size : sizes) {
            this.getSizesCmb().add(String.valueOf(String.valueOf(size.getWaist()) + " x " + size.getInseam()));
            this.getSizesCmb().setData(String.valueOf(String.valueOf(size.getWaist()) + " x " + size.getInseam()), (Object)size);
        }
    }

}

