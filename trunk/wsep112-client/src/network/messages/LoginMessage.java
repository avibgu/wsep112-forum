/**
 * 
 */
package network.messages;

import java.io.Serializable;


/**
 * @author avi
 *
 */
public class LoginMessage implements Message ,Serializable {

	private static final long serialVersionUID = -6144281381240070187L;

	/* (non-Javadoc)
	 * @see network.Message#getMessageType()
	 */
	@Override
	public MessageType getMessageType() {
		// TODO Auto-generated method stub
		return MessageType.LOGIN;
	}

}
