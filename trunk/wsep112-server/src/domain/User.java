package domain;

import java.io.Serializable;
import java.util.Observable;
import java.util.Vector;
import common.network.messages.ErrorMessage;
import common.network.messages.Message;
import common.network.messages.OKMessage;

public class User extends Observable implements Serializable{

	private static final long serialVersionUID = 3753192357127381924L;

	public enum Status { ONLINE, OFFLINE }

	private String _firstName;
	private String _lastName;
	private String _username;
	private String _password;
	private String _email;
	private Status _status;
	private Vector<String> _friends;
	private Vector<Post> _posts;

	/**
	 * Constructor of User
	 *  @param firstName
	 *  @param lastName
	 *  @param username
	 *  @param password
	 *  @param email
	 */
	public User(String firstName, String lastName, String username,
			    String password, String email){

			_firstName = firstName;
			_lastName = lastName;
			_username = username;
			_password = password;
			_email = email;
			_status= Status.OFFLINE;
			_friends = new Vector<String>();
			_posts= new Vector<Post>();
	}

	/**
	 * @return String first name of user
	 */
	public String getFirstName() {
		return _firstName;
	}

	/**
	 * @return Status - status (Offline/Online) of user
	 */
	public Status getStatus() {
		return _status;
	}

	/**
	 *
	 * @param state status of user (Offline/Online)
	 */
	public void setStatus(Status state){
		this._status=state;
	}

	/**
	 * @param firstName
	 * Sets first name of user
	 */
	public void setFirstName(String firstName) {
		this._firstName = firstName;
	}

	/**
	 * @param lastName
	 * Sets last name of user
	 */
	public void setLastName(String lastName) {
		this._lastName = lastName;
	}

	/**
	 * @return String - last name of user
	 */
	public String getLastName() {
		return _lastName;
	}

	/**
	 * @param username
	 * Sets user name
	 */
	public void setUserName(String username) {
		this._username = username;
	}

	/**
	 * @return String
	 * Returns user name
	 */
	public String getUserName(){
		return _username;
	}

	/**
	 * @param password
	 * Sets user's password as HASH
	 */
	public void setPassword(String password) {
		this._password = password;
	}

	/**
	 * @return String- password of user
	 */
	public String getPassword(){
		return _password;
	}

	/**
	 * @param email
	 * Sets email of user
	 */
	public void setEmail(String email) {
		this._email = email;
	}

	/**
	 * @return String
	 * Returns email of user
	 */
	public String getEmail() {
		return _email;
	}

	/**
	 * Adding friend to user's list
	 * @param userNameFriend
	 * @return Message
	 */
	public Message addFriend(String userNameFriend){

		// Check if the username is already in his friends
		if (isExistUser(userNameFriend.toLowerCase())){
			return new ErrorMessage("Friend is already exists in user's list.");
		}

		_friends.add(userNameFriend);
		return new OKMessage();
	}

	/**
	 *
	 * @param userNameFriend
	 * @return Message
	 */
	public Message removeFriend(String userNameFriend){

		// Check if the userName is in user's list of friends
		if (isExistUser(userNameFriend.toLowerCase())){
			_friends.removeElementAt(this.getFriendIndex(userNameFriend));
			return new OKMessage();
		}
		return new ErrorMessage("Friend is not exists in user's list.");
	}

	/**
	 * @return Vector<Post> - posts of User (Owner)
	 */
	public Vector<Post> getPosts(){
		return _posts;
	}

	/**
	 * @param post
	 * Links a post to user (owner)
	 */
	public void addPostToOwnerUser(Post post){
		_posts.add(post);
	}

	/**
	 * Links a post to user (owner)
	 * @param threadId
	 * @param postId
	 */
	public void removePost(int threadId, int postId){
		if (this.isExistPostId(postId,threadId)){
			_posts.remove(getPostIndex(postId,threadId));
		}
	}

	/**
	 *
	 * @param username
	 * @return true if the userName exist, and false otherwise.
	 */
	public Boolean isExistUser(String username){
		for (int i=0; i < _friends.size() ; i++){
			if (_friends.get(i).equals(username))
				return true;
		}
		return false;
	}

	/**
	 * 
	 * @param threadId
	 * @param postId
	 * @return true if the postId exists, and false otherwise.
	 */
	public Boolean isExistPostId(int threadId, int postId){
		for (int i=0; i < _posts.size() ; i++){
			if (_posts.get(i).get_post_id()==postId && _posts.get(i).getThread_id()==threadId)
				return true;
		}
		return false;
	}

	/**
	 *
	 * @param username username of friend
	 * @return index of string in array _friends, -1 if not exists
	 */
	public int getFriendIndex(String username){
		for (int i=0; i < _friends.size() ; i++){
			if (_friends.get(i).equals(username))
				return i;
		}
		return -1;
	}

	/**
	 * index of post id in array _posts, -1 if not exists
	 * @param postId 
	 * @param threadId
	 * @return
	 */
	public int getPostIndex(int postId, int threadId){
		for (int i=0; i < _posts.size() ; i++){
			if (_posts.get(i).get_post_id()==postId && _posts.get(i).getThread_id()==threadId)
				return i;
		}
		return -1;
	}
}