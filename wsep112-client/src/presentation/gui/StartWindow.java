/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * StartWindow.java
 *
 * Created on 12/04/2011, 00:45:24
 */

package presentation.gui;

import common.network.messages.ErrorMessage;
import domain.ClientController;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author מירי
 */
public class StartWindow extends javax.swing.JFrame {

    ClientController controller;

    /** Creates new form StartWindow */
    public StartWindow( ClientController clientController) {
        controller = clientController;
        setVisible(true);
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField7 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField7);
        jTextField7.setBounds(210, 250, 120, 30);

        jLabel4.setFont(new java.awt.Font("Kristen ITC", 1, 14));
        jLabel4.setText("Password");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(120, 250, 73, 20);

        jLabel3.setFont(new java.awt.Font("Kristen ITC", 1, 14));
        jLabel3.setText("UserName");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(120, 210, 79, 20);

        jTextField6.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField6);
        jTextField6.setBounds(210, 200, 120, 30);

        jButton1.setFont(new java.awt.Font("Kristen ITC", 1, 14));
        jButton1.setText("Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(230, 300, 80, 30);

        jToggleButton1.setFont(new java.awt.Font("Kristen ITC", 1, 14));
        jToggleButton1.setForeground(new java.awt.Color(255, 0, 102));
        jToggleButton1.setText("Click here to register");
        jToggleButton1.setBorderPainted(false);
        jToggleButton1.setContentAreaFilled(false);
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jToggleButton1);
        jToggleButton1.setBounds(160, 370, 230, 29);

        jLabel2.setFont(new java.awt.Font("Kristen ITC", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 102));
        jLabel2.setText("Don't you have an account?");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(170, 350, 230, 20);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentation/gui/forums.jpg"))); // NOI18N
        jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 0, 0)));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 530, 430);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       if (jTextField6.getText().equals("") || (jTextField7.getText().equals(""))){
            JOptionPane.showMessageDialog(null, "Please insert all details!", "Login Error", 0);
       }
       else{
           String username = jTextField6.getText();
           String password = jTextField7.getText();
           System.out.println("username=" + username);
           System.out.println("password=" + password);
           ErrorMessage message = controller.login(username, password);
           if (message==null) {
                new Forum(controller, new ForumsViewPanel(controller) ).setSize(693,516);
                this.setVisible(false);
           }
           else System.out.println(message.getReason());
       }
}//GEN-LAST:event_jButton1ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
           new Register(controller).setSize(550,467);
           this.setVisible(false);
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables

}
