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
public class SeeForumThreadsMessage implements Message, Serializable {

	private static final long serialVersionUID = -5130012869822490753L;

	private String forumID;
	private Vector<String> listOfThreads;
	
	/**
	 * 
	 */
	public SeeForumThreadsMessage(String forumID) {

		super();
		setForumID(forumID);
		setListOfThreads(null);
	}
	
	/* (non-Javadoc)
	 * @see common.network.messages.Message#getMessageType()
	 */
	@Override
	public MessageType getMessageType() {
		return MessageType.SEE_FORUM_THREADS;
	}

	public void setForumID(String forumdID) {
		this.forumID = forumdID;
	}

	public String getForumID() {
		return forumID;
	}

	public void setListOfThreads(Vector<String> listOfThreads) {
		this.listOfThreads = listOfThreads;
	}

	public Vector<String> getListOfThreads() {
		return listOfThreads;
	}
}
