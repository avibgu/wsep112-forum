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
public class SeeForumsListMessage implements Message, Serializable {

	private static final long serialVersionUID = 6946360701823648687L;

	private Vector<String> _listOfForums;
	
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

	public void setListOfForums(Vector<String> listOfForums) {
		this._listOfForums = listOfForums;
	}

	public Vector<String> getListOfForums() {
		return _listOfForums;
	}
}
