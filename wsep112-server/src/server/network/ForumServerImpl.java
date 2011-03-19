/**
 * 
 */
package server.network;

import java.rmi.RemoteException;
import java.rmi.server.RemoteStub;

import common.network.ForumServer;
import common.network.messages.AddFriendMessage;
import common.network.messages.AddPostMessage;
import common.network.messages.AddThreadMessage;
import common.network.messages.ErrorMessage;
import common.network.messages.LoginMessage;
import common.network.messages.LogoutMessage;
import common.network.messages.Message;
import common.network.messages.OKMessage;
import common.network.messages.RegMessage;
import common.network.messages.RemoveFriendMessage;

import domain.ForumController;

/**
 * @author Avi Digmi
 *
 */
public class ForumServerImpl extends RemoteStub implements ForumServer {

	private static final long serialVersionUID = 2555939371339195609L;
	
	private ForumController forumController;

	public ForumServerImpl(ForumController forumController) throws RemoteException {
		
		super();
		setForumController(forumController);
	}
	
	/* (non-Javadoc)
	 * @see network.ForumServer#getInformation(network.Message)
	 */
	@Override
	public Message getInformation(Message whatToGet) throws RemoteException {
		
		// TODO:
		/*
	    SEE_FORUMS_LIST,
	    SEE_FORUM_THREADS
		SEE_POSTS_OF_SOME_THREAD
		*/
		return new OKMessage();
	}

	@Override
	public Message setInformation(Message whatToSet){

		switch(whatToSet.getMessageType()){
		
			case REGISTRATION:
				
				RegMessage rm = (RegMessage)whatToSet;

				return getForumController().register(rm.getFirstName(), rm.getLastName(), rm.getUsername(),
						rm.getPassword(), rm.getEmail());
			
			case LOGIN:
				
				LoginMessage lim = (LoginMessage)whatToSet;
				
				return getForumController().login(lim.getUsername(), lim.getPassword());
				
			case LOGOUT:
				
				LogoutMessage lom = (LogoutMessage)whatToSet;
				
				return getForumController().logout(lom.getUsername());
				
			case ADD_FRIEND:
				
				AddFriendMessage afm = (AddFriendMessage)whatToSet;
				
				return getForumController().AddFriend(afm.getUsername(), afm.getFriendUsername());
				
			case REMOVE_FRIEND:
				
				RemoveFriendMessage rfm = (RemoveFriendMessage)whatToSet;
				
				return getForumController().RemoveFriend(rfm.getUsername(), rfm.getFriendUsername());
			
			case ADD_POST_TO_THREAD:
				
				AddPostMessage apttm = (AddPostMessage)whatToSet;
				
				return getForumController().replyToThread(apttm.getTitle(), apttm.getBody(), apttm.getThreadId());

			case ADD_THREAD:
				
				AddThreadMessage athm = (AddThreadMessage)whatToSet;
				
				return getForumController().addThread(athm.getTitle(), athm.getBody());
				
			default:
				
				return new ErrorMessage("Message Type is unrecognized");
		}
	}

	public void setForumController(ForumController forumController) {
		this.forumController = forumController;
	}

	public ForumController getForumController() {
		return forumController;
	}
}
