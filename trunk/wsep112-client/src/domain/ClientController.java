/**
 *
 */
package domain;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Observer;
import java.util.Vector;
import java.util.logging.Logger;


import common.encryption.SHA1;
import common.forum.items.ForumInfo;
import common.forum.items.PostInfo;
import common.forum.items.ThreadInfo;
import common.forum.items.UserInfo;
import common.network.ForumServer;
import common.network.messages.AddFriendMessage;
import common.network.messages.AddPostMessage;
import common.network.messages.AddThreadMessage;
import common.network.messages.ChangePasswordMessage;
import common.network.messages.EditPostMessage;
import common.network.messages.ErrorMessage;
import common.network.messages.LoginMessage;
import common.network.messages.LogoutMessage;
import common.network.messages.Message;
import common.network.messages.MessageType;
import common.network.messages.OKMessage;
import common.network.messages.RegMessage;
import common.network.messages.RemoveFriendMessage;
import common.network.messages.RemovePostMessage;
import common.network.messages.RemoveThreadMessage;
import common.network.messages.SeeForumThreadsMessage;
import common.network.messages.SeeForumsListMessage;
import common.network.messages.SeeFriendsMessage;
import common.network.messages.SeeThreadPostsMessage;
import common.network.messages.SeeUsersMessage;
import common.notifications.Notification;
import common.notifications.ThreadChangedNotification;
import common.observation.Observable;
import common.observation.RemoteObserver;


/**
 * @author Avi Digmi
 *
 */
public class ClientController extends UnicastRemoteObject implements RemoteObserver, Observable{

	private static final long serialVersionUID = 4717931904659119985L;
	
	private ForumServer _forumServerStub;
	private Logger _logger;
	private String _currentLogedInUsername;
	private String _currentShownForum;			//AVID: use this variable.. ????
	private String _currentShownThread;			//AVID: use this variable.. ????
	private ArrayList<Observer> _observers;

	public ClientController(ForumServer forumServerStub, Logger logger)  throws RemoteException {

		setForumServerStub(forumServerStub);
		setLogger(logger);
		setCurrentLogedInUsername("");
		setObservers(new ArrayList<Observer>());
	}

	/**
	 *
	 * @param firstName
	 * @param lastName
	 * @param username
	 * @param password
	 * @param email
	 *
	 * @return OKMessage on success, or ErrorMessage (with reason) on failure
	 */
	public boolean register(String firstName, String lastName, String username,
			String password, String email) {
		
		ErrorMessage errorMessage;
		
		// Check if the password is strong enough.
		if (!validPassword(password)){
			errorMessage = new ErrorMessage("Password is too weak.");
		}
		
		// Check if the mail is valid.
		else if (!validMail(email)){
			errorMessage = new ErrorMessage("Mail address is invalid.");
		}
		
		else{

			// Encrypt the password using SHA1 algorithm.
			String tEncrypted_Password = SHA1.hash(password);
	
			RegMessage rm = new RegMessage(firstName, lastName, username,
					tEncrypted_Password, email, this);
	
			try {
	
				Message answer = getForumServerStub().setInformation(rm);

				if (answer.getMessageType() == MessageType.OK){
					
					setCurrentLogedInUsername(username);
					return true;
				}

				errorMessage = (ErrorMessage)answer;
			}
			catch (RemoteException e) {
				
				String reason = "Connection Error - can't connect with the server";
				
				log(reason);
				errorMessage = new ErrorMessage(reason);
			}
		}

		errorMessage.setForWho("REGERROR");
		
		notifyObservers(errorMessage);
		
		return false;
	}

	/**
	 *
	 * @param username
	 * @param password
	 *
	 * @return OKMessage on success, or ErrorMessage (with reason) on failure
	 */
	public boolean login(String username, String password) {

		ErrorMessage errorMessage;
		
		// Encrypt the password using SHA1 algorithm.
		String tEncrypted_Password = SHA1.hash(password);

		LoginMessage lm = new LoginMessage(username, tEncrypted_Password, this);

		try {

			Message answer = getForumServerStub().setInformation(lm);
			
			if (answer.getMessageType() == MessageType.OK){
				
				setCurrentLogedInUsername(username);
				return true;
			}

			errorMessage = (ErrorMessage)answer;
		}
		catch (RemoteException e) {
			
			String reason = "Connection Error - can't connect with the server";
			
			log(reason);
			errorMessage = new ErrorMessage(reason);
		}

		errorMessage.setForWho(getCurrentLogedInUsername());
		
		notifyObservers(errorMessage);
		
		return false;
	}
	
	/**
	 *
	 * @param username
	 * @param password
	 *
	 * @return OKMessage on success, or ErrorMessage (with reason) on failure
	 */
	public boolean changePass(String username, String password) {
		
		ErrorMessage errorMessage;
		
		// Check if the password is strong enough.
		if (!validPassword(password)){
			errorMessage = new ErrorMessage("Password is too weak.");
		}
		
		else{

			// Encrypt the password using SHA1 algorithm.
			String tEncrypted_Password = SHA1.hash(password);
	
			ChangePasswordMessage cpm = new ChangePasswordMessage(username, tEncrypted_Password, this);
	
			try {
	
				Message answer = getForumServerStub().setInformation(cpm);

				if (answer.getMessageType() == MessageType.OK){

					return true;
				}

				errorMessage = (ErrorMessage)answer;
			}
			catch (RemoteException e) {
				
				String reason = "Connection Error - can't connect with the server";
				
				log(reason);
				errorMessage = new ErrorMessage(reason);
			}
		}

		errorMessage.setForWho(getCurrentLogedInUsername());
		
		notifyObservers(errorMessage);
		
		return false;
	}

	/**
	 *
	 * @param username
	 *
	 * @return OKMessage on success, or ErrorMessage (with reason) on failure
	 */
	public void logout() {
		
		ErrorMessage errorMessage;

		LogoutMessage lm = new LogoutMessage(getCurrentLogedInUsername(), this);

		setCurrentLogedInUsername("");
		
		try {
			
			Message answer = getForumServerStub().setInformation(lm);
			
			if (answer.getMessageType() == MessageType.OK){
				
				setCurrentLogedInUsername("");
				return;
			}

			errorMessage = (ErrorMessage)answer;
		}
		catch (RemoteException e) {
			
			String reason = "Connection Error - can't connect with the server";
			
			log(reason);
			errorMessage = new ErrorMessage(reason);
		}

		errorMessage.setForWho(getCurrentLogedInUsername());
		
		notifyObservers(errorMessage);
	}

    /**
     *
     * @param username
     * @param friendUsername
     *
     * @return OKMessage on success, or ErrorMessage (with reason) on failure
     */
    public boolean AddFriend(String friendUsername) {

    	ErrorMessage errorMessage;
    	
    	AddFriendMessage afm = new AddFriendMessage(getCurrentLogedInUsername(),
    			friendUsername, this);

		try {

			Message answer = getForumServerStub().setInformation(afm);
			
			if (answer.getMessageType() != MessageType.ERROR) return true;

			errorMessage = (ErrorMessage)answer;
		}
		catch (RemoteException e) {
			
			String reason = "Connection Error - can't connect with the server";
			
			log(reason);
			errorMessage = new ErrorMessage(reason);
		}

		errorMessage.setForWho(getCurrentLogedInUsername());
		
		notifyObservers(errorMessage);
		
		return false;
    }

    /**
     *
     * @param username
     * @param friendUsername
     *
     * @return OKMessage on success, or ErrorMessage (with reason) on failure
     */
    public boolean RemoveFriend(String friendUsername) {

    	ErrorMessage errorMessage;
    	
    	RemoveFriendMessage rfm = new RemoveFriendMessage(getCurrentLogedInUsername(),
    			friendUsername, this);

		try {

			Message answer = getForumServerStub().setInformation(rfm);

			if (answer.getMessageType() != MessageType.ERROR) return true;

			errorMessage = (ErrorMessage)answer;
		}
		catch (RemoteException e) {
			
			String reason = "Connection Error - can't connect with the server";
			
			log(reason);
			errorMessage = new ErrorMessage(reason);
		}

		errorMessage.setForWho(getCurrentLogedInUsername());
		
		notifyObservers(errorMessage);
		
		return false;
    }
    
	public Vector<UserInfo> searchFriendsByInput(String input) {

		ErrorMessage errorMessage;

    	SeeUsersMessage sum = new SeeUsersMessage();

		try {

			Message answer = getForumServerStub().getInformation(sum);
			
			if (answer.getMessageType() != MessageType.ERROR){
				Vector<UserInfo> users = ((SeeUsersMessage)answer).getListOfUsers();
				for (int i=0; i<users.size(); i++){
					if (!users.get(i).getUserName().contains(input)){
						users.remove(i);
					}
				}
				return users;
			}
			errorMessage = (ErrorMessage)answer;
		}
		catch (RemoteException e) {
			
			String reason = "Connection Error - can't connect with the server";
			
			log(reason);
			errorMessage = new ErrorMessage(reason);
		}

		errorMessage.setForWho(getCurrentLogedInUsername());
		
		notifyObservers(errorMessage);

		return null;
	}
    

    /**
     *
     * @param title
     * @param body
     * @param threadId
     *
     * @return OKMessage on success, or ErrorMessage (with reason) on failure
     */
    public void replyToThread(String forumID, String title, String body, String threadId) {

    	ErrorMessage errorMessage;
    	
    	AddPostMessage apm = new AddPostMessage(forumID,title, body, threadId,
    			getCurrentLogedInUsername(), this);

		try {

			Message answer = getForumServerStub().setInformation(apm);

			if (answer.getMessageType() != MessageType.ERROR) return;
			
			errorMessage = (ErrorMessage)answer;
		}
		catch (RemoteException e) {
			
			String reason = "Connection Error - can't connect with the server";
			
			log(reason);
			errorMessage = new ErrorMessage(reason);
		}

		errorMessage.setForWho(getCurrentLogedInUsername());
		
		notifyObservers(errorMessage);
    }
    
    /**
    *
    * @param title
    * @param body
    * @param threadId
    *
    * @return OKMessage on success, or ErrorMessage (with reason) on failure
    */
   public void editPost(String forumID, String title, String body,
		   String threadId, String postId) {

   	ErrorMessage errorMessage;
   	
   	EditPostMessage apm = new EditPostMessage(forumID, title, body,
   			threadId, postId, getCurrentLogedInUsername(), this);

		try {

			Message answer = getForumServerStub().setInformation(apm);

			if (answer.getMessageType() != MessageType.ERROR) return;
			
			errorMessage = (ErrorMessage)answer;
		}
		catch (RemoteException e) {
			
			String reason = "Connection Error - can't connect with the server";
			
			log(reason);
			errorMessage = new ErrorMessage(reason);
		}

		errorMessage.setForWho(getCurrentLogedInUsername());
		
		notifyObservers(errorMessage);
   }

    /**
     *
     * @param title
     * @param body
     *
     * @return OKMessage on success, or ErrorMessage (with reason) on failure
     */
    public void addThread(String forumID,String title, String body) {

    	ErrorMessage errorMessage;
    	
    	AddThreadMessage atm = new AddThreadMessage(forumID,title, body,
    			getCurrentLogedInUsername(), this);

		try {
			
			Message answer = getForumServerStub().setInformation(atm);
			
			if (answer.getMessageType() != MessageType.ERROR) return;
			
			errorMessage = (ErrorMessage)answer;
		}
		catch (RemoteException e) {
			
			String reason = "Connection Error - can't connect with the server";
			
			log(reason);
			errorMessage = new ErrorMessage(reason);
		}

		errorMessage.setForWho(getCurrentLogedInUsername());
		
		notifyObservers(errorMessage);
    }

    /**
     *
     * @param sflm
     *
     * @return list of Forums, or null on failure
     */
    public Vector<ForumInfo> getForumsList() {

    	ErrorMessage errorMessage;
    	
    	SeeForumsListMessage sflm = new SeeForumsListMessage();

		try {
			
			Message answer = getForumServerStub().getInformation(sflm);
			
			if (answer.getMessageType() != MessageType.ERROR)
				return ((SeeForumsListMessage)answer).getListOfForums();
			
			errorMessage = (ErrorMessage)answer;
		}
		catch (RemoteException e) {
			
			String reason = "Connection Error - can't connect with the server";
			
			log(reason);
			errorMessage = new ErrorMessage(reason);
		}

		errorMessage.setForWho(getCurrentLogedInUsername());
		
		notifyObservers(errorMessage);

		return null;
    }

	/**
	 *
	 * @param sflm
	 *
	 * @return list of Threads inside the given message, or ErrorMessage (with reason) on failure
	 */
	public Vector<ThreadInfo> getThreadsList(String forumID) {

		ErrorMessage errorMessage;
		
    	SeeForumThreadsMessage sftm = new SeeForumThreadsMessage(forumID);

		try {
			Message answer = getForumServerStub().getInformation(sftm);
			if (answer.getMessageType() != MessageType.ERROR)
				return ((SeeForumThreadsMessage)answer).getListOfThreads();
			errorMessage = (ErrorMessage)answer;
		}
		catch (RemoteException e) {
			
			String reason = "Connection Error - can't connect with the server";
			
			log(reason);
			errorMessage = new ErrorMessage(reason);
		}

		errorMessage.setForWho(getCurrentLogedInUsername());
		
		notifyObservers(errorMessage);

		return null;
	}

	/**
	 *
	 * @param threadID
	 * @param stpm
	 *
	 * @return list of Posts inside the given message, or ErrorMessage (with reason) on failure
	 */
	public Vector<PostInfo> getPostsList(String threadID) {

		ErrorMessage errorMessage;

    	SeeThreadPostsMessage stpm = new SeeThreadPostsMessage(
    			threadID, getCurrentLogedInUsername(), this);

    	setCurrentShownThread(threadID);
    	
		try {

			Message answer = getForumServerStub().getInformation(stpm);
			
			if (answer.getMessageType() != MessageType.ERROR)
				return ((SeeThreadPostsMessage)answer).getListOfPosts();
			
			errorMessage = (ErrorMessage)answer;
		}
		catch (RemoteException e) {
			
			String reason = "Connection Error - can't connect with the server";
			
			log(reason);
			errorMessage = new ErrorMessage(reason);
		}

		errorMessage.setForWho(getCurrentLogedInUsername());
		
		notifyObservers(errorMessage);

		return null;
	}

	/**
	 * 
	 * @param forumID
	 * @param threadID
	 * 
	 * @return list of Friends inside the given message, or ErrorMessage (with reason) on failure
	 */
	public Vector<UserInfo> getFriendList() {

		ErrorMessage errorMessage;

    	SeeFriendsMessage sfm = new SeeFriendsMessage(getCurrentLogedInUsername());

		try {

			Message answer = getForumServerStub().getInformation(sfm);
			
			if (answer.getMessageType() != MessageType.ERROR)
				return ((SeeFriendsMessage)answer).getListOfFriends();
			
			errorMessage = (ErrorMessage)answer;
		}
		catch (RemoteException e) {
			
			String reason = "Connection Error - can't connect with the server";
			
			log(reason);
			errorMessage = new ErrorMessage(reason);
		}

		errorMessage.setForWho(getCurrentLogedInUsername());
		
		notifyObservers(errorMessage);

		return null;
	}
	
	/**
	 * 
	 * @return list of Users inside the given message, or ErrorMessage (with reason) on failure
	 */
	public Vector<UserInfo> getUsersList() {

		ErrorMessage errorMessage;

    	SeeUsersMessage sum = new SeeUsersMessage();

		try {

			Message answer = getForumServerStub().getInformation(sum);
			
			if (answer.getMessageType() != MessageType.ERROR)
				return ((SeeUsersMessage)answer).getListOfUsers();
			
			errorMessage = (ErrorMessage)answer;
		}
		catch (RemoteException e) {
			
			String reason = "Connection Error - can't connect with the server";
			
			log(reason);
			errorMessage = new ErrorMessage(reason);
		}

		errorMessage.setForWho(getCurrentLogedInUsername());
		
		notifyObservers(errorMessage);

		return null;
	}
	
	/**
	 * 
	 * @param threadId
	 * @return
	 */
	public boolean RemoveThread(String threadId, String forumId) {

    	ErrorMessage errorMessage;
    	
    	//	RemoveThreadMessage rfm = new RemoveThreadMessage(
    	//			getCurrentShownThread(), getCurrentShownForum());

    	RemoveThreadMessage rtm = new RemoveThreadMessage(threadId, forumId);
    	
		try {

			Message answer = getForumServerStub().setInformation(rtm);

			if (answer.getMessageType() != MessageType.ERROR) return true;

			errorMessage = (ErrorMessage)answer;
		}
		catch (RemoteException e) {
			
			String reason = "Connection Error - can't connect with the server";
			
			log(reason);
			errorMessage = new ErrorMessage(reason);
		}

		errorMessage.setForWho(getCurrentLogedInUsername());
		
		notifyObservers(errorMessage);
		
		return false;
    }
	
	/**
	 * 
	 * @param threadId
	 * @param postId
	 * @return
	 */
	public boolean RemovePost(String threadId, String postId) {

    	ErrorMessage errorMessage;
    	
    	//	RemovePostMessage rpm = new RemovePostMessage(getCurrentShownThread(), postId);

    	RemovePostMessage rpm = new RemovePostMessage(threadId, postId, this);
    	
		try {

			Message answer = getForumServerStub().setInformation(rpm);

			if (answer.getMessageType() != MessageType.ERROR) return true;

			errorMessage = (ErrorMessage)answer;
		}
		catch (RemoteException e) {
			
			String reason = "Connection Error - can't connect with the server";
			
			log(reason);
			errorMessage = new ErrorMessage(reason);
		}

		errorMessage.setForWho(getCurrentLogedInUsername());
		
		notifyObservers(errorMessage);
		
		return false;
    }
	
	/**
	 * Check if the word contains upper letter.
	 * @param word
	 * @return
	 */
	private Boolean containUpper(String word){

		for (char c : word.toCharArray()) {
		    if (Character.isUpperCase(c))
		    	return true;
		}
		return false;
	}
	
	/**
	 * Check if the mail address is valid.
	 * @param word 
	 * @return true if the word is valid, false otherwise.
	 */
	private Boolean validMail(String word){
		
		return (word.contains("@") && word.contains("."));
	}
	
	/**
	 *
	 * @param word
	 * @return
	 */
	private Boolean containDigit(String word){

		for (char c : word.toCharArray()) {
		    if (Character.isDigit(c))
		    	return true;
		}
		return false;
	}

	/**
	 *
	 * @param password
	 * @return
	 */
	private Boolean validPassword(String password){
		if ((password.length() < 6) || !(containUpper(password)) || !(containDigit(password)) ){
			return false;
		}

		return true;
	}
	
	@Override
	public void update(Object observable, Object arg){

	/*	if	(	(arg instanceof ThreadChangedNotification) &&
				(0 == _currentShownThread.compareTo(String.valueOf(
					((ThreadChangedNotification)arg).getThreadInfo().getThread_id()))) )
			notifyObservers(arg);
*/
		//else 
		if (arg instanceof Notification || arg instanceof ErrorMessage)
			notifyObservers(arg);
	}

	@Override
	public synchronized void addObserver(Observer o) {

		getObservers().add(o);
	}
	
	@Override
	public synchronized void deleteObserver(Observer o) {
		getObservers().remove(o);		
	}
	
	@Override
	public void notifyObservers(Object arg) {

		ArrayList<Observer> list = getObservers();
		
		for (Observer observer : list) {
			observer.update(null, arg);
		}
	}
	
	public void setForumServerStub(ForumServer forumServerStub) {
		this._forumServerStub = forumServerStub;
	}

	public ForumServer getForumServerStub() {
		return _forumServerStub;
	}

	public void setLogger(Logger logger) {
		this._logger = logger;
	}

	public Logger getLogger() {
		return _logger;
	}

	private void log(String msg){
		
		if (getLogger() != null)
			getLogger().info(msg);
	}

	public void setCurrentLogedInUsername(String currentLogedInUsername) {
		this._currentLogedInUsername = currentLogedInUsername;
	}

	public String getCurrentLogedInUsername() {
		return _currentLogedInUsername;
	}

	public void setCurrentShownForum(String _currentShownForum) {
		this._currentShownForum = _currentShownForum;
	}

	public String getCurrentShownForum() {
		return _currentShownForum;
	}

	public void setCurrentShownThread(String _currentShownThread) {
		this._currentShownThread = _currentShownThread;
	}

	public String getCurrentShownThread() {
		return _currentShownThread;
	}

	public void setObservers(ArrayList<Observer> _observers) {
		this._observers = _observers;
	}

	public ArrayList<Observer> getObservers() {
		return _observers;
	}
}
