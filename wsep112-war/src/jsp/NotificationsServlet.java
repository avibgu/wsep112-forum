package jsp;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.WebController;

public class NotificationsServlet extends HttpServlet {

	private static final long serialVersionUID = -7121723042024482446L;

	private RequestDispatcher _notificationJsp;
	private WebController _webController;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		ServletContext context = config.getServletContext();
		_notificationJsp = context.getRequestDispatcher("/WEB-INF/jsp/notification.jsp");
		_webController = WebController.getInstance();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setAttribute("notification", new Date().toString());
		
		_notificationJsp.forward(req, resp);
	}
}