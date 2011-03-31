/**
 * 
 */
package common.network.messages;

import java.io.Serializable;
import java.util.Vector;

/**
 * @author Avi Digmi
 *
 */
public class SeeThreadPostsMessage implements Message, Serializable {

	private static final long serialVersionUID = 371165257644698436L;
	 
	private String _forumID;
	private String _threadID;
	private Vector<String> _listOfPosts;
	
	/**
	 * @param threadId
	 */
	public SeeThreadPostsMessage(String forumID, String threadID) {
		super();
		setForumID(forumID);
		setThreadID(threadID);
	}

	/* (non-Javadoc)
	 * @see common.network.messages.Message#getMessageType()
	 */
	@Override
	public MessageType getMessageType() {
		return MessageType.SEE_POSTS_OF_SOME_THREAD;
	}

	public void setThreadID(String threadID) {
		this._threadID = threadID;
	}

	public String getThreadID() {
		return _threadID;
	}

	public void setListOfPosts(Vector<String> listOfPosts) {
		this._listOfPosts = listOfPosts;
	}

	public Vector<String> getListOfPosts() {
		return _listOfPosts;
	}

	public void setForumID(String forumID) {
		this._forumID = forumID;
	}

	public String getForumID() {
		return _forumID;
	}
}
