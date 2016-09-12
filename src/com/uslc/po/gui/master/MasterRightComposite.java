/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Control
 *  org.eclipse.swt.widgets.Group
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.Shell
 */
package com.uslc.po.gui.master;

import com.uslc.po.gui.master.NewPurchaseOrderDetailComposite;
import com.uslc.po.gui.master.POMaster;
import com.uslc.po.gui.master.interfaces.MasterCompositeInterface;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Shell;

public class MasterRightComposite
extends Composite
implements MasterCompositeInterface {
    private POMaster master = null;
    private NewPurchaseOrderDetailComposite poDetail = null;
    private Group infoComposite = null;
    private Label infoText = null;

    public MasterRightComposite(POMaster master) {
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
        this.getInfoComposite();
    }

    @Override
    public POMaster getMaster() {
        return this.master;
    }

    public NewPurchaseOrderDetailComposite getNewPurchaseOrderDetail() {
        if (this.poDetail == null) {
            this.poDetail = new NewPurchaseOrderDetailComposite(this);
        }
        return this.poDetail;
    }

    public Group getInfoComposite() {
        if (this.infoComposite == null) {
            this.infoComposite = new Group((Composite)this, 0);
            this.infoComposite.setText("info");
            GridData gd = new GridData(4, 4, true, true);
            this.infoComposite.setLayoutData((Object)gd);
            GridLayout gl = new GridLayout();
            gl.numColumns = 1;
            this.infoComposite.setLayout((Layout)gl);
            this.getInfoText();
            this.getInfoText().setParent((Composite)this.infoComposite);
        }
        return this.infoComposite;
    }

    public void hideAllComposites() {
        try {
            this.getNewPurchaseOrderDetail().hide();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showComposite(Composite composite) {
        this.hideAllComposites();
        composite.setParent((Composite)this);
        composite.moveAbove((Control)this.getInfoComposite());
        this.getInfoComposite().moveBelow((Control)composite);
        composite.setVisible(true);
        composite.layout();
        this.layout();
        this.getMaster().getShell().layout();
    }

    public Label getInfoText() {
        if (this.infoText == null) {
            this.infoText = new Label((Composite)this.getInfoComposite(), 2048);
            GridData gd = new GridData();
            gd.horizontalAlignment = 768;
            gd.verticalAlignment = 1040;
            gd.grabExcessHorizontalSpace = true;
            gd.grabExcessVerticalSpace = true;
            this.infoText.setLayoutData((Object)gd);
            this.infoText.setText("");
        }
        return this.infoText;
    }

    public void setInfoText(String infoText) {
        this.getInfoText().setText(infoText);
    }
}

