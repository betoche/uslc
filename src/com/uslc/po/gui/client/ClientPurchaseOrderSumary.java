/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  com.uslc.po.jpa.entity.Carton
 *  com.uslc.po.jpa.entity.Color
 *  com.uslc.po.jpa.entity.Item
 *  com.uslc.po.jpa.entity.PackingDetail
 *  com.uslc.po.jpa.entity.PurchaseOrder
 *  com.uslc.po.jpa.entity.PurchaseOrderDetail
 *  com.uslc.po.jpa.entity.ScanDetail
 *  com.uslc.po.jpa.entity.Upc
 *  org.eclipse.swt.custom.ScrolledComposite
 *  org.eclipse.swt.graphics.Device
 *  org.eclipse.swt.graphics.Font
 *  org.eclipse.swt.graphics.FontData
 *  org.eclipse.swt.graphics.Point
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Control
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.Group
 *  org.eclipse.swt.widgets.Label
 */
package com.uslc.po.gui.client;

import com.uslc.po.jpa.entity.Carton;
import com.uslc.po.jpa.entity.Color;
import com.uslc.po.jpa.entity.Item;
import com.uslc.po.jpa.entity.PackingDetail;
import com.uslc.po.jpa.entity.PurchaseOrder;
import com.uslc.po.jpa.entity.PurchaseOrderDetail;
import com.uslc.po.jpa.entity.ScanDetail;
import com.uslc.po.jpa.entity.Upc;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

public class ClientPurchaseOrderSumary {
    private PurchaseOrder po = null;
    private HashMap<String, PurchaseOrderStats> poInfo = null;

    public ClientPurchaseOrderSumary(PurchaseOrder po) {
        this.po = po;
        HashSet<String> colorItem = new HashSet<String>();
        for (PurchaseOrderDetail pod : po.getPurchaseOrderDetails()) {
            Upc upc = pod.getUpc();
            colorItem.add(String.valueOf(upc.getColor().getName()) + "-" + upc.getItem().getCode());
        }
        for (String str : colorItem) {
            this.getPoInfo().put(str, new PurchaseOrderStats(this, str));
        }
    }

    public void addInfoToComposites(Composite colors, Group poDetails, Group packingDetails) {
        Control c;
        Control[] arrcontrol = colors.getChildren();
        int n = arrcontrol.length;
        int n2 = 0;
        while (n2 < n) {
            c = arrcontrol[n2];
            c.dispose();
            ++n2;
        }
        arrcontrol = poDetails.getChildren();
        n = arrcontrol.length;
        n2 = 0;
        while (n2 < n) {
            c = arrcontrol[n2];
            c.dispose();
            ++n2;
        }
        arrcontrol = packingDetails.getChildren();
        n = arrcontrol.length;
        n2 = 0;
        while (n2 < n) {
            c = arrcontrol[n2];
            c.dispose();
            ++n2;
        }
        Iterator<Map.Entry<String, PurchaseOrderStats>> it = this.getPoInfo().entrySet().iterator();
        GridData gd = new GridData(768);
        gd.grabExcessHorizontalSpace = true;
        gd.heightHint = 16;
        FontData[] fsd = colors.getFont().getFontData();
        Font f = null;
        Font fc = null;
        FontData[] arrfontData = fsd;
        int n3 = arrfontData.length;
        int n4 = 0;
        while (n4 < n3) {
            FontData fd = arrfontData[n4];
            f = new Font((Device)colors.getDisplay(), fd.getName(), 7, fd.getStyle());
            fc = new Font((Device)colors.getDisplay(), fd.getName(), 6, fd.getStyle());
            ++n4;
        }
        Label blankSpace = new Label(colors, 0);
        blankSpace.setFont(fc);
        blankSpace.setLayoutData((Object)gd);
        Label origQtyTitle = new Label((Composite)poDetails, 0);
        origQtyTitle.setText("Qty");
        origQtyTitle.setFont(f);
        origQtyTitle.setLayoutData((Object)gd);
        origQtyTitle.setAlignment(16777216);
        Label origBoxTitle = new Label((Composite)poDetails, 0);
        origBoxTitle.setText("Boxes");
        origBoxTitle.setFont(f);
        origBoxTitle.setLayoutData((Object)gd);
        origBoxTitle.setAlignment(16777216);
        Label workQtyTitle = new Label((Composite)packingDetails, 0);
        workQtyTitle.setText("Qty");
        workQtyTitle.setFont(f);
        workQtyTitle.setLayoutData((Object)gd);
        workQtyTitle.setAlignment(16777216);
        Label workBoxTitle = new Label((Composite)packingDetails, 0);
        workBoxTitle.setText("Boxes");
        workBoxTitle.setFont(f);
        workBoxTitle.setLayoutData((Object)gd);
        workBoxTitle.setAlignment(16777216);
        int rows = 0;
        while (it.hasNext()) {
            ++rows;
            Map.Entry<String, PurchaseOrderStats> entry = it.next();
            Label color = new Label(colors, 0);
            color.setText(entry.getKey());
            color.setFont(fc);
            color.setLayoutData((Object)gd);
            Label origQty = new Label((Composite)poDetails, 0);
            origQty.setText(String.valueOf(entry.getValue().getOriginalQty()));
            origQty.setFont(f);
            origQty.setLayoutData((Object)gd);
            origQty.setAlignment(16777216);
            Label origBoxes = new Label((Composite)poDetails, 0);
            origBoxes.setText(String.valueOf(entry.getValue().getOriginalBoxes()));
            origBoxes.setFont(f);
            origBoxes.setLayoutData((Object)gd);
            origBoxes.setAlignment(16777216);
            Label workQty = new Label((Composite)packingDetails, 0);
            workQty.setText(String.valueOf(entry.getValue().getWorkedQty()));
            workQty.setFont(f);
            workQty.setLayoutData((Object)gd);
            workQty.setAlignment(16777216);
            Label workBoxes = new Label((Composite)packingDetails, 0);
            workBoxes.setText(String.valueOf(entry.getValue().getWorkedBoxes()));
            workBoxes.setFont(f);
            workBoxes.setLayoutData((Object)gd);
            workBoxes.setAlignment(16777216);
        }
        colors.layout();
        poDetails.layout();
        packingDetails.layout();
        colors.getParent().layout();
        ((ScrolledComposite)colors.getParent().getParent()).setContent((Control)colors.getParent());
        ((ScrolledComposite)colors.getParent().getParent()).setMinSize(colors.getParent().computeSize(-1, -1));
        ((ScrolledComposite)colors.getParent().getParent()).layout();
    }

    public HashMap<String, PurchaseOrderStats> getPoInfo() {
        if (this.poInfo == null) {
            this.poInfo = new HashMap();
        }
        return this.poInfo;
    }

    public PurchaseOrder getPo() {
        return this.po;
    }

    public class PurchaseOrderStats {
        private String colorItem;
        private int originalQty;
        private int originalBoxes;
        private int workedQty;
        private int workedBoxes;
        final /* synthetic */ ClientPurchaseOrderSumary this$0;

        public PurchaseOrderStats(ClientPurchaseOrderSumary clientPurchaseOrderSumary, String colorItem) {
            this.this$0 = clientPurchaseOrderSumary;
            this.colorItem = "";
            this.originalQty = 0;
            this.originalBoxes = 0;
            this.workedQty = 0;
            this.workedBoxes = 0;
            this.colorItem = colorItem;
            for (PurchaseOrderDetail pod : clientPurchaseOrderSumary.getPo().getPurchaseOrderDetails()) {
                Upc upc = pod.getUpc();
                String ci = String.valueOf(upc.getColor().getName()) + "-" + upc.getItem().getCode();
                if (ci.compareTo(colorItem) != 0) continue;
                this.originalQty += pod.getTotal();
                this.originalBoxes += pod.getPackingDetails().size();
                if (pod.getDeleted()) continue;
                for (PackingDetail pd : pod.getPackingDetails()) {
                    Carton carton;
                    if (pd.getDeleted() || (carton = pd.getCarton()) == null || carton.getDeleted()) continue;
                    boolean boxHasAtLeastOneScan = false;
                    for (ScanDetail sd : carton.getScanDetails()) {
                        if (sd.getDeleted()) continue;
                        boxHasAtLeastOneScan = true;
                        ++this.workedQty;
                    }
                    if (!boxHasAtLeastOneScan) continue;
                    ++this.workedBoxes;
                }
            }
        }

        public PurchaseOrderStats(ClientPurchaseOrderSumary clientPurchaseOrderSumary, String colorItem, int originalQty, int originalBoxes, int workedQty, int workedBoxes) {
            this.this$0 = clientPurchaseOrderSumary;
            this.colorItem = "";
            this.originalQty = 0;
            this.originalBoxes = 0;
            this.workedQty = 0;
            this.workedBoxes = 0;
            this.colorItem = colorItem;
            this.originalQty = originalQty;
            this.originalBoxes = originalBoxes;
            this.workedQty = workedQty;
            this.workedBoxes = workedBoxes;
        }

        public String getColorItem() {
            return this.colorItem;
        }

        public int getOriginalQty() {
            return this.originalQty;
        }

        public int getOriginalBoxes() {
            return this.originalBoxes;
        }

        public int getWorkedQty() {
            return this.workedQty;
        }

        public int getWorkedBoxes() {
            return this.workedBoxes;
        }
    }

}

