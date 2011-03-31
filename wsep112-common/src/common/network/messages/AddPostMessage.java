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
 
	private String _title;
	private String _body;
	private String _threadId;
	private String _forumId;
	private String _ownerUsername;

	/**
	 * @param title
	 * @param body
	 * @param threadId
	 */
	public AddPostMessage(String forumID,String title, String body, String threadId, String ownerUsername) {
		super();
		setForumId(forumID);
		setTitle(title);
		setBody(body);
		setThreadId(threadId);
		setOwnerUsername(ownerUsername);
	}

	/* (non-Javadoc)
	 * @see common.network.messages.Message#getMessageType()
	 */
	@Override
	public MessageType getMessageType() {
		return MessageType.ADD_POST_TO_THREAD;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		this._title = title;
	}

	public String getBody() {
		return _body;
	}

	public void setBody(String body) {
		this._body = body;
	}

	public String getThreadId() {
		return _threadId;
	}

	public void setThreadId(String threadId) {
		this._threadId = threadId;
	}

	public void setOwnerUsername(String ownerUsername) {
		this._ownerUsername = ownerUsername;
	}

	public String getOwnerUsername() {
		return _ownerUsername;
	}

	public void setForumId(String forumId) {
		this._forumId = forumId;
	}

	public String getForumId() {
		return _forumId;
	}
}
