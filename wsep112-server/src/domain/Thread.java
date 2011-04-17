package domain;

import java.io.Serializable;
import java.util.ArrayList;
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

public class Thread implements Observable, Serializable{

	private static final long serialVersionUID = 3069041512726662410L;
	private int _forumId;
	private int _threadID;
	private String _title;
	private List<Post> _posts;
	static int _available_post_id = 0;
	
	private WrappedObserver _ownerObserver;
	private List<WrappedObserver> _observers;
	
	public Thread(){
		this._posts=new ArrayList<Post>();
		this.setObservers(new ArrayList<WrappedObserver>());
	}
	public Thread (String title,int forumId){
		this._posts=new ArrayList<Post>();
		this._title=title;
		this.setForumId(forumId);
		this.setObservers(new ArrayList<WrappedObserver>());
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
		
		return new OKMessage();
	}
	
	/**
	 * delete post from thread
	 * side effect : deleting the post from the post list of the owner
	 * @param post_id the id of post to be deleted
	 * @param owner the owner of the post
	 */
	public Message delete (int post_id,User owner){
		System.out.println("size1 = " + getPosts().size());
		for(int i=1;i<getPosts().size();i++){
			if(getPosts().get(i).get_post_id() == post_id){
				owner.removePost(getThread_id(),post_id);
				//HibernateUtil.updateDB(owner);
				HibernateUtil.runQuery("delete from user_posts where post_id =" + post_id);
				getPosts().remove(i);
				System.out.println("size2 = " + getPosts().size());
				return new OKMessage();
			}
		}
		return new ErrorMessage("post doesn't exists.");
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
		return _posts;
		//return HibernateUtil.retrievePostList(_threadID);
	}

	public void setPosts(Vector<Post> posts) {
		this._posts = posts;
	}

	public void setForumId(int _forumID) {
		this._forumId = _forumID;
	}

	public int getForumId() {
		return _forumId;
	}
	
	@Override
	public void notifyObservers(Object arg){
		
		List<WrappedObserver> observers = getObservers();
		
		for (WrappedObserver wo : observers)
			wo.update(null, arg);
	}
	
	public void notifyOwner(PostAddedToYourThreadNotification notification) {
		getOwnerObserver().update(null, notification);
	}
	
	@Override
	public synchronized void deleteObserver(Observer o){
		
		if (o == null) throw new NullPointerException();
		
		if (o instanceof WrappedObserver)
			deleteObserver((WrappedObserver)o);
    }
    
	public synchronized void deleteObserver(WrappedObserver wo) {

		if (getObservers().contains(wo))
			getObservers().remove(wo);
	}
	
	@Override
	public synchronized void addObserver(Observer o) {
		
		if (o == null) throw new NullPointerException();
		
		if (o instanceof WrappedObserver)
			addObserver((WrappedObserver)o);
	}
	
    public synchronized void addObserver(WrappedObserver wo) {
		if (!getObservers().contains(wo)) getObservers().add(wo);
    }
	
	public void setOwnerObserver(WrappedObserver _ownerObserver) {
		this._ownerObserver = _ownerObserver;
	}
	
	public WrappedObserver getOwnerObserver() {
		return _ownerObserver;
	}
	
	public void setObservers(List<WrappedObserver> _observers) {
		this._observers = _observers;
	}
	
	public List<WrappedObserver> getObservers() {
		return _observers;
	}
}
