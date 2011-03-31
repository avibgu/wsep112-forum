/**
 * 
 */
package common.network.messages;

import java.io.Serializable;

/**
 * @author avi
 *
 */
public class LogoutMessage implements Message, Serializable{

	private static final long serialVersionUID = 8498004073663790243L;

	private String _username;
	
	/**
	 * @param username
	 */
	public LogoutMessage(String username) {
		
		super();
		setUsername(username);
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
}
