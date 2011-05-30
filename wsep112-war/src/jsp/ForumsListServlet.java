package jsp;

import java.io.IOException;
import java.util.Date;
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

import common.forum.items.ForumInfo;

import domain.WebController;

public class ForumsListServlet extends HttpServlet{
	private static final long serialVersionUID = -7121723042024482446L;

	private RequestDispatcher _ForumsListJsp;
	private WebController _webController;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		ServletContext context = config.getServletContext();
		_ForumsListJsp = context.getRequestDispatcher("/WEB-INF/jsp/fourmsList.jsp");
		_webController = WebController.getInstance();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		/*
		// get the username
		Cookie[] cookies = req.getCookies();
		
		String username = "";
		
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("username"))
				username = cookie.getValue();
		}
		
		req.setAttribute("username", username);

		String notification = _webController.getNotificationFromQueue(username);
		
		if (null == notification)
			req.setAttribute("notification","");
		else
			req.setAttribute("notification", notification);
		
		req.setAttribute("date", new Date().toString());
		*/
		// decide which data should we retrieve from the server (forums\posts\threads)
		
		// Get the session
	//	HttpSession session = req.getSession();
		
		// get the username
		Cookie[] cookies = req.getCookies();
		
		String username = "";
		
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("username"))
				username = cookie.getValue();
		}
		
		req.setAttribute("username", username);
		
		
		String window = (String)req.getParameter("window");
		if (null == window){
			
			req.setAttribute("window", "forums");
			window = "forums";
		}
		if (window.equals("forums")){
			
			//System.out.println("Forums");
			
			Vector<ForumInfo> forumList = _webController.getForumList(username);
			System.out.println(forumList.size());
			req.setAttribute("forums_list", forumList);
		}
		
		System.out.println(window);
		_ForumsListJsp.forward(req, resp);
	}
}
