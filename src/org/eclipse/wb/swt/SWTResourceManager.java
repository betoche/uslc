/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  org.eclipse.swt.graphics.Color
 *  org.eclipse.swt.graphics.Cursor
 *  org.eclipse.swt.graphics.Device
 *  org.eclipse.swt.graphics.Drawable
 *  org.eclipse.swt.graphics.Font
 *  org.eclipse.swt.graphics.FontData
 *  org.eclipse.swt.graphics.GC
 *  org.eclipse.swt.graphics.Image
 *  org.eclipse.swt.graphics.ImageData
 *  org.eclipse.swt.graphics.RGB
 *  org.eclipse.swt.graphics.Rectangle
 *  org.eclipse.swt.widgets.Display
 */
package org.eclipse.wb.swt;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;

public class SWTResourceManager {
    private static Map<RGB, Color> m_colorMap = new HashMap<RGB, Color>();
    private static Map<String, Image> m_imageMap = new HashMap<String, Image>();
    private static final int MISSING_IMAGE_SIZE = 10;
    public static final int TOP_LEFT = 1;
    public static final int TOP_RIGHT = 2;
    public static final int BOTTOM_LEFT = 3;
    public static final int BOTTOM_RIGHT = 4;
    protected static final int LAST_CORNER_KEY = 5;
    private static Map<Image, Map<Image, Image>>[] m_decoratedImageMap = new Map[5];
    private static Map<String, Font> m_fontMap = new HashMap<String, Font>();
    private static Map<Font, Font> m_fontToBoldFontMap = new HashMap<Font, Font>();
    private static Map<Integer, Cursor> m_idToCursorMap = new HashMap<Integer, Cursor>();

    public static Color getColor(int systemColorID) {
        Display display = Display.getCurrent();
        return display.getSystemColor(systemColorID);
    }

    public static Color getColor(int r, int g, int b) {
        return SWTResourceManager.getColor(new RGB(r, g, b));
    }

    public static Color getColor(RGB rgb) {
        Color color = m_colorMap.get((Object)rgb);
        if (color == null) {
            Display display = Display.getCurrent();
            color = new Color((Device)display, rgb);
            m_colorMap.put(rgb, color);
        }
        return color;
    }

    public static void disposeColors() {
        for (Color color : m_colorMap.values()) {
            color.dispose();
        }
        m_colorMap.clear();
    }

    protected static Image getImage(InputStream stream) throws IOException {
        try {
            Display display = Display.getCurrent();
            ImageData data = new ImageData(stream);
            if (data.transparentPixel > 0) {
                Image image = new Image((Device)display, data, data.getTransparencyMask());
                return image;
            }
            Image image = new Image((Device)display, data);
            return image;
        }
        finally {
            stream.close();
        }
    }

    public static Image getImage(String path) {
        Image image = m_imageMap.get(path);
        if (image == null) {
            try {
                image = SWTResourceManager.getImage(new FileInputStream(path));
                m_imageMap.put(path, image);
            }
            catch (Exception e) {
                image = SWTResourceManager.getMissingImage();
                m_imageMap.put(path, image);
            }
        }
        return image;
    }

    public static Image getImage(Class<?> clazz, String path) {
        String key = String.valueOf(clazz.getName()) + '|' + path;
        Image image = m_imageMap.get(key);
        if (image == null) {
            try {
                image = SWTResourceManager.getImage(clazz.getResourceAsStream(path));
                m_imageMap.put(key, image);
            }
            catch (Exception e) {
                image = SWTResourceManager.getMissingImage();
                m_imageMap.put(key, image);
            }
        }
        return image;
    }

    private static Image getMissingImage() {
        Image image = new Image((Device)Display.getCurrent(), 10, 10);
        GC gc = new GC((Drawable)image);
        gc.setBackground(SWTResourceManager.getColor(3));
        gc.fillRectangle(0, 0, 10, 10);
        gc.dispose();
        return image;
    }

    public static Image decorateImage(Image baseImage, Image decorator) {
        return SWTResourceManager.decorateImage(baseImage, decorator, 4);
    }

    public static Image decorateImage(Image baseImage, Image decorator, int corner) {
        Map<Image, Image> decoratedMap;
        Image result;
        if (corner <= 0 || corner >= 5) {
            throw new IllegalArgumentException("Wrong decorate corner");
        }
        Map<Image, Map<Image, Image>> cornerDecoratedImageMap = m_decoratedImageMap[corner];
        if (cornerDecoratedImageMap == null) {
            SWTResourceManager.m_decoratedImageMap[corner] = cornerDecoratedImageMap = new HashMap<Image, Map<Image, Image>>();
        }
        if ((decoratedMap = cornerDecoratedImageMap.get((Object)baseImage)) == null) {
            decoratedMap = new HashMap<Image, Image>();
            cornerDecoratedImageMap.put(baseImage, decoratedMap);
        }
        if ((result = decoratedMap.get((Object)decorator)) == null) {
            Rectangle bib = baseImage.getBounds();
            Rectangle dib = decorator.getBounds();
            result = new Image((Device)Display.getCurrent(), bib.width, bib.height);
            GC gc = new GC((Drawable)result);
            gc.drawImage(baseImage, 0, 0);
            if (corner == 1) {
                gc.drawImage(decorator, 0, 0);
            } else if (corner == 2) {
                gc.drawImage(decorator, bib.width - dib.width, 0);
            } else if (corner == 3) {
                gc.drawImage(decorator, 0, bib.height - dib.height);
            } else if (corner == 4) {
                gc.drawImage(decorator, bib.width - dib.width, bib.height - dib.height);
            }
            gc.dispose();
            decoratedMap.put(decorator, result);
        }
        return result;
    }

    public static void disposeImages() {
        for (Image image : m_imageMap.values()) {
            image.dispose();
        }
        m_imageMap.clear();
        int i = 0;
        while (i < m_decoratedImageMap.length) {
            Map<Image, Map<Image, Image>> cornerDecoratedImageMap = m_decoratedImageMap[i];
            if (cornerDecoratedImageMap != null) {
                for (Map<Image, Image> decoratedMap : cornerDecoratedImageMap.values()) {
                    for (Image image2 : decoratedMap.values()) {
                        image2.dispose();
                    }
                    decoratedMap.clear();
                }
                cornerDecoratedImageMap.clear();
            }
            ++i;
        }
    }

    public static Font getFont(String name, int height, int style) {
        return SWTResourceManager.getFont(name, height, style, false, false);
    }

    public static Font getFont(String name, int size, int style, boolean strikeout, boolean underline) {
        String fontName = String.valueOf(name) + '|' + size + '|' + style + '|' + strikeout + '|' + underline;
        Font font = m_fontMap.get(fontName);
        if (font == null) {
            FontData fontData = new FontData(name, size, style);
            if (strikeout || underline) {
                try {
                    Class logFontClass = Class.forName("org.eclipse.swt.internal.win32.LOGFONT");
                    Object logFont = FontData.class.getField("data").get((Object)fontData);
                    if (logFont != null && logFontClass != null) {
                        if (strikeout) {
                            logFontClass.getField("lfStrikeOut").set(logFont, Byte.valueOf(1));
                        }
                        if (underline) {
                            logFontClass.getField("lfUnderline").set(logFont, Byte.valueOf(1));
                        }
                    }
                }
                catch (Throwable e) {
                    System.err.println("Unable to set underline or strikeout (probably on a non-Windows platform). " + e);
                }
            }
            font = new Font((Device)Display.getCurrent(), fontData);
            m_fontMap.put(fontName, font);
        }
        return font;
    }

    public static Font getBoldFont(Font baseFont) {
        Font font = m_fontToBoldFontMap.get((Object)baseFont);
        if (font == null) {
            FontData[] fontDatas = baseFont.getFontData();
            FontData data = fontDatas[0];
            font = new Font((Device)Display.getCurrent(), data.getName(), data.getHeight(), 1);
            m_fontToBoldFontMap.put(baseFont, font);
        }
        return font;
    }

    public static void disposeFonts() {
        for (Font font2 : m_fontMap.values()) {
            font2.dispose();
        }
        m_fontMap.clear();
        for (Font font2 : m_fontToBoldFontMap.values()) {
            font2.dispose();
        }
        m_fontToBoldFontMap.clear();
    }

    public static Cursor getCursor(int id) {
        Integer key = id;
        Cursor cursor = m_idToCursorMap.get(key);
        if (cursor == null) {
            cursor = new Cursor((Device)Display.getDefault(), id);
            m_idToCursorMap.put(key, cursor);
        }
        return cursor;
    }

    public static void disposeCursors() {
        for (Cursor cursor : m_idToCursorMap.values()) {
            cursor.dispose();
        }
        m_idToCursorMap.clear();
    }

    public static void dispose() {
        SWTResourceManager.disposeColors();
        SWTResourceManager.disposeImages();
        SWTResourceManager.disposeFonts();
        SWTResourceManager.disposeCursors();
    }
}

