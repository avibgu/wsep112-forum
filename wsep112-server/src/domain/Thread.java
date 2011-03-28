package domain;

import java.util.Vector;

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
	
	//adding new post to topic
	//return the new post created un order the user to store it on his list
	public Post reaply(String title, String body){
		Post new_post=new Post(available_post_id, title, body);
		available_post_id+=1;//updating the next available post id
		posts.add(new_post);
		return new_post;
	}
	
	//removing the post indentified with the given id from the theared's id
	public void delete (int post_id){
		posts.remove(post_id);
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
