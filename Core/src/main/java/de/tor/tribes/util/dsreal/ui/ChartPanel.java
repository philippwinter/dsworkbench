/* 
 * Copyright 2015 Torridity.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.tor.tribes.util.dsreal.ui;

import de.tor.tribes.types.ext.Ally;
import de.tor.tribes.types.ext.Tribe;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 *
 * @author Charon
 */
public class ChartPanel extends javax.swing.JPanel {

    private List<BufferedImage> mBuffers = null;

    public ChartPanel(List<String> pFiles) throws Exception {
        mBuffers = new LinkedList<>();
        for (String file : pFiles) {
            mBuffers.add(ImageIO.read(new File(file)));
        }
        setSize(mBuffers.get(0).getWidth(), mBuffers.get(0).getHeight() * mBuffers.size());
        setPreferredSize(new Dimension(mBuffers.get(0).getWidth(), mBuffers.get(0).getHeight() * mBuffers.size()));
    }

    public static boolean showChart(List<String> pLocalFiles, List<Tribe> pTribes) {
        try {
            ChartPanel p = new ChartPanel(pLocalFiles);
            JFrame f = new JFrame();
            f.setTitle("Stats");
            f.add(p);
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.pack();
            f.setVisible(true);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean showPointChart(String pLocalFile, Tribe pTribe) {
        try {
            List<String> file = new LinkedList<>();
            file.add(pLocalFile);
            ChartPanel p = new ChartPanel(file);
            JFrame f = new JFrame();
            f.setTitle("Spielerstatistik für " + pTribe.getName());
            f.add(p);
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.pack();
            f.setVisible(true);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean showPointChart(String pLocalFile, Ally pAlly) {
        try {
            List<String> file = new LinkedList<>();
            file.add(pLocalFile);
            ChartPanel p = new ChartPanel(file);
            JFrame f = new JFrame();
            f.setTitle("Stammesstatistik für " + pAlly.getName());
            f.add(p);
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.pack();
            f.setVisible(true);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean showBashChart(String pOffFile, String pDefFile, String pAllFile, Tribe pTribe) {
        try {
            List<String> file = new LinkedList<>();
            file.add(pOffFile);
            file.add(pDefFile);
            file.add(pAllFile);
            ChartPanel p = new ChartPanel(file);
            JFrame f = new JFrame();
            f.setTitle("Spielerbasherstatistik für " + pTribe.getName());
            f.add(p);
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.pack();
            f.setVisible(true);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean showBashChart(String pOffFile, String pDefFile, String pAllFile, Ally pAlly) {
        try {
            List<String> file = new LinkedList<>();
            file.add(pOffFile);
            file.add(pDefFile);
            file.add(pAllFile);
            ChartPanel p = new ChartPanel(file);
            JFrame f = new JFrame();
            f.setTitle("Stammesbasherstatistik für " + pAlly.getName());
            f.add(p);
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.pack();
            f.setVisible(true);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        int h = mBuffers.get(0).getHeight();
        int cnt = 0;
        for (BufferedImage i : mBuffers) {
            g2d.drawImage(i, 0, cnt * h, null);
            cnt++;
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
