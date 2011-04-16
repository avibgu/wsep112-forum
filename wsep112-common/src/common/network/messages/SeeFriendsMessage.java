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
public class SeeFriendsMessage implements Message, Serializable {

	private static final long serialVersionUID = 6615560281665635565L;

	private String _username;
	private Vector<UserInfo> _listOfFriends;
	
	/**
	 * 
	 * @param username
	 */
	public SeeFriendsMessage(String username) {
		
		super();
		setUsername(username);
		setListOfFriends(null);
	}
	
	/* (non-Javadoc)
	 * @see common.network.messages.Message#getMessageType()
	 */
	@Override
	public MessageType getMessageType() {
		return MessageType.SEE_FRIENDS_LIST;
	}

	public void setUsername(String _username) {
		this._username = _username;
	}

	public String getUsername() {
		return _username;
	}

	public void setListOfFriends(Vector<UserInfo> _listOfFriends) {
		this._listOfFriends = _listOfFriends;
	}

	public Vector<UserInfo> getListOfFriends() {
		return _listOfFriends;
	}
}
