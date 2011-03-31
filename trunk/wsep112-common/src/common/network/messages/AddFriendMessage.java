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

	private String _username;
	private String _friendUsername;

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
		return MessageType.ADD_FRIEND;
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
