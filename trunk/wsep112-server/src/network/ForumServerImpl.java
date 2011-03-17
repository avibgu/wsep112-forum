/**
 * 
 */
package network;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author Avi Digmi
 *
 */
public class ForumServerImpl extends UnicastRemoteObject implements ForumServer {

	private static final long serialVersionUID = 2555939371339195609L;

	public ForumServerImpl() throws RemoteException {
		// TODO Auto-generated constructor stub
	}
	
	/* (non-Javadoc)
	 * @see network.ForumServer#getInformation(network.Message)
	 */
	@Override
	public Message getInformation(Message whatToGet) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
