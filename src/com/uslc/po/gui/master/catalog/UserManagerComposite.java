/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  com.ibm.icu.util.Calendar
 *  com.uslc.po.jpa.entity.User
 *  com.uslc.po.jpa.logic.UserRepo
 *  com.uslc.po.jpa.logic.UserType
 *  com.uslc.po.jpa.util.Constants
 *  com.uslc.po.jpa.util.Encryptor
 *  com.uslc.po.jpa.util.UslcJpa
 *  org.apache.log4j.Logger
 *  org.apache.log4j.PropertyConfigurator
 *  org.eclipse.swt.events.MouseAdapter
 *  org.eclipse.swt.events.MouseEvent
 *  org.eclipse.swt.events.MouseListener
 *  org.eclipse.swt.events.SelectionAdapter
 *  org.eclipse.swt.events.SelectionEvent
 *  org.eclipse.swt.events.SelectionListener
 *  org.eclipse.swt.graphics.Color
 *  org.eclipse.swt.graphics.Device
 *  org.eclipse.swt.layout.FormData
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Button
 *  org.eclipse.swt.widgets.Combo
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Display
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

import com.ibm.icu.util.Calendar;
import com.uslc.po.gui.master.MasterCenterComposite;
import com.uslc.po.gui.master.NewPurchaseOrderComposite;
import com.uslc.po.gui.master.POMaster;
import com.uslc.po.gui.util.MyGridData;
import com.uslc.po.jpa.entity.User;
import com.uslc.po.jpa.logic.UserRepo;
import com.uslc.po.jpa.logic.UserType;
import com.uslc.po.jpa.util.Constants;
import com.uslc.po.jpa.util.Encryptor;
import com.uslc.po.jpa.util.UslcJpa;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class UserManagerComposite
extends Composite {
    private MasterCenterComposite parent = null;
    private Logger log = null;
    private final String infoAddText = "Info: Add a new user";
    private Label titleLbl = null;
    private Table usersTbl = null;
    private Label infoLbl = null;
    private Label firstNameLbl = null;
    private Text firstNameTxt = null;
    private Label lastNameLbl = null;
    private Text lastNameTxt = null;
    private Label userNameLbl = null;
    private Text userNameTxt = null;
    private Label passwordLbl = null;
    private Text passwordTxt = null;
    private Label timestampLbl = null;
    private Text timestampTxt = null;
    private Label userTypeLbl = null;
    private Combo userTypeCbx = null;
    private Label enabledLbl = null;
    private Button enabledBtn = null;
    private Button actionBtn = null;
    private Button cancelBtn = null;
    private GridData valuesGd = null;
    private boolean editing = false;
    private User selectedUser = null;
    private SimpleDateFormat sdf = null;

    public UserManagerComposite(MasterCenterComposite composite) {
        super((Composite)composite.getMaster().getHiddenShell(), 0);
        this.parent = composite;
        this.initComposite();
    }

    private void initComposite() {
        FormData data = new FormData(470, 350);
        this.setLayoutData((Object)data);
        this.setLayout((Layout)new GridLayout(4, false));
        this.getTitleLbl();
        this.getUsersTbl();
        this.getInfoLbl();
        this.getFirstNameLbl();
        this.getFirstNameTxt();
        this.getLastNameLbl();
        this.getLastNameTxt();
        this.getUserNameLbl();
        this.getUserNameTxt();
        this.getPasswordLbl();
        this.getPasswordTxt();
        this.getTimestampLbl();
        this.getTimestampTxt();
        this.getUserTypeLbl();
        this.getUserTypeCbx();
        this.getEnabledLbl();
        this.getEnabledBtn();
        this.getActionBtn();
        this.getCancelBtn();
        this.loadValues();
    }

    public Label getTitleLbl() {
        if (this.titleLbl == null) {
            this.titleLbl = new Label((Composite)this, 0);
            this.titleLbl.setText("users");
            GridData gd = new GridData(768);
            gd.horizontalSpan = 4;
            this.titleLbl.setLayoutData((Object)gd);
            Label horizontalLine = new Label((Composite)this, 258);
            horizontalLine.setLayoutData((Object)gd);
        }
        return this.titleLbl;
    }

    public Table getUsersTbl() {
        if (this.usersTbl == null) {
            this.usersTbl = new Table((Composite)this, 4);
            TableColumn id = new TableColumn(this.usersTbl, 0);
            TableColumn userName = new TableColumn(this.usersTbl, 0);
            id.setText("id");
            userName.setText("name");
            id.setWidth(30);
            userName.setWidth(100);
            this.usersTbl.setHeaderVisible(true);
            GridData gd = new GridData(1040);
            gd.grabExcessVerticalSpace = true;
            gd.verticalSpan = 9;
            this.usersTbl.setLayoutData((Object)gd);
            this.usersTbl.addMouseListener((MouseListener)new MouseAdapter(){

                public void mouseDoubleClick(MouseEvent arg0) {
                    UserManagerComposite.this.setEditMode();
                }
            });
            Label verticalLine = new Label((Composite)this, 514);
            GridData data1 = new GridData(1040);
            data1.verticalSpan = 9;
            data1.widthHint = 15;
            verticalLine.setLayoutData((Object)data1);
        }
        return this.usersTbl;
    }

    private void setEditMode() {
        TableItem[] items = this.getUsersTbl().getSelection();
        User user = null;
        TableItem[] arrtableItem = items;
        int n = arrtableItem.length;
        int n2 = 0;
        while (n2 < n) {
            TableItem tableItem = arrtableItem[n2];
            user = (User)tableItem.getData();
            ++n2;
        }
        if (user != null) {
            this.editing = true;
            this.selectedUser = user;
            this.getInfoLbl().setText("USER[" + user.getUsername() + "] - UPDATE");
            this.getInfoLbl().setAlignment(131072);
            this.getEnabledBtn().setSelection(user.isEnabled());
            this.getFirstNameTxt().setText(user.getFirstName());
            this.getLastNameTxt().setText(user.getLastName());
            this.getPasswordTxt().setText(new Encryptor("").decrypt(user.getPassword()));
            this.getTimestampTxt().setText(this.getSdf().format(user.getTimestamp()));
            int i = 0;
            while (i < this.getUserTypeCbx().getItemCount()) {
                if (((UserType)this.getUserTypeCbx().getData(this.getUserTypeCbx().getItem(i))).getId() == user.getUserType()) {
                    this.getUserTypeCbx().select(i);
                    break;
                }
                ++i;
            }
            this.getUserNameTxt().setText(user.getUsername());
            this.getActionBtn().setText("update");
        } else {
            this.editing = false;
            this.clean();
        }
    }

    public Label getInfoLbl() {
        if (this.infoLbl == null) {
            this.infoLbl = new Label((Composite)this, 0);
            this.infoLbl.setText("Info: Add a new user");
            this.infoLbl.setLayoutData((Object)MyGridData.getDgHorizontalDoubleSpan());
            Label horizontalLine = new Label((Composite)this, 258);
            horizontalLine.setLayoutData((Object)MyGridData.getDgHorizontalDoubleSpan());
        }
        return this.infoLbl;
    }

    public Label getFirstNameLbl() {
        if (this.firstNameLbl == null) {
            this.firstNameLbl = new Label((Composite)this, 0);
            this.firstNameLbl.setText("first name:");
        }
        return this.firstNameLbl;
    }

    public Text getFirstNameTxt() {
        if (this.firstNameTxt == null) {
            this.firstNameTxt = new Text((Composite)this, 0);
            this.firstNameTxt.setLayoutData((Object)this.getValuesGd());
        }
        return this.firstNameTxt;
    }

    public Label getLastNameLbl() {
        if (this.lastNameLbl == null) {
            this.lastNameLbl = new Label((Composite)this, 0);
            this.lastNameLbl.setText("last name");
        }
        return this.lastNameLbl;
    }

    public Text getLastNameTxt() {
        if (this.lastNameTxt == null) {
            this.lastNameTxt = new Text((Composite)this, 0);
            this.lastNameTxt.setLayoutData((Object)this.getValuesGd());
        }
        return this.lastNameTxt;
    }

    public Label getUserNameLbl() {
        if (this.userNameLbl == null) {
            this.userNameLbl = new Label((Composite)this, 0);
            this.userNameLbl.setText("username:");
        }
        return this.userNameLbl;
    }

    public Text getUserNameTxt() {
        if (this.userNameTxt == null) {
            this.userNameTxt = new Text((Composite)this, 0);
            this.userNameTxt.setLayoutData((Object)this.getValuesGd());
        }
        return this.userNameTxt;
    }

    public Label getPasswordLbl() {
        if (this.passwordLbl == null) {
            this.passwordLbl = new Label((Composite)this, 0);
            this.passwordLbl.setText("password:");
        }
        return this.passwordLbl;
    }

    public Text getPasswordTxt() {
        if (this.passwordTxt == null) {
            this.passwordTxt = new Text((Composite)this, 4194304);
            this.passwordTxt.setLayoutData((Object)this.getValuesGd());
        }
        return this.passwordTxt;
    }

    public Label getTimestampLbl() {
        if (this.timestampLbl == null) {
            this.timestampLbl = new Label((Composite)this, 0);
            this.timestampLbl.setText("timestamp:");
        }
        return this.timestampLbl;
    }

    public Text getTimestampTxt() {
        if (this.timestampTxt == null) {
            this.timestampTxt = new Text((Composite)this, 8);
            this.timestampTxt.setLayoutData((Object)this.getValuesGd());
        }
        return this.timestampTxt;
    }

    public Label getUserTypeLbl() {
        if (this.userTypeLbl == null) {
            this.userTypeLbl = new Label((Composite)this, 0);
            this.userTypeLbl.setText("type:");
        }
        return this.userTypeLbl;
    }

    public Combo getUserTypeCbx() {
        if (this.userTypeCbx == null) {
            this.userTypeCbx = new Combo((Composite)this, 12);
            this.userTypeCbx.setLayoutData((Object)this.getValuesGd());
            UserType[] arruserType = UserType.values();
            int n = arruserType.length;
            int n2 = 0;
            while (n2 < n) {
                UserType ut = arruserType[n2];
                this.userTypeCbx.add(ut.toString());
                this.userTypeCbx.setData(ut.toString(), (Object)ut);
                ++n2;
            }
        }
        return this.userTypeCbx;
    }

    public Label getEnabledLbl() {
        if (this.enabledLbl == null) {
            this.enabledLbl = new Label((Composite)this, 0);
            this.enabledLbl.setText("enabled:");
        }
        return this.enabledLbl;
    }

    public Button getEnabledBtn() {
        if (this.enabledBtn == null) {
            this.enabledBtn = new Button((Composite)this, 32);
            GridData gd = new GridData(768);
            gd.horizontalSpan = 4;
            Label horizontalLine = new Label((Composite)this, 258);
            horizontalLine.setLayoutData((Object)gd);
        }
        return this.enabledBtn;
    }

    public Button getActionBtn() {
        if (this.actionBtn == null) {
            this.actionBtn = new Button((Composite)this, 8);
            this.actionBtn.setText("add");
            this.actionBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent e) {
                    try {
                        UserManagerComposite.this.performAction();
                    }
                    catch (Exception e1) {
                        UserManagerComposite.this.getLog().error((Object)"error", (Throwable)e1);
                    }
                }
            });
            GridData gd = new GridData(16777216, 16777216, true, false);
            gd.grabExcessHorizontalSpace = true;
            gd.widthHint = 70;
            gd.horizontalSpan = 2;
            this.actionBtn.setLayoutData((Object)gd);
        }
        return this.actionBtn;
    }

    private void performAction() throws Exception {
        User user = null;
        String firstName = this.getFirstNameTxt().getText();
        String lastName = this.getLastNameTxt().getText();
        String username = this.getUserNameTxt().getText().trim();
        String password = new Encryptor("").encrypt(this.getPasswordTxt().getText().trim());
        int ut = ((UserType)this.getUserTypeCbx().getData(this.getUserTypeCbx().getItem(this.getUserTypeCbx().getSelectionIndex()))).getId();
        boolean active = false;
        boolean enabled = this.getEnabledBtn().getSelection();
        if (this.editing) {
            user = this.selectedUser;
        } else {
            user = new User();
            user.setActive(active);
            user.setTimestamp(Calendar.getInstance().getTime());
        }
        user.setEnabled(enabled);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        user.setUserType(ut);
        user.setUsername(username);
        int style = 2;
        MessageBox diag = new MessageBox(this.getShell(), style);
        diag.setText(Constants.MESSAGE_BOX_DIAG_TITLE.toString());
        String errorMsg = "there was a problem adding the user";
        String successMsg = "user added/modified correctly.";
        UslcJpa jpa = new UslcJpa();
        if (jpa.persist((Object)user)) {
            diag.setMessage(successMsg);
            this.clean();
            this.loadValues();
        } else {
            style = 1;
            diag.setMessage(errorMsg);
        }
        diag.open();
    }

    public Button getCancelBtn() {
        if (this.cancelBtn == null) {
            this.cancelBtn = new Button((Composite)this, 8);
            this.cancelBtn.setText("cancel");
            this.cancelBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent e) {
                    if (UserManagerComposite.this.editing) {
                        UserManagerComposite.this.clean();
                    } else {
                        UserManagerComposite.this.hide();
                    }
                }
            });
            GridData gd = new GridData(16777216, 16777216, true, false);
            gd.grabExcessHorizontalSpace = true;
            gd.horizontalAlignment = 64;
            gd.widthHint = 70;
            gd.horizontalSpan = 2;
            this.cancelBtn.setLayoutData((Object)gd);
        }
        return this.cancelBtn;
    }

    public void hide() {
        this.clean();
        this.setParent((Composite)this.getParent().getMaster().getHiddenShell());
        this.setVisible(false);
    }

    private void clean() {
        this.editing = false;
        this.selectedUser = null;
        this.getInfoLbl().setText("Info: Add a new user");
        this.getInfoLbl().setAlignment(16384);
        this.getEnabledBtn().setSelection(false);
        this.getFirstNameTxt().setText("");
        this.getLastNameTxt().setText("");
        this.getPasswordTxt().setText("");
        this.getTimestampTxt().setText(this.getSdf().format(Calendar.getInstance().getTime()));
        this.getUserTypeCbx().select(-1);
        this.getUserNameTxt().setText("");
        this.getActionBtn().setText("add");
    }

    private Logger getLog() {
        if (this.log == null) {
            this.log = Logger.getLogger((Class)NewPurchaseOrderComposite.class);
            PropertyConfigurator.configure((String)"log4j.properties");
        }
        return this.log;
    }

    public MasterCenterComposite getParent() {
        return this.parent;
    }

    public GridData getValuesGd() {
        if (this.valuesGd == null) {
            this.valuesGd = new GridData(768);
            this.valuesGd.grabExcessHorizontalSpace = true;
        }
        return this.valuesGd;
    }

    public void loadValues() {
        this.getUsersTbl().removeAll();
        for (User user : UserRepo.findAll()) {
            TableItem item = new TableItem(this.getUsersTbl(), 0);
            if (!user.isEnabled()) {
                item.setBackground(new Color((Device)this.getDisplay(), 255, 224, 237));
            }
            String[] texts = new String[]{String.valueOf(user.getId()), String.valueOf(user.getFirstName()) + " " + user.getLastName()};
            item.setData((Object)user);
            item.setText(texts);
        }
        this.getTimestampTxt().setText(this.getSdf().format(Calendar.getInstance().getTime()));
    }

    public SimpleDateFormat getSdf() {
        if (this.sdf == null) {
            this.sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        return this.sdf;
    }

}

