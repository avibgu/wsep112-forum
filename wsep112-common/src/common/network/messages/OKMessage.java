/**
 * 
 */
package common.network.messages;

import java.io.Serializable;

/**
 * @author Avi Digmi
 *
 */
public class OKMessage implements Message, Serializable {

	private static final long serialVersionUID = 1300815185723245629L;

	/* (non-Javadoc)
	 * @see common.network.messages.Message#getMessageType()
	 */
	@Override
	public MessageType getMessageType() {

		return MessageType.OK;
	}

}
