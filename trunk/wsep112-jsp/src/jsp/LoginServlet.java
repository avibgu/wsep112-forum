package jsp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.WebController;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -3167997417561262196L;

	private RequestDispatcher _loginJsp;
	private WebController _webController;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		ServletContext context = config.getServletContext();
		_loginJsp = context.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		_webController = WebController.getInstance();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		_loginJsp.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException{
		
		String username = req.getParameter( "username" );
		String password = req.getParameter( "password" );
		
		boolean ans = _webController.login(username, password);

		if(ans)
			resp.sendRedirect("forum");
		
		else
			_loginJsp.forward(req, resp);
		
		/*
		// Check if cancel button was pressed.
		String cancelButton = req.getParameter("cancel-button");
		
		if (cancelButton != null){

			resp.sendRedirect("list-news-items");
			return;
		}
		
		Map<String, String> errors = EditNewsItemServlet.validate(req);
		
		if (!errors.isEmpty()){

			jsp.forward(req, resp);
			return;
		}

		NewsItem newsItem = (NewsItem) req.getAttribute("newsItem");
		new NewsItemDAO().create(newsItem);
		
		resp.sendRedirect("view-news-item?id=" + newsItem.getId());
		*/
	}
}