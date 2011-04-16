/**
 * 
 */
package common.network.messages;

import java.io.Serializable;

/**
 * @author Avi Digmi
 *
 */
public class RemoveThreadMessage implements Serializable, Message {

	private static final long serialVersionUID = 8760287282205068029L;

	private String _threadId;
	private String _forumId;
	
	public RemoveThreadMessage(String threadId, String forumId) {

		super();
		setThreadId(threadId);
		setForumId(forumId);
	}

	/* (non-Javadoc)
	 * @see common.network.messages.Message#getMessageType()
	 */
	@Override
	public MessageType getMessageType() {
		return MessageType.REMOVE_THREAD;
	}

	public void setThreadId(String _threadId) {
		this._threadId = _threadId;
	}

	public String getThreadId() {
		return _threadId;
	}

	public void setForumId(String _forumId) {
		this._forumId = _forumId;
	}

	public String getForumId() {
		return _forumId;
	}
}
