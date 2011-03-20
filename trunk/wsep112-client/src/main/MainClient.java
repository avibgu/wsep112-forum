/**
 * 
 */
package main;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import presentation.CLI;

import common.network.ForumServer;

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

        	registry = LocateRegistry.getRegistry(args[0]);
        	
        	forumServerStub = (ForumServer) registry.lookup(serverName);
        }
        catch (Exception e){
        	
            System.err.println("ForumServer exception:");
            e.printStackTrace();
        }
        
        ClientController clientController = new ClientController(forumServerStub);
        
        new CLI(clientController).start();
	}
}
