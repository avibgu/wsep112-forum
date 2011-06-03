
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
import common.forum.items.PostInfo;

import domain.WebController;

public class PostsListServlet extends HttpServlet{

	private static final long serialVersionUID = 1744399642421914945L;
	
	private RequestDispatcher _postsListJsp;
	private WebController _webController;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		ServletContext context = config.getServletContext();
		_postsListJsp = context.getRequestDispatcher("/WEB-INF/jsp/postsList.jsp");
		_webController = WebController.getInstance();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Get the session
		System.out.println("***********pOST List Servlet");
		HttpSession session = req.getSession();
		
		// get the username
		Cookie[] cookies = req.getCookies();
		
		String username = "";
		String threadId="";
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("username"))
				username = cookie.getValue();
		}
		
		String id = req.getParameter("deletePostId");
		System.out.println("delete = " + id);
		if (id != null){
			threadId = session.getAttribute("ThreadId").toString();
			_webController.deletePost(username, threadId,id);
		}
		String editThread = req.getParameter("idThread");
		System.out.println("FLAG IS "+editThread);
		if (editThread != null){
			System.out.println("Here");
			threadId = req.getParameter("idThreadEdit");
			session.setAttribute("ThreadId", threadId);
			System.out.println("thread id "+threadId);
			String title = req.getParameter("title");
			System.out.println("title "+title);
			String body = req.getParameter("body");
			System.out.println("body "+body);
			_webController.editPost(username, (String)session.getAttribute("ForumId"), title, body, 
					session.getAttribute("ThreadId").toString(),(String)session.getAttribute("postId"));
		}
		else{
			System.out.println("Here1");
			threadId = req.getParameter("id");
			System.out.println("threadid = " + threadId);
			session.setAttribute("ThreadId", threadId);
		}
	    Vector<PostInfo> postsList = _webController.getPostList(username,threadId);
/*	    if (postsList.size() == 0)
	    	_webController.deleteThread(username, forumID, threadId)*/
	    req.setAttribute("posts_list", postsList);
	    req.setAttribute("username", username);
		
	    
	    _postsListJsp.forward(req, resp);
	}
}
