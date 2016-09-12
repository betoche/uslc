/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  com.ibm.icu.util.Calendar
 *  com.uslc.po.jpa.entity.Color
 *  com.uslc.po.jpa.entity.Item
 *  com.uslc.po.jpa.entity.PurchaseOrder
 *  com.uslc.po.jpa.entity.Size
 *  com.uslc.po.jpa.entity.Upc
 *  com.uslc.po.jpa.logic.ColorRepo
 *  com.uslc.po.jpa.logic.ItemRepo
 *  com.uslc.po.jpa.logic.SizeRepo
 *  com.uslc.po.jpa.logic.UpcRepo
 *  com.uslc.po.jpa.util.Constants
 *  org.apache.log4j.Logger
 *  org.apache.log4j.PropertyConfigurator
 *  org.apache.poi.openxml4j.exceptions.InvalidFormatException
 *  org.apache.poi.ss.usermodel.Cell
 *  org.apache.poi.ss.usermodel.Row
 *  org.apache.poi.ss.usermodel.Sheet
 *  org.apache.poi.ss.usermodel.Workbook
 *  org.apache.poi.ss.usermodel.WorkbookFactory
 *  org.eclipse.swt.events.SelectionAdapter
 *  org.eclipse.swt.events.SelectionEvent
 *  org.eclipse.swt.events.SelectionListener
 *  org.eclipse.swt.graphics.Device
 *  org.eclipse.swt.graphics.Font
 *  org.eclipse.swt.graphics.FontData
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
package com.uslc.po.gui.master;

import com.ibm.icu.util.Calendar;
import com.uslc.po.gui.master.MasterCenterComposite;
import com.uslc.po.gui.master.MasterLeftComposite;
import com.uslc.po.gui.master.MasterRightComposite;
import com.uslc.po.gui.master.MasterTopComposite;
import com.uslc.po.gui.master.NewPurchaseOrderDetailComposite;
import com.uslc.po.gui.master.POMaster;
import com.uslc.po.gui.util.Master;
import com.uslc.po.gui.util.PurchaseOrder;
import com.uslc.po.gui.util.PurchaseOrderDetailUpload;
import com.uslc.po.jpa.entity.Color;
import com.uslc.po.jpa.entity.Item;
import com.uslc.po.jpa.entity.Size;
import com.uslc.po.jpa.entity.Upc;
import com.uslc.po.jpa.logic.ColorRepo;
import com.uslc.po.jpa.logic.ItemRepo;
import com.uslc.po.jpa.logic.SizeRepo;
import com.uslc.po.jpa.logic.UpcRepo;
import com.uslc.po.jpa.util.Constants;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
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

public class NewPurchaseOrderComposite
extends Composite {
    private MasterCenterComposite parent = null;
    private Label titleLbl = null;
    private Text ordenNumberTxt = null;
    private Label shipFromLbl = null;
    private Text shipFromTxt = null;
    private Label shipToLbl = null;
    private Text shipToTxt = null;
    private Label cartonLbl = null;
    private Text cartonTxt = null;
    private Label itemsLbl = null;
    private Combo itemsCbx = null;
    private Label deptLbl = null;
    private Text deptTxt = null;
    private Label timestampLbl = null;
    private Text timestampTxt = null;
    private Label poDetailLbl = null;
    private Table poDetailTbl = null;
    private Button add = null;
    private Button del = null;
    private Label totalItemsLbl = null;
    private Font fontLabels = null;
    private Font fontTexts = null;
    private Font fontAreas = null;
    private GridData gdLabels = null;
    private GridData gdTextAreas = null;
    private GridData gdTexts = null;
    private GridData gdSmallButtons = null;
    private GridData gdHorizontal = null;
    private List<PODetailData> poDetailData = null;
    private Logger log = null;
    private int totalItems = 0;

    public NewPurchaseOrderComposite(MasterCenterComposite composite) {
        super((Composite)composite.getMaster().getHiddenShell(), 0);
        this.parent = composite;
        this.initComposite();
    }

    private void initComposite() {
        FormData data = new FormData(550, 550);
        this.setLayoutData((Object)data);
        this.setLayout((Layout)new GridLayout(6, false));
        this.getTitleLbl();
        this.getOrderNumberTxt();
        this.getShipFromLbl();
        this.getShipFromTxt();
        this.getShipToLbl();
        this.getShipToTxt();
        this.getCartonLbl();
        this.getCartonTxt();
        this.getItemsLbl();
        this.getItemsCbx();
        this.getDeptLbl();
        this.getDeptTxt();
        this.getTimestampLbl();
        this.getTimestampTxt();
        this.getPoDetailLbl();
        this.getPoDetailTbl();
        this.getAdd();
        this.getDel();
        this.getTotalItemsLbl();
        this.loadValues();
    }

    public Label getTitleLbl() {
        if (this.titleLbl == null) {
            this.getLog().info((Object)"getTitleLbl()");
            this.titleLbl = new Label((Composite)this, 0);
            this.titleLbl.setText("purchase order: ");
        }
        return this.titleLbl;
    }

    public Text getOrderNumberTxt() {
        if (this.ordenNumberTxt == null) {
            this.ordenNumberTxt = new Text((Composite)this, 0);
            this.ordenNumberTxt.setLayoutData((Object)this.getGdTexts());
            this.ordenNumberTxt.setFont(this.getFontTexts());
            Label horizontaLabel = new Label((Composite)this, 258);
            horizontaLabel.setLayoutData((Object)this.getGdHorizontal());
        }
        return this.ordenNumberTxt;
    }

    public Label getShipFromLbl() {
        if (this.shipFromLbl == null) {
            this.shipFromLbl = new Label((Composite)this, 0);
            this.shipFromLbl.setText("ship from:");
            this.shipFromLbl.setLayoutData((Object)this.getGdLabels());
            this.shipFromLbl.setAlignment(131072);
            this.shipFromLbl.setFont(this.getFontLabels());
        }
        return this.shipFromLbl;
    }

    public Text getShipFromTxt() {
        if (this.shipFromTxt == null) {
            this.shipFromTxt = new Text((Composite)this, 578);
            this.shipFromTxt.setLayoutData((Object)this.getGdTextAreas());
            this.shipFromTxt.setFont(this.getFontTexts());
        }
        return this.shipFromTxt;
    }

    public Label getShipToLbl() {
        if (this.shipToLbl == null) {
            this.shipToLbl = new Label((Composite)this, 0);
            this.shipToLbl.setText("ship to:");
            this.shipToLbl.setLayoutData((Object)this.getGdLabels());
            this.shipToLbl.setAlignment(131072);
            this.shipToLbl.setFont(this.getFontLabels());
        }
        return this.shipToLbl;
    }

    public Text getShipToTxt() {
        if (this.shipToTxt == null) {
            this.shipToTxt = new Text((Composite)this, 578);
            this.shipToTxt.setLayoutData((Object)this.getGdTextAreas());
            this.shipToTxt.setFont(this.getFontTexts());
        }
        return this.shipToTxt;
    }

    public Label getCartonLbl() {
        if (this.cartonLbl == null) {
            this.cartonLbl = new Label((Composite)this, 0);
            this.cartonLbl.setText("carton:");
            this.cartonLbl.setLayoutData((Object)this.getGdLabels());
            this.cartonLbl.setAlignment(131072);
            this.cartonLbl.setFont(this.getFontLabels());
        }
        return this.cartonLbl;
    }

    public Text getCartonTxt() {
        if (this.cartonTxt == null) {
            this.cartonTxt = new Text((Composite)this, 12);
            this.cartonTxt.setLayoutData((Object)this.getGdTexts());
            this.cartonTxt.setFont(this.getFontTexts());
        }
        return this.cartonTxt;
    }

    public Label getItemsLbl() {
        if (this.itemsLbl == null) {
            this.itemsLbl = new Label((Composite)this, 0);
            this.itemsLbl.setText("items:");
            this.itemsLbl.setLayoutData((Object)this.getGdLabels());
            this.itemsLbl.setAlignment(131072);
            this.itemsLbl.setFont(this.getFontLabels());
        }
        return this.itemsLbl;
    }

    public Combo getItemsCbx() {
        if (this.itemsCbx == null) {
            this.itemsCbx = new Combo((Composite)this, 8);
            this.itemsCbx.setLayoutData((Object)this.getGdTexts());
            this.itemsCbx.setFont(this.getFontTexts());
            this.itemsCbx.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent arg0) {
                    NewPurchaseOrderComposite.this.getParent().getMaster().getRightComposite().getNewPurchaseOrderDetail().loadVaues();
                }
            });
        }
        return this.itemsCbx;
    }

    public Label getDeptLbl() {
        if (this.deptLbl == null) {
            this.deptLbl = new Label((Composite)this, 0);
            this.deptLbl.setText("dept #:");
            this.deptLbl.setLayoutData((Object)this.getGdLabels());
            this.deptLbl.setAlignment(131072);
            this.deptLbl.setFont(this.getFontLabels());
        }
        return this.deptLbl;
    }

    public Text getDeptTxt() {
        if (this.deptTxt == null) {
            this.deptTxt = new Text((Composite)this, 4);
            this.deptTxt.setLayoutData((Object)this.getGdTexts());
            this.deptTxt.setFont(this.getFontTexts());
        }
        return this.deptTxt;
    }

    public Label getTimestampLbl() {
        if (this.timestampLbl == null) {
            this.timestampLbl = new Label((Composite)this, 0);
            this.timestampLbl.setText("timestamp:");
            this.timestampLbl.setLayoutData((Object)this.getGdLabels());
            this.timestampLbl.setAlignment(131072);
            this.timestampLbl.setFont(this.getFontLabels());
        }
        return this.timestampLbl;
    }

    public Text getTimestampTxt() {
        if (this.timestampTxt == null) {
            this.timestampTxt = new Text((Composite)this, 12);
            this.timestampTxt.setFont(this.getFontTexts());
            GridData gd = new GridData();
            gd = new GridData(768);
            gd.grabExcessHorizontalSpace = true;
            gd.horizontalSpan = 2;
            gd.widthHint = 150;
            gd.heightHint = 23;
            gd.horizontalAlignment = 16777216;
            this.timestampTxt.setLayoutData((Object)gd);
            Label horizontaLabel = new Label((Composite)this, 258);
            horizontaLabel.setLayoutData((Object)this.getGdHorizontal());
        }
        return this.timestampTxt;
    }

    public Label getPoDetailLbl() {
        if (this.poDetailLbl == null) {
            this.poDetailLbl = new Label((Composite)this, 0);
            this.poDetailLbl.setText("PO Details:");
            this.poDetailLbl.setLayoutData((Object)this.getGdHorizontal());
        }
        return this.poDetailLbl;
    }

    public Table getPoDetailTbl() {
        if (this.poDetailTbl == null) {
            this.poDetailTbl = new Table((Composite)this, 2);
            this.poDetailTbl.setHeaderVisible(true);
            Font f = this.poDetailTbl.getFont();
            FontData[] fds = f.getFontData();
            int i = 0;
            while (i < fds.length) {
                fds[i].setHeight(8);
                ++i;
            }
            this.poDetailTbl.setFont(new Font((Device)this.getDisplay(), fds));
            TableColumn upc = new TableColumn(this.poDetailTbl, 0);
            upc.setAlignment(16777216);
            upc.setText("upc");
            upc.setWidth(106);
            TableColumn colorName = new TableColumn(this.poDetailTbl, 0);
            colorName.setText("color name");
            colorName.setWidth(89);
            TableColumn colorItem = new TableColumn(this.poDetailTbl, 0);
            colorItem.setText("");
            colorItem.setWidth(106);
            TableColumn colorNumber = new TableColumn(this.poDetailTbl, 0);
            colorNumber.setText("color #");
            colorNumber.setWidth(65);
            TableColumn sku = new TableColumn(this.poDetailTbl, 0);
            sku.setText("sku");
            sku.setWidth(40);
            TableColumn size = new TableColumn(this.poDetailTbl, 0);
            size.setText("size");
            size.setWidth(63);
            TableColumn quantity = new TableColumn(this.poDetailTbl, 0);
            quantity.setText("qty");
            quantity.setWidth(39);
            GridData gd = new GridData(768);
            gd.heightHint = 250;
            gd.horizontalSpan = 5;
            gd.verticalSpan = 10;
            gd.verticalAlignment = 128;
            this.poDetailTbl.setLayoutData((Object)gd);
        }
        return this.poDetailTbl;
    }

    public Button getAdd() {
        if (this.add == null) {
            this.add = new Button((Composite)this, 8);
            this.add.setText("+");
            this.add.setLayoutData((Object)this.getGdSmallButtons());
            this.add.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent arg0) {
                    Item item = null;
                    try {
                        item = (Item)NewPurchaseOrderComposite.this.getItemsCbx().getData(NewPurchaseOrderComposite.this.getItemsCbx().getItem(NewPurchaseOrderComposite.this.getItemsCbx().getSelectionIndex()));
                    }
                    catch (Exception var3_3) {
                        // empty catch block
                    }
                    if (item != null) {
                        NewPurchaseOrderComposite.this.getParent().getMaster().getRightComposite().showComposite(NewPurchaseOrderComposite.this.getParent().getMaster().getRightComposite().getNewPurchaseOrderDetail());
                    } else {
                        MessageBox box = new MessageBox(NewPurchaseOrderComposite.this.getShell(), 2);
                        box.setText(Constants.MESSAGE_BOX_DIAG_TITLE.toString());
                        box.setMessage("please select an item before adding the purchase order details");
                        box.open();
                        NewPurchaseOrderComposite.this.getItemsCbx().setFocus();
                    }
                }
            });
        }
        return this.add;
    }

    public Button getDel() {
        if (this.del == null) {
            this.del = new Button((Composite)this, 8);
            this.del.setText("-");
            this.del.setLayoutData((Object)this.getGdSmallButtons());
            this.del.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent arg0) {
                    NewPurchaseOrderComposite.this.removeSelectedPoDetail();
                }
            });
        }
        return this.del;
    }

    public Label getTotalItemsLbl() {
        if (this.totalItemsLbl == null) {
            this.totalItemsLbl = new Label((Composite)this, 2048);
            this.totalItemsLbl.setText("total items: 0");
            this.totalItemsLbl.setAlignment(131072);
            GridData gd = new GridData(132);
            gd.grabExcessHorizontalSpace = true;
            gd.grabExcessVerticalSpace = true;
            gd.horizontalSpan = 5;
            gd.widthHint = 140;
            this.totalItemsLbl.setLayoutData((Object)gd);
            Label horizontaLabel = new Label((Composite)this, 258);
            horizontaLabel.setLayoutData((Object)this.getGdHorizontal());
        }
        return this.totalItemsLbl;
    }

    private void removeSelectedPoDetail() {
        TableItem[] items;
        TableItem[] arrtableItem = items = this.getPoDetailTbl().getSelection();
        int n = arrtableItem.length;
        int n2 = 0;
        while (n2 < n) {
            TableItem tableItem = arrtableItem[n2];
            PODetailData obj = (PODetailData)tableItem.getData();
            this.getPoDetailData().remove(obj);
            ++n2;
        }
        this.loadPoDetails();
    }

    public GridData getGdLabels() {
        if (this.gdLabels == null) {
            this.gdLabels = new GridData(70, 23);
            this.gdLabels.horizontalAlignment = 131072;
            this.gdLabels.verticalAlignment = 128;
        }
        return this.gdLabels;
    }

    public GridData getGdTexts() {
        if (this.gdTexts == null) {
            this.gdTexts = new GridData();
            this.gdTexts.horizontalSpan = 2;
            this.gdTexts.widthHint = 150;
            this.gdTexts.heightHint = 23;
        }
        return this.gdTexts;
    }

    public GridData getGdTextAreas() {
        if (this.gdTextAreas == null) {
            this.gdTextAreas = new GridData();
            this.gdTextAreas.horizontalSpan = 2;
            this.gdTextAreas.widthHint = 150;
            this.gdTextAreas.heightHint = 46;
        }
        return this.gdTextAreas;
    }

    public GridData getGdSmallButtons() {
        if (this.gdSmallButtons == null) {
            this.gdSmallButtons = new GridData(23, 23);
            this.gdSmallButtons.horizontalAlignment = 16777216;
            this.gdSmallButtons.verticalAlignment = 16777216;
        }
        return this.gdSmallButtons;
    }

    public GridData getGdHorizontal() {
        if (this.gdHorizontal == null) {
            this.gdHorizontal = new GridData(768);
            this.gdHorizontal.grabExcessHorizontalSpace = true;
            this.gdHorizontal.horizontalSpan = 6;
        }
        return this.gdHorizontal;
    }

    public void hide() {
        this.clean();
        this.setParent((Composite)this.getParent().getMaster().getHiddenShell());
        this.setVisible(false);
        this.getParent().getMaster().getTopComposite().getCancel().setEnabled(false);
    }

    private void clean() {
        this.poDetailData = null;
        this.getShipFromTxt().setText("");
        this.getShipToTxt().setText("");
        this.getCartonTxt().setText("0");
        this.getDeptTxt().setText("");
        this.getItemsCbx().select(-1);
        this.loadValues();
        this.loadPoDetails();
    }

    public MasterCenterComposite getParent() {
        return this.parent;
    }

    private void loadValues() {
        this.getLog().info((Object)"loadValues method");
        this.getItemsCbx().removeAll();
        List items = ItemRepo.findAll();
        for (Item item : items) {
            this.getItemsCbx().add(String.valueOf(item.getCode()));
            this.getItemsCbx().setData(String.valueOf(item.getCode()), (Object)item);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        this.getTimestampTxt().setText(sdf.format(cal.getTime()));
        this.getCartonTxt().setText("0");
        try {
            this.getShipFromTxt().setText(this.getParent().getMaster().getMaster().getShipFrom());
            this.getShipToTxt().setText(this.getParent().getMaster().getMaster().getShipTo());
            this.getDeptTxt().setText(this.getParent().getMaster().getMaster().getDepartmentNumber());
        }
        catch (IOException | NullPointerException e) {
            this.getLog().error((Object)"Error at loadVaues method", (Throwable)e);
        }
    }

    private Font getFontLabels() {
        if (this.fontLabels == null) {
            this.fontLabels = new Font((Device)this.getDisplay(), "Segoe UI", 7, 0);
        }
        return this.fontLabels;
    }

    private Font getFontTexts() {
        if (this.fontTexts == null) {
            try {
                String fontName = "";
                FontData[] arrfontData = this.getParent().getMaster().getMaster().getDefaultMasterFont().getFontData();
                int n = arrfontData.length;
                int n2 = 0;
                while (n2 < n) {
                    FontData fd = arrfontData[n2];
                    fontName = fd.getName();
                    ++n2;
                }
                this.getLog().info((Object)("fontName[" + fontName + "]"));
                this.fontTexts = new Font((Device)this.getDisplay(), fontName, 8, 0);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this.fontTexts;
    }

    private Font getFontAreas() {
        if (this.fontAreas == null) {
            try {
                String fontName = "";
                FontData[] arrfontData = this.getParent().getMaster().getMaster().getDefaultMasterFont().getFontData();
                int n = arrfontData.length;
                int n2 = 0;
                while (n2 < n) {
                    FontData fd = arrfontData[n2];
                    fontName = fd.getName();
                    ++n2;
                }
                this.getLog().info((Object)("fontName[" + fontName + "]"));
                this.fontAreas = new Font((Device)this.getDisplay(), fontName, 8, 0);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this.fontAreas;
    }

    public List<PODetailData> getPoDetailData() {
        if (this.poDetailData == null) {
            this.poDetailData = new ArrayList<PODetailData>();
        }
        return this.poDetailData;
    }

    public boolean addPoDetail(PODetailData poDetail) {
        boolean success = false;
        boolean alreadyIn = false;
        Upc newUpc = poDetail.getUpc();
        int qty = poDetail.getQty();
        int itemsPerCarton = poDetail.getItemsPerCarton();
        boolean preticketed = poDetail.isPreticketed();
        for (PODetailData o : this.getPoDetailData()) {
            Upc tmpUpc = o.getUpc();
            int tmpItemsPerCarton = o.getItemsPerCarton();
            boolean tmpPreticketed = o.isPreticketed();
            if (tmpUpc.getId() != newUpc.getId() || tmpItemsPerCarton != itemsPerCarton || tmpPreticketed != preticketed) continue;
            this.getLog().info((Object)("tmpUpc[" + tmpUpc.getId() + "," + tmpUpc.getUpcCode() + "] - newUpc[" + newUpc.getId() + "," + newUpc.getUpcCode() + "]"));
            alreadyIn = true;
            break;
        }
        if (alreadyIn) {
            MessageBox msg = new MessageBox(this.getShell(), 1);
            msg.setText(Constants.MESSAGE_BOX_DIAG_TITLE.toString());
            msg.setMessage("selected upc[" + newUpc.getUpcCode() + "] is already in list, select a different one, or change the quantity");
            msg.open();
        } else {
            this.getPoDetailData().add(poDetail);
            this.loadPoDetails();
            success = true;
        }
        return success;
    }

    private void loadPoDetails() {
        this.getPoDetailTbl().removeAll();
        this.getLog().info((Object)("loadPoDetails - getPoDetailTbl().getItemCount( " + this.getPoDetailTbl().getItemCount() + " )"));
        int sku = 0;
        this.totalItems = 0;
        this.getLog().info((Object)("loadPoDetails - getPoDetailData().size( " + this.getPoDetailData().size() + " )"));
        for (PODetailData det : this.getPoDetailData()) {
            Upc upc = det.getUpc();
            int qty = det.getQty();
            int itemsPerCarton = det.getItemsPerCarton();
            int cartons = (int)Math.ceil(new Double(qty) / new Double(itemsPerCarton));
            int qtyControl = qty;
            this.getLog().info((Object)("qty[" + qty + "], itemsPerCarton[" + itemsPerCarton + "], cartons[" + cartons + "], qtyControl[" + qtyControl + "]"));
            int i = 0;
            while (i < cartons) {
                int itemsInCarton = 0;
                itemsInCarton = qtyControl < itemsPerCarton ? qtyControl : itemsPerCarton;
                qtyControl -= itemsInCarton;
                TableItem row = new TableItem(this.getPoDetailTbl(), 8);
                String size = "";
                size = this.getOrderNumberTxt().getText().trim().endsWith("11") ? String.valueOf(upc.getSize().getWaist()) : String.valueOf(upc.getSize().getWaist()) + " x " + upc.getSize().getInseam();
                String[] arrstring = new String[]{upc.getUpcCode(), upc.getColor().getName(), String.valueOf(upc.getColor().getName()) + "-" + upc.getItem().getCode(), upc.getColor().getNumber(), String.valueOf(++sku), size, String.valueOf(itemsInCarton)};
                String[] texts = arrstring;
                row.setText(texts);
                row.setData((Object)det);
                ++i;
            }
            this.totalItems += qty;
        }
        if (this.getPoDetailTbl().getItems().length > 0) {
            this.getParent().getMaster().getTopComposite().getSavePo().setEnabled(true);
            this.getCartonTxt().setText(String.valueOf(sku));
        } else {
            this.getParent().getMaster().getTopComposite().getSavePo().setEnabled(false);
            this.getCartonTxt().setText("0");
            this.totalItems = 0;
        }
        this.getLog().info((Object)("total items: " + this.totalItems));
        this.getTotalItemsLbl().setText("total items: " + String.valueOf(this.totalItems));
        this.layout();
        this.getParent().layout();
        this.getParent().getMaster().getShell().layout();
    }

    private Logger getLog() {
        if (this.log == null) {
            this.log = Logger.getLogger((Class)NewPurchaseOrderComposite.class);
            PropertyConfigurator.configure((String)"log4j.properties");
        }
        return this.log;
    }

    public void savePo() {
        String departmentNumber = "";
        String referenceNumber = "";
        String shipFrom = "";
        String shipTo = "";
        int totalCartons = 0;
        com.uslc.po.jpa.entity.PurchaseOrder po = null;
        try {
            departmentNumber = this.getDeptTxt().getText().trim();
            referenceNumber = this.getOrderNumberTxt().getText();
            shipFrom = this.getShipFromTxt().getText();
            shipTo = this.getShipToTxt().getText();
            totalCartons = Integer.parseInt(this.getCartonTxt().getText());
            this.getLog().info((Object)("getPoDetailData().size()[" + this.getPoDetailData().size() + "] - departmentNumber[" + departmentNumber + "]"));
            po = PurchaseOrder.createPurchaseOrderCascade(departmentNumber, referenceNumber, shipFrom, shipTo, totalCartons, this.totalItems, this.getPoDetailData());
            MessageBox box = null;
            if (po != null) {
                box = new MessageBox(this.getShell(), 2);
                this.clean();
                this.getParent().getMaster().getRightComposite().hideAllComposites();
                this.getParent().getMaster().getLeftComposite().fillPo();
                box.setMessage("purchase order added correctly to the system.");
            } else {
                box = new MessageBox(this.getShell(), 1);
                box.setMessage("there was a problem adding the purchase order.");
            }
            box.setText(Constants.MESSAGE_BOX_DIAG_TITLE.toString());
            box.open();
        }
        catch (Exception e) {
            e.printStackTrace();
            MessageBox box = new MessageBox(this.getShell(), 1);
            box.setText(Constants.MESSAGE_BOX_DIAG_TITLE.toString());
            box.setMessage(e.getMessage());
            box.open();
        }
    }

    public Exception fillWithUploadedFile(String path) {
        int upcCol = -1;
        int deptCol = -1;
        int itemCol = -1;
        int poCol = -1;
        int colorNameCol = -1;
        int colorNumCol = -1;
        int sizeCol = -1;
        int qtyCol = -1;
        int qtyPerCartonCol = -1;
        this.getPoDetailData().clear();
        Exception e = null;
        try {
            FileInputStream file = new FileInputStream(new File(path));
            Workbook workbook = WorkbookFactory.create((InputStream)file);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator rows = sheet.iterator();
            ArrayList<PurchaseOrderDetailUpload> podUpload = new ArrayList<PurchaseOrderDetailUpload>();
            HashMap<String, Upc> uploadUpc = new HashMap<String, Upc>();
            HashMap<String, Color> uploadColor = new HashMap<String, Color>();
            HashMap<String, Size> uploadSize = new HashMap<String, Size>();
            HashMap<String, Item> uploadItem = new HashMap<String, Item>();
            ArrayList det = new ArrayList();
            boolean missingInCatalog = false;
            while (rows.hasNext()) {
                Row row = (Row)rows.next();
                if (upcCol == -1 || deptCol == -1 || itemCol == -1 || poCol == -1 || colorNameCol == -1 || colorNumCol == -1 || sizeCol == -1 || qtyCol == -1) {
                    for (Cell cell : row) {
                        String name = cell.getStringCellValue().toLowerCase().trim();
                        int colIndex = cell.getColumnIndex();
                        this.getLog().info((Object)("name[" + name + "] - colIndex[" + colIndex + "]"));
                        if (name.contains("upc")) {
                            upcCol = colIndex;
                        }
                        if (name.contains("dept")) {
                            deptCol = colIndex;
                        }
                        if (name.contains("item")) {
                            itemCol = colIndex;
                        }
                        if (name.contains("po #")) {
                            poCol = colIndex;
                        }
                        if (name.contains("color name")) {
                            colorNameCol = colIndex;
                        }
                        if (name.contains("color #")) {
                            colorNumCol = colIndex;
                        }
                        if (name.contains("size")) {
                            sizeCol = colIndex;
                        }
                        if (name.contains("po qty")) {
                            qtyCol = colIndex;
                        }
                        if (!name.contains("carton")) continue;
                        qtyPerCartonCol = colIndex;
                    }
                    this.getLog().info((Object)("upcCol[" + upcCol + "], deptCol[" + deptCol + "], " + "itemCol[" + itemCol + "], poCol[" + poCol + "], colorNameCol[" + colorNameCol + "], colorNumCol[" + colorNumCol + "], sizeCol[" + sizeCol + "], " + "qtyCol[" + qtyCol + "], qtyPerCartonCol[" + qtyPerCartonCol + "]"));
                    continue;
                }
                try {
                    int itemsPerCartonVal;
                    String upcCodeVal = this.getStringValue(row.getCell(upcCol));
                    String deptNumberVal = this.getStringValue(row.getCell(deptCol));
                    String itemCodeVal = this.getStringValue(row.getCell(itemCol));
                    String poNumberVal = this.getStringValue(row.getCell(poCol));
                    String colorNameVal = this.getStringValue(row.getCell(colorNameCol));
                    String colorNumberVal = this.getStringValue(row.getCell(colorNumCol));
                    String sizeVal = this.getStringValue(row.getCell(sizeCol));
                    int itemsVal = this.getIntValue(row.getCell(qtyCol));
                    int n = itemsPerCartonVal = qtyPerCartonCol != -1 ? this.getIntValue(row.getCell(qtyPerCartonCol)) : this.getParent().getMaster().getMaster().getDefaultQtyPerCarton();
                    if (colorNameVal == null || colorNameVal.compareTo("") == 0 || itemCodeVal.compareTo("") == 0 || sizeVal == null || sizeVal.compareTo("") == 0 || upcCodeVal == null || upcCodeVal.compareTo("") == 0) break;
                    Color color = ColorRepo.findByNumber((String)colorNumberVal);
                    Item item = ItemRepo.findByCode((String)itemCodeVal);
                    Size size = null;
                    size = poNumberVal.endsWith("11") ? SizeRepo.findByWaist((int)Integer.parseInt(sizeVal)) : SizeRepo.findByWaistInstream((String)sizeVal);
                    Upc upc = UpcRepo.findByCode((String)upcCodeVal);
                    if (color == null) {
                        color = new Color();
                        color.setName(colorNameVal);
                        color.setNumber(colorNumberVal);
                        missingInCatalog = true;
                        this.getLog().info((Object)("not found color{" + colorNameVal + "}"));
                    }
                    if (item == null) {
                        item = new Item();
                        item.setCode(itemCodeVal);
                        missingInCatalog = true;
                        this.getLog().info((Object)("not found item{" + itemCodeVal + "}"));
                    }
                    if (size == null) {
                        size = new Size();
                        int waist = Integer.parseInt(sizeVal.substring(0, 2));
                        int inseam = 36;
                        if (!poNumberVal.endsWith("11")) {
                            inseam = Integer.parseInt(sizeVal.substring(3, 5));
                        }
                        size.setWaist(waist);
                        size.setHip(30);
                        size.setInseam(inseam);
                        missingInCatalog = true;
                        this.getLog().info((Object)("not found size{" + sizeVal + "}"));
                    }
                    if (upc == null) {
                        upc = new Upc();
                        upc.setColor(color);
                        upc.setColorItemCode(String.valueOf(color.getName()) + "-" + item.getCode());
                        upc.setDeleted(false);
                        upc.setItem(item);
                        upc.setSize(size);
                        upc.setUpcCode(upcCodeVal);
                        upc.setTimestamp(Calendar.getInstance().getTime());
                        missingInCatalog = true;
                        this.getLog().info((Object)("not found upc{" + upcCodeVal + "}"));
                    }
                    uploadColor.put(colorNumberVal, color);
                    uploadItem.put(itemCodeVal, item);
                    uploadSize.put(sizeVal, size);
                    uploadUpc.put(upcCodeVal, upc);
                    podUpload.add(new PurchaseOrderDetailUpload(upcCodeVal, deptNumberVal, itemCodeVal, poNumberVal, colorNameVal, colorNumberVal, sizeVal, itemsVal, itemsPerCartonVal));
                    continue;
                }
                catch (Exception e1) {
                    this.getLog().error((Object)"error while reading po details", (Throwable)e1);
                }
            }
            this.getLog().info((Object)("podUpload.size( " + podUpload.size() + ")"));
            if (missingInCatalog) {
                String message = this.countMissingCatalogs(uploadColor, uploadItem, uploadSize, uploadUpc);
                MessageBox msg = new MessageBox(this.getShell(), 196);
                msg.setText(Constants.MESSAGE_BOX_DIAG_TITLE.toString());
                msg.setMessage(message);
                e = msg.open() == 64 ? (this.addMisingCatalogs(uploadColor, uploadItem, uploadSize, uploadUpc) ? this.addUploadedPurchaseOrder(uploadColor, uploadItem, uploadSize, uploadUpc, podUpload) : new Exception("there was a problem adding the catalogs.")) : new Exception("there are missing catalogs that need to be added before");
            } else {
                e = this.addUploadedPurchaseOrder(uploadColor, uploadItem, uploadSize, uploadUpc, podUpload);
            }
        }
        catch (FileNotFoundException e1) {
            e1.printStackTrace();
            this.getLog().error((Object)e1);
            e = e1;
        }
        catch (InvalidFormatException e1) {
            e1.printStackTrace();
            this.getLog().error((Object)e1);
            e = e1;
        }
        catch (IOException e1) {
            e1.printStackTrace();
            this.getLog().error((Object)e1);
            e = e1;
        }
        catch (NullPointerException e1) {
            e1.printStackTrace();
            this.getLog().error((Object)e1);
            e = e1;
        }
        catch (Exception e1) {
            e1.printStackTrace();
            this.getLog().error((Object)e1);
            e = e1;
        }
        return e;
    }

    private Exception addUploadedPurchaseOrder(HashMap<String, Color> uploadColor, HashMap<String, Item> uploadItem, HashMap<String, Size> uploadSize, HashMap<String, Upc> uploadUpc, List<PurchaseOrderDetailUpload> podUpload) {
        IOException e = null;
        this.getLog().info((Object)("uploadItem.size(" + uploadItem.size() + ")"));
        String poReference = "";
        for (PurchaseOrderDetailUpload podu2 : podUpload) {
            poReference = podu2.getPoNumber();
            if (poReference != null && poReference.compareTo("") != 0) break;
        }
        try {
            this.getOrderNumberTxt().setText(poReference);
            this.getShipFromTxt().setText(this.getParent().getMaster().getMaster().getShipFrom());
            this.getShipToTxt().setText(this.getParent().getMaster().getMaster().getShipTo());
            this.getDeptTxt().setText(this.getParent().getMaster().getMaster().getDepartmentNumber());
            this.getPoDetailData().clear();
            for (PurchaseOrderDetailUpload podu2 : podUpload) {
                PODetailData poData = new PODetailData(uploadUpc.get(podu2.getUpcCode()), podu2.getItems(), podu2.getItemsPerCarton(), !poReference.endsWith("0011"));
                this.addPoDetail(poData);
            }
            this.loadPoDetails();
        }
        catch (IOException e1) {
            this.getLog().error((Object)"error in addUploadedPurchaseOrder method", (Throwable)e1);
            e = e1;
        }
        return e;
    }

    private boolean addMisingCatalogs(HashMap<String, Color> uploadColor, HashMap<String, Item> uploadItem, HashMap<String, Size> uploadSize, HashMap<String, Upc> uploadUpc) throws Exception {
        boolean success = false;
        for (Map.Entry<String, Color> entry : uploadColor.entrySet()) {
            if (entry.getValue().getId() > 0) continue;
            uploadColor.put(entry.getKey(), ColorRepo.createColor((Color)entry.getValue()));
        }
        for (Map.Entry<String, Item> entry2 : uploadItem.entrySet()) {
            if (entry2.getValue().getId() > 0) continue;
            uploadItem.put(entry2.getKey(), ItemRepo.createItem((Item)entry2.getValue()));
        }
        for (Map.Entry<String, Size> entry3 : uploadSize.entrySet()) {
            if (entry3.getValue().getId() > 0) continue;
            uploadSize.put(entry3.getKey(), SizeRepo.createSize((Size)entry3.getValue()));
        }
        for (Map.Entry<String, Upc> entry4 : uploadUpc.entrySet()) {
            Upc upc = entry4.getValue();
            upc.setColor(ColorRepo.findByNumber((String)upc.getColor().getNumber()));
            upc.setItem(ItemRepo.findByCode((String)upc.getItem().getCode()));
            upc.setSize(SizeRepo.findByWaistInstream((String)String.valueOf(String.valueOf(upc.getSize().getWaist()) + "x" + upc.getSize().getInseam())));
            if (entry4.getValue().getId() > 0) continue;
            uploadUpc.put(entry4.getKey(), UpcRepo.createUpc((Upc)entry4.getValue()));
        }
        success = true;
        return success;
    }

    private String countMissingCatalogs(HashMap<String, Color> uploadColor, HashMap<String, Item> uploadItem, HashMap<String, Size> uploadSize, HashMap<String, Upc> uploadUpc) {
        String msg = "there are ";
        int missingColors = 0;
        int missingItems = 0;
        int missingSizes = 0;
        int missindUpcs = 0;
        for (Map.Entry<String, Color> entry : uploadColor.entrySet()) {
            if (entry.getValue().getId() > 0) continue;
            ++missingColors;
        }
        if (missingColors > 0) {
            msg = String.valueOf(msg) + missingColors + " colors, ";
        }
        for (Map.Entry<String, Item> entry2 : uploadItem.entrySet()) {
            if (entry2.getValue().getId() > 0) continue;
            ++missingItems;
        }
        if (missingItems > 0) {
            msg = String.valueOf(msg) + missingItems + " items, ";
        }
        for (Map.Entry<String, Size> entry3 : uploadSize.entrySet()) {
            if (entry3.getValue().getId() > 0) continue;
            ++missingSizes;
        }
        if (missingSizes > 0) {
            msg = String.valueOf(msg) + missingSizes + " sizes, ";
        }
        for (Map.Entry<String, Upc> entry4 : uploadUpc.entrySet()) {
            if (entry4.getValue().getId() > 0) continue;
            ++missindUpcs;
        }
        if (missindUpcs > 0) {
            msg = String.valueOf(msg) + missindUpcs + " upc ";
        }
        StringBuilder b = new StringBuilder(msg);
        try {
            b.replace(msg.lastIndexOf(","), msg.lastIndexOf(",") + 1, "");
        }
        catch (Exception var15_15) {
            // empty catch block
        }
        msg = b.toString();
        msg = String.valueOf(msg) + "missing, would you like to add them before?";
        return msg;
    }

    private String getStringValue(Cell cell) throws Exception {
        String val = "";
        if (cell.getCellType() == 0) {
            DecimalFormat decimalFormat = new DecimalFormat("#");
            val = decimalFormat.format(cell.getNumericCellValue());
        } else if (cell.getCellType() == 1) {
            val = cell.getStringCellValue();
        }
        return val;
    }

    private int getIntValue(Cell cell) throws Exception {
        int val = 0;
        if (cell.getCellType() == 0) {
            val = Integer.parseInt(this.getStringValue(cell));
        } else if (cell.getCellType() == 1) {
            val = Integer.parseInt(cell.getStringCellValue());
        }
        return val;
    }

    public class PODetailData {
        private Upc upc;
        private int qty;
        private int itemsPerCarton;
        private boolean preticketed;

        public PODetailData(Upc newUpc, int qty, int itemsPerCarton, boolean preticketed) {
            this.upc = null;
            this.qty = 0;
            this.itemsPerCarton = 0;
            this.preticketed = false;
            this.upc = newUpc;
            this.qty = qty;
            this.itemsPerCarton = itemsPerCarton;
            this.preticketed = preticketed;
        }

        public Upc getUpc() {
            return this.upc;
        }

        public int getQty() {
            return this.qty;
        }

        public int getItemsPerCarton() {
            return this.itemsPerCarton;
        }

        public boolean isPreticketed() {
            return this.preticketed;
        }
    }

}

