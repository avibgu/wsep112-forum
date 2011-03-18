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

	private String username;
	private String password;

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
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
