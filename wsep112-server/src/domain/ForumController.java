package domain;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
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
import common.network.messages.MessageType;
import common.network.messages.OKMessage;
import common.network.messages.SearchUsersMessage;
import common.network.messages.SeeForumThreadsMessage;
import common.network.messages.SeeForumsListMessage;
import common.network.messages.SeeFriendsMessage;
import common.network.messages.SeeThreadPostsMessage;
import common.network.messages.SeeUsersMessage;
import common.notifications.FriendAddedPostNotification;
import common.notifications.PostAddedToYourThreadNotification;
import common.notifications.ThreadChangedNotification;
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

	private HashMap<String, WrappedObserver> _usersToObserversMap;
	
	public ForumController(Logger logger){
		setRegisterdUsers(new Vector<User>());
		_loginUsers = new HashSet<String>();
		_forums = new Vector<Forum>();
		setLogger(logger);
		Forum initialForum = new Forum("Initial forum",_availableForumId);
		_forums.add(initialForum);
		setUsersToObserversMap(new HashMap<String, WrappedObserver>());
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
			String password, String email, WrappedObserver wo) {

		// Check if the username is already in use.
		if (isExist(username.toLowerCase())){
			return new ErrorMessage("Username is already exists.");
		}

		// Check if when of the parameters is empty.
		if (firstName.equals("") || lastName.equals("") || username.equals("") ||
			email.equals("")){
			return new ErrorMessage("Missing parameters.");
		}

		// unrelate between this user and his observer
		getUsersToObserversMap().remove(username);
		
		// relate between this user and his observer
		getUsersToObserversMap().put(username, wo);
		
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
		
		//AVID_DONE: ???? notify user about things that happened when he was offline?.. no

		// Check is username exists.
		if (!isExist(username))
			return new ErrorMessage("Username doesn't exists.");

		User loginUser = getUser(username);

		// check if password is correct.
		if (!(loginUser.getPassword().equals(password)))
			return new ErrorMessage("Invalid password.");

		// unrelate between this user and his observer
		getUsersToObserversMap().remove(username);
		
		// relate between this user and his observer
		getUsersToObserversMap().put(username, wo);
		
		loginUser.setStatus(Status.ONLINE);
		HibernateUtil.updateDB(loginUser);
		return new OKMessage();
	}
	
	
	/**
	 *
	 * @param username
	 * @param password
	 *
	 * @return OKMessage on success, or ErrorMessage (with reason) on failure
	 */
	public Message changePass(String username, String password, WrappedObserver wo) {
			
		System.out.println("CHANGE PASSWORDDDDDDD");
		//AVID_DONE: ???? notify user about things that happened when he was offline?.. no

		// Check is username exists.
		if (!isExist(username))
			return new ErrorMessage("Username doesn't exists.");

		User loginUser = getUser(username);
		loginUser.setPassword(password);
		
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

		//AVID_DONE: ???? what to do about offline observers?.. nothing
		
		// Check is username exists.
		if (!isExist(username))
			return new ErrorMessage("Username doesn't exists.");

		User logoutUser = getUser(username);
		logoutUser.setStatus(Status.OFFLINE);

		// unrelate between this user and his observer?.. yes
		getUsersToObserversMap().remove(username);
		
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
		// Check is username exists.
		if (!isExist(username))
			return new ErrorMessage("Username doesn't exists.");
		// Check is friendUsername exists.
		if (!isExist(friendUsername))
			return new ErrorMessage("Friend Username doesn't exists.");

		User user = getUser(username);
		Message msg1 =  user.addFriend(friendUsername);
		
		User friend = getUser(friendUsername);
		Message msg2 =  friend.addFriendToNotify(username);
		
		HibernateUtil.updateDB(user);
		HibernateUtil.updateDB(friend);
		
		//return (msg1.getMessageType() == MessageType.OK) ? msg2 : msg1;
		return (msg1.getMessageType() == MessageType.OK)? msg1 : new ErrorMessage("");
    }

    /**
     * Remove friend from user's list
     * @param username
     * @param friendUsername
     *
     * @return OKMessage on success, or ErrorMessage (with reason) on failure
     */
    public Message RemoveFriend(String username, String friendUsername, WrappedObserver wo) {
    	
		// Check is username exists.
		if (!isExist(username))
			return new ErrorMessage("Username doesn't exists.");
		// Check is friendUsername exists.
		if (!isExist(friendUsername))
			return new ErrorMessage("Friend Username doesn't exists.");

		User user = getUser(username);
		Message msg1 = user.removeFriend(friendUsername);
		
		User friend = getUser(friendUsername);
		Message msg2 = friend.removeFriendToNotify(username);
		
		HibernateUtil.updateDB(user);
		HibernateUtil.updateDB(friend);
		
		return (msg1.getMessageType() == MessageType.OK)? msg1 : new ErrorMessage("");
    }

    /**
     *
     * @param title
     * @param body
     *
     * @return OKMessage on success, or ErrorMessage (with reason) on failure
     */
    public Message replyToThread(String forumId, String title, String body,
    		String threadId, String username, WrappedObserver wo) {
    	
    	if (!isExist(username))
			return new ErrorMessage("Username doesn't exists.");
    	
    	 // Find the owner
		User user = getUser(username);

		Thread thread = HibernateUtil.retrieveThread(Integer.parseInt(threadId));

		Message msg = HibernateUtil.retrieveForum(Integer.valueOf(forumId)).reaplyToThread(title, body,  Integer.parseInt(threadId), user);
		
		notifyAboutNewPost(user, thread);
		
		return msg;
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
    	
    	if (!isExist(ownerUsername))
			return new ErrorMessage("Username doesn't exists.");
    	
    	 // Find the owner
		User user = getUser(ownerUsername);
		
		// find the forum
		Forum tForum =  HibernateUtil.retrieveForum(Integer.parseInt(forumId));
		Thread new_thread = new Thread(title,tForum.getForumId(),user.get_Username());
		Message tMsg = tForum.add_thread(new_thread, title, body, user, wo); 
		
		//HibernateUtil.updateDB(tForum);
		
		notifyAboutNewPost(user, new_thread);
		
		return tMsg;
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
					thread.getThread_id(), thread.getTitle(), thread.get_forumId(),
					thread.get_owner(),thread.get_lastModifiedDate(),thread.get_lastModifiedUser(),
					thread.getNumOfPosts(),thread.getNumOfViews()));

		sftm.setListOfThreads(tListOfThreads);
		
		return sftm;
	}

	/**
	 * 
	 * @param forumID
	 * @param threadID
	 * @param username
	 * 
	 * @return list of Posts inside the given message, or ErrorMessage (with reason) on failure
	 */
	public Message getPostsList(String threadID, String username,
			SeeThreadPostsMessage stpm, WrappedObserver wo) {
		
		// remove this user from viewing on other threads
		removeThisUserFromObservingOnThreads(username);
		
		// add this user as watcher on this thread..
		Thread thread = HibernateUtil.retrieveThread(Integer.parseInt(threadID));
		thread.setNumOfViews(thread.getNumOfViews() + 1);
		boolean ans = thread.addWatchUser(username);
		HibernateUtil.updateDB(thread);

		Vector<PostInfo> tListOfPosts = new Vector<PostInfo>();
		
		List<Post> tPost = HibernateUtil.retrievePostList(Integer.parseInt(threadID));
		
		for (Post post : tPost){
			
			UserInfo ui = new UserInfo(post.get_Owner().getStatusAsString(),
					post.get_Owner().get_Username());
			
			tListOfPosts.add(new PostInfo(post.get_post_id(), post.get_title(),
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
		User tUser = HibernateUtil.retrieveUser(username);
		List<String> tUserFriends = tUser.get_friends();
		tUserFriends.removeAll(Arrays.asList(new Object[]{null}));
		for (int i=0; i< tUserFriends.size(); ++i){
			String tFriendUserName= tUserFriends.get(i);
			User tFriend = HibernateUtil.retrieveUser(tFriendUserName);
			tListOfFriends.add(new UserInfo(tFriend.getStatusAsString(),tUserFriends.get(i)));
		}
		
	
		sfm.setListOfFriends(tListOfFriends);
		
		return sfm;
	}
	
	/**
	 * 
	 * @param sum
	 * 
	 * @return list of users inside the given message, or ErrorMessage (with reason) on failure
	 */
	public Message getUsersList(SeeUsersMessage sum) {

		Vector<UserInfo> tListOfUsers = new Vector<UserInfo>();
		
		List<User> tUsers = HibernateUtil.retrieveUsers();
		for (int i=0; i< tUsers.size(); ++i){
			tListOfUsers.add(new UserInfo(tUsers.get(i).getStatusAsString(),tUsers.get(i).get_Username()));
		}
				
		sum.setListOfUsers(tListOfUsers);
		
		return sum;
	}
	/**
	 * 
	 * @param sum
	 * @param input - part of a string of username to search
	 * @return list of all users that contain String input
	 */
	public Message getSearchUsersList(SearchUsersMessage sum, String input) {

		Vector<UserInfo> tListOfUsers = new Vector<UserInfo>();
		
		List<User> tUsers = HibernateUtil.retrieveUsers();
		for (int i=0; i< tUsers.size(); ++i){
			if (tUsers.get(i).get_Username().contains(input)){
				tListOfUsers.add(new UserInfo(tUsers.get(i).getStatusAsString(),tUsers.get(i).get_Username()));	
			}
		}
				
		sum.setListOfUsers(tListOfUsers);
		
		return sum;
	}
	
	/**
	 * 
	 * @param threadId
	 * @param forumId
	 * 
	 * @return OKMessage on success, or ErrorMessage (with reason) on failure
	 */
	public Message RemoveThread(String threadId, String forumId) {
		
		// TODO: need to remove this thread from the DB..
		//		 should delete also its observers and posts..
		
		Forum tForum = HibernateUtil.retrieveForum(Integer.parseInt(forumId));
		tForum.deleteThread(Integer.parseInt(threadId));
		
		// Update the db
		//HibernateUtil.updateDB(tForum);
		
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
		Thread tThread = HibernateUtil.retrieveThread(Integer.parseInt(threadId));
		User tUser = HibernateUtil.retrievePostOwner(Integer.parseInt(postId));
		tThread.delete(Integer.parseInt(postId), tUser);
			
		// TODO: return real answer..
		return new OKMessage();
	}
	
	/**
	 * 
	 * @param forumID
	 * @param title
	 * @param body
	 * @param threadId
	 * @param postId
	 * @param username
	 * @param wo
	 * @return
	 */
	public Message editPost(String forumID, String title, String body,
			String threadId, String postId, String username, WrappedObserver wo) {
		Post tPost = HibernateUtil.retrievePost(Integer.parseInt(postId));
		tPost.edit_post(title, body);
		HibernateUtil.updateDB(tPost);
		Thread thread = HibernateUtil.retrieveThread(Integer.parseInt(threadId));
		thread.set_lastModifiedDate(new Date());
		thread.set_lastModifiedUser(username);
		HibernateUtil.updateDB(thread);
		return new OKMessage();
	}

    /**
     * 
     * @param user
     * @param thread
     */
    private void notifyAboutNewPost(User user, Thread thread) {

		WrappedObserver wo;
		
		ThreadInfo threadInfo = new ThreadInfo(
				thread.getThread_id(), thread.getTitle(), thread.get_forumId(),
				thread.get_owner(),thread.get_lastModifiedDate(),thread.get_lastModifiedUser(),
				thread.getNumOfPosts(),thread.getNumOfViews());
		
		// notify thread's owner..

		if (0 != user.get_Username().compareTo(thread.get_owner())){
			wo = getUsersToObserversMap().get(thread.get_owner());
			if (wo != null)
				wo.update(null, new PostAddedToYourThreadNotification(
						threadInfo, thread.get_owner(), user.get_Username()));
		}
		
		// notify thread's observers..
		
		List<String> viewers = thread.get_watchingUsers();
		
		for (String viewer : viewers) {
			
			wo = getUsersToObserversMap().get(viewer);
			if (wo != null)
				wo.update(null, new ThreadChangedNotification(threadInfo, viewer));
		}
		
    	// notify friends
		
		List<String> friends = user.get_friendsToNotify();

		for (String friend : friends) {
			
			if (friend.equals(thread.get_owner()))
				continue;
			
			wo = getUsersToObserversMap().get(friend);
			if (wo != null)
				wo.update(null, new FriendAddedPostNotification( threadInfo,
					new UserInfo(user.getStatusAsString(), user.get_Username()), friend));
		}
	}
	
    /**
     * 
     * @param username
     */
	private boolean removeThisUserFromObservingOnThreads(String username) {
		
		boolean change = false;
		List<Thread> threads = HibernateUtil.retrieveAllThreadsList();
	
		for (Thread thread : threads){
			
			if (thread.removeWatchUser(username)){
				change = true;
				HibernateUtil.updateDB(thread);
			}	
		}
		return change;
	}
	
	/**
	 * 
	 * @param msg
	 */
	public void log(String msg){
		getLogger().info(msg);
	}
	
	public void setLogger(Logger logger) {
		this._logger = logger;
	}

	public Logger getLogger() {
		return _logger;
	}
	
	public void setRegisterdUsers(Vector<User> _registerdUsers) {
		this._registerdUsers = _registerdUsers;
	}
	
	public Vector<User> getRegisterdUsers() {
		return _registerdUsers;
	}
	
	public void setUsersToObserversMap(HashMap<String, WrappedObserver> _usersToObserversMap) {
		this._usersToObserversMap = _usersToObserversMap;
	}
	
	public HashMap<String, WrappedObserver> getUsersToObserversMap() {
		return _usersToObserversMap;
	}
}