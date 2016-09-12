/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  org.eclipse.swt.graphics.Device
 *  org.eclipse.swt.graphics.Drawable
 *  org.eclipse.swt.graphics.GC
 *  org.eclipse.swt.graphics.Image
 *  org.eclipse.swt.graphics.ImageData
 *  org.eclipse.swt.printing.PrintDialog
 *  org.eclipse.swt.printing.Printer
 *  org.eclipse.swt.printing.PrinterData
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.Shell
 */
package com.uslc.po.gui.util;

import com.uslc.po.gui.util.ImageUtils;
import com.uslc.po.gui.util.Master;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.printing.PrintDialog;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class PrintingUtils {
    private DocFlavor flavor = null;
    private Doc doc = null;
    private PrinterData data = null;
    private PrintService[] services = null;
    private PrintService defaultService = null;
    private PrintDialog printDialog = null;
    private Master master = null;
    private Shell shell = null;

    public PrintingUtils(Master master, Shell shell) {
        this.master = master;
        this.shell = shell;
    }

    public PrinterData getPrinterData() {
        if (this.data == null) {
            this.data = new PrinterData();
            this.data.copyCount = 1;
            this.data.orientation = 2;
        }
        return this.data;
    }

    public PrintDialog getPrintDialog() {
        if (this.printDialog == null) {
            this.printDialog = new PrintDialog(this.getShell(), 2048);
        }
        return this.printDialog;
    }

    public Master getMaster() {
        return this.master;
    }

    public Shell getShell() {
        return this.shell;
    }

    public PrintService[] getServices() {
        if (this.services == null) {
            HashPrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
            this.services = PrintServiceLookup.lookupPrintServices(this.getFlavor(), aset);
        }
        return this.services;
    }

    public DocFlavor getFlavor() {
        if (this.flavor == null) {
            this.flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
        }
        return this.flavor;
    }

    public PrintService getDefaultService() {
        if (this.defaultService == null) {
            PrintService[] arrprintService = this.getServices();
            int n = arrprintService.length;
            int n2 = 0;
            while (n2 < n) {
                PrintService service = arrprintService[n2];
                if (this.getServices() != null && this.getServices().length > 0 && !this.getMaster().getDefaultTicketPrinter().isEmpty() && service.getName().compareTo(this.getMaster().getDefaultTicketPrinter()) == 0) {
                    this.defaultService = service;
                    break;
                }
                ++n2;
            }
        }
        return this.defaultService;
    }

    public void printSWTImage(PrinterData data, ImageData[] image, String jobName) {
        if (image.length > 0 && data != null) {
            Printer printer = new Printer(data);
            if (printer.startJob(jobName) && printer.startPage()) {
                GC gc = new GC((Drawable)printer);
                Image printerImage = new Image((Device)printer, image[0]);
                gc.drawImage(printerImage, image[0].x, image[0].y);
                printerImage.dispose();
                gc.dispose();
                printer.endPage();
            }
            printer.endJob();
            printer.dispose();
        }
    }

    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);
        Image img = ImageUtils.getBarcodeImage(display, "410013940956");
        PrintingUtils printing = new PrintingUtils(null, shell);
        PrinterData data = printing.getPrintDialog().open();
        if (data == null) {
            return;
        }
        printing.printSWTImage(data, new ImageData[]{img.getImageData()}, "test upc printing");
    }
}

