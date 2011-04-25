/**
 * 
 */
package web.server;

import java.rmi.RemoteException;
import java.util.Observable;
import java.util.Observer;

import main.MainClient;
import web.client.WebController;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import domain.ClientController;

/**
 * @author Avi Digmi
 *
 */
public class WebControllerImpl extends RemoteServiceServlet implements
		WebController, Observer {

	private static final long serialVersionUID = -3389019660797950746L;

	private ClientController _clientController;
	
	public WebControllerImpl() {

		while (true){
		
			try {
				
				setClientController(MainClient.connectToRmiServer("127.0.0.1", null));
				
				if (getClientController() != null) break;
			}
			catch (RemoteException e) {
				//TODO: log the error..
			}
		}
		
		getClientController().addObserver(this);
	}
	
	/* (non-Javadoc)
	 * @see web.client.WebController#login(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean login(String username, String password) {
		return getClientController().login(username, password);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
	}
	
	public void setClientController(ClientController clientController) {
		this._clientController = clientController;
	}

	public ClientController getClientController() {
		return _clientController;
	}
}
