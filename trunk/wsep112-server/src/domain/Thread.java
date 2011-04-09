package domain;

import java.io.Serializable;
import java.util.Vector;

import common.network.messages.ErrorMessage;
import common.network.messages.Message;
import common.network.messages.OKMessage;

public class Thread implements Serializable{

	private static final long serialVersionUID = 3069041512726662410L;

	private int _threadID;
	private String _title;
	private Vector<Post> _posts;
	static int _available_post_id=0;
	
	public Thread (int thread_id,String title){
		this._posts=new Vector<Post>(0,1);
		this._threadID=thread_id;
		this._title=title;
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
		
		Post new_post=new Post(this._threadID,_available_post_id, title, body,owner);
		_available_post_id+=1;//updating the next available post id
		_posts.add(new_post);
		owner.addPostToOwnerUser(new_post);//adding the post to owner
		
		return new OKMessage();
	}
	
	/**
	 * delete post from thread
	 * side effect : deleting the post from the post list of the owner
	 * @param post_id the id of post to be deleted
	 * @param owner the owner of the post
	 */
	public Message delete (int post_id,User owner){
		
		for(int i=0;i<this._posts.size();i++){
			if(_posts.elementAt(i).get_post_id()==post_id){
				_posts.remove(i);
				owner.removePost(this._threadID,post_id);
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

	public Vector<Post> getPosts() {
		return _posts;
	}

	public void setPosts(Vector<Post> posts) {
		this._posts = posts;
	}
}
