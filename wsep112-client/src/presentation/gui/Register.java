
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

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Register", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Kristen ITC", 1, 14))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Kristen ITC", 1, 14));
        jLabel2.setText("FirstName");
        jLabel2.setBounds(110, 140, 79, 20);
        jLayeredPane1.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel6.setFont(new java.awt.Font("Kristen ITC", 1, 14));
        jLabel6.setText("LastName");
        jLabel6.setBounds(110, 180, 78, 20);
        jLayeredPane1.add(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel3.setFont(new java.awt.Font("Kristen ITC", 1, 14));
        jLabel3.setText("UserName");
        jLabel3.setBounds(110, 220, 79, 20);
        jLayeredPane1.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel4.setFont(new java.awt.Font("Kristen ITC", 1, 14));
        jLabel4.setText("Password");
        jLabel4.setBounds(110, 260, 73, 20);
        jLayeredPane1.add(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel5.setFont(new java.awt.Font("Kristen ITC", 1, 14));
        jLabel5.setText("Email");
        jLabel5.setBounds(140, 300, 50, 20);
        jLayeredPane1.add(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton1.setFont(new java.awt.Font("Kristen ITC", 1, 14));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentation/gui/pics/Ok-icon.png"))); // NOI18N
        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.setBounds(220, 340, 100, 30);
        jLayeredPane1.add(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel7.setFont(new java.awt.Font("Kristen ITC", 1, 14)); // NOI18N
        jLabel7.setText("Please insert all details in order to register to the forum");
        jLabel7.setBounds(30, 40, 450, 20);
        jLayeredPane1.add(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jTextField2.setBounds(200, 300, 140, 30);
        jLayeredPane1.add(jTextField2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jTextField5.setBounds(200, 260, 140, 30);
        jLayeredPane1.add(jTextField5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jTextField6.setBounds(200, 220, 140, 30);
        jLayeredPane1.add(jTextField6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jTextField3.setBounds(200, 180, 140, 30);
        jLayeredPane1.add(jTextField3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jTextField4.setBounds(200, 140, 140, 30);
        jLayeredPane1.add(jTextField4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        getContentPane().add(jLayeredPane1);
        jLayeredPane1.setBounds(20, 40, 490, 380);

        jButton2.setFont(new java.awt.Font("Kristen ITC", 1, 12));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentation/gui/pics/go_back.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(10, 10, 50, 30);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentation/gui/pics/forums.jpg"))); // NOI18N
        jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 0, 0)));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 530, 430);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       if (jTextField2.getText().equals("") ||  jTextField3.getText().equals("") || jTextField4.getText().equals("") || jTextField5.getText().equals("") || jTextField6.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please insert all details!", "Register Error", 0);
       }
       else{
           String firstname = jTextField4.getText();
           String lastname = jTextField3.getText();
           String username = jTextField6.getText();
           String password = jTextField5.getText();
           String email = jTextField2.getText();
           boolean flag = controller.register(firstname, lastname, username, password, email);
           if (flag){
                   this.getStartWindow().setForum(new Forum(controller, getStartWindow()));
                   this.setVisible(false);
           }
       }
}//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         new StartWindow(controller).setSize(546,465);
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables

}
