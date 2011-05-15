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

	public Vector<UserInfo> getFriendList() {
		// TODO Auto-generated method stub
		
		Vector<UserInfo> ans = new Vector<UserInfo>();
		
		ans.add(new UserInfo("ONLINE", "Avi"));
		ans.add(new UserInfo("OFFLINE", "Shiran"));
		
		return ans;
	}

	
	public Vector<ForumInfo> getForumList(){
		Vector<ForumInfo> ans = new Vector<ForumInfo>();
		
		ans.add(new ForumInfo("Shiran Forum", 1));
		ans.add(new ForumInfo("Avi Forum", 2));
		
		return ans;
	}
	
	public Vector<ThreadInfo> getThreadList(int forum_id){
		Vector<ThreadInfo> ans = new Vector<ThreadInfo>();
		
		ans.add(new ThreadInfo(17,"Shiran1 Thread", 1));
		ans.add(new ThreadInfo(18,"Shiran2 Thread", 1));
		
		return ans;
	}
	
	public Vector<PostInfo> getPostList(int thread_id){
		Vector<PostInfo> ans = new Vector<PostInfo>();
		
		ans.add(new PostInfo(21, "Shiran post1", "Hello, How r u?", new UserInfo("ONLINE", "gshir"), 17, "1/5/11"));
		//ans.add(new ThreadInfo(18,"Shiran2 Thread", 1));
		
		return ans;
	}

	public void AddFriend(String username, String addFriendName) {
		// TODO Auto-generated method stub
		
	}

	public void RemoveFriend(String username, String removeFriendName) {
		// TODO Auto-generated method stub
		
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
