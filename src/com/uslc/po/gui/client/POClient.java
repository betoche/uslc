/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  com.jasperassistant.designer.viewer.ViewerComposite
 *  com.uslc.po.jpa.comparator.PackageDetailComparator
 *  com.uslc.po.jpa.entity.Carton
 *  com.uslc.po.jpa.entity.Color
 *  com.uslc.po.jpa.entity.Item
 *  com.uslc.po.jpa.entity.PackingDetail
 *  com.uslc.po.jpa.entity.PurchaseOrder
 *  com.uslc.po.jpa.entity.PurchaseOrderDetail
 *  com.uslc.po.jpa.entity.Size
 *  com.uslc.po.jpa.entity.Upc
 *  com.uslc.po.jpa.entity.User
 *  net.sf.jasperreports.engine.JRDataSource
 *  net.sf.jasperreports.engine.JREmptyDataSource
 *  net.sf.jasperreports.engine.JRException
 *  net.sf.jasperreports.engine.JRExporterParameter
 *  net.sf.jasperreports.engine.JasperCompileManager
 *  net.sf.jasperreports.engine.JasperFillManager
 *  net.sf.jasperreports.engine.JasperPrint
 *  net.sf.jasperreports.engine.JasperPrintManager
 *  net.sf.jasperreports.engine.JasperReport
 *  net.sf.jasperreports.engine.export.JRPrintServiceExporter
 *  net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter
 *  net.sf.jasperreports.engine.util.JRFontNotFoundException
 *  org.apache.log4j.Logger
 *  org.apache.log4j.PropertyConfigurator
 *  org.eclipse.swt.custom.ScrolledComposite
 *  org.eclipse.swt.events.SelectionAdapter
 *  org.eclipse.swt.events.SelectionEvent
 *  org.eclipse.swt.events.SelectionListener
 *  org.eclipse.swt.events.TraverseEvent
 *  org.eclipse.swt.events.TraverseListener
 *  org.eclipse.swt.graphics.Font
 *  org.eclipse.swt.graphics.Point
 *  org.eclipse.swt.layout.FormAttachment
 *  org.eclipse.swt.layout.FormData
 *  org.eclipse.swt.layout.FormLayout
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Button
 *  org.eclipse.swt.widgets.Combo
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Control
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.Group
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.MessageBox
 *  org.eclipse.swt.widgets.ProgressBar
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Spinner
 *  org.eclipse.swt.widgets.TabFolder
 *  org.eclipse.swt.widgets.TabItem
 *  org.eclipse.swt.widgets.Table
 *  org.eclipse.swt.widgets.TableColumn
 *  org.eclipse.swt.widgets.Text
 */
package com.uslc.po.gui.client;

import com.jasperassistant.designer.viewer.ViewerComposite;
import com.uslc.po.gui.util.Client;
import com.uslc.po.gui.util.ImageUtils;
import com.uslc.po.gui.util.Master;
import com.uslc.po.gui.util.PrintingUtils;
import com.uslc.po.jpa.comparator.PackageDetailComparator;
import com.uslc.po.jpa.entity.Carton;
import com.uslc.po.jpa.entity.Color;
import com.uslc.po.jpa.entity.Item;
import com.uslc.po.jpa.entity.PackingDetail;
import com.uslc.po.jpa.entity.PurchaseOrder;
import com.uslc.po.jpa.entity.PurchaseOrderDetail;
import com.uslc.po.jpa.entity.Size;
import com.uslc.po.jpa.entity.Upc;
import com.uslc.po.jpa.entity.User;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.print.PrintService;
import javax.print.attribute.Attribute;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.MediaSizeName;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.util.JRFontNotFoundException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class POClient
extends Shell {
    private Master master = null;
    private User user = null;
    private Client client = null;
    private Table poDetailTbl;
    private Table packingDetailTbl;
    private Combo purchaseOrderCbx;
    private Logger log = null;
    private Combo sizeCbx;
    private Combo colorCbx;
    private Combo itemCbx;
    private Table scannedItemsTbl;
    private Text scannedBarTxt;
    private ViewerComposite polybagViewer = null;
    private ViewerComposite ticketViewer = null;
    private JasperPrint jasperPrintTicket = null;
    private JasperPrint jasperPrintPolybag = null;
    private JasperReport noPreticketed = null;
    private JasperReport preticketed = null;
    private JasperReport polybag = null;
    private JasperReport blankTicket = null;
    private JasperReport blankPolybag = null;
    private int poDetailQty = 0;
    private Group grpTicketsComposite;
    private Combo printersCbx;
    private Group grpScanningInfo;
    private Spinner numberOfCopiesSpn;
    private ProgressBar progressBar;
    private Button savePrintingSettingsBtn;
    private Button autoPrintingChk;
    private Button printDialogChk;
    private Label infoUpcLbl;
    private Label barcodeLbl;
    private Label infoSizeLbl;
    private Label infoColorLbl;
    private Label infoItemLbl;
    private Label infoSkuLabel;
    private Button infoPreticketedCbx;
    private Label poDetailLbl;
    private Label packingDetailLbl;
    private Composite colorsComposite;
    private Group grpPoDetails;
    private Group grpPackingDetails;
    private Composite summaryScrolledComposite;
    private ScrolledComposite scrolledComposite;
    private Button btnCompleted;

    public User getUser() {
        return this.user;
    }

    public Client getClient() {
        if (this.client == null) {
            this.client = new Client(this, this.getUser());
        }
        return this.client;
    }

    public ViewerComposite getPolybagViewer() {
        if (this.polybagViewer == null) {
            this.polybagViewer = new ViewerComposite((Composite)this.getGrpTicketsComposite(), 0);
            GridData gd_ticketViewer = new GridData(4, 4, true, true, 1, 1);
            gd_ticketViewer.heightHint = 150;
            gd_ticketViewer.widthHint = 200;
            this.polybagViewer.setLayoutData((Object)gd_ticketViewer);
        }
        return this.polybagViewer;
    }

    public ViewerComposite getTicketViewer() {
        if (this.ticketViewer == null) {
            Composite printComposite = new Composite((Composite)this.grpTicketsComposite, 0);
            printComposite.setLayout((Layout)new GridLayout(1, false));
            GridData gd_printComposite = new GridData(131072, 4, true, true, 1, 1);
            gd_printComposite.widthHint = 90;
            printComposite.setLayoutData((Object)gd_printComposite);
            Button btnPrintSmall = new Button(printComposite, 0);
            btnPrintSmall.setLayoutData((Object)new GridData(16777216, 16777216, true, true, 1, 1));
            btnPrintSmall.setText("print small");
            Button btnPrintTicket = new Button(printComposite, 0);
            btnPrintTicket.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent e) {
                    POClient.this.getClient().printTicket();
                }
            });
            btnPrintTicket.setLayoutData((Object)new GridData(16777216, 16777216, true, true, 1, 1));
            btnPrintTicket.setText("print ticket");
            this.ticketViewer = new ViewerComposite((Composite)this.getGrpTicketsComposite(), 0);
            GridData gd_polybagViewer = new GridData(4, 4, true, true, 2, 1);
            gd_polybagViewer.heightHint = 200;
            gd_polybagViewer.widthHint = 300;
            this.ticketViewer.setLayoutData((Object)gd_polybagViewer);
        }
        return this.ticketViewer;
    }

    public JasperPrint getJasperPrintTicket(PackingDetail pd) throws JRException {
        HashMap<String, Object> hm = new HashMap<String, Object>();
        if (pd == null) {
            this.jasperPrintTicket = JasperFillManager.fillReport((JasperReport)this.getJasperReportBlankTicket(), hm, (JRDataSource)new JREmptyDataSource());
        } else {
            hm.put("carton", String.valueOf(pd.getSku()));
            hm.put("barcode", ImageUtils.getAwtBarcodeImage(pd.getPurchaseOrderDetail().getUpc().getUpcCode()));
            hm.put("dept", String.valueOf(pd.getPurchaseOrderDetail().getPurchaseOrder().getDepartmentNumber()));
            hm.put("item", String.valueOf(pd.getPurchaseOrderDetail().getUpc().getItem().getCode()));
            hm.put("color_number", String.valueOf(pd.getPurchaseOrderDetail().getUpc().getColor().getNumber()));
            String size = "";
            size = pd.getPurchaseOrderDetail().getPurchaseOrder().getReferenceNumber().endsWith("0011") ? String.valueOf(pd.getPurchaseOrderDetail().getUpc().getSize().getWaist()) : String.valueOf(String.valueOf(pd.getPurchaseOrderDetail().getUpc().getSize().getWaist()) + "x" + pd.getPurchaseOrderDetail().getUpc().getSize().getInseam());
            hm.put("size", size);
            hm.put("color_name", pd.getPurchaseOrderDetail().getUpc().getColor().getName());
            hm.put("po_number", pd.getPurchaseOrderDetail().getPurchaseOrder().getReferenceNumber());
            hm.put("qty", String.valueOf(Client.getNumberOfScannedItems(pd.getCarton())));
            try {
                hm.put("country", this.getMaster().getCountryOfOrigin());
            }
            catch (IOException e) {
                hm.put("country", "Nicaragua");
            }
            hm.put("to", pd.getPurchaseOrderDetail().getPurchaseOrder().getShipTo());
            hm.put("from", pd.getPurchaseOrderDetail().getPurchaseOrder().getShipFrom());
            try {
                this.jasperPrintTicket = JasperFillManager.fillReport((JasperReport)(pd.getPurchaseOrderDetail().getPreticketed() ? this.getJasperReportPreticketed() : this.getJasperReportNoPreticketed()), hm, (JRDataSource)new JREmptyDataSource());
                if (this.jasperPrintTicket.getPages().size() > 1) {
                    this.jasperPrintTicket.getPages().remove(1);
                }
                this.getLog().info((Object)("number of pages[" + this.jasperPrintTicket.getPages().size() + "] - topMargin[" + this.jasperPrintTicket.getTopMargin() + "]"));
            }
            catch (JRFontNotFoundException e) {
                e.printStackTrace();
            }
            this.getLog().info((Object)("jasperPrint[ " + (Object)this.jasperPrintTicket + " ]"));
        }
        return this.jasperPrintTicket;
    }

    public JasperPrint getJasperPrintPolybag(PackingDetail pd) throws JRException {
        HashMap<String, Object> hm = new HashMap<String, Object>();
        if (pd == null) {
            this.jasperPrintPolybag = JasperFillManager.fillReport((JasperReport)this.getJasperReportBlankPolybag(), hm, (JRDataSource)new JREmptyDataSource());
        } else {
            hm.put("item", String.valueOf(pd.getPurchaseOrderDetail().getUpc().getItem().getCode()));
            hm.put("department", String.valueOf(pd.getPurchaseOrderDetail().getPurchaseOrder().getDepartmentNumber()));
            hm.put("color", String.valueOf(pd.getPurchaseOrderDetail().getUpc().getColor().getNumber()));
            String size = "";
            size = pd.getPurchaseOrderDetail().getPurchaseOrder().getReferenceNumber().endsWith("0011") ? String.valueOf(pd.getPurchaseOrderDetail().getUpc().getSize().getWaist()) : String.valueOf(String.valueOf(pd.getPurchaseOrderDetail().getUpc().getSize().getWaist()) + "x" + pd.getPurchaseOrderDetail().getUpc().getSize().getInseam());
            hm.put("size", size);
            hm.put("color_name", pd.getPurchaseOrderDetail().getUpc().getColor().getName());
            hm.put("upc_image", ImageUtils.getAwtBarcodeImage(pd.getPurchaseOrderDetail().getUpc().getUpcCode()));
            try {
                this.jasperPrintPolybag = JasperFillManager.fillReport((JasperReport)this.getJasperReportPolybag(), hm, (JRDataSource)new JREmptyDataSource());
                if (this.jasperPrintPolybag.getPages().size() > 1) {
                    this.jasperPrintPolybag.getPages().remove(1);
                }
                this.getLog().info((Object)("number of pages[" + this.jasperPrintPolybag.getPages().size() + "] - topMargin[" + this.jasperPrintPolybag.getTopMargin() + "]"));
            }
            catch (JRFontNotFoundException e) {
                e.printStackTrace();
            }
        }
        this.getLog().info((Object)("jasperPrintPolybag[ " + (Object)this.jasperPrintPolybag + " ]"));
        return this.jasperPrintPolybag;
    }

    public JasperReport getJasperReportPreticketed() throws JRException {
        if (this.preticketed == null) {
            try {
                this.preticketed = JasperCompileManager.compileReport((String)this.getMaster().getPreticketedReportPath());
            }
            catch (IOException e) {
                this.getLog().info((Object)"", (Throwable)e);
            }
        }
        return this.preticketed;
    }

    public JasperReport getJasperReportNoPreticketed() throws JRException {
        if (this.noPreticketed == null) {
            try {
                this.noPreticketed = JasperCompileManager.compileReport((String)this.getMaster().getNoPreticketedReportPath());
            }
            catch (IOException e) {
                this.getLog().info((Object)"", (Throwable)e);
            }
        }
        return this.noPreticketed;
    }

    public JasperReport getJasperReportPolybag() throws JRException {
        if (this.polybag == null) {
            try {
                this.polybag = JasperCompileManager.compileReport((String)this.getMaster().getStickerReportPath());
            }
            catch (IOException e) {
                this.getLog().info((Object)"", (Throwable)e);
            }
        }
        return this.polybag;
    }

    public JasperReport getJasperReportBlankPolybag() throws JRException {
        if (this.blankPolybag == null) {
            try {
                this.blankPolybag = JasperCompileManager.compileReport((String)this.getMaster().getBlankStickerReportPath());
            }
            catch (IOException e) {
                this.getLog().info((Object)"", (Throwable)e);
            }
        }
        return this.blankPolybag;
    }

    public JasperReport getJasperReportBlankTicket() throws JRException {
        if (this.blankTicket == null) {
            try {
                this.blankTicket = JasperCompileManager.compileReport((String)this.getMaster().getBlanckPreticketedPath());
            }
            catch (IOException e) {
                this.getLog().info((Object)"", (Throwable)e);
            }
        }
        return this.blankTicket;
    }

    public POClient(Display display) {
        super(display, 1264);
        this.init();
    }

    public POClient(Shell shell, Display display, User user) {
        super(display, 1264);
        this.user = user;
        try {
            shell.dispose();
            this.init();
            this.open();
            this.layout();
            while (!this.isDisposed()) {
                if (display.readAndDispatch()) continue;
                display.sleep();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        GridLayout gridLayout = new GridLayout(1, false);
        gridLayout.marginTop = 5;
        gridLayout.marginRight = 5;
        gridLayout.marginLeft = 5;
        gridLayout.marginWidth = 0;
        this.setLayout((Layout)gridLayout);
        TabFolder tabFolder = new TabFolder((Composite)this, 0);
        tabFolder.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        TabItem tbtmScan = new TabItem(tabFolder, 0);
        tbtmScan.setText("scan");
        Composite scanComposite = new Composite((Composite)tabFolder, 0);
        tbtmScan.setControl((Control)scanComposite);
        GridLayout gl_bottomScanComposite = new GridLayout(2, false);
        gl_bottomScanComposite.marginBottom = 5;
        gl_bottomScanComposite.marginTop = 5;
        gl_bottomScanComposite.marginRight = 5;
        gl_bottomScanComposite.marginLeft = 5;
        scanComposite.setLayout((Layout)gl_bottomScanComposite);
        Composite leftScanComposite = new Composite(scanComposite, 2048);
        GridLayout gl_leftScanComposite = new GridLayout(4, false);
        gl_leftScanComposite.marginTop = 2;
        gl_leftScanComposite.marginRight = 2;
        gl_leftScanComposite.marginLeft = 2;
        gl_leftScanComposite.marginBottom = 2;
        leftScanComposite.setLayout((Layout)gl_leftScanComposite);
        GridData gd_leftScanComposite = new GridData(16384, 4, false, true, 1, 2);
        gd_leftScanComposite.widthHint = 300;
        leftScanComposite.setLayoutData((Object)gd_leftScanComposite);
        Label lblPo = new Label(leftScanComposite, 0);
        lblPo.setAlignment(131072);
        lblPo.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        lblPo.setText("po:");
        this.purchaseOrderCbx = new Combo(leftScanComposite, 8);
        this.purchaseOrderCbx.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        this.purchaseOrderCbx.setLayoutData((Object)new GridData(4, 16777216, true, false, 1, 1));
        this.purchaseOrderCbx.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent arg0) {
                try {
                    POClient.this.getClient().selectingPurchaseOrder();
                }
                catch (JRException e) {
                    POClient.this.getLog().error((Object)"error on purchase order cbx selection", (Throwable)e);
                    POClient.this.getClient().getErrorBox(e.getLocalizedMessage());
                }
            }
        });
        Label itemLbl = new Label(leftScanComposite, 0);
        itemLbl.setAlignment(131072);
        itemLbl.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        itemLbl.setText("item:");
        this.itemCbx = new Combo(leftScanComposite, 8);
        this.itemCbx.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        this.itemCbx.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                try {
                    POClient.this.getClient().selectingFilter();
                }
                catch (JRException e1) {
                    POClient.this.getLog().error((Object)"error on selecting item filter", (Throwable)e1);
                    POClient.this.getClient().getErrorBox(e1.getMessage());
                }
            }
        });
        this.itemCbx.setLayoutData((Object)new GridData(4, 16777216, false, false, 1, 1));
        Label colorLbl = new Label(leftScanComposite, 0);
        colorLbl.setAlignment(131072);
        colorLbl.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        colorLbl.setText("color:");
        this.colorCbx = new Combo(leftScanComposite, 8);
        this.colorCbx.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        this.colorCbx.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                try {
                    POClient.this.getClient().selectingFilter();
                }
                catch (JRException e1) {
                    POClient.this.getLog().error((Object)"error on selecting color filter", (Throwable)e1);
                    POClient.this.getClient().getErrorBox(e1.getMessage());
                }
            }
        });
        this.colorCbx.setLayoutData((Object)new GridData(4, 16777216, true, false, 1, 1));
        Label sizeLbl = new Label(leftScanComposite, 0);
        sizeLbl.setAlignment(131072);
        sizeLbl.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        sizeLbl.setText("size:");
        this.sizeCbx = new Combo(leftScanComposite, 8);
        this.sizeCbx.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        this.sizeCbx.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                try {
                    POClient.this.getClient().selectingFilter();
                }
                catch (JRException e1) {
                    POClient.this.getLog().error((Object)"error on selecting size filter", (Throwable)e1);
                    POClient.this.getClient().getErrorBox(e1.getMessage());
                }
            }
        });
        this.sizeCbx.setLayoutData((Object)new GridData(4, 16777216, false, false, 1, 1));
        Label horizontalLine2 = new Label(leftScanComposite, 258);
        horizontalLine2.setLayoutData((Object)new GridData(4, 16777216, true, false, 4, 1));
        this.poDetailLbl = new Label(leftScanComposite, 0);
        this.poDetailLbl.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        this.poDetailLbl.setLayoutData((Object)new GridData(4, 16777216, false, false, 3, 1));
        this.poDetailLbl.setText("po detail:");
        new org.eclipse.swt.widgets.Label(leftScanComposite, 0);
        this.poDetailTbl = new Table(leftScanComposite, 67584);
        this.poDetailTbl.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        this.poDetailTbl.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                try {
                    POClient.this.getClient().selectingPurchaseOrderDetail();
                }
                catch (JRException e1) {
                    POClient.this.getLog().error((Object)"error on selecting a purchase order detail", (Throwable)e1);
                    POClient.this.getClient().getErrorBox(e1.getMessage());
                }
            }
        });
        GridData gd_poDetailTbl = new GridData(4, 4, true, false, 4, 1);
        gd_poDetailTbl.heightHint = 100;
        this.poDetailTbl.setLayoutData((Object)gd_poDetailTbl);
        this.poDetailTbl.setHeaderVisible(true);
        this.poDetailTbl.setLinesVisible(true);
        TableColumn tableColumn = new TableColumn(this.poDetailTbl, 0);
        tableColumn.setWidth(30);
        TableColumn tblclmnUpc = new TableColumn(this.poDetailTbl, 0);
        tblclmnUpc.setWidth(105);
        tblclmnUpc.setText("upc");
        TableColumn tblclmnQty = new TableColumn(this.poDetailTbl, 0);
        tblclmnQty.setWidth(30);
        tblclmnQty.setText("qty");
        TableColumn tblclmnCarton = new TableColumn(this.poDetailTbl, 0);
        tblclmnCarton.setWidth(50);
        tblclmnCarton.setText("carton");
        TableColumn tblclmnReady = new TableColumn(this.poDetailTbl, 0);
        tblclmnReady.setWidth(45);
        tblclmnReady.setText("ready");
        this.packingDetailLbl = new Label(leftScanComposite, 0);
        this.packingDetailLbl.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        this.packingDetailLbl.setLayoutData((Object)new GridData(4, 16777216, false, false, 3, 1));
        this.packingDetailLbl.setText("packing detail:");
        Button btnAddCarton = new Button(leftScanComposite, 0);
        btnAddCarton.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        btnAddCarton.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                POClient.this.getClient().addCarton();
            }
        });
        btnAddCarton.setLayoutData((Object)new GridData(131072, 16777216, false, false, 1, 1));
        btnAddCarton.setText("add carton");
        this.packingDetailTbl = new Table(leftScanComposite, 67584);
        this.packingDetailTbl.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        this.packingDetailTbl.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                try {
                    POClient.this.getClient().selectingPackingDetail();
                }
                catch (JRException e1) {
                    POClient.this.getLog().error((Object)"error on selecting packing detail from table", (Throwable)e1);
                    POClient.this.getClient().getErrorBox(e1.getMessage());
                }
            }
        });
        GridData gd_packingDetailTbl = new GridData(4, 4, true, false, 4, 1);
        gd_packingDetailTbl.heightHint = 120;
        this.packingDetailTbl.setLayoutData((Object)gd_packingDetailTbl);
        this.packingDetailTbl.setHeaderVisible(true);
        this.packingDetailTbl.setLinesVisible(true);
        TableColumn tblclmnSize = new TableColumn(this.packingDetailTbl, 0);
        tblclmnSize.setWidth(44);
        tblclmnSize.setText("size");
        TableColumn tblclmnColor = new TableColumn(this.packingDetailTbl, 0);
        tblclmnColor.setWidth(95);
        tblclmnColor.setText("color");
        TableColumn tblclmnSku = new TableColumn(this.packingDetailTbl, 0);
        tblclmnSku.setWidth(36);
        tblclmnSku.setText("sku");
        TableColumn tblclmnQty_1 = new TableColumn(this.packingDetailTbl, 0);
        tblclmnQty_1.setWidth(30);
        tblclmnQty_1.setText("qty");
        TableColumn tblclmnScanned = new TableColumn(this.packingDetailTbl, 0);
        tblclmnScanned.setWidth(60);
        tblclmnScanned.setText("scanned");
        Group grpSumary = new Group(leftScanComposite, 0);
        grpSumary.setFont(SWTResourceManager.getFont("Segoe UI", 9, 0));
        GridLayout gl_grpSumary = new GridLayout(1, false);
        gl_grpSumary.verticalSpacing = 0;
        grpSumary.setLayout((Layout)gl_grpSumary);
        grpSumary.setLayoutData((Object)new GridData(4, 4, false, true, 4, 1));
        grpSumary.setText("summary");
        this.scrolledComposite = new ScrolledComposite((Composite)grpSumary, 768);
        this.scrolledComposite.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.scrolledComposite.setExpandHorizontal(true);
        this.scrolledComposite.setExpandVertical(true);
        this.summaryScrolledComposite = new Composite((Composite)this.scrolledComposite, 0);
        this.scrolledComposite.setContent((Control)this.summaryScrolledComposite);
        this.scrolledComposite.setMinSize(this.summaryScrolledComposite.computeSize(-1, -1));
        this.summaryScrolledComposite.setLayout((Layout)new GridLayout(3, false));
        new org.eclipse.swt.widgets.Label(this.summaryScrolledComposite, 0);
        this.grpPoDetails = new Group(this.summaryScrolledComposite, 0);
        this.grpPoDetails.setFont(SWTResourceManager.getFont("Segoe UI", 7, 0));
        GridLayout gl_grpPoDetails = new GridLayout(2, true);
        gl_grpPoDetails.marginTop = 2;
        this.grpPoDetails.setLayout((Layout)gl_grpPoDetails);
        GridData gd_grpPoDetails = new GridData(16384, 4, false, true, 1, 2);
        gd_grpPoDetails.widthHint = 75;
        this.grpPoDetails.setLayoutData((Object)gd_grpPoDetails);
        this.grpPoDetails.setText("PO Details");
        this.grpPackingDetails = new Group(this.summaryScrolledComposite, 0);
        this.grpPackingDetails.setFont(SWTResourceManager.getFont("Segoe UI", 7, 0));
        GridLayout gl_grpPackingDetails = new GridLayout(2, true);
        gl_grpPackingDetails.marginTop = 5;
        this.grpPackingDetails.setLayout((Layout)gl_grpPackingDetails);
        GridData gd_grpPackingDetails = new GridData(16384, 4, false, true, 1, 2);
        gd_grpPackingDetails.widthHint = 75;
        this.grpPackingDetails.setLayoutData((Object)gd_grpPackingDetails);
        this.grpPackingDetails.setText("Pk Details");
        this.colorsComposite = new Composite(this.summaryScrolledComposite, 0);
        this.colorsComposite.setLayout((Layout)new GridLayout(1, false));
        this.colorsComposite.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        Composite rightScanComposite = new Composite(scanComposite, 2048);
        rightScanComposite.setLayout((Layout)new GridLayout(2, false));
        rightScanComposite.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        Composite topScanningComposite = new Composite(rightScanComposite, 0);
        topScanningComposite.setLayout((Layout)new FormLayout());
        topScanningComposite.setLayoutData((Object)new GridData(4, 16777216, false, false, 2, 1));
        Label lblNowScanning = new Label(topScanningComposite, 0);
        FormData fd_lblNowScanning = new FormData();
        fd_lblNowScanning.bottom = new FormAttachment(100);
        fd_lblNowScanning.left = new FormAttachment(0, 29);
        lblNowScanning.setLayoutData((Object)fd_lblNowScanning);
        lblNowScanning.setText("now scanning:");
        this.scannedBarTxt = new Text(topScanningComposite, 2048);
        this.scannedBarTxt.addTraverseListener(new TraverseListener(){

            public void keyTraversed(TraverseEvent event) {
                try {
                    POClient.this.getClient().scanning();
                }
                catch (JRException e) {
                    POClient.this.getLog().error((Object)"error on scanning", (Throwable)e);
                    POClient.this.getClient().getErrorBox(e.getMessage());
                }
                catch (Exception e) {
                    POClient.this.getLog().error((Object)"error on scanning", (Throwable)e);
                    POClient.this.getClient().getErrorBox(e.getMessage());
                }
            }
        });
        FormData fd_scannedBarTxt = new FormData();
        fd_scannedBarTxt.top = new FormAttachment(100, -23);
        fd_scannedBarTxt.bottom = new FormAttachment(100);
        fd_scannedBarTxt.right = new FormAttachment((Control)lblNowScanning, 167, 131072);
        fd_scannedBarTxt.left = new FormAttachment((Control)lblNowScanning, 5);
        this.scannedBarTxt.setLayoutData((Object)fd_scannedBarTxt);
        Composite scanningArea = new Composite(rightScanComposite, 2048);
        scanningArea.setLayout((Layout)new GridLayout(1, false));
        GridData gd_scanningArea = new GridData(16384, 4, false, true, 1, 1);
        gd_scanningArea.widthHint = 318;
        scanningArea.setLayoutData((Object)gd_scanningArea);
        this.scannedItemsTbl = new Table(scanningArea, 67584);
        this.scannedItemsTbl.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                POClient.this.getClient().selectingScanDetail();
            }
        });
        this.scannedItemsTbl.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        this.scannedItemsTbl.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.scannedItemsTbl.setHeaderVisible(true);
        this.scannedItemsTbl.setLinesVisible(true);
        TableColumn tblclmnItems = new TableColumn(this.scannedItemsTbl, 0);
        tblclmnItems.setWidth(45);
        tblclmnItems.setText("items");
        TableColumn tblclmnUpc_1 = new TableColumn(this.scannedItemsTbl, 0);
        tblclmnUpc_1.setWidth(117);
        tblclmnUpc_1.setText("upc");
        TableColumn tblclmnSize_1 = new TableColumn(this.scannedItemsTbl, 0);
        tblclmnSize_1.setWidth(57);
        tblclmnSize_1.setText("size");
        TableColumn tblclmnColor_1 = new TableColumn(this.scannedItemsTbl, 0);
        tblclmnColor_1.setWidth(84);
        tblclmnColor_1.setText("color");
        Composite composite_1 = new Composite(scanningArea, 0);
        composite_1.setLayoutData((Object)new GridData(131072, 16777216, false, false, 1, 1));
        composite_1.setLayout((Layout)new GridLayout(4, false));
        Button delBtn = new Button(composite_1, 0);
        delBtn.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        delBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                try {
                    POClient.this.getClient().deleteScan();
                }
                catch (Exception e1) {
                    POClient.this.getLog().error((Object)"error deleting the selected scan", (Throwable)e1);
                }
            }
        });
        delBtn.setText("del scan");
        Button cleanCartonBtn = new Button(composite_1, 0);
        cleanCartonBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                try {
                    POClient.this.getClient().cleanCarton();
                }
                catch (Exception e1) {
                    POClient.this.getLog().error((Object)"error cleaning the selected carton", (Throwable)e1);
                }
            }
        });
        cleanCartonBtn.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        cleanCartonBtn.setText("clean carton");
        Button delCartonBtn = new Button(composite_1, 0);
        delCartonBtn.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        delCartonBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                try {
                    POClient.this.getClient().deleteCarton();
                }
                catch (Exception e1) {
                    POClient.this.getLog().error((Object)"error deleting the carton", (Throwable)e1);
                    POClient.this.getClient().getErrorBox(e1.getMessage());
                }
            }
        });
        delCartonBtn.setText("del carton");
        this.btnCompleted = new Button(composite_1, 2);
        this.btnCompleted.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                try {
                    POClient.this.getClient().completeCarton();
                }
                catch (Exception e1) {
                    POClient.this.getLog().error((Object)"error on completing the carton", (Throwable)e1);
                    POClient.this.getClient().getErrorBox(e1.getMessage());
                }
            }
        });
        this.btnCompleted.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        this.btnCompleted.setText("completed");
        Composite scanningTicketViewer = new Composite(rightScanComposite, 2048);
        scanningTicketViewer.setLayout((Layout)new GridLayout(1, false));
        scanningTicketViewer.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.grpTicketsComposite = new Group(scanningTicketViewer, 0);
        this.grpTicketsComposite.setText("tickets");
        this.grpTicketsComposite.setLayout((Layout)new GridLayout(2, false));
        this.grpTicketsComposite.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        Composite bottomScanComposite = new Composite(scanComposite, 0);
        bottomScanComposite.setLayout((Layout)new GridLayout(2, false));
        GridData gd_bottomScanComposite = new GridData(4, 1024, true, false, 1, 1);
        gd_bottomScanComposite.heightHint = 150;
        bottomScanComposite.setLayoutData((Object)gd_bottomScanComposite);
        this.grpScanningInfo = new Group(bottomScanComposite, 0);
        this.grpScanningInfo.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        this.grpScanningInfo.setLayout((Layout)new GridLayout(4, false));
        this.grpScanningInfo.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
        this.grpScanningInfo.setText("scanning info");
        this.infoUpcLbl = new Label((Composite)this.grpScanningInfo, 0);
        this.infoUpcLbl.setLayoutData((Object)new GridData(4, 16777216, true, false, 1, 1));
        this.infoUpcLbl.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        this.infoUpcLbl.setText("upc:");
        Label verticalLineLbl = new Label((Composite)this.grpScanningInfo, 514);
        verticalLineLbl.setLayoutData((Object)new GridData(16384, 4, false, true, 1, 5));
        Label lblSize = new Label((Composite)this.grpScanningInfo, 0);
        lblSize.setAlignment(131072);
        lblSize.setLayoutData((Object)new GridData(4, 16777216, false, false, 1, 1));
        lblSize.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        lblSize.setText("size:");
        this.infoSizeLbl = new Label((Composite)this.grpScanningInfo, 0);
        this.infoSizeLbl.setLayoutData((Object)new GridData(4, 16777216, true, false, 1, 1));
        this.infoSizeLbl.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        this.infoSizeLbl.setText("");
        this.barcodeLbl = new Label((Composite)this.grpScanningInfo, 5);
        this.barcodeLbl.setAlignment(16777216);
        GridData gd_barcodeLbl = new GridData(16777216, 4, false, true, 1, 4);
        gd_barcodeLbl.widthHint = 150;
        this.barcodeLbl.setLayoutData((Object)gd_barcodeLbl);
        Label lblColor = new Label((Composite)this.grpScanningInfo, 0);
        lblColor.setAlignment(131072);
        lblColor.setLayoutData((Object)new GridData(4, 16777216, false, false, 1, 1));
        lblColor.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        lblColor.setText("color:");
        this.infoColorLbl = new Label((Composite)this.grpScanningInfo, 0);
        this.infoColorLbl.setLayoutData((Object)new GridData(4, 16777216, true, false, 1, 1));
        this.infoColorLbl.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        this.infoColorLbl.setText("");
        Label lblItem = new Label((Composite)this.grpScanningInfo, 0);
        lblItem.setAlignment(131072);
        lblItem.setLayoutData((Object)new GridData(4, 16777216, false, false, 1, 1));
        lblItem.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        lblItem.setText("item:");
        this.infoItemLbl = new Label((Composite)this.grpScanningInfo, 0);
        this.infoItemLbl.setLayoutData((Object)new GridData(4, 16777216, true, false, 1, 1));
        this.infoItemLbl.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        this.infoItemLbl.setText("");
        Label lblSku = new Label((Composite)this.grpScanningInfo, 0);
        lblSku.setAlignment(131072);
        lblSku.setLayoutData((Object)new GridData(4, 16777216, false, false, 1, 1));
        lblSku.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        lblSku.setText("sku:");
        this.infoSkuLabel = new Label((Composite)this.grpScanningInfo, 0);
        this.infoSkuLabel.setLayoutData((Object)new GridData(4, 16777216, true, false, 1, 1));
        this.infoSkuLabel.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        this.infoSkuLabel.setText("");
        this.infoPreticketedCbx = new Button((Composite)this.grpScanningInfo, 32);
        this.infoPreticketedCbx.setLayoutData((Object)new GridData(16384, 16777216, false, false, 2, 1));
        this.infoPreticketedCbx.setText("preticketed");
        this.progressBar = new ProgressBar((Composite)this.grpScanningInfo, 0);
        GridData gd_progressBar = new GridData(4, 16777216, true, false, 4, 1);
        gd_progressBar.heightHint = 5;
        this.progressBar.setLayoutData((Object)gd_progressBar);
        Group grpPrintingOptions = new Group(bottomScanComposite, 0);
        grpPrintingOptions.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        GridLayout gl_grpPrintingOptions = new GridLayout(2, false);
        gl_grpPrintingOptions.verticalSpacing = 2;
        grpPrintingOptions.setLayout((Layout)gl_grpPrintingOptions);
        GridData gd_grpPrintingOptions = new GridData(131072, 4, false, true, 1, 1);
        gd_grpPrintingOptions.widthHint = 250;
        grpPrintingOptions.setLayoutData((Object)gd_grpPrintingOptions);
        grpPrintingOptions.setText("printing settings");
        Label lblPrinters = new Label((Composite)grpPrintingOptions, 0);
        lblPrinters.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        lblPrinters.setLayoutData((Object)new GridData(131072, 16777216, false, false, 1, 1));
        lblPrinters.setText("printers:");
        this.printersCbx = new Combo((Composite)grpPrintingOptions, 8);
        this.printersCbx.setFont(SWTResourceManager.getFont("Segoe UI", 7, 0));
        GridData gd_printersCbx = new GridData(4, 1024, true, false, 1, 1);
        gd_printersCbx.heightHint = 14;
        this.printersCbx.setLayoutData((Object)gd_printersCbx);
        Label lblCopies = new Label((Composite)grpPrintingOptions, 0);
        lblCopies.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        lblCopies.setLayoutData((Object)new GridData(131072, 16777216, false, false, 1, 1));
        lblCopies.setText("copies:");
        this.numberOfCopiesSpn = new Spinner((Composite)grpPrintingOptions, 2048);
        this.numberOfCopiesSpn.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        GridData gd_numberOfCopiesSpn = new GridData(16384, 1024, false, false, 1, 1);
        gd_numberOfCopiesSpn.heightHint = 14;
        this.numberOfCopiesSpn.setLayoutData((Object)gd_numberOfCopiesSpn);
        Label autoPrintingLbl = new Label((Composite)grpPrintingOptions, 0);
        autoPrintingLbl.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        autoPrintingLbl.setLayoutData((Object)new GridData(131072, 16777216, false, false, 1, 1));
        autoPrintingLbl.setText("auto:");
        this.autoPrintingChk = new Button((Composite)grpPrintingOptions, 32);
        this.autoPrintingChk.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        this.autoPrintingChk.setToolTipText("automatic printing after completing the carton");
        Label lblPrintdialog = new Label((Composite)grpPrintingOptions, 0);
        lblPrintdialog.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        lblPrintdialog.setText("print-dialog:");
        this.printDialogChk = new Button((Composite)grpPrintingOptions, 32);
        this.printDialogChk.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        new org.eclipse.swt.widgets.Label((Composite)grpPrintingOptions, 0);
        this.savePrintingSettingsBtn = new Button((Composite)grpPrintingOptions, 0);
        this.savePrintingSettingsBtn.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        this.savePrintingSettingsBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                String defaultPrinter = POClient.this.getPrintersCbx().getText();
                int numberOfCopies = Integer.parseInt(POClient.this.getNumberOfCopiesSpn().getText());
                boolean automaticPrinting = POClient.this.getAutoPrintingChk().getSelection();
                try {
                    POClient.this.getMaster().setDefaultTicketPrinter(defaultPrinter);
                    POClient.this.getMaster().setNumberOfCopies(numberOfCopies);
                    POClient.this.getMaster().setAutomaticPrinting(automaticPrinting);
                    if (POClient.this.getMaster().saveMasterProperties()) {
                        POClient.this.getClient().getInformationBox("printing settings saved:\n - default-printer: " + defaultPrinter + "\n - number-of-copies: " + numberOfCopies);
                    } else {
                        POClient.this.getClient().getErrorBox("there is a problem saving printing settings, please contact your sysadmin");
                    }
                }
                catch (IOException e1) {
                    POClient.this.getLog().info((Object)"error saving printing settings", (Throwable)e1);
                }
            }
        });
        this.savePrintingSettingsBtn.setLayoutData((Object)new GridData(16777216, 1024, false, false, 1, 1));
        this.savePrintingSettingsBtn.setText("save");
        Composite composite = new Composite((Composite)this, 2048);
        GridData gd_composite = new GridData(4, 16777216, true, false, 1, 1);
        gd_composite.heightHint = 30;
        composite.setLayoutData((Object)gd_composite);
        this.getPolybagViewer();
        this.getTicketViewer();
        this.createContents();
    }

    protected void createContents() {
        this.setText("purchase order client");
        this.setSize(1037, 748);
        try {
            PrintingUtils printing = new PrintingUtils(this.getMaster(), this.getShell());
            int printerIndex = -1;
            int i = 0;
            PrintService[] arrprintService = printing.getServices();
            int n = arrprintService.length;
            int n2 = 0;
            while (n2 < n) {
                PrintService print = arrprintService[n2];
                if (this.getMaster().getDefaultTicketPrinter() != null && !this.getMaster().getDefaultTicketPrinter().isEmpty() && this.getMaster().getDefaultTicketPrinter().trim().compareTo(print.getName().trim()) == 0) {
                    printerIndex = i;
                }
                ++i;
                this.getPrintersCbx().add(print.getName());
                this.getPrintersCbx().setData(print.getName(), (Object)print);
                ++n2;
            }
            this.getPrintersCbx().select(printerIndex);
            this.getNumberOfCopiesSpn().setSelection(this.getMaster().getNumberOfCopies());
            this.getAutoPrintingChk().setSelection(this.getMaster().isAutomaticPrinting());
            this.getPrintDialogChk().setSelection(this.getMaster().isPrintingDialogEnabled());
        }
        catch (IOException e) {
            this.getLog().error((Object)"error", (Throwable)e);
            this.getClient().getErrorBox(e.getMessage());
        }
        this.getClient().loadAvailablePurchaseOrders();
    }

    private Logger getLog() {
        if (this.log == null) {
            this.log = Logger.getLogger((Class)POClient.class);
            PropertyConfigurator.configure((String)"log4j.properties");
        }
        return this.log;
    }

    protected void checkSubclass() {
    }

    public Combo getPurchaseOrderCbx() {
        return this.purchaseOrderCbx;
    }

    public Combo getSizeCbx() {
        return this.sizeCbx;
    }

    public Combo getColorCbx() {
        return this.colorCbx;
    }

    public Combo getItemCbx() {
        return this.itemCbx;
    }

    public Table getPoDetailTbl() {
        return this.poDetailTbl;
    }

    public Table getPackingDetailTbl() {
        return this.packingDetailTbl;
    }

    public Text getScannedBarTxt() {
        return this.scannedBarTxt;
    }

    public void printCartonTicket(Carton carton) {
        this.getLog().info((Object)("printing carton with upc: " + carton.getUpcCode()));
        try {
            PrintService service = null;
            try {
                service = (PrintService)this.getPrintersCbx().getData(this.getPrintersCbx().getItem(this.getPrintersCbx().getSelectionIndex()));
            }
            catch (Exception e) {
                this.getLog().error((Object)("printCartonTicket, carton: " + carton.getId()), (Throwable)e);
                this.getClient().getErrorBox(e.getMessage());
            }
            if (service != null) {
                HashPrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
                MediaSizeName mediaSizeName = MediaSize.findMedia(4.0f, 4.0f, 25400);
                printRequestAttributeSet.add(mediaSizeName);
                printRequestAttributeSet.add(new Copies(1));
                JRPrintServiceExporter exporter = new JRPrintServiceExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, (Object)this.getJasperPrintTicket(carton.getPackingDetail()));
                exporter.setParameter((JRExporterParameter)JRPrintServiceExporterParameter.PRINT_SERVICE, (Object)service);
                exporter.setParameter((JRExporterParameter)JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, (Object)Boolean.FALSE);
                exporter.setParameter((JRExporterParameter)JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, (Object)this.getPrintDialogChk().getSelection());
                exporter.exportReport();
            } else {
                JasperPrintManager.printReport((JasperPrint)this.getJasperPrintTicket(carton.getPackingDetail()), (boolean)true);
            }
        }
        catch (JRException e) {
            this.getLog().error((Object)"error printing the ticket", (Throwable)e);
            this.getClient().getErrorBox(e.getMessage());
        }
    }

    public int getLastSku(PurchaseOrder po) {
        int lastSku = 0;
        ArrayList<PackingDetail> pdList = new ArrayList<PackingDetail>();
        for (PurchaseOrderDetail pod : po.getPurchaseOrderDetails()) {
            if (pod.getDeleted()) continue;
            for (PackingDetail pd : pod.getPackingDetails()) {
                pdList.add(pd);
            }
        }
        Collections.sort(pdList, new PackageDetailComparator());
        lastSku = ((PackingDetail)pdList.get(pdList.size() - 1)).getSku();
        return lastSku;
    }

    public static void main(String[] args) {
        try {
            Display display = Display.getDefault();
            POClient shell = new POClient(display);
            shell.open();
            shell.layout();
            while (!shell.isDisposed()) {
                if (display.readAndDispatch()) continue;
                display.sleep();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isScanning() {
        boolean scanning = false;
        scanning = this.getPoDetailTbl().getEnabled() || this.getSizeCbx().getEnabled() || this.getColorCbx().getEnabled() || this.getItemCbx().getEnabled() || this.getPoDetailTbl().getEnabled() || this.getPackingDetailTbl().getEnabled();
        return scanning;
    }

    public boolean lockForScanning() {
        boolean locked = false;
        this.getPoDetailTbl().setEnabled(false);
        this.getSizeCbx().setEnabled(false);
        this.getColorCbx().setEnabled(false);
        this.getItemCbx().setEnabled(false);
        this.getPoDetailTbl().setEnabled(false);
        this.getPackingDetailTbl().setEnabled(false);
        locked = this.getPoDetailTbl().getEnabled() || this.getSizeCbx().getEnabled() || this.getColorCbx().getEnabled() || this.getItemCbx().getEnabled() || this.getPoDetailTbl().getEnabled() || this.getPackingDetailTbl().getEnabled();
        return locked;
    }

    public boolean unlockScanningFinished() {
        boolean unlocked = false;
        this.getPoDetailTbl().setEnabled(true);
        this.getSizeCbx().setEnabled(true);
        this.getColorCbx().setEnabled(true);
        this.getItemCbx().setEnabled(true);
        this.getPoDetailTbl().setEnabled(true);
        this.getPackingDetailTbl().setEnabled(true);
        this.getLayout();
        unlocked = this.getPoDetailTbl().getEnabled() && this.getSizeCbx().getEnabled() && this.getColorCbx().getEnabled() && this.getItemCbx().getEnabled() && this.getPoDetailTbl().getEnabled() && this.getPackingDetailTbl().getEnabled();
        return unlocked;
    }

    public Table getScannedItemsTbl() {
        return this.scannedItemsTbl;
    }

    public int getPoDetailQty() {
        return this.poDetailQty;
    }

    public void setPoDetailQty(int poDetailQty) {
        this.poDetailQty = poDetailQty;
    }

    public Master getMaster() throws IOException {
        if (this.master == null) {
            this.master = new Master(this);
        }
        return this.master;
    }

    public Group getGrpTicketsComposite() {
        return this.grpTicketsComposite;
    }

    public Combo getPrintersCbx() {
        return this.printersCbx;
    }

    public Group getGrpScanningInfo() {
        return this.grpScanningInfo;
    }

    public Spinner getNumberOfCopiesSpn() {
        return this.numberOfCopiesSpn;
    }

    public ProgressBar getProgressBar() {
        return this.progressBar;
    }

    public Button getSavePrintingSettingsBtn() {
        return this.savePrintingSettingsBtn;
    }

    public Button getAutoPrintingChk() {
        return this.autoPrintingChk;
    }

    public Button getPrintDialogChk() {
        return this.printDialogChk;
    }

    public Label getInfoUpcLbl() {
        return this.infoUpcLbl;
    }

    public Label getBarcodeLbl() {
        return this.barcodeLbl;
    }

    public Label getInfoSizeLbl() {
        return this.infoSizeLbl;
    }

    public Label getInfoColorLbl() {
        return this.infoColorLbl;
    }

    public Label getInfoItemLbl() {
        return this.infoItemLbl;
    }

    public Label getInfoSkuLabel() {
        return this.infoSkuLabel;
    }

    public Button getInfoPreticketedCbx() {
        return this.infoPreticketedCbx;
    }

    public Label getPoDetailLbl() {
        return this.poDetailLbl;
    }

    public Label getPackingDetailLbl() {
        return this.packingDetailLbl;
    }

    public Composite getColorsComposite() {
        return this.colorsComposite;
    }

    public Group getGrpPoDetails() {
        return this.grpPoDetails;
    }

    public Group getGrpPackingDetails() {
        return this.grpPackingDetails;
    }

    public Composite getSummaryScrolledComposite() {
        return this.summaryScrolledComposite;
    }

    public ScrolledComposite getScrolledComposite() {
        return this.scrolledComposite;
    }

    public Button getBtnCompleted() {
        return this.btnCompleted;
    }

}

