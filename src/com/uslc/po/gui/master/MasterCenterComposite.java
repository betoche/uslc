/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  org.eclipse.swt.layout.FormLayout
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Button
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.Shell
 */
package com.uslc.po.gui.master;

import com.uslc.po.gui.master.AboutComposite;
import com.uslc.po.gui.master.MasterTopComposite;
import com.uslc.po.gui.master.NewPurchaseOrderComposite;
import com.uslc.po.gui.master.POMaster;
import com.uslc.po.gui.master.PackingDetailComposite;
import com.uslc.po.gui.master.PurchaseOrderDetailComposite;
import com.uslc.po.gui.master.ReportsManagerComposite;
import com.uslc.po.gui.master.catalog.ColorManagerComposite;
import com.uslc.po.gui.master.catalog.ItemManagerComposite;
import com.uslc.po.gui.master.catalog.PurchaseOrderByUserComposite;
import com.uslc.po.gui.master.catalog.SizeManagerComposite;
import com.uslc.po.gui.master.catalog.UpcManagerComposite;
import com.uslc.po.gui.master.catalog.UserManagerComposite;
import com.uslc.po.gui.master.interfaces.MasterCompositeInterface;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Shell;

public class MasterCenterComposite
extends Composite
implements MasterCompositeInterface {
    private POMaster master = null;
    private ColorManagerComposite colorManager = null;
    private SizeManagerComposite sizeManager = null;
    private UpcManagerComposite upcManager = null;
    private ItemManagerComposite itemManager = null;
    private UserManagerComposite userManager = null;
    private NewPurchaseOrderComposite newPoManager = null;
    private PackingDetailComposite packingDetailViewer = null;
    private PurchaseOrderDetailComposite purchaseDetailViewer = null;
    private AboutComposite about = null;
    private PurchaseOrderByUserComposite purchaseByUserManager = null;
    private ReportsManagerComposite reportsCentre = null;
    private Composite left = null;
    private Composite center = null;
    private Composite right = null;

    public MasterCenterComposite(POMaster master) {
        super((Composite)master.getShell(), 2048);
        this.master = master;
        this.initComposite();
    }

    private void initComposite() {
        GridData data = new GridData(1808);
        this.setLayoutData((Object)data);
        GridLayout layout = new GridLayout(3, false);
        layout.marginBottom = 20;
        layout.marginTop = 20;
        layout.marginLeft = 20;
        layout.marginRight = 20;
        this.setLayout((Layout)layout);
    }

    @Override
    public POMaster getMaster() {
        return this.master;
    }

    public void showComposite(Composite composite) {
        this.getMaster().hideAllComposites();
        composite.setParent(this.getCenterComposite());
        composite.setVisible(true);
        composite.layout();
        this.layout();
        this.getMaster().getShell().layout();
        if (composite instanceof NewPurchaseOrderComposite) {
            this.getMaster().getTopComposite().getCancel().setEnabled(true);
        } else {
            this.getMaster().getTopComposite().getCancel().setEnabled(false);
        }
    }

    public SizeManagerComposite getSizeManager() {
        if (this.sizeManager == null) {
            this.sizeManager = new SizeManagerComposite(this);
        }
        return this.sizeManager;
    }

    public ColorManagerComposite getColorManager() {
        if (this.colorManager == null) {
            this.colorManager = new ColorManagerComposite(this);
        }
        return this.colorManager;
    }

    public UpcManagerComposite getUpcManager() {
        if (this.upcManager == null) {
            this.upcManager = new UpcManagerComposite(this);
        }
        return this.upcManager;
    }

    public Composite getCenterComposite() {
        if (this.center == null) {
            GridData data = new GridData(1808);
            this.left = new Composite((Composite)this, 0);
            this.center = new Composite((Composite)this, 0);
            this.right = new Composite((Composite)this, 0);
            this.left.setLayoutData((Object)data);
            this.right.setLayoutData((Object)data);
            FormLayout layout = new FormLayout();
            this.center.setLayout((Layout)layout);
        }
        return this.center;
    }

    public void setCenterComposite(Composite composite) {
        this.center = composite;
    }

    public NewPurchaseOrderComposite getNewPurchaseOrder() {
        if (this.newPoManager == null) {
            this.newPoManager = new NewPurchaseOrderComposite(this);
        }
        return this.newPoManager;
    }

    public PackingDetailComposite getPackingDetailViewer() {
        if (this.packingDetailViewer == null) {
            this.packingDetailViewer = new PackingDetailComposite(this);
        }
        return this.packingDetailViewer;
    }

    public PurchaseOrderDetailComposite getPurchaseDetailViewer() {
        if (this.purchaseDetailViewer == null) {
            this.purchaseDetailViewer = new PurchaseOrderDetailComposite(this);
        }
        return this.purchaseDetailViewer;
    }

    public ItemManagerComposite getItemManager() {
        if (this.itemManager == null) {
            this.itemManager = new ItemManagerComposite(this);
        }
        return this.itemManager;
    }

    public AboutComposite getAbout() {
        if (this.about == null) {
            this.about = new AboutComposite(this);
        }
        return this.about;
    }

    public UserManagerComposite getUserManager() {
        if (this.userManager == null) {
            this.userManager = new UserManagerComposite(this);
        }
        return this.userManager;
    }

    public PurchaseOrderByUserComposite getPurchaseOrderByUserManager() {
        if (this.purchaseByUserManager == null) {
            this.purchaseByUserManager = new PurchaseOrderByUserComposite(this);
        }
        return this.purchaseByUserManager;
    }

    public ReportsManagerComposite getReportsManagerCentre() {
        if (this.reportsCentre == null) {
            this.reportsCentre = new ReportsManagerComposite(this);
        }
        return this.reportsCentre;
    }
}

