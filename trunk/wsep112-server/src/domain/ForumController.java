package domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Logger;

import server.network.WrappedObserver;

import common.network.messages.ErrorMessage;
import common.network.messages.Message;
import common.network.messages.OKMessage;
import common.network.messages.SeeForumThreadsMessage;
import common.network.messages.SeeForumsListMessage;
import common.network.messages.SeeThreadPostsMessage;
import database.HibernateUtil;
import domain.User.Status;

/**
 *
 */
public class ForumController implements Serializable{

	private static final long serialVersionUID = -6364114409567569573L;
	
	private HibernateUtil _database;
	private Vector<User> _registerdUsers;
	private Set<String> _loginUsers;
	private Logger _logger;
	private Vector<Forum> _forums;
	static int _availableForumId = 0;
	
	public ForumController(Logger logger){
		_registerdUsers = new Vector<User>();
		_loginUsers = new HashSet<String>();
		_forums = new Vector<Forum>();
		setLogger(logger);
		Forum initialForum = new Forum("Initial forum",_availableForumId);
		_forums.add(initialForum);
		_database = new HibernateUtil();
		
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
			return new ErrorMessage("Username is already exists.");
		}

		// Check if when of the parameters is empty.
		if (firstName.equals("") || lastName.equals("") || username.equals("") ||
			email.equals("")){
			return new ErrorMessage("Missing parameters.");
		}

		// Add the user.
		User newUser = new User(firstName,lastName,username,password,email);
		newUser.setStatus(Status.ONLINE);
		_database.insertDB(newUser);
		return new OKMessage();
	}

	/**
	 *
	 * @param username
	 * @return true if the username exist, and false otherwise.
	 */
	public Boolean isExist(String username){
		User user = _database.retrieveUser(username);
		if (user == null)
			return false;
		return true;
	}

	
	/**
	 *
	 * @param username
	 * @param password
	 *
	 * @return OKMessage on success, or ErrorMessage (with reason) on failure
	 */
	public Message login(String username, String password, WrappedObserver wo) {
		
		//AVID: notify user about things that happened when he was offline?..

		// Check is username exists.
		if (!isExist(username))
			return new ErrorMessage("Username doesn't exists.");

		User loginUser = getUser(username);

		// check if password is correct.
		if (!(loginUser.getPassword().equals(password)))
			return new ErrorMessage("Invalid password.");

		loginUser.setStatus(Status.ONLINE);
		//_loginUsers.add(username);
		_database.updateDB(loginUser);
		return new OKMessage();
	}

	/**
	 *
	 * @param username
	 * @return the user object.
	 */
	public User getUser(String username){
		return _database.retrieveUser(username);
	}

	/**
	 *
	 * @param username
	 *
	 * @return OKMessage on success, or ErrorMessage (with reason) on failure
	 */
	public Message logout(String username, WrappedObserver wo) {

		//AVID: what to do about offline observers?..
		
		// Check is username exists.
		if (!isExist(username))
			return new ErrorMessage("Username doesn't exists.");

		User logoutUser = getUser(username);
		logoutUser.setStatus(Status.OFFLINE);

		_loginUsers.remove(username);
		_database.updateDB(logoutUser);
		return new OKMessage();
	}

    /**
     * Add friend to user's list
     * @param username
     * @param friendUsername
     *
     * @return OKMessage on success, or ErrorMessage (with reason) on failure
     */
    public Message AddFriend(String username, String friendUsername, WrappedObserver wo) {
    	
    	//AVID: add this user to friend's observers..
    	
		// Check is username exists.
		if (!isExist(username))
			return new ErrorMessage("Username doesn't exists.");
		// Check is friendUsername exists.
		if (!isExist(friendUsername))
			return new ErrorMessage("Friend Username doesn't exists.");

		User user = getUser(username);
		Message msg =  user.addFriend(friendUsername);
		
		_database.updateDB(user);
		
		return msg;
    }

    /**
     * Remove friend from user's list
     * @param username
     * @param friendUsername
     *
     * @return OKMessage on success, or ErrorMessage (with reason) on failure
     */
    public Message RemoveFriend(String username, String friendUsername, WrappedObserver wo) {
    	
    	//AVID: remove this user from friend's observers..
    	
		// Check is username exists.
		if (!isExist(username))
			return new ErrorMessage("Username doesn't exists.");
		// Check is friendUsername exists.
		if (!isExist(friendUsername))
			return new ErrorMessage("Friend Username doesn't exists.");

		User user = getUser(username);
		Message msg = user.removeFriend(friendUsername);
		
		_database.updateDB(user);
		
		return msg;
    }

    /**
     *
     * @param title
     * @param body
     *
     * @return OKMessage on success, or ErrorMessage (with reason) on failure
     */
    public Message replyToThread(String forumId, String title, String body,
    		String threadId, String ownerUsername, WrappedObserver wo) {
    	
    	//AVID: notify to friends and thread's observers..
		//AVID: add this user as observer on this thread..
    	//		(nobody can remove him from observation..)
    	
    	if (!isExist(ownerUsername))
			return new ErrorMessage("Username doesn't exists.");
    	
    	 // Find the owner
		User user = getUser(ownerUsername);
		
		// find the forum
		//return _forums.get(Integer.parseInt(forumId)).reaplyToThread(title, body, Integer.parseInt(threadId), user);
		return _database.retrieveForum(Integer.valueOf(forumId)).reaplyToThread(title, body,  Integer.parseInt(threadId), user);
    }

    /**
     *
     * @param title
     * @param body
     *
     * @return OKMessage on success, or ErrorMessage (with reason) on failure
     */
    public Message addThread(String forumId,String title, String body,
    		String ownerUsername, WrappedObserver wo) {
    	
		//AVID: add this user as observer on this thread..
    	//		(nobody can remove him from observation..)
    	
    	if (!isExist(ownerUsername))
			return new ErrorMessage("Username doesn't exists.");
    	
    	 // Find the owner
		User user = getUser(ownerUsername);
		
		// find the forum
		return _database.retrieveForum(Integer.parseInt(forumId)).add_thread(title, body, user);
		
    }

    /**
     *
     * @param sflm
     *
     * @return list of Forums inside the given message, or ErrorMessage (with reason) on failure
     */
    public Message getForumsList(SeeForumsListMessage sflm) {
    		List<Forum> forums = _database.retrieveForumsList();
    		Vector<String> listOfForums = new Vector<String>();
    	    for (int i=0; i < forums.size() ; ++i){
            	listOfForums.add(forums.get(i).getName());
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
		List<Thread> threads = _database.retrieveThreadList(Integer.parseInt(forumID));
		Vector<String> tListOfThreads = new Vector<String>();
		for (int i=0; i < threads.size(); ++i){
			tListOfThreads.add(threads.get(i).getTitle());
			
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
	public Message getPostsList(String forumID, String threadID,
			SeeThreadPostsMessage stpm, WrappedObserver wo) {
		
		//AVID: add this user as observer on this thread..
		//AVID: remove this user from observation on other threads
		//		(just in case he is not their owner or replyer)
		
		Vector<String> tListOfPosts = new Vector<String>();
		List<Post> tPost = _database.retrievePostList(Integer.parseInt(threadID));
		for (int i=0; i < tPost.size(); ++i){
			Post tcurrPost = tPost.get(i);
			tListOfPosts.add("Title:   " + tcurrPost.get_title() +"\n  Date:    " + tcurrPost.getDateTime() + "\n  Message: " + tcurrPost.get_body());
		}
		
		stpm.setListOfPosts(tListOfPosts);

		return stpm;
	}
	/**
	 * 
	 * @param logger
	 */
	public void setLogger(Logger logger) {
		this._logger = logger;
	}
	/**
	 * 
	 * @return
	 */
	public Logger getLogger() {
		return _logger;
	}
	/**
	 * 
	 * @param msg
	 */
	public void log(String msg){
		getLogger().info(msg);
	}
}