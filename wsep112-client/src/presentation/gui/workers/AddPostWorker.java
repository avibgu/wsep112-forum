package presentation.gui.workers;

import javax.swing.SwingWorker;

import domain.ClientController;

import presentation.gui.NewPost;
import presentation.gui.PostsViewPanel;
import presentation.gui.StartWindow;

public class AddPostWorker extends SwingWorker<Void, Void> {

	private NewPost _newPost;
	private ClientController _clientController;
	private StartWindow _startWindow;
	private String _forumID;
	private String _title;
	private String _body;
	private String _threadId;
	
	public AddPostWorker(NewPost newPost, ClientController clientController, StartWindow startWindow,
			String forumID, String title, String body, String threadId) {

		super();
		_newPost = newPost;
		_clientController = clientController;
		_startWindow = startWindow;
		_forumID = forumID;
		_title = title;
		_body = body;
		_threadId = threadId;
	}
	
	protected Void doInBackground() throws Exception {
		
		_clientController.replyToThread(_forumID, _title, _body, _threadId);
        
        return null;
	}

	protected void done(){

        //shows the new post list
		_startWindow.getForum().displayForum(
    		   new PostsViewPanel(_clientController, _forumID, _threadId, _startWindow));
        
        //removig the view of new post
		_newPost.setVisible(false);
		_newPost.updateUI();
	}

}
