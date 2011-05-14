package jsp;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.forum.items.UserInfo;

public class RegServlet extends HttpServlet {

	private static final long serialVersionUID = 7726888796226117461L;

	private RequestDispatcher _regJsp;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		ServletContext context = config.getServletContext();
		_regJsp = context.getRequestDispatcher("/WEB-INF/jsp/registration.jsp");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		_regJsp.forward(req, resp);
	}
	
}
