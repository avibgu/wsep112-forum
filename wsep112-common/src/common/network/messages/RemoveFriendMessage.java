/**
 * 
 */
package common.network.messages;

import java.io.Serializable;

/**
 * @author Avi Digmi
 *
 */
public class RemoveFriendMessage implements Message, Serializable {

	private static final long serialVersionUID = -7878798852757988393L;

	private String username;
	private String friendUsername;

	/**
	 * @param username
	 * @param friendUsername
	 */
	public RemoveFriendMessage(String username, String friendUsername) {
		
		super();
		setUsername(username);
		setFriendUsername(friendUsername);
	}
	
	/* (non-Javadoc)
	 * @see common.network.messages.Message#getMessageType()
	 */
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
