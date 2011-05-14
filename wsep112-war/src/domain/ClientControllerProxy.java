/**
 *
 */
package domain;

import java.util.Vector;

/**
 * @author Avi Digmi
 *
 */
public class ClientControllerProxy{

	private static final long serialVersionUID = 4717931904659119985L;

	public boolean register(String firstName, String lastName, String username,
			String password, String email) {	
		return true;
	}

	public boolean login(String username, String password) {
		return true;
	}

	public void logout() {
		return;
	}

    public boolean AddFriend(String friendUsername) {
    	return true;
    }

    public boolean RemoveFriend(String friendUsername) {
    	return true;
    }

    public void replyToThread(String forumID, String title, String body, String threadId) {
    	return;
    }
    
   public void editPost(String forumID, String title, String body,
		   String threadId, String postId) {
	   return;
   }

    public void addThread(String forumID,String title, String body) {
    	return;
    }

    public Vector<String> getForumsList() {
		return new Vector<String>();
    }

	public Vector<String> getThreadsList(String forumID) {
		return new Vector<String>();
	}

	public Vector<String> getPostsList(String forumID,String threadID) {
		return new Vector<String>();
	}

	public Vector<String> getFriendList() {
		return new Vector<String>();
	}

	public Vector<String> getUsersList() {
		return new Vector<String>();
	}

	public boolean RemoveThread(String threadId, String forumId) {
    	return true;
    }
	
	public boolean RemovePost(String threadId, String postId) {
		return true;
    }
}
