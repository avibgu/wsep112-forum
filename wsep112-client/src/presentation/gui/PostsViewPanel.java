/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PostsViewPanel1.java
 *
 * Created on 26/04/2011, 19:46:07
 */

package presentation.gui;
import common.forum.items.PostInfo;
import domain.ClientController;
//import domain.DemoClientController;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.View;


/**
 *
 * @author yedidim
 */
public class PostsViewPanel extends javax.swing.JPanel {

 //class fields
    private ClientController _clientController;
    private String _ForumId;
    private String _threadId;
    private  StartWindow _start;

    /** Creates new form PostViewForm */
    public PostsViewPanel(ClientController clientController,String forum_id,String thread_id,StartWindow start)  {

        this._clientController=clientController;
        this._ForumId=forum_id;
        this._threadId=thread_id;
        this._start=start;
        initComponents();

        //avi shahimov
        make1_panels();
        setVisible(true);
       // postsPanel.setVisible(true);
        //avi shaimov

    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        back = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        newPost = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        postsPanel = new javax.swing.JPanel();

        mainPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "forum system", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Kristen ITC", 3, 14), new java.awt.Color(255, 0, 0))); // NOI18N

        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentation/gui/go_back.png"))); // NOI18N
        back.setBorder(null);
        back.setBorderPainted(false);
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Kristen ITC", 3, 14));
        jLabel2.setText("List Of Posts");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentation/gui/kuser.png"))); // NOI18N

        newPost.setFont(new java.awt.Font("Kristen ITC", 1, 14));
        newPost.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentation/gui/Threadadd.png"))); // NOI18N
        newPost.setText("Add New Post");
        newPost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newPostActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout postsPanelLayout = new javax.swing.GroupLayout(postsPanel);
        postsPanel.setLayout(postsPanelLayout);
        postsPanelLayout.setHorizontalGroup(
            postsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 530, Short.MAX_VALUE)
        );
        postsPanelLayout.setVerticalGroup(
            postsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(postsPanel);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)
                        .addComponent(newPost, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(2, 2, 2)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addComponent(newPost, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 517, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 485, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed

       getStart().getForum().displayForum(new ThreadsViewPanel(this.getclientController(),this.getForum_id(),this.getStart()));
        this.setVisible(false);
}//GEN-LAST:event_backActionPerformed

    private void newPostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newPostActionPerformed

       getStart().getForum().displayForum(new NewPost(this.getclientController(), this.getForum_id(),this.getThread_id(),this.getStart()));
        this.setVisible(false);
}//GEN-LAST:event_newPostActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton newPost;
    private javax.swing.JPanel postsPanel;
    // End of variables declaration//GEN-END:variables

     public void make1_panels(){
        String title;
        String body;
        String author;
        String dateTime;
        String thread_id;
        String post_id;
         // Vector<PostInfo> posts = _clientController.getPostsList(_ForumId,_threadId);
          Vector<PostInfo> posts = this.getclientController().getPostsList(this.getForum_id(),this.getThread_id());
        List<JPanel> panels = new ArrayList<JPanel>();

        for (PostInfo post : posts) {
        title=post.get_title();
        body=post.get_body();
        author=post.getOwner().getUserName();
        dateTime=post.getDateTime();
        thread_id=Integer.toString(post.getThread_id());
        post_id=Integer.toString(post.get_post_id());
        JPanel jPanel = new PostPanel(this.getclientController(),this.getForum_id(),this.getThread_id(),title,body,author,dateTime,post_id,this.getStart());
         panels.add(jPanel);

        }

        addPanels(panels);

       this.getPostsPanel().updateUI();

    }
    /**
     * adds pannels to posts panel for view
     * @param newPanels
     */
     public void addPanels(List<JPanel> newPanels){
        //postsPanel = new javax.swing.JPanel();
        javax.swing.GroupLayout postsPanelLayout = new javax.swing.GroupLayout(this.getPostsPanel());
        this.getPostsPanel().setLayout(postsPanelLayout);

        postsPanelLayout.setHorizontalGroup(
            postsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 488, Short.MAX_VALUE)
        );

        postsPanelLayout.setVerticalGroup(
            postsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 232, Short.MAX_VALUE)
        );


        ParallelGroup group1 = postsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING);

        for (JPanel jPanel : newPanels) {
                group1.addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        }

        postsPanelLayout.setHorizontalGroup(
           postsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(postsPanelLayout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addGroup(group1)
                .addContainerGap(167, Short.MAX_VALUE))
        );

        SequentialGroup group2 =postsPanelLayout.createSequentialGroup();

        for (JPanel jPanel : newPanels) {

            group2.addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        }

        postsPanelLayout.setVerticalGroup(
        postsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(group2.addContainerGap(25, Short.MAX_VALUE))
        );

       // pack();

    }


       //******************************************* GETTERS ************************************************//
    public ClientController getclientController(){
        return this._clientController;
    }
    public String getForum_id(){
        return this._ForumId;
    }
    public String getThread_id(){
        return this._threadId;
    }
     public JPanel getPostsPanel(){
         return postsPanel;
     }

     public  StartWindow getStart(){
         return this._start;
     }


       /**
    * @param args the command line arguments
    */
/* public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PostsViewPanel(new DemoClientController(),"1","2");
            }
        });
    }*/

}
