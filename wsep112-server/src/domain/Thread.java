package domain;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observer;
import java.util.Vector;

import server.network.WrappedObserver;

import common.network.messages.ErrorMessage;
import common.network.messages.Message;
import common.network.messages.OKMessage;
import common.notifications.PostAddedToYourThreadNotification;
import common.observation.Observable;
import database.HibernateUtil;

public class Thread implements Serializable{

	private static final long serialVersionUID = 3069041512726662410L;
	private int _forumId;
	private int _threadID;
	private String _title;
	private List<Post> _posts;
	private List<String> _watchingUsers;
	private String _owner;
	private Date _lastModifiedDate;
	private String _lastModifiedUser;
	private int numOfPosts;
	private int numOfViews;

	
	private WrappedObserver _ownerObserver;
	private List<WrappedObserver> _observers;
	
	public Thread(){
		this._posts=new ArrayList<Post>();
		this.set_observers(new ArrayList<WrappedObserver>());
		this._watchingUsers = new ArrayList<String>();	
	}
	public Thread (String title,int forumId,String username){
		this._watchingUsers = new ArrayList<String>();
		this._posts=new ArrayList<Post>();
		this._title=title;
		this.set_forumId(forumId);
		this.set_observers(new ArrayList<WrappedObserver>());
		this.set_owner(username);
		this.set_lastModifiedDate(new Date());
		this.set_lastModifiedUser(username);
		this.setNumOfPosts(0);
	}
	
	/**
	 * adding new post to topic
	 * @param title the title of the new post
	 * @param body the body of the new post
	 * @param owner the owner of the post
	 * @return  massage of success 
	 */
	//return the new post created un order the user to store it on his list
public Message reaply(String title, String body,User owner){
		
		Post new_post=new Post(getThread_id(), title, body,owner);
		owner.addPostToOwnerUser(new_post);//adding the post to owner
		getPosts().add(new_post);
		HibernateUtil.insertDB(new_post);
		HibernateUtil.updateDB(owner);
		
		Thread thread = HibernateUtil.retrieveThread(getThread_id());
		thread.set_lastModifiedDate(new Date());
		thread.set_lastModifiedUser(owner.get_Username());
		thread.setNumOfPosts(thread.getNumOfPosts() + 1);
		HibernateUtil.updateDB(thread);
		return new OKMessage();
	}
	
	/**
	 * delete post from thread
	 * side effect : deleting the post from the post list of the owner
	 * @param post_id the id of post to be deleted
	 * @param owner the owner of the post
	 */
	public Message delete (int post_id,User owner){
		List<Post> tAllPosts = getPosts();
		for(int i=0;i<tAllPosts.size();i++){
			Post tPost = tAllPosts.get(i); 
			if(tPost.get_post_id() == post_id){
				owner.removePost(getThread_id(),post_id);
				HibernateUtil.runQuery("delete from user_posts where post_id =" + String.valueOf(post_id));
				int row = HibernateUtil.deletePost(post_id);
				
				getPosts().remove(tPost);
				
				return new OKMessage();
			}
		}
		
		return new ErrorMessage("post doesn't exists.");
	}
	
	public boolean addWatchUser(String username){
		boolean ans=false;
		if (!get_watchingUsers().contains(username)){
			ans = true;
			get_watchingUsers().add(username);
		}
		return ans;
	}
	
	public boolean removeWatchUser(String username){
		return get_watchingUsers().remove(username);
	}
	
	
	//*************************** GETTERS AND SETTERS ***********************************
	public int getThread_id() {
		return _threadID;
	}

	public void setThread_id(int thread_id) {
		this._threadID = thread_id;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		this._title = title;
	}

	public List<Post> getPosts() {
		//return _posts;
		return HibernateUtil.retrievePostList(_threadID);
	}

	public void setPosts(List<Post> posts) {
		this._posts = posts;
	}

	public void set_forumId(int _forumID) {
		this._forumId = _forumID;
	}

	public int get_forumId() {
		return _forumId;
	}
	

	
	public void notifyOwner(PostAddedToYourThreadNotification notification) {
		if (get_ownerObserver() != null)
			get_ownerObserver().update(null, notification);
	}
	
	public synchronized void deleteObserver(WrappedObserver wo) {

		if (get_observers().contains(wo))
			get_observers().remove(wo);
	}
	
	
	
    public synchronized void addObserver(WrappedObserver wo) {
		if (!get_observers().contains(wo)) get_observers().add(wo);
    }
	
	public void setOwnerObserver(WrappedObserver _ownerObserver) {
		this._ownerObserver = _ownerObserver;
	}
	
	public WrappedObserver get_ownerObserver() {
		return _ownerObserver;
	}
	
	public void set_observers(List<WrappedObserver> _observers) {
		this._observers = _observers;
	}
	
	public List<WrappedObserver> get_observers() {
		return _observers;
	}
	public void set_watchingUsers(List<String> _watchingUsers) {
		this._watchingUsers = _watchingUsers;
	}
	public List<String> get_watchingUsers() {
		return _watchingUsers;
	}
	public void set_owner(String _owner) {
		this._owner = _owner;
	}
	public String get_owner() {
		return _owner;
	}
	public void set_lastModifiedDate(Date _lastModifiedDate) {
		this._lastModifiedDate = _lastModifiedDate;
	}
	public Date get_lastModifiedDate() {
		return _lastModifiedDate;
	}
	public void set_lastModifiedUser(String _lastModifiedUser) {
		this._lastModifiedUser = _lastModifiedUser;
	}
	public String get_lastModifiedUser() {
		return _lastModifiedUser;
	}
	
	public String getDateTime() {
	    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    return dateFormat.format(_lastModifiedDate);
	}
	public void setNumOfPosts(int numOfPosts) {
		this.numOfPosts = numOfPosts;
	}
	public int getNumOfPosts() {
		return numOfPosts;
	}
	public void setNumOfViews(int numOfViews) {
		this.numOfViews = numOfViews;
	}
	public int getNumOfViews() {
		return numOfViews;
	}

	
}
