/**
 * 
 */
package presentation;

import java.util.logging.Logger;

import domain.ClientController;

/**
 * @author Avi Digmi
 *
 */
public class CLI {

	private ClientController clientController;
	private Logger logger;
	
	public CLI(ClientController clientController, Logger logger) {
		
		setClientController(clientController);
		setLogger(logger);
	}

	/**
	 * This is the main method of the cli.
	 * Should while forever, which do:
	 * 1. shows options to user,
	 * 2. get his input,
	 * 3. redirect the quries to clientController.
	 */
	public void start() {
		// TODO Auto-generated method stub
	}

	public void setClientController(ClientController clientController) {
		this.clientController = clientController;
	}

	public ClientController getClientController() {
		return clientController;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public Logger getLogger() {
		return logger;
	}
	
	public void log(String msg){
		getLogger().info(msg);
	}
}
