package jsp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		String addedThread = req.getParameter("FillThreadDetails");

		if (null != addedThread){
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
			String title = req.getParameter("title");
			String body = req.getParameter("body");
			_webController.editPost(username, (String)session.getAttribute("ForumId"), title, body, 
								  (String)session.getAttribute("ThreadId"),(String)session.getAttribute("postId"));
			forwardTo = "forum?id="+(String)session.getAttribute("ThreadId")+"&window=posts";
		}
		
		resp.sendRedirect(forwardTo);
	}
}
