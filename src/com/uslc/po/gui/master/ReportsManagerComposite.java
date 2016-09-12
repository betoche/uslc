/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  com.mysql.jdbc.exceptions.jdbc4.CommunicationsException
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
 *  com.uslc.po.jpa.logic.PurchaseOrderRepo
 *  com.uslc.po.jpa.logic.UserRepo
 *  com.uslc.po.jpa.util.Constants
 *  org.apache.log4j.Logger
 *  org.apache.log4j.PropertyConfigurator
 *  org.apache.poi.hssf.usermodel.HSSFPalette
 *  org.apache.poi.hssf.usermodel.HSSFWorkbook
 *  org.apache.poi.hssf.util.HSSFColor
 *  org.apache.poi.ss.usermodel.Cell
 *  org.apache.poi.ss.usermodel.CellStyle
 *  org.apache.poi.ss.usermodel.DataFormat
 *  org.apache.poi.ss.usermodel.Font
 *  org.apache.poi.ss.usermodel.IndexedColors
 *  org.apache.poi.ss.usermodel.Row
 *  org.apache.poi.ss.usermodel.Sheet
 *  org.apache.poi.ss.util.CellRangeAddress
 *  org.apache.poi.xssf.usermodel.XSSFWorkbook
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
 *  org.eclipse.swt.widgets.Link
 *  org.eclipse.swt.widgets.MessageBox
 *  org.eclipse.swt.widgets.Shell
 */
package com.uslc.po.gui.master;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.uslc.po.gui.client.ClientPurchaseOrderSumary;
import com.uslc.po.gui.master.MasterCenterComposite;
import com.uslc.po.gui.master.POMaster;
import com.uslc.po.gui.report.PoComparativeByColorItemSize;
import com.uslc.po.gui.report.SizeDistributionByColorReport;
import com.uslc.po.gui.util.Client;
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
import com.uslc.po.jpa.logic.PurchaseOrderRepo;
import com.uslc.po.jpa.logic.UserRepo;
import com.uslc.po.jpa.util.Constants;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class ReportsManagerComposite
extends Composite {
    private MasterCenterComposite parent = null;
    private Logger log = null;
    private Combo purchaseOrderCbx;
    private Combo userCbx;
    private MessageBox questionBox = null;

    public ReportsManagerComposite(MasterCenterComposite composite) {
        this((Composite)composite.getMaster().getHiddenShell(), 0);
        this.parent = composite;
        this.getLog().info((Object)"ReportsManagerComposite constructor");
        FormData data = new FormData(520, 430);
        this.setLayoutData((Object)data);
        try {
            this.loadValues();
        }
        catch (CommunicationsException e) {
            this.getLog().error((Object)"error", (Throwable)e);
        }
        catch (ConnectException e) {
            this.getLog().error((Object)"error", (Throwable)e);
        }
    }

    public void loadValues() throws CommunicationsException, ConnectException {
        List pos = PurchaseOrderRepo.findAll();
        List users = UserRepo.findAllClients((boolean)true);
        this.getPurchaseOrderCbx().removeAll();
        for (PurchaseOrder po : pos) {
            this.getPurchaseOrderCbx().add(po.getReferenceNumber());
            this.getPurchaseOrderCbx().setData(po.getReferenceNumber(), (Object)po);
        }
        this.getUserCbx().removeAll();
        for (User user : users) {
            this.getUserCbx().add(String.valueOf(user.getFirstName()) + " " + user.getLastName());
            this.getUserCbx().setData(String.valueOf(user.getFirstName()) + " " + user.getLastName(), (Object)user);
        }
    }

    public ReportsManagerComposite(Composite parent, int style) {
        super(parent, style);
        this.setLayout((Layout)new GridLayout(4, false));
        new org.eclipse.swt.widgets.Label((Composite)this, 0);
        new org.eclipse.swt.widgets.Label((Composite)this, 0);
        new org.eclipse.swt.widgets.Label((Composite)this, 0);
        new org.eclipse.swt.widgets.Label((Composite)this, 0);
        new org.eclipse.swt.widgets.Label((Composite)this, 0);
        Label label_1 = new Label((Composite)this, 258);
        label_1.setLayoutData((Object)new GridData(4, 1024, true, false, 1, 1));
        Label lblCentralReport = new Label((Composite)this, 0);
        lblCentralReport.setText("reports");
        new org.eclipse.swt.widgets.Label((Composite)this, 0);
        new org.eclipse.swt.widgets.Label((Composite)this, 0);
        new org.eclipse.swt.widgets.Label((Composite)this, 0);
        new org.eclipse.swt.widgets.Label((Composite)this, 0);
        new org.eclipse.swt.widgets.Label((Composite)this, 0);
        new org.eclipse.swt.widgets.Label((Composite)this, 0);
        Composite composite = new Composite((Composite)this, 0);
        composite.setLayout((Layout)new GridLayout(5, false));
        composite.setLayoutData((Object)new GridData(4, 4, true, true, 2, 1));
        Label lblPo = new Label(composite, 0);
        lblPo.setLayoutData((Object)new GridData(131072, 16777216, false, false, 1, 1));
        lblPo.setText("po:");
        this.purchaseOrderCbx = new Combo(composite, 8);
        GridData gd_purchaseOrderCbx = new GridData(16384, 16777216, false, false, 1, 1);
        gd_purchaseOrderCbx.widthHint = 150;
        this.purchaseOrderCbx.setLayoutData((Object)gd_purchaseOrderCbx);
        Label label = new Label(composite, 514);
        GridData gd_label = new GridData(16384, 16777216, false, false, 1, 2);
        gd_label.widthHint = 30;
        label.setLayoutData((Object)gd_label);
        Link shippingListLnk = new Link(composite, 0);
        shippingListLnk.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                ReportsManagerComposite.this.generateEBShippingList();
            }
        });
        shippingListLnk.setLayoutData((Object)new GridData(16384, 16777216, false, false, 2, 1));
        shippingListLnk.setText("<a>- eb shipping list</a>");
        new org.eclipse.swt.widgets.Label(composite, 0);
        new org.eclipse.swt.widgets.Label(composite, 0);
        Link varianceReportLnk = new Link(composite, 0);
        varianceReportLnk.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                try {
                    ReportsManagerComposite.this.generateVarianceReport();
                }
                catch (Exception e1) {
                    ReportsManagerComposite.this.getLog().error((Object)"error", (Throwable)e1);
                }
            }
        });
        varianceReportLnk.setLayoutData((Object)new GridData(16384, 16777216, false, false, 2, 1));
        varianceReportLnk.setText("<a>- eb variance report</a>");
        new org.eclipse.swt.widgets.Label(composite, 0);
        new org.eclipse.swt.widgets.Label(composite, 0);
        new org.eclipse.swt.widgets.Label(composite, 0);
        new org.eclipse.swt.widgets.Label(composite, 0);
        new org.eclipse.swt.widgets.Label(composite, 0);
        new org.eclipse.swt.widgets.Label(composite, 0);
        new org.eclipse.swt.widgets.Label(composite, 0);
        new org.eclipse.swt.widgets.Label(composite, 0);
        new org.eclipse.swt.widgets.Label(composite, 0);
        new org.eclipse.swt.widgets.Label(composite, 0);
        Label label_2 = new Label(composite, 258);
        label_2.setLayoutData((Object)new GridData(4, 16777216, true, false, 7, 1));
        new org.eclipse.swt.widgets.Label(composite, 0);
        new org.eclipse.swt.widgets.Label(composite, 0);
        new org.eclipse.swt.widgets.Label(composite, 0);
        new org.eclipse.swt.widgets.Label(composite, 0);
        new org.eclipse.swt.widgets.Label(composite, 0);
        Label lblUser = new Label(composite, 0);
        lblUser.setLayoutData((Object)new GridData(131072, 16777216, false, false, 1, 1));
        lblUser.setText("user:");
        this.userCbx = new Combo(composite, 8);
        this.userCbx.setLayoutData((Object)new GridData(4, 16777216, false, false, 1, 1));
        Label label_3 = new Label(composite, 514);
        GridData gd_label_3 = new GridData(16384, 16777216, false, false, 1, 2);
        gd_label_3.widthHint = 30;
        label_3.setLayoutData((Object)gd_label_3);
        Link link_2 = new Link(composite, 0);
        link_2.setLayoutData((Object)new GridData(16384, 16777216, false, false, 2, 1));
        link_2.setText("<a>- po distribution by user</a>");
        new org.eclipse.swt.widgets.Label(composite, 0);
        new org.eclipse.swt.widgets.Label(composite, 0);
        new org.eclipse.swt.widgets.Label(composite, 0);
        new org.eclipse.swt.widgets.Label(composite, 0);
        new org.eclipse.swt.widgets.Label(composite, 0);
        new org.eclipse.swt.widgets.Label(composite, 0);
        new org.eclipse.swt.widgets.Label(composite, 0);
        new org.eclipse.swt.widgets.Label(composite, 0);
        new org.eclipse.swt.widgets.Label(composite, 0);
        Label label_4 = new Label(composite, 258);
        label_4.setLayoutData((Object)new GridData(4, 16777216, true, false, 5, 1));
        new org.eclipse.swt.widgets.Label(composite, 0);
        new org.eclipse.swt.widgets.Label(composite, 0);
        new org.eclipse.swt.widgets.Label(composite, 0);
        new org.eclipse.swt.widgets.Label(composite, 0);
        new org.eclipse.swt.widgets.Label(composite, 0);
        new org.eclipse.swt.widgets.Label(composite, 0);
        new org.eclipse.swt.widgets.Label(composite, 0);
        new org.eclipse.swt.widgets.Label(composite, 0);
        new org.eclipse.swt.widgets.Label(composite, 0);
        Button btnCancel = new Button(composite, 0);
        btnCancel.addSelectionListener((SelectionListener)new SelectionAdapter(){

            public void widgetSelected(SelectionEvent e) {
                ReportsManagerComposite.this.hide();
            }
        });
        btnCancel.setLayoutData((Object)new GridData(131072, 16777216, false, false, 1, 1));
        btnCancel.setText("cancel");
        new org.eclipse.swt.widgets.Label((Composite)this, 0);
        new org.eclipse.swt.widgets.Label((Composite)this, 0);
        new org.eclipse.swt.widgets.Label((Composite)this, 0);
        new org.eclipse.swt.widgets.Label((Composite)this, 0);
        new org.eclipse.swt.widgets.Label((Composite)this, 0);
    }

    public PurchaseOrder getSelectedPurchaseOrder() {
        PurchaseOrder po = null;
        try {
            po = (PurchaseOrder)this.getPurchaseOrderCbx().getData(this.getPurchaseOrderCbx().getItem(this.getPurchaseOrderCbx().getSelectionIndex()));
        }
        catch (Exception e) {
            this.getLog().error((Object)"error", (Throwable)e);
        }
        return po;
    }

    public void generateEBShippingList() {
        block37 : {
            if (this.getSelectedPurchaseOrder() != null) {
                try {
                    XSSFWorkbook workbook = new XSSFWorkbook();
                    Font arialBold14 = workbook.createFont();
                    Font arialBlackBold10 = workbook.createFont();
                    Font calibriBold11 = workbook.createFont();
                    Font arialRedBold10 = workbook.createFont();
                    Font arialBold10 = workbook.createFont();
                    Font arialBold9 = workbook.createFont();
                    Font calibri10 = workbook.createFont();
                    arialBold14.setFontHeightInPoints(14);
                    arialBold14.setColor(IndexedColors.BLACK.getIndex());
                    arialBold14.setFontName("Arial Black");
                    arialBold14.setItalic(false);
                    arialBold14.setBoldweight(700);
                    arialBlackBold10.setFontHeightInPoints(10);
                    arialBlackBold10.setColor(IndexedColors.BLACK.getIndex());
                    arialBlackBold10.setFontName("Arial Black");
                    arialBlackBold10.setItalic(false);
                    arialBlackBold10.setBoldweight(700);
                    calibriBold11.setFontHeightInPoints(11);
                    calibriBold11.setColor(IndexedColors.BLACK.getIndex());
                    calibriBold11.setFontName("Calibri");
                    calibriBold11.setItalic(false);
                    calibriBold11.setBoldweight(700);
                    arialRedBold10.setFontHeightInPoints(10);
                    arialRedBold10.setColor(IndexedColors.RED.getIndex());
                    arialRedBold10.setFontName("Arial Black");
                    arialRedBold10.setItalic(false);
                    arialRedBold10.setBoldweight(700);
                    arialBold10.setFontHeightInPoints(10);
                    arialBold10.setColor(IndexedColors.BLACK.getIndex());
                    arialBold10.setFontName("Arial");
                    arialBold10.setItalic(false);
                    arialBold10.setBoldweight(700);
                    arialBold9.setFontHeightInPoints(9);
                    arialBold9.setColor(IndexedColors.BLACK.getIndex());
                    arialBold9.setFontName("Arial");
                    arialBold9.setItalic(false);
                    arialBold9.setBoldweight(700);
                    calibri10.setFontHeightInPoints(10);
                    calibri10.setColor(IndexedColors.BLACK.getIndex());
                    calibri10.setFontName("Calibri");
                    calibri10.setItalic(false);
                    String sheetName = this.getSelectedPurchaseOrder().getReferenceNumber().substring(2, 8);
                    Sheet sheet = workbook.createSheet(sheetName);
                    int rowIndex = 0;
                    int cellIndex = 0;
                    String folderPath = "reports/eb_shipping_list_reports/";
                    String fileName = String.valueOf(folderPath) + this.getSelectedPurchaseOrder().getReferenceNumber() + "_eb_shipping_list.xlsx";
                    File folder = new File(folderPath);
                    if (!folder.exists()) {
                        folder.mkdir();
                    }
                    Row row = sheet.createRow(rowIndex);
                    Cell cel = row.createCell(cellIndex);
                    cel.setCellValue("PACKING LIST FROM USLC APPAREL S.A.");
                    CellStyle titleStyle = workbook.createCellStyle();
                    titleStyle.setFont(arialBold14);
                    titleStyle.setAlignment(2);
                    cel.setCellStyle(titleStyle);
                    sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, cellIndex, cellIndex + 9));
                    Row addressRow = sheet.createRow(++rowIndex);
                    Cell addressCell = addressRow.createCell(cellIndex);
                    addressCell.setCellValue("DE DONDE FUE LA PEPSI 1 1/2 AL LAGO , ZONA FRANCA INDEX");
                    CellStyle addressStyle = workbook.createCellStyle();
                    addressStyle.setFont(arialBlackBold10);
                    addressStyle.setAlignment(2);
                    addressCell.setCellStyle(addressStyle);
                    sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, cellIndex, cellIndex + 9));
                    Row contactRow = sheet.createRow(++rowIndex);
                    Cell contactCell = contactRow.createCell(cellIndex);
                    contactCell.setCellValue("CONTACT PERSON: ALFREDO FERNANDEZ / TEL: 22481700");
                    CellStyle contactStyle = workbook.createCellStyle();
                    contactStyle.setFont(calibriBold11);
                    contactStyle.setAlignment(2);
                    contactCell.setCellStyle(contactStyle);
                    sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, cellIndex, cellIndex + 9));
                    Row reportTitleRow = sheet.createRow(++rowIndex);
                    Cell reportTitleCell = reportTitleRow.createCell(cellIndex);
                    reportTitleCell.setCellValue("PACKING LIST / DETAIL BY CARTON / CARTON SIZE 23x17x5.5");
                    Cell poRefNumCell = reportTitleRow.createCell(cellIndex + 11);
                    poRefNumCell.setCellValue(this.getSelectedPurchaseOrder().getReferenceNumber());
                    CellStyle reportTitleStyle = workbook.createCellStyle();
                    reportTitleStyle.setFont(arialRedBold10);
                    reportTitleStyle.setAlignment(2);
                    reportTitleCell.setCellStyle(reportTitleStyle);
                    sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, cellIndex, cellIndex + 9));
                    Row chartColumnTitleRow = sheet.createRow(++rowIndex);
                    CellStyle chartColumnStyle = workbook.createCellStyle();
                    chartColumnStyle.setFont(arialBold10);
                    chartColumnStyle.setAlignment(2);
                    chartColumnStyle.setBorderBottom(1);
                    chartColumnStyle.setBorderTop(1);
                    chartColumnStyle.setBorderLeft(1);
                    chartColumnStyle.setBorderRight(1);
                    String[] colNames = new String[]{"UPC", "DEPT", "ITEM CODE", "PO #", "COLOR NAME", "", "COLOR #", "SKU", "SIZE", "QTY"};
                    ArrayList<PackingDetail> packingDetailList = new ArrayList<PackingDetail>();
                    for (PurchaseOrderDetail pod : this.getSelectedPurchaseOrder().getPurchaseOrderDetails()) {
                        for (PackingDetail pd : pod.getPackingDetails()) {
                            packingDetailList.add(pd);
                        }
                    }
                    Collections.sort(packingDetailList, new PackageDetailComparator());
                    int i = 0;
                    while (i < 10) {
                        Cell cell = chartColumnTitleRow.createCell(cellIndex + i);
                        cell.setCellValue(colNames[i]);
                        cell.setCellStyle(chartColumnStyle);
                        ++i;
                    }
                    CellStyle dataTableCellStyle = workbook.createCellStyle();
                    dataTableCellStyle.setFont(arialBold9);
                    dataTableCellStyle.setAlignment(2);
                    dataTableCellStyle.setBorderBottom(1);
                    dataTableCellStyle.setBorderTop(1);
                    dataTableCellStyle.setBorderLeft(1);
                    dataTableCellStyle.setBorderRight(1);
                    CellStyle numbersCellStyle = workbook.createCellStyle();
                    numbersCellStyle.setFont(arialBold9);
                    numbersCellStyle.setAlignment(2);
                    CellStyle yellowCellStyle = workbook.createCellStyle();
                    yellowCellStyle.setFont(arialBold10);
                    yellowCellStyle.setAlignment(2);
                    yellowCellStyle.setBorderBottom(1);
                    yellowCellStyle.setBorderTop(1);
                    yellowCellStyle.setBorderLeft(1);
                    yellowCellStyle.setBorderRight(1);
                    yellowCellStyle.setFillForegroundColor(13);
                    yellowCellStyle.setFillPattern(1);
                    int totalScannedItems = 0;
                    int totalBoxes = 0;
                    for (PackingDetail pd : packingDetailList) {
                        Row dataRow = sheet.createRow(++rowIndex);
                        Upc upc = pd.getPurchaseOrderDetail().getUpc();
                        Size size = upc.getSize();
                        String sizeStr = String.valueOf(size.getWaist());
                        if (!pd.getPurchaseOrderDetail().getPurchaseOrder().getReferenceNumber().endsWith("11")) {
                            sizeStr = String.valueOf(sizeStr) + "x" + size.getInseam();
                        }
                        int i2 = 0;
                        while (i2 < 10) {
                            Cell cell = dataRow.createCell(i2);
                            cell.setCellStyle(dataTableCellStyle);
                            switch (i2) {
                                case 0: {
                                    cell.setCellValue(upc.getUpcCode());
                                    break;
                                }
                                case 1: {
                                    cell.setCellValue(pd.getPurchaseOrderDetail().getPurchaseOrder().getDepartmentNumber());
                                    break;
                                }
                                case 2: {
                                    cell.setCellValue(upc.getItem().getCode());
                                    break;
                                }
                                case 3: {
                                    cell.setCellValue(pd.getPurchaseOrderDetail().getPurchaseOrder().getReferenceNumber());
                                    break;
                                }
                                case 4: {
                                    cell.setCellValue(upc.getColor().getName());
                                    break;
                                }
                                case 5: {
                                    cell.setCellValue(upc.getColorItemCode());
                                    break;
                                }
                                case 6: {
                                    cell.setCellValue(upc.getColor().getNumber());
                                    break;
                                }
                                case 7: {
                                    if (!pd.getDeleted() && pd.getCarton() != null && !pd.getCarton().getDeleted()) {
                                        ++totalBoxes;
                                    }
                                    if (pd.getPurchaseOrderDetail().getPurchaseOrder().getTotalCartons() < pd.getSku()) {
                                        dataRow.getCell(7).setCellStyle(yellowCellStyle);
                                    }
                                    cell.setCellValue((double)pd.getSku());
                                    break;
                                }
                                case 8: {
                                    cell.setCellValue(sizeStr);
                                    break;
                                }
                                case 9: {
                                    int scannedItems = 0;
                                    if (!pd.getDeleted() && pd.getCarton() != null && !pd.getCarton().getDeleted()) {
                                        scannedItems = pd.getCarton() != null ? Client.getNumberOfScannedItems(pd.getCarton()) : 0;
                                        totalScannedItems += scannedItems;
                                        if (pd.getQuantity() != scannedItems) {
                                            dataRow.getCell(7).setCellStyle(yellowCellStyle);
                                        }
                                    }
                                    cell.setCellValue((double)scannedItems);
                                }
                            }
                            ++i2;
                        }
                    }
                    Row totalChartRow = sheet.createRow(++rowIndex);
                    Cell cel1 = totalChartRow.createCell(cellIndex);
                    cel1.setCellValue(1.0);
                    cel1.setCellStyle(numbersCellStyle);
                    Cell cel2 = totalChartRow.createCell(cellIndex + 1);
                    cel2.setCellValue(2.0);
                    cel2.setCellStyle(numbersCellStyle);
                    Cell cel3 = totalChartRow.createCell(cellIndex + 2);
                    cel3.setCellValue(3.0);
                    cel3.setCellStyle(numbersCellStyle);
                    Cell cel4 = totalChartRow.createCell(cellIndex + 6);
                    cel4.setCellValue(4.0);
                    cel4.setCellStyle(numbersCellStyle);
                    Cell cel5 = totalChartRow.createCell(cellIndex + 7);
                    cel5.setCellValue("TOTAL:");
                    cel5.setCellStyle(numbersCellStyle);
                    Cell cel6 = totalChartRow.createCell(cellIndex + 8);
                    cel6.setCellValue(5.0);
                    cel6.setCellStyle(numbersCellStyle);
                    Cell cel7 = totalChartRow.createCell(cellIndex + 9);
                    cel7.setCellValue((double)totalScannedItems);
                    cel7.setCellStyle(dataTableCellStyle);
                    Cell cel8 = sheet.createRow(++rowIndex).createCell(cellIndex + 9);
                    cel8.setCellValue((double)totalBoxes);
                    cel8.setCellStyle(dataTableCellStyle);
                    ++rowIndex;
                    Row summaryTitleRow = sheet.createRow(++rowIndex);
                    Cell summaryColorCell = summaryTitleRow.createCell(cellIndex);
                    summaryColorCell.setCellValue("COLOR");
                    summaryColorCell.setCellStyle(contactStyle);
                    Cell summaryUnitsCell = summaryTitleRow.createCell(cellIndex + 2);
                    summaryUnitsCell.setCellValue("UNITS");
                    summaryUnitsCell.setCellStyle(contactStyle);
                    Cell summaryBoxesCell = summaryTitleRow.createCell(cellIndex + 3);
                    summaryBoxesCell.setCellValue("BOXES");
                    summaryBoxesCell.setCellStyle(contactStyle);
                    ClientPurchaseOrderSumary cpos = new ClientPurchaseOrderSumary(this.getSelectedPurchaseOrder());
                    Iterator<Map.Entry<String, ClientPurchaseOrderSumary.PurchaseOrderStats>> it = cpos.getPoInfo().entrySet().iterator();
                    CellStyle calibriBordered10 = workbook.createCellStyle();
                    calibriBordered10.setFont(calibri10);
                    calibriBordered10.setAlignment(2);
                    calibriBordered10.setBorderBottom(1);
                    calibriBordered10.setBorderTop(1);
                    calibriBordered10.setBorderLeft(1);
                    calibriBordered10.setBorderRight(1);
                    int totalSummaryQty = 0;
                    int totalSummaryBoxes = 0;
                    while (it.hasNext()) {
                        Map.Entry<String, ClientPurchaseOrderSumary.PurchaseOrderStats> entry = it.next();
                        Row summaryRow = sheet.createRow(++rowIndex);
                        Cell dataColorCell = summaryRow.createCell(cellIndex);
                        dataColorCell.setCellValue(entry.getKey());
                        dataColorCell.setCellStyle(dataTableCellStyle);
                        Cell dataUnitsCell = summaryRow.createCell(cellIndex + 2);
                        dataUnitsCell.setCellValue((double)entry.getValue().getWorkedQty());
                        dataUnitsCell.setCellStyle(calibriBordered10);
                        Cell dataBoxesCell = summaryRow.createCell(cellIndex + 3);
                        dataBoxesCell.setCellValue((double)entry.getValue().getWorkedBoxes());
                        dataBoxesCell.setCellStyle(calibriBordered10);
                        totalSummaryQty += entry.getValue().getWorkedQty();
                        totalSummaryBoxes += entry.getValue().getWorkedBoxes();
                    }
                    Row summaryTotalRow = sheet.createRow(++rowIndex);
                    Cell summaryTotalColorCell = summaryTotalRow.createCell(cellIndex);
                    summaryTotalColorCell.setCellValue("TOTAL BY PO");
                    summaryTotalColorCell.setCellStyle(contactStyle);
                    Cell summaryTotalUnitsCell = summaryTotalRow.createCell(cellIndex + 2);
                    summaryTotalUnitsCell.setCellValue((double)totalSummaryQty);
                    summaryTotalUnitsCell.setCellStyle(contactStyle);
                    Cell summaryTotalBoxesCell = summaryTotalRow.createCell(cellIndex + 3);
                    summaryTotalBoxesCell.setCellValue((double)totalSummaryBoxes);
                    summaryTotalBoxesCell.setCellStyle(contactStyle);
                    int i3 = 0;
                    while (i3 < 10) {
                        sheet.autoSizeColumn(i3);
                        ++i3;
                    }
                    CellStyle colorItemTitleStyle = workbook.createCellStyle();
                    colorItemTitleStyle.setFont(arialBold9);
                    CellStyle yellowSizeTitleCellStyle = workbook.createCellStyle();
                    yellowSizeTitleCellStyle.setFont(calibri10);
                    yellowSizeTitleCellStyle.setAlignment(2);
                    yellowSizeTitleCellStyle.setFillForegroundColor(13);
                    yellowSizeTitleCellStyle.setFillPattern(1);
                    yellowSizeTitleCellStyle.setBorderBottom(2);
                    yellowSizeTitleCellStyle.setBorderTop(2);
                    yellowSizeTitleCellStyle.setBorderLeft(2);
                    yellowSizeTitleCellStyle.setBorderRight(2);
                    HSSFPalette palette = new HSSFWorkbook().getCustomPalette();
                    palette.setColorAtIndex(45, -1, -39, -17);
                    palette.setColorAtIndex(50, 102, -52, -1);
                    CellStyle lightBlueSizeCellStyle = workbook.createCellStyle();
                    lightBlueSizeCellStyle.setFont(calibri10);
                    lightBlueSizeCellStyle.setAlignment(2);
                    lightBlueSizeCellStyle.setFillForegroundColor(50);
                    lightBlueSizeCellStyle.setFillPattern(1);
                    lightBlueSizeCellStyle.setBorderBottom(2);
                    lightBlueSizeCellStyle.setBorderTop(2);
                    lightBlueSizeCellStyle.setBorderLeft(2);
                    lightBlueSizeCellStyle.setBorderRight(2);
                    CellStyle countMediumBorderedCellStyle = workbook.createCellStyle();
                    countMediumBorderedCellStyle.setFont(calibri10);
                    countMediumBorderedCellStyle.setAlignment(2);
                    countMediumBorderedCellStyle.setBorderBottom(2);
                    countMediumBorderedCellStyle.setBorderTop(2);
                    countMediumBorderedCellStyle.setBorderLeft(2);
                    countMediumBorderedCellStyle.setBorderRight(2);
                    CellStyle percentageFucsiaBorderedCellStyle = workbook.createCellStyle();
                    percentageFucsiaBorderedCellStyle.setFont(calibri10);
                    percentageFucsiaBorderedCellStyle.setAlignment(2);
                    percentageFucsiaBorderedCellStyle.setFillForegroundColor(45);
                    percentageFucsiaBorderedCellStyle.setFillPattern(1);
                    percentageFucsiaBorderedCellStyle.setBorderBottom(2);
                    percentageFucsiaBorderedCellStyle.setBorderTop(2);
                    percentageFucsiaBorderedCellStyle.setBorderLeft(2);
                    percentageFucsiaBorderedCellStyle.setBorderRight(2);
                    percentageFucsiaBorderedCellStyle.setDataFormat(workbook.createDataFormat().getFormat("0%"));
                    CellStyle percentageBorderedCellStyle = workbook.createCellStyle();
                    percentageBorderedCellStyle.setFont(calibri10);
                    percentageBorderedCellStyle.setAlignment(2);
                    percentageBorderedCellStyle.setBorderBottom(2);
                    percentageBorderedCellStyle.setBorderTop(2);
                    percentageBorderedCellStyle.setBorderLeft(2);
                    percentageBorderedCellStyle.setBorderRight(2);
                    percentageBorderedCellStyle.setDataFormat(workbook.createDataFormat().getFormat("0%"));
                    rowIndex = 4;
                    cellIndex = 11;
                    SizeDistributionByColorReport sdbcr = new SizeDistributionByColorReport(this.getSelectedPurchaseOrder());
                    for (Map.Entry<String, SizeDistributionByColorReport.ColorScanDistribution> entry : sdbcr.getColorSizeScanDistribution().entrySet()) {
                        Row colorItemRow = this.getRowAt(rowIndex, sheet);
                        Cell colorItemCell = colorItemRow.createCell(cellIndex);
                        colorItemCell.setCellValue(entry.getKey());
                        colorItemCell.setCellStyle(colorItemTitleStyle);
                        Row colorItemTitleRow = this.getRowAt(++rowIndex, sheet);
                        Cell sizeTitleCell = colorItemTitleRow.createCell(cellIndex);
                        sizeTitleCell.setCellValue("SIZE");
                        sizeTitleCell.setCellStyle(yellowSizeTitleCellStyle);
                        Cell poQtyTitleCell = colorItemTitleRow.createCell(cellIndex + 1);
                        poQtyTitleCell.setCellValue("PO QTY");
                        poQtyTitleCell.setCellStyle(yellowSizeTitleCellStyle);
                        Cell qtyPackTitleCell = colorItemTitleRow.createCell(cellIndex + 2);
                        qtyPackTitleCell.setCellValue("QTY PACK");
                        qtyPackTitleCell.setCellStyle(yellowSizeTitleCellStyle);
                        Cell difTitleCell = colorItemTitleRow.createCell(cellIndex + 3);
                        difTitleCell.setCellValue("DIF");
                        difTitleCell.setCellStyle(yellowSizeTitleCellStyle);
                        Cell percentageTitleCell = colorItemTitleRow.createCell(cellIndex + 4);
                        percentageTitleCell.setCellValue("%");
                        percentageTitleCell.setCellStyle(yellowSizeTitleCellStyle);
                        int totalPoQty = 0;
                        int totalPackedQty = 0;
                        int totalDif = 0;
                        for (SizeDistributionByColorReport.SizeScanDistribution ssd : entry.getValue().getSizeScanDistribution()) {
                            Row colorItemDataRow = this.getRowAt(++rowIndex, sheet);
                            String sizeStr = "";
                            sizeStr = this.getSelectedPurchaseOrder().getReferenceNumber().endsWith("11") ? String.valueOf(ssd.getSize().getWaist()) : String.valueOf(ssd.getSize().getWaist()) + "x" + ssd.getSize().getInseam();
                            Cell size = colorItemDataRow.createCell(cellIndex);
                            size.setCellValue(sizeStr);
                            size.setCellStyle(lightBlueSizeCellStyle);
                            Cell poQty = colorItemDataRow.createCell(cellIndex + 1);
                            poQty.setCellValue((double)ssd.getPoQty());
                            poQty.setCellStyle(countMediumBorderedCellStyle);
                            Cell packedQty = colorItemDataRow.createCell(cellIndex + 2);
                            packedQty.setCellValue((double)ssd.getPackedQty());
                            packedQty.setCellStyle(countMediumBorderedCellStyle);
                            Cell diff = colorItemDataRow.createCell(cellIndex + 3);
                            diff.setCellValue((double)(ssd.getPoQty() - ssd.getPackedQty()));
                            diff.setCellStyle(countMediumBorderedCellStyle);
                            double poQtyF = ssd.getPoQty();
                            double packedQtyF = ssd.getPackedQty();
                            double perc = 1.0 - (poQtyF - packedQtyF) / poQtyF;
                            Cell percentage = colorItemDataRow.createCell(cellIndex + 4);
                            percentage.setCellValue(perc);
                            percentage.setCellStyle(percentageFucsiaBorderedCellStyle);
                            if (perc < 0.95 || perc > 1.05) {
                                percentage.setCellStyle(percentageFucsiaBorderedCellStyle);
                            } else {
                                percentage.setCellStyle(percentageBorderedCellStyle);
                            }
                            totalPoQty += ssd.getPoQty();
                            totalPackedQty += ssd.getPackedQty();
                            totalDif += ssd.getPoQty() - ssd.getPackedQty();
                        }
                        Row blankRow = this.getRowAt(++rowIndex, sheet);
                        blankRow.createCell(cellIndex).setCellStyle(lightBlueSizeCellStyle);
                        blankRow.createCell(cellIndex + 1).setCellStyle(countMediumBorderedCellStyle);
                        blankRow.createCell(cellIndex + 2).setCellStyle(countMediumBorderedCellStyle);
                        blankRow.createCell(cellIndex + 3).setCellStyle(countMediumBorderedCellStyle);
                        blankRow.createCell(cellIndex + 4).setCellStyle(percentageFucsiaBorderedCellStyle);
                        Row totalColorSizeScanRow = this.getRowAt(++rowIndex, sheet);
                        Cell totalPoQtyCell = totalColorSizeScanRow.createCell(cellIndex + 1);
                        totalPoQtyCell.setCellValue((double)totalPoQty);
                        totalPoQtyCell.setCellStyle(yellowSizeTitleCellStyle);
                        Cell totalPackedQtyCell = totalColorSizeScanRow.createCell(cellIndex + 2);
                        totalPackedQtyCell.setCellValue((double)totalPackedQty);
                        totalPackedQtyCell.setCellStyle(yellowSizeTitleCellStyle);
                        Cell totalDifCell = totalColorSizeScanRow.createCell(cellIndex + 3);
                        totalDifCell.setCellValue((double)totalPoQty);
                        totalDifCell.setCellStyle(yellowSizeTitleCellStyle);
                        ++rowIndex;
                        ++rowIndex;
                    }
                    try {
                        File f = new File(fileName);
                        FileOutputStream out = new FileOutputStream(f);
                        workbook.write((OutputStream)out);
                        out.close();
                        if (this.getQuestionBox("the report was generated at " + f.getAbsolutePath() + "\nwould you like to open it?").open() != 64) break block37;
                        Desktop desktop = null;
                        if (!Desktop.isDesktopSupported()) break block37;
                        desktop = Desktop.getDesktop();
                        try {
                            desktop.open(f);
                        }
                        catch (IOException e) {
                            this.getLog().error((Object)"error", (Throwable)e);
                        }
                    }
                    catch (FileNotFoundException e) {
                        this.getLog().error((Object)"error", (Throwable)e);
                    }
                    catch (IOException e) {
                        this.getLog().error((Object)"error", (Throwable)e);
                    }
                }
                catch (Exception e) {
                    this.getLog().error((Object)"error", (Throwable)e);
                }
            }
        }
    }

    public void setMergedCells(Sheet sheet, int rowFrom, int rowTo, int cellFrom, int cellTo) {
        sheet.addMergedRegion(new CellRangeAddress(rowFrom, rowTo, cellFrom, cellTo));
    }

    public void generateVarianceReport() throws Exception {
        if (this.getSelectedPurchaseOrder() != null) {
            XSSFWorkbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Variance Report");
            sheet.setZoom(9, 10);
            sheet.createFreezePane(4, 6);
            int rowIndex = 0;
            int cellIndex = 0;
            PoComparativeByColorItemSize poComp = new PoComparativeByColorItemSize(this.getSelectedPurchaseOrder());
            Font arialGreen8 = workbook.createFont();
            arialGreen8.setColor(IndexedColors.GREEN.index);
            arialGreen8.setFontHeightInPoints(8);
            arialGreen8.setFontName("Arial");
            Font arialGreenBold10 = workbook.createFont();
            arialGreenBold10.setColor(IndexedColors.GREEN.index);
            arialGreenBold10.setFontHeightInPoints(10);
            arialGreenBold10.setFontName("Arial");
            arialGreenBold10.setBoldweight(700);
            Font arial20 = workbook.createFont();
            arial20.setFontName("Arial");
            arial20.setColor(IndexedColors.BLACK.index);
            arial20.setFontHeightInPoints(20);
            arial20.setItalic(false);
            Font arial10 = workbook.createFont();
            arial10.setFontName("Arial");
            arial10.setBoldweight(400);
            arial10.setItalic(false);
            arial10.setFontHeightInPoints(10);
            arial10.setColor(IndexedColors.BLACK.index);
            Font arialRed10 = workbook.createFont();
            arialRed10.setFontName("Arial");
            arialRed10.setBoldweight(400);
            arialRed10.setItalic(false);
            arialRed10.setFontHeightInPoints(10);
            arialRed10.setColor(IndexedColors.RED.index);
            Font arialBlue10 = workbook.createFont();
            arialBlue10.setFontName("Arial");
            arialBlue10.setBoldweight(400);
            arialBlue10.setItalic(false);
            arialBlue10.setFontHeightInPoints(10);
            arialBlue10.setColor(IndexedColors.BLUE.index);
            Font arialBold10 = workbook.createFont();
            arialBold10.setFontName("Arial");
            arialBold10.setBoldweight(700);
            arialBold10.setItalic(false);
            arialBold10.setFontHeightInPoints(10);
            arialBold10.setColor(IndexedColors.BLACK.index);
            Font arialBold14 = workbook.createFont();
            arialBold14.setFontName("Arial");
            arialBold14.setItalic(false);
            arialBold14.setFontHeightInPoints(14);
            arialBold14.setColor(IndexedColors.BLACK.index);
            arialBold14.setBoldweight(700);
            Font arialBold12 = workbook.createFont();
            arialBold12.setFontName("Arial");
            arialBold12.setBoldweight(700);
            arialBold12.setFontHeightInPoints(12);
            arialBold12.setColor(IndexedColors.BLACK.index);
            arialBold12.setItalic(false);
            CellStyle titleStyle = workbook.createCellStyle();
            titleStyle.setAlignment(2);
            titleStyle.setFont(arial20);
            CellStyle arialBold10Style = workbook.createCellStyle();
            arialBold10Style.setFont(arialBold10);
            CellStyle arial10CenteredCellStyle = workbook.createCellStyle();
            arial10CenteredCellStyle.setFont(arial10);
            arial10CenteredCellStyle.setAlignment(2);
            CellStyle titleArial14BoldCellStyle = workbook.createCellStyle();
            titleArial14BoldCellStyle.setFont(arialBold14);
            titleArial14BoldCellStyle.setAlignment(2);
            titleArial14BoldCellStyle.setBorderBottom(1);
            CellStyle titleArial12BoldCellStyle = workbook.createCellStyle();
            titleArial12BoldCellStyle.setFont(arialBold12);
            titleArial12BoldCellStyle.setBorderBottom(1);
            titleArial12BoldCellStyle.setBorderTop(1);
            titleArial12BoldCellStyle.setBorderRight(1);
            titleArial12BoldCellStyle.setBorderLeft(1);
            CellStyle colorItemCellStyle = workbook.createCellStyle();
            colorItemCellStyle.setAlignment(2);
            colorItemCellStyle.setFont(arialBold10);
            colorItemCellStyle.setFillForegroundColor(44);
            colorItemCellStyle.setFillPattern(1);
            colorItemCellStyle.setBorderBottom(1);
            colorItemCellStyle.setBorderTop(1);
            colorItemCellStyle.setBorderRight(1);
            colorItemCellStyle.setBorderLeft(1);
            CellStyle orderedCountsCellStyle = workbook.createCellStyle();
            orderedCountsCellStyle.setAlignment(2);
            orderedCountsCellStyle.setFillForegroundColor(44);
            orderedCountsCellStyle.setFillPattern(1);
            orderedCountsCellStyle.setFont(arial10);
            CellStyle shippedBlueCellStyle = workbook.createCellStyle();
            shippedBlueCellStyle.setAlignment(2);
            shippedBlueCellStyle.setFillForegroundColor(44);
            shippedBlueCellStyle.setFillPattern(1);
            shippedBlueCellStyle.setFont(arialBlue10);
            CellStyle differenceCellStyle = workbook.createCellStyle();
            differenceCellStyle.setFont(arial10);
            differenceCellStyle.setBorderBottom(1);
            differenceCellStyle.setBorderTop(1);
            differenceCellStyle.setBorderLeft(1);
            differenceCellStyle.setBorderRight(1);
            differenceCellStyle.setAlignment(2);
            CellStyle totalDifferenceCellStyle = workbook.createCellStyle();
            totalDifferenceCellStyle.setFont(arialGreen8);
            totalDifferenceCellStyle.setBorderBottom(1);
            totalDifferenceCellStyle.setBorderTop(1);
            totalDifferenceCellStyle.setBorderLeft(1);
            totalDifferenceCellStyle.setBorderRight(1);
            totalDifferenceCellStyle.setAlignment(2);
            CellStyle shippedWordCellStyle = workbook.createCellStyle();
            shippedWordCellStyle.setFont(arialBlue10);
            CellStyle differenceWordCellStyle = workbook.createCellStyle();
            differenceWordCellStyle.setFont(arialBold10);
            differenceWordCellStyle.setBorderBottom(1);
            differenceWordCellStyle.setBorderTop(1);
            differenceWordCellStyle.setBorderLeft(1);
            differenceWordCellStyle.setBorderRight(1);
            CellStyle percentageWordCellStyle = workbook.createCellStyle();
            percentageWordCellStyle.setFont(arialGreenBold10);
            percentageWordCellStyle.setBorderBottom(1);
            percentageWordCellStyle.setBorderTop(1);
            percentageWordCellStyle.setBorderLeft(1);
            percentageWordCellStyle.setBorderRight(1);
            CellStyle differenceRedCellStyle = workbook.createCellStyle();
            differenceRedCellStyle.setFont(arialRed10);
            differenceRedCellStyle.setBorderBottom(1);
            differenceRedCellStyle.setBorderTop(1);
            differenceRedCellStyle.setBorderLeft(1);
            differenceRedCellStyle.setBorderRight(1);
            CellStyle shippedBlueTransparentCellStyle = workbook.createCellStyle();
            shippedBlueTransparentCellStyle.setFont(arialBlue10);
            shippedBlueTransparentCellStyle.setAlignment(2);
            CellStyle percentageGreenCellStyle = workbook.createCellStyle();
            percentageGreenCellStyle.setFont(arialGreen8);
            percentageGreenCellStyle.setAlignment(2);
            percentageGreenCellStyle.setBorderBottom(1);
            percentageGreenCellStyle.setBorderTop(1);
            percentageGreenCellStyle.setBorderRight(1);
            percentageGreenCellStyle.setBorderLeft(1);
            percentageGreenCellStyle.setDataFormat(workbook.createDataFormat().getFormat("0%"));
            Row reportTitleRow = this.getRowAt(rowIndex, sheet);
            Cell reportTitleCell = reportTitleRow.createCell(cellIndex);
            reportTitleCell.setCellValue("Eddie Bauer Variance Report");
            reportTitleCell.setCellStyle(titleStyle);
            this.setMergedCells(sheet, rowIndex, rowIndex, cellIndex, cellIndex + poComp.getSizeList().size() + 6);
            this.getRowAt(++rowIndex, sheet, arialBold10Style).createCell(cellIndex).setCellValue("DRN/Style Description:");
            this.getRowAt(++rowIndex, sheet, arialBold10Style).createCell(cellIndex).setCellValue("P.O. Number: " + this.getSelectedPurchaseOrder().getReferenceNumber());
            this.getRowAt(++rowIndex, sheet, arialBold10Style).createCell(cellIndex).setCellValue("Ship Date: ");
            ++rowIndex;
            Cell titleItem = this.getRowAt(++rowIndex, sheet).createCell(cellIndex);
            titleItem.setCellStyle(titleArial14BoldCellStyle);
            Cell titlePrt = this.getRowAt(rowIndex, sheet).createCell(cellIndex + 1);
            titlePrt.setCellStyle(titleArial14BoldCellStyle);
            Cell titleColor = this.getRowAt(rowIndex, sheet).createCell(cellIndex + 2);
            titleColor.setCellStyle(titleArial14BoldCellStyle);
            Cell titleSize = this.getRowAt(rowIndex, sheet).createCell(cellIndex + 3);
            titleSize.setCellStyle(titleArial14BoldCellStyle);
            titleItem.setCellValue("Item #");
            titlePrt.setCellValue("P-R-T");
            titleColor.setCellValue("Color");
            titleSize.setCellValue("Size");
            int i = 0;
            while (i < poComp.getSizeList().size()) {
                Cell titleSizeCell = this.getRowAt(rowIndex, sheet).createCell(cellIndex + 4 + i);
                titleSizeCell.setCellStyle(titleArial12BoldCellStyle);
                Object sizeStr = String.valueOf(poComp.getSizeList().get(i).getWaist());
                if (!this.getSelectedPurchaseOrder().getReferenceNumber().endsWith("11")) {
                    sizeStr = String.valueOf(sizeStr) + "x" + poComp.getSizeList().get(i).getInseam();
                }
                titleSizeCell.setCellValue((String)sizeStr);
                ++i;
            }
            Cell totalCell = this.getRowAt(rowIndex, sheet).createCell(cellIndex + 4 + poComp.getSizeList().size() + 1);
            totalCell.setCellValue("Total");
            totalCell.setCellStyle(titleArial14BoldCellStyle);
            for (PoComparativeByColorItemSize.ItemColorSizeCount icsc : poComp.getItemColorSizeCountList()) {
                ++rowIndex;
                ++rowIndex;
                Cell itemValueCell = this.getRowAt(++rowIndex, sheet).createCell(cellIndex);
                itemValueCell.setCellStyle(colorItemCellStyle);
                itemValueCell.setCellValue(icsc.getItem().getCode());
                Cell prtValueCell = this.getRowAt(rowIndex, sheet).createCell(cellIndex + 1);
                prtValueCell.setCellStyle(colorItemCellStyle);
                prtValueCell.setCellValue("Regular");
                Cell colorValueCell = this.getRowAt(rowIndex, sheet).createCell(cellIndex + 2);
                colorValueCell.setCellStyle(colorItemCellStyle);
                colorValueCell.setCellValue(icsc.getColor().getName());
                Cell sizeValueCell = this.getRowAt(rowIndex, sheet).createCell(cellIndex + 3);
                sizeValueCell.setCellStyle(colorItemCellStyle);
                int i2 = 0;
                while (i2 < icsc.getSizeCountsList().size()) {
                    Cell sizeCountCell = this.getRowAt(rowIndex, sheet).createCell(cellIndex + 4 + i2);
                    sizeCountCell.setCellStyle(orderedCountsCellStyle);
                    sizeCountCell.setCellValue((double)icsc.getSizeCountsList().get(i2).getOrdered());
                    ++i2;
                }
                Cell totalOrderedCell = this.getRowAt(rowIndex, sheet).createCell(cellIndex + 4 + poComp.getSizeList().size() + 1);
                totalOrderedCell.setCellValue((double)icsc.getTotalOrdered());
                totalOrderedCell.setCellStyle(arial10CenteredCellStyle);
                this.getRowAt(rowIndex, sheet).createCell(cellIndex + 4 + poComp.getSizeList().size() + 2).setCellValue("Ordered");
                ++rowIndex;
                ++rowIndex;
                int i3 = 0;
                while (i3 < icsc.getSizeCountsList().size()) {
                    Cell sizeCountCell = this.getRowAt(rowIndex, sheet).createCell(cellIndex + 4 + i3);
                    if (icsc.getSizeCountsList().get(i3).getDifference() != 0) {
                        sizeCountCell.setCellStyle(shippedBlueCellStyle);
                    } else {
                        sizeCountCell.setCellStyle(orderedCountsCellStyle);
                    }
                    sizeCountCell.setCellValue((double)icsc.getSizeCountsList().get(i3).getShipped());
                    ++i3;
                }
                Cell totalShippedCell = this.getRowAt(rowIndex, sheet).createCell(cellIndex + 4 + poComp.getSizeList().size() + 1);
                totalShippedCell.setCellStyle(shippedBlueTransparentCellStyle);
                totalShippedCell.setCellValue((double)icsc.getTotalShipped());
                Cell shippedWordCell = this.getRowAt(rowIndex, sheet).createCell(cellIndex + 4 + poComp.getSizeList().size() + 2);
                shippedWordCell.setCellStyle(shippedWordCellStyle);
                shippedWordCell.setCellValue("Shipped");
                ++rowIndex;
                ++rowIndex;
                int i4 = 0;
                while (i4 < icsc.getSizeCountsList().size()) {
                    Cell sizeCountCell = this.getRowAt(rowIndex, sheet).createCell(cellIndex + 4 + i4);
                    if (icsc.getSizeCountsList().get(i4).getDifference() < 0) {
                        sizeCountCell.setCellStyle(differenceRedCellStyle);
                    } else {
                        sizeCountCell.setCellStyle(differenceCellStyle);
                    }
                    sizeCountCell.setCellValue((double)icsc.getSizeCountsList().get(i4).getDifference());
                    ++i4;
                }
                Cell totalDifferenceCell = this.getRowAt(rowIndex, sheet).createCell(cellIndex + 4 + poComp.getSizeList().size() + 1);
                totalDifferenceCell.setCellValue((double)icsc.getTotalDifference());
                totalDifferenceCell.setCellStyle(totalDifferenceCellStyle);
                Cell differenceWordCell = this.getRowAt(rowIndex, sheet).createCell(cellIndex + 4 + poComp.getSizeList().size() + 2);
                differenceWordCell.setCellValue("Difference");
                differenceWordCell.setCellStyle(differenceWordCellStyle);
                ++rowIndex;
                int i5 = 0;
                while (i5 < icsc.getSizeCountsList().size()) {
                    Cell sizeCountCell = this.getRowAt(rowIndex, sheet).createCell(cellIndex + 4 + i5);
                    sizeCountCell.setCellStyle(percentageGreenCellStyle);
                    sizeCountCell.setCellValue(icsc.getSizeCountsList().get(i5).getPercentage());
                    ++i5;
                }
                Cell totalPercentageCell = this.getRowAt(rowIndex, sheet).createCell(cellIndex + 4 + poComp.getSizeList().size() + 1);
                totalPercentageCell.setCellValue(icsc.getTotalPercentage());
                totalPercentageCell.setCellStyle(percentageGreenCellStyle);
                Cell percentageWordCell = this.getRowAt(rowIndex, sheet).createCell(cellIndex + 4 + poComp.getSizeList().size() + 2);
                percentageWordCell.setCellValue("Percentage");
                percentageWordCell.setCellStyle(percentageWordCellStyle);
            }
            ++rowIndex;
            ++rowIndex;
            ++rowIndex;
            CellStyle leftTopBorderedCornerCellStyle = workbook.createCellStyle();
            leftTopBorderedCornerCellStyle.setBorderTop(6);
            leftTopBorderedCornerCellStyle.setBorderLeft(6);
            CellStyle topBorderedCornerCellStyle = workbook.createCellStyle();
            topBorderedCornerCellStyle.setBorderTop(6);
            CellStyle leftBorderedCornerCellStyle = workbook.createCellStyle();
            leftBorderedCornerCellStyle.setBorderLeft(6);
            CellStyle rightBorderedCornerCellStyle = workbook.createCellStyle();
            rightBorderedCornerCellStyle.setBorderRight(6);
            CellStyle bottomBorderedCornerCellStyle = workbook.createCellStyle();
            bottomBorderedCornerCellStyle.setBorderBottom(6);
            CellStyle rightTopBorderedCornerCellStyle = workbook.createCellStyle();
            rightTopBorderedCornerCellStyle.setBorderTop(6);
            rightTopBorderedCornerCellStyle.setBorderRight(6);
            CellStyle leftBottomBorderedCornerCellStyle = workbook.createCellStyle();
            leftBottomBorderedCornerCellStyle.setBorderBottom(6);
            leftBottomBorderedCornerCellStyle.setBorderLeft(6);
            CellStyle rightBottomBorderedCornerCellStyle = workbook.createCellStyle();
            rightBottomBorderedCornerCellStyle.setBorderBottom(6);
            rightBottomBorderedCornerCellStyle.setBorderRight(6);
            this.getRowAt(++rowIndex, sheet).createCell(cellIndex).setCellStyle(leftTopBorderedCornerCellStyle);
            ++cellIndex;
            int i6 = 0;
            while (i6 < poComp.getTotalBySizes().size() + 6) {
                this.getRowAt(rowIndex, sheet).createCell(cellIndex).setCellStyle(topBorderedCornerCellStyle);
                ++cellIndex;
                ++i6;
            }
            this.getRowAt(rowIndex, sheet).createCell(cellIndex).setCellStyle(rightTopBorderedCornerCellStyle);
            cellIndex = 0;
            ++rowIndex;
            i6 = 0;
            while (i6 < 6) {
                this.getRowAt(rowIndex + i6, sheet).createCell(cellIndex).setCellStyle(leftBorderedCornerCellStyle);
                ++i6;
            }
            this.getRowAt(rowIndex, sheet).createCell(cellIndex += 2).setCellValue("TOTAL");
            cellIndex += 2;
            int totalOrdered = 0;
            int totalShipped = 0;
            boolean totalDifference = false;
            int i7 = 0;
            while (i7 < poComp.getTotalBySizes().size()) {
                Cell cellOrdered = this.getRowAt(rowIndex, sheet).createCell(cellIndex);
                cellOrdered.setCellValue((double)poComp.getTotalBySizes().get(i7).getOrdered());
                cellOrdered.setCellStyle(arial10CenteredCellStyle);
                Cell cellShipped = this.getRowAt(rowIndex + 2, sheet).createCell(cellIndex);
                cellShipped.setCellValue((double)poComp.getTotalBySizes().get(i7).getShipped());
                cellShipped.setCellStyle(shippedBlueTransparentCellStyle);
                Cell cellDifference = this.getRowAt(rowIndex + 4, sheet).createCell(cellIndex);
                cellDifference.setCellValue((double)poComp.getTotalBySizes().get(i7).getDifference());
                cellDifference.setCellStyle(differenceCellStyle);
                Cell cellPercentage = this.getRowAt(rowIndex + 5, sheet).createCell(cellIndex);
                cellPercentage.setCellValue(poComp.getTotalBySizes().get(i7).getPercentage());
                cellPercentage.setCellStyle(percentageGreenCellStyle);
                totalOrdered += poComp.getTotalBySizes().get(i7).getOrdered();
                totalShipped += poComp.getTotalBySizes().get(i7).getShipped();
                ++cellIndex;
                ++i7;
            }
            Cell grandTotalOrdered = this.getRowAt(rowIndex, sheet).createCell(++cellIndex);
            grandTotalOrdered.setCellValue((double)totalOrdered);
            grandTotalOrdered.setCellStyle(arial10CenteredCellStyle);
            Cell grandTotalShipped = this.getRowAt(rowIndex + 2, sheet).createCell(cellIndex);
            grandTotalShipped.setCellValue((double)totalShipped);
            grandTotalShipped.setCellStyle(shippedBlueTransparentCellStyle);
            Cell grandTotalDifference = this.getRowAt(rowIndex + 4, sheet).createCell(cellIndex);
            grandTotalDifference.setCellValue((double)totalDifference ? 1 : 0);
            grandTotalDifference.setCellStyle(totalDifferenceCellStyle);
            Cell grandTotalPercentage = this.getRowAt(rowIndex + 5, sheet).createCell(cellIndex);
            grandTotalPercentage.setCellValue(new Double(totalShipped) / new Double(totalOrdered));
            grandTotalPercentage.setCellStyle(percentageGreenCellStyle);
            this.getRowAt(rowIndex, sheet).createCell(++cellIndex).setCellValue("Ordered");
            Cell shippedWordCell = this.getRowAt(rowIndex + 2, sheet).createCell(cellIndex);
            shippedWordCell.setCellStyle(shippedWordCellStyle);
            shippedWordCell.setCellValue("Shipped");
            Cell differenceWordCell = this.getRowAt(rowIndex + 4, sheet).createCell(cellIndex);
            differenceWordCell.setCellValue("Difference");
            differenceWordCell.setCellStyle(differenceWordCellStyle);
            Cell percentageWordCell = this.getRowAt(rowIndex + 5, sheet).createCell(cellIndex);
            percentageWordCell.setCellValue("Percentage");
            percentageWordCell.setCellStyle(percentageWordCellStyle);
            ++cellIndex;
            int i8 = 0;
            while (i8 < 6) {
                this.getRowAt(rowIndex + i8, sheet).createCell(cellIndex).setCellStyle(rightBorderedCornerCellStyle);
                ++i8;
            }
            cellIndex = 0;
            this.getRowAt(rowIndex += 6, sheet).createCell(cellIndex).setCellStyle(leftBottomBorderedCornerCellStyle);
            ++cellIndex;
            i8 = 0;
            while (i8 < poComp.getTotalBySizes().size() + 6) {
                this.getRowAt(rowIndex, sheet).createCell(cellIndex).setCellStyle(bottomBorderedCornerCellStyle);
                ++cellIndex;
                ++i8;
            }
            this.getRowAt(rowIndex, sheet).createCell(cellIndex).setCellStyle(rightBottomBorderedCornerCellStyle);
            cellIndex = 0;
            Cell lastUpdateCell = this.getRowAt(rowIndex += 2, sheet).createCell(cellIndex);
            lastUpdateCell.setCellValue("last update " + Client.getSimpleDateFormat().format(Calendar.getInstance().getTime()));
            int i9 = 0;
            while (i9 < poComp.getSizeList().size() + 7) {
                sheet.autoSizeColumn(i9);
                ++i9;
            }
            this.getLog().info((Object)("column width: " + sheet.getColumnWidth(poComp.getSizeList().size() + 8)));
            sheet.setColumnWidth(poComp.getSizeList().size() + 7, 10);
            i9 = 0;
            while (i9 < rowIndex) {
                this.getRowAt(rowIndex + i9, sheet).setHeight(0);
                ++i9;
            }
            try {
                String folderPath = "reports/quantity_variance_po/";
                String fileName = String.valueOf(folderPath) + "Quantity Variance PO " + this.getSelectedPurchaseOrder().getReferenceNumber().substring(0, 8) + ".xlsx";
                File folder = new File(folderPath);
                if (!folder.exists()) {
                    folder.mkdir();
                }
                File f = new File(fileName);
                FileOutputStream out = new FileOutputStream(f);
                workbook.write((OutputStream)out);
                out.close();
                if (this.getQuestionBox("the report was generated at " + f.getAbsolutePath() + "\nwould you like to open it?").open() == 64) {
                    Desktop desktop = null;
                    if (Desktop.isDesktopSupported()) {
                        desktop = Desktop.getDesktop();
                        try {
                            desktop.open(f);
                        }
                        catch (IOException e) {
                            this.getLog().error((Object)"error", (Throwable)e);
                        }
                    }
                }
            }
            catch (FileNotFoundException e) {
                this.getLog().error((Object)"error", (Throwable)e);
            }
            catch (IOException e) {
                this.getLog().error((Object)"error", (Throwable)e);
            }
        }
    }

    protected void checkSubclass() {
    }

    public Row getRowAt(int rowIndex, Sheet sheet, CellStyle style) {
        Row row = null;
        try {
            row = this.getRowAt(rowIndex, sheet);
            row.setRowStyle(style);
        }
        catch (Exception e) {
            this.getLog().error((Object)"error", (Throwable)e);
        }
        return row;
    }

    public Row getRowAt(int rowIndex, Sheet sheet) {
        Row row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex);
        }
        return row;
    }

    public void hide() {
        this.setParent((Composite)this.getParent().getMaster().getHiddenShell());
        this.setVisible(false);
    }

    public MasterCenterComposite getParent() {
        return this.parent;
    }

    private Logger getLog() {
        if (this.log == null) {
            this.log = Logger.getLogger((Class)ReportsManagerComposite.class);
            PropertyConfigurator.configure((String)"log4j.properties");
        }
        return this.log;
    }

    public Combo getPurchaseOrderCbx() {
        return this.purchaseOrderCbx;
    }

    public Combo getUserCbx() {
        return this.userCbx;
    }

    public MessageBox getQuestionBox(String message) {
        if (this.questionBox == null) {
            this.questionBox = new MessageBox(this.getShell(), 196);
            this.questionBox.setText(Constants.MESSAGE_BOX_DIAG_TITLE.toString());
        }
        this.questionBox.setMessage(message);
        return this.questionBox;
    }

    public static void main(String[] args) {
        HSSFPalette palette = new HSSFWorkbook().getCustomPalette();
        HSSFColor lightBlue = palette.addColor(-103, 0, 0);
        Iterator itHash = HSSFColor.getIndexHash().entrySet().iterator();
        System.out.println("HSSFColor.getIndexHash():");
        while (itHash.hasNext()) {
            Map.Entry entry = itHash.next();
            System.out.println("\t" + entry.getValue() + "[ " + entry.getKey() + " ]");
        }
        Set mutableIndexKeys = HSSFColor.getMutableIndexHash().keySet();
        System.out.println("\nHSSFColor.getMutableIndexHash():");
        for (Integer key : mutableIndexKeys) {
            System.out.println("\t" + HSSFColor.getMutableIndexHash().get(key) + "[ " + key + " ]");
        }
        Set tripleKeys = HSSFColor.getTripletHash().keySet();
        System.out.println("\nHSSFHSSFColor.getTripletHash():");
        for (String key2 : tripleKeys) {
            System.out.println("\t" + HSSFColor.getTripletHash().get(key2) + "[ " + key2 + " ]");
        }
    }

}

