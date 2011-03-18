/**
 * 
 */
package main;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import presentation.CLI;

import common.network.ForumServer;
import common.network.messages.LoginMessage;
import domain.ClientController;

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
		
		Registry registry  = null;
		
		ForumServer forumServerStub = null;
		
        try {

        	//	TODO: get the real ip of the server from the command line..
        	registry = LocateRegistry.getRegistry("192.168.56.1");
        	
        	forumServerStub = (ForumServer) registry.lookup(serverName);
        }
        catch (Exception e){
        	
            System.err.println("ForumServer exception:");
            e.printStackTrace();
        }
        
        ClientController clientController = new ClientController(forumServerStub);
        
        new CLI(clientController).start();
        
        
        //	TODO: just for test - remove it..            
        try {
			System.out.println(forumServerStub.getInformation(new LoginMessage()).getMessageType());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
