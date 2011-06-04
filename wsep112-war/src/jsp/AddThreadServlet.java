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
public class AddThreadServlet extends HttpServlet {

	private static final long serialVersionUID = -8203970789782134483L;

	private RequestDispatcher _addThreadJsp;
	private WebController _webController;
	
	@Override
	public void init(ServletConfig config) throws ServletException{
		
		ServletContext context = config.getServletContext();
		_addThreadJsp = context.getRequestDispatcher("/WEB-INF/jsp/addThread.jsp");
		
		_webController = WebController.getInstance();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		_addThreadJsp.forward(req, resp);
	}
}
