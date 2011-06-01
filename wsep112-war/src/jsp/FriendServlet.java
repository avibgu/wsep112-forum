package jsp;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.forum.items.UserInfo;
import domain.WebController;

public class FriendServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1744399642421914945L;
	
	private RequestDispatcher _friendsJsp;
	private WebController _webController;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		ServletContext context = config.getServletContext();
		_friendsJsp = context.getRequestDispatcher("/WEB-INF/jsp/friendsList.jsp");
		_webController = WebController.getInstance();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Get the session
		HttpSession session = req.getSession();
		
		// get the username
		Cookie[] cookies = req.getCookies();
		
		String username = "";
		
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("username"))
				username = cookie.getValue();
		}
		
		req.setAttribute("username", username);
		// sets friends list
		
		
		Vector<String> online_friends = new Vector<String>();
		Vector<String> offline_friends = new Vector<String>();
		Vector<String> usersToAdd = new Vector<String>();
		Vector<String> usersToRemove = new Vector<String>();


		String addFriendName = req.getParameter("Addfriend");
		String removeFriendName = req.getParameter( "Removefriend" );
		System.out.println("FRIEND GETTTTTTTT"+ addFriendName);
		System.out.println("add friend name = " + req.getParameter( "addFriendName" ));
		if (null != addFriendName){
			System.out.println("Add friend");
			_webController.AddFriend(username, addFriendName);
			
		}
			
		else if (null != removeFriendName){
			System.out.println("Remove friend");
			_webController.RemoveFriend(username, removeFriendName);
		}

		Vector<UserInfo> friends = _webController.getFriendList(username);
		
		for(UserInfo friend: friends){
			if (friend.getStatus().equals("ONLINE"))
				online_friends.add(friend.getUserName());
			
			else
				offline_friends.add(friend.getUserName());
			usersToRemove.add(friend.getUserName());
		}
		
		Vector<UserInfo> users = _webController.getUsersList(username);
		for(UserInfo user: users){
			if (!user.getUserName().equals(username))
			{
				boolean inFriends = false;
				for(UserInfo friend: friends){
					if (user.getUserName().equals(friend.getUserName())){
						inFriends = true;
					}
				}
				if (!inFriends){
					usersToAdd.add(user.getUserName());
				}
			}
		}
		
		
		req.setAttribute("online_friends", online_friends);
		req.setAttribute("offline_friends", offline_friends);
		req.setAttribute("users_to_add", usersToAdd);
		req.setAttribute("users_to_remove", usersToRemove);
		
		_friendsJsp.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
	}
	
	
}
