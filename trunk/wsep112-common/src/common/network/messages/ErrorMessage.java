/**
 * 
 */
package common.network.messages;

import java.io.Serializable;

/**
 * @author Avi Digmi
 *
 */
public class ErrorMessage implements Message, Serializable {

	private static final long serialVersionUID = 5203913683848654373L;

	private String reason;

	/**
	 * @param reason
	 */
	public ErrorMessage(String reason) {
		
		super();
		setReason(reason);
	}

	/* (non-Javadoc)
	 * @see common.network.messages.Message#getMessageType()
	 */
	@Override
	public MessageType getMessageType() {
		return MessageType.ERROR;
	}

	public String getReason() {
		return reason;
	}
	
	public void setReason(String reason) {
		this.reason = reason;
	}
}
