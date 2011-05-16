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

import common.forum.items.ForumInfo;
import common.forum.items.PostInfo;
import common.forum.items.ThreadInfo;
import common.forum.items.UserInfo;
import domain.WebController;

public class ForumServlet extends HttpServlet {

	private static final long serialVersionUID = -3167997417561262196L;

	private RequestDispatcher _forumJsp;
	private WebController _webController;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		ServletContext context = config.getServletContext();
		_forumJsp = context.getRequestDispatcher("/WEB-INF/jsp/forum.jsp");
		_webController = WebController.getInstance();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Cookie[] cookies = req.getCookies();
		
		String username = "";
		
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("username"))
				username = cookie.getValue();
		}
		
		req.setAttribute("username", username);
		
		// sets friends list
		Vector<UserInfo> friends = _webController.getFriendList(username);
		
		Vector<String> online_friends = new Vector<String>();
		Vector<String> offline_friends = new Vector<String>();
		
		for(UserInfo friend: friends){
			
			if (friend.getStatus().equals("ONLINE"))
				online_friends.add(friend.getUserName());
			
			else
				offline_friends.add(friend.getUserName());
		}

		req.setAttribute("online_friends", online_friends);
		req.setAttribute("offline_friends", offline_friends);
	
		// decide which data should we retrieve from the server (forums\posts\threads)
		String window = (String)req.getParameter("window");
		System.out.println("window " + window);
		if (null == window){
			
			req.setAttribute("window", "forums");
			window = "forums";
		}
		
		if (window.equals("forums")){
			
			Vector<ForumInfo> forumList = _webController.getForumList(username);
			
			req.setAttribute("forums_list", forumList);
		}
		
		else if (window.equals("threads")){
			
			String  forumId = req.getParameter("id");
		    
			Vector<ThreadInfo> threadList =
		    	_webController.getThreadList(username, forumId);
		    
		    req.setAttribute("window", "threads");
		    req.setAttribute("threads_list", threadList);
		}
		
		else if (window.equals("posts")){
			
			String threadId= req.getParameter("id");
		    Vector<PostInfo> postsList = _webController.getPostList(username,threadId);
		    req.setAttribute("window", "posts");
		    req.setAttribute("posts_list", postsList);
		}
		
		_forumJsp.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String username = "";
		
		Cookie[] cookies = req.getCookies();
		
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("username"))
				username = cookie.getValue();
		}
		
		String addFriendName = req.getParameter( "addFriendName" );
		String removeFriendName = req.getParameter( "removeFriendName" );
		
		if (null != addFriendName)
			_webController.AddFriend(username, addFriendName);
			
		else if (null != removeFriendName)
			_webController.RemoveFriend(username, removeFriendName);

		resp.sendRedirect("forum");
	}
}