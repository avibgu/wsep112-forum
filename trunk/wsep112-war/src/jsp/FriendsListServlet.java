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

public class FriendsListServlet extends HttpServlet {

	private static final long serialVersionUID = -2615064375944529358L;

	private RequestDispatcher _friendsListJsp;
	private WebController _webController;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		ServletContext context = config.getServletContext();
		_friendsListJsp = context.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		_webController = WebController.getInstance();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Vector<UserInfo> friends = _webController.getFriendList();
		req.setAttribute("friends", friends);

		_friendsListJsp.forward(req, resp);
	}
}
