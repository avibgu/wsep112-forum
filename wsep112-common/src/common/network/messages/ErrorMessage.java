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

	private String _reason;
	private String _forWho;

	/**
	 * @param reason
	 */
	public ErrorMessage(String reason) {
		
		super();
		setReason(reason);
		setForWho("");
	}

	/* (non-Javadoc)
	 * @see common.network.messages.Message#getMessageType()
	 */
	@Override
	public MessageType getMessageType() {
		return MessageType.ERROR;
	}

	public String getReason() {
		return _reason;
	}
	
	public void setReason(String reason) {
		this._reason = reason;
	}

	public String getForWho() {
		return _forWho;
	}
	
	public void setForWho(String forWho) {
		this._forWho = forWho;
	}
}
