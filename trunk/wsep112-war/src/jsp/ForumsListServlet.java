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

import domain.WebController;

public class ForumsListServlet extends HttpServlet{

	private static final long serialVersionUID = 1744399642421914945L;
	
	private RequestDispatcher _forumsListJsp;
	private WebController _webController;
	
	@Override
	public void init(ServletConfig config) throws ServletException {		
		ServletContext context = config.getServletContext();
		_forumsListJsp = context.getRequestDispatcher("/WEB-INF/jsp/forumsList.jsp");
		_webController = WebController.getInstance();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// get the username
		Cookie[] cookies = req.getCookies();
		
		String username = "";
		
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("username"))
				username = cookie.getValue();
		}

		Vector<ForumInfo> forumList = _webController.getForumList(username);

		req.setAttribute("forums_list", forumList);
		
		_forumsListJsp.forward(req, resp);
	}
}
