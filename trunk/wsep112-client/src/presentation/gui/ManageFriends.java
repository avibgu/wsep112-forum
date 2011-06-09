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

import java.util.Vector;

import common.forum.items.UserInfo;
import domain.ClientController;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

/**
 *
 * @author ׳�׳™׳¨׳™
 */
public class ManageFriends extends javax.swing.JFrame {

     ClientController controller;
     private StartWindow _start;
     private String _nameSearch;
     public static DefaultListModel membersList= new DefaultListModel();
     public static DefaultListModel friendsList= new DefaultListModel();

    public boolean checkFriends(String member) {

    	if (controller.getCurrentLogedInUsername().equals(member)) return false;

//		Vector<UserInfo> friends = controller.getFriendList();
//		
//		for (UserInfo friend : friends)
//			if ((friend.getUserName()).equals(member)) return false;
//		
//        return true;
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
        _nameSearch = "";
//		Vector<UserInfo> friends = controller.getFriendList();
//		
//		for (UserInfo friend : friends)
//			friendsList.addElement(friend.getUserName());
//			
//		Vector<UserInfo> members = controller.getFriendList();
//			
//		for (UserInfo member : members)
//			if (checkFriends(member.getUserName()))
//                membersList.addElement(member.getUserName());
  
        if (_nameSearch.equals("")){
              for (int i=0; i<controller.getFriendList().size(); i++  ){
                  UserInfo friend = controller.getFriendList().get(i);
                  friendsList.addElement(friend.getUserName());
             }
       }
        for (int i=0; i<controller.getUsersList().size(); i++  ){
                 UserInfo member = controller.getUsersList().get(i);
                 if (checkFriends(member.getUserName())) {
                     membersList.addElement(member.getUserName()); }
        }
        
        setVisible(true);
        initComponents();
        this.setSize(540,449);
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
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jButton3.setFont(new java.awt.Font("Kristen ITC", 1, 12));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentation/gui/pics/go_back.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(20, 20, 50, 30);

        jButton1.setFont(new java.awt.Font("Kristen ITC", 1, 14));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentation/gui/pics/add-user-icon (1).png"))); // NOI18N
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(370, 430, 150, 41);

        jButton2.setFont(new java.awt.Font("Kristen ITC", 1, 14));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentation/gui/pics/remove-user-icon (1).png"))); // NOI18N
        jButton2.setText("Remove");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(660, 430, 150, 41);

        jLabel2.setFont(new java.awt.Font("Kristen ITC", 1, 18));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Please choose members to add or remove to your list");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(210, 130, 530, 25);

        jList2.setFont(new java.awt.Font("Kristen ITC", 1, 14));
        jList2.setModel(presentation.gui.ManageFriends.getMembersListModel());
        jList2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList2.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList2ValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(jList2);

        getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(370, 230, 150, 150);

        jLabel1.setFont(new java.awt.Font("Kristen ITC", 1, 18));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Add new friend");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(360, 190, 200, 25);

        jLabel3.setFont(new java.awt.Font("Kristen ITC", 1, 18));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Remove exist friend");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(630, 190, 240, 25);

        jLabel6.setFont(new java.awt.Font("Kristen ITC", 1, 18));
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Search Friend");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(110, 190, 160, 30);

        jTextField1.setFont(new java.awt.Font("Kristen ITC", 1, 14));
        getContentPane().add(jTextField1);
        jTextField1.setBounds(100, 230, 160, 50);

        jCheckBox1.setFont(new java.awt.Font("Kristen ITC", 1, 12)); // NOI18N
        jCheckBox1.setText("show all users");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jCheckBox1);
        jCheckBox1.setBounds(370, 390, 140, 25);

        jButton4.setFont(new java.awt.Font("Kristen ITC", 1, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentation/gui/pics/SearchUser.png"))); // NOI18N
        jButton4.setText("Search");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4);
        jButton4.setBounds(100, 430, 160, 40);

        jList1.setFont(new java.awt.Font("Kristen ITC", 1, 14));
        jList1.setModel(presentation.gui.ManageFriends.getFriendsListModel());
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jList1);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(660, 230, 150, 150);

        jLabel5.setFont(new java.awt.Font("Kristen ITC", 1, 36));
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Forum members");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(340, 50, 320, 50);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentation/gui/pics/wallpaper_by_J_Sheldon.jpg"))); // NOI18N
        getContentPane().add(jLabel4);
        jLabel4.setBounds(0, -40, 1020, 680);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        friendsList.removeAllElements();
        membersList.removeAllElements();
        getStartWindow().getForum().displayForum(new ForumsViewPanel(controller,getStartWindow()));
        this.setVisible(false);
}//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     int index = jList2.getSelectedIndex();
     if (index==-1){
           JOptionPane.showMessageDialog(null, "Please choose member to add.", "Error Adding friend", 0);
     }
     else{
          String name= (String)membersList.getElementAt(index);
          if (controller.AddFriend(name)) {
              membersList.removeElementAt(index);
              friendsList.addElement(name);
          };
     }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
     int index = jList1.getSelectedIndex();
     if (index==-1){
           JOptionPane.showMessageDialog(null, "Please choose friend to remove.", "Error Removing friend", 0);
     }
     else{
          String name= (String)friendsList.getElementAt(index);
          if (controller.RemoveFriend(name)) {
              friendsList.removeElementAt(index);
              membersList.addElement(name);
          };
     }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jList2ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList2ValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jList2ValueChanged

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jList1ValueChanged

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       _nameSearch = jTextField1.getText();
       if (_nameSearch.equals("")){
            JOptionPane.showMessageDialog(null, "Please type search name!", "Invalid seach name", 0);
        }
       else{
           if (!jCheckBox1.isSelected()){
           membersList.removeAllElements();
            Vector <UserInfo> friendSearchList = controller.searchFriendsByInput(_nameSearch);
           for (int i=0; i<friendSearchList.size(); i++  ){
                    membersList.addElement(friendSearchList.get(i).getUserName());
           }
           }
            else
                    JOptionPane.showMessageDialog(null, "Please remove option 'Show all users'", "Error", 0);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if (jCheckBox1.isSelected()){
            membersList.removeAllElements();
                    for (int i=0; i<controller.getUsersList().size(); i++  ){
                 UserInfo member = controller.getUsersList().get(i);
                 if (checkFriends(member.getUserName())) {
                     membersList.addElement(member.getUserName()); }
            }
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

}
