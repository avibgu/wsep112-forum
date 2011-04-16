/**
 * 
 */
package common.network.messages;

import java.io.Serializable;

import common.observation.RemoteObserver;

/**
 * @author Avi Digmi
 *
 */
public class RemoveFriendMessage implements Message, Serializable {

	private static final long serialVersionUID = -7878798852757988393L;

	private String _username;
	private String _friendUsername;
	private RemoteObserver _remoteObserver;

	/**
	 * @param username
	 * @param friendUsername
	 */
	public RemoveFriendMessage(String username, String friendUsername, RemoteObserver ro) {
		
		super();
		setUsername(username);
		setFriendUsername(friendUsername);
		setRemoteObserver(ro);
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

	public void setRemoteObserver(RemoteObserver _remoteObserver) {
		this._remoteObserver = _remoteObserver;
	}
	
	public RemoteObserver getRemoteObserver() {
		return this._remoteObserver;
	}
}
