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
	 
	private String forumID;
	private String threadID;
	private Vector<String> listOfPosts;
	
	/**
	 * @param threadId
	 */
	public SeeThreadPostsMessage(String threadID) {
		super();
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
		this.threadID = threadID;
	}

	public String getThreadID() {
		return threadID;
	}

	public void setListOfPosts(Vector<String> listOfPosts) {
		this.listOfPosts = listOfPosts;
	}

	public Vector<String> getListOfPosts() {
		return listOfPosts;
	}

	public void setForumID(String forumID) {
		this.forumID = forumID;
	}

	public String getForumID() {
		return forumID;
	}
}
