/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AlgorithmLogPanel.java
 *
 * Created on 29.01.2010, 11:00:59
 */
package de.tor.tribes.ui.algo;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author Jejkal
 */
public class AlgorithmLogPanel extends javax.swing.JPanel {

    private SimpleDateFormat dateFormat = null;

    /** Creates new form AlgorithmLogPanel */
    public AlgorithmLogPanel() {
        initComponents();
        StyledDocument doc = (StyledDocument) jTextPane1.getDocument();

        // Create a style object and then set the style attributes
        Style defaultStyle = doc.addStyle("Default", null);
        StyleConstants.setItalic(defaultStyle, true);
        StyleConstants.setFontFamily(defaultStyle, "SansSerif");
        Style infoStyle = doc.addStyle("Info", null);
        StyleConstants.setItalic(infoStyle, true);
        StyleConstants.setFontFamily(infoStyle, "SansSerif");
        StyleConstants.setForeground(infoStyle, Color.LIGHT_GRAY);
        Style errorStyle = doc.addStyle("Error", null);
        StyleConstants.setFontFamily(errorStyle, "SansSerif");
        StyleConstants.setForeground(errorStyle, Color.RED);
        dateFormat = new SimpleDateFormat("HH:mm:ss");
    }

    public void addText(String pText) {
        try {
            StyledDocument doc = jTextPane1.getStyledDocument();
            doc.insertString(doc.getLength(), "(" + dateFormat.format(new Date(System.currentTimeMillis())) + ") " + pText + "\n", doc.getStyle("Default"));
        } catch (Exception e) {
        }
    }

    public void addInfo(String pText) {
        try {
            StyledDocument doc = jTextPane1.getStyledDocument();
            doc.insertString(doc.getLength(), "(" + dateFormat.format(new Date(System.currentTimeMillis())) + ") " + pText + "\n", doc.getStyle("Info"));
        } catch (Exception e) {
        }
    }

    public void addError(String pText) {
        try {
            StyledDocument doc = jTextPane1.getStyledDocument();
            doc.insertString(doc.getLength(), "(" + dateFormat.format(new Date(System.currentTimeMillis())) + ") " + pText + "\n", doc.getStyle("Error"));
        } catch (Exception e) {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

        jScrollPane1.setViewportView(jTextPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables
}