/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  com.jasperassistant.designer.viewer.IReportViewer
 *  com.jasperassistant.designer.viewer.ViewerComposite
 *  com.uslc.po.jpa.comparator.PackageDetailComparator
 *  com.uslc.po.jpa.comparator.PurchaseOrderDetailComparator
 *  com.uslc.po.jpa.entity.Color
 *  com.uslc.po.jpa.entity.Item
 *  com.uslc.po.jpa.entity.PackingDetail
 *  com.uslc.po.jpa.entity.PurchaseOrder
 *  com.uslc.po.jpa.entity.PurchaseOrderDetail
 *  com.uslc.po.jpa.entity.Size
 *  com.uslc.po.jpa.entity.Upc
 *  com.uslc.po.jpa.util.Constants
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
 *  org.eclipse.swt.graphics.Device
 *  org.eclipse.swt.graphics.Font
 *  org.eclipse.swt.graphics.FontData
 *  org.eclipse.swt.layout.FormData
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.MessageBox
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Table
 *  org.eclipse.swt.widgets.TableColumn
 *  org.eclipse.swt.widgets.TableItem
 */
package com.uslc.po.gui.master;

import com.jasperassistant.designer.viewer.IReportViewer;
import com.jasperassistant.designer.viewer.ViewerComposite;
import com.uslc.po.gui.master.MasterCenterComposite;
import com.uslc.po.gui.master.POMaster;
import com.uslc.po.gui.util.ImageUtils;
import com.uslc.po.gui.util.Master;
import com.uslc.po.gui.util.PurchaseOrder;
import com.uslc.po.jpa.comparator.PackageDetailComparator;
import com.uslc.po.jpa.comparator.PurchaseOrderDetailComparator;
import com.uslc.po.jpa.entity.Item;
import com.uslc.po.jpa.entity.PackingDetail;
import com.uslc.po.jpa.entity.PurchaseOrderDetail;
import com.uslc.po.jpa.entity.Size;
import com.uslc.po.jpa.entity.Upc;
import com.uslc.po.jpa.util.Constants;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.Attribute;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.Copies;
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
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class PackingDetailComposite
extends Composite {
    private MasterCenterComposite parent = null;
    private Logger log = null;
    private Label titleLbl = null;
    private Table packingDetailTbl = null;
    private ViewerComposite ticketReportViewer = null;
    private ViewerComposite polybagReportViewer = null;
    private GridData gdHorizontal = null;
    private JasperReport noPreticketed = null;
    private JasperReport preticketed = null;
    private JasperReport blank = null;
    private JasperReport polybag = null;
    private JasperReport blankPolybag = null;
    private JasperPrint jasperPrint = null;
    private JasperPrint polybagJasperPrint = null;

    public PackingDetailComposite() {
        super((Composite)new Shell(), 0);
        this.initComposite();
    }

    public PackingDetailComposite(MasterCenterComposite composite) {
        super((Composite)composite.getMaster().getHiddenShell(), 0);
        this.parent = composite;
        this.initComposite();
    }

    private void initComposite() {
        FormData data = new FormData(520, 620);
        this.setLayoutData((Object)data);
        this.setLayout((Layout)new GridLayout(2, false));
        this.getTitleLbl();
        this.getPackingDetailTbl();
        this.getTicketReportViewer();
        this.getPolybagReportViewer();
    }

    public Label getTitleLbl() {
        if (this.titleLbl == null) {
            this.titleLbl = new Label((Composite)this, 0);
            this.titleLbl.setText("packing details");
            this.titleLbl.setAlignment(131072);
            this.titleLbl.setLayoutData((Object)this.getGdHorizontal());
            Label hl = new Label((Composite)this, 258);
            hl.setLayoutData((Object)this.getGdHorizontal());
        }
        return this.titleLbl;
    }

    public Table getPackingDetailTbl() {
        if (this.packingDetailTbl == null) {
            this.packingDetailTbl = new Table((Composite)this, 4);
            this.packingDetailTbl.setHeaderVisible(true);
            Font f = this.packingDetailTbl.getFont();
            FontData[] fds = f.getFontData();
            int i = 0;
            while (i < fds.length) {
                fds[i].setHeight(8);
                ++i;
            }
            this.packingDetailTbl.setFont(new Font((Device)this.getDisplay(), fds));
            this.packingDetailTbl.addSelectionListener((SelectionListener)new SelectionAdapter(){

                public void widgetSelected(SelectionEvent arg0) {
                    PackingDetailComposite.this.showTicket();
                }
            });
            TableColumn upc = new TableColumn(this.packingDetailTbl, 0);
            upc.setAlignment(16777216);
            upc.setText("upc");
            upc.setWidth(106);
            TableColumn colorName = new TableColumn(this.packingDetailTbl, 0);
            colorName.setText("color name");
            colorName.setWidth(89);
            TableColumn colorItem = new TableColumn(this.packingDetailTbl, 0);
            colorItem.setText("");
            colorItem.setWidth(106);
            TableColumn colorNumber = new TableColumn(this.packingDetailTbl, 0);
            colorNumber.setText("color #");
            colorNumber.setWidth(65);
            TableColumn sku = new TableColumn(this.packingDetailTbl, 0);
            sku.setText("sku");
            sku.setWidth(40);
            TableColumn size = new TableColumn(this.packingDetailTbl, 0);
            size.setText("size");
            size.setWidth(63);
            TableColumn quantity = new TableColumn(this.packingDetailTbl, 0);
            quantity.setText("qty");
            quantity.setWidth(39);
            GridData gd = new GridData(768);
            gd.heightHint = 180;
            gd.verticalAlignment = 128;
            gd.horizontalSpan = 2;
            this.packingDetailTbl.setLayoutData((Object)gd);
            Label hl = new Label((Composite)this, 258);
            hl.setLayoutData((Object)this.getGdHorizontal());
        }
        return this.packingDetailTbl;
    }

    public ViewerComposite getTicketReportViewer() {
        if (this.ticketReportViewer == null) {
            this.ticketReportViewer = new ViewerComposite((Composite)this, 0);
            GridData gd = new GridData(768);
            gd.heightHint = 300;
            gd.verticalAlignment = 128;
            this.ticketReportViewer.setLayoutData((Object)gd);
        }
        return this.ticketReportViewer;
    }

    public ViewerComposite getPolybagReportViewer() {
        if (this.polybagReportViewer == null) {
            this.polybagReportViewer = new ViewerComposite((Composite)this, 0);
            GridData gd = new GridData();
            gd.widthHint = 170;
            gd.verticalAlignment = 128;
            this.polybagReportViewer.setLayoutData((Object)gd);
            Label hl = new Label((Composite)this, 258);
            hl.setLayoutData((Object)this.getGdHorizontal());
        }
        return this.polybagReportViewer;
    }

    public GridData getGdHorizontal() {
        if (this.gdHorizontal == null) {
            this.gdHorizontal = new GridData(4, 4, true, false);
            this.gdHorizontal.horizontalSpan = 2;
        }
        return this.gdHorizontal;
    }

    public void displayPackingDetails(PurchaseOrder po) {
        this.clean();
        Collections.sort(po.getPo().getPurchaseOrderDetails(), new PurchaseOrderDetailComparator());
        for (PurchaseOrderDetail pod : po.getPo().getPurchaseOrderDetails()) {
            Upc upc = pod.getUpc();
            Collections.sort(pod.getPackingDetails(), new PackageDetailComparator());
            for (PackingDetail pd : pod.getPackingDetails()) {
                TableItem row = new TableItem(this.getPackingDetailTbl(), 8);
                String[] texts = new String[]{upc.getUpcCode(), upc.getColor().getName(), String.valueOf(upc.getColor().getName()) + "-" + upc.getItem().getCode(), upc.getColor().getNumber(), String.valueOf(pd.getSku()), String.valueOf(upc.getSize().getWaist()) + " x " + upc.getSize().getInseam(), String.valueOf(pd.getQuantity())};
                row.setText(texts);
                row.setData((Object)pd);
            }
        }
    }

    public void hide() {
        this.clean();
        this.setParent((Composite)this.getParent().getMaster().getHiddenShell());
        this.setVisible(false);
    }

    private void clean() {
        this.getPackingDetailTbl().removeAll();
        try {
            this.getTicketReportViewer().getReportViewer().setDocument(this.getJasperPrint(null));
            this.getTicketReportViewer().getReportViewer().setZoomMode(3);
            this.getPolybagReportViewer().getReportViewer().setDocument(this.getPolybagJasperPrint(null));
            this.getPolybagReportViewer().getReportViewer().setZoomMode(2);
        }
        catch (JRException e) {
            e.printStackTrace();
        }
    }

    public MasterCenterComposite getParent() {
        return this.parent;
    }

    private Logger getLog() {
        if (this.log == null) {
            this.log = Logger.getLogger((Class)PackingDetailComposite.class);
            PropertyConfigurator.configure((String)"log4j.properties");
        }
        return this.log;
    }

    public JasperReport getPreticketed() throws JRException {
        if (this.preticketed == null) {
            try {
                this.preticketed = JasperCompileManager.compileReport((String)this.getParent().getMaster().getMaster().getPreticketedReportPath());
            }
            catch (IOException e) {
                this.getLog().info((Object)"", (Throwable)e);
            }
        }
        return this.preticketed;
    }

    public JasperReport getNoPreticketed() throws JRException {
        if (this.noPreticketed == null) {
            try {
                this.noPreticketed = JasperCompileManager.compileReport((String)this.getParent().getMaster().getMaster().getNoPreticketedReportPath());
            }
            catch (IOException e) {
                this.getLog().info((Object)"", (Throwable)e);
            }
        }
        return this.noPreticketed;
    }

    public JasperReport getBlankPage() throws JRException {
        if (this.blank == null) {
            try {
                this.blank = JasperCompileManager.compileReport((String)this.getParent().getMaster().getMaster().getBlanckPreticketedPath());
            }
            catch (IOException e) {
                this.getLog().info((Object)"", (Throwable)e);
            }
        }
        return this.blank;
    }

    public JasperReport getBlankPolybag() throws JRException {
        if (this.blankPolybag == null) {
            try {
                this.getLog().info((Object)("blankPolybag[" + (Object)this.blankPolybag + "]\t"));
                this.blankPolybag = JasperCompileManager.compileReport((String)this.getParent().getMaster().getMaster().getBlankStickerReportPath());
            }
            catch (IOException e) {
                this.getLog().info((Object)"", (Throwable)e);
            }
        }
        return this.blankPolybag;
    }

    public JasperReport getPolybag() throws JRException {
        if (this.polybag == null) {
            try {
                this.polybag = JasperCompileManager.compileReport((String)this.getParent().getMaster().getMaster().getStickerReportPath());
            }
            catch (IOException e) {
                this.getLog().info((Object)"erro compiling polybag blank report", (Throwable)e);
            }
        }
        return this.polybag;
    }

    private JasperPrint getPolybagJasperPrint(PackingDetail pd) throws JRException {
        HashMap<String, Object> hm = new HashMap<String, Object>();
        if (pd == null) {
            this.polybagJasperPrint = JasperFillManager.fillReport((JasperReport)this.getBlankPolybag(), hm, (JRDataSource)new JREmptyDataSource());
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
                this.polybagJasperPrint = JasperFillManager.fillReport((JasperReport)this.getPolybag(), hm, (JRDataSource)new JREmptyDataSource());
                if (this.polybagJasperPrint.getPages().size() > 1) {
                    this.polybagJasperPrint.getPages().remove(1);
                }
                this.getLog().info((Object)("number of pages[" + this.polybagJasperPrint.getPages().size() + "] - topMargin[" + this.polybagJasperPrint.getTopMargin() + "]"));
            }
            catch (JRFontNotFoundException e) {
                e.printStackTrace();
            }
        }
        this.getLog().info((Object)("polybagJasperPrint[ " + (Object)this.polybagJasperPrint + " ]"));
        return this.polybagJasperPrint;
    }

    private JasperPrint getJasperPrint(PackingDetail pd) throws JRException {
        HashMap<String, Object> hm = new HashMap<String, Object>();
        if (pd == null) {
            this.jasperPrint = JasperFillManager.fillReport((JasperReport)this.getBlankPage(), hm, (JRDataSource)new JREmptyDataSource());
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
            hm.put("qty", String.valueOf(pd.getQuantity()));
            try {
                hm.put("country", this.getParent().getMaster().getMaster().getCountryOfOrigin());
            }
            catch (IOException e) {
                hm.put("country", "Nicaragua");
            }
            hm.put("to", pd.getPurchaseOrderDetail().getPurchaseOrder().getShipTo());
            hm.put("from", pd.getPurchaseOrderDetail().getPurchaseOrder().getShipFrom());
            try {
                this.jasperPrint = JasperFillManager.fillReport((JasperReport)(pd.getPurchaseOrderDetail().getPreticketed() ? this.getPreticketed() : this.getNoPreticketed()), hm, (JRDataSource)new JREmptyDataSource());
                if (this.jasperPrint.getPages().size() > 1) {
                    this.jasperPrint.getPages().remove(1);
                }
                this.getLog().info((Object)("number of pages[" + this.jasperPrint.getPages().size() + "] - topMargin[" + this.jasperPrint.getTopMargin() + "]"));
            }
            catch (JRFontNotFoundException e) {
                e.printStackTrace();
            }
            this.getLog().info((Object)("jasperPrint[ " + (Object)this.jasperPrint + " ]"));
        }
        return this.jasperPrint;
    }

    private void showTicket() {
        this.getLog().info((Object)"showTicket method called");
        TableItem[] items = this.getPackingDetailTbl().getSelection();
        PackingDetail pd = null;
        TableItem[] arrtableItem = items;
        int n = arrtableItem.length;
        int n2 = 0;
        while (n2 < n) {
            TableItem it = arrtableItem[n2];
            pd = (PackingDetail)it.getData();
            ++n2;
        }
        try {
            if (pd != null) {
                this.getPolybagReportViewer().getReportViewer().setDocument(this.getPolybagJasperPrint(pd));
                this.getPolybagReportViewer().getReportViewer().setZoomMode(2);
                this.getTicketReportViewer().getReportViewer().setDocument(this.getJasperPrint(pd));
                this.getTicketReportViewer().getReportViewer().setZoomMode(3);
            } else {
                this.getPolybagReportViewer().getReportViewer().setDocument(this.getPolybagJasperPrint(null));
                this.getTicketReportViewer().getReportViewer().setDocument(this.getPolybagJasperPrint(null));
            }
        }
        catch (Exception e) {
            this.getLog().error((Object)"error generating the report", (Throwable)e);
            MessageBox box = new MessageBox(this.getShell(), 1);
            box.setText(Constants.MESSAGE_BOX_DIAG_TITLE.toString());
            box.setMessage("error while creating the report\n" + e.getMessage());
        }
    }

    public void printTicket() {
        TableItem[] items = this.getPackingDetailTbl().getSelection();
        PackingDetail pd = null;
        TableItem[] arrtableItem = items;
        int n = arrtableItem.length;
        int n2 = 0;
        while (n2 < n) {
            TableItem it = arrtableItem[n2];
            pd = (PackingDetail)it.getData();
            ++n2;
        }
        try {
            if (pd != null) {
                JasperPrintManager.printReport((JasperPrint)this.getJasperPrint(pd), (boolean)false);
            }
        }
        catch (Exception e) {
            this.getLog().error((Object)"error", (Throwable)e);
        }
    }

    public void exportToImage() {
        TableItem[] items = this.getPackingDetailTbl().getSelection();
        PackingDetail pd = null;
        TableItem[] arrtableItem = items;
        int n = arrtableItem.length;
        int n2 = 0;
        while (n2 < n) {
            TableItem it = arrtableItem[n2];
            pd = (PackingDetail)it.getData();
            ++n2;
        }
        try {
            if (pd != null) {
                JasperPrint print = this.getJasperPrint(pd);
                PrinterJob job = PrinterJob.getPrinterJob();
                PageFormat pf = job.defaultPage();
                Paper paper = pf.getPaper();
                double margin = 0.0;
                paper.setImageableArea(margin, margin, paper.getWidth() - 2.0, paper.getHeight() - 2.0);
                pf.setPaper(paper);
                Book pBook = new Book();
                pBook.append(new MyPrintable(), pf);
                job.setPageable(pBook);
                PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
                int selectedService = 0;
                int i = 0;
                while (i < services.length) {
                    if (services[i].getName().toUpperCase().contains("Your printer's name")) {
                        selectedService = i;
                    }
                    ++i;
                }
                job.setPrintService(services[selectedService]);
                HashPrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
                printRequestAttributeSet.add(new Copies(1));
                JRPrintServiceExporter exporter = new JRPrintServiceExporter();
                this.getLog().info((Object)(print.getLeftMargin() + " - " + print.getTopMargin() + " - " + print.getPageWidth() + " - " + print.getPageHeight()));
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, (Object)print);
                exporter.setParameter((JRExporterParameter)JRPrintServiceExporterParameter.PRINT_SERVICE, (Object)services[selectedService]);
                exporter.setParameter((JRExporterParameter)JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, (Object)services[selectedService].getAttributes());
                exporter.setParameter((JRExporterParameter)JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, (Object)printRequestAttributeSet);
                exporter.setParameter((JRExporterParameter)JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, (Object)Boolean.TRUE);
                exporter.setParameter((JRExporterParameter)JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, (Object)Boolean.TRUE);
                exporter.setParameter(JRPrintServiceExporterParameter.IGNORE_PAGE_MARGINS, (Object)Boolean.TRUE);
                exporter.exportReport();
            } else {
                this.getTicketReportViewer().getReportViewer().setDocument(this.getJasperPrint(null));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    class MyPrintable
    implements Printable {
        MyPrintable() {
        }

        @Override
        public int print(Graphics g, PageFormat pf, int pageIndex) {
            if (pageIndex != 0) {
                return 1;
            }
            Graphics2D g2 = (Graphics2D)g;
            g2.setFont(new java.awt.Font("Serif", 0, 36));
            g2.setPaint(Color.black);
            g2.drawString("www.java2s.com", 100, 100);
            Rectangle2D.Double outline = new Rectangle2D.Double(pf.getImageableX(), pf.getImageableY(), pf.getImageableWidth(), pf.getImageableHeight());
            g2.draw(outline);
            return 0;
        }
    }

}

