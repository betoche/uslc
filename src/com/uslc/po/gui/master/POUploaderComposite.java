/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  org.apache.log4j.Logger
 *  org.apache.log4j.PropertyConfigurator
 *  org.eclipse.swt.layout.FormData
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Button
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.Shell
 */
package com.uslc.po.gui.master;

import com.uslc.po.gui.master.MasterCenterComposite;
import com.uslc.po.gui.master.MasterTopComposite;
import com.uslc.po.gui.master.POMaster;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Shell;

public class POUploaderComposite
extends Composite {
    private MasterCenterComposite parent = null;
    private Logger log = null;

    public POUploaderComposite(MasterCenterComposite composite) {
        super((Composite)composite.getMaster().getHiddenShell(), 2048);
        this.parent = composite;
        this.initComposite();
    }

    private void initComposite() {
        FormData data = new FormData(550, 550);
        this.setLayoutData((Object)data);
        this.setLayout((Layout)new GridLayout(6, false));
    }

    public void hide() {
        this.clean();
        this.setParent((Composite)this.getParent().getMaster().getHiddenShell());
        this.setVisible(false);
        this.getParent().getMaster().getTopComposite().getCancel().setEnabled(false);
    }

    private void clean() {
    }

    public MasterCenterComposite getParent() {
        return this.parent;
    }

    private Logger getLog() {
        if (this.log == null) {
            this.log = Logger.getLogger((Class)POUploaderComposite.class);
            PropertyConfigurator.configure((String)"log4j.properties");
        }
        return this.log;
    }
}

