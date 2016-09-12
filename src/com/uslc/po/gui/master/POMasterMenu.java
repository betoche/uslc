/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  com.ibm.icu.util.Calendar
 *  com.uslc.po.jpa.entity.Log
 *  com.uslc.po.jpa.entity.User
 *  com.uslc.po.jpa.logic.Actions
 *  com.uslc.po.jpa.logic.Forms
 *  com.uslc.po.jpa.util.Constants
 *  com.uslc.po.jpa.util.UslcJpa
 *  org.apache.log4j.Logger
 *  org.apache.log4j.PropertyConfigurator
 *  org.eclipse.swt.events.SelectionAdapter
 *  org.eclipse.swt.events.SelectionEvent
 *  org.eclipse.swt.events.SelectionListener
 *  org.eclipse.swt.graphics.Device
 *  org.eclipse.swt.graphics.Image
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Decorations
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.Menu
 *  org.eclipse.swt.widgets.MenuItem
 *  org.eclipse.swt.widgets.MessageBox
 *  org.eclipse.swt.widgets.Shell
 */
package com.uslc.po.gui.master;

import com.ibm.icu.util.Calendar;
import com.uslc.po.gui.master.AboutComposite;
import com.uslc.po.gui.master.MasterCenterComposite;
import com.uslc.po.gui.master.MasterTopComposite;
import com.uslc.po.gui.master.NewPurchaseOrderComposite;
import com.uslc.po.gui.master.POMaster;
import com.uslc.po.gui.master.ReportsManagerComposite;
import com.uslc.po.gui.master.catalog.ColorManagerComposite;
import com.uslc.po.gui.master.catalog.ItemManagerComposite;
import com.uslc.po.gui.master.catalog.PurchaseOrderByUserComposite;
import com.uslc.po.gui.master.catalog.SizeManagerComposite;
import com.uslc.po.gui.master.catalog.UpcManagerComposite;
import com.uslc.po.gui.master.catalog.UserManagerComposite;
import com.uslc.po.jpa.entity.Log;
import com.uslc.po.jpa.entity.User;
import com.uslc.po.jpa.logic.Actions;
import com.uslc.po.jpa.logic.Forms;
import com.uslc.po.jpa.util.Constants;
import com.uslc.po.jpa.util.UslcJpa;
import java.util.Date;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Decorations;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class POMasterMenu {
    private Menu bar = null;
    private Menu catalogs = null;
    private MenuItem catalogsMenu = null;
    private MenuItem colorMenuItem = null;
    private MenuItem sizeMenuItem = null;
    private MenuItem itemMenuItem = null;
    private MenuItem upcMenuItem = null;
    private MenuItem userMenuItem = null;
    private Menu purchaseOrder = null;
    private MenuItem purchaseOrderMenu = null;
    private MenuItem newMenuItem = null;
    private MenuItem uploadMenuItem = null;
    private MenuItem assignPurchaseOrderItem = null;
    private MenuItem closeMenuItem = null;
    private Menu reports = null;
    private MenuItem reportsMenu = null;
    private MenuItem purchaseOrderReporMenuItem = null;
    private Menu help = null;
    private MenuItem helpMenu = null;
    private MenuItem aboutMenuItem = null;
    private POMaster master = null;
    private Logger log = null;

    public POMasterMenu(POMaster master) {
        this.master = master;
    }

    public Menu getBar() {
        if (this.bar == null) {
            this.bar = new Menu((Decorations)this.getMaster().getShell(), 2);
            this.getPurchaseOrderMenu();
            this.getCatalogsMenu();
            this.getReportsMenu();
            this.getHelpMenu();
        }
        return this.bar;
    }

    public Menu getCatalogs() {
        if (this.catalogs == null) {
            this.catalogs = new Menu((Decorations)this.getMaster().getShell(), 4);
            this.getColorMenuItem();
            this.getSizeMenuItem();
            this.getUpcMenuItem();
            this.getItemMenuItem();
            this.getUserMenuItem();
        }
        return this.catalogs;
    }

    public MenuItem getColorMenuItem() {
        if (this.colorMenuItem == null) {
            this.colorMenuItem = new MenuItem(this.getCatalogs(), 8);
            this.colorMenuItem.setText("&color");
            Image img = new Image((Device)this.getMaster().getDisplay(), "images/color.png");
            this.colorMenuItem.setImage(img);
            this.colorMenuItem.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent e) {
                    POMasterMenu.this.getMaster().getCenterComposite().showComposite(POMasterMenu.this.getMaster().getCenterComposite().getColorManager());
                    POMasterMenu.this.getMaster().getCenterComposite().layout();
                    POMasterMenu.this.getMaster().getShell().layout();
                }
            });
        }
        return this.colorMenuItem;
    }

    public MenuItem getSizeMenuItem() {
        if (this.sizeMenuItem == null) {
            this.sizeMenuItem = new MenuItem(this.getCatalogs(), 8);
            this.sizeMenuItem.setText("&size");
            Image img = new Image((Device)this.getMaster().getDisplay(), "images/size.png");
            this.sizeMenuItem.setImage(img);
            this.sizeMenuItem.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent e) {
                    POMasterMenu.this.getMaster().getCenterComposite().showComposite(POMasterMenu.this.getMaster().getCenterComposite().getSizeManager());
                    POMasterMenu.this.getMaster().getCenterComposite().layout();
                    POMasterMenu.this.getMaster().getShell().layout();
                }
            });
        }
        return this.sizeMenuItem;
    }

    public MenuItem getUpcMenuItem() {
        if (this.upcMenuItem == null) {
            this.upcMenuItem = new MenuItem(this.getCatalogs(), 8);
            this.upcMenuItem.setText("&upc");
            Image img = new Image((Device)this.getMaster().getDisplay(), "images/barcode.png");
            this.upcMenuItem.setImage(img);
            this.upcMenuItem.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent e) {
                    POMasterMenu.this.getMaster().getCenterComposite().showComposite(POMasterMenu.this.getMaster().getCenterComposite().getUpcManager());
                    POMasterMenu.this.getMaster().getCenterComposite().layout();
                    POMasterMenu.this.getMaster().getShell().layout();
                }
            });
        }
        return this.upcMenuItem;
    }

    public MenuItem getItemMenuItem() {
        if (this.itemMenuItem == null) {
            this.itemMenuItem = new MenuItem(this.getCatalogs(), 8);
            this.itemMenuItem.setText("&item");
            Image img = new Image((Device)this.getMaster().getDisplay(), "images/item.png");
            this.itemMenuItem.setImage(img);
            this.itemMenuItem.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent e) {
                    POMasterMenu.this.getMaster().getCenterComposite().showComposite(POMasterMenu.this.getMaster().getCenterComposite().getItemManager());
                    POMasterMenu.this.getMaster().getCenterComposite().layout();
                    POMasterMenu.this.getMaster().getShell().layout();
                }
            });
        }
        return this.itemMenuItem;
    }

    public MenuItem getUserMenuItem() {
        if (this.userMenuItem == null) {
            this.userMenuItem = new MenuItem(this.getCatalogs(), 8);
            this.userMenuItem.setText("&user");
            Image img = new Image((Device)this.getMaster().getDisplay(), "images/user.png");
            this.userMenuItem.setImage(img);
            this.userMenuItem.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent e) {
                    POMasterMenu.this.getMaster().getCenterComposite().showComposite(POMasterMenu.this.getMaster().getCenterComposite().getUserManager());
                    POMasterMenu.this.getMaster().getCenterComposite().layout();
                    POMasterMenu.this.getMaster().getShell().layout();
                }
            });
        }
        return this.userMenuItem;
    }

    public MenuItem getCatalogsMenu() {
        if (this.catalogsMenu == null) {
            this.catalogsMenu = new MenuItem(this.getBar(), 64);
            this.catalogsMenu.setText("&catalogs");
            this.catalogsMenu.setMenu(this.getCatalogs());
        }
        return this.catalogsMenu;
    }

    public Menu getPurchaseOrder() {
        if (this.purchaseOrder == null) {
            this.purchaseOrder = new Menu((Decorations)this.getMaster().getShell(), 4);
            this.getNewMenuItem();
            this.getUploadMenuItem();
            this.getAssignPurchaseOrderItem();
            this.getCloseMenuItem();
        }
        return this.purchaseOrder;
    }

    public MenuItem getNewMenuItem() {
        if (this.newMenuItem == null) {
            this.newMenuItem = new MenuItem(this.getPurchaseOrder(), 8);
            this.newMenuItem.setText("&new");
            Image img = new Image((Device)this.getMaster().getDisplay(), "images/new.png");
            this.newMenuItem.setImage(img);
            this.newMenuItem.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent arg0) {
                    POMasterMenu.this.getMaster().getCenterComposite().showComposite(POMasterMenu.this.getMaster().getCenterComposite().getNewPurchaseOrder());
                    POMasterMenu.this.getMaster().getCenterComposite().layout();
                    POMasterMenu.this.getMaster().getShell().layout();
                }
            });
        }
        return this.newMenuItem;
    }

    public MenuItem getUploadMenuItem() {
        if (this.uploadMenuItem == null) {
            this.uploadMenuItem = new MenuItem(this.getPurchaseOrder(), 8);
            this.uploadMenuItem.setText("&upload");
            Image img = new Image((Device)this.getMaster().getDisplay(), "images/upload.png");
            this.uploadMenuItem.setImage(img);
            this.uploadMenuItem.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent arg0) {
                    POMasterMenu.this.getMaster().getTopComposite().showFileUploadDialog();
                }
            });
        }
        return this.uploadMenuItem;
    }

    public MenuItem getAssignPurchaseOrderItem() {
        if (this.assignPurchaseOrderItem == null) {
            this.assignPurchaseOrderItem = new MenuItem(this.getPurchaseOrder(), 8);
            this.assignPurchaseOrderItem.setText("&assign po");
            Image img = new Image((Device)this.getMaster().getDisplay(), "images/assign.png");
            this.assignPurchaseOrderItem.setImage(img);
            this.assignPurchaseOrderItem.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent arg0) {
                    POMasterMenu.this.getMaster().getCenterComposite().showComposite(POMasterMenu.this.getMaster().getCenterComposite().getPurchaseOrderByUserManager());
                    POMasterMenu.this.getMaster().getCenterComposite().layout();
                    POMasterMenu.this.getMaster().getShell().layout();
                }
            });
            new org.eclipse.swt.widgets.MenuItem(this.getPurchaseOrder(), 2);
        }
        return this.assignPurchaseOrderItem;
    }

    public MenuItem getCloseMenuItem() {
        if (this.closeMenuItem == null) {
            this.closeMenuItem = new MenuItem(this.getPurchaseOrder(), 8);
            this.closeMenuItem.setText("&close");
            Image img = new Image((Device)this.getMaster().getDisplay(), "images/exit.png");
            this.closeMenuItem.setImage(img);
            this.closeMenuItem.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent arg0) {
                    MessageBox msg = new MessageBox(POMasterMenu.this.getMaster().getShell(), 196);
                    msg.setMessage("Do you really want to exit?");
                    msg.setText(Constants.MESSAGE_BOX_DIAG_TITLE.toString());
                    int response = msg.open();
                    if (response == 64) {
                        UslcJpa jpa = new UslcJpa();
                        Log newLog = new Log();
                        newLog.setActionId(Actions.EXIT.getId());
                        newLog.setFormId(Forms.MASTER.getId());
                        newLog.setDescription("application close");
                        newLog.setTimestamp(Calendar.getInstance().getTime());
                        newLog.setUser(POMasterMenu.this.master.getCurentUser());
                        try {
                            try {
                                jpa.persist((Object)newLog);
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                                POMasterMenu.this.getMaster().getShell().dispose();
                                System.exit(0);
                            }
                        }
                        finally {
                            POMasterMenu.this.getMaster().getShell().dispose();
                            System.exit(0);
                        }
                    }
                }
            });
        }
        return this.closeMenuItem;
    }

    public MenuItem getPurchaseOrderMenu() {
        if (this.purchaseOrderMenu == null) {
            this.purchaseOrderMenu = new MenuItem(this.getBar(), 64);
            this.purchaseOrderMenu.setText("&po manager");
            this.purchaseOrderMenu.setMenu(this.getPurchaseOrder());
        }
        return this.purchaseOrderMenu;
    }

    public Menu getReports() {
        if (this.reports == null) {
            this.reports = new Menu((Decorations)this.getMaster().getShell(), 4);
            this.getPurchaseOrderReporMenuItem();
        }
        return this.reports;
    }

    public MenuItem getPurchaseOrderReporMenuItem() {
        if (this.purchaseOrderReporMenuItem == null) {
            this.purchaseOrderReporMenuItem = new MenuItem(this.getReports(), 8);
            this.purchaseOrderReporMenuItem.setText("&reports");
            Image img = new Image((Device)this.getMaster().getDisplay(), "images/report.png");
            this.purchaseOrderReporMenuItem.setImage(img);
            this.purchaseOrderReporMenuItem.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent e) {
                    POMasterMenu.this.getMaster().getCenterComposite().showComposite(POMasterMenu.this.getMaster().getCenterComposite().getReportsManagerCentre());
                    POMasterMenu.this.getMaster().getCenterComposite().layout();
                    POMasterMenu.this.getMaster().getShell().layout();
                }
            });
        }
        return this.purchaseOrderReporMenuItem;
    }

    public MenuItem getReportsMenu() {
        if (this.reportsMenu == null) {
            this.reportsMenu = new MenuItem(this.getBar(), 64);
            this.reportsMenu.setText("&reports");
            this.reportsMenu.setMenu(this.getReports());
        }
        return this.reportsMenu;
    }

    public Menu getHelp() {
        if (this.help == null) {
            this.help = new Menu((Decorations)this.getMaster().getShell(), 4);
            this.getAboutMenuItem();
        }
        return this.help;
    }

    public MenuItem getAboutMenuItem() {
        if (this.aboutMenuItem == null) {
            this.aboutMenuItem = new MenuItem(this.getHelp(), 8);
            this.aboutMenuItem.setText("&about");
            Image img = new Image((Device)this.getMaster().getDisplay(), "images/about.png");
            this.aboutMenuItem.setImage(img);
            this.aboutMenuItem.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent e) {
                    POMasterMenu.this.getMaster().getCenterComposite().showComposite(POMasterMenu.this.getMaster().getCenterComposite().getAbout());
                    POMasterMenu.this.getMaster().getCenterComposite().layout();
                    POMasterMenu.this.getMaster().getShell().layout();
                }
            });
        }
        return this.aboutMenuItem;
    }

    public MenuItem getHelpMenu() {
        if (this.helpMenu == null) {
            this.helpMenu = new MenuItem(this.getBar(), 64);
            this.helpMenu.setText("&help");
            this.helpMenu.setMenu(this.getHelp());
        }
        return this.helpMenu;
    }

    public POMaster getMaster() {
        return this.master;
    }

    private Logger getLog() {
        if (this.log == null) {
            this.log = Logger.getLogger((Class)POMasterMenu.class);
            PropertyConfigurator.configure((String)"log4j.properties");
        }
        return this.log;
    }

}

