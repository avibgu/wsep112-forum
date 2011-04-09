/**
 * 
 */
package common.network.messages;

import java.io.Serializable;

import common.network.RemoteObserver;

/**
 * @author Avi Digmi
 *
 */
public class AddThreadMessage implements Message, Serializable {

	private static final long serialVersionUID = 5581097442389236130L;
	 
	private String _forumId;
	private String _title;
	private String _body;
	private String _ownerUsername;
	private RemoteObserver _remoteObserver;
	
	/**
	 * 
	 * @param title
	 * @param body
	 */
	public AddThreadMessage(String forumID,String title, String body,
			String ownerUsername, RemoteObserver ro) {
		
		super();
		setForumId(forumID);
		setTitle(title);
		setBody(body);
		setOwnerUsername(ownerUsername);
		setRemoteObserver(ro);
	}

	/* (non-Javadoc)
	 * @see common.network.messages.Message#getMessageType()
	 */
	@Override
	public MessageType getMessageType() {
		return MessageType.ADD_THREAD;
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

	public void setRemoteObserver(RemoteObserver _remoteObserver) {
		this._remoteObserver = _remoteObserver;
	}

	public RemoteObserver getRemoteObserver() {
		return _remoteObserver;
	}
}
