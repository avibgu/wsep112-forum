/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AddThreadPanel.java
 *
 * Created on 27/04/2011, 13:23:49
 */

package presentation.gui;

import domain.ClientController;
import javax.swing.JOptionPane;

/**
 *
 * @author מוש
 */
public class AddThreadPanel extends javax.swing.JPanel {
         private ClientController _client;
         private String _forumId;
         private StartWindow _start;

    /** Creates new form AddThreadFrame */
    public AddThreadPanel(ClientController c, String forumId,StartWindow start) {
         _client=c;
        _forumId= forumId;
        _start=start;
        setVisible(true);
        initComponents();
    }

     private ClientController getClientController(){
        return this._client;
    }

    private StartWindow getStartWindow(){
        return _start;
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
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Add Thread", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Kristen ITC", 1, 14))); // NOI18N
        jLayeredPane1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField3.setBounds(70, 80, 380, 250);
        jLayeredPane1.add(jTextField3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jTextField4.setBounds(70, 40, 380, 30);
        jLayeredPane1.add(jTextField4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton1.setFont(new java.awt.Font("Kristen ITC", 1, 14));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentation/gui/addThreadIcon.png"))); // NOI18N
        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.setBounds(120, 380, 120, 30);
        jLayeredPane1.add(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton2.setFont(new java.awt.Font("Kristen ITC", 1, 14));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentation/gui/1302965210_Cancel2.png"))); // NOI18N
        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jButton2.setBounds(280, 380, 130, 30);
        jLayeredPane1.add(jButton2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel6.setFont(new java.awt.Font("Kristen ITC", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Body");
        jLabel6.setBounds(20, 80, 38, 20);
        jLayeredPane1.add(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel2.setFont(new java.awt.Font("Kristen ITC", 1, 14));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("title");
        jLabel2.setBounds(20, 40, 79, 20);
        jLayeredPane1.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(203, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //OK Button
        if (jTextField3.getText().equals("") ||  jTextField4.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please insert all details of Thread", "Add Thread Error", 0);
        } else{
            String title = jTextField4.getText(); //Title
            String body = jTextField3.getText(); //Body
            getClientController().addThread(_forumId, title, body);
            getStartWindow().getForum().displayForum(null); //setVisible false
            getStartWindow().getForum().displayForum( new ThreadsViewPanel( getClientController(),_forumId,  getStartWindow()));
            this.setVisible(false);
        }
}//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //Cancel Button
        getStartWindow().getForum().displayForum(null); //setVisible false
        getStartWindow().getForum().displayForum( new ThreadsViewPanel( getClientController(),_forumId,  getStartWindow()));
        setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables

}
