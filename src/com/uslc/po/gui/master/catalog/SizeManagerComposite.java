/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  com.uslc.po.jpa.entity.Size
 *  com.uslc.po.jpa.logic.SizeRepo
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
import com.uslc.po.jpa.entity.Size;
import com.uslc.po.jpa.logic.SizeRepo;
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

public class SizeManagerComposite
extends Composite {
    private MasterCenterComposite parent = null;
    private Label title = null;
    private Table sizes = null;
    private Label info = null;
    private Label waistLabel = null;
    private Text waistText = null;
    private Label hipLabel = null;
    private Text hipText = null;
    private Label inseamLabel = null;
    private Text inseamText = null;
    private Button action = null;
    private Button cancel = null;
    private GridData labelGd = null;
    private GridData textGd = null;
    private Logger log = null;
    private Size selectedSize = null;
    private boolean editing = false;
    private final String infoAddText = "Info: Add a new Size";

    public SizeManagerComposite(MasterCenterComposite composite) {
        super((Composite)composite.getMaster().getHiddenShell(), 0);
        this.parent = composite;
        this.initComposite();
    }

    private void initComposite() {
        GridLayout layout = new GridLayout(4, false);
        this.setLayout((Layout)layout);
        FormData data = new FormData(400, 300);
        this.setLayoutData((Object)data);
        this.getTitle();
        this.getSizes();
        this.getInfo();
        this.getWaistLabel();
        this.getWaistText();
        this.getHipLabel();
        this.getHipText();
        this.getInseamLabel();
        this.getInseamText();
        this.getAction();
        this.getCancel();
    }

    public MasterCenterComposite getParent() {
        return this.parent;
    }

    public Label getTitle() {
        if (this.title == null) {
            this.title = new Label((Composite)this, 0);
            this.title.setText("Sizes");
            this.title.setAlignment(16384);
            GridData data = new GridData(4, 4, true, false);
            data.horizontalSpan = 4;
            this.title.setLayoutData((Object)data);
            Label horizontalLine = new Label((Composite)this, 258);
            GridData data2 = new GridData(4, 4, true, false);
            data2.horizontalSpan = 4;
            horizontalLine.setLayoutData((Object)data2);
        }
        return this.title;
    }

    public Table getSizes() {
        if (this.sizes == null) {
            this.sizes = new Table((Composite)this, 4);
            TableColumn id = new TableColumn(this.sizes, 0);
            id.setText("id");
            TableColumn size = new TableColumn(this.sizes, 0);
            size.setText("size");
            id.setWidth(30);
            size.setWidth(70);
            this.sizes.setHeaderVisible(true);
            GridData data = new GridData(1040);
            data.verticalSpan = 6;
            this.sizes.setLayoutData((Object)data);
            this.sizes.addMouseListener((MouseListener)new MouseAdapter(){

                public void mouseDoubleClick(MouseEvent arg0) {
                    SizeManagerComposite.this.setEditMode();
                }
            });
            this.loadSizes();
            GridData data1 = new GridData(4, 4, false, false);
            data1.verticalSpan = 6;
            data1.widthHint = 15;
            Label horizontalLine = new Label((Composite)this, 514);
            horizontalLine.setLayoutData((Object)data1);
        }
        return this.sizes;
    }

    private void setEditMode() {
        this.editing = true;
        TableItem[] selection = this.getSizes().getSelection();
        Size size = null;
        if (selection != null) {
            TableItem[] arrtableItem = selection;
            int n = arrtableItem.length;
            int n2 = 0;
            while (n2 < n) {
                TableItem item = arrtableItem[n2];
                size = (Size)item.getData();
                ++n2;
            }
        }
        if (size != null) {
            this.editing = true;
            this.selectedSize = size;
            this.getWaistText().setText(String.valueOf(size.getWaist()));
            this.getHipText().setText(String.valueOf(size.getHip()));
            this.getInseamText().setText(String.valueOf(size.getInseam()));
            this.getInfo().setText("Size[" + size.getId() + "] - UPDATE");
            this.getInfo().setAlignment(131072);
            this.getAction().setText("update");
            this.getAction().setAlignment(131072);
        } else {
            this.editing = false;
            this.selectedSize = null;
        }
    }

    public Label getInfo() {
        if (this.info == null) {
            this.info = new Label((Composite)this, 0);
            this.info.setText("Info: Add a new Size");
            this.info.setLayoutData((Object)MyGridData.getDgHorizontalDoubleSpan());
            Label horizontalLine = new Label((Composite)this, 258);
            horizontalLine.setLayoutData((Object)MyGridData.getDgHorizontalDoubleSpan());
        }
        return this.info;
    }

    public Label getWaistLabel() {
        if (this.waistLabel == null) {
            this.waistLabel = new Label((Composite)this, 0);
            this.waistLabel.setText("waist:");
            this.waistLabel.setAlignment(131072);
            this.waistLabel.setLayoutData((Object)this.getLabelGd());
        }
        return this.waistLabel;
    }

    public Text getWaistText() {
        if (this.waistText == null) {
            this.waistText = new Text((Composite)this, 2048);
            this.waistText.setLayoutData((Object)this.getTextGd());
        }
        return this.waistText;
    }

    public Label getHipLabel() {
        if (this.hipLabel == null) {
            this.hipLabel = new Label((Composite)this, 0);
            this.hipLabel.setText("hip:");
            this.hipLabel.setAlignment(131072);
            this.labelGd = new GridData(100, 23);
            this.hipLabel.setLayoutData((Object)this.labelGd);
        }
        return this.hipLabel;
    }

    public Text getHipText() {
        if (this.hipText == null) {
            this.hipText = new Text((Composite)this, 2048);
            this.hipText.setLayoutData((Object)new GridData(100, 23));
        }
        return this.hipText;
    }

    public Label getInseamLabel() {
        if (this.inseamLabel == null) {
            this.inseamLabel = new Label((Composite)this, 0);
            this.inseamLabel.setText("inseam:");
            this.inseamLabel.setAlignment(131072);
            this.labelGd = new GridData(100, 23);
            this.inseamLabel.setLayoutData((Object)this.labelGd);
        }
        return this.inseamLabel;
    }

    public Text getInseamText() {
        if (this.inseamText == null) {
            this.inseamText = new Text((Composite)this, 2048);
            this.inseamText.setLayoutData((Object)new GridData(100, 23));
        }
        return this.inseamText;
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
                        SizeManagerComposite.this.performAction();
                    }
                    catch (Exception e1) {
                        SizeManagerComposite.this.getLog().error((Object)"error", (Throwable)e1);
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
                    if (SizeManagerComposite.this.editing) {
                        SizeManagerComposite.this.clean();
                    } else {
                        SizeManagerComposite.this.hide();
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

    private void loadSizes() {
        List sizes = SizeRepo.findAll();
        this.getSizes().removeAll();
        for (Size size : sizes) {
            TableItem item = new TableItem(this.getSizes(), 0);
            String[] texts = new String[]{String.valueOf(size.getId()), String.valueOf(size.getWaist()) + " x " + size.getInseam()};
            item.setData((Object)size);
            item.setText(texts);
        }
    }

    private GridData getLabelGd() {
        if (this.labelGd == null) {
            this.labelGd = new GridData(100, 23);
        }
        return this.labelGd;
    }

    private GridData getTextGd() {
        if (this.textGd == null) {
            this.textGd = new GridData(100, 23);
        }
        return this.textGd;
    }

    private void performAction() throws Exception {
        Size size = null;
        int waist = Integer.parseInt(this.getWaistText().getText());
        int hip = Integer.parseInt(this.getHipText().getText());
        int inseam = Integer.parseInt(this.getInseamText().getText());
        String successMsg = "";
        String errorMsg = "";
        if (this.editing) {
            size = this.selectedSize;
            successMsg = "Size updated correctly.";
            errorMsg = "There was a problem while updating the size.";
        } else {
            size = new Size();
            successMsg = "Size added correctly.";
            errorMsg = "There was a problem adding the size.";
        }
        size.setWaist(waist);
        size.setHip(hip);
        size.setInseam(inseam);
        UslcJpa jpa = new UslcJpa();
        int style = 2;
        MessageBox diag = new MessageBox(this.getShell(), style);
        diag.setText(Constants.MESSAGE_BOX_DIAG_TITLE.toString());
        if (jpa.persist((Object)size)) {
            diag.setMessage(successMsg);
            this.clean();
            this.loadSizes();
        } else {
            style = 1;
            diag.setMessage(errorMsg);
        }
        diag.open();
    }

    private void clean() {
        this.editing = false;
        this.getInfo().setText("Info: Add a new Size");
        this.getInfo().setAlignment(16384);
        this.getWaistText().setText("");
        this.getHipText().setText("");
        this.getInseamText().setText("");
        this.getAction().setText("add");
    }

    public void hide() {
        this.clean();
        this.setParent((Composite)this.getParent().getMaster().getHiddenShell());
        this.setVisible(false);
    }

    private Logger getLog() {
        if (this.log == null) {
            this.log = Logger.getLogger((Class)SizeManagerComposite.class);
            PropertyConfigurator.configure((String)"log4j.properties");
        }
        return this.log;
    }

    public SizeManagerComposite(Composite parent, int style) {
        super(parent, style);
        this.initComposite();
    }

    protected void checkSubclass() {
    }

}

