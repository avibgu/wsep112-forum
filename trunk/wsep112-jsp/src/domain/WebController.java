package domain;

import java.util.Vector;

import common.forum.items.UserInfo;

/**
 * Web Controller Singleton..
 */
public class WebController {

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

	public Vector<UserInfo> getFriendList() {
		// TODO Auto-generated method stub
		return null;
	}

	public void login(String username, String password) {
		// TODO Auto-generated method stub
		
	}
}
