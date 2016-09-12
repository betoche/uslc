/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  com.ibm.icu.util.Calendar
 *  com.uslc.po.jpa.entity.Log
 *  com.uslc.po.jpa.entity.User
 *  com.uslc.po.jpa.logic.Actions
 *  com.uslc.po.jpa.logic.Forms
 *  com.uslc.po.jpa.logic.UserRepo
 *  com.uslc.po.jpa.logic.UserType
 *  com.uslc.po.jpa.util.Constants
 *  com.uslc.po.jpa.util.UslcJpa
 *  org.apache.log4j.Logger
 *  org.apache.log4j.PropertyConfigurator
 *  org.eclipse.swt.events.FocusEvent
 *  org.eclipse.swt.events.FocusListener
 *  org.eclipse.swt.events.SelectionAdapter
 *  org.eclipse.swt.events.SelectionEvent
 *  org.eclipse.swt.events.SelectionListener
 *  org.eclipse.swt.events.TraverseEvent
 *  org.eclipse.swt.events.TraverseListener
 *  org.eclipse.swt.graphics.Device
 *  org.eclipse.swt.graphics.Font
 *  org.eclipse.swt.graphics.Image
 *  org.eclipse.swt.graphics.Rectangle
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Button
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.MessageBox
 *  org.eclipse.swt.widgets.Monitor
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Text
 */
package com.uslc.po.gui.client;

import com.ibm.icu.util.Calendar;
import com.uslc.po.gui.client.POClient;
import com.uslc.po.gui.master.POMaster;
import com.uslc.po.jpa.entity.Log;
import com.uslc.po.jpa.entity.User;
import com.uslc.po.jpa.logic.Actions;
import com.uslc.po.jpa.logic.Forms;
import com.uslc.po.jpa.logic.UserRepo;
import com.uslc.po.jpa.logic.UserType;
import com.uslc.po.jpa.util.Constants;
import com.uslc.po.jpa.util.UslcJpa;
import java.awt.Toolkit;
import java.io.PrintStream;
import java.util.Date;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class POLogin {
    private Display display = null;
    private Shell shell = null;
    private Monitor monitor = null;
    private Label formTitle = null;
    private Label userLabel = null;
    private Label passwordLabel = null;
    private Text userInput = null;
    private Text passwordInput = null;
    private Button loginButton = null;
    private Button cancelButton = null;
    private Logger log = null;

    public POLogin() {
        this.init();
    }

    private void init() {
        this.getFormTitle();
        this.getUserLabel();
        this.getUserInput();
        this.getPasswordLabel();
        this.getPasswordInput();
        this.getLoginButton();
        this.getCancelButton();
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
            this.shell = new Shell(this.getDisplay(), 64);
            this.shell.setLayout((Layout)new GridLayout(4, false));
            this.shell.setTouchEnabled(true);
            this.shell.setText("USLC Apparel - login");
            this.shell.setSize(260, 220);
            this.shell.setImage(new Image((Device)this.getDisplay(), "images/login.png"));
            Rectangle bounds = this.getMonitor().getBounds();
            Rectangle rect = this.getShell().getBounds();
            int x = bounds.x + (bounds.width - rect.width) / 2;
            int y = bounds.y + (bounds.height - rect.height) / 2;
            this.getShell().setLocation(x, y);
        }
        return this.shell;
    }

    public Label getFormTitle() {
        if (this.formTitle == null) {
            this.formTitle = new Label((Composite)this.getShell(), 0);
            this.formTitle.setText("purchase order login");
            this.formTitle.setAlignment(16384);
            this.formTitle.setFont(new Font((Device)this.getDisplay(), "Calibri", 12, 0));
            GridData data = new GridData(768);
            data.horizontalSpan = 4;
            this.formTitle.setLayoutData((Object)data);
            new Label((Composite)this.getShell(), 258).setLayoutData((Object)data);
        }
        return this.formTitle;
    }

    public Label getUserLabel() {
        if (this.userLabel == null) {
            this.userLabel = new Label((Composite)this.getShell(), 0);
            this.userLabel.setText("username:");
            this.userLabel.setFont(new Font((Device)this.getDisplay(), "Calibri", 10, 0));
            this.userLabel.setAlignment(131072);
            GridData gd = new GridData(768);
            gd.widthHint = 70;
            this.userLabel.setLayoutData((Object)gd);
            GridData data = new GridData(1040);
            data.verticalSpan = 2;
            data.widthHint = 10;
            new Label((Composite)this.getShell(), 514).setLayoutData((Object)data);
        }
        return this.userLabel;
    }

    public Label getPasswordLabel() {
        if (this.passwordLabel == null) {
            this.passwordLabel = new Label((Composite)this.getShell(), 0);
            this.passwordLabel.setText("password:");
            this.passwordLabel.setFont(new Font((Device)this.getDisplay(), "Calibri", 10, 0));
            this.passwordLabel.setAlignment(131072);
            GridData gd = new GridData(768);
            gd.widthHint = 70;
            this.passwordLabel.setLayoutData((Object)gd);
        }
        return this.passwordLabel;
    }

    public Text getUserInput() {
        if (this.userInput == null) {
            this.userInput = new Text((Composite)this.getShell(), 2048);
            this.userInput.setText("Admin");
            this.userInput.addTraverseListener(new TraverseListener(){

                public void keyTraversed(TraverseEvent event) {
                    if (event.detail == 4) {
                        POLogin.this.makeLogin();
                    }
                }
            });
            this.userInput.addFocusListener(new FocusListener(){

                public void focusLost(FocusEvent arg0) {
                }

                public void focusGained(FocusEvent arg0) {
                    POLogin.this.userInput.selectAll();
                }
            });
            GridData gd = new GridData(768);
            gd.horizontalSpan = 2;
            gd.grabExcessHorizontalSpace = true;
            gd.widthHint = 150;
            this.userInput.setLayoutData((Object)gd);
        }
        return this.userInput;
    }

    public Text getPasswordInput() {
        if (this.passwordInput == null) {
            this.passwordInput = new Text((Composite)this.getShell(), 4196352);
            this.passwordInput.setText("guacalito");
            this.passwordInput.addTraverseListener(new TraverseListener(){

                public void keyTraversed(TraverseEvent event) {
                    if (event.detail == 4) {
                        POLogin.this.makeLogin();
                    }
                }
            });
            this.passwordInput.addFocusListener(new FocusListener(){

                public void focusLost(FocusEvent arg0) {
                }

                public void focusGained(FocusEvent arg0) {
                    POLogin.this.passwordInput.selectAll();
                }
            });
            GridData gd = new GridData(768);
            gd.horizontalSpan = 2;
            gd.grabExcessHorizontalSpace = true;
            gd.widthHint = 150;
            this.passwordInput.setLayoutData((Object)gd);
        }
        return this.passwordInput;
    }

    public Button getLoginButton() {
        if (this.loginButton == null) {
            this.loginButton = new Button((Composite)this.getShell(), 8);
            this.loginButton.setText("login");
            this.loginButton.setFont(new Font((Device)this.getDisplay(), "Calibri", 10, 0));
            this.loginButton.setImage(new Image((Device)this.getDisplay(), "images/go.png"));
            GridData data = new GridData(768);
            data.horizontalSpan = 2;
            data.widthHint = 100;
            this.loginButton.setLayoutData((Object)data);
            this.loginButton.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent arg0) {
                    POLogin.this.makeLogin();
                }
            });
        }
        return this.loginButton;
    }

    public void makeLogin() {
        block10 : {
            String username = "";
            String password = "";
            username = this.getUserInput().getText().trim();
            password = this.getPasswordInput().getText().trim();
            User user = null;
            try {
                user = UserRepo.findUser((String)username, (String)password);
                if (user == null) {
                    MessageBox msg = new MessageBox(this.getShell(), 1);
                    msg.setText(Constants.MESSAGE_BOX_DIAG_TITLE.toString());
                    msg.setMessage("username or password invalid, please try again.");
                    msg.open();
                    break block10;
                }
                Log newLog = new Log();
                newLog.setActionId(Actions.LOGIN.getId());
                newLog.setFormId(Forms.LOGIN.getId());
                newLog.setDescription("user log in success");
                newLog.setTimestamp(Calendar.getInstance().getTime());
                newLog.setUser(user);
                try {
                    UslcJpa jpa = new UslcJpa();
                    if (user.getId() > 0) {
                        jpa.persist((Object)newLog);
                    } else {
                        newLog = (Log)jpa.merge((Object)newLog);
                    }
                    user = newLog.getUser();
                }
                catch (Exception e) {
                    this.getLog().error((Object)"error trying to create a log", (Throwable)e);
                }
                if (user.getUserType() == UserType.ADMIN.getId()) {
                    POMaster master = new POMaster(this.getShell(), this.getDisplay(), user);
                    this.getShell().dispose();
                } else if (user.getUserType() == UserType.CLIENT.getId()) {
                    POClient client = new POClient(this.getShell(), this.getDisplay(), user);
                    this.getShell().dispose();
                }
            }
            catch (Exception e1) {
                MessageBox msg = new MessageBox(this.getShell(), 1);
                msg.setText(Constants.MESSAGE_BOX_DIAG_TITLE.toString());
                msg.setMessage(e1.getMessage());
                msg.open();
                this.getLog().error((Object)"error", (Throwable)e1);
            }
        }
    }

    public Button getCancelButton() {
        if (this.cancelButton == null) {
            this.cancelButton = new Button((Composite)this.getShell(), 8);
            this.cancelButton.setText("cancel");
            this.cancelButton.setFont(new Font((Device)this.getDisplay(), "Calibri", 10, 0));
            this.cancelButton.setImage(new Image((Device)this.getDisplay(), "images/login_cancel.png"));
            GridData data = new GridData(768);
            data.horizontalSpan = 2;
            data.widthHint = 150;
            this.cancelButton.setLayoutData((Object)data);
            this.cancelButton.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent arg0) {
                    Toolkit.getDefaultToolkit().beep();
                    POLogin.this.getShell().dispose();
                    System.exit(0);
                }
            });
        }
        return this.cancelButton;
    }

    private Logger getLog() {
        if (this.log == null) {
            this.log = Logger.getLogger((Class)POLogin.class);
            PropertyConfigurator.configure((String)"log4j.properties");
        }
        return this.log;
    }

    public static void main(String[] args) {
        System.out.println("Starting Purchase Oder Client App");
        POLogin pocl = new POLogin();
    }

}

