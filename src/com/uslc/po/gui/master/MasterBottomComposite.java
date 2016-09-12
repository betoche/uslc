/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  com.mysql.jdbc.exceptions.jdbc4.CommunicationsException
 *  com.uslc.po.jpa.entity.User
 *  com.uslc.po.jpa.logic.UserRepo
 *  org.apache.log4j.Logger
 *  org.apache.log4j.PropertyConfigurator
 *  org.eclipse.swt.SWTException
 *  org.eclipse.swt.custom.CLabel
 *  org.eclipse.swt.graphics.Device
 *  org.eclipse.swt.graphics.Image
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.Shell
 */
package com.uslc.po.gui.master;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.uslc.po.gui.master.POMaster;
import com.uslc.po.jpa.entity.User;
import com.uslc.po.jpa.logic.UserRepo;
import java.net.ConnectException;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Shell;

public class MasterBottomComposite
extends Composite {
    private POMaster master = null;
    private CLabel sessionInfo = null;
    private Label usersInfo = null;
    private Label lastAction = null;
    private ClientUpdater updater = null;
    private Logger log = null;

    public MasterBottomComposite(POMaster master) {
        super((Composite)master.getShell(), 2048);
        this.master = master;
        this.initComposite();
    }

    private void initComposite() {
        GridData data = new GridData(768);
        data.heightHint = 25;
        data.horizontalSpan = 3;
        this.setLayoutData((Object)data);
        GridLayout layout = new GridLayout(3, false);
        layout.verticalSpacing = 4;
        layout.marginBottom = 0;
        layout.marginTop = 0;
        layout.marginLeft = 10;
        layout.marginRight = 10;
        layout.marginWidth = 0;
        layout.marginHeight = 0;
        this.setLayout((Layout)layout);
        this.getSessionInfo();
        this.getUsersInfo();
        this.getLastAction();
        this.getUpdater();
    }

    public CLabel getSessionInfo() {
        if (this.sessionInfo == null) {
            this.sessionInfo = new CLabel((Composite)this, 0);
            this.sessionInfo.setImage(new Image((Device)this.getDisplay(), "images/user.png"));
            this.sessionInfo.setText("user:[" + this.getMaster().getCurentUser().getFirstName() + " " + this.getMaster().getCurentUser().getLastName() + "]");
            GridData data = new GridData(4, 4, false, true);
            data.horizontalAlignment = 16384;
            data.verticalAlignment = 16777216;
            data.widthHint = 200;
            this.sessionInfo.setLayoutData((Object)data);
        }
        return this.sessionInfo;
    }

    public Label getUsersInfo() {
        if (this.usersInfo == null) {
            this.usersInfo = new Label((Composite)this, 0);
            this.usersInfo.setText("clients[0] = {active: 0, inactive: 0}");
            this.usersInfo.setAlignment(16777216);
            GridData data = new GridData(4, 4, true, true);
            data.horizontalAlignment = 16777216;
            data.verticalAlignment = 16777216;
            this.usersInfo.setLayoutData((Object)data);
        }
        return this.usersInfo;
    }

    public Label getLastAction() {
        if (this.lastAction == null) {
            this.lastAction = new Label((Composite)this, 0);
            this.lastAction.setText("last action");
            this.lastAction.setAlignment(131072);
            GridData data = new GridData(4, 4, false, true);
            data.horizontalAlignment = 131072;
            data.verticalAlignment = 16777216;
            data.widthHint = 200;
            this.lastAction.setLayoutData((Object)data);
        }
        return this.lastAction;
    }

    private ClientUpdater getUpdater() {
        if (this.updater == null) {
            this.updater = new ClientUpdater();
            this.updater.start();
        }
        return this.updater;
    }

    private POMaster getMaster() {
        return this.master;
    }

    public synchronized void update(final String value) {
        if (this.getDisplay() == null || this.getDisplay().isDisposed()) {
            return;
        }
        this.getDisplay().asyncExec(new Runnable(){

            @Override
            public void run() {
                MasterBottomComposite.this.getUsersInfo().setText(value);
                MasterBottomComposite.this.getShell().layout();
            }
        });
    }

    private Logger getLog() {
        if (this.log == null) {
            this.log = Logger.getLogger((Class)MasterBottomComposite.class);
            PropertyConfigurator.configure((String)"log4j.properties");
        }
        return this.log;
    }

    public class ClientUpdater
    extends Thread {
        @Override
        public void run() {
            try {
                do {
                    Thread.sleep(5000);
                    List users = UserRepo.findAllClients((boolean)true);
                    int total = users.size();
                    int active = 0;
                    int inactive = 0;
                    for (User user : users) {
                        if (user.isActive()) {
                            ++active;
                            continue;
                        }
                        ++inactive;
                    }
                    MasterBottomComposite.this.update("clients[" + total + "] = [active: " + active + ", inactive: " + inactive + "]");
                } while (true);
            }
            catch (InterruptedException e2) {
                MasterBottomComposite.this.getLog().error((Object)"error", (Throwable)e2);
            }
            catch (SWTException e2) {
            }
            catch (CommunicationsException e) {
                MasterBottomComposite.this.getLog().error((Object)"error", (Throwable)e);
            }
            catch (ConnectException e) {
                MasterBottomComposite.this.getLog().error((Object)"error", (Throwable)e);
            }
        }
    }

}

