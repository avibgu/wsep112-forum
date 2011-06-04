package jsp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.WebController;

/**
 * Servlet implementation class ThreadsListServlet
 */
public class AddPostServlet extends HttpServlet {

	private static final long serialVersionUID = -7210511051821948978L;
	
	private RequestDispatcher _addPostJsp;
	private WebController _webController;
	
	@Override
	public void init(ServletConfig config) throws ServletException{
		
		ServletContext context = config.getServletContext();
		_addPostJsp = context.getRequestDispatcher("/WEB-INF/jsp/addPost.jsp");
		_webController = WebController.getInstance();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		_addPostJsp.forward(req, resp);
	}
}
