package jsp;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.forum.items.ThreadInfo;

import domain.WebController;

/**
 * Servlet implementation class ThreadsListServlet
 */
@WebServlet("/ThreadsListServlet")
public class ThreadsListServlet extends HttpServlet {

	private static final long serialVersionUID = -8203970789782134483L;

	private RequestDispatcher _threadsListJsp;
	private WebController _webController;
	
	@Override
	public void init(ServletConfig config) throws ServletException{
		ServletContext context = config.getServletContext();
		_threadsListJsp = context.getRequestDispatcher("/WEB-INF/jsp/threadsList.jsp");
		_webController = WebController.getInstance();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Get the session
		HttpSession session = req.getSession();
		
		// get the username
		Cookie[] cookies = req.getCookies();
		
		String username = "";
		
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("username"))
				username = cookie.getValue();
		}
		String forumId ="";
		String addedThread = req.getParameter("idOfForum");
		System.out.println("Added: "+addedThread);
		if (addedThread!=null){
			forumId = req.getParameter("idOfForum");
			String title = req.getParameter("title");
			String body = req.getParameter("body");
			_webController.addThread(username, forumId,title, body);
		}
		else{
			forumId = req.getParameter("id");
			System.out.println("parameter = "+forumId);
			session.setAttribute("ForumId", forumId);
		}
	    Vector<ThreadInfo> threadList =
	    	_webController.getThreadList(username, forumId);
		
	    req.setAttribute("threads_list", threadList);
		_threadsListJsp.forward(req, resp);
	}
}
