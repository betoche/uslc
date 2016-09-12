/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  com.ibm.icu.util.Calendar
 *  com.jasperassistant.designer.viewer.IReportViewer
 *  com.jasperassistant.designer.viewer.ViewerComposite
 *  com.uslc.po.jpa.comparator.PackageDetailComparator
 *  com.uslc.po.jpa.comparator.PurchaseOrderDetailComparator
 *  com.uslc.po.jpa.comparator.ScanDetailComparator
 *  com.uslc.po.jpa.comparator.SizeComparator
 *  com.uslc.po.jpa.entity.Carton
 *  com.uslc.po.jpa.entity.Color
 *  com.uslc.po.jpa.entity.Item
 *  com.uslc.po.jpa.entity.PackingDetail
 *  com.uslc.po.jpa.entity.PurchaseOrder
 *  com.uslc.po.jpa.entity.PurchaseOrderDetail
 *  com.uslc.po.jpa.entity.ScanDetail
 *  com.uslc.po.jpa.entity.Size
 *  com.uslc.po.jpa.entity.Upc
 *  com.uslc.po.jpa.entity.User
 *  com.uslc.po.jpa.logic.PurchaseOrderRepo
 *  com.uslc.po.jpa.logic.UpcRepo
 *  com.uslc.po.jpa.util.Constants
 *  com.uslc.po.jpa.util.UslcJpa
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
 *  org.eclipse.swt.events.SelectionAdapter
 *  org.eclipse.swt.events.SelectionEvent
 *  org.eclipse.swt.events.SelectionListener
 *  org.eclipse.swt.events.TraverseEvent
 *  org.eclipse.swt.events.TraverseListener
 *  org.eclipse.swt.graphics.Font
 *  org.eclipse.swt.graphics.Image
 *  org.eclipse.swt.graphics.Rectangle
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
 *  org.eclipse.swt.widgets.Monitor
 *  org.eclipse.swt.widgets.ProgressBar
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Spinner
 *  org.eclipse.swt.widgets.TabFolder
 *  org.eclipse.swt.widgets.TabItem
 *  org.eclipse.swt.widgets.Table
 *  org.eclipse.swt.widgets.TableColumn
 *  org.eclipse.swt.widgets.TableItem
 *  org.eclipse.swt.widgets.Text
 */
package com.uslc.po.gui.client;

import com.ibm.icu.util.Calendar;
import com.jasperassistant.designer.viewer.IReportViewer;
import com.jasperassistant.designer.viewer.ViewerComposite;
import com.uslc.po.gui.util.Client;
import com.uslc.po.gui.util.ImageUtils;
import com.uslc.po.gui.util.Master;
import com.uslc.po.gui.util.PrintingUtils;
import com.uslc.po.jpa.comparator.PackageDetailComparator;
import com.uslc.po.jpa.comparator.PurchaseOrderDetailComparator;
import com.uslc.po.jpa.comparator.ScanDetailComparator;
import com.uslc.po.jpa.comparator.SizeComparator;
import com.uslc.po.jpa.entity.Carton;
import com.uslc.po.jpa.entity.Color;
import com.uslc.po.jpa.entity.Item;
import com.uslc.po.jpa.entity.PackingDetail;
import com.uslc.po.jpa.entity.PurchaseOrder;
import com.uslc.po.jpa.entity.PurchaseOrderDetail;
import com.uslc.po.jpa.entity.ScanDetail;
import com.uslc.po.jpa.entity.Size;
import com.uslc.po.jpa.entity.Upc;
import com.uslc.po.jpa.entity.User;
import com.uslc.po.jpa.logic.PurchaseOrderRepo;
import com.uslc.po.jpa.logic.UpcRepo;
import com.uslc.po.jpa.util.Constants;
import com.uslc.po.jpa.util.UslcJpa;
import java.awt.Image;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Rectangle;
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
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class POClient2
extends Shell {
    private Master master = null;
    private User user = null;
    private Table poDetailTbl;
    private Table packingDetailTbl;
    private Combo purchaseOrderCbx;
    private Logger log = null;
    private Combo sizeCbx;
    private Combo colorCbx;
    private Combo itemCbx;
    private Label totalUpcLbl;
    private Label totalItemsLbl;
    private Label totalCartonLbl;
    private Label totalColorsLbl;
    private Label totalSizesLbl;
    private Label remainingUpcLbl;
    private Label remainingItemsLbl;
    private Label remainingCartonLbl;
    private Label remainingColorsLbl;
    private Label remainingSizesLbl;
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
    private PackingDetail scanningPackingDetail = null;
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

    public ViewerComposite getPolybagViewer() {
        if (this.polybagViewer == null) {
            this.polybagViewer = new ViewerComposite((Composite)this.getGrpTicketsComposite(), 0);
            GridData gd_ticketViewer = new GridData(16384, 16777216, false, false, 1, 1);
            gd_ticketViewer.widthHint = 170;
            this.polybagViewer.setLayoutData((Object)gd_ticketViewer);
        }
        return this.polybagViewer;
    }

    public ViewerComposite getTicketViewer() {
        if (this.ticketViewer == null) {
            Composite printComposite = new Composite((Composite)this.grpTicketsComposite, 0);
            printComposite.setLayout((Layout)new GridLayout(1, false));
            printComposite.setLayoutData((Object)new GridData(4, 4, true, true, 1, 1));
            Button btnPrintSmall = new Button(printComposite, 0);
            btnPrintSmall.setLayoutData((Object)new GridData(16777216, 16777216, true, true, 1, 1));
            btnPrintSmall.setText("print small");
            Button btnPrintTicket = new Button(printComposite, 0);
            btnPrintTicket.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent e) {
                    TableItem[] items = POClient2.this.getPackingDetailTbl().getSelection();
                    if (items != null) {
                        PoCartonTable poCarton = null;
                        TableItem[] arrtableItem = items;
                        int n = arrtableItem.length;
                        int n2 = 0;
                        while (n2 < n) {
                            TableItem item = arrtableItem[n2];
                            poCarton = (PoCartonTable)item.getData();
                            ++n2;
                        }
                        if (poCarton != null && poCarton.getPd().getCarton() != null) {
                            POClient2.this.printCartonTicket(poCarton.getPd().getCarton());
                        }
                    }
                }
            });
            btnPrintTicket.setLayoutData((Object)new GridData(16777216, 16777216, true, true, 1, 1));
            btnPrintTicket.setText("print ticket");
            this.ticketViewer = new ViewerComposite((Composite)this.getGrpTicketsComposite(), 0);
            GridData gd_polybagViewer = new GridData(4, 4, true, true, 2, 1);
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
            hm.put("qty", String.valueOf(this.getNumberOfScannedItems(pd.getCarton())));
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

    public POClient2(Display display) {
        super(display, 1264);
        this.init();
    }

    public POClient2(Shell shell, Display display, User user) {
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
                POClient2.this.loadFilters();
                POClient2.this.fillSumary();
                POClient2.this.unlockScanningFinished();
                POClient2.this.clean();
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
                POClient2.this.loadPoDetailTable();
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
                POClient2.this.loadPoDetailTable();
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
                POClient2.this.loadPoDetailTable();
            }
        });
        this.sizeCbx.setLayoutData((Object)new GridData(4, 16777216, false, false, 1, 1));
        Label horizontalLine2 = new Label(leftScanComposite, 258);
        horizontalLine2.setLayoutData((Object)new GridData(4, 16777216, true, false, 4, 1));
        Label poDetailLbl = new Label(leftScanComposite, 0);
        poDetailLbl.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        poDetailLbl.setLayoutData((Object)new GridData(16384, 16777216, false, false, 3, 1));
        poDetailLbl.setText("po detail:");
        new org.eclipse.swt.widgets.Label(leftScanComposite, 0);
        this.poDetailTbl = new Table(leftScanComposite, 67584);
        this.poDetailTbl.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        this.poDetailTbl.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                POClient2.this.loadCartons();
            }
        });
        GridData gd_poDetailTbl = new GridData(4, 4, true, false, 4, 1);
        gd_poDetailTbl.heightHint = 100;
        this.poDetailTbl.setLayoutData((Object)gd_poDetailTbl);
        this.poDetailTbl.setHeaderVisible(true);
        this.poDetailTbl.setLinesVisible(true);
        TableColumn tableColumn = new TableColumn(this.poDetailTbl, 0);
        tableColumn.setWidth(20);
        TableColumn tblclmnUpc = new TableColumn(this.poDetailTbl, 0);
        tblclmnUpc.setWidth(110);
        tblclmnUpc.setText("upc");
        TableColumn tblclmnQty = new TableColumn(this.poDetailTbl, 0);
        tblclmnQty.setWidth(38);
        tblclmnQty.setText("qty");
        TableColumn tblclmnCarton = new TableColumn(this.poDetailTbl, 0);
        tblclmnCarton.setWidth(58);
        tblclmnCarton.setText("carton");
        TableColumn tblclmnReady = new TableColumn(this.poDetailTbl, 0);
        tblclmnReady.setWidth(56);
        tblclmnReady.setText("ready");
        Label packingDetailLbl = new Label(leftScanComposite, 0);
        packingDetailLbl.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        packingDetailLbl.setLayoutData((Object)new GridData(16384, 16777216, false, false, 3, 1));
        packingDetailLbl.setText("packing detail:");
        Button btnAddCarton = new Button(leftScanComposite, 0);
        btnAddCarton.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        btnAddCarton.addSelectionListener((SelectionListener)new SelectionAdapter(){

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public void widgetSelected(SelectionEvent e) {
                TableItem[] items = POClient2.this.getPoDetailTbl().getSelection();
                if (items != null) {
                    PoDetailTable poDetail = null;
                    TableItem[] arrtableItem = items;
                    int n = arrtableItem.length;
                    int n2 = 0;
                    while (n2 < n) {
                        TableItem item = arrtableItem[n2];
                        poDetail = (PoDetailTable)item.getData();
                        ++n2;
                    }
                    if (poDetail == null) return;
                    try {
                        POClient2.this.setPoDetailQty(0);
                        final Shell shell = new Shell(POClient2.this.getShell(), 83968);
                        shell.setSize(102, 100);
                        shell.setText(POClient2.this.getText());
                        Rectangle bounds = Display.getDefault().getPrimaryMonitor().getBounds();
                        Rectangle rect = shell.getBounds();
                        int x = bounds.x + (bounds.width - rect.width) / 2;
                        int y = bounds.y + (bounds.height - rect.height) / 2;
                        shell.setLocation(x, y);
                        shell.setLayout((Layout)new GridLayout(2, false));
                        Label qtyLbl = new Label((Composite)shell, 0);
                        qtyLbl.setLayoutData((Object)new GridData(4, 16777216, false, false, 2, 1));
                        qtyLbl.setText("quantity:");
                        final Text qtyTxt = new Text((Composite)shell, 0);
                        qtyTxt.setLayoutData((Object)new GridData(4, 16777216, false, false, 2, 1));
                        qtyTxt.setText("");
                        Button cancelBtn = new Button((Composite)shell, 8);
                        cancelBtn.setLayoutData((Object)new GridData(131072, 16777216, false, false, 1, 1));
                        cancelBtn.setText("cancel");
                        cancelBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

                            public void widgetSelected(SelectionEvent arg0) {
                                shell.close();
                            }
                        });
                        Button okBtn = new Button((Composite)shell, 8);
                        okBtn.setLayoutData((Object)new GridData(131072, 16777216, false, false, 1, 1));
                        okBtn.setText("ok");
                        okBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

                            public void widgetSelected(SelectionEvent arg0) {
                                try {
                                    7.this.POClient2.this.setPoDetailQty(Integer.parseInt(qtyTxt.getText()));
                                    System.out.println("qty[" + 7.this.POClient2.this.getPoDetailQty() + "]");
                                    shell.close();
                                }
                                catch (Exception e) {
                                    MessageBox box = new MessageBox(7.this.POClient2.this.getShell(), 1);
                                    box.setText(Constants.MESSAGE_BOX_DIAG_TITLE.toString());
                                    box.setMessage(e.toString());
                                    box.open();
                                }
                            }
                        });
                        shell.open();
                        shell.layout();
                        Display display = shell.getDisplay();
                        while (!shell.isDisposed()) {
                            if (display.readAndDispatch()) continue;
                            display.sleep();
                        }
                        if (POClient2.this.getPoDetailQty() <= 0) return;
                        PurchaseOrderDetail pod = poDetail.getPod();
                        PackingDetail pd = new PackingDetail();
                        pd.setDeleted(false);
                        pd.setPurchaseOrderDetail(pod);
                        pd.setQuantity(POClient2.this.getPoDetailQty());
                        int newSku = POClient2.this.getLastSku(pod.getPurchaseOrder()) + 1;
                        pd.setSku(newSku);
                        UslcJpa jpa = new UslcJpa();
                        pd = (PackingDetail)jpa.merge((Object)pd);
                        if (pd.getId() <= 0) throw new Exception("the packingdetail { purchase_order_detail_id:" + pod.getId() + ", quantity:" + POClient2.this.getPoDetailQty() + ", sku:" + newSku + " }");
                        pod.getPackingDetails().add(pd);
                        POClient2.this.loadFilters();
                        POClient2.this.fillSumary();
                        POClient2.this.loadScanningItems(null);
                        POClient2.this.setScanningPackingDetail(null);
                        return;
                    }
                    catch (Exception e2) {
                        POClient2.this.getLog().error((Object)"error", (Throwable)e2);
                        return;
                    }
                } else {
                    MessageBox box = new MessageBox(POClient2.this.getShell(), 8);
                    box.setText(Constants.MESSAGE_BOX_DIAG_TITLE.toString());
                    box.setMessage("please select a po detail from where you want to add the carton");
                    box.open();
                }
            }

        });
        btnAddCarton.setLayoutData((Object)new GridData(131072, 16777216, false, false, 1, 1));
        btnAddCarton.setText("add carton");
        this.packingDetailTbl = new Table(leftScanComposite, 67584);
        this.packingDetailTbl.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        this.packingDetailTbl.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                TableItem[] items = POClient2.this.packingDetailTbl.getSelection();
                PackingDetail pd = null;
                TableItem[] arrtableItem = items;
                int n = arrtableItem.length;
                int n2 = 0;
                while (n2 < n) {
                    TableItem item = arrtableItem[n2];
                    pd = ((PoCartonTable)item.getData()).getPd();
                    ++n2;
                }
                POClient2.this.getLog().info((Object)("packing detail selected, pd[" + (Object)pd + "]"));
                POClient2.this.setScanningPackingDetail(pd);
                POClient2.this.loadScanningItems(null);
                POClient2.this.showPackingDetailInfo();
                POClient2.this.getScannedBarTxt().setFocus();
            }
        });
        GridData gd_packingDetailTbl = new GridData(4, 4, true, true, 4, 1);
        gd_packingDetailTbl.heightHint = 150;
        this.packingDetailTbl.setLayoutData((Object)gd_packingDetailTbl);
        this.packingDetailTbl.setHeaderVisible(true);
        this.packingDetailTbl.setLinesVisible(true);
        TableColumn tblclmnSize = new TableColumn(this.packingDetailTbl, 0);
        tblclmnSize.setWidth(57);
        tblclmnSize.setText("size");
        TableColumn tblclmnColor = new TableColumn(this.packingDetailTbl, 0);
        tblclmnColor.setWidth(74);
        tblclmnColor.setText("color");
        TableColumn tblclmnSku = new TableColumn(this.packingDetailTbl, 0);
        tblclmnSku.setWidth(40);
        tblclmnSku.setText("sku");
        TableColumn tblclmnQty_1 = new TableColumn(this.packingDetailTbl, 0);
        tblclmnQty_1.setWidth(38);
        tblclmnQty_1.setText("qty");
        TableColumn tblclmnScanned = new TableColumn(this.packingDetailTbl, 0);
        tblclmnScanned.setWidth(75);
        tblclmnScanned.setText("scanned");
        Group grpSumary = new Group(leftScanComposite, 0);
        grpSumary.setFont(SWTResourceManager.getFont("Segoe UI", 9, 0));
        GridLayout gl_grpSumary = new GridLayout(4, true);
        gl_grpSumary.verticalSpacing = 0;
        grpSumary.setLayout((Layout)gl_grpSumary);
        grpSumary.setLayoutData((Object)new GridData(131072, 1024, false, true, 4, 1));
        grpSumary.setText("summary");
        Label lblTotal = new Label((Composite)grpSumary, 0);
        lblTotal.setAlignment(131072);
        lblTotal.setLayoutData((Object)new GridData(131072, 16777216, false, false, 2, 1));
        lblTotal.setFont(SWTResourceManager.getFont("Cantarell", 8, 0));
        lblTotal.setText("total");
        Label lblRemaining = new Label((Composite)grpSumary, 0);
        lblRemaining.setLayoutData((Object)new GridData(131072, 16777216, false, false, 2, 1));
        lblRemaining.setFont(SWTResourceManager.getFont("Cantarell", 8, 0));
        lblRemaining.setText("remaining");
        Label lblUpc = new Label((Composite)grpSumary, 0);
        lblUpc.setFont(SWTResourceManager.getFont("Cantarell", 8, 0));
        lblUpc.setText("upc");
        this.totalUpcLbl = new Label((Composite)grpSumary, 0);
        this.totalUpcLbl.setAlignment(131072);
        this.totalUpcLbl.setLayoutData((Object)new GridData(4, 16777216, false, false, 1, 1));
        this.totalUpcLbl.setFont(SWTResourceManager.getFont("Cantarell", 8, 0));
        this.totalUpcLbl.setText("0");
        Label label_1 = new Label((Composite)grpSumary, 514);
        label_1.setFont(SWTResourceManager.getFont("Cantarell", 8, 0));
        GridData gd_label_1 = new GridData(16777216, 4, false, false, 1, 5);
        gd_label_1.widthHint = 15;
        label_1.setLayoutData((Object)gd_label_1);
        this.remainingUpcLbl = new Label((Composite)grpSumary, 0);
        this.remainingUpcLbl.setAlignment(131072);
        this.remainingUpcLbl.setLayoutData((Object)new GridData(4, 16777216, false, false, 1, 1));
        this.remainingUpcLbl.setFont(SWTResourceManager.getFont("Cantarell", 8, 0));
        this.remainingUpcLbl.setText("0");
        Label lblItems = new Label((Composite)grpSumary, 0);
        lblItems.setFont(SWTResourceManager.getFont("Cantarell", 8, 0));
        lblItems.setText("items");
        this.totalItemsLbl = new Label((Composite)grpSumary, 0);
        this.totalItemsLbl.setAlignment(131072);
        this.totalItemsLbl.setLayoutData((Object)new GridData(4, 16777216, false, false, 1, 1));
        this.totalItemsLbl.setFont(SWTResourceManager.getFont("Cantarell", 8, 0));
        this.totalItemsLbl.setText("0");
        this.remainingItemsLbl = new Label((Composite)grpSumary, 0);
        this.remainingItemsLbl.setAlignment(131072);
        this.remainingItemsLbl.setLayoutData((Object)new GridData(4, 16777216, false, false, 1, 1));
        this.remainingItemsLbl.setFont(SWTResourceManager.getFont("Cantarell", 8, 0));
        this.remainingItemsLbl.setText("0");
        Label lblCartons = new Label((Composite)grpSumary, 0);
        lblCartons.setFont(SWTResourceManager.getFont("Cantarell", 8, 0));
        lblCartons.setText("cartons");
        this.totalCartonLbl = new Label((Composite)grpSumary, 0);
        this.totalCartonLbl.setAlignment(131072);
        this.totalCartonLbl.setLayoutData((Object)new GridData(4, 16777216, false, false, 1, 1));
        this.totalCartonLbl.setFont(SWTResourceManager.getFont("Cantarell", 8, 0));
        this.totalCartonLbl.setText("0");
        this.remainingCartonLbl = new Label((Composite)grpSumary, 0);
        this.remainingCartonLbl.setAlignment(131072);
        this.remainingCartonLbl.setLayoutData((Object)new GridData(4, 16777216, false, false, 1, 1));
        this.remainingCartonLbl.setFont(SWTResourceManager.getFont("Cantarell", 8, 0));
        this.remainingCartonLbl.setText("0");
        Label lblColors = new Label((Composite)grpSumary, 0);
        lblColors.setFont(SWTResourceManager.getFont("Cantarell", 8, 0));
        lblColors.setText("colors");
        this.totalColorsLbl = new Label((Composite)grpSumary, 0);
        this.totalColorsLbl.setAlignment(131072);
        this.totalColorsLbl.setLayoutData((Object)new GridData(4, 16777216, false, false, 1, 1));
        this.totalColorsLbl.setFont(SWTResourceManager.getFont("Cantarell", 8, 0));
        this.totalColorsLbl.setText("0");
        this.remainingColorsLbl = new Label((Composite)grpSumary, 0);
        this.remainingColorsLbl.setAlignment(131072);
        this.remainingColorsLbl.setLayoutData((Object)new GridData(4, 16777216, false, false, 1, 1));
        this.remainingColorsLbl.setFont(SWTResourceManager.getFont("Cantarell", 8, 0));
        this.remainingColorsLbl.setText("0");
        Label lblSizes = new Label((Composite)grpSumary, 0);
        lblSizes.setFont(SWTResourceManager.getFont("Cantarell", 8, 0));
        lblSizes.setText("sizes");
        this.totalSizesLbl = new Label((Composite)grpSumary, 0);
        this.totalSizesLbl.setAlignment(131072);
        this.totalSizesLbl.setLayoutData((Object)new GridData(4, 16777216, false, false, 1, 1));
        this.totalSizesLbl.setFont(SWTResourceManager.getFont("Cantarell", 8, 0));
        this.totalSizesLbl.setText("0");
        this.remainingSizesLbl = new Label((Composite)grpSumary, 0);
        this.remainingSizesLbl.setAlignment(131072);
        this.remainingSizesLbl.setFont(SWTResourceManager.getFont("Cantarell", 8, 0));
        this.remainingSizesLbl.setLayoutData((Object)new GridData(4, 16777216, false, false, 1, 1));
        this.remainingSizesLbl.setText("0");
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
                if (event.detail == 4) {
                    String scannedUpcCode = POClient2.this.scannedBarTxt.getText().trim();
                    POClient2.this.getLog().info((Object)("Enter Pressed: " + scannedUpcCode));
                    Upc upc = UpcRepo.findByCode((String)scannedUpcCode);
                    if (upc != null) {
                        POClient2.this.scanPerformed(upc);
                    } else {
                        POClient2.this.getLog().error((Object)("upc lenght: " + scannedUpcCode.length()));
                    }
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
        composite_1.setLayout((Layout)new GridLayout(3, false));
        Button delBtn = new Button(composite_1, 0);
        delBtn.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        delBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                TableItem[] items = POClient2.this.scannedItemsTbl.getSelection();
                ScannedItemsTable sit = null;
                TableItem[] arrtableItem = items;
                int n = arrtableItem.length;
                int n2 = 0;
                while (n2 < n) {
                    TableItem item = arrtableItem[n2];
                    sit = (ScannedItemsTable)item.getData();
                    ++n2;
                }
                if (sit != null) {
                    ScanDetail sd = sit.getSd();
                    UslcJpa jpa = new UslcJpa();
                    sd.setDeleted(true);
                    try {
                        if (jpa.persist((Object)sd) && POClient2.this.changeCartonCompleteStatus(sd.getCarton())) {
                            POClient2.this.loadCartons();
                            POClient2.this.fillSumary();
                            POClient2.this.loadScanningItems(null);
                        }
                    }
                    catch (Exception e1) {
                        POClient2.this.getLog().error((Object)"error deleted the scan detail", (Throwable)e1);
                    }
                }
            }
        });
        delBtn.setText("del scan");
        Button cleanCartonBtn = new Button(composite_1, 0);
        cleanCartonBtn.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                TableItem[] items = POClient2.this.getPackingDetailTbl().getSelection();
                if (items != null) {
                    PoCartonTable carton = null;
                    TableItem[] arrtableItem = items;
                    int n = arrtableItem.length;
                    int n2 = 0;
                    while (n2 < n) {
                        TableItem item = arrtableItem[n2];
                        carton = (PoCartonTable)item.getData();
                        ++n2;
                    }
                    if (carton != null) {
                        try {
                            UslcJpa jpa = new UslcJpa();
                            for (ScanDetail sd : carton.getPd().getCarton().getScanDetails()) {
                                sd.setDeleted(true);
                                if (jpa.persist((Object)sd)) continue;
                                throw new Exception("there is an error while cleaning the carton # " + carton.getSku());
                            }
                            POClient2.this.getLog().info((Object)("setting deleted to true the scans from carton with id: " + carton.getPd().getCarton().getId()));
                            carton.getPd().getCarton().setCompleted(false);
                            if (jpa.persist((Object)carton.getPd().getCarton())) {
                                MessageBox box = new MessageBox(POClient2.this.getShell(), 2);
                                box.setText(Constants.MESSAGE_BOX_DIAG_TITLE.toString());
                                box.setMessage("carton # " + carton.getSku() + " from purchase order: " + carton.getPd().getPurchaseOrderDetail().getPurchaseOrder().getReferenceNumber() + " was cleaned");
                                box.open();
                                POClient2.this.loadFilters();
                                POClient2.this.fillSumary();
                                POClient2.this.loadScanningItems(null);
                                POClient2.this.setScanningPackingDetail(null);
                            }
                        }
                        catch (Exception e1) {
                            POClient2.this.getLog().error((Object)"error cleaning the carton", (Throwable)e1);
                        }
                    }
                }
            }
        });
        cleanCartonBtn.setFont(SWTResourceManager.getFont("Segoe UI", 8, 0));
        cleanCartonBtn.setText("clean carton");
        Button btnCleanCarton = new Button(composite_1, 0);
        btnCleanCarton.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                TableItem[] items = POClient2.this.getPackingDetailTbl().getSelection();
                if (items != null) {
                    PoCartonTable carton = null;
                    TableItem[] arrtableItem = items;
                    int n = arrtableItem.length;
                    int n2 = 0;
                    while (n2 < n) {
                        TableItem item = arrtableItem[n2];
                        carton = (PoCartonTable)item.getData();
                        ++n2;
                    }
                    if (carton != null) {
                        try {
                            UslcJpa jpa = new UslcJpa();
                            carton.getPd().setDeleted(true);
                            if (carton.getPd().getCarton() != null) {
                                carton.getPd().getCarton().setDeleted(true);
                            }
                            if (jpa.persist((Object)carton.getPd())) {
                                MessageBox box = new MessageBox(POClient2.this.getShell(), 2);
                                box.setText(Constants.MESSAGE_BOX_DIAG_TITLE.toString());
                                box.setMessage("carton # " + carton.getSku() + " from purchase order: " + carton.getPd().getPurchaseOrderDetail().getPurchaseOrder().getReferenceNumber() + " removed from list");
                                box.open();
                                POClient2.this.loadFilters();
                                POClient2.this.fillSumary();
                                POClient2.this.loadScanningItems(null);
                                POClient2.this.setScanningPackingDetail(null);
                            }
                        }
                        catch (Exception e1) {
                            POClient2.this.getLog().error((Object)"error cleaning the carton", (Throwable)e1);
                        }
                    }
                }
            }
        });
        btnCleanCarton.setText("del carton");
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
                String defaultPrinter = POClient2.this.getPrintersCbx().getText();
                int numberOfCopies = Integer.parseInt(POClient2.this.getNumberOfCopiesSpn().getText());
                boolean automaticPrinting = POClient2.this.getAutoPrintingChk().getSelection();
                try {
                    POClient2.this.getMaster().setDefaultTicketPrinter(defaultPrinter);
                    POClient2.this.getMaster().setNumberOfCopies(numberOfCopies);
                    POClient2.this.getMaster().setAutomaticPrinting(automaticPrinting);
                    if (POClient2.this.getMaster().saveMasterProperties()) {
                        MessageBox box = new MessageBox(POClient2.this.getShell(), 2);
                        box.setText(Constants.MESSAGE_BOX_DIAG_TITLE.toString());
                        box.setMessage("printing settings saved:\n - default-printer: " + defaultPrinter + "\n - number-of-copies: " + numberOfCopies);
                        box.open();
                    } else {
                        MessageBox box = new MessageBox(POClient2.this.getShell(), 2);
                        box.setText(Constants.MESSAGE_BOX_DIAG_TITLE.toString());
                        box.setMessage("there is a problem saving printing settings, please contact your sysadmin");
                        box.open();
                    }
                }
                catch (IOException e1) {
                    POClient2.this.getLog().info((Object)"error saving printing settings", (Throwable)e1);
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

    protected void clean() {
        this.getScannedBarTxt().setText("");
        this.getScannedItemsTbl().removeAll();
        this.setScanningPackingDetail(null);
        this.showPackingDetailInfo();
    }

    protected void createContents() {
        this.setText("purchase order client");
        this.setSize(1037, 748);
        this.loadValues();
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
        }
    }

    private PurchaseOrder getSelectedPurchaseOrder() {
        PurchaseOrder po = null;
        try {
            po = (PurchaseOrder)this.getPurchaseOrderCbx().getData(this.getPurchaseOrderCbx().getItem(this.getPurchaseOrderCbx().getSelectionIndex()));
        }
        catch (Exception var2_2) {
            // empty catch block
        }
        return po;
    }

    private Logger getLog() {
        if (this.log == null) {
            this.log = Logger.getLogger((Class)POClient2.class);
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

    public Label getTotalUpcLbl() {
        return this.totalUpcLbl;
    }

    public Label getTotalItemsLbl() {
        return this.totalItemsLbl;
    }

    public Label getTotalCartonLbl() {
        return this.totalCartonLbl;
    }

    public Label getTotalColorsLbl() {
        return this.totalColorsLbl;
    }

    public Label getTotalSizesLbl() {
        return this.totalSizesLbl;
    }

    public Label getRemainingUpcLbl() {
        return this.remainingUpcLbl;
    }

    public Label getRemainingItemsLbl() {
        return this.remainingItemsLbl;
    }

    public Label getRemainingCartonLbl() {
        return this.remainingCartonLbl;
    }

    public Label getRemainingColorsLbl() {
        return this.remainingColorsLbl;
    }

    public Label getRemainingSizesLbl() {
        return this.remainingSizesLbl;
    }

    public Text getScannedBarTxt() {
        return this.scannedBarTxt;
    }

    public void updatePackingInfo() {
        TableItem[] items = this.getPackingDetailTbl().getSelection();
        if (items != null) {
            PoCartonTable poCarton = null;
            TableItem tableItem = null;
            TableItem[] arrtableItem = items;
            int n = arrtableItem.length;
            int n2 = 0;
            while (n2 < n) {
                TableItem item;
                tableItem = item = arrtableItem[n2];
                poCarton = (PoCartonTable)item.getData();
                ++n2;
            }
            if (poCarton != null) {
                poCarton = new PoCartonTable(poCarton.getPd());
                String[] texts = new String[]{poCarton.getSize(), poCarton.getColor(), String.valueOf(poCarton.getSku()), String.valueOf(poCarton.getQty()), String.valueOf(poCarton.getScanned())};
                tableItem.setText(texts);
                tableItem.setData((Object)poCarton);
            }
        }
    }

    public void showPackingDetailInfo() {
        TableItem[] items = this.getPackingDetailTbl().getSelection();
        if (items != null) {
            PoCartonTable poCarton = null;
            TableItem[] arrtableItem = items;
            int n = arrtableItem.length;
            int n2 = 0;
            while (n2 < n) {
                TableItem item = arrtableItem[n2];
                poCarton = (PoCartonTable)item.getData();
                ++n2;
            }
            if (poCarton != null) {
                try {
                    this.getPolybagViewer().getReportViewer().setDocument(this.getJasperPrintPolybag(poCarton.getPd()));
                    this.getTicketViewer().getReportViewer().setDocument(this.getJasperPrintTicket(poCarton.getPd()));
                    this.getTicketViewer().getReportViewer().setZoomMode(2);
                    this.getPolybagViewer().getReportViewer().setZoomMode(2);
                    int selection = poCarton.getScanned() * 100 / poCarton.getQty();
                    this.getProgressBar().setSelection(selection);
                }
                catch (JRException e) {
                    this.getLog().error((Object)"", (Throwable)e);
                }
                this.getInfoUpcLbl().setText("upc: " + poCarton.getPd().getPurchaseOrderDetail().getUpc().getUpcCode());
                this.getInfoColorLbl().setText(poCarton.getColor());
                this.getInfoItemLbl().setText(String.valueOf(poCarton.getPd().getPurchaseOrderDetail().getUpc().getItem().getCode()));
                this.getInfoPreticketedCbx().setSelection(poCarton.getPd().getPurchaseOrderDetail().getPreticketed());
                this.getInfoSizeLbl().setText(poCarton.getSize());
                this.getInfoSkuLabel().setText(String.valueOf(poCarton.getSku()));
                org.eclipse.swt.graphics.Image img = ImageUtils.getBarcodeImage(this.getDisplay(), poCarton.getPd().getPurchaseOrderDetail().getUpc().getUpcCode());
                this.getBarcodeLbl().setImage(ImageUtils.resize(img, 130, 60));
            } else {
                try {
                    this.getPolybagViewer().getReportViewer().setDocument(this.getJasperPrintPolybag(null));
                    this.getTicketViewer().getReportViewer().setDocument(this.getJasperPrintPolybag(null));
                    this.getInfoUpcLbl().setText("upc: ");
                    this.getInfoColorLbl().setText("");
                    this.getInfoItemLbl().setText("");
                    this.getInfoPreticketedCbx().setSelection(false);
                    this.getInfoSizeLbl().setText("");
                    this.getInfoSkuLabel().setText("");
                    this.getBarcodeLbl().setImage(null);
                }
                catch (JRException e) {
                    this.getLog().error((Object)"", (Throwable)e);
                }
            }
        }
    }

    public void loadValues() {
        List pos = PurchaseOrderRepo.findAll();
        ArrayList<PurchaseOrder> cbxPoList = new ArrayList<PurchaseOrder>();
        block0 : for (PurchaseOrder po2 : pos) {
            for (PurchaseOrderDetail pod : po2.getPurchaseOrderDetails()) {
                for (PackingDetail pd : pod.getPackingDetails()) {
                    if (pd.getCarton() != null) continue;
                    cbxPoList.add(po2);
                    continue block0;
                }
            }
        }
        this.getLog().info((Object)("cbxPoList.size()[" + cbxPoList.size() + "]"));
        this.getPurchaseOrderCbx().removeAll();
        for (PurchaseOrder po2 : cbxPoList) {
            this.getPurchaseOrderCbx().add(po2.getReferenceNumber());
            this.getPurchaseOrderCbx().setData(po2.getReferenceNumber(), (Object)po2);
        }
    }

    public void loadFilters() {
        PurchaseOrder po = this.getSelectedPurchaseOrder();
        HashSet<Size> sizes = new HashSet<Size>();
        HashSet<Color> colors = new HashSet<Color>();
        HashSet<Item> items = new HashSet<Item>();
        for (PurchaseOrderDetail pod : po.getPurchaseOrderDetails()) {
            colors.add(pod.getUpc().getColor());
            sizes.add(pod.getUpc().getSize());
            items.add(pod.getUpc().getItem());
        }
        this.getItemCbx().removeAll();
        for (Item item : items) {
            this.getItemCbx().add(String.valueOf(item.getCode()));
            this.getItemCbx().setData(String.valueOf(item.getCode()), (Object)item);
        }
        this.getColorCbx().removeAll();
        for (Color color : colors) {
            this.getColorCbx().add(color.getName());
            this.getColorCbx().setData(color.getName(), (Object)color);
        }
        this.getSizeCbx().removeAll();
        TreeSet<Size> sizesTreeSet = new TreeSet<Size>((Comparator<Size>)new SizeComparator());
        sizesTreeSet.addAll(sizes);
        for (Size size : sizesTreeSet) {
            String str = "";
            str = po.getReferenceNumber().endsWith("11") ? String.valueOf(size.getWaist()) : String.valueOf(size.getWaist()) + "x" + size.getInseam();
            this.getSizeCbx().add(str);
            this.getSizeCbx().setData(str, (Object)size);
        }
        this.loadPoDetailTable();
        this.clean();
    }

    public void loadPoDetailTable() {
        PurchaseOrder po = this.getSelectedPurchaseOrder();
        Color color = null;
        Size size = null;
        Item item = null;
        try {
            color = (Color)this.getColorCbx().getData(this.getColorCbx().getItem(this.getColorCbx().getSelectionIndex()));
        }
        catch (Exception var5_5) {
            // empty catch block
        }
        try {
            size = (Size)this.getSizeCbx().getData(this.getSizeCbx().getItem(this.getSizeCbx().getSelectionIndex()));
        }
        catch (Exception var5_6) {
            // empty catch block
        }
        try {
            item = (Item)this.getItemCbx().getData(this.getItemCbx().getItem(this.getItemCbx().getSelectionIndex()));
        }
        catch (Exception var5_8) {
            // empty catch block
        }
        ArrayList<PurchaseOrderDetail> tmpDet = new ArrayList<PurchaseOrderDetail>();
        for (PurchaseOrderDetail pod : po.getPurchaseOrderDetails()) {
            Upc upc = pod.getUpc();
            tmpDet.add(pod);
            if (color != null && upc.getColor().getId() != color.getId()) {
                tmpDet.remove((Object)pod);
            }
            if (size != null && upc.getSize().getId() != size.getId()) {
                tmpDet.remove((Object)pod);
            }
            if (item == null || upc.getItem().getId() == item.getId()) continue;
            tmpDet.remove((Object)pod);
        }
        this.getPoDetailTbl().removeAll();
        Collections.sort(tmpDet, new PurchaseOrderDetailComparator());
        int row = 0;
        for (PurchaseOrderDetail pod2 : tmpDet) {
            PoDetailTable t = new PoDetailTable(pod2);
            String[] texts = new String[]{String.valueOf(++row), t.getUpc(), String.valueOf(t.getQty()), String.valueOf(t.getCarton()), String.valueOf(t.getReady())};
            TableItem it = new TableItem(this.getPoDetailTbl(), 8);
            it.setText(texts);
            it.setData((Object)t);
        }
        this.loadCartons();
    }

    public void loadCartons() {
        ArrayList<PurchaseOrderDetail> podList = new ArrayList<PurchaseOrderDetail>();
        TableItem[] arrtableItem = this.getPoDetailTbl().getItems();
        int n = arrtableItem.length;
        int n2 = 0;
        while (n2 < n) {
            TableItem ti = arrtableItem[n2];
            podList.add(((PoDetailTable)ti.getData()).getPod());
            ++n2;
        }
        TableItem[] items = this.getPoDetailTbl().getSelection();
        PoDetailTable poDetTable = null;
        TableItem[] arrtableItem2 = items;
        int n3 = arrtableItem2.length;
        int n4 = 0;
        while (n4 < n3) {
            TableItem it = arrtableItem2[n4];
            poDetTable = (PoDetailTable)it.getData();
            ++n4;
        }
        ArrayList<PackingDetail> packingList = new ArrayList<PackingDetail>();
        for (PurchaseOrderDetail pod : podList) {
            for (PackingDetail pd : pod.getPackingDetails()) {
                if (pd.getDeleted()) continue;
                packingList.add(pd);
                if (poDetTable == null || pd.getPurchaseOrderDetail().getId() == poDetTable.getPod().getId()) continue;
                packingList.remove((Object)pd);
            }
        }
        this.getPackingDetailTbl().removeAll();
        Collections.sort(packingList, new PackageDetailComparator());
        for (PackingDetail packingDetail : packingList) {
            TableItem item = new TableItem(this.getPackingDetailTbl(), 8);
            PoCartonTable carton = new PoCartonTable(packingDetail);
            String[] texts = new String[]{carton.getSize(), carton.getColor(), String.valueOf(carton.getSku()), String.valueOf(carton.getQty()), String.valueOf(carton.getScanned())};
            item.setText(texts);
            item.setData((Object)carton);
        }
        this.showPackingDetailInfo();
    }

    public void fillSumary() {
        PurchaseOrder po = this.getSelectedPurchaseOrder();
        HashSet<Size> sizes = new HashSet<Size>();
        HashSet<Color> colors = new HashSet<Color>();
        HashSet<Item> items = new HashSet<Item>();
        HashSet<Upc> upcs = new HashSet<Upc>();
        for (PurchaseOrderDetail pod : po.getPurchaseOrderDetails()) {
            Upc upc = pod.getUpc();
            upcs.add(upc);
            sizes.add(upc.getSize());
            colors.add(upc.getColor());
            items.add(upc.getItem());
        }
        this.getTotalUpcLbl().setText(String.valueOf(upcs.size()));
        this.getTotalCartonLbl().setText(String.valueOf(po.getTotalCartons()));
        this.getTotalColorsLbl().setText(String.valueOf(colors.size()));
        this.getTotalItemsLbl().setText(String.valueOf(po.getTotalItems()));
        this.getTotalSizesLbl().setText(String.valueOf(sizes.size()));
        int scannedItems = 0;
        int missingUpcs = 0;
        int missingCartons = 0;
        int missingColors = 0;
        int missingSizes = 0;
        for (PurchaseOrderDetail pod22 : po.getPurchaseOrderDetails()) {
            for (PackingDetail pd : pod22.getPackingDetails()) {
                Carton carton = pd.getCarton();
                scannedItems += this.getNumberOfScannedItems(carton);
            }
        }
        block3 : for (Upc upc : upcs) {
            for (PurchaseOrderDetail pod3 : po.getPurchaseOrderDetails()) {
                Upc podUpc = pod3.getUpc();
                if (upc.getId() != podUpc.getId()) continue;
                for (PackingDetail pd : pod3.getPackingDetails()) {
                    if (pd.getCarton() != null && pd.getCarton().getCompleted()) continue;
                    ++missingUpcs;
                    continue block3;
                }
            }
        }
        for (PurchaseOrderDetail pod22 : po.getPurchaseOrderDetails()) {
            for (PackingDetail pd : pod22.getPackingDetails()) {
                if (pd.getCarton() == null) {
                    ++missingCartons;
                    continue;
                }
                if (pd.getCarton().getCompleted() || pd.getCarton().getDeleted()) continue;
                ++missingCartons;
            }
        }
        block8 : for (Color c : colors) {
            for (PurchaseOrderDetail pod3 : po.getPurchaseOrderDetails()) {
                if (c.getId() != pod3.getUpc().getColor().getId()) continue;
                for (PackingDetail pd : pod3.getPackingDetails()) {
                    if (pd.getCarton() != null && pd.getCarton().getCompleted()) continue;
                    ++missingColors;
                    continue block8;
                }
            }
        }
        block11 : for (Size s : sizes) {
            for (PurchaseOrderDetail pod3 : po.getPurchaseOrderDetails()) {
                if (s.getId() != pod3.getUpc().getSize().getId()) continue;
                for (PackingDetail pd : pod3.getPackingDetails()) {
                    if (pd.getCarton() != null && pd.getCarton().getCompleted()) continue;
                    ++missingSizes;
                    continue block11;
                }
            }
        }
        this.getRemainingUpcLbl().setText(String.valueOf(missingUpcs));
        this.getRemainingItemsLbl().setText(String.valueOf(po.getTotalItems() - scannedItems));
        this.getRemainingCartonLbl().setText(String.valueOf(missingCartons));
        this.getRemainingColorsLbl().setText(String.valueOf(missingColors));
        this.getRemainingSizesLbl().setText(String.valueOf(missingSizes));
        this.getLayout();
    }

    public void scanPerformed(Upc upc) {
        ScanDetail sd = null;
        if (this.scanningPackingDetail == null) {
            this.getLog().info((Object)"starting a new packing detail");
            if (this.addPackingDetail(upc)) {
                sd = this.addScannedItem(upc);
            }
        } else {
            this.getLog().info((Object)"continuing with carton selected before");
            sd = this.addScannedItem(upc);
        }
        if (sd != null) {
            this.loadScanningItems(sd);
            this.getScannedBarTxt().setText("");
        } else {
            this.getLog().error((Object)("sd is null: sd[" + (Object)sd + "]"));
        }
        if (this.getScannedItemsTbl().getItems().length > 0 && this.scanningPackingDetail != null) {
            this.lockForScanning();
        } else {
            this.unlockScanningFinished();
        }
        this.showPackingDetailInfo();
        this.updatePackingInfo();
        this.fillSumary();
        this.getScannedBarTxt().setFocus();
    }

    public void loadScanningItems(ScanDetail sd) {
        Carton c;
        this.getScannedItemsTbl().removeAll();
        if (this.scanningPackingDetail != null && (c = this.scanningPackingDetail.getCarton()) != null) {
            List sdList = c.getScanDetails();
            Collections.sort(sdList, new ScanDetailComparator());
            int scannedItems = 0;
            for (ScanDetail scanned : sdList) {
                if (scanned.getDeleted()) continue;
                TableItem item = new TableItem(this.getScannedItemsTbl(), 8);
                ScannedItemsTable sit = new ScannedItemsTable(++scannedItems, scanned);
                String[] texts = new String[]{String.valueOf(sit.getOrder()), sit.getUpc().getUpcCode(), sit.getSize(), sit.getColor()};
                item.setText(texts);
                item.setData((Object)sit);
            }
        }
    }

    public ScanDetail addScannedItem(Upc upc) {
        ScanDetail sd;
        block11 : {
            sd = null;
            UslcJpa jpa = new UslcJpa();
            Carton carton = this.scanningPackingDetail.getCarton();
            boolean add = false;
            if (carton == null) {
                this.getLog().info((Object)("there's no carton attached to the packing detail[" + this.scanningPackingDetail.getId() + "], so let's create a new one for it"));
                carton = new Carton();
                carton.setCompleted(false);
                carton.setDeleted(false);
                carton.setPackingDetail(this.scanningPackingDetail);
                carton.setReferenceNumber(this.scanningPackingDetail.getPurchaseOrderDetail().getPurchaseOrder().getReferenceNumber());
                carton.setUpcCode(upc.getUpcCode());
                carton.setUser(this.user);
                try {
                    carton = (Carton)jpa.merge((Object)carton);
                }
                catch (Exception e) {
                    this.getLog().error((Object)("error inserting the carton for upc: " + upc.getUpcCode()), (Throwable)e);
                }
            }
            if (this.getNumberOfScannedItems(carton) < this.scanningPackingDetail.getQuantity()) {
                add = true;
            } else {
                MessageBox box = new MessageBox((Shell)this, 196);
                box.setText(Constants.MESSAGE_BOX_DIAG_TITLE.toString());
                box.setMessage("the purchase order detail is already completed do you want to add an extra item?");
                boolean bl = add = box.open() == 64;
            }
            if (add) {
                sd = new ScanDetail();
                sd.setCarton(carton);
                sd.setDeleted(false);
                sd.setTimestamp(Calendar.getInstance().getTime());
                sd.setUpc(upc);
                sd.setUpcReferenceNumber(upc.getUpcCode());
                try {
                    sd = (ScanDetail)jpa.merge((Object)sd);
                    if (sd.getId() > 0) {
                        carton.addScanDetail(sd);
                        carton.setItems(carton.getItems() + 1);
                        this.scanningPackingDetail.setCarton(carton);
                        if (this.changeCartonCompleteStatus(carton) && this.getNumberOfScannedItems(carton) == this.scanningPackingDetail.getQuantity()) {
                            if (this.getAutoPrintingChk().getSelection()) {
                                this.printCartonTicket(carton);
                            }
                            this.loadFilters();
                            this.fillSumary();
                            this.loadScanningItems(sd);
                            this.setScanningPackingDetail(null);
                        }
                        break block11;
                    }
                    return null;
                }
                catch (Exception e) {
                    this.getLog().error((Object)"error trying to add the scanned upc code to the carton", (Throwable)e);
                }
            }
        }
        return sd;
    }

    private boolean changeCartonCompleteStatus(Carton carton) {
        boolean success = false;
        if (Client.getNumberOfScannedItems(carton) < carton.getPackingDetail().getQuantity()) {
            carton.setCompleted(false);
        } else {
            carton.setCompleted(true);
        }
        UslcJpa jpa = new UslcJpa();
        try {
            success = jpa.persist((Object)carton);
        }
        catch (Exception e) {
            this.getLog().error((Object)"error modifying carton status", (Throwable)e);
        }
        return success;
    }

    public void printCartonTicket(Carton carton) {
        this.getLog().info((Object)("printing carton with upc: " + carton.getUpcCode()));
        try {
            PrintService service = null;
            try {
                service = (PrintService)this.getPrintersCbx().getData(this.getPrintersCbx().getItem(this.getPrintersCbx().getSelectionIndex()));
            }
            catch (Exception e) {
                this.getLog().error((Object)e);
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
            MessageBox box = new MessageBox((Shell)this, 1);
            box.setText(Constants.MESSAGE_BOX_DIAG_TITLE.toString());
            box.setMessage(e.getMessage());
            box.open();
            this.getLog().error((Object)"error printing the ticket", (Throwable)e);
        }
    }

    public int getNumberOfScannedItems(Carton carton) {
        int scanned = 0;
        if (carton != null) {
            for (ScanDetail scan : carton.getScanDetails()) {
                if (scan.getDeleted()) continue;
                ++scanned;
            }
        }
        return scanned;
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

    public void setScanningPackingDetail(PackingDetail scanningPackingDetail) {
        if (scanningPackingDetail != null) {
            this.displayPackingDetailInfo();
        } else {
            this.clearPackingDetailInfo();
        }
        this.scanningPackingDetail = scanningPackingDetail;
    }

    public void displayPackingDetailInfo() {
    }

    public void clearPackingDetailInfo() {
    }

    public boolean addPackingDetail(Upc upc) {
        MessageBox box;
        boolean success = false;
        TableItem[] tableItems = this.getPackingDetailTbl().getSelection();
        PackingDetail pd = null;
        UslcJpa jpa = new UslcJpa();
        TableItem[] arrtableItem = tableItems;
        int n = arrtableItem.length;
        int n2 = 0;
        while (n2 < n) {
            TableItem item = arrtableItem[n2];
            pd = ((PoCartonTable)item.getData()).getPd();
            ++n2;
        }
        if (pd != null) {
            if (upc.getId() == pd.getPurchaseOrderDetail().getUpc().getId()) {
                Carton carton = null;
                if (pd.getCarton() == null) {
                    this.getLog().info((Object)("there's no carton attached to the packing detail[" + pd.getId() + "], so let's create a new one for it"));
                    carton = new Carton();
                    carton.setCompleted(false);
                    carton.setDeleted(false);
                    carton.setPackingDetail(pd);
                    carton.setReferenceNumber(pd.getPurchaseOrderDetail().getPurchaseOrder().getReferenceNumber());
                    carton.setUpcCode(upc.getUpcCode());
                    carton.setUser(this.user);
                    try {
                        carton = (Carton)jpa.merge((Object)carton);
                    }
                    catch (Exception e) {
                        this.getLog().error((Object)("error inserting the carton for upc: " + upc.getUpcCode()), (Throwable)e);
                    }
                } else if (!pd.getCarton().getCompleted()) {
                    carton = pd.getCarton();
                    this.getLog().info((Object)("there's already a carton[" + carton.getId() + ",completed:" + carton.getCompleted() + "]"));
                } else {
                    MessageBox box2 = new MessageBox((Shell)this, 1);
                    box2.setText(Constants.MESSAGE_BOX_DIAG_TITLE.toString());
                    box2.setMessage("the selected packing detail is already finished, please choose a different one");
                    box2.open();
                }
                if (carton != null) {
                    this.setScanningPackingDetail(pd);
                    this.scanningPackingDetail.setCarton(carton);
                    success = true;
                }
            } else {
                box = new MessageBox((Shell)this, 1);
                box.setText(Constants.MESSAGE_BOX_DIAG_TITLE.toString());
                box.setMessage("the scanned upc code is different than the one set in the purchase order detail");
                box.open();
            }
        } else {
            box = new MessageBox((Shell)this, 1);
            box.setText(Constants.MESSAGE_BOX_DIAG_TITLE.toString());
            box.setMessage("you need to select the packing detail to work with first");
            box.open();
        }
        return success;
    }

    public static void main(String[] args) {
        try {
            Display display = Display.getDefault();
            POClient2 shell = new POClient2(display);
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
        throw new Error("Unresolved compilation problem: \n\tThe constructor Master(POClient2) is undefined\n");
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

    public class PoCartonTable {
        private PackingDetail pd;
        private String color;
        private String size;
        private int sku;
        private int qty;
        private int scanned;

        public String getColor() {
            if (this.color == null || this.color.compareTo("") == 0) {
                this.color = this.pd.getPurchaseOrderDetail().getUpc().getColor().getName();
            }
            return this.color;
        }

        public String getSize() {
            if (this.size == null || this.size.compareTo("") == 0) {
                Size s = this.pd.getPurchaseOrderDetail().getUpc().getSize();
                this.size = this.pd.getPurchaseOrderDetail().getPurchaseOrder().getReferenceNumber().endsWith("11") ? String.valueOf(s.getWaist()) : String.valueOf(String.valueOf(s.getWaist()) + "x" + s.getInseam());
            }
            return this.size;
        }

        public PoCartonTable(PackingDetail pd) {
            this.pd = null;
            this.color = "";
            this.size = "";
            this.sku = 0;
            this.qty = 0;
            this.scanned = 0;
            this.pd = pd;
        }

        public PackingDetail getPd() {
            return this.pd;
        }

        public int getSku() {
            if (this.sku == 0) {
                this.sku = this.pd.getSku();
            }
            return this.sku;
        }

        public int getQty() {
            if (this.qty == 0) {
                this.qty = this.pd.getQuantity();
            }
            return this.qty;
        }

        public int getScanned() {
            if (this.scanned == 0 && this.pd.getCarton() != null && this.pd.getCarton().getScanDetails() != null) {
                for (ScanDetail sd : this.pd.getCarton().getScanDetails()) {
                    if (sd.getDeleted()) continue;
                    ++this.scanned;
                }
            }
            return this.scanned;
        }
    }

    public class PoDetailTable {
        private PurchaseOrderDetail pod;
        private String upc;
        private int qty;
        private int carton;
        private int ready;

        public PoDetailTable(PurchaseOrderDetail pod) {
            this.pod = null;
            this.upc = "";
            this.qty = 0;
            this.carton = 0;
            this.ready = 0;
            this.pod = pod;
        }

        public PurchaseOrderDetail getPod() {
            return this.pod;
        }

        public String getUpc() {
            if (this.upc == null || this.upc.compareTo("") == 0) {
                this.upc = this.pod.getUpc().getUpcCode();
            }
            return this.upc;
        }

        public int getQty() {
            if (this.qty == 0) {
                this.qty = this.getPod().getTotal();
            }
            return this.qty;
        }

        public int getCarton() {
            if (this.carton == 0) {
                for (PackingDetail pd : this.getPod().getPackingDetails()) {
                    if (pd.getDeleted()) continue;
                    ++this.carton;
                }
            }
            return this.carton;
        }

        public int getReady() {
            if (this.ready == 0) {
                for (PackingDetail pd : this.getPod().getPackingDetails()) {
                    if (pd.getCarton() == null || !pd.getCarton().getCompleted()) continue;
                    ++this.ready;
                }
            }
            return this.ready;
        }
    }

    public class ScannedItemsTable {
        private int order;
        private ScanDetail sd;
        private Upc upc;
        private String size;
        private String color;
        private PurchaseOrder po;

        public ScannedItemsTable(int order, ScanDetail sd) {
            this.order = 0;
            this.upc = null;
            this.size = "";
            this.color = "";
            this.po = null;
            this.order = order;
            this.sd = sd;
        }

        public ScanDetail getSd() {
            return this.sd;
        }

        public int getOrder() {
            return this.order;
        }

        public Upc getUpc() {
            if (this.upc == null) {
                this.upc = this.sd.getUpc();
            }
            return this.upc;
        }

        public String getSize() {
            if (this.size == null || this.size.compareTo("") == 0) {
                this.size = this.getPo().getReferenceNumber().endsWith("11") ? String.valueOf(this.getUpc().getSize().getWaist()) : String.valueOf(this.getUpc().getSize().getWaist()) + "x" + this.getUpc().getSize().getInseam();
            }
            return this.size;
        }

        public String getColor() {
            if (this.color == null || this.color.compareTo("") == 0) {
                this.color = this.upc.getColor().getName();
            }
            return this.color;
        }

        public PurchaseOrder getPo() {
            if (this.po == null) {
                this.po = this.sd.getCarton().getPackingDetail().getPurchaseOrderDetail().getPurchaseOrder();
            }
            return this.po;
        }
    }

}

