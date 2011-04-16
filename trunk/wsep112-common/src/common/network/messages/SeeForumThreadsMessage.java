/**
 * 
 */
package common.network.messages;

import java.io.Serializable;
import java.util.Vector;

import common.forum.items.ThreadInfo;

/**
 * @author Avi Digmi
 *
 */
public class SeeForumThreadsMessage implements Message, Serializable {

	private static final long serialVersionUID = -5130012869822490753L;

	private String _forumID;
	private Vector<ThreadInfo> _listOfThreads;
	
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
		this._forumID = forumdID;
	}

	public String getForumID() {
		return _forumID;
	}

	public void setListOfThreads(Vector<ThreadInfo> listOfThreads) {
		this._listOfThreads = listOfThreads;
	}

	public Vector<ThreadInfo> getListOfThreads() {
		return _listOfThreads;
	}
}
