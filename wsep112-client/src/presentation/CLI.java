/**
 * 
 */
package presentation;

import domain.ClientController;

/**
 * @author Avi Digmi
 *
 */
public class CLI {

	private ClientController clientController;
	
	public CLI(ClientController clientController) {
		setClientController(clientController);
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
}
