/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  com.uslc.po.jpa.entity.Color
 *  com.uslc.po.jpa.entity.Item
 *  com.uslc.po.jpa.entity.Size
 *  com.uslc.po.jpa.entity.Upc
 *  com.uslc.po.jpa.logic.UpcRepo
 *  com.uslc.po.jpa.util.Constants
 *  org.apache.log4j.Logger
 *  org.apache.log4j.PropertyConfigurator
 *  org.eclipse.swt.events.ModifyEvent
 *  org.eclipse.swt.events.ModifyListener
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
 *  org.eclipse.swt.widgets.Combo
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.MessageBox
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Text
 */
package com.uslc.po.gui.master;

import com.uslc.po.gui.master.MasterCenterComposite;
import com.uslc.po.gui.master.MasterRightComposite;
import com.uslc.po.gui.master.NewPurchaseOrderComposite;
import com.uslc.po.gui.master.POMaster;
import com.uslc.po.gui.util.ImageUtils;
import com.uslc.po.gui.util.Master;
import com.uslc.po.jpa.entity.Color;
import com.uslc.po.jpa.entity.Item;
import com.uslc.po.jpa.entity.Size;
import com.uslc.po.jpa.entity.Upc;
import com.uslc.po.jpa.logic.UpcRepo;
import com.uslc.po.jpa.util.Constants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
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
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class NewPurchaseOrderDetailComposite
extends Composite {
    private MasterRightComposite parent = null;
    private Label titleLbl = null;
    private Label sizeLbl = null;
    private Combo sizeCbx = null;
    private Label colorLbl = null;
    private Combo colorCbx = null;
    private Label upcLbl = null;
    private Combo upcCbx = null;
    private Label imageLbl = null;
    private Label quantityLbl = null;
    private Text quantityTxt = null;
    private Label itemsByCartonLbl = null;
    private Text itemsByCartonTxt = null;
    private Label cartonsLbl = null;
    private Text cartonTxt = null;
    private Label preticketedLbl = null;
    private Button[] preticketedBtn = null;
    private Label createdLbl = null;
    private Label timestampLbl = null;
    private Button addBtn = null;
    private List<Upc> upcList = null;
    private GridData gdHorizontalDouble = null;
    private GridData gdLabel = null;
    private GridData gdText = null;
    private Font defaultFont = null;
    private Logger log = null;

    public NewPurchaseOrderDetailComposite(MasterRightComposite composite) {
        super((Composite)composite.getMaster().getHiddenShell(), 0);
        this.parent = composite;
        this.initComposite();
    }

    private void initComposite() {
        GridData data = new GridData(68);
        data.grabExcessVerticalSpace = true;
        data.widthHint = 185;
        data.heightHint = 550;
        this.setLayoutData((Object)data);
        this.layout();
        this.getParent().layout();
        this.getParent().getMaster().getShell().layout();
        GridLayout layout = new GridLayout(2, false);
        this.setLayout((Layout)layout);
        this.getTitleLbl();
        this.getSizeLbl();
        this.getSizeCbx();
        this.getColorLbl();
        this.getColorCbx();
        this.getUpcLbl();
        this.getUpcCbx();
        this.getImageLbl();
        this.getQuantityLbl();
        this.getQuantityTxt();
        this.getItemsByCartonLbl();
        this.getItemsByCartonTxt();
        this.getCartonsLbl();
        this.getCartonTxt();
        this.getPreticketedLbl();
        this.getPreticketedBtn();
        this.getCreatedLbl();
        this.getTimestampLbl();
        this.getAddBtn();
        this.loadVaues();
    }

    public Label getTitleLbl() {
        if (this.titleLbl == null) {
            this.titleLbl = new Label((Composite)this, 0);
            this.titleLbl.setText("add order detail");
            this.titleLbl.setAlignment(131072);
            this.titleLbl.setLayoutData((Object)this.getGdHorizontalDouble());
            this.titleLbl.setFont(this.getDefaultFont());
            Label horizontalLine = new Label((Composite)this, 258);
            horizontalLine.setLayoutData((Object)this.getGdHorizontalDouble());
        }
        return this.titleLbl;
    }

    public Label getSizeLbl() {
        if (this.sizeLbl == null) {
            this.sizeLbl = new Label((Composite)this, 0);
            this.sizeLbl.setText("size:");
            this.sizeLbl.setAlignment(131072);
            this.sizeLbl.setFont(this.getDefaultFont());
            this.sizeLbl.setLayoutData((Object)this.getGdLabel());
        }
        return this.sizeLbl;
    }

    public Combo getSizeCbx() {
        if (this.sizeCbx == null) {
            this.sizeCbx = new Combo((Composite)this, 8);
            this.sizeCbx.setLayoutData((Object)new GridData(768));
            this.sizeCbx.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent arg0) {
                    NewPurchaseOrderDetailComposite.this.filterUpcs();
                }
            });
            this.sizeCbx.setLayoutData((Object)this.getGdText());
            this.sizeCbx.setFont(this.getDefaultFont());
        }
        return this.sizeCbx;
    }

    private void filterUpcs() {
        Color color = null;
        Size size = null;
        try {
            color = (Color)this.getColorCbx().getData(this.getColorCbx().getItem(this.getColorCbx().getSelectionIndex()));
        }
        catch (Exception var3_3) {
            // empty catch block
        }
        try {
            size = (Size)this.getSizeCbx().getData(this.getSizeCbx().getItem(this.getSizeCbx().getSelectionIndex()));
        }
        catch (Exception var3_4) {
            // empty catch block
        }
        ArrayList<Upc> tmpList = new ArrayList<Upc>();
        for (Upc upc : this.getUpcList()) {
            if (color != null && size != null) {
                if (upc.getColor().getId() == color.getId() && upc.getSize().getId() == size.getId()) {
                    tmpList.add(upc);
                }
            } else if (color != null) {
                if (upc.getColor().getId() == color.getId()) {
                    tmpList.add(upc);
                }
            } else if (size != null && upc.getSize().getId() == size.getId()) {
                tmpList.add(upc);
            }
            this.getUpcCbx().removeAll();
            if (tmpList.size() <= 0) continue;
            for (Upc u : tmpList) {
                this.getUpcCbx().add(u.getUpcCode());
                this.getUpcCbx().setData(u.getUpcCode(), (Object)u);
            }
            this.getUpcCbx().select(-1);
        }
    }

    public Label getColorLbl() {
        if (this.colorLbl == null) {
            this.colorLbl = new Label((Composite)this, 0);
            this.colorLbl.setText("color:");
            this.colorLbl.setAlignment(131072);
            this.colorLbl.setLayoutData((Object)this.getGdLabel());
            this.colorLbl.setFont(this.getDefaultFont());
        }
        return this.colorLbl;
    }

    public Combo getColorCbx() {
        if (this.colorCbx == null) {
            this.colorCbx = new Combo((Composite)this, 8);
            this.colorCbx.setLayoutData((Object)new GridData(768));
            this.colorCbx.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent arg0) {
                    NewPurchaseOrderDetailComposite.this.filterUpcs();
                }
            });
            this.colorCbx.setLayoutData((Object)this.getGdText());
            this.colorCbx.setFont(this.getDefaultFont());
        }
        return this.colorCbx;
    }

    public Label getUpcLbl() {
        if (this.upcLbl == null) {
            this.upcLbl = new Label((Composite)this, 0);
            this.upcLbl.setText("upc:");
            this.upcLbl.setLayoutData((Object)this.getGdLabel());
            this.upcLbl.setFont(this.getDefaultFont());
        }
        return this.upcLbl;
    }

    public Combo getUpcCbx() {
        if (this.upcCbx == null) {
            this.upcCbx = new Combo((Composite)this, 8);
            this.upcCbx.setLayoutData((Object)this.getGdHorizontalDouble());
            this.upcCbx.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent arg0) {
                    NewPurchaseOrderDetailComposite.this.updateUpcImage();
                }
            });
            this.upcCbx.setFont(this.getDefaultFont());
        }
        return this.upcCbx;
    }

    public Label getImageLbl() {
        if (this.imageLbl == null) {
            this.imageLbl = new Label((Composite)this, 5);
            GridData gd = new GridData(150, 60);
            gd.horizontalSpan = 2;
            gd.horizontalAlignment = 16777216;
            this.imageLbl.setLayoutData((Object)gd);
            Label horizontalLine = new Label((Composite)this, 258);
            horizontalLine.setLayoutData((Object)this.getGdHorizontalDouble());
        }
        return this.imageLbl;
    }

    public Label getQuantityLbl() {
        if (this.quantityLbl == null) {
            this.quantityLbl = new Label((Composite)this, 0);
            this.quantityLbl.setText("qty:");
            this.quantityLbl.setAlignment(131072);
            this.quantityLbl.setLayoutData((Object)this.getGdLabel());
            this.quantityLbl.setFont(this.getDefaultFont());
        }
        return this.quantityLbl;
    }

    public Text getQuantityTxt() {
        if (this.quantityTxt == null) {
            this.quantityTxt = new Text((Composite)this, 0);
            this.quantityTxt.setLayoutData((Object)this.getGdText());
            this.quantityTxt.addModifyListener(new ModifyListener(){

                public void modifyText(ModifyEvent arg0) {
                    NewPurchaseOrderDetailComposite.this.updateCartonTotal();
                }
            });
            this.quantityTxt.setFont(this.getDefaultFont());
        }
        return this.quantityTxt;
    }

    public Label getItemsByCartonLbl() {
        if (this.itemsByCartonLbl == null) {
            this.itemsByCartonLbl = new Label((Composite)this, 0);
            this.itemsByCartonLbl.setText("item/c:");
            this.itemsByCartonLbl.setAlignment(131072);
            this.itemsByCartonLbl.setLayoutData((Object)this.getGdLabel());
            this.itemsByCartonLbl.setFont(this.getDefaultFont());
        }
        return this.itemsByCartonLbl;
    }

    public Text getItemsByCartonTxt() {
        if (this.itemsByCartonTxt == null) {
            this.itemsByCartonTxt = new Text((Composite)this, 0);
            this.itemsByCartonTxt.setLayoutData((Object)this.getGdText());
            this.itemsByCartonTxt.addModifyListener(new ModifyListener(){

                public void modifyText(ModifyEvent arg0) {
                    NewPurchaseOrderDetailComposite.this.updateCartonTotal();
                }
            });
            this.itemsByCartonTxt.setFont(this.getDefaultFont());
        }
        return this.itemsByCartonTxt;
    }

    public Label getCartonsLbl() {
        if (this.cartonsLbl == null) {
            this.cartonsLbl = new Label((Composite)this, 0);
            this.cartonsLbl.setText("cartons:");
            this.cartonsLbl.setAlignment(131072);
            this.cartonsLbl.setLayoutData((Object)this.getGdLabel());
            this.cartonsLbl.setFont(this.getDefaultFont());
        }
        return this.cartonsLbl;
    }

    public Text getCartonTxt() {
        if (this.cartonTxt == null) {
            this.cartonTxt = new Text((Composite)this, 8);
            this.cartonTxt.setLayoutData((Object)this.getGdText());
            try {
                this.cartonTxt.setText(String.valueOf(this.getParent().getMaster().getMaster().getDefaultQtyPerCarton()));
            }
            catch (IOException e) {
                this.getItemsByCartonTxt().setText("0");
                e.printStackTrace();
            }
            this.cartonTxt.setFont(this.getDefaultFont());
        }
        return this.cartonTxt;
    }

    public Label getPreticketedLbl() {
        if (this.preticketedLbl == null) {
            this.preticketedLbl = new Label((Composite)this, 0);
            this.preticketedLbl.setText("preticketed:");
            this.preticketedLbl.setLayoutData((Object)this.getGdHorizontalDouble());
            this.preticketedLbl.setFont(this.getDefaultFont());
        }
        return this.preticketedLbl;
    }

    public Button[] getPreticketedBtn() {
        if (this.preticketedBtn == null) {
            this.preticketedBtn = new Button[2];
            GridData gd = new GridData(50, 23);
            this.preticketedBtn[0] = new Button((Composite)this, 16777232);
            this.preticketedBtn[0].setSelection(false);
            this.preticketedBtn[0].setText("Y");
            this.preticketedBtn[0].setLayoutData((Object)gd);
            this.preticketedBtn[0].setFont(this.getDefaultFont());
            this.preticketedBtn[1] = new Button((Composite)this, 16777232);
            this.preticketedBtn[1].setSelection(false);
            this.preticketedBtn[1].setText("N");
            this.preticketedBtn[1].setLayoutData((Object)gd);
            this.preticketedBtn[1].setFont(this.getDefaultFont());
        }
        return this.preticketedBtn;
    }

    public Label getCreatedLbl() {
        if (this.createdLbl == null) {
            this.createdLbl = new Label((Composite)this, 0);
            this.createdLbl.setText("created:");
            this.createdLbl.setLayoutData((Object)this.getGdHorizontalDouble());
            this.createdLbl.setFont(this.getDefaultFont());
        }
        return this.createdLbl;
    }

    public Label getTimestampLbl() {
        if (this.timestampLbl == null) {
            this.timestampLbl = new Label((Composite)this, 0);
            this.timestampLbl.setText("2014-01-15 15:09:23");
            this.timestampLbl.setLayoutData((Object)this.getGdHorizontalDouble());
            this.timestampLbl.setAlignment(131072);
            this.timestampLbl.setFont(this.getDefaultFont());
            Label horizontalLine = new Label((Composite)this, 258);
            horizontalLine.setLayoutData((Object)this.getGdHorizontalDouble());
        }
        return this.timestampLbl;
    }

    public Button getAddBtn() {
        if (this.addBtn == null) {
            this.addBtn = new Button((Composite)this, 8);
            this.addBtn.setText("add detail");
            this.addBtn.setAlignment(16777216);
            GridData gd = new GridData(64);
            gd.grabExcessHorizontalSpace = true;
            gd.heightHint = 20;
            gd.widthHint = 100;
            gd.horizontalSpan = 2;
            this.addBtn.setLayoutData((Object)gd);
            this.addBtn.setFont(this.getDefaultFont());
            this.addBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent arg0) {
                    NewPurchaseOrderDetailComposite.this.addPoDetail();
                }
            });
            Label horizontalLine = new Label((Composite)this, 258);
            horizontalLine.setLayoutData((Object)this.getGdHorizontalDouble());
        }
        return this.addBtn;
    }

    public GridData getGdHorizontalDouble() {
        if (this.gdHorizontalDouble == null) {
            this.gdHorizontalDouble = new GridData(4, 4, true, false);
            this.gdHorizontalDouble.horizontalSpan = 2;
        }
        return this.gdHorizontalDouble;
    }

    public GridData getGdLabel() {
        if (this.gdLabel == null) {
            this.gdLabel = new GridData();
            this.gdLabel.widthHint = 65;
        }
        return this.gdLabel;
    }

    public GridData getGdText() {
        if (this.gdText == null) {
            this.gdText = new GridData();
            this.gdText.widthHint = 100;
            this.gdText.heightHint = 23;
        }
        return this.gdText;
    }

    public MasterRightComposite getParent() {
        return this.parent;
    }

    private void clean() {
        this.getUpcCbx().select(-1);
        this.getQuantityTxt().setText("0");
        try {
            this.getItemsByCartonTxt().setText(String.valueOf(this.getParent().getMaster().getMaster().getDefaultQtyPerCarton()));
        }
        catch (IOException e) {
            this.getItemsByCartonTxt().setText("0");
            e.printStackTrace();
        }
        this.getPreticketedBtn()[0].setSelection(false);
        this.getPreticketedBtn()[1].setSelection(false);
    }

    public void hide() {
        this.clean();
        this.setParent((Composite)this.getParent().getMaster().getHiddenShell());
        this.setVisible(false);
    }

    public void loadVaues() {
        this.getUpcCbx().removeAll();
        this.getColorCbx().removeAll();
        this.getSizeCbx().removeAll();
        this.upcList = null;
        if (this.getSelectedItem() != null) {
            this.fillUpcCombo();
            this.fillColorCombo();
            this.fillSizeCombo();
        }
    }

    private void fillUpcCombo() {
        if (this.getUpcList() != null) {
            for (Upc upc : this.getUpcList()) {
                this.getUpcCbx().add(upc.getUpcCode());
                this.getUpcCbx().setData(upc.getUpcCode(), (Object)upc);
            }
            this.getLog().info((Object)("fillUpcCombo.getUpcCbx().getItemCount(): " + this.getUpcCbx().getItemCount()));
            this.getUpcCbx().select(-1);
        }
    }

    private void fillColorCombo() {
        if (this.getUpcList() != null) {
            HashMap<String, Color> colors = new HashMap<String, Color>();
            for (Upc upc : this.getUpcList()) {
                colors.put(upc.getColor().getName(), upc.getColor());
            }
            for (Map.Entry entry : colors.entrySet()) {
                this.getColorCbx().add((String)entry.getKey());
                this.getColorCbx().setData((String)entry.getKey(), entry.getValue());
            }
        }
    }

    private void fillSizeCombo() {
        if (this.getUpcList() != null) {
            HashMap<String, Size> sizes = new HashMap<String, Size>();
            for (Upc upc : this.getUpcList()) {
                if (this.getParent().getMaster().getCenterComposite().getNewPurchaseOrder().getOrderNumberTxt().getText().endsWith("11")) {
                    sizes.put(String.valueOf(upc.getSize().getWaist()), upc.getSize());
                    continue;
                }
                sizes.put(String.valueOf(upc.getSize().getWaist()) + "x" + upc.getSize().getInseam(), upc.getSize());
            }
            for (Map.Entry entry : sizes.entrySet()) {
                this.getSizeCbx().add((String)entry.getKey());
                this.getSizeCbx().setData((String)entry.getKey(), entry.getValue());
            }
        }
    }

    private void addPoDetail() {
        Upc upc = null;
        int qty = 0;
        int itemsPerCarton = 0;
        try {
            upc = (Upc)this.getUpcCbx().getData(this.getUpcCbx().getItem(this.getUpcCbx().getSelectionIndex()));
            qty = Integer.parseInt(this.getQuantityTxt().getText());
            itemsPerCarton = Integer.parseInt(this.getItemsByCartonTxt().getText());
            Combo combo = this.getParent().getMaster().getCenterComposite().getNewPurchaseOrder().getItemsCbx();
        }
        catch (Exception e) {
            MessageBox box = new MessageBox(this.getShell(), 1);
            box.setText(Constants.MESSAGE_BOX_DIAG_TITLE.toString());
            box.setMessage("Check the detail values.");
            box.open();
            return;
        }
        if (qty > 0 && itemsPerCarton > 0) {
            boolean preticketed = false;
            if (this.getPreticketedBtn()[0].getSelection() == this.getPreticketedBtn()[1].getSelection()) {
                MessageBox box = new MessageBox(this.getShell(), 1);
                box.setText(Constants.MESSAGE_BOX_DIAG_TITLE.toString());
                box.setMessage("Please select if the items are preticketed or not.");
                box.open();
                return;
            }
            preticketed = this.getPreticketedBtn()[0].getSelection();
            NewPurchaseOrderComposite newPurchaseOrderComposite = this.getParent().getMaster().getCenterComposite().getNewPurchaseOrder();
            newPurchaseOrderComposite.getClass();
            NewPurchaseOrderComposite.PODetailData poData = newPurchaseOrderComposite.new NewPurchaseOrderComposite.PODetailData(upc, qty, itemsPerCarton, preticketed);
            if (this.getParent().getMaster().getCenterComposite().getNewPurchaseOrder().addPoDetail(poData)) {
                this.clean();
            } else {
                MessageBox box = new MessageBox(this.getShell(), 2);
                box.setText(Constants.MESSAGE_BOX_DIAG_TITLE.toString());
                box.setMessage("There was a problem while adding the purchase order detail");
                box.open();
            }
        } else {
            MessageBox box = new MessageBox(this.getShell(), 2);
            box.setText(Constants.MESSAGE_BOX_DIAG_TITLE.toString());
            box.setMessage("Please check the correct amounts.");
            box.open();
        }
    }

    private void updateCartonTotal() {
        int totalItems = 0;
        int itemsPerCarton = 0;
        int cartons = 0;
        try {
            totalItems = (int)Double.parseDouble(this.getQuantityTxt().getText());
        }
        catch (Exception var4_4) {
            // empty catch block
        }
        try {
            itemsPerCarton = (int)Double.parseDouble(this.getItemsByCartonTxt().getText());
        }
        catch (Exception var4_5) {
            // empty catch block
        }
        if (itemsPerCarton > 0) {
            try {
                cartons = (int)Math.ceil(new Double(totalItems) / new Double(itemsPerCarton));
            }
            catch (Exception var4_6) {
                // empty catch block
            }
        }
        this.getCartonTxt().setText(String.valueOf(cartons));
    }

    private void updateUpcImage() {
        Upc upc = (Upc)this.getUpcCbx().getData(this.getUpcCbx().getItem(this.getUpcCbx().getSelectionIndex()));
        this.getImageLbl().setImage(null);
        if (upc != null) {
            Image ucpImage = ImageUtils.getBarcodeImage(this.getDisplay(), upc.getUpcCode());
            this.getImageLbl().setImage(ucpImage);
        }
    }

    private Logger getLog() {
        if (this.log == null) {
            this.log = Logger.getLogger((Class)NewPurchaseOrderDetailComposite.class);
            PropertyConfigurator.configure((String)"log4j.properties");
        }
        return this.log;
    }

    private Item getSelectedItem() {
        Item item = null;
        Combo cbx = this.getParent().getMaster().getCenterComposite().getNewPurchaseOrder().getItemsCbx();
        try {
            item = (Item)cbx.getData(cbx.getItem(cbx.getSelectionIndex()));
        }
        catch (Exception e) {
            this.getLog().error((Object)e);
        }
        return item;
    }

    public List<Upc> getUpcList() {
        if (this.upcList == null) {
            this.upcList = this.getParent().getMaster().getCenterComposite().getNewPurchaseOrder().getOrderNumberTxt().getText().endsWith("11") ? UpcRepo.findByItemAndInseam((Item)this.getSelectedItem(), (int)36) : UpcRepo.findByItem((Item)this.getSelectedItem());
            this.getLog().info((Object)("getUpcList.upcList.size(): " + this.upcList.size()));
        }
        return this.upcList;
    }

    public Font getDefaultFont() {
        if (this.defaultFont == null) {
            try {
                FontData[] fds;
                FontData[] arrfontData = fds = this.getParent().getMaster().getMaster().getDefaultMasterFont().getFontData();
                int n = arrfontData.length;
                int n2 = 0;
                while (n2 < n) {
                    FontData fd = arrfontData[n2];
                    this.defaultFont = new Font((Device)this.getDisplay(), fd.getName(), 7, fd.getStyle());
                    ++n2;
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this.defaultFont;
    }

}

