package domain;

public class User {
	public enum Status { ONLINE, OFFLINE }
	
	private String _firstName;
	private String _lastName;
	private String _username;
	private String _password;
	private String _email;
	private Status _status;
	private Set<String> _friends;
	private Vector<Post> _posts;
	
	/*
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
			_status= OFFLINE;
			_friends = new HashSet<String>();	
			_posts= new Vector<Post>();
	}
	
	/*
	 * @return String first name of user
	 */
	public String getFirstName() {
		return _firstName;
	}
	/*
	 * @return Status - status (Offline/Online) of user
	 */
	public Status getStatus() {
		return _status;
	}
	/*
	 * sets status of user to ONLINE
	 */
	public void setOnline() {
		_status=ONLINE;
	}
	/*
	 * sets status of user to OFFLINE
	 */
	public void setOffline() {
		_status=OFFLINE;
	}
	/*
	 * @param firstName
	 * Sets first name of user
	 */
	public void setFirstName(String firstName) {
		this._firstName = firstName;
	}
	/*
	 * @param lastName
	 * Sets last name of user
	 */
	public void setLastName(String lastName) {
		this._lastName = lastName;
	}
	/*
	 * @return String - last name of user
	 */
	public String getLastName() {
		return _lastName;
	}
	/*
	 * @param username
	 * Sets user name
	 */
	public void setUserName(String username) {
		this._username = username;
	}
	/*
	 * @return String
	 * Returns user name
	 */
	public String getUserName(){
		return _username;
	}
	/*
	 * @param password
	 * Sets user's password as HASH 
	 */
	public void setPassword(String password) {
		this._password = password;
	}
	/*
	 * @return String- password of user
	 */
	public String getPassword(){
		return _password;
	}
	/*
	 * @param email
	 * Sets email of user
	 */
	public void setEmail(String email) {
		this._email = email;
	}
	/*
	 * @return String
	 * Returns email of user
	 */
	public String getEmail() {
		return _email;
	}
	/*
	 * @param userNameFriend
	 * @return AddFriendMessage
	 * Adds friend to user's list of friends
	 */
	public AddFriendMessage addFriend(String userNameFriend){
		_friends.add(userNameFriend); 
		return new AddFriendMessage(_username,userNameFriend);
	}
	/*
	 * @param userNameFriend
	 * @return RemoveFriendMessage
	 * Removes a friend from user's list of friends
	 */	
	public RemoveFriendMessage removeFriend(String userNameFriend){
		_friends.remove(userNameFriend); 
		return new RemoveFriendMessage(_username,userNameFriend);
	}
	/*
	 * @return Vector<Post> - posts of User (Owner)
	 */
	public Vector<Post> getPosts(){
		return _posts;
	}
	/*
	 * @param post
	 * Links a post to user (owner)
	 */
	public void addPostToOwnerUser(Post post){
		_posts.add(post);
	}
}
