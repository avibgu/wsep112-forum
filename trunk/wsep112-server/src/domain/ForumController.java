package domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Logger;

import server.network.WrappedObserver;

import common.forum.items.ForumInfo;
import common.forum.items.PostInfo;
import common.forum.items.ThreadInfo;
import common.forum.items.UserInfo;
import common.network.messages.ErrorMessage;
import common.network.messages.Message;
import common.network.messages.OKMessage;
import common.network.messages.SeeForumThreadsMessage;
import common.network.messages.SeeForumsListMessage;
import common.network.messages.SeeFriendsMessage;
import common.network.messages.SeeThreadPostsMessage;
import database.HibernateUtil;
import domain.User.Status;

/**
 *
 */
public class ForumController implements Serializable{

	private static final long serialVersionUID = -6364114409567569573L;
	
	private Vector<User> _registerdUsers;
	private Set<String> _loginUsers;
	private Logger _logger;
	private Vector<Forum> _forums;
	static int _availableForumId = 0;
	
	public ForumController(Logger logger){
		setRegisterdUsers(new Vector<User>());
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
		HibernateUtil.insertDB(newUser);
		return new OKMessage();
	}

	/**
	 *
	 * @param username
	 * @return true if the username exist, and false otherwise.
	 */
	public Boolean isExist(String username){
		User user = HibernateUtil.retrieveUser(username);
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
		HibernateUtil.updateDB(loginUser);
		return new OKMessage();
	}

	/**
	 *
	 * @param username
	 * @return the user object.
	 */
	public User getUser(String username){
		return HibernateUtil.retrieveUser(username);
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
		HibernateUtil.updateDB(logoutUser);
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
		
		HibernateUtil.updateDB(user);
		
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
		
		HibernateUtil.updateDB(user);
		
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
		//AVID: add this user as observer on this thread.. ????
    	//		(nobody can remove him from observation..)
    	
    	if (!isExist(ownerUsername))
			return new ErrorMessage("Username doesn't exists.");
    	
    	 // Find the owner
		User user = getUser(ownerUsername);
		
		// find the forum
		//return _forums.get(Integer.parseInt(forumId)).reaplyToThread(title, body, Integer.parseInt(threadId), user);
		return HibernateUtil.retrieveForum(Integer.valueOf(forumId)).reaplyToThread(title, body,  Integer.parseInt(threadId), user);
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
		return HibernateUtil.retrieveForum(Integer.parseInt(forumId)).add_thread(title, body, user);
		
    }

    /**
     *
     * @param sflm
     *
     * @return list of Forums inside the given message, or ErrorMessage (with reason) on failure
     */
    public Message getForumsList(SeeForumsListMessage sflm) {
    	
    		List<Forum> forums = HibernateUtil.retrieveForumsList();
    		
    		Vector<ForumInfo> listOfForums = new Vector<ForumInfo>();
    		
    		for (Forum forum : forums)
    			listOfForums.add(new ForumInfo(forum.getName(), forum.getForumId()));
    	    
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
		
		List<Thread> threads = HibernateUtil.
			retrieveThreadList(Integer.parseInt(forumID));
		
		Vector<ThreadInfo> tListOfThreads = new Vector<ThreadInfo>();
		
		for (Thread thread : threads)
			tListOfThreads.add(new ThreadInfo(
					thread.getThread_id(), thread.getTitle(), thread.get_forumId()));

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
		
		Vector<PostInfo> tListOfPosts = new Vector<PostInfo>();
		
		List<Post> tPost = HibernateUtil.retrievePostList(Integer.parseInt(threadID));
		
		for (Post post : tPost){
			
			UserInfo ui = new UserInfo(post.getOwner().getStatusAsString(),
					post.getOwner().getUserName());
			
			tListOfPosts.add( new PostInfo(post.get_post_id(), post.get_title(),
					post.get_body(), ui, post.getThread_id(), post.getDateTime()));
		}
		
		stpm.setListOfPosts(tListOfPosts);

		return stpm;
	}
	
	/**
	 * 
	 * @param username
	 * @param sfm
	 * 
	 * @return list of Friends inside the given message, or ErrorMessage (with reason) on failure
	 */
	public Message getFriendsList(String username, SeeFriendsMessage sfm) {
		
		Vector<UserInfo> tListOfFriends = new Vector<UserInfo>();
		
		// TODO need to get all the friends of 'username' from the DB..
		tListOfFriends.add(new UserInfo("OFFLINE", "TODO: get list of friends from the db.."));
		///////////////////////////////////////////////////////////////
		
		sfm.setListOfFriends(tListOfFriends);
		
		return sfm;
	}
	
	/**
	 * 
	 * @param sum
	 * 
	 * @return list of users inside the given message, or ErrorMessage (with reason) on failure
	 */
	public Message getUsersList(SeeFriendsMessage sum) {

		Vector<UserInfo> tListOfUsers = new Vector<UserInfo>();
		
		// TODO need to get all the users from the DB..
		tListOfUsers.add(new UserInfo("OFFLINE", "TODO: get list of users from the db.."));
		///////////////////////////////////////////////////////////////
		
		sum.setListOfFriends(tListOfUsers);
		
		return sum;
	}
	
	/**
	 * 
	 * @param threadId
	 * @param wo
	 * 
	 * @return OKMessage on success, or ErrorMessage (with reason) on failure
	 */
	public Message RemoveThread(String threadId) {
		
		// TODO: need to remove this thread from the DB..
		//		 should delete also its observers and posts..
		
    	// AVID: verify that there is no observers on this thread after deletion
		
		// TODO: return real answer..
		return new OKMessage();
	}
	
	/**
	 * 
	 * @param threadId
	 * @param postId
	 * @param wo
	 * 
	 * @return OKMessage on success, or ErrorMessage (with reason) on failure
	 */
	public Message RemovePost(String threadId, String postId, WrappedObserver wo) {
		
		// TODO: need to remove this post from the DB..
		
		// TODO: return real answer..
		return new OKMessage();
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
	
	public void setRegisterdUsers(Vector<User> _registerdUsers) {
		this._registerdUsers = _registerdUsers;
	}
	
	public Vector<User> getRegisterdUsers() {
		return _registerdUsers;
	}
}