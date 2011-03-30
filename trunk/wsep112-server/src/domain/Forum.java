package domain;

import java.util.Vector;
import common.network.messages.ErrorMessage;


import common.network.messages.Message;


public class Forum {

	private String _name;
	private int _forumId;
	private Vector<Thread> _threads;
	private Vector<Forum> _subForums;
	static int _threadAvailableID=0;
	
	public Forum (String name,int forumId){
		this._name=name;
		this._threads=new Vector<Thread>(0,1);
		this._subForums=new Vector<Forum>(0,1);
		setForumId(forumId);
	}
	
	/**
	 * 
	 * @param title
	 * @param body
	 * @param threadId
	 * @param owner
	 * @return
	 */
	public Message reaplyToThread(String title, String body, int threadId,User owner) {
		
		for(int i=0;i<this._threads.size();i++){
			Thread thread=_threads.elementAt(i);
			if(thread.getThread_id()==threadId){
				return thread.reaply(title, body, owner);
			}
		}
		
		return new ErrorMessage("theard doesn't exists.");//meaning that we didn't found the thread 
		
	}
	
	/**
	 * 
	 * @param threadId
	 */
	//removing the given specific thread from the forum
	public void deleteThread(int threadId){
		for(int i=0;i<this._threads.size();i++){
			if(_threads.elementAt(i).getThread_id()==threadId)
				 _threads.remove(i);
		}
		
	}
	
	/**
	 * 
	 * @param title
	 * @param body
	 * @param owner
	 * @return
	 */
	//add new thread to the forum , and filling the thread with the given massage
	public Message add_thread (String title,String body,User owner){
		Thread new_thread=new Thread(_threadAvailableID,title);
		this._threads.add(new_thread);
		_threadAvailableID++;
		return new_thread.reaply(title, body,owner);
		
		
	}
	
	/**
	 * returns list of posts of given thread id
	 * @param threadID
	 * @return list of posts related to the given thread
	 */
	public  Vector<Post> getPostsList (int threadID){
		for(int i=0;i<this._threads.size();i++){
			if(_threads.elementAt(i).getThread_id()==threadID)
				return _threads.elementAt(i).getPosts();
		}
		
		return null; //meaning that we don't found the thread by the given id;
		
	}
	
	/**
	 * 
	 * @return
	 */
	public Vector<Forum> getSub_forums() {
		return _subForums;
	}

	/**
	 * 
	 * @param sub_forums
	 */
	public void setSub_forums(Vector<Forum> sub_forums) {
		this._subForums = sub_forums;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getName() {
		return _name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this._name = name;
	}
	
	/**
	 * 
	 * @return
	 */
	public Vector<Thread> getThreads() {
		return _threads;
	}

	/**
	 * 
	 * @param threads
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
