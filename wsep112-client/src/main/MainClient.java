/**
 * 
 */
package main;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

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

        	String name = "ForumServer";
        	
        	//	TODO: get the real ip of the server from the command line..
        	Registry registry = LocateRegistry.getRegistry("192.168.56.1");
        	
        	ForumServer forumServerStub = (ForumServer) registry.lookup(name);
            
            //	TODO: just for test - remove it..
            Message answer = forumServerStub.getInformation(new LoginMessage());
            
            System.out.println(answer.getMessageType());
            
        }
        catch (Exception e){
        	
            System.err.println("ForumServer exception:");
            e.printStackTrace();
        }
	}

}
