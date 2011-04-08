/**
 *
 */
package domain;

import java.rmi.RemoteException;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;
import java.util.logging.Logger;

import common.encryption.SHA1;
import common.network.ForumServer;
import common.network.messages.AddFriendMessage;
import common.network.messages.AddPostMessage;
import common.network.messages.AddThreadMessage;
import common.network.messages.ErrorMessage;
import common.network.messages.LoginMessage;
import common.network.messages.LogoutMessage;
import common.network.messages.Message;
import common.network.messages.MessageType;
import common.network.messages.RegMessage;
import common.network.messages.RemoveFriendMessage;
import common.network.messages.SeeForumThreadsMessage;
import common.network.messages.SeeForumsListMessage;
import common.network.messages.SeeThreadPostsMessage;

/**
 * @author Avi Digmi
 *
 */
public class ClientController extends Observable implements Observer{

	private ForumServer _forumServerStub;
	private Logger _logger;
	private String _currentLogedInUsername;

	public ClientController(ForumServer forumServerStub, Logger logger) {

		setForumServerStub(forumServerStub);
		setLogger(logger);
		setCurrentLogedInUsername("");
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
	public Message register(String firstName, String lastName, String username,
			String password, String email) {
		
		// Check if the password is strong enough.
		if (!validPassword(password)){
			return new ErrorMessage("Password is too weak.");
		}
		
		// Check if the mail is valid.
		if (!validMail(email)){
			return new ErrorMessage("Mail address is invalid.");
		}
		
		
		// Encrypt the password using SHA1 algorithm.
		String tEncrypted_Password = SHA1.hash(password);

		RegMessage rm = new RegMessage(firstName, lastName, username, tEncrypted_Password, email);

		try {

			Message answer = getForumServerStub().setInformation(rm);
			
			if (answer.getMessageType() == MessageType.OK) setCurrentLogedInUsername(username);
			
			return answer;
		}
		catch (RemoteException e) { log("Connection Error - can't connect with the server"); }

		return new ErrorMessage("Connection Error - can't connect with the server");
	}

	/**
	 *
	 * @param username
	 * @param password
	 *
	 * @return OKMessage on success, or ErrorMessage (with reason) on failure
	 */
	public Message login(String username, String password) {

		// Encrypt the password using SHA1 algorithm.
		String tEncrypted_Password = SHA1.hash(password);

		LoginMessage lm = new LoginMessage(username, tEncrypted_Password);

		try {

			Message answer = getForumServerStub().setInformation(lm);
			
			if (answer.getMessageType() == MessageType.OK) setCurrentLogedInUsername(username);
			
			return answer;
		}
		catch (RemoteException e) { log("Connection Error - can't connect with the server"); }

		return new ErrorMessage("Connection Error - can't connect with the server");
	}

	/**
	 *
	 * @param username
	 *
	 * @return OKMessage on success, or ErrorMessage (with reason) on failure
	 */
	public Message logout() {
		

		LogoutMessage lm = new LogoutMessage(getCurrentLogedInUsername());

		setCurrentLogedInUsername("");
		
		try {
			
			Message answer = getForumServerStub().setInformation(lm);
			
			if (answer.getMessageType() == MessageType.OK) setCurrentLogedInUsername("");
			
			return answer;
		}
		catch (RemoteException e) { log("Connection Error - can't connect with the server"); }

		return new ErrorMessage("Connection Error - can't connect with the server");
	}

    /**
     *
     * @param username
     * @param friendUsername
     *
     * @return OKMessage on success, or ErrorMessage (with reason) on failure
     */
    public Message AddFriend(String friendUsername) {

    	AddFriendMessage afm = new AddFriendMessage(getCurrentLogedInUsername(), friendUsername);

		try {

			return getForumServerStub().setInformation(afm);
		}
		catch (RemoteException e) { log("Connection Error - can't connect with the server"); }

		return new ErrorMessage("Connection Error - can't connect with the server");
    }

    /**
     *
     * @param username
     * @param friendUsername
     *
     * @return OKMessage on success, or ErrorMessage (with reason) on failure
     */
    public Message RemoveFriend(String friendUsername) {

    	RemoveFriendMessage rfm = new RemoveFriendMessage(getCurrentLogedInUsername(), friendUsername);

		try {

			return getForumServerStub().setInformation(rfm);
		}
		catch (RemoteException e) { log("Connection Error - can't connect with the server"); }

		return new ErrorMessage("Connection Error - can't connect with the server");
    }

    /**
     *
     * @param title
     * @param body
     * @param threadId
     *
     * @return OKMessage on success, or ErrorMessage (with reason) on failure
     */
    public Message replyToThread(String forumID, String title, String body, String threadId) {

    	AddPostMessage apm = new AddPostMessage(forumID,title, body, threadId, getCurrentLogedInUsername());

		try {

			return getForumServerStub().setInformation(apm);
		}
		catch (RemoteException e) { log("Connection Error - can't connect with the server"); }

		return new ErrorMessage("Connection Error - can't connect with the server");
    }

    /**
     *
     * @param title
     * @param body
     *
     * @return OKMessage on success, or ErrorMessage (with reason) on failure
     */
    public Message addThread(String forumID,String title, String body) {

    	AddThreadMessage atm = new AddThreadMessage(forumID,title, body, getCurrentLogedInUsername());

		try {

			return getForumServerStub().setInformation(atm);
		}
		catch (RemoteException e) { log("Connection Error - can't connect with the server"); }

		return new ErrorMessage("Connection Error - can't connect with the server");
    }

    /**
     *
     * @param sflm
     *
     * @return list of Forums, or null on failure
     */
    public Vector<String> getForumsList() {

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
		setChanged();
		notifyObservers(errorMessage);
		clearChanged();

		return null;
    }

	/**
	 *
	 * @param sflm
	 *
	 * @return list of Threads inside the given message, or ErrorMessage (with reason) on failure
	 */
	public Vector<String> getThreadsList(String forumID) {

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

		setChanged();
		notifyObservers(errorMessage);
		clearChanged();

		return null;
	}

	/**
	 *
	 * @param threadID
	 * @param stpm
	 *
	 * @return list of Posts inside the given message, or ErrorMessage (with reason) on failure
	 */
	public Vector<String> getPostsList(String forumID,String threadID) {

		ErrorMessage errorMessage;

    	SeeThreadPostsMessage stpm = new SeeThreadPostsMessage(forumID,threadID);

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

		setChanged();
		notifyObservers(errorMessage);
		clearChanged();

		return null;
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
		getLogger().info(msg);
	}

	public void setCurrentLogedInUsername(String currentLogedInUsername) {
		this._currentLogedInUsername = currentLogedInUsername;
	}

	public String getCurrentLogedInUsername() {
		return _currentLogedInUsername;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
