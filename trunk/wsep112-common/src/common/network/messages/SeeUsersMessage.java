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
public class SeeUsersMessage implements Message, Serializable {

	private static final long serialVersionUID = 3080176512679983013L;

	private Vector<String> _listOfUsers;
	
	/**
	 * 
	 */
	public SeeUsersMessage() {
		
		super();
		setListOfUsers(null);
	}
	
	/* (non-Javadoc)
	 * @see common.network.messages.Message#getMessageType()
	 */
	@Override
	public MessageType getMessageType() {
		return MessageType.SEE_USERS_LIST;
	}

	public void setListOfUsers(Vector<String> _listOfUsers) {
		this._listOfUsers = _listOfUsers;
	}

	public Vector<String> getListOfUsers() {
		return _listOfUsers;
	}
}
