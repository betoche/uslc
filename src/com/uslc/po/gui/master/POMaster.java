/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  com.uslc.po.jpa.entity.User
 *  com.uslc.po.jpa.logic.UserType
 *  com.uslc.po.jpa.util.Constants
 *  com.uslc.po.jpa.util.UslcJpa
 *  org.apache.log4j.Logger
 *  org.apache.log4j.PropertyConfigurator
 *  org.eclipse.swt.graphics.Device
 *  org.eclipse.swt.graphics.Font
 *  org.eclipse.swt.graphics.Image
 *  org.eclipse.swt.graphics.Rectangle
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.Listener
 *  org.eclipse.swt.widgets.Menu
 *  org.eclipse.swt.widgets.MessageBox
 *  org.eclipse.swt.widgets.Monitor
 *  org.eclipse.swt.widgets.Shell
 */
package com.uslc.po.gui.master;

import com.uslc.po.gui.master.MasterBottomComposite;
import com.uslc.po.gui.master.MasterCenterComposite;
import com.uslc.po.gui.master.MasterLeftComposite;
import com.uslc.po.gui.master.MasterRightComposite;
import com.uslc.po.gui.master.MasterTopComposite;
import com.uslc.po.gui.master.NewPurchaseOrderComposite;
import com.uslc.po.gui.master.NewPurchaseOrderDetailComposite;
import com.uslc.po.gui.master.POMasterMenu;
import com.uslc.po.gui.master.PackingDetailComposite;
import com.uslc.po.gui.master.PurchaseOrderDetailComposite;
import com.uslc.po.gui.master.ReportsManagerComposite;
import com.uslc.po.gui.master.catalog.ColorManagerComposite;
import com.uslc.po.gui.master.catalog.ItemManagerComposite;
import com.uslc.po.gui.master.catalog.PurchaseOrderByUserComposite;
import com.uslc.po.gui.master.catalog.SizeManagerComposite;
import com.uslc.po.gui.master.catalog.UpcManagerComposite;
import com.uslc.po.gui.master.catalog.UserManagerComposite;
import com.uslc.po.gui.util.ConfirmCloseListener;
import com.uslc.po.gui.util.Master;
import com.uslc.po.jpa.entity.User;
import com.uslc.po.jpa.logic.UserType;
import com.uslc.po.jpa.util.Constants;
import com.uslc.po.jpa.util.UslcJpa;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;

public class POMaster {
    private Monitor monitor = null;
    private Display display = null;
    private Shell hiddenShell = null;
    private Shell shell = null;
    private POMasterMenu menu = null;
    private GridLayout gridLayout = null;
    private MasterTopComposite topComposite = null;
    private MasterLeftComposite leftComposite = null;
    private MasterCenterComposite centerComposite = null;
    private MasterRightComposite rightComposite = null;
    private MasterBottomComposite bottomComposite = null;
    private Master master = null;
    private User currUser = null;
    private Logger log = null;

    public POMaster(Shell shell, Display display, User user) {
        this.display = display;
        this.currUser = user;
        shell.dispose();
        this.initMasterForm();
    }

    private void initMasterForm() {
        this.centerUI();
        this.getShell().open();
        while (!this.getShell().isDisposed()) {
            if (this.getDisplay().readAndDispatch()) continue;
            this.getDisplay().sleep();
        }
        this.getDisplay().dispose();
    }

    public Display getDisplay() {
        if (this.display == null) {
            this.display = new Display();
        }
        return this.display;
    }

    public Monitor getMonitor() {
        if (this.monitor == null) {
            this.monitor = this.getDisplay().getPrimaryMonitor();
        }
        return this.monitor;
    }

    public Shell getShell() {
        if (this.shell == null) {
            this.shell = new Shell(this.getDisplay());
            this.shell.setSize(1050, 800);
            this.shell.setLayout((Layout)this.getGridLayout());
            this.shell.setMenuBar(this.getMenu().getBar());
            this.shell.setText("purchase order master - USLC Apparel");
            this.shell.addListener(21, (Listener)new ConfirmCloseListener(this));
            Image img = new Image((Device)this.getDisplay(), "images/po.ico");
            this.shell.setImage(img);
            try {
                this.shell.setFont(this.getMaster().getDefaultMasterFont());
            }
            catch (IOException e) {
                this.getLog().error((Object)"Error in getShell method", (Throwable)e);
            }
            this.getTopComposite();
            this.getLeftComposite();
            this.getCenterComposite();
            this.getRightComposite();
            this.getBottomComposite();
            this.getShell().layout();
            this.getCurentUser().setActive(true);
            UslcJpa jpa = new UslcJpa();
            try {
                jpa.persist((Object)this.getCurentUser());
            }
            catch (Exception e) {
                MessageBox msg = new MessageBox(new Shell(), 1);
                msg.setText(Constants.MESSAGE_BOX_DIAG_TITLE.toString());
                msg.setMessage(e.getMessage());
                this.getLog().error((Object)"error", (Throwable)e);
            }
        }
        return this.shell;
    }

    public MasterBottomComposite getBottomComposite() {
        if (this.bottomComposite == null) {
            this.bottomComposite = new MasterBottomComposite(this);
        }
        return this.bottomComposite;
    }

    public MasterRightComposite getRightComposite() {
        if (this.rightComposite == null) {
            this.rightComposite = new MasterRightComposite(this);
        }
        return this.rightComposite;
    }

    public MasterCenterComposite getCenterComposite() {
        if (this.centerComposite == null) {
            this.centerComposite = new MasterCenterComposite(this);
        }
        return this.centerComposite;
    }

    public MasterTopComposite getTopComposite() {
        if (this.topComposite == null) {
            this.topComposite = new MasterTopComposite(this);
        }
        return this.topComposite;
    }

    public MasterLeftComposite getLeftComposite() {
        if (this.leftComposite == null) {
            this.leftComposite = new MasterLeftComposite(this);
        }
        return this.leftComposite;
    }

    public GridLayout getGridLayout() {
        if (this.gridLayout == null) {
            this.gridLayout = new GridLayout(3, false);
            this.gridLayout.horizontalSpacing = 2;
            this.gridLayout.marginBottom = 2;
            this.gridLayout.marginTop = 2;
            this.gridLayout.marginLeft = 2;
            this.gridLayout.marginRight = 2;
        }
        return this.gridLayout;
    }

    public POMasterMenu getMenu() {
        if (this.menu == null) {
            this.menu = new POMasterMenu(this);
        }
        return this.menu;
    }

    public Shell getHiddenShell() {
        if (this.hiddenShell == null) {
            this.hiddenShell = new Shell();
        }
        return this.hiddenShell;
    }

    private void centerUI() {
        Rectangle bounds = this.getMonitor().getBounds();
        Rectangle rect = this.getShell().getBounds();
        int x = bounds.x + (bounds.width - rect.width) / 2;
        int y = bounds.y + (bounds.height - rect.height) / 2;
        this.getShell().setLocation(x, y);
    }

    public static void main(String[] args) {
        User user = new User();
        user.setUserType(UserType.ADMIN.getId());
        POMaster master = new POMaster(null, null, user);
    }

    public Master getMaster() throws IOException {
        if (this.master == null) {
            this.master = new Master(this);
        }
        return this.master;
    }

    private Logger getLog() {
        if (this.log == null) {
            this.log = Logger.getLogger((Class)POMaster.class);
            PropertyConfigurator.configure((String)"log4j.properties");
        }
        return this.log;
    }

    public User getCurentUser() {
        return this.currUser;
    }

    public void hideAllComposites() {
        try {
            this.getCenterComposite().getSizeManager().hide();
        }
        catch (Exception var1_1) {
            // empty catch block
        }
        try {
            this.getCenterComposite().getColorManager().hide();
        }
        catch (Exception var1_2) {
            // empty catch block
        }
        try {
            this.getCenterComposite().getUpcManager().hide();
        }
        catch (Exception var1_3) {
            // empty catch block
        }
        try {
            this.getCenterComposite().getNewPurchaseOrder().hide();
        }
        catch (Exception var1_4) {
            // empty catch block
        }
        try {
            this.getCenterComposite().getItemManager().hide();
        }
        catch (Exception var1_5) {
            // empty catch block
        }
        try {
            this.getCenterComposite().getPackingDetailViewer().hide();
        }
        catch (Exception var1_6) {
            // empty catch block
        }
        try {
            this.getCenterComposite().getPackingDetailViewer().hide();
        }
        catch (Exception var1_7) {
            // empty catch block
        }
        try {
            this.getCenterComposite().getPurchaseDetailViewer().hide();
        }
        catch (Exception var1_8) {
            // empty catch block
        }
        try {
            this.getCenterComposite().getUserManager().hide();
        }
        catch (Exception var1_9) {
            // empty catch block
        }
        try {
            this.getCenterComposite().getPurchaseOrderByUserManager().hide();
        }
        catch (Exception var1_10) {
            // empty catch block
        }
        try {
            this.getCenterComposite().getReportsManagerCentre().hide();
        }
        catch (Exception var1_11) {
            // empty catch block
        }
        try {
            this.getRightComposite().getNewPurchaseOrderDetail().hide();
        }
        catch (Exception var1_12) {
            // empty catch block
        }
    }
}

