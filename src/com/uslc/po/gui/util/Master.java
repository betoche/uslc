/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  org.apache.log4j.Logger
 *  org.apache.log4j.PropertyConfigurator
 *  org.eclipse.swt.graphics.Device
 *  org.eclipse.swt.graphics.Font
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.Table
 *  org.eclipse.swt.widgets.TableColumn
 */
package com.uslc.po.gui.util;

import com.uslc.po.gui.client.POClient;
import com.uslc.po.gui.master.POMaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class Master {
    private POMaster master = null;
    private POClient client = null;
    private Font defaultTicketFont = null;
    private Font defaultMasterFont = null;
    private String dbHost = "";
    private String dbPort = "";
    private String dbName = "";
    private String defaultTicketPrinter = "";
    private int numberOfCopies = 0;
    private String shipTo = "";
    private String shipFrom = "";
    private String departmentNumber = "";
    private String countryOfOrigin = "";
    private int defaultQtyPerCarton = 0;
    private String preticketedReportPath = "";
    private String noPreticketedReportPath = "";
    private String blanckPreticketedPath = "";
    private String stickerReportPath = "";
    private String blankStickerReportPath = "";
    private boolean automaticPrinting = false;
    private Properties masterProps = null;
    private Logger log = null;
    private boolean printingDialogEnabled = false;

    public Master(POMaster master) throws IOException {
        this.master = master;
        this.getMasterProperties();
    }

    public Master(POClient client) throws IOException {
        this.client = client;
        this.getMasterProperties();
    }

    public POClient getClient() {
        return this.client;
    }

    public POMaster getMaster() {
        return this.master;
    }

    public Properties getMasterProperties() throws IOException {
        this.getLog().info((Object)("getMasterProps[" + this.masterProps + "] method"));
        if (this.masterProps == null) {
            InputStream input = null;
            try {
                input = new FileInputStream(new File("uslc_master.properties"));
                this.masterProps = new Properties();
                this.masterProps.load(input);
                this.dbHost = String.valueOf(this.masterProps.getProperty("db-host", "db"));
                this.dbPort = String.valueOf(this.masterProps.getProperty("db-port", "3306"));
                this.dbName = String.valueOf(this.masterProps.getProperty("db-name", "uslc"));
                this.defaultTicketPrinter = String.valueOf(this.masterProps.getProperty("ticket-default-printer", ""));
                this.shipTo = String.valueOf(this.masterProps.getProperty("ship-to", "ED 6755 ROAD\nGROVEPORT OH 43125"));
                this.shipFrom = String.valueOf(this.masterProps.getProperty("ship-from", "USLC APPAREL S.A.\nCARRETERA NORTE ANTIGUA PEPSI 2C\nAL NORTE\nMANAGUA, A 49\nNICARAGUA"));
                this.departmentNumber = String.valueOf(this.masterProps.getProperty("department-number", "003"));
                this.countryOfOrigin = String.valueOf(this.masterProps.getProperty("country-of-origin", "Nicaragua"));
                this.defaultQtyPerCarton = Integer.parseInt(String.valueOf(this.masterProps.getProperty("items-per-carton", "12")));
                this.preticketedReportPath = String.valueOf(this.masterProps.getProperty("preticketed-report-path", ""));
                this.noPreticketedReportPath = String.valueOf(this.masterProps.getProperty("no-preticketed-report-path", ""));
                this.blanckPreticketedPath = String.valueOf(this.masterProps.getProperty("blank-preticketed-report-path", ""));
                this.stickerReportPath = String.valueOf(this.masterProps.getProperty("sticker-report-path", ""));
                this.blankStickerReportPath = String.valueOf(this.masterProps.getProperty("blank-sticker-report-path", ""));
                this.numberOfCopies = Integer.parseInt(this.masterProps.getProperty("number-of-copies", "1"));
                this.automaticPrinting = Boolean.parseBoolean(this.masterProps.getProperty("automatic-printing", "true"));
                this.printingDialogEnabled = Boolean.parseBoolean(this.masterProps.getProperty("printing-dialog-enabled", "true"));
            }
            finally {
                if (input != null) {
                    try {
                        input.close();
                    }
                    catch (IOException e) {
                        this.getLog().error((Object)"Error at getMasterProperties method", (Throwable)e);
                    }
                }
            }
        }
        return this.masterProps;
    }

    public Font getDefaultTicketFont() throws IOException {
        if (this.defaultTicketFont == null) {
            String fontName = this.getMasterProperties().getProperty("ticket-font-family");
            this.getPoMaster().getDisplay();
            if (Display.getCurrent().loadFont("font/" + fontName)) {
                this.defaultTicketFont = new Font((Device)this.getMaster().getDisplay(), fontName, 8, 0);
            } else {
                this.getLog().info((Object)("couldn't load font in font/" + fontName));
                this.defaultTicketFont = this.getMaster().getDisplay().getSystemFont();
            }
        }
        return this.defaultTicketFont;
    }

    public Font getDefaultMasterFont() throws IOException {
        if (this.defaultMasterFont == null) {
            String fontName = this.getMasterProperties().get("master-default-font-family").toString();
            this.getMaster().getDisplay();
            if (Display.getCurrent().loadFont("fonts/" + fontName)) {
                this.defaultMasterFont = new Font((Device)this.getMaster().getDisplay(), "Segoe UI", 8, 0);
            } else {
                this.getLog().info((Object)("fonts/" + fontName + " couldn't be loaded"));
                this.defaultMasterFont = this.getMaster().getDisplay().getSystemFont();
            }
        }
        return this.defaultMasterFont;
    }

    public String getDbHost() {
        return this.dbHost;
    }

    public String getDbPort() {
        return this.dbPort;
    }

    public String getDbName() {
        return this.dbName;
    }

    public String getDefaultTicketPrinter() {
        return this.defaultTicketPrinter;
    }

    public boolean isAutomaticPrinting() {
        return this.automaticPrinting;
    }

    public int getDefaultQtyPerCarton() {
        return this.defaultQtyPerCarton;
    }

    public int getNumberOfCopies() {
        return this.numberOfCopies;
    }

    public String getShipTo() {
        return this.shipTo;
    }

    public String getShipFrom() {
        return this.shipFrom;
    }

    public String getDepartmentNumber() {
        return this.departmentNumber;
    }

    public String getCountryOfOrigin() {
        return this.countryOfOrigin;
    }

    public String getPreticketedReportPath() {
        return this.preticketedReportPath;
    }

    public String getNoPreticketedReportPath() {
        return this.noPreticketedReportPath;
    }

    public String getBlanckPreticketedPath() {
        return this.blanckPreticketedPath;
    }

    public String getStickerReportPath() {
        return this.stickerReportPath;
    }

    public String getBlankStickerReportPath() {
        return this.blankStickerReportPath;
    }

    private Logger getLog() {
        if (this.log == null) {
            this.log = Logger.getLogger((Class)Master.class);
            PropertyConfigurator.configure((String)"log4j.properties");
        }
        return this.log;
    }

    public POMaster getPoMaster() {
        return this.master;
    }

    public Date getCurrDate() {
        Calendar cal = Calendar.getInstance();
        return cal.getTime();
    }

    public void getColumnsWidth(Table t) {
        TableColumn[] arrtableColumn = t.getColumns();
        int n = arrtableColumn.length;
        int n2 = 0;
        while (n2 < n) {
            TableColumn col = arrtableColumn[n2];
            this.getLog().info((Object)(String.valueOf(col.getText()) + ": " + col.getWidth()));
            ++n2;
        }
    }

    public boolean saveMasterProperties() {
        boolean success = false;
        try {
            FileOutputStream out = new FileOutputStream(new File("uslc_master.properties"));
            this.getMasterProperties().setProperty("db-host", this.getDbHost());
            this.getMasterProperties().setProperty("db-port", this.getDbPort());
            this.getMasterProperties().setProperty("db-name", this.getDbName());
            this.getMasterProperties().setProperty("ticket-default-printer", this.getDefaultTicketPrinter());
            this.getMasterProperties().setProperty("ship-to", this.getShipTo());
            this.getMasterProperties().setProperty("ship-from", this.getShipFrom());
            this.getMasterProperties().setProperty("department-number", this.getDepartmentNumber());
            this.getMasterProperties().setProperty("country-of-origin", this.getCountryOfOrigin());
            this.getMasterProperties().setProperty("items-per-carton", String.valueOf(this.getDefaultQtyPerCarton()));
            this.getMasterProperties().setProperty("preticketed-report-path", this.getPreticketedReportPath());
            this.getMasterProperties().setProperty("no-preticketed-report-path", this.getNoPreticketedReportPath());
            this.getMasterProperties().setProperty("blank-preticketed-report-path", this.getBlanckPreticketedPath());
            this.getMasterProperties().setProperty("sticker-report-path", this.getStickerReportPath());
            this.getMasterProperties().setProperty("blank-sticker-report-path", this.getBlankStickerReportPath());
            this.getMasterProperties().setProperty("number-of-copies", String.valueOf(this.getNumberOfCopies()));
            this.getMasterProperties().setProperty("automatic-printing", String.valueOf(this.isAutomaticPrinting()));
            this.getMasterProperties().setProperty("printing-dialog-enabled", String.valueOf(this.isPrintingDialogEnabled()));
            this.getMasterProperties().store(out, null);
            out.close();
            success = true;
        }
        catch (FileNotFoundException e) {
            this.getLog().info((Object)"error saving master properties", (Throwable)e);
        }
        catch (IOException e) {
            this.getLog().info((Object)"error saving master properties", (Throwable)e);
        }
        return success;
    }

    public void setNumberOfCopies(int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    public void setDefaultTicketPrinter(String defaultPrinter) {
        this.defaultTicketPrinter = defaultPrinter;
    }

    public void setAutomaticPrinting(boolean automaticPrinting) {
        this.automaticPrinting = automaticPrinting;
    }

    public boolean isPrintingDialogEnabled() {
        return this.printingDialogEnabled;
    }
}

