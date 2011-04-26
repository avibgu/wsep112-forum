/**
 * 
 */
package common.network.messages;

import java.io.Serializable;

import common.observation.RemoteObserver;

/**
 * @author Avi Digmi
 *
 */
public class EditPostMessage implements Message, Serializable {

	private static final long serialVersionUID = -1514662712199426341L;
	
	private String _forumID;
	private String _title;
	private String _body;
	private String _threadId;
	private String _postId;
	private String _username;
	private RemoteObserver _remoteObserver;
	
	/**
	 * 
	 * @param forumID
	 * @param title
	 * @param body
	 * @param threadId
	 * @param postId
	 * @param currentLogedInUsername
	 * @param clientController
	 */
	public EditPostMessage(String forumID, String title, String body,
			String threadId, String postId, String username,
			RemoteObserver ro) {
		super();
		setForumID(forumID);
		setTitle(title);
		setBody(body);
		setThreadId(threadId);
		setPostId(postId);
		setUsername(username);
		setRemoteObserver(ro);
	}

	/* (non-Javadoc)
	 * @see common.network.messages.Message#getMessageType()
	 */
	@Override
	public MessageType getMessageType() {
		return MessageType.EDIT_POST;
	}

	public void setForumID(String _forumID) {
		this._forumID = _forumID;
	}

	public String getForumID() {
		return _forumID;
	}

	public void setTitle(String _title) {
		this._title = _title;
	}

	public String getTitle() {
		return _title;
	}

	public void setBody(String _body) {
		this._body = _body;
	}

	public String getBody() {
		return _body;
	}

	public void setThreadId(String _threadId) {
		this._threadId = _threadId;
	}

	public String getThreadId() {
		return _threadId;
	}

	public void setPostId(String _postId) {
		this._postId = _postId;
	}

	public String getPostId() {
		return _postId;
	}

	public void setUsername(String _username) {
		this._username = _username;
	}

	public String getUsername() {
		return _username;
	}

	public void setRemoteObserver(RemoteObserver _remoteObserver) {
		this._remoteObserver = _remoteObserver;
	}

	public RemoteObserver getRemoteObserver() {
		return _remoteObserver;
	}
}
