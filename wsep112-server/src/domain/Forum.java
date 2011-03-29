package domain;

import java.util.Vector;
import common.network.messages.ErrorMessage;


import common.network.messages.Message;


public class Forum {

	private String name;
	private int _forumId;
	private Vector<Thread> threads;
	private Vector<Forum> sub_forums;
	static int thread_available_id=0;
	
	public Forum (String name,int forumId){
		this.name=name;
		this.threads=new Vector<Thread>(0,1);
		this.sub_forums=new Vector<Forum>(0,1);
		this.setForumId(forumId);
	}
	
	public Message reaplyToThread(String title, String body, int threadId,User owner) {
		
		for(int i=0;i<this.threads.size();i++){
			Thread thread=threads.elementAt(i);
			if(thread.getThread_id()==threadId){
				return thread.reaply(title, body, owner);
			}
		}
		
		return new ErrorMessage("theard doesn't exists.");//meaning that we didn't found the thread 
		
	}
	
	//removing the given specific thread from the forum
	public void deleteThread(int threadId){
		for(int i=0;i<this.threads.size();i++){
			if(threads.elementAt(i).getThread_id()==threadId)
				 threads.remove(i);
		}
		
	}
	
	//add new thread to the forum , and filling the thread with the given massage
	public Message add_thread (String title,String body,User owner){
		Thread new_thread=new Thread(thread_available_id,title);
		this.threads.add(new_thread);
		thread_available_id++;
		return new_thread.reaply(title, body,owner);
		
		
	}
	
	
	/**
	 * returns list of posts of given thread id
	 * @param threadID
	 * @return list of posts related to the given thread
	 */
	
	public  Vector<Post> getPostsList (int threadID){
		for(int i=0;i<this.threads.size();i++){
			if(threads.elementAt(i).getThread_id()==threadID)
				return threads.elementAt(i).getPosts();
		}
		
		return null; //meaning that we don't found the thread by the given id;
		
	}
	
	public Vector<Forum> getSub_forums() {
		return sub_forums;
	}

	public void setSub_forums(Vector<Forum> sub_forums) {
		this.sub_forums = sub_forums;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Vector<Thread> getThreads() {
		return threads;
	}

	public void setThreads(Vector<Thread> threads) {
		this.threads = threads;
	}

	public void setForumId(int _forumId) {
		this._forumId = _forumId;
	}

	public int getForumId() {
		return _forumId;
	}

	
	
}
