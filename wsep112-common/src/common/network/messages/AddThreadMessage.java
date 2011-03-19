/**
 * 
 */
package common.network.messages;

import java.io.Serializable;

/**
 * @author Avi Digmi
 *
 */
public class AddThreadMessage implements Message, Serializable {

	private static final long serialVersionUID = 5581097442389236130L;
	
	private String title;
	private String body;
	
	/**
	 * 
	 * @param title
	 * @param body
	 */
	public AddThreadMessage(String title, String body) {
		
		super();
		setTitle(title);
		setBody(body);
	}

	/* (non-Javadoc)
	 * @see common.network.messages.Message#getMessageType()
	 */
	@Override
	public MessageType getMessageType() {
		return MessageType.ADD_THREAD;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
