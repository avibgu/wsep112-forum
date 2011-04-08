/**
 * 
 */
package common.network;

import java.rmi.Remote;
import java.rmi.RemoteException;

import common.network.messages.Message;


/**
 * @author Avi Digmi
 *
 */
public interface ForumServer extends Remote{

	Message getInformation(Message whatToGet) throws RemoteException;
	
	Message setInformation(Message whatToSet) throws RemoteException;
	
	void addObserver(RemoteObserver o) throws RemoteException;
}
