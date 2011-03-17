/**
 * 
 */
package network;

/**
 * @author avi
 *
 */
public class LoginMessage implements Message /* ,Serializable */{

	/* (non-Javadoc)
	 * @see network.Message#getMessageType()
	 */
	@Override
	public MessageType getMessageType() {
		// TODO Auto-generated method stub
		return MessageType.LOGIN;
	}

}
