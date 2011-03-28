package domain;

import java.util.Vector;
import common.network.messages.ErrorMessage;

public class Forum {

	private String name;
	private Vector<Thread> threads;
	private Vector<Forum> related_forums;
	static int thread_available_id=0;
	
	public Forum (String name){
		this.name=name;
		this.threads=new Vector<Thread>(0,1);
		this.related_forums=new Vector<Forum>(0,1);
	}
	
	public Post reaplyToThread(String title, String body, int threadId) {
		
		for(int i=0;i<this.threads.size();i++){
			if(threads.elementAt(i).getThread_id()==threadId)
				 return threads.elementAt(i).reaply(title, body);
		}
		System.out.println("incorrect given id");
		return null;//meaning that we didn't found the thread 
		
	}
	
	//removing the given specific thread from the forum
	public void deleteThread(int threadId){
		for(int i=0;i<this.threads.size();i++){
			if(threads.elementAt(i).getThread_id()==threadId)
				 threads.remove(i);
		}
		
	}
	
	//add new thread to the forum , and filling the thread with the given massage
	public Post add_thread (String title,String body){
		Thread new_thread=new Thread(thread_available_id,title);
		this.threads.add(new_thread);
		thread_available_id++;
		Post new_post=new_thread.reaply(title, body);
		
		return new_post;
	}
	
	
	//returns list of posts of given thread id
	//returns null if we didn't found the thread
	public  Vector<Post> getPostsList (int threadID){
		for(int i=0;i<this.threads.size();i++){
			if(threads.elementAt(i).getThread_id()==threadID)
				return threads.elementAt(i).getPosts();
		}
		
		return null; //meaning that we don't found the thread by the given id;
		
	}
	
	
}
