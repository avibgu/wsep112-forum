
/*
 * Register.java
 *
 * Created on 10/04/2011, 19:33:16
 */

package presentation.gui;

import common.network.messages.ErrorMessage;
import domain.ClientController;
import javax.swing.JOptionPane;

/**
 *
 * @author ׳�׳™׳¨׳™
 */
public class Register extends javax.swing.JFrame {

    ClientController controller;
    StartWindow _start;

    /** Creates new form Register */
    public Register(ClientController clientController, StartWindow start) {
         controller = clientController;
         _start=start;
        setVisible(true);
        initComponents();
    }

     private StartWindow getStartWindow(){
        return this._start;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField6 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jCheckBox1 = new javax.swing.JCheckBox();
        jTextField4 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel2.setFont(new java.awt.Font("Kristen ITC", 1, 14));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("FirstName");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(360, 150, 79, 20);

        jLabel6.setFont(new java.awt.Font("Kristen ITC", 1, 14));
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("LastName");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(360, 190, 78, 20);

        jLabel3.setFont(new java.awt.Font("Kristen ITC", 1, 14));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("UserName");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(360, 230, 79, 20);

        jLabel4.setFont(new java.awt.Font("Kristen ITC", 1, 14));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Password");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(360, 270, 73, 20);

        jLabel5.setFont(new java.awt.Font("Kristen ITC", 1, 14));
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("License Agreement");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(250, 340, 149, 20);

        jButton1.setFont(new java.awt.Font("Kristen ITC", 1, 14));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentation/gui/pics/Ok-icon.png"))); // NOI18N
        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(460, 530, 110, 33);

        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField6);
        jTextField6.setBounds(450, 230, 130, 30);
        getContentPane().add(jTextField2);
        jTextField2.setBounds(450, 310, 130, 30);

        jLabel7.setFont(new java.awt.Font("Kristen ITC", 1, 18));
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Please insert all details in order to register to the forum");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(220, 90, 570, 25);
        getContentPane().add(jTextField3);
        jTextField3.setBounds(450, 190, 130, 30);
        getContentPane().add(jTextField5);
        jTextField5.setBounds(450, 270, 130, 30);

        jLabel8.setFont(new java.awt.Font("Kristen ITC", 1, 14));
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Email");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(380, 310, 42, 20);

        jScrollPane1.setName(""); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setText("IMPORTANT! PLEASE READ CAREFULLY!\nBY COPYING, OR USING OUR FORUM(S)IN ANY MANNER,\nYOU AGREE TO BE BOUND BY THE TERMS OF THIS AGREEMENT AND PRIVACY POLICY.\nIF YOU DO NOT AGREE WITH OUR END USER LICENSE AGREEMENT,\nPLEASE DO NOT PROCEED WITH THE REGISTRATION PROCESS\nAND LEAVE OUR SITES IMMEDIATELY AND DO NOT ACCESS OR USE OUR FORUM(S).");
        jTextArea1.setEnabled(false);
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(250, 370, 520, 100);

        jCheckBox1.setFont(new java.awt.Font("Kristen ITC", 1, 12));
        jCheckBox1.setLabel("I Agree");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jCheckBox1);
        jCheckBox1.setBounds(470, 480, 90, 25);
        getContentPane().add(jTextField4);
        jTextField4.setBounds(450, 150, 130, 30);

        jButton2.setFont(new java.awt.Font("Kristen ITC", 1, 12));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentation/gui/pics/go_back.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(10, 10, 50, 30);

        jLabel9.setFont(new java.awt.Font("Kristen ITC", 1, 36));
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Register");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(430, 30, 180, 49);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentation/gui/pics/wallpaper_by_J_Sheldon.jpg"))); // NOI18N
        jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 0, 0)));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 970, 610);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         new StartWindow(controller).setSize(985,605);
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (jTextField2.getText().equals("") ||  jTextField3.getText().equals("") || jTextField4.getText().equals("") || jTextField5.getText().equals("") || jTextField6.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please insert all details!", "Register Error", 0);
        } else{
            if(!(jCheckBox1.isSelected())){
                JOptionPane.showMessageDialog(null, "Please accept the term!", "License Agreement", 0);
             }
            else {
            String firstname = jTextField4.getText();
            String lastname = jTextField3.getText();
            String username = jTextField6.getText();
            String password = jTextField5.getText();
            String email = jTextField2.getText();
            boolean flag = controller.register(firstname, lastname, username, password, email, "");
            if (flag){
                this.getStartWindow().setForum(new Forum(controller, getStartWindow()));
                this.setVisible(false);
            }
        }
}//GEN-LAST:event_jButton1ActionPerformed
    }
    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables

}
