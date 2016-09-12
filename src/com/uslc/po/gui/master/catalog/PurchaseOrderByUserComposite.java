/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  com.mysql.jdbc.exceptions.jdbc4.CommunicationsException
 *  com.uslc.po.jpa.entity.PurchaseOrder
 *  com.uslc.po.jpa.entity.PurchaseOrderByUser
 *  com.uslc.po.jpa.entity.User
 *  com.uslc.po.jpa.logic.PurchaseOrderRepo
 *  com.uslc.po.jpa.logic.UserRepo
 *  com.uslc.po.jpa.util.Constants
 *  com.uslc.po.jpa.util.UslcJpa
 *  org.apache.log4j.Logger
 *  org.apache.log4j.PropertyConfigurator
 *  org.eclipse.swt.events.SelectionAdapter
 *  org.eclipse.swt.events.SelectionEvent
 *  org.eclipse.swt.events.SelectionListener
 *  org.eclipse.swt.layout.FormData
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Button
 *  org.eclipse.swt.widgets.Combo
 *  org.eclipse.swt.widgets.Composite
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

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.uslc.po.gui.master.MasterCenterComposite;
import com.uslc.po.gui.master.POMaster;
import com.uslc.po.jpa.entity.PurchaseOrder;
import com.uslc.po.jpa.entity.PurchaseOrderByUser;
import com.uslc.po.jpa.entity.User;
import com.uslc.po.jpa.logic.PurchaseOrderRepo;
import com.uslc.po.jpa.logic.UserRepo;
import com.uslc.po.jpa.util.Constants;
import com.uslc.po.jpa.util.UslcJpa;
import java.net.ConnectException;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class PurchaseOrderByUserComposite
extends Composite {
    private MasterCenterComposite parent = null;
    private Combo userCbx;
    private Table purchaseOrderTbl;
    private Label label;
    private Table userTable;
    private Label lblUser;
    private Text poSearchTxt;
    private Label lblNewLabel_1;
    private Button cancelBtn;
    private Label label_3;
    private Logger log = null;
    private Button btnAdd;
    private Button btnDel;
    private TableColumn tblclmnPo;
    private TableColumn tblclmnUser;
    private TableColumn tblclmnUsers;
    private TableColumn tblclmnPos;
    private UslcJpa jpa = null;

    public PurchaseOrderByUserComposite(MasterCenterComposite composite) {
        super((Composite)composite.getMaster().getHiddenShell(), 0);
        this.parent = composite;
        this.init();
        this.loadValues();
    }

    private void loadValues() {
        List purchaseOrderList = PurchaseOrderRepo.findAll();
        for (PurchaseOrder po : purchaseOrderList) {
            TableItem item = new TableItem(this.getPurchaseOrderTbl(), 8);
            int users = 0;
            for (PurchaseOrderByUser pobu : po.getPurchaseOrders()) {
                if (pobu.getDeleted()) continue;
                ++users;
            }
            item.setText(new String[]{po.getReferenceNumber(), String.valueOf(users)});
            item.setData((Object)po);
        }
        try {
            List userList = UserRepo.findAllClients((boolean)true);
            for (User user : userList) {
                String userStr = String.valueOf(user.getFirstName()) + " " + user.getLastName();
                this.getUserCbx().add(userStr);
                this.getUserCbx().setData(userStr, (Object)user);
            }
        }
        catch (CommunicationsException e) {
            this.getLog().error((Object)"error loading clients", (Throwable)e);
        }
        catch (ConnectException e) {
            e.printStackTrace();
        }
    }

    public PurchaseOrderByUserComposite(Composite parent, int style) {
        super(parent, style);
        this.init();
        this.loadValues();
    }

    private void addUser() throws Exception {
        block8 : {
            PurchaseOrder po = null;
            User user = (User)this.getUserCbx().getData(this.getUserCbx().getItem(this.getUserCbx().getSelectionIndex()));
            PurchaseOrderByUser newPobu = null;
            TableItem[] items = this.getPurchaseOrderTbl().getSelection();
            TableItem selectedItem = null;
            int i = 0;
            while (i < items.length) {
                selectedItem = items[i];
                po = (PurchaseOrder)selectedItem.getData();
                ++i;
            }
            if (po != null && user != null) {
                for (PurchaseOrderByUser pobu : po.getPurchaseOrders()) {
                    if (pobu.getUser().getId() != user.getId()) continue;
                    newPobu = pobu;
                    break;
                }
                if (newPobu == null) {
                    newPobu = new PurchaseOrderByUser();
                    newPobu.setUser(user);
                    newPobu.setPurchaseOrder(po);
                    po.addpurchaseOrder(newPobu);
                }
                newPobu.setDeleted(false);
                newPobu = (PurchaseOrderByUser)this.getJpa().merge((Object)newPobu);
                if (newPobu.getId() > 0 && !newPobu.getDeleted()) {
                    MessageBox box = new MessageBox(this.getShell(), 2);
                    box.setText(Constants.MESSAGE_BOX_DIAG_TITLE.toString());
                    box.setMessage("po[" + po.getReferenceNumber() + "] assigned to " + user.getFirstName() + " " + user.getLastName());
                    box.open();
                    int users = 0;
                    for (PurchaseOrderByUser pobu2 : po.getPurchaseOrders()) {
                        if (pobu2.getDeleted()) continue;
                        ++users;
                    }
                    selectedItem.setText(new String[]{po.getReferenceNumber(), String.valueOf(users)});
                    selectedItem.setData((Object)po);
                    this.loadPurchaseOrderUsers();
                    break block8;
                }
                throw new Exception("error trying to assign the po to a user, please contact your sysadmin");
            }
            throw new Exception("user[" + (Object)user + "] - po[" + (Object)po + "]");
        }
    }

    private void loadPurchaseOrderUsers() {
        this.getUserTable().removeAll();
        TableItem[] items = this.getPurchaseOrderTbl().getSelection();
        PurchaseOrder po = null;
        TableItem[] arrtableItem = items;
        int n = arrtableItem.length;
        int n2 = 0;
        while (n2 < n) {
            TableItem item = arrtableItem[n2];
            po = (PurchaseOrder)item.getData();
            ++n2;
        }
        if (po != null) {
            for (PurchaseOrderByUser pobu : po.getPurchaseOrders()) {
                if (pobu.getDeleted()) continue;
                User user = pobu.getUser();
                int pos = 0;
                for (PurchaseOrderByUser p : user.getPurchaseOrders()) {
                    if (p.getDeleted()) continue;
                    ++pos;
                }
                TableItem item = new TableItem(this.getUserTable(), 8);
                item.setText(new String[]{String.valueOf(user.getFirstName()) + " " + user.getLastName(), String.valueOf(pos)});
                item.setData((Object)pobu);
            }
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void delUser() throws Exception {
        TableItem[] poItems;
        TableItem[] items = this.getUserTable().getSelection();
        PurchaseOrderByUser pobu = null;
        TableItem[] arrtableItem = items;
        int n = arrtableItem.length;
        int n2 = 0;
        while (n2 < n) {
            TableItem item = arrtableItem[n2];
            pobu = (PurchaseOrderByUser)item.getData();
            ++n2;
        }
        if (pobu == null) throw new Exception("please select a user from the users table first");
        pobu.setDeleted(true);
        if (!this.getJpa().persist((Object)pobu)) throw new Exception("there was an error trying to persist the change, please contact your sysadmin");
        MessageBox box = new MessageBox(this.getShell(), 2);
        box.setText(Constants.MESSAGE_BOX_DIAG_TITLE.toString());
        box.setMessage("po[" + pobu.getPurchaseOrder().getReferenceNumber() + "] unassigned to " + pobu.getUser().getFirstName() + " " + pobu.getUser().getLastName());
        box.open();
        TableItem[] arrtableItem2 = poItems = this.getPurchaseOrderTbl().getSelection();
        int n3 = arrtableItem2.length;
        int n4 = 0;
        while (n4 < n3) {
            TableItem item = arrtableItem2[n4];
            PurchaseOrder po = (PurchaseOrder)item.getData();
            if (po.getId() == pobu.getPurchaseOrder().getId()) {
                int users = 0;
                for (PurchaseOrderByUser pu : po.getPurchaseOrders()) {
                    if (pu.getDeleted()) continue;
                    ++users;
                }
                item.setText(new String[]{po.getReferenceNumber(), String.valueOf(users)});
                item.setData((Object)po);
                break;
            }
            ++n4;
        }
        this.loadPurchaseOrderUsers();
    }

    public void init() {
        FormData data = new FormData(550, 400);
        this.setLayoutData((Object)data);
        this.setLayout((Layout)new GridLayout(6, false));
        this.getLog().info((Object)"init method called");
        this.lblNewLabel_1 = new Label((Composite)this, 0);
        this.lblNewLabel_1.setLayoutData((Object)new GridData(16384, 16777216, false, false, 5, 1));
        this.lblNewLabel_1.setText("purchase order assigner");
        new org.eclipse.swt.widgets.Label((Composite)this, 0);
        Label label_2 = new Label((Composite)this, 258);
        label_2.setLayoutData((Object)new GridData(4, 16777216, true, false, 6, 1));
        this.lblUser = new Label((Composite)this, 0);
        this.lblUser.setLayoutData((Object)new GridData(131072, 16777216, false, false, 1, 1));
        this.lblUser.setText("po:");
        this.poSearchTxt = new Text((Composite)this, 2048);
        this.poSearchTxt.setLayoutData((Object)new GridData(4, 16777216, true, false, 1, 1));
        new org.eclipse.swt.widgets.Label((Composite)this, 0);
        Label lblNewLabel = new Label((Composite)this, 0);
        lblNewLabel.setLayoutData((Object)new GridData(131072, 16777216, false, false, 1, 1));
        lblNewLabel.setText("user:");
        this.userCbx = new Combo((Composite)this, 8);
        this.userCbx.setLayoutData((Object)new GridData(4, 16777216, true, false, 1, 1));
        this.btnAdd = new Button((Composite)this, 0);
        this.btnAdd.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                try {
                    PurchaseOrderByUserComposite.this.addUser();
                }
                catch (Exception e1) {
                    PurchaseOrderByUserComposite.this.getLog().error((Object)"error while trying to add an user", (Throwable)e1);
                    MessageBox box = new MessageBox(PurchaseOrderByUserComposite.this.getShell(), 1);
                    box.setText(Constants.MESSAGE_BOX_DIAG_TITLE.toString());
                    box.setMessage(e1.getMessage());
                    box.open();
                }
            }
        });
        this.btnAdd.setText("add");
        this.purchaseOrderTbl = new Table((Composite)this, 67584);
        this.purchaseOrderTbl.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                PurchaseOrderByUserComposite.this.loadPurchaseOrderUsers();
            }
        });
        this.purchaseOrderTbl.setLayoutData((Object)new GridData(4, 4, true, true, 2, 1));
        this.purchaseOrderTbl.setHeaderVisible(true);
        this.purchaseOrderTbl.setLinesVisible(true);
        this.tblclmnPo = new TableColumn(this.purchaseOrderTbl, 0);
        this.tblclmnPo.setText("po");
        this.tblclmnPo.setWidth(120);
        this.tblclmnUsers = new TableColumn(this.purchaseOrderTbl, 0);
        this.tblclmnUsers.setWidth(50);
        this.tblclmnUsers.setText("users");
        this.label = new Label((Composite)this, 0);
        this.label.setLayoutData((Object)new GridData(16777216, 16777216, false, false, 1, 1));
        this.label.setText("->");
        this.userTable = new Table((Composite)this, 67584);
        this.userTable.setLayoutData((Object)new GridData(4, 4, true, true, 2, 1));
        this.userTable.setHeaderVisible(true);
        this.userTable.setLinesVisible(false);
        this.tblclmnUser = new TableColumn(this.userTable, 0);
        this.tblclmnUser.setWidth(120);
        this.tblclmnUser.setText("user");
        this.tblclmnPos = new TableColumn(this.userTable, 0);
        this.tblclmnPos.setWidth(40);
        this.tblclmnPos.setText("pos");
        this.btnDel = new Button((Composite)this, 0);
        this.btnDel.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                try {
                    PurchaseOrderByUserComposite.this.delUser();
                }
                catch (Exception e1) {
                    PurchaseOrderByUserComposite.this.getLog().error((Object)"error while trying to delete an user", (Throwable)e1);
                    MessageBox box = new MessageBox(PurchaseOrderByUserComposite.this.getShell(), 1);
                    box.setText(Constants.MESSAGE_BOX_DIAG_TITLE.toString());
                    box.setMessage(e1.getMessage());
                    box.open();
                }
            }
        });
        this.btnDel.setLayoutData((Object)new GridData(4, 128, false, false, 1, 1));
        this.btnDel.setText("del");
        this.label_3 = new Label((Composite)this, 258);
        this.label_3.setLayoutData((Object)new GridData(4, 16777216, true, false, 6, 1));
        this.cancelBtn = new Button((Composite)this, 0);
        this.cancelBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                PurchaseOrderByUserComposite.this.hide();
            }
        });
        this.cancelBtn.setLayoutData((Object)new GridData(16777216, 16777216, false, false, 6, 1));
        this.cancelBtn.setText("cancel");
    }

    public void hide() {
        this.setParent((Composite)this.getParent().getMaster().getHiddenShell());
        this.setVisible(false);
    }

    protected void checkSubclass() {
    }

    public Table getPurchaseOrderTbl() {
        return this.purchaseOrderTbl;
    }

    public Table getUserTable() {
        return this.userTable;
    }

    public Text getPoSearchTxt() {
        return this.poSearchTxt;
    }

    public Combo getUserCbx() {
        return this.userCbx;
    }

    public Button getCancelBtn() {
        return this.cancelBtn;
    }

    public MasterCenterComposite getParent() {
        return this.parent;
    }

    private UslcJpa getJpa() {
        if (this.jpa == null) {
            this.jpa = new UslcJpa();
        }
        return this.jpa;
    }

    private Logger getLog() {
        if (this.log == null) {
            this.log = Logger.getLogger((Class)PurchaseOrderByUserComposite.class);
            PropertyConfigurator.configure((String)"log4j.properties");
        }
        return this.log;
    }

}

