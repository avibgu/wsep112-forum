package domain;

import java.util.HashMap;
import java.util.Vector;

import common.forum.items.UserInfo;

/**
 * Web Controller Singleton..
 */
public class WebController {

	private HashMap<String, ClientController> usersControllersMap;	
	
	// Private constructor prevents instantiation from other classes
	private WebController() { }
	
	/**
	* SingletonHolder is loaded on the first execution of Singleton.getInstance()
	* or the first access to SingletonHolder.INSTANCE, not before.
	*/
	private static class WebControllerHolder {
		public static final WebController INSTANCE = new WebController();
	}

	public static WebController getInstance() {
		return WebControllerHolder.INSTANCE;
	}
	
//	From Here:	delegations to a ClientController

	public boolean login(String username, String password) {
		// TODO Auto-generated method stub
		return true;
	}
	
	public Vector<UserInfo> getFriendList() {
		// TODO Auto-generated method stub
		
		Vector<UserInfo> ans = new Vector<UserInfo>();
		
		ans.add(new UserInfo("ONLINE", "Avi"));
		ans.add(new UserInfo("OFFLINE", "Shiran"));
		
		return ans;
	}

	public boolean register(String firstName, String lastName, String username,
			String password, String email) {
		// TODO Auto-generated method stub
		return true;
	}

	public void AddFriend(String username, String addFriendName) {
		// TODO Auto-generated method stub
		
	}

	public void RemoveFriend(String username, String removeFriendName) {
		// TODO Auto-generated method stub
		
	}
}
