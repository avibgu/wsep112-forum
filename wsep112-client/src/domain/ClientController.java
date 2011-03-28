/**
 *
 */
package domain;

import java.rmi.RemoteException;
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
import common.network.messages.RegMessage;
import common.network.messages.RemoveFriendMessage;
import common.network.messages.SeeForumThreadsMessage;
import common.network.messages.SeeForumsListMessage;
import common.network.messages.SeeThreadPostsMessage;

/**
 * @author Avi Digmi
 *
 */
public class ClientController {

	private ForumServer forumServerStub;
	private Logger logger;
	private String currentLogedInUsername;

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
		
		// Encrypt the password using SHA1 algorithm.
		String tEncrypted_Password = SHA1.hash(password);
		
		setCurrentLogedInUsername(username);

		RegMessage rm = new RegMessage(firstName, lastName, username, tEncrypted_Password, email);

		try {

			return getForumServerStub().setInformation(rm);
		}
		catch (RemoteException e) { e.printStackTrace(); }

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
		
		setCurrentLogedInUsername(username);

		// Encrypt the password using SHA1 algorithm.
		String tEncrypted_Password = SHA1.hash(password);

		LoginMessage lm = new LoginMessage(username, tEncrypted_Password);

		try {

			return getForumServerStub().setInformation(lm);
		}
		catch (RemoteException e) { e.printStackTrace(); }

		return new ErrorMessage("Connection Error - can't connect with the server");
	}

	/**
	 *
	 * @param word
	 * @return
	 */
	public Boolean containUpper(String word){

		for (char c : word.toCharArray()) {
		    if (Character.isUpperCase(c))
		    	return true;
		}
		return false;
	}

	/**
	 *
	 * @param word
	 * @return
	 */
	public Boolean containDigit(String word){

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
	public Boolean validPassword(String password){
		if ((password.length() < 6) || !(containUpper(password)) || !(containDigit(password)) ){
			return false;
		}

		return true;
	}

	
	/**
	 *
	 * @param username
	 *
	 * @return OKMessage on success, or ErrorMessage (with reason) on failure
	 */
	public Message logout() {
		
		setCurrentLogedInUsername("");

		LogoutMessage lm = new LogoutMessage(getCurrentLogedInUsername());

		try {

			return getForumServerStub().setInformation(lm);
		}
		catch (RemoteException e) { e.printStackTrace(); }

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
		catch (RemoteException e) { e.printStackTrace(); }

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
		catch (RemoteException e) { e.printStackTrace(); }

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
    public Message replyToThread(String title, String body, String threadId) {

    	AddPostMessage apm = new AddPostMessage(title, body, threadId, getCurrentLogedInUsername());

		try {

			return getForumServerStub().setInformation(apm);
		}
		catch (RemoteException e) { e.printStackTrace(); }

		return new ErrorMessage("Connection Error - can't connect with the server");
    }

    /**
     *
     * @param title
     * @param body
     *
     * @return OKMessage on success, or ErrorMessage (with reason) on failure
     */
    public Message addThread(String title, String body) {

    	AddThreadMessage atm = new AddThreadMessage(title, body);

		try {

			return getForumServerStub().setInformation(atm);
		}
		catch (RemoteException e) { e.printStackTrace(); }

		return new ErrorMessage("Connection Error - can't connect with the server");
    }

    /**
     *
     * @param sflm
     *
     * @return list of Forums inside the given message, or ErrorMessage (with reason) on failure
     */
    public Message getForumsList() {

    	SeeForumsListMessage sflm = new SeeForumsListMessage();

		try {

			return getForumServerStub().getInformation(sflm);
		}
		catch (RemoteException e) { e.printStackTrace(); }

		return new ErrorMessage("Connection Error - can't connect with the server");
    }

	/**
	 *
	 * @param sflm
	 *
	 * @return list of Threads inside the given message, or ErrorMessage (with reason) on failure
	 */
	public Message getThreadsList(String forumID) {

    	SeeForumThreadsMessage sftm = new SeeForumThreadsMessage(forumID);

		try {

			return getForumServerStub().getInformation(sftm);
		}
		catch (RemoteException e) { e.printStackTrace(); }

		return new ErrorMessage("Connection Error - can't connect with the server");
	}

	/**
	 *
	 * @param threadID
	 * @param stpm
	 *
	 * @return list of Posts inside the given message, or ErrorMessage (with reason) on failure
	 */
	public Message getPostsList(String threadID) {

    	SeeThreadPostsMessage stpm = new SeeThreadPostsMessage(threadID);

		try {

			return getForumServerStub().getInformation(stpm);
		}
		catch (RemoteException e) { e.printStackTrace(); }

		return new ErrorMessage("Connection Error - can't connect with the server");
	}

	public void setForumServerStub(ForumServer forumServerStub) {
		this.forumServerStub = forumServerStub;
	}

	public ForumServer getForumServerStub() {
		return forumServerStub;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public Logger getLogger() {
		return logger;
	}

	public void log(String msg){
		getLogger().info(msg);
	}

	public void setCurrentLogedInUsername(String currentLogedInUsername) {
		this.currentLogedInUsername = currentLogedInUsername;
	}

	public String getCurrentLogedInUsername() {
		return currentLogedInUsername;
	}
}
