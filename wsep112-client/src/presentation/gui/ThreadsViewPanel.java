/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ForumsViewPanel.java
 *
 * Created on 16/04/2011, 13:10:23
 */

package presentation.gui;

import common.forum.items.ThreadInfo;
import domain.ClientController;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author משה
 */
public class ThreadsViewPanel extends javax.swing.JPanel {
    private  DefaultListModel _threadsList;
    private ClientController _clientController;
    private int _threadsIndexSelected;
    private String _ForumId;
     DefaultListModel thread_list;

    /** Creates new form ForumsViewPanel */
    public ThreadsViewPanel(ClientController clientController, String forumId) {
        _clientController= clientController;
        _threadsList= new DefaultListModel();
       this.fillListThreads();
        _ForumId= forumId;
        initComponents();
    }

    public void fillListThreads( ) {
         Vector <ThreadInfo> threads= _clientController.getThreadsList(_ForumId);
        //this.setVisible(true);
     for (int j=0; j<threads.size(); j++  ){
               getThreadsListModel().addElement(threads.get(j).getTitle());
             }
    }

    public   DefaultListModel  getThreadsListModel() {
        return _threadsList;
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane4 = new javax.swing.JLayeredPane();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jLayeredPane4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Forum System", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Kristen ITC", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        jButton2.setFont(new java.awt.Font("Kristen ITC", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentation/gui/Threadadd.png"))); // NOI18N
        jButton2.setText("Add Thread");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jButton2.setBounds(315, 350, 190, 40);
        jLayeredPane4.add(jButton2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton1.setFont(new java.awt.Font("Kristen ITC", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentation/gui/view_bottom.png"))); // NOI18N
        jButton1.setText("View Thread");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.setBounds(20, 350, 170, 40);
        jLayeredPane4.add(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Please choose one of the Threads below", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Kristen ITC", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        jList1.setModel(getThreadsListModel());
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jList1);

        jScrollPane1.setBounds(10, 30, 470, 270);
        jLayeredPane2.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane2.setBounds(20, 30, 490, 310);
        jLayeredPane4.add(jLayeredPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentation/gui/go_back.png"))); // NOI18N
        jButton4.setBorder(null);
        jButton4.setBorderPainted(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jButton4.setBounds(20, 20, 40, 30);
        jLayeredPane4.add(jButton4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentation/gui/kuser.png"))); // NOI18N
        jLabel1.setBounds(190, 330, 140, 140);
        jLayeredPane4.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         this.thread_list=   (DefaultListModel)jList1.getModel();
         _threadsIndexSelected= jList1.getSelectedIndex();
         if (_threadsIndexSelected>=0  ){
               //TODO: CALL FRAME OF VIEW POSTS OF THREAD
                this.setVisible(false);
        }
       else
                JOptionPane.showMessageDialog(null, "Please choose one of the threads above.", "Thread Selection  Error", 0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:               
        new Forum(_clientController).setSize(693,516);
        this.setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new AddThread(_clientController, _ForumId).setSize(693,516);
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane4;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}
