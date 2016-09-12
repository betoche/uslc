/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  org.eclipse.swt.graphics.Device
 *  org.eclipse.swt.graphics.Drawable
 *  org.eclipse.swt.graphics.GC
 *  org.eclipse.swt.graphics.Image
 *  org.eclipse.swt.graphics.Rectangle
 *  org.eclipse.swt.widgets.Display
 */
package com.uslc.po.gui.util;

import com.uslc.po.gui.util.Barcode4JGenerator;
import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;

public class ImageUtils {
    public static org.eclipse.swt.graphics.Image resize(org.eclipse.swt.graphics.Image image, int width, int height) {
        org.eclipse.swt.graphics.Image scaled = new org.eclipse.swt.graphics.Image((Device)Display.getDefault(), width, height);
        GC gc = new GC((Drawable)scaled);
        gc.setAntialias(1);
        gc.setInterpolation(2);
        gc.drawImage(image, 0, 0, image.getBounds().width, image.getBounds().height, 0, 0, width, height);
        gc.dispose();
        image.dispose();
        return scaled;
    }

    public static org.eclipse.swt.graphics.Image getBarcodeImage(Display display, String code) {
        org.eclipse.swt.graphics.Image img = null;
        ByteArrayInputStream in = ImageUtils.getGeneratedBarcodeImage(code);
        if (in != null) {
            img = ImageUtils.resize(new org.eclipse.swt.graphics.Image((Device)display, (InputStream)in), 105, 70);
        } else {
            System.out.println("in[" + in + "]");
        }
        return img;
    }

    public static BufferedImage createResizedCopy(Image originalImage, int scaledWidth, int scaledHeight, boolean preserveAlpha) {
        System.out.println("resizing...");
        int imageType = preserveAlpha ? 1 : 2;
        BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
        Graphics2D g = scaledBI.createGraphics();
        if (preserveAlpha) {
            g.setComposite(AlphaComposite.Src);
        }
        g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
        g.dispose();
        return scaledBI;
    }

    public static Image getAwtBarcodeImage(String code) {
        ByteArrayInputStream bis = ImageUtils.getGeneratedBarcodeImage(code);
        Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("png");
        ImageReader reader = readers.next();
        ByteArrayInputStream source = bis;
        BufferedImage image = null;
        try {
            ImageInputStream iis = ImageIO.createImageInputStream(source);
            reader.setInput(iis, true);
            ImageReadParam param = reader.getDefaultReadParam();
            image = reader.read(0, param);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public static ByteArrayInputStream getGeneratedBarcodeImage(String code) {
        Barcode4JGenerator barcode4j = new Barcode4JGenerator();
        ByteArrayInputStream in = barcode4j.getGeneratedBarcode(code);
        return in;
    }
}

