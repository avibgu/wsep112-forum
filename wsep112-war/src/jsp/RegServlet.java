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
import domain.WebController;

public class RegServlet extends HttpServlet {

	private static final long serialVersionUID = 7726888796226117461L;

	private RequestDispatcher _regJsp;
	private WebController _webController;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		ServletContext context = config.getServletContext();
		_regJsp = context.getRequestDispatcher("/WEB-INF/jsp/registration.jsp");
		_webController = WebController.getInstance();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String error = (String) req.getAttribute("error");
		
		if (null == error)
			req.setAttribute("error", "");
		
		_regJsp.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException{
		
		String firstName = req.getParameter( "firstName" );
		String lastName = req.getParameter( "lastName" );
		String username = req.getParameter( "username" );
		String password = req.getParameter( "password" );
		String email = req.getParameter( "email" );
		
		System.out.println(firstName + "," + lastName + "," + username
				 + "," + password + "," + email);
		
		boolean ans = _webController.register(firstName, lastName,
				username, password, email);
		
		if(ans)
			resp.sendRedirect("forum");
		
		else{
		
			req.setAttribute("error", "ERROR - Enter Details again..");
			_regJsp.forward(req, resp);
		}
	}
	
}
