/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PostsViewPanel1.java
 *
 * Created on 28/04/2011, 09:23:32
 */

package presentation.gui;

import common.forum.items.PostInfo;
import domain.ClientController;
import java.util.Vector;
import javax.swing.DefaultListModel;

/**
 *
 * @author yedidim
 */
public class PostsViewPanel extends javax.swing.JPanel {

    private ClientController _clientController;
     private  DefaultListModel _postsList;
    private String _ForumId;
    private String _threadId;
    private int _postSelected;
    private  StartWindow _start;
    /** Creates new form PostsViewPanel1 */
    public PostsViewPanel(ClientController clientController, String forumId,String threadId ,StartWindow start) {

        _clientController= clientController;
        _postsList= new DefaultListModel();
        _ForumId= forumId;
        _start=start;
        _threadId=threadId;
        initComponents();
       this.fillListPosts();
        setVisible(true);
        
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
        newPost = new javax.swing.JButton();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        postsList = new javax.swing.JList();
        back = new javax.swing.JButton();

        jLayeredPane4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Forum System", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Kristen ITC", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        newPost.setFont(new java.awt.Font("Kristen ITC", 1, 14)); // NOI18N
        newPost.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentation/gui/pics/view_bottom.png"))); // NOI18N
        newPost.setText("Add Post");
        newPost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newPostActionPerformed(evt);
            }
        });
        newPost.setBounds(170, 350, 180, 40);
        jLayeredPane4.add(newPost, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Please choose one of the Posts below for view", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Kristen ITC", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        postsList.setFont(new java.awt.Font("Kristen ITC", 1, 14)); // NOI18N
        postsList.setModel(getThreadsListModel());
        postsList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        postsList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                postsListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(postsList);

        jScrollPane1.setBounds(10, 30, 470, 260);
        jLayeredPane2.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane2.setBounds(10, 30, 490, 300);
        jLayeredPane4.add(jLayeredPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentation/gui/pics/go_back.png"))); // NOI18N
        back.setBorder(null);
        back.setBorderPainted(false);
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });
        back.setBounds(20, 20, 40, 30);
        jLayeredPane4.add(back, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 508, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLayeredPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 459, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLayeredPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void newPostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newPostActionPerformed
        // Add Thread Button
        getStartWindow().nonDisplayForum(); //setVisible false
        getStartWindow().getForum().displayForum( new NewPost(this.getclientController(), this.getForum_id(), this.getThread_id(), this.getStartWindow()));
}//GEN-LAST:event_newPostActionPerformed

    private void postsListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_postsListValueChanged
        //View Thread Button
        //  this.thread_list=   (DefaultListModel)jList1.getModel();
     //  _threadsIndexSelected= jList1.getSelectedIndex();
      //  String threadIdString=  Integer.toString(_threadsIndexSelected);
           _postSelected= postsList.getSelectedIndex();
            Vector <PostInfo> posts= _clientController.getPostsList(this.getForum_id(), this.getThread_id());
            PostInfo post=posts.get(_postSelected);
        String   title=post.get_title();
        String body=post.get_body();
        String author=post.getOwner().getUserName();
        String dateTime=post.getDateTime();
        String thread_id=Integer.toString(post.getThread_id());
        String post_id=Integer.toString(post.get_post_id());
        
           //getStartWindow().nonDisplayForum(); //setVisible false
            getStartWindow().getForum().displayForum( new PostPanel(this.getclientController(), this.getForum_id(),this.getThread_id(), title, body, author, dateTime, post_id, _start));
            this.setVisible(false);
}//GEN-LAST:event_postsListValueChanged

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // Back Button
        this.setVisible(false);
        getStartWindow().nonDisplayForum(); //setVisible false
        getStartWindow().getForum().displayForum( new ThreadsViewPanel(this.getclientController(), this.getForum_id(), this.getStartWindow()));
}//GEN-LAST:event_backActionPerformed

    public void fillListPosts( ) {
         Vector <PostInfo> posts= _clientController.getPostsList(this.getForum_id(), this.getThread_id());
        //this.setVisible(true);
     for (int j=0; j<posts.size(); j++  ){
               getPostsListModel().addElement(posts.get(j).get_title());
             }
    }

      public ClientController getclientController(){
        return this._clientController;
    }
    public String getForum_id(){
        return this._ForumId;
    }
    public String getThread_id(){
        return this._threadId;
    }
   

     public  StartWindow getStartWindow(){
         return this._start;
     }

     public   DefaultListModel  getPostsListModel() {
        return _postsList;
    }


      public   DefaultListModel  getThreadsListModel() {
        return _postsList;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton newPost;
    private javax.swing.JList postsList;
    // End of variables declaration//GEN-END:variables

}