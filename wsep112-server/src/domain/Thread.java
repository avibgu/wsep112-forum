package domain;

import java.util.Vector;

import common.network.messages.ErrorMessage;
import common.network.messages.Message;
import common.network.messages.OKMessage;

public class Thread {

	private int thread_id;
	private String title;
	private Vector<Post> posts;
	static int available_post_id=0;
	
	
	
	public Thread (int thread_id,String title){
		this.posts=new Vector<Post>(0,1);
		this.thread_id=thread_id;
		this.title=title;
		
		
	}
	
	/**
	 * adding new post to topic
	 * @param title
	 * @param body
	 * @param owner
	 * @return
	 */
	//return the new post created un order the user to store it on his list
	public Message reaply(String title, String body,User owner){
		
		Post new_post=new Post(this.thread_id,available_post_id, title, body,owner);
		available_post_id+=1;//updating the next available post id
		posts.add(new_post);
		owner.addPostToOwnerUser(new_post);//adding the post to owner
		
		return new OKMessage();
	}
	
	/**
	 * delete post from thread
	 * side effect : deleting the post from the post list of the owner
	 * @param post_id
	 * @param owner the owner of the post
	 */
	public Message delete (int post_id,User owner){
		
		for(int i=0;i<this.posts.size();i++){
			if(posts.elementAt(i).get_post_id()==post_id){
				posts.remove(i);
			//	owner.remove_post(this.thread_id,post_id);
				return new OKMessage();
			}
		}
		
		 return new ErrorMessage("post doesn't exists.");
	}

	
	
	//*************************** GETTERS AND SETTERS ***********************************
	public int getThread_id() {
		return thread_id;
	}

	public void setThread_id(int thread_id) {
		this.thread_id = thread_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Vector<Post> getPosts() {
		return posts;
	}

	public void setPosts(Vector<Post> posts) {
		this.posts = posts;
	}
	
	
	
}
