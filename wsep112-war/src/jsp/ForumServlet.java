package jsp;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		// check for notifications
		String notification = _webController.getNotificationFromQueue(username);
		req.setAttribute("notification", notification);
		
		// sets friends list
		Vector<UserInfo> friends = _webController.getFriendList(username);
		
		Vector<String> online_friends = new Vector<String>();
		Vector<String> offline_friends = new Vector<String>();
		Vector<String> usersToAdd = new Vector<String>();
		Vector<String> usersToRemove = new Vector<String>();

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

/*
	
		// decide which data should we retrieve from the server (forums\posts\threads)
		String window = (String)req.getParameter("window");
		if (null == window){
			
			req.setAttribute("window", "forums");
			window = "forums";
		}
		if (req.getParameter("AddThreadButton") != null){
			req.setAttribute("window", "addThread");
			
		}
		else if (req.getParameter("AddPostButton") != null){
			req.setAttribute("window", "addPost");
		}
		else if (window.equals("edit")){
			String postId = req.getParameter("postId");
		    session.setAttribute("postId", postId);
		    PostInfo post = _webController.getPost(username, (String)session.getAttribute("ThreadId"), postId);
			req.setAttribute("title", post.get_title());
			req.setAttribute("body", post.get_body());
			req.setAttribute("window", "editPost");
		}
		else if (window.equals("delete")){
			String threadId = (String)session.getAttribute("ThreadId");
			_webController.deletePost(username, threadId, req.getParameter("postId"));
			session.setAttribute("postId", req.getParameter("postId"));
			Vector<PostInfo> postsList = _webController.getPostList(username,threadId);
			req.setAttribute("posts_list", postsList);
			req.setAttribute("window", "posts");
		}
		else if (window.equals("forums")){
			
			System.out.println("Forums old");
			
			Vector<ForumInfo> forumList = _webController.getForumList(username);
			
			req.setAttribute("forums_list", forumList);
		}
		else if (window.equals("threads")){
			
			String forumId = req.getParameter("id");
			session.setAttribute("ForumId", forumId);
		    Vector<ThreadInfo> threadList =
		    	_webController.getThreadList(username, forumId);
		    req.setAttribute("window", "threads");
		    req.setAttribute("threads_list", threadList);
		}
		
		else if (window.equals("posts")){
			String threadId= req.getParameter("id");
			session.setAttribute("ThreadId", threadId);
		    Vector<PostInfo> postsList = _webController.getPostList(username,threadId);
		    req.setAttribute("window", "posts");
		    req.setAttribute("posts_list", postsList);
		    req.setAttribute("username", username);
		}
		
	*/
		_forumJsp.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String username = "";
		HttpSession session = req.getSession();
		String forwardTo="forum";
		
		Cookie[] cookies = req.getCookies();
		
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("username"))
				username = cookie.getValue();
		}
		String addFriendName = req.getParameter( "addFriendName" );
		String removeFriendName = req.getParameter( "removeFriendName" );
		String addedThread = req.getParameter("FillThreadDetails");
		
		if (null != addFriendName)
			_webController.AddFriend(username, addFriendName);
			
		else if (null != removeFriendName)
			_webController.RemoveFriend(username, removeFriendName);
		
		else if (null != addedThread){
			String title = req.getParameter("title");
			String body = req.getParameter("body");
			_webController.addThread(username, (String) session.getAttribute("ForumId"),title, body);
			forwardTo ="forum?id="+(String) session.getAttribute("ForumId")+"&window=threads";
		}
		else if (null != req.getParameter("FillPostDetails")){
			String title = req.getParameter("title");
			String body = req.getParameter("body");
			_webController.addPost(username, (String)session.getAttribute("ForumId"), title, body, 
								  (String)session.getAttribute("ThreadId"));
			forwardTo = "forum?id="+(String)session.getAttribute("ThreadId")+"&window=posts";
		}
		else if (null != req.getParameter("FillEditPostDetails")){
			System.out.println("Post - edit post");
			String title = req.getParameter("title");
			String body = req.getParameter("body");
			_webController.editPost(username, (String)session.getAttribute("ForumId"), title, body, 
								  (String)session.getAttribute("ThreadId"),(String)session.getAttribute("postId"));
			forwardTo = "forum?id="+(String)session.getAttribute("ThreadId")+"&window=posts";
		}
		
		resp.sendRedirect(forwardTo);
	}
}
