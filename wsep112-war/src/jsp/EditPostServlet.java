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

import common.forum.items.PostInfo;
import common.forum.items.ThreadInfo;

import domain.WebController;

@WebServlet("/editPost")

public class EditPostServlet extends HttpServlet{

	private static final long serialVersionUID = 6157326032127556463L;
	//private static final long serialVersionUID = -83460053766926996L;
	private RequestDispatcher _editPostJsp;
	private WebController _webController;
	
	@Override
	public void init(ServletConfig config) throws ServletException{
		
		ServletContext context = config.getServletContext();
		_editPostJsp = context.getRequestDispatcher("/WEB-INF/jsp/editPost.jsp");
		
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
		System.out.println("*************** GET TO EDIT SERVLET *************\n");
		System.out.println("username is "+username);
		String numEditPost= req.getParameter("numPostEdit");
		System.out.println("numEditPost "+numEditPost);
		System.out.println("thread id " + (String)session.getAttribute("ThreadId"));
		session.setAttribute("postId", numEditPost);
		 PostInfo post = _webController.getPost(username, (String)session.getAttribute("ThreadId"), numEditPost);
		 if (post==null)
			 System.out.println("nullllllll");
		//String title=post.get_title();
		//System.out.println("title "+title);
		String body=post.get_body();
		System.out.println("body "+body);
		//req.setAttribute("title", title);
		req.setAttribute("body", body);
		_editPostJsp.forward(req, resp);
	}
}
