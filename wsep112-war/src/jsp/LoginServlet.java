package jsp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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

		Cookie[] cookies = req.getCookies();
		
		String username = "";
		
		if (null != cookies){
			
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("username"))
					username = cookie.getValue();
			}
		}
		
		String error = (String) req.getAttribute("error");
		
		if (null == error)
			req.setAttribute("error", "");
		
		String logout = (String) req.getParameter("logoutButton");
		
		if (null != logout){
			
			System.out.println("n\n\n\n\n\n" + logout + "\n\n\n\n");
			_webController.logout(username);
		}
			
		
		_loginJsp.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException{
		
		String username = req.getParameter( "username" );
		String password = req.getParameter( "password" );
		
		boolean ans = _webController.login(username, password);	
		
		if(ans){
			
			resp.addCookie(new Cookie("username", username));
			resp.sendRedirect("forum");
		}
		else{
		
			req.setAttribute("error", "Wrong Username or Password");
			_loginJsp.forward(req, resp);
		}
	}
}