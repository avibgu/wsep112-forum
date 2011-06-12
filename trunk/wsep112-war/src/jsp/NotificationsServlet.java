package jsp;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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
		
		// get the username
		Cookie[] cookies = req.getCookies();
		
		String username = "";
		
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("username"))
				username = cookie.getValue();
		}
		
		req.setAttribute("username", username);

		String notification = _webController.getNotificationFromQueue(username);
		
		String audio =	"<audio autoplay=\"autoplay\"> " +
		"<source src=\"http://www.oringz.com/oringz-uploads/31_oringz-pack-nine-15.mp3\" type=\"audio/mpeg\"/>" +
		"</audio>";
		
		req.setAttribute("audio","");
		
		if (null == notification){
			req.setAttribute("notification","");
		}
		else{
			req.setAttribute("notification", notification);
			
			if ( !notification.equals("REFRESH"))
				req.setAttribute("audio", audio);
		}
		
		req.setAttribute("date", new Date().toString());
		
		_notificationJsp.forward(req, resp);
	}
}