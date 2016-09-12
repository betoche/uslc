/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  com.uslc.po.jpa.entity.PurchaseOrder
 *  com.uslc.po.jpa.util.Constants
 *  com.uslc.po.jpa.util.UslcJpa
 *  org.apache.log4j.Logger
 *  org.apache.log4j.PropertyConfigurator
 *  org.eclipse.swt.events.SelectionAdapter
 *  org.eclipse.swt.events.SelectionEvent
 *  org.eclipse.swt.events.SelectionListener
 *  org.eclipse.swt.graphics.Device
 *  org.eclipse.swt.graphics.Image
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Button
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.FileDialog
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.MessageBox
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Table
 *  org.eclipse.swt.widgets.TableItem
 */
package com.uslc.po.gui.master;

import com.uslc.po.gui.master.MasterCenterComposite;
import com.uslc.po.gui.master.MasterLeftComposite;
import com.uslc.po.gui.master.NewPurchaseOrderComposite;
import com.uslc.po.gui.master.POMaster;
import com.uslc.po.gui.master.PackingDetailComposite;
import com.uslc.po.gui.util.PurchaseOrder;
import com.uslc.po.jpa.util.Constants;
import com.uslc.po.jpa.util.UslcJpa;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

public class MasterTopComposite
extends Composite {
    private POMaster master = null;
    private Button uploadPo = null;
    private Button print = null;
    private Button savePo = null;
    private Button editPo = null;
    private Button deletePo = null;
    private Button cancel = null;
    private GridData gdButtonsLeft = null;
    private GridData gdButtonsRight = null;
    private Logger log = null;

    public MasterTopComposite(POMaster master) {
        super((Composite)master.getShell(), 2048);
        this.master = master;
        this.initComposite();
    }

    private void initComposite() {
        GridData data = new GridData(4, 4, true, false);
        data.heightHint = 50;
        data.horizontalSpan = 3;
        this.setLayoutData((Object)data);
        GridLayout layout = new GridLayout(7, false);
        this.setLayout((Layout)layout);
        this.getUploadPo();
        this.getPrint();
        GridData gd = new GridData(68);
        gd.grabExcessHorizontalSpace = true;
        gd.grabExcessVerticalSpace = true;
        Label title = new Label((Composite)this, 0);
        title.setText("master");
        title.setLayoutData((Object)gd);
        title.setAlignment(16777216);
        this.getSavePo();
        this.getEditPo();
        this.getDeletePo();
        this.getCancel();
    }

    public Button getPrint() {
        if (this.print == null) {
            this.print = new Button((Composite)this, 8);
            this.print.setText("print");
            this.print.setEnabled(false);
            this.print.setLayoutData((Object)this.getGdButtonsLeft());
            Image img = new Image((Device)this.getDisplay(), "images/print.ico");
            this.print.setImage(img);
            this.print.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent arg0) {
                    if (MasterTopComposite.this.getMaster().getCenterComposite().getPackingDetailViewer().isVisible()) {
                        MasterTopComposite.this.getMaster().getCenterComposite().getPackingDetailViewer().exportToImage();
                    }
                }
            });
        }
        return this.print;
    }

    public Button getUploadPo() {
        if (this.uploadPo == null) {
            this.uploadPo = new Button((Composite)this, 8);
            this.uploadPo.setText("upload");
            this.uploadPo.setLayoutData((Object)this.getGdButtonsLeft());
            Image img = new Image((Device)this.getDisplay(), "images/upload.png");
            this.uploadPo.setImage(img);
            this.uploadPo.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent arg0) {
                    MasterTopComposite.this.showFileUploadDialog();
                }
            });
        }
        return this.uploadPo;
    }

    public void showFileUploadDialog() {
        FileDialog dlg = new FileDialog(this.getMaster().getShell(), 4096);
        dlg.setFilterExtensions(new String[]{"*.xlsx", "*.xls"});
        dlg.setText("Open");
        String path = dlg.open();
        if (path == null) {
            return;
        }
        this.getMaster().getCenterComposite().showComposite(this.getMaster().getCenterComposite().getNewPurchaseOrder());
        Exception e = this.getMaster().getCenterComposite().getNewPurchaseOrder().fillWithUploadedFile(path);
        if (e == null) {
            this.getMaster().getCenterComposite().layout();
            this.getMaster().getShell().layout();
        } else {
            this.getMaster().getCenterComposite().getNewPurchaseOrder().hide();
            MessageBox box = new MessageBox(this.getShell(), 1);
            box.setText(Constants.MESSAGE_BOX_DIAG_TITLE.toString());
            box.setMessage("error uploading the file\n\n" + e.getMessage());
            box.open();
        }
    }

    public Button getSavePo() {
        if (this.savePo == null) {
            this.savePo = new Button((Composite)this, 8);
            this.savePo.setText("save");
            this.savePo.setEnabled(false);
            this.savePo.setLayoutData((Object)this.getGdButtonsRight());
            Image img = new Image((Device)this.getDisplay(), "images/save.png");
            this.savePo.setImage(img);
            this.savePo.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent arg0) {
                    MasterTopComposite.this.getMaster().getCenterComposite().getNewPurchaseOrder().savePo();
                }
            });
        }
        return this.savePo;
    }

    public Button getEditPo() {
        if (this.editPo == null) {
            this.editPo = new Button((Composite)this, 8);
            this.editPo.setText("edit");
            this.editPo.setEnabled(false);
            Image img = new Image((Device)this.getDisplay(), "images/edit.png");
            this.editPo.setImage(img);
            this.editPo.setLayoutData((Object)this.getGdButtonsRight());
        }
        return this.editPo;
    }

    public Button getDeletePo() {
        if (this.deletePo == null) {
            this.deletePo = new Button((Composite)this, 8);
            this.deletePo.setText("delete");
            this.deletePo.setEnabled(false);
            this.deletePo.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent event) {
                    block7 : {
                        MasterTopComposite.this.getLog().info((Object)"inside widgetSelected from DeletePo");
                        try {
                            TableItem[] items;
                            PurchaseOrder p = null;
                            TableItem[] arrtableItem = items = MasterTopComposite.this.getMaster().getLeftComposite().getPurchaseOrderTbl().getSelection();
                            int n = arrtableItem.length;
                            int n2 = 0;
                            while (n2 < n) {
                                TableItem item = arrtableItem[n2];
                                p = (PurchaseOrder)item.getData();
                                ++n2;
                            }
                            if (p == null || p.getPo().getDeleted()) break block7;
                            com.uslc.po.jpa.entity.PurchaseOrder po = p.getPo();
                            MessageBox box = new MessageBox(MasterTopComposite.this.getMaster().getShell(), 196);
                            box.setText(Constants.MESSAGE_BOX_DIAG_TITLE.toString());
                            box.setMessage("you are about to delete the purchase order with reference number: " + po.getReferenceNumber() + "\ndo you want to proceed?");
                            if (box.open() != 64) break block7;
                            po.setDeleted(true);
                            UslcJpa jpa = new UslcJpa();
                            try {
                                if (jpa.persist((Object)po)) {
                                    box = new MessageBox(MasterTopComposite.this.getMaster().getShell(), 2);
                                    box.setText(Constants.MESSAGE_BOX_DIAG_TITLE.toString());
                                    box.setMessage("purchase order: " + po.getReferenceNumber() + " deleted correctly.");
                                    MasterTopComposite.this.getMaster().hideAllComposites();
                                    MasterTopComposite.this.getMaster().getLeftComposite().clean();
                                    MasterTopComposite.this.getMaster().getLeftComposite().fillPo();
                                } else {
                                    box = new MessageBox(MasterTopComposite.this.getMaster().getShell(), 1);
                                    box.setText(Constants.MESSAGE_BOX_DIAG_TITLE.toString());
                                    box.setMessage("there was a problem while deleting the purchase order: " + po.getReferenceNumber() + ",\nplease contact your system admin.");
                                }
                            }
                            catch (Exception e) {
                                box = new MessageBox(MasterTopComposite.this.getMaster().getShell(), 1);
                                box.setText(Constants.MESSAGE_BOX_DIAG_TITLE.toString());
                                box.setMessage("there was a problem while deleting the purchase order: " + po.getReferenceNumber() + ",\nplease contact your system admin.\n\nERROR:\n" + e.toString());
                                MasterTopComposite.this.getLog().error((Object)("eror deleting po[" + po.getId() + "]"), (Throwable)e);
                            }
                            box.open();
                        }
                        catch (Exception e) {
                            MasterTopComposite.this.getLog().error((Object)"error?", (Throwable)e);
                        }
                    }
                }
            });
            Image img = new Image((Device)this.getDisplay(), "images/delete.png");
            this.deletePo.setImage(img);
            this.deletePo.setLayoutData((Object)this.getGdButtonsRight());
        }
        return this.deletePo;
    }

    public Button getCancel() {
        if (this.cancel == null) {
            this.cancel = new Button((Composite)this, 8);
            this.cancel.setText("cancel");
            this.cancel.setEnabled(false);
            this.cancel.setLayoutData((Object)this.getGdButtonsRight());
            Image img = new Image((Device)this.getDisplay(), "images/cancel.png");
            this.cancel.setImage(img);
            this.cancel.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent arg0) {
                    MasterTopComposite.this.getMaster().getCenterComposite().getMaster().hideAllComposites();
                }
            });
        }
        return this.cancel;
    }

    private GridData getGdButtonsLeft() {
        if (this.gdButtonsLeft == null) {
            this.gdButtonsLeft = new GridData(1076);
            this.gdButtonsLeft.grabExcessVerticalSpace = true;
            this.gdButtonsLeft.widthHint = 80;
        }
        return this.gdButtonsLeft;
    }

    private GridData getGdButtonsRight() {
        if (this.gdButtonsRight == null) {
            this.gdButtonsRight = new GridData(1172);
            this.gdButtonsRight.grabExcessVerticalSpace = true;
            this.gdButtonsRight.widthHint = 80;
        }
        return this.gdButtonsRight;
    }

    private POMaster getMaster() {
        return this.master;
    }

    private Logger getLog() {
        if (this.log == null) {
            this.log = Logger.getLogger((Class)MasterTopComposite.class);
            PropertyConfigurator.configure((String)"log4j.properties");
        }
        return this.log;
    }

}

