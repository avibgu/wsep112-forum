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
        Vector<ForumInfo> forums= getController().getForumsList();
        //this.setVisible(true);
     for (int j=0; j<forums.size(); j++  ){
               getForumsListModel().addElement(forums.get(j).getName());
              }
    }

    public   DefaultListModel  getForumsListModel() {
        return _forumsList;
    }

    private ClientController getController(){
        return this._clientController;
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jLayeredPane4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Forum System", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Kristen ITC", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Please choose one of the forums below", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Kristen ITC", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        jList1.setModel(getForumsListModel());
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jScrollPane1.setBounds(10, 30, 470, 300);
        jLayeredPane2.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane2.setBounds(10, 40, 490, 340);
        jLayeredPane4.add(jLayeredPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLabel1.setBounds(360, 340, 140, 130);
        jLayeredPane4.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentation/gui/forum_img.jpg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(161, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLayeredPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 1, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
         this.forum_list=   (DefaultListModel)jList1.getModel();
        _forumsIndexSelected= jList1.getSelectedIndex();
        String forumIdString=  Integer.toString(_forumsIndexSelected);
        if (_forumsIndexSelected>=0  ){
               this.setVisible(false);;
               new Forum(getController(), new ThreadsViewPanel(getController(),forumIdString ) ).setSize(693,516);
        }
    }//GEN-LAST:event_jList1ValueChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane4;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}
