/**
 * 
 */
package server.network;

import java.rmi.RemoteException;
import java.rmi.server.RemoteStub;

import common.network.ForumServer;
import common.network.messages.LogutMessage;
import common.network.messages.Message;


import domain.ForumController;

/**
 * @author Avi Digmi
 *
 */
public class ForumServerImpl extends RemoteStub implements ForumServer {

	private static final long serialVersionUID = 2555939371339195609L;

	public ForumServerImpl(ForumController forumController) throws RemoteException {
		// TODO Auto-generated constructor stub
		super();
	}
	
	/* (non-Javadoc)
	 * @see network.ForumServer#getInformation(network.Message)
	 */
	@Override
	public Message getInformation(Message whatToGet) throws RemoteException {
		// TODO Auto-generated method stub
		return new LogutMessage();
	}

	@Override
	public void SetInformation(Message whatToSet) throws RemoteException {
		// TODO Auto-generated method stub
	}
}
