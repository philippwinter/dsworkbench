/*
 * ClockFrame.java
 *
 * Created on 7. Juli 2008, 08:15
 */
package de.tor.tribes.ui;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author  Jejkal
 */
public class ClockFrame extends javax.swing.JFrame {

    private TimerThread tThread = null;
    private static ClockFrame CLOCK = null;

    public static ClockFrame getGlobalClockFrame() {
        if (CLOCK == null) {
            CLOCK = new ClockFrame();
        }
        return CLOCK;
    }

    /** Creates new form ClockFrame */
    ClockFrame() {
        initComponents();
        frameControlPanel1.setupPanel(this, true, false);
        SpinnerDateModel dateModel = new SpinnerDateModel();
        jSpinner1.setValue(new Date(System.currentTimeMillis()));
        ((DateEditor) jSpinner1.getEditor()).getFormat().applyPattern("dd.MM.yy HH:mm:ss.SSS");
        tThread = new TimerThread(this);
        tThread.start();
    }

    protected void updateTime(String time) {
        jLabel1.setText(time);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        frameControlPanel1 = new de.tor.tribes.ui.FrameControlPanel();
        jSpinner1 = new javax.swing.JSpinner();
        jActivateTimerButton = new javax.swing.JToggleButton();

        setTitle("Uhr");
        setUndecorated(true);

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel1.setText("jLabel1");

        jSpinner1.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.MILLISECOND));

        jActivateTimerButton.setText("Aktivieren");
        jActivateTimerButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fireActivateTimerEvent(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(frameControlPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(196, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jActivateTimerButton)
                .addContainerGap(61, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(frameControlPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jActivateTimerButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void fireActivateTimerEvent(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fireActivateTimerEvent
    if (jActivateTimerButton.isSelected()) {
        tThread.setNotifyTime(((Date) jSpinner1.getValue()).getTime());
    } else {
        tThread.setNotifyTime(-1);
    }
}//GEN-LAST:event_fireActivateTimerEvent

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new  

              Runnable() {

                 public void run() {
                new ClockFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private de.tor.tribes.ui.FrameControlPanel frameControlPanel1;
    private javax.swing.JToggleButton jActivateTimerButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSpinner jSpinner1;
    // End of variables declaration//GEN-END:variables

    public void beep() {
        jActivateTimerButton.setSelected(false);
        System.out.print("\007");
        System.out.flush();
    }
}

class TimerThread extends Thread {

    private ClockFrame mParent;
    private final SimpleDateFormat FORMAT = new SimpleDateFormat("HH:mm:ss:SSS");
    private long lNotifyTime = -1;

    public TimerThread(ClockFrame pParent) {
        mParent = pParent;
        setDaemon(true);
    }

    public void setNotifyTime(long pTime) {
        lNotifyTime = pTime;
    }

    public void run() {
        while (true) {
            long currentTime = System.currentTimeMillis();

            if ((lNotifyTime != -1) && (currentTime >= lNotifyTime)) {
                mParent.beep();
                lNotifyTime = -1;
            }
            mParent.updateTime(FORMAT.format(new Date(currentTime)));
            mParent.repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException ie) {
            }
        }
    }
    // NIST, Boulder, Colorado  (time-a.timefreq.bldrdoc.gov)
    public static final String ATOMICTIME_SERVER = "http://132.163.4.101:13";
    // NIST, Gaithersburg, Maryland (time-a.nist.gov)
    // public static final String ATOMICTIME_SERVER="http://129.6.15.28:13";
}
