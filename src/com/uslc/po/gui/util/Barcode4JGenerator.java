/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  org.apache.avalon.framework.configuration.Configuration
 *  org.apache.avalon.framework.configuration.ConfigurationException
 *  org.apache.avalon.framework.configuration.DefaultConfigurationBuilder
 *  org.krysalis.barcode4j.BarcodeException
 *  org.krysalis.barcode4j.BarcodeGenerator
 *  org.krysalis.barcode4j.BarcodeUtil
 *  org.krysalis.barcode4j.output.CanvasProvider
 *  org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider
 */
package com.uslc.po.gui.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.configuration.DefaultConfigurationBuilder;
import org.krysalis.barcode4j.BarcodeException;
import org.krysalis.barcode4j.BarcodeGenerator;
import org.krysalis.barcode4j.BarcodeUtil;
import org.krysalis.barcode4j.output.CanvasProvider;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.xml.sax.SAXException;

public class Barcode4JGenerator {
    public ByteArrayInputStream getGeneratedBarcode(String msg) {
        ByteArrayInputStream in = null;
        DefaultConfigurationBuilder builder = new DefaultConfigurationBuilder();
        try {
            System.out.println(new File("").getAbsolutePath());
            Configuration cfg = builder.buildFromFile(new File("barcode-cfg.xml"));
            BarcodeGenerator gen = BarcodeUtil.getInstance().createBarcodeGenerator(cfg);
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            BitmapCanvasProvider provider = new BitmapCanvasProvider((OutputStream)byteOut, "image/x-png", 300, 10, true, 0);
            gen.generateBarcode((CanvasProvider)provider, msg);
            provider.finish();
            in = new ByteArrayInputStream(byteOut.toByteArray());
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        catch (ConfigurationException e) {
            e.printStackTrace();
        }
        catch (SAXException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (BarcodeException e) {
            e.printStackTrace();
        }
        return in;
    }
}

