/**
 * 
 */
package main;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import network.ForumServer;
import network.messages.LoginMessage;

/**
 * @author Avi Digmi
 *
 */
public class MainClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//	TODO: change to log..
		System.out.println("Client is Starting..");
		
		//	creating security policy manager
		if (System.getSecurityManager() == null) {
			
            System.setSecurityManager(new SecurityManager());
        }
		
		String serverName = "ForumServer";
		
        try {

        	//	TODO: get the real ip of the server from the command line..
        	Registry registry = LocateRegistry.getRegistry("192.168.56.1");
        	
        	ForumServer forumServerStub = (ForumServer) registry.lookup(serverName);
            
            //	TODO: just for test - remove it..            
            System.out.println(forumServerStub.getInformation(new LoginMessage()).getMessageType());
        }
        catch (Exception e){
        	
            System.err.println("ForumServer exception:");
            e.printStackTrace();
        }
	}

}
