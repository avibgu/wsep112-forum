/**
 * 
 */
package network;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

/**
 * @author Avi Digmi
 *
 */
public class NetController {
	
	private ForumServer server;

	public NetController() throws RemoteException, MalformedURLException {

		setServer(new ForumServerImpl());
		
		//	the naming server will be in port 2002
		Naming.rebind("//127.0.0.1:2002/Forum", getServer());
		
		System.out.println("ForumServer is ready..");
	}
	
	public ForumServer getServer() {
		return server;
	}

	public void setServer(ForumServer server) {
		this.server = server;
	}
}
