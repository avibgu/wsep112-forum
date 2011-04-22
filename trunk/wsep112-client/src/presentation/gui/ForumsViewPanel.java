/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ThreadsViewPanel.java
 *
 * Created on 16/04/2011, 12:31:00
 */

package presentation.gui;

import common.forum.items.ForumInfo;
import domain.ClientController;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author משה
 */
public class ForumsViewPanel extends javax.swing.JPanel {
    public  DefaultListModel _forumsList;
    private ClientController _clientController;
    private int _forumsIndexSelected;
    DefaultListModel forum_list;

    /** Creates new form ThreadsViewPanel */
    public ForumsViewPanel(ClientController clientController) {
            _clientController= clientController;
            _forumsList= new DefaultListModel();
            fillListForum();
             setVisible(true);
            initComponents();
    }


    public void fillListForum( ) {
        Vector<ForumInfo> forums= _clientController.getForumsList();
        //this.setVisible(true);
     for (int j=0; j<forums.size(); j++  ){
               getForumsListModel().addElement(forums.get(j).getName());
              }
    }

    public   DefaultListModel  getForumsListModel() {
        return _forumsList;
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
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jLayeredPane4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Forum System", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Kristen ITC", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Please choose one of the forums below", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Kristen ITC", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        jList1.setModel(getForumsListModel());
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jList1);

        jScrollPane1.setBounds(10, 30, 470, 260);
        jLayeredPane2.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane2.setBounds(10, 40, 490, 300);
        jLayeredPane4.add(jLayeredPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentation/gui/go_back.png"))); // NOI18N
        jButton4.setBorder(null);
        jButton4.setBorderPainted(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jButton4.setBounds(10, 20, 40, 30);
        jLayeredPane4.add(jButton4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton1.setFont(new java.awt.Font("Kristen ITC", 1, 14)); // NOI18N
        jButton1.setText("Enter Forum");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.setBounds(190, 350, 140, 29);
        jLayeredPane4.add(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLabel1.setBounds(360, 340, 140, 130);
        jLayeredPane4.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLayeredPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.forum_list=   (DefaultListModel)jList1.getModel();
        _forumsIndexSelected= jList1.getSelectedIndex();
        if (_forumsIndexSelected>=0  ){
            //TODO
        } else
            JOptionPane.showMessageDialog(null, "Please select one of the forums above.", "Forum Selection  Error", 0);
}//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        new Forum(_clientController).setSize(693,516);
        this.setVisible(false);
}//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane4;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}
