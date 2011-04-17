package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.hibernate.Session;
import org.hibernate.Transaction;

import server.network.WrappedObserver;

import common.network.messages.ErrorMessage;
import common.network.messages.Message;

import database.HibernateUtil;


public class Forum implements Serializable{

	private static final long serialVersionUID = 8391028464314667274L;

	private String _name;
	private int _forumId;
	private List<Thread> _threads;
	private List<Forum> _subForums;
	static int _threadAvailableID = 0;
	
	public Forum(){
		
	}
	
	public Forum (String name,int forumId){
		this._name = name;
		this._threads = new ArrayList<Thread>();
		this._subForums = new ArrayList<Forum>();
		setForumId(forumId);
	}
	
	/**
	 * 
	 * @param title title of the reply
	 * @param body body of the replay
	 * @param threadId id of the thread
	 * @param owner the owner of the post
	 * @return
	 */
	public Message reaplyToThread(String title, String body, int threadId,User owner) {
		
		List<Thread> tThreadList = getThreads();
		
		for(int i=0 ; i < tThreadList.size() ; i++){
			
			Thread thread=tThreadList.get(i);
			
			if(thread.getThread_id()==threadId)
				return thread.reaply(title, body, owner);
		}
		
		return new ErrorMessage("theard doesn't exists.");//meaning that we didn't found the thread 
	}
	
	/**
	 * deletes the given thread from the list of forums threads 
	 * @param threadId the id of the thread
	 */
	//removing the given specific thread from the forum
	public void deleteThread(int threadId){
		//List<Thread> tThreadList = getThreads();
		//for(int i=0;i<tThreadList.size();i++){
		//	Thread tThread = tThreadList.get(i); 
		//	if(tThread.getThread_id()==threadId){
				 HibernateUtil.deleteThread(threadId);
		//		 break;
		//	}
		//}
		
	}
	
	/**
	 * add new thread to the forum , and filling the thread with the given massage
	 * @param title title of the new thread and the post creating in it
	 * @param body the body of the post
	 * @param owner the owner of the post
	 * @return
	 */
	
	public Message add_thread (Thread new_thread, String title, String body, User owner, WrappedObserver wo){

		System.out.println("num of threads before  = " + getThreads().size());

		getThreads().add(new_thread);
		Integer ans = (Integer) HibernateUtil.insertDB(new_thread);
		
		try {
			
			Session session = HibernateUtil.getSession();
			Transaction t = session.beginTransaction();	
		}
		catch(Exception e){ e.printStackTrace(); }
		
		new_thread.setThread_id(ans.intValue());
		HibernateUtil.updateDB(new_thread);
		return new_thread.reaply(title, body, owner);
	}
	
	/**
	 * returns list of posts of given thread id
	 * @param threadID
	 * @return list of posts related to the given thread
	 */
	public  List<Post> getPostsList (int threadID){
		return HibernateUtil.retrievePostList(threadID);
	}
	
	//***************************************** GETTERS AND SETTERS ************************************8
	/**
	 * 
	 * @return sub forums list
	 */
	public List<Forum> getSubForums() {
		return _subForums;
	}

	/**
	 * 
	 * @param sub_forums sub forums list
	 */
	public void setSubForums(Vector<Forum> sub_forums) {
		this._subForums = sub_forums;
	}
	
	/**
	 * 
	 * @return the name of the forum
	 */
	public String getName() {
		return _name;
	}

	/**
	 * 
	 * @param name name of the forum
	 */
	public void setName(String name) {
		this._name = name;
	}
	
	/**
	 * 
	 * @return list of forum's threads
	 */
	public List<Thread> getThreads() {
		//return _threads;
		return HibernateUtil.retrieveThreadList(getForumId());
	}

	/**
	 * 
	 * @param threads list of forum's threads
	 */
	public void setThreads(Vector<Thread> threads) {
		this._threads = threads;
	}

	/**
	 * 
	 * @param _forumId
	 */
	public void setForumId(int _forumId) {
		this._forumId = _forumId;
	}

	/**
	 * 
	 * @return
	 */
	public int getForumId() {
		return _forumId;
	}
}
