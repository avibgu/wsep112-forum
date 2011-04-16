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
            initComponents();
    }

    /** Creates new form ChooseSpaceShip */
    public void fillListForum( ) {
        Vector<String> forums= _clientController.getForumsList();
        //this.setVisible(true);
     for (int j=0; j<forums.size(); j++  ){
               _forumsList.addElement(forums.get(j));
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

        jLayeredPane3 = new javax.swing.JLayeredPane();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        jLayeredPane3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Forum System", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Kristen ITC", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Please choose one of the forums below", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Kristen ITC", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        jList1.setModel(getForumsListModel());
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jList1);

        jScrollPane1.setBounds(10, 30, 470, 220);
        jLayeredPane2.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane2.setBounds(10, 40, 490, 260);
        jLayeredPane3.add(jLayeredPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton1.setText("Enter Forum");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.setBounds(220, 300, 100, 23);
        jLayeredPane3.add(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentation/gui/go_back.png"))); // NOI18N
        jButton4.setBorder(null);
        jButton4.setBorderPainted(false);
        jButton4.setBounds(20, 20, 40, 30);
        jLayeredPane3.add(jButton4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 540, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLayeredPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 470, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLayeredPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(126, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.forum_list=   (DefaultListModel)jList1.getModel();
         _forumsIndexSelected= jList1.getSelectedIndex();
         if (_forumsIndexSelected>=0  ){
               //TODO
        }
       else
                JOptionPane.showMessageDialog(null, "Please selsct one of the forums above.!", "Forum Selection  Error", 0);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}