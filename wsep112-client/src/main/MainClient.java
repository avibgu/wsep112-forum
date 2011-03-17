/**
 * 
 */
package main;

import java.rmi.registry.LocateRegistry;

import network.ForumServer;
import network.LoginMessage;
import network.Message;

/**
 * @author Avi Digmi
 *
 */
public class MainClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("Client is Starting..");
		
		if (System.getSecurityManager() == null) {
			
            System.setSecurityManager(new SecurityManager());
        }
		
        try {

        	ForumServer forumServer = (ForumServer) LocateRegistry.getRegistry(args[0]).lookup("ForumServer");
            
            //	TODO: just for test - remove it..
            Message answer = forumServer.getInformation(new LoginMessage());
            
            System.out.println(answer.getMessageType());
            
        }
        catch (Exception e){
        	
            System.err.println("ForumServer exception:");
            e.printStackTrace();
        }
	}

}
