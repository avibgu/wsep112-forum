/**
 * 
 */
package common.network.messages;

import java.io.Serializable;

/**
 * @author avi
 *
 */
public class LogutMessage implements Message, Serializable{

	private static final long serialVersionUID = 8498004073663790243L;

	/* (non-Javadoc)
	 * @see network.Message#getMessageType()
	 */
	@Override
	public MessageType getMessageType() {
		// TODO Auto-generated method stub
		return MessageType.LOGOUT;
	}

}
