/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  com.ibm.icu.util.Calendar
 *  com.uslc.po.jpa.entity.Log
 *  com.uslc.po.jpa.entity.User
 *  com.uslc.po.jpa.logic.Actions
 *  com.uslc.po.jpa.logic.Forms
 *  com.uslc.po.jpa.util.UslcJpa
 *  org.apache.log4j.Logger
 *  org.apache.log4j.PropertyConfigurator
 *  org.eclipse.swt.widgets.Event
 *  org.eclipse.swt.widgets.Listener
 *  org.eclipse.swt.widgets.MessageBox
 *  org.eclipse.swt.widgets.Shell
 */
package com.uslc.po.gui.util;

import com.ibm.icu.util.Calendar;
import com.uslc.po.gui.master.POMaster;
import com.uslc.po.jpa.entity.Log;
import com.uslc.po.jpa.entity.User;
import com.uslc.po.jpa.logic.Actions;
import com.uslc.po.jpa.logic.Forms;
import com.uslc.po.jpa.util.UslcJpa;
import java.util.Date;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class ConfirmCloseListener
implements Listener {
    private POMaster master = null;
    private Logger log = null;

    public ConfirmCloseListener(POMaster poMaster) {
        this.master = poMaster;
    }

    public void handleEvent(Event event) {
        int style = 65732;
        MessageBox messageBox = new MessageBox(this.master.getShell(), style);
        messageBox.setText("Information");
        messageBox.setMessage("Are you sure you want to exit?");
        boolean bl = event.doit = messageBox.open() == 64;
        if (event.doit) {
            try {
                UslcJpa jpa = new UslcJpa();
                this.master.getCurentUser().setActive(false);
                jpa.persist((Object)this.master.getCurentUser());
                Log newLog = new Log();
                newLog.setActionId(Actions.EXIT.getId());
                newLog.setFormId(Forms.MASTER.getId());
                newLog.setDescription("application exits");
                newLog.setTimestamp(Calendar.getInstance().getTime());
                newLog.setUser(this.master.getCurentUser());
                jpa.persist((Object)newLog);
                this.master.getShell().dispose();
            }
            catch (Exception e) {
                this.getLog().error((Object)e);
            }
        }
    }

    private Logger getLog() {
        if (this.log == null) {
            this.log = Logger.getLogger((Class)ConfirmCloseListener.class);
            PropertyConfigurator.configure((String)"log4j.properties");
        }
        return this.log;
    }
}

