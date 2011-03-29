package domain;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Logger;

import common.network.messages.ErrorMessage;
import common.network.messages.Message;
import common.network.messages.OKMessage;
import common.network.messages.SeeForumThreadsMessage;
import common.network.messages.SeeForumsListMessage;
import common.network.messages.SeeThreadPostsMessage;
import domain.User.Status;

/**
 *
 */
public class ForumController {

	private Vector<User> _registerdUsers;
	private Set<String> _loginUsers;
	private Logger logger;
	private Vector<Forum> _forums;
	static int _availableForumId=0;
	
	public ForumController(Logger logger){
		_registerdUsers = new Vector<User>();
		_loginUsers = new HashSet<String>();
		_forums = new Vector<Forum>();
		setLogger(logger);
		Forum initialForum = new Forum("Initial forum",_availableForumId);
		_forums.add(initialForum);
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
		// Check if the username is already in use.
		if (isExist(username.toLowerCase())){
			return new ErrorMessage("username is already exists.");
		}

		// Check if when of the parameters is empty.
		if (firstName.equals("") || lastName.equals("") || username.equals("") ||
			email.equals("")){
			return new ErrorMessage("Missing parameters.");
		}

		// Add the user.
		User newUser = new User(firstName,lastName,username,password,email);
		_registerdUsers.add(newUser);
		_loginUsers.add(username);
		return new OKMessage();
	}

	/**
	 *
	 * @param username
	 * @return true if the username exist, and false otherwise.
	 */
	public Boolean isExist(String username){
		for (int i=0; i < _registerdUsers.size() ; ++i){
			if ((_registerdUsers.get(i).getUserName()).equals(username))
				return true;
		}

		return false;
	}

	
	/**
	 *
	 * @param username
	 * @param password
	 *
	 * @return OKMessage on success, or ErrorMessage (with reason) on failure
	 */
	public Message login(String username, String password) {

		// Check is username exists.
		if (!isExist(username))
			return new ErrorMessage("Username doesn't exists.");

		User loginUser = getUser(username);

		// check if password is correct.
		if (!(loginUser.getPassword().equals(password)))
			return new ErrorMessage("Invalid password.");

		loginUser.setStatus(Status.ONLINE);
		_loginUsers.add(username);

		return new OKMessage();
	}

	/**
	 *
	 * @param username
	 * @return the user object.
	 */
	public User getUser(String username){
		for (int i=0; i< _registerdUsers.size(); ++i){
			User user = _registerdUsers.get(i);
			if (user.getUserName().equals(username.toLowerCase()))
				return user;
		}
		return null;
	}

	/**
	 *
	 * @param username
	 *
	 * @return OKMessage on success, or ErrorMessage (with reason) on failure
	 */
	public Message logout(String username) {

		// Check is username exists.
		if (!isExist(username))
			return new ErrorMessage("Username doesn't exists.");

		User logoutUser = getUser(username);
		logoutUser.setStatus(Status.OFFLINE);

		_loginUsers.remove(username);

		return new OKMessage();
	}

    /**
     * Add friend to user's list
     * @param username
     * @param friendUsername
     *
     * @return OKMessage on success, or ErrorMessage (with reason) on failure
     */
    public Message AddFriend(String username, String friendUsername) {
		// Check is username exists.
		if (!isExist(username))
			return new ErrorMessage("Username doesn't exists.");
		// Check is friendUsername exists.
		if (!isExist(friendUsername))
			return new ErrorMessage("friend Username doesn't exists.");


		User user = getUser(username);
		return user.addFriend(friendUsername);
    }

    /**
     * Remove friend from user's list
     * @param username
     * @param friendUsername
     *
     * @return OKMessage on success, or ErrorMessage (with reason) on failure
     */
    public Message RemoveFriend(String username, String friendUsername) {
		// Check is username exists.
		if (!isExist(username))
			return new ErrorMessage("Username doesn't exists.");
		// Check is friendUsername exists.
		if (!isExist(friendUsername))
			return new ErrorMessage("friend Username doesn't exists.");

		User user = getUser(username);
		return user.removeFriend(friendUsername);
    }

    /**
     *
     * @param title
     * @param body
     * @param threadId
     *
     * @return OKMessage on success, or ErrorMessage (with reason) on failure
     */
    public Message replyToThread(String forumId, String title, String body, String threadId, String ownerUsername) {
    	if (!isExist(ownerUsername))
			return new ErrorMessage("Username doesn't exists.");
    	
    	 // Find the owner
		User user = getUser(ownerUsername);
		
		// find the forum
		return _forums.get(Integer.parseInt(forumId)).reaplyToThread(title, body, Integer.parseInt(threadId), user);
    }

    /**
     *
     * @param title
     * @param body
     *
     * @return OKMessage on success, or ErrorMessage (with reason) on failure
     */
    public Message addThread(String forumId,String title, String body, String ownerUsername) {
    	if (!isExist(ownerUsername))
			return new ErrorMessage("Username doesn't exists.");
    	
    	 // Find the owner
		User user = getUser(ownerUsername);
		
		// find the forum
		return _forums.get(Integer.parseInt(forumId)).add_thread(title, body, user);
		
    }

    /**
     *
     * @param sflm
     *
     * @return list of Forums inside the given message, or ErrorMessage (with reason) on failure
     */
    public Message getForumsList(SeeForumsListMessage sflm) {

            Vector<String> listOfForums = new Vector<String>();

            for (int i=0; i < _forums.size() ; ++i){
            	listOfForums.add(_forums.get(i).getName());
            }

            sflm.setListOfForums(listOfForums);

            return sflm;
    }

	/**
	 *
	 * @param sflm
	 *
	 * @return list of Threads inside the given message, or ErrorMessage (with reason) on failure
	 */
	public Message getThreadsList(String forumID, SeeForumThreadsMessage sftm) {

		Vector<String> tListOfThreads = new Vector<String>();
		Vector<Thread> tThread = _forums.get(Integer.parseInt(forumID)).getThreads(); 
		for (int i=0; i < tThread.size(); ++i){
			tListOfThreads.add(tThread.get(i).getTitle());
		}
		
		sftm.setListOfThreads(tListOfThreads);

		return sftm;
	}

	/**
	 *
	 * @param threadID
	 * @param stpm
	 *
	 * @return list of Posts inside the given message, or ErrorMessage (with reason) on failure
	 */
	public Message getPostsList(String forumID, String threadID, SeeThreadPostsMessage stpm) {
		Vector<String> tListOfPosts = new Vector<String>();
		Vector<Post> tPost = _forums.get(Integer.parseInt(forumID)).getThreads().get(Integer.parseInt(threadID)).getPosts();
		for (int i=0; i < tPost.size(); ++i){
			Post tcurrPost = tPost.get(i);
			tListOfPosts.add("Title:   " + tcurrPost.get_title() +"\n  Date:    " + tcurrPost.getDateTime() + "\n  Message: " + tcurrPost.get_body());
		}
		

		stpm.setListOfPosts(tListOfPosts);

		return stpm;
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
}