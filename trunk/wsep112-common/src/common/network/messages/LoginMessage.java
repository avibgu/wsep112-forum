/**
 * 
 */
package common.network.messages;

import java.io.Serializable;


/**
 * @author avi
 *
 */
public class LoginMessage implements Message ,Serializable {

	private static final long serialVersionUID = -6144281381240070187L;

	private String _username;
	private String _password;

	/**
	 * @param username
	 * @param password
	 */
	public LoginMessage(String username, String password) {
		
		super();
		setUsername(username);
		setPassword(password);
	}

	/* (non-Javadoc)
	 * @see network.Message#getMessageType()
	 */
	@Override
	public MessageType getMessageType() {
		return MessageType.LOGIN;
	}

	public String getUsername() {
		return _username;
	}

	public void setUsername(String username) {
		this._username = username;
	}

	public String getPassword() {
		return _password;
	}

	public void setPassword(String password) {
		this._password = password;
	}
}
