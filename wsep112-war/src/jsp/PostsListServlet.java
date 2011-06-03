
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
		if (editThread != null){
			threadId = req.getParameter("idThread");
			session.setAttribute("ThreadId", threadId);
			String title = req.getParameter("title");
			String body = req.getParameter("body");
			String postId = (String)session.getAttribute("postId");
			String forumId =(String)session.getAttribute("ForumId");
			//get the right post by his place in array (postId)
			PostInfo post = _webController.getPostByLocation(username,threadId,postId);

			_webController.editPost(username, forumId , title, body, threadId, String.valueOf(post.get_post_id()));
		}
		else{
			threadId = req.getParameter("id");
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
