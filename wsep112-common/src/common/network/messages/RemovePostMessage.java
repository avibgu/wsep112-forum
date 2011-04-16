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
public class RemovePostMessage implements Serializable, Message {

	private static final long serialVersionUID = -293643222414529870L;

	private String _threadId;
	private String _postId;
	private RemoteObserver _remoteObserver;
	
	/**
	 * @param _threadId
	 * @param _postId
	 */
	public RemovePostMessage(String threadId, String postId, RemoteObserver ro) {
		
		super();
		setThreadId(threadId);
		setPostId(postId);
		setRemoteObserver(ro);
	}

	/* (non-Javadoc)
	 * @see common.network.messages.Message#getMessageType()
	 */
	@Override
	public MessageType getMessageType() {
		return MessageType.REMOVE_POST;
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

	public void setRemoteObserver(RemoteObserver _remoteObserver) {
		this._remoteObserver = _remoteObserver;
	}

	public RemoteObserver getRemoteObserver() {
		return _remoteObserver;
	}
}
