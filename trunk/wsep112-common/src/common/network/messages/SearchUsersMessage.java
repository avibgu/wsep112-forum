/**
 * 
 */
package common.network.messages;

import java.io.Serializable;
import java.util.Vector;

import common.forum.items.UserInfo;

/**
 * @author Moshe Unger
 *
 */
public class SearchUsersMessage implements Message, Serializable {

	private static final long serialVersionUID = -8758876705429432208L;
	private Vector<UserInfo> _listOfUsers;
	private String _input;
	
	/**
	 * 
	 */
	public SearchUsersMessage(String input) {
		super();
		setListOfUsers(null);
		this._input = input;
	}
	
	/* (non-Javadoc)
	 * @see common.network.messages.Message#getMessageType()
	 */
	@Override
	public MessageType getMessageType() {
		return MessageType.SEARCH_USERS_LIST;
	}

	public void setListOfUsers(Vector<UserInfo> _listOfUsers) {
		this._listOfUsers = _listOfUsers;
	}

	public Vector<UserInfo> getListOfUsers() {
		return _listOfUsers;
	}
	
	public String getSearchInput(){
		return this._input;
	}
}
