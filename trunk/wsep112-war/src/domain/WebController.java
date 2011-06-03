package domain;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

import main.MainClient;

import common.forum.items.ForumInfo;
import common.forum.items.PostInfo;
import common.forum.items.ThreadInfo;
import common.forum.items.UserInfo;
import common.notifications.FriendAddedPostNotification;
import common.notifications.PostAddedToYourThreadNotification;
import common.notifications.ThreadChangedNotification;

/**
 * Web Controller Singleton..
 */
public class WebController implements Observer{

	private HashMap<String, ClientController> _usersControllersMap;
	private HashMap<String, LinkedList<String>> _usersNotificationsMap;
	private ReentrantReadWriteLock _rwControllerLock;
	private ReadLock _rdControllerLock;
	private WriteLock _wrControllerLock;
	private ReentrantReadWriteLock _rwNotificationLock;
	private ReadLock _rdNotificationLock;
	private WriteLock _wrNotificationLock;
	
	// Private constructor prevents instantiation from other classes
	private WebController() {
		
		setUsersControllersMap(new HashMap<String, ClientController>());
		setRwControllerLock(new ReentrantReadWriteLock(true));
		setRdControllerLock(getRwControllerLock().readLock());
		setWrControllerLock(getRwControllerLock().writeLock());
		
		setUsersNotificationsMap(new HashMap<String, LinkedList<String>>());
		setRwNotificationLock(new ReentrantReadWriteLock(true));
		setRdNotificationLock(getRwNotificationLock().readLock());
		setWrNotificationLock(getRwNotificationLock().writeLock());
	}
	
	/**
	* SingletonHolder is loaded on the first execution of Singleton.getInstance()
	* or the first access to SingletonHolder.INSTANCE, not before.
	*/
	private static class WebControllerHolder {
		public static final WebController INSTANCE = new WebController();
	}

	public static WebController getInstance() {
		return WebControllerHolder.INSTANCE;
	}
	
//	From Here:	delegations to a ClientController

	public boolean login(String username, String password) {
		
		ClientController cc = getClientController(username);
		
		return cc.login(username, password);
	}
	
	public boolean register(String firstName, String lastName, String username,
			String password, String email) {
		
		ClientController cc = getClientController(username);
		
		return cc.register(firstName, lastName, username, password, email);
	}
	
	public void logout(String username) {

		ClientController cc = getClientController(username);
		
		cc.logout();
	}

	public Vector<UserInfo> getFriendList(String username) {

		ClientController cc = getClientController(username);
		
		return cc.getFriendList();
	}
	
	public Vector<UserInfo> getUsersList(String username){
		ClientController cc = getClientController(username);
		
		return cc.getUsersList();
	}

	
	public Vector<ForumInfo> getForumList(String username){
		
		ClientController cc = getClientController(username);
		
		return cc.getForumsList();
	}
	
	public Vector<ThreadInfo> getThreadList(String username, String forum_id){

		ClientController cc = getClientController(username);
		
		return cc.getThreadsList(forum_id);
	}
	
	public Vector<PostInfo> getPostList(String username,String thread_id){
		
		ClientController cc = getClientController(username);
		return cc.getPostsList(thread_id);
	}

	public boolean AddFriend(String username, String addFriendName) {
		System.out.println("username = " + username + " friend= " + addFriendName);
		ClientController cc = getClientController(username);
		
		return cc.AddFriend(addFriendName);
	}

	public boolean RemoveFriend(String username, String removeFriendName) {
		
		ClientController cc = getClientController(username);
		
		return cc.RemoveFriend(removeFriendName);
	}
	
	public void addThread(String username,String forumID,String title, String body) {
		ClientController cc = getClientController(username);
		cc.addThread(forumID, title, body);
	}
	
	public void deleteThread(String username,String forumID,String threadId) {
		ClientController cc = getClientController(username);
		cc.RemoveThread(threadId, forumID);
	}
	
	public void addPost(String username, String forumID, String title, String body, String threadId){
		ClientController cc = getClientController(username);
		cc.replyToThread(forumID, title, body, threadId);
	}
	
	public void editPost(String username, String forumID, String title, String body, String threadId,String postId){
		ClientController cc = getClientController(username);
		cc.editPost(forumID, title, body, threadId, postId);
	}
	
	public void deletePost(String username,String threadId,String postId){
		ClientController cc = getClientController(username);
		cc.RemovePost(threadId, postId);
	}
	
		
	public PostInfo getPost(String username,String threadId,String postId){
		ClientController cc = getClientController(username);
		Vector<PostInfo> posts = cc.getPostsList(threadId);
		
		for(PostInfo post: posts){
			if (post.get_post_id() == Integer.parseInt(postId))
				return post;
				
		}
		
		return null;
	}
	
	public PostInfo getPostByLocation(String username,String threadId,String postId){
		ClientController cc = getClientController(username);
		Vector<PostInfo> posts = cc.getPostsList(threadId);
		
		return posts.get(Integer.parseInt(postId));
	}

	public ClientController getClientController(String username) {

		getRdControllerLock().lock();
		
		ClientController cc = getUsersControllersMap().get(username);
		
		getRdControllerLock().unlock();
		
		if (cc == null){
			
			while (true){
				
				try {
					
					cc = MainClient.connectToRmiServer("127.0.0.1", null);
					
					if (cc != null){
						
						getWrControllerLock().lock();
						
						getUsersControllersMap().put(username, cc);
						
						cc.addObserver(this);
						
						getWrControllerLock().unlock();
						
						getWrNotificationLock().lock();
						
						getUsersNotificationsMap().put(username, new LinkedList<String>());
						
						getWrNotificationLock().unlock();
						
						break;
					}
				}
				catch (RemoteException e) {}
			}
		}

		return cc;
	}
		
	@Override
	public void update(Observable observer, Object arg) {
		
		if (arg instanceof ThreadChangedNotification)
			nofity((ThreadChangedNotification)arg);
		
		else if (arg instanceof FriendAddedPostNotification)
			nofity((FriendAddedPostNotification)arg);
		
		else if (arg instanceof PostAddedToYourThreadNotification)
			nofity((PostAddedToYourThreadNotification)arg);
    }
	
	private void nofity(ThreadChangedNotification tcn) {

		//	TODO: should do nothing.. the page will refresh automatically..
		//	AVID: CHANGE IT
		
		//	ThreadInfo tInfo = tcn.getThreadInfo();

		//	String msg =	"Thread \"" + tInfo.getTitle() + "\" has been changed";
		String msg = "REFRESH";
		
		getWrNotificationLock().lock();
		
		getUsersNotificationsMap().get(tcn.getForWho()).add(msg);
		
		getWrNotificationLock().unlock();
	}
	
	private void nofity(FriendAddedPostNotification fapn) {

		ThreadInfo tInfo = fapn.getThreadInfo();
		UserInfo uInfo = fapn.getUserInfo();
		
		String msg =	"Your friend " + uInfo.getUserName() +
						" added post to Thread \"" + tInfo.getTitle() + "\"";
		
		getWrNotificationLock().lock();
		
		getUsersNotificationsMap().get(fapn.getForWho()).add(msg);
		
		getWrNotificationLock().unlock();
	}
	
	private void nofity(PostAddedToYourThreadNotification patytn) {

		ThreadInfo tInfo = patytn.getThreadInfo();
					
		String msg =	"Your Thread \"" + tInfo.getTitle() + "\" has been changed";

		getWrNotificationLock().lock();
		
		getUsersNotificationsMap().get(patytn.getForWho()).add(msg);
		
		getWrNotificationLock().unlock();
	}
	
	public String getNotificationFromQueue(String username){
		
		getWrNotificationLock().lock();
		
		LinkedList<String> list = getUsersNotificationsMap().get(username);
		
		String msg = null;
		
		if (null != list && !list.isEmpty())
			msg = list.remove();
		
		getWrNotificationLock().unlock();
		
		return msg;
	}

	public void setUsersControllersMap(HashMap<String, ClientController> _usersControllersMap) {
		this._usersControllersMap = _usersControllersMap;
	}

	public HashMap<String, ClientController> getUsersControllersMap() {
		return _usersControllersMap;
	}
	
	private void setRwControllerLock(ReentrantReadWriteLock rwLock) {
		this._rwControllerLock = rwLock;
	}

	public void setUsersNotificationsMap(HashMap<String, LinkedList<String>> _usersNotificationsMap) {
		this._usersNotificationsMap = _usersNotificationsMap;
	}

	public HashMap<String, LinkedList<String>> getUsersNotificationsMap() {
		return _usersNotificationsMap;
	}

	private ReentrantReadWriteLock getRwControllerLock() {
		return _rwControllerLock;
	}

	private void setRdControllerLock(ReadLock rdLock) {
		this._rdControllerLock = rdLock;
	}

	private ReadLock getRdControllerLock() {
		return _rdControllerLock;
	}

	private void setWrControllerLock(WriteLock wrLock) {
		this._wrControllerLock = wrLock;
	}

	private WriteLock getWrControllerLock() {
		return _wrControllerLock;
	}

	public void setRwNotificationLock(ReentrantReadWriteLock _rwNotificationLock) {
		this._rwNotificationLock = _rwNotificationLock;
	}

	public ReentrantReadWriteLock getRwNotificationLock() {
		return _rwNotificationLock;
	}

	public void setRdNotificationLock(ReadLock _rdNotificationLock) {
		this._rdNotificationLock = _rdNotificationLock;
	}

	public ReadLock getRdNotificationLock() {
		return _rdNotificationLock;
	}

	public void setWrNotificationLock(WriteLock _wrNotificationLock) {
		this._wrNotificationLock = _wrNotificationLock;
	}

	public WriteLock getWrNotificationLock() {
		return _wrNotificationLock;
	}
}
