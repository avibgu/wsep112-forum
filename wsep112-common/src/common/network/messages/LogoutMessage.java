/**
 * 
 */
package common.network.messages;

import java.io.Serializable;

import common.network.RemoteObserver;

/**
 * @author avi
 *
 */
public class LogoutMessage implements Message, Serializable{

	private static final long serialVersionUID = 8498004073663790243L;

	private String _username;
	private RemoteObserver _remoteObserver;
	
	/**
	 * @param username
	 */
	public LogoutMessage(String username, RemoteObserver ro) {
		
		super();
		setUsername(username);
		setRemoteObserver(ro);
	}

	/* (non-Javadoc)
	 * @see network.Message#getMessageType()
	 */
	@Override
	public MessageType getMessageType() {
		// TODO Auto-generated method stub
		return MessageType.LOGOUT;
	}

	public String getUsername() {
		return _username;
	}

	public void setUsername(String username) {
		this._username = username;
	}

	public void setRemoteObserver(RemoteObserver _remoteObserver) {
		this._remoteObserver = _remoteObserver;
	}
	
	public RemoteObserver getRemoteObserver() {
		return this._remoteObserver;
	}
}
