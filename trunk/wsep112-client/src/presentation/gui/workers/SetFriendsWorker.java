package presentation.gui.workers;

import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.SwingWorker;

import presentation.gui.Forum;

import common.forum.items.UserInfo;
import domain.ClientController;

public class SetFriendsWorker<T1, T2> extends SwingWorker<Void, Void> {

	private ClientController _controller;
	private Forum _forum;
	private Vector<UserInfo> _friends;
	private DefaultListModel _friendsList;
	
	public SetFriendsWorker(ClientController controller, Forum forum,
			DefaultListModel friendsList) {
		super();
		_controller = controller;
		_forum = forum;
		_friendsList = friendsList;
	}
	
	@Override
	protected Void doInBackground() throws Exception {

		_friends = _controller.getFriendList();				
		return null;
	}
	
	protected void done(){
		try{
			
			for (UserInfo friend : _friends)
	    		_friendsList.addElement(friend.getUserName());
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
