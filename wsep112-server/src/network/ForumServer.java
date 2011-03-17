/**
 * 
 */
package network;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Avi Digmi
 *
 */
public interface ForumServer extends Remote{

	Message getInformation(Message whatToGet) throws RemoteException;
}
