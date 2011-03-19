/**
 * 
 */
package common.network.messages;

import java.io.Serializable;

/**
 * @author Avi Digmi
 *
 */
public class AddPostMessage implements Message, Serializable {

	private static final long serialVersionUID = 4205576880359045229L;

	private String title;
	private String body;
	private String threadId;

	/**
	 * @param title
	 * @param body
	 * @param threadId
	 */
	public AddPostMessage(String title, String body, String threadId) {
		super();
		setTitle(title);
		setBody(body);
		setThreadId(threadId);
	}

	/* (non-Javadoc)
	 * @see common.network.messages.Message#getMessageType()
	 */
	@Override
	public MessageType getMessageType() {
		return MessageType.ADD_POST_TO_THREAD;
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

	public String getThreadId() {
		return threadId;
	}

	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}
}
