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

@WebServlet("/EditPostServlet")

public class EditPostServlet extends HttpServlet{

	private static final long serialVersionUID = 6457326032127556463L;
	//private static final long serialVersionUID = -83460053766926996L;
	private RequestDispatcher _ediePostJsp;
	private WebController _webController;
	
	@Override
	public void init(ServletConfig config) throws ServletException{
		
		ServletContext context = config.getServletContext();
		_ediePostJsp = context.getRequestDispatcher("/WEB-INF/jsp/editPost.jsp");
		
		_webController = WebController.getInstance();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("*************** GET TO EDIT SERVLET *************\n");
		String title= req.getParameter("title");
		String body=req.getParameter("body");
		req.setAttribute("title", title);
		req.setAttribute("body", body);
		_ediePostJsp.forward(req, resp);
	}
}
