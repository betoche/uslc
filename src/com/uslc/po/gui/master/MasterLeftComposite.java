/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  com.ibm.icu.text.SimpleDateFormat
 *  com.uslc.po.jpa.entity.PurchaseOrder
 *  org.apache.log4j.Logger
 *  org.apache.log4j.PropertyConfigurator
 *  org.eclipse.swt.events.FocusAdapter
 *  org.eclipse.swt.events.FocusEvent
 *  org.eclipse.swt.events.FocusListener
 *  org.eclipse.swt.events.MouseAdapter
 *  org.eclipse.swt.events.MouseEvent
 *  org.eclipse.swt.events.MouseListener
 *  org.eclipse.swt.events.SelectionAdapter
 *  org.eclipse.swt.events.SelectionEvent
 *  org.eclipse.swt.events.SelectionListener
 *  org.eclipse.swt.graphics.Device
 *  org.eclipse.swt.graphics.Font
 *  org.eclipse.swt.graphics.FontData
 *  org.eclipse.swt.graphics.Image
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Button
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

import com.ibm.icu.text.SimpleDateFormat;
import com.uslc.po.gui.master.MasterCenterComposite;
import com.uslc.po.gui.master.MasterTopComposite;
import com.uslc.po.gui.master.POMaster;
import com.uslc.po.gui.master.PackingDetailComposite;
import com.uslc.po.gui.master.PurchaseOrderDetailComposite;
import com.uslc.po.gui.util.PurchaseOrder;
import java.util.Date;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class MasterLeftComposite
extends Composite {
    private POMaster master = null;
    private Label purchaseOrderLbl = null;
    private Table purchaseOrderTbl = null;
    private Button showPackingDetails = null;
    private Group purchaseOrderInfoGrp = null;
    private Label refNum = null;
    private Label deptNum = null;
    private Label items = null;
    private Label cartons = null;
    private Label from = null;
    private Label to = null;
    private Label created = null;
    private Font infoFont = null;
    private GridData gdValues = null;
    private Font addressesFont = null;
    private Logger log = null;
    private SimpleDateFormat sdf = null;

    public MasterLeftComposite(POMaster master) {
        super((Composite)master.getShell(), 2048);
        this.master = master;
        this.initComposite();
    }

    private void initComposite() {
        GridData data = new GridData(4, 4, false, true);
        data.widthHint = 200;
        this.setLayoutData((Object)data);
        GridLayout layout = new GridLayout();
        layout.marginTop = 20;
        this.setLayout((Layout)layout);
        this.getPurchaseOrderLbl();
        this.getPurchaseOrderTbl();
        this.getShowPackingDetails();
        this.getPurchaseOrderInfoGrp();
    }

    public Label getPurchaseOrderLbl() {
        if (this.purchaseOrderLbl == null) {
            this.purchaseOrderLbl = new Label((Composite)this, 0);
            this.purchaseOrderLbl.setText("purchase orders:");
        }
        return this.purchaseOrderLbl;
    }

    public Table getPurchaseOrderTbl() {
        if (this.purchaseOrderTbl == null) {
            this.purchaseOrderTbl = new Table((Composite)this, 4);
            GridData data = new GridData(4, 4, true, false);
            data.heightHint = 200;
            this.purchaseOrderTbl.setLayoutData((Object)data);
            TableColumn po = new TableColumn(this.purchaseOrderTbl, 0);
            TableColumn status = new TableColumn(this.purchaseOrderTbl, 0);
            po.setText("po");
            status.setText("status");
            po.setWidth(100);
            status.setWidth(90);
            this.purchaseOrderTbl.setHeaderVisible(true);
            this.purchaseOrderTbl.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent arg0) {
                    MasterLeftComposite.this.showPoInfo();
                }
            });
            this.purchaseOrderTbl.addFocusListener((FocusListener)new FocusAdapter(){

                public void focusLost(FocusEvent arg0) {
                    TableItem[] items = MasterLeftComposite.this.purchaseOrderTbl.getSelection();
                    if (items != null) {
                        PurchaseOrder po = null;
                        TableItem[] arrtableItem = items;
                        int n = arrtableItem.length;
                        int n2 = 0;
                        while (n2 < n) {
                            TableItem item = arrtableItem[n2];
                            po = (PurchaseOrder)item.getData();
                            ++n2;
                        }
                        if (po != null) {
                            MasterLeftComposite.this.getMaster().getTopComposite().getDeletePo().setEnabled(true);
                        } else {
                            MasterLeftComposite.this.getMaster().getTopComposite().getDeletePo().setEnabled(false);
                        }
                    } else {
                        MasterLeftComposite.this.getMaster().getTopComposite().getDeletePo().setEnabled(false);
                    }
                }
            });
            this.purchaseOrderTbl.addMouseListener((MouseListener)new MouseAdapter(){

                public void mouseDoubleClick(MouseEvent arg0) {
                    MasterLeftComposite.this.showPoDetails();
                }
            });
            this.fillPo();
        }
        return this.purchaseOrderTbl;
    }

    public void showPoDetails() {
        TableItem[] items = this.getPurchaseOrderTbl().getSelection();
        PurchaseOrder po = null;
        TableItem[] arrtableItem = items;
        int n = arrtableItem.length;
        int n2 = 0;
        while (n2 < n) {
            TableItem ti = arrtableItem[n2];
            po = (PurchaseOrder)ti.getData();
            ++n2;
        }
        if (po != null) {
            this.getMaster().getCenterComposite().showComposite(this.getMaster().getCenterComposite().getPurchaseDetailViewer());
            this.getMaster().getCenterComposite().getPurchaseDetailViewer().displayPurchaseDetails(po);
        }
    }

    public Button getShowPackingDetails() {
        if (this.showPackingDetails == null) {
            this.showPackingDetails = new Button((Composite)this, 8);
            this.showPackingDetails.setText("packing");
            GridData rightAlign = new GridData(128);
            rightAlign.grabExcessHorizontalSpace = true;
            this.showPackingDetails.setLayoutData((Object)rightAlign);
            this.showPackingDetails.setImage(new Image((Device)this.getDisplay(), "images/box.png"));
            this.showPackingDetails.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent arg0) {
                    MasterLeftComposite.this.showPackingDetail();
                }
            });
            GridData gdHorizontal = new GridData(768);
            gdHorizontal.grabExcessHorizontalSpace = true;
            gdHorizontal.heightHint = 20;
            Label horizontaLabel = new Label((Composite)this, 258);
            horizontaLabel.setLayoutData((Object)gdHorizontal);
        }
        return this.showPackingDetails;
    }

    public Group getPurchaseOrderInfoGrp() {
        if (this.purchaseOrderInfoGrp == null) {
            this.purchaseOrderInfoGrp = new Group((Composite)this, 16);
            this.purchaseOrderInfoGrp.setText("info");
            GridData data = new GridData(4, 4, true, false);
            data.heightHint = 240;
            this.purchaseOrderInfoGrp.setLayoutData((Object)data);
            GridLayout layout = new GridLayout();
            layout.marginLeft = 0;
            layout.marginRight = 0;
            layout.marginWidth = 0;
            layout.horizontalSpacing = 0;
            layout.makeColumnsEqualWidth = true;
            layout.numColumns = 2;
            this.purchaseOrderInfoGrp.setLayout((Layout)layout);
            GridData gd = new GridData();
            gd.widthHint = 60;
            Label d = new Label((Composite)this.purchaseOrderInfoGrp, 0);
            d.setText("dept#:");
            d.setLayoutData((Object)gd);
            d.setAlignment(131072);
            d.setFont(this.getInfoFont());
            this.getDeptNum().setFont(this.getInfoFont());
            Label i = new Label((Composite)this.purchaseOrderInfoGrp, 0);
            i.setText("items:");
            i.setLayoutData((Object)gd);
            i.setAlignment(131072);
            i.setFont(this.getInfoFont());
            this.getItems().setFont(this.getInfoFont());
            Label c = new Label((Composite)this.purchaseOrderInfoGrp, 0);
            c.setText("cartons:");
            c.setLayoutData((Object)gd);
            c.setAlignment(131072);
            c.setFont(this.getInfoFont());
            this.getCartons().setFont(this.getInfoFont());
            Label f = new Label((Composite)this.purchaseOrderInfoGrp, 0);
            f.setText("from:");
            f.setLayoutData((Object)gd);
            f.setAlignment(131072);
            f.setFont(this.getInfoFont());
            this.getFrom().setFont(this.getAddressesFont());
            Label t = new Label((Composite)this.purchaseOrderInfoGrp, 0);
            t.setText("to:");
            t.setLayoutData((Object)gd);
            t.setAlignment(131072);
            t.setFont(this.getInfoFont());
            this.getTo().setFont(this.getAddressesFont());
            Label cr = new Label((Composite)this.purchaseOrderInfoGrp, 0);
            cr.setText("created:");
            cr.setLayoutData((Object)gd);
            cr.setAlignment(131072);
            cr.setFont(this.getInfoFont());
            this.getCreated().setFont(this.getInfoFont());
        }
        return this.purchaseOrderInfoGrp;
    }

    public Label getRefNum() {
        if (this.refNum == null) {
            this.refNum = new Label((Composite)this.purchaseOrderInfoGrp, 0);
            this.refNum.setText("po#");
            this.refNum.setAlignment(131072);
            GridData refNumGd = new GridData(4, 2, true, false);
            refNumGd.horizontalSpan = 2;
            this.refNum.setLayoutData((Object)refNumGd);
        }
        return this.refNum;
    }

    public Label getDeptNum() {
        if (this.deptNum == null) {
            this.deptNum = new Label((Composite)this.purchaseOrderInfoGrp, 0);
            this.deptNum.setText("");
            this.deptNum.setAlignment(16384);
            this.deptNum.setLayoutData((Object)this.getGdValues());
        }
        return this.deptNum;
    }

    public Label getItems() {
        if (this.items == null) {
            this.items = new Label((Composite)this.purchaseOrderInfoGrp, 0);
            this.items.setText("");
            this.items.setAlignment(16384);
            this.items.setLayoutData((Object)this.getGdValues());
        }
        return this.items;
    }

    public Label getCartons() {
        if (this.cartons == null) {
            this.cartons = new Label((Composite)this.purchaseOrderInfoGrp, 0);
            this.cartons.setText("");
            this.cartons.setAlignment(16384);
            this.cartons.setLayoutData((Object)this.getGdValues());
        }
        return this.cartons;
    }

    public Label getFrom() {
        if (this.from == null) {
            this.from = new Label((Composite)this.purchaseOrderInfoGrp, 64);
            this.from.setText("");
            this.from.setAlignment(131072);
            GridData gd = new GridData();
            gd.horizontalAlignment = 4;
            gd.grabExcessHorizontalSpace = true;
            gd.horizontalSpan = 2;
            gd.heightHint = 50;
            this.from.setLayoutData((Object)gd);
            this.from.setFont(this.getAddressesFont());
        }
        return this.from;
    }

    public Label getTo() {
        if (this.to == null) {
            this.to = new Label((Composite)this.purchaseOrderInfoGrp, 64);
            this.to.setText("");
            this.to.setAlignment(131072);
            GridData gd = new GridData();
            gd.horizontalAlignment = 4;
            gd.grabExcessHorizontalSpace = true;
            gd.horizontalSpan = 2;
            gd.heightHint = 40;
            this.to.setLayoutData((Object)gd);
            this.to.setFont(this.getAddressesFont());
        }
        return this.to;
    }

    public Label getCreated() {
        if (this.created == null) {
            this.created = new Label((Composite)this.purchaseOrderInfoGrp, 0);
            this.created.setText("");
            this.created.setAlignment(16384);
            this.created.setLayoutData((Object)this.getGdValues());
        }
        return this.created;
    }

    public GridData getGdValues() {
        if (this.gdValues == null) {
            this.gdValues = new GridData(768);
            this.gdValues.grabExcessHorizontalSpace = true;
        }
        return this.gdValues;
    }

    public Font getInfoFont() {
        if (this.infoFont == null) {
            this.infoFont = new Font((Device)this.getDisplay(), "Segoe UI", 8, 0);
        }
        return this.infoFont;
    }

    public Font getAddressesFont() {
        if (this.addressesFont == null) {
            FontData[] fd;
            FontData[] arrfontData = fd = this.getDisplay().getSystemFont().getFontData();
            int n = arrfontData.length;
            int n2 = 0;
            while (n2 < n) {
                FontData f = arrfontData[n2];
                this.getLog().info((Object)("f[" + f.getName() + "," + f.getStyle() + "," + f.getHeight() + "]"));
                this.addressesFont = new Font((Device)this.getDisplay(), f.getName(), 6, f.getStyle());
                ++n2;
            }
        }
        return this.addressesFont;
    }

    public void fillPo() {
        PurchaseOrder[] pos = PurchaseOrder.getPurchaseOrders();
        this.getPurchaseOrderTbl().removeAll();
        try {
            Font f = new Font((Device)this.getDisplay(), "Segoe UI", 8, 0);
            PurchaseOrder[] arrpurchaseOrder = pos;
            int n = arrpurchaseOrder.length;
            int n2 = 0;
            while (n2 < n) {
                PurchaseOrder po = arrpurchaseOrder[n2];
                TableItem ti = new TableItem(this.getPurchaseOrderTbl(), 0);
                ti.setFont(f);
                String[] texts = new String[]{po.getCode(), po.getStatus()};
                ti.setData((Object)po);
                ti.setText(texts);
                ++n2;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showPackingDetail() {
        TableItem[] items = this.getPurchaseOrderTbl().getSelection();
        PurchaseOrder po = null;
        TableItem[] arrtableItem = items;
        int n = arrtableItem.length;
        int n2 = 0;
        while (n2 < n) {
            TableItem ti = arrtableItem[n2];
            po = (PurchaseOrder)ti.getData();
            ++n2;
        }
        if (po != null) {
            this.getMaster().getCenterComposite().showComposite(this.getMaster().getCenterComposite().getPackingDetailViewer());
            this.getMaster().getCenterComposite().getPackingDetailViewer().displayPackingDetails(po);
        }
    }

    public void showPoInfo() {
        TableItem[] items = this.getPurchaseOrderTbl().getSelection();
        PurchaseOrder po = null;
        TableItem[] arrtableItem = items;
        int n = arrtableItem.length;
        int n2 = 0;
        while (n2 < n) {
            TableItem ti = arrtableItem[n2];
            po = (PurchaseOrder)ti.getData();
            ++n2;
        }
        if (po != null) {
            this.getPurchaseOrderInfoGrp().setText("info: " + po.getPo().getReferenceNumber());
            com.uslc.po.jpa.entity.PurchaseOrder p = po.getPo();
            this.getRefNum().setText(p.getReferenceNumber());
            this.getDeptNum().setText(String.valueOf(p.getDepartmentNumber()));
            this.getItems().setText(String.valueOf(p.getTotalItems()));
            this.getCartons().setText(String.valueOf(p.getTotalCartons()));
            this.getFrom().setText(p.getShipFrom());
            this.getTo().setText(p.getShipTo());
            this.getCreated().setText(this.getSdf().format(p.getTimestamp()));
            this.getMaster().getTopComposite().getEditPo().setEnabled(true);
            this.getMaster().getTopComposite().getPrint().setEnabled(true);
            this.getMaster().getTopComposite().getDeletePo().setEnabled(true);
        } else {
            this.getPurchaseOrderInfoGrp().setText("info");
            this.getRefNum().setText("");
            this.getDeptNum().setText("");
            this.getItems().setText("");
            this.getCartons().setText("");
            this.getFrom().setText("");
            this.getTo().setText("");
            this.getCreated().setText("");
            this.getMaster().getTopComposite().getEditPo().setEnabled(false);
            this.getMaster().getTopComposite().getPrint().setEnabled(false);
            this.getMaster().getTopComposite().getDeletePo().setEnabled(false);
        }
    }

    public SimpleDateFormat getSdf() {
        if (this.sdf == null) {
            this.sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        return this.sdf;
    }

    public POMaster getMaster() {
        return this.master;
    }

    private Logger getLog() {
        if (this.log == null) {
            this.log = Logger.getLogger((Class)MasterLeftComposite.class);
            PropertyConfigurator.configure((String)"log4j.properties");
        }
        return this.log;
    }

    public void clean() {
        this.getDeptNum().setText("");
        this.getItems().setText("");
        this.getCartons().setText("");
        this.getFrom().setText("");
        this.getTo().setText("");
        this.getCreated().setText("");
        this.getPurchaseOrderTbl().setSelection(-1);
    }

}

