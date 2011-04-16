/**
 * 
 */
package common.network.messages;

import java.io.Serializable;
import java.util.Vector;

import common.forum.items.ForumInfo;

/**
 * @author Avi Digmi
 *
 */
public class SeeForumsListMessage implements Message, Serializable {

	private static final long serialVersionUID = 6946360701823648687L;

	private Vector<ForumInfo> _listOfForums;
	
	/**
	 * 
	 */
	public SeeForumsListMessage() {
		setListOfForums(null);
	}
	
	/* (non-Javadoc)
	 * @see common.network.messages.Message#getMessageType()
	 */
	@Override
	public MessageType getMessageType() {
		return MessageType.SEE_FORUMS_LIST;
	}

	public void setListOfForums(Vector<ForumInfo> listOfForums) {
		this._listOfForums = listOfForums;
	}

	public Vector<ForumInfo> getListOfForums() {
		return _listOfForums;
	}
}
