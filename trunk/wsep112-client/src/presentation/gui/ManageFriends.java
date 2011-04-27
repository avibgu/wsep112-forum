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

import common.forum.items.UserInfo;
import domain.ClientController;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author ׳�׳™׳¨׳™
 */
public class ManageFriends extends javax.swing.JFrame {

     ClientController controller;
     private StartWindow _start;
     public static DefaultListModel membersList= new DefaultListModel();
     public static DefaultListModel friendsList= new DefaultListModel();

    public boolean checkFriends(String member) {
        if (controller.getCurrentLogedInUsername().equals(member)) return false;
        for (int i=0; i<controller.getFriendList().size(); i++  ){
             UserInfo friend = controller.getFriendList().get(i);
             if ((friend.getUserName()).equals(member)) return false;
        }
        return true;
    }
    
    /** Creates new form ManageFriends */
   public ManageFriends(ClientController clientController, StartWindow start) {
        controller = clientController;
        _start=start;
        for (int i=0; i<controller.getFriendList().size(); i++  ){
                 UserInfo friend = controller.getFriendList().get(i);
                 friendsList.addElement(friend.getUserName());
        }
        for (int i=0; i<controller.getUsersList().size(); i++  ){
                 UserInfo member = controller.getUsersList().get(i);
                 if (checkFriends(member.getUserName())) {
                     membersList.addElement(member.getUserName()); }
        }
        setVisible(true);
        initComponents();
    }

   private StartWindow getStartWindow(){
       return _start;
   }

   public static  DefaultListModel  getMembersListModel() {
        return membersList;
    }

   public static  DefaultListModel  getFriendsListModel() {
        return friendsList;
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
        jScrollPane3 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jButton3.setFont(new java.awt.Font("Kristen ITC", 1, 12));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentation/gui/go_back.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(10, 370, 50, 30);

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Forum members", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Kristen ITC", 1, 14))); // NOI18N

        jList1.setModel(presentation.gui.ManageFriends.getFriendsListModel());
        jScrollPane2.setViewportView(jList1);

        jScrollPane2.setBounds(280, 130, 130, 160);
        jLayeredPane1.add(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton1.setFont(new java.awt.Font("Kristen ITC", 1, 14)); // NOI18N
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.setBounds(60, 300, 110, 30);
        jLayeredPane1.add(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton2.setFont(new java.awt.Font("Kristen ITC", 1, 14)); // NOI18N
        jButton2.setText("Remove");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jButton2.setBounds(290, 300, 110, 30);
        jLayeredPane1.add(jButton2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel2.setFont(new java.awt.Font("Kristen ITC", 1, 14));
        jLabel2.setText("Please choose members to add or remove to your list");
        jLabel2.setBounds(40, 30, 430, 70);
        jLayeredPane1.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jList2.setModel(presentation.gui.ManageFriends.getMembersListModel());
        jList2.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList2ValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(jList2);

        jScrollPane3.setBounds(50, 130, 130, 160);
        jLayeredPane1.add(jScrollPane3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel1.setFont(new java.awt.Font("Kristen ITC", 1, 12));
        jLabel1.setText("Add new friend");
        jLabel1.setBounds(60, 100, 110, 20);
        jLayeredPane1.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel3.setFont(new java.awt.Font("Kristen ITC", 1, 12));
        jLabel3.setText("Remove exist friend");
        jLabel3.setBounds(270, 100, 150, 20);
        jLayeredPane1.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        getContentPane().add(jLayeredPane1);
        jLayeredPane1.setBounds(20, 10, 490, 360);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
      getStartWindow().getForum().displayForum(new ForumsViewPanel(controller,getStartWindow() ) );
        this.setVisible(false);
}//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     int index= jList2.getSelectedIndex();
     if (index==-1){
           JOptionPane.showMessageDialog(null, "Please choose member to add.", "Error Adding friend", 0);
     }
     else{
          String name= (String)membersList.getElementAt(index);
          System.out.println("member name= " + name);
          controller.AddFriend(name);
     }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.friendsList = (DefaultListModel)jList1.getModel();
        int friendSelected = jList1.getSelectedIndex();
        String friendName =  Integer.toString(friendSelected);
        controller.RemoveFriend(friendName);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jList2ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList2ValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jList2ValueChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables

}
