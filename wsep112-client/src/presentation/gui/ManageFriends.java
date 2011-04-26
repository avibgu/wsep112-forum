/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ManageFriends.java
 *
 * Created on 15/04/2011, 00:09:52
 */

package presentation.gui;

import domain.ClientController;
import javax.swing.DefaultListModel;

/**
 *
 * @author מירי
 */
public class ManageFriends extends javax.swing.JFrame {

     ClientController controller;
     private StartWindow _start;
     public static DefaultListModel membersList= new DefaultListModel();

    /** Creates new form ManageFriends */
   public ManageFriends(ClientController clientController, StartWindow start) {
        controller = clientController;
        _start=start;
       /* //TO DO - wait for this implementation by avi
       for (int i=0; i<controller.getMembers().size(); i++  ){
                 String member = controller.getMembers().get(i);
                 membersList.addElement(member);
        }
         * /
         */
        setVisible(true);
        initComponents();
    }

   private StartWindow getStartWindow(){
       return _start;
   }

   public static  DefaultListModel  getMembersListModel() {
        return membersList;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jButton3.setFont(new java.awt.Font("Kristen ITC", 1, 12)); // NOI18N
        jButton3.setText("Back");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(460, 370, 65, 30);

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Forum members", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Kristen ITC", 1, 14))); // NOI18N

        jList1.setModel(presentation.gui.ManageFriends.getMembersListModel());
        jScrollPane2.setViewportView(jList1);

        jScrollPane2.setBounds(170, 140, 130, 160);
        jLayeredPane1.add(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton1.setFont(new java.awt.Font("Kristen ITC", 1, 14)); // NOI18N
        jButton1.setText("Add");
        jButton1.setBounds(130, 310, 90, 30);
        jLayeredPane1.add(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton2.setFont(new java.awt.Font("Kristen ITC", 1, 14)); // NOI18N
        jButton2.setText("Remove");
        jButton2.setBounds(240, 310, 91, 30);
        jLayeredPane1.add(jButton2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel2.setFont(new java.awt.Font("Kristen ITC", 1, 14)); // NOI18N
        jLabel2.setText("Please choose members to add or remove to your list");
        jLabel2.setBounds(40, 30, 430, 70);
        jLayeredPane1.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        getContentPane().add(jLayeredPane1);
        jLayeredPane1.setBounds(20, 10, 490, 360);

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\מירי\\Desktop\\סדנא\\forums.jpg")); // NOI18N
        jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 0, 0)));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 535, 410);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
      getStartWindow().getForum().displayForum(new ForumsViewPanel(controller,getStartWindow() ) );
        this.setVisible(false);
}//GEN-LAST:event_jButton3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

}
