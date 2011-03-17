/**
 * 
 */
package network;

import java.rmi.RemoteException;

import domain.ForumController;

/**
 * @author Avi Digmi
 *
 */
public class ForumServerImpl implements ForumServer {

	private static final long serialVersionUID = 2555939371339195609L;

	public ForumServerImpl(ForumController forumController) throws RemoteException {
		// TODO Auto-generated constructor stub
	}
	
	/* (non-Javadoc)
	 * @see network.ForumServer#getInformation(network.Message)
	 */
	@Override
	public Message getInformation(Message whatToGet) throws RemoteException {
		// TODO Auto-generated method stub
		return new LogutMessage();
	}

}
