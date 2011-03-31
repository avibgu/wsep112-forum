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

	private String _username;
	private String _friendUsername;

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
		return MessageType.REMOVE_FRIEND;
	}

	public String getUsername() {
		return _username;
	}

	public void setUsername(String username) {
		this._username = username;
	}

	public String getFriendUsername() {
		return _friendUsername;
	}

	public void setFriendUsername(String friendUsername) {
		this._friendUsername = friendUsername;
	}
}
