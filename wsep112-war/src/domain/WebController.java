package domain;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import main.MainClient;

import common.forum.items.ForumInfo;
import common.forum.items.PostInfo;
import common.forum.items.ThreadInfo;
import common.forum.items.UserInfo;

/**
 * Web Controller Singleton..
 */
public class WebController implements Observer{

	private HashMap<String, ClientController> _usersControllersMap;	
	
	// Private constructor prevents instantiation from other classes
	private WebController() {
		
		setUsersControllersMap(new HashMap<String, ClientController>());
	}
	
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
		
		ClientController cc = getClientController(username);
		
		return cc.login(username, password);
	}
	
	public boolean register(String firstName, String lastName, String username,
			String password, String email) {
		
		ClientController cc = getClientController(username);
		
		return cc.register(firstName, lastName, username, password, email);
	}
	
	public void logout(String username) {

		ClientController cc = getClientController(username);
		
		cc.logout();
	}

	public Vector<UserInfo> getFriendList(String username) {

		ClientController cc = getClientController(username);
		
		return cc.getFriendList();
	}

	
	public Vector<ForumInfo> getForumList(String username){
		
		ClientController cc = getClientController(username);
		
		return cc.getForumsList();
	}
	
	public Vector<ThreadInfo> getThreadList(String username, String forum_id){

		ClientController cc = getClientController(username);
		
		return cc.getThreadsList(forum_id);
	}
	
	public Vector<PostInfo> getPostList(String username,String thread_id){
		
		ClientController cc = getClientController(username);
		
		return cc.getPostsList(thread_id);
	}

	public boolean AddFriend(String username, String addFriendName) {

		ClientController cc = getClientController(username);
		
		return cc.AddFriend(addFriendName);
	}

	public boolean RemoveFriend(String username, String removeFriendName) {
		
		ClientController cc = getClientController(username);
		
		return cc.RemoveFriend(removeFriendName);
	}

	public ClientController getClientController(String username) {

		ClientController cc = getUsersControllersMap().get(username);
		
		if (cc == null){
			
			while (true){
				
				try {
					
					cc = MainClient.connectToRmiServer("127.0.0.1", null);
					
					if (cc != null){
						
						getUsersControllersMap().put(username, cc);
						
						cc.addObserver(this);
						
						break;
					}
				}
				catch (RemoteException e) {}
			}
		}

		return cc;
	}
		
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	public void setUsersControllersMap(HashMap<String, ClientController> _usersControllersMap) {
		this._usersControllersMap = _usersControllersMap;
	}

	public HashMap<String, ClientController> getUsersControllersMap() {
		return _usersControllersMap;
	}
}
