/**
 * 
 */
package common.network.messages;

import java.io.Serializable;

/**
 * @author Avi Digmi
 *
 */
public class AddFriendMessage implements Message, Serializable{

	private static final long serialVersionUID = -2134597089758169161L;

	private String username;
	private String friendUsername;

	/**
	 * @param username
	 * @param friendUsername
	 */
	public AddFriendMessage(String username, String friendUsername) {
		
		super();
		setUsername(username);
		setFriendUsername(friendUsername);
	}

	@Override
	public MessageType getMessageType() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFriendUsername() {
		return friendUsername;
	}

	public void setFriendUsername(String friendUsername) {
		this.friendUsername = friendUsername;
	}
}
