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

public class changePasswordServlet extends HttpServlet {

	private static final long serialVersionUID = -3167997417561262196L;

	private RequestDispatcher _changePassJsp;
	private WebController _webController;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		ServletContext context = config.getServletContext();
		_changePassJsp = context.getRequestDispatcher("/WEB-INF/jsp/changePassword.jsp");
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
		
		req.setAttribute("username", username);
		
		if(req.getParameter("newpass") != null){
			boolean ans = _webController.changePassword(username, req.getParameter( "newpass" ));
			if (!ans){
				String error = _webController.getErrorFromQueue(username);
				
				if (null != error)
					req.setAttribute("error", error);
				else
					req.setAttribute("error", "Password too weak");
			}
			else{
				req.setAttribute("error", "Password has been changed");
			}
				
		}
		else{
			String error = (String) req.getAttribute("error");
			
			if (null == error)
				req.setAttribute("error", "");
		}
		_changePassJsp.forward(req, resp);
	}
}
