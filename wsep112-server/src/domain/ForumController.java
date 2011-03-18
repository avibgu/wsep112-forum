
package domain;

import common.network.messages.Message;
import common.network.messages.OKMessage;

/**
 *
 */
public class ForumController {

	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @param username
	 * @param password
	 * @param email
	 * 
	 * @return OKMessage on success, or ErrorMessage (with reason) on failure
	 */
	public Message register(String firstName, String lastName, String username,
			String password, String email) {
		// TODO Auto-generated method stub
		return new OKMessage();
	}

	/**
	 * 
	 * @param username
	 * @param password
	 * 
	 * @return OKMessage on success, or ErrorMessage (with reason) on failure
	 */
	public Message login(String username, String password) {
		// TODO Auto-generated method stub
		return new OKMessage();
	}

	/**
	 * 
	 * @param username
	 * 
	 * @return OKMessage on success, or ErrorMessage (with reason) on failure
	 */
	public Message logout(String username) {
		// TODO Auto-generated method stub
		return new OKMessage();
	}

	/**
	 * 
	 * @param username
	 * @param friendUsername
	 * 
	 * @return OKMessage on success, or ErrorMessage (with reason) on failure
	 */
	public Message AddFriend(String username, String friendUsername) {
		// TODO Auto-generated method stub
		return new OKMessage();
	}

	/**
	 * 
	 * @param username
	 * @param friendUsername
	 * 
	 * @return OKMessage on success, or ErrorMessage (with reason) on failure
	 */
	public Message RemoveFriend(String username, String friendUsername) {
		// TODO Auto-generated method stub
		return new OKMessage();
	}
}
