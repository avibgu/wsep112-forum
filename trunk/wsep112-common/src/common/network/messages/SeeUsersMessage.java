/**
 * 
 */
package common.network.messages;

import java.io.Serializable;
import java.util.Vector;

import common.forum.items.UserInfo;

/**
 * @author Avi Digmi
 *
 */
public class SeeUsersMessage implements Message, Serializable {

	private static final long serialVersionUID = 3080176512679983013L;

	private Vector<UserInfo> _listOfUsers;
	
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

	public void setListOfUsers(Vector<UserInfo> _listOfUsers) {
		this._listOfUsers = _listOfUsers;
	}

	public Vector<UserInfo> getListOfUsers() {
		return _listOfUsers;
	}
}
