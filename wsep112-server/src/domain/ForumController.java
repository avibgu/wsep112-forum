
package domain;

import java.util.Vector;

import common.network.messages.Message;
import common.network.messages.OKMessage;
import common.network.messages.SeeForumThreadsMessage;
import common.network.messages.SeeForumsListMessage;

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

	/**
	 * 
	 * @param title
	 * @param body
	 * @param threadId
	 * 
	 * @return OKMessage on success, or ErrorMessage (with reason) on failure
	 */
	public Message replyToThread(String title, String body, String threadId) {
		// TODO Auto-generated method stub
		return new OKMessage();
	}

	/**
	 * 
	 * @param title
	 * @param body
	 * 
	 * @return OKMessage on success, or ErrorMessage (with reason) on failure
	 */
	public Message addThread(String title, String body) {
		// TODO Auto-generated method stub
		return new OKMessage();
	}

	/**
	 * 
	 * @param sflm
	 * 
	 * @return list of Forums inside the given message, or ErrorMessage (with reason) on failure
	 */
	public Message getForumsList(SeeForumsListMessage sflm) {
		
		Vector<String> listOfForums = new Vector<String>();
		
		//	TODO: add the forums names to listOfForums
		
		sflm.setListOfForums(listOfForums);
		
		return sflm;
	}

	/**
	 * 
	 * @param sflm
	 * 
	 * @return list of Threads inside the given message, or ErrorMessage (with reason) on failure
	 */
	public Message getForumsList(String forumID, SeeForumThreadsMessage sftm) {

		Vector<String> listOfThreads = new Vector<String>();
		
		//	TODO: add the threads names to listOfThreads
		
		sftm.setListOfThreads(listOfThreads);
		
		return sftm;
	}
}
