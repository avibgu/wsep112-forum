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
		
//		if (System.getSecurityManager() == null) {
//			
//            System.setSecurityManager(new SecurityManager());
//        }
		
        try {

        	//	TODO: get the real ip of the server from the command line..
        	ForumServer forumServerStub = (ForumServer) LocateRegistry.getRegistry("127.0.0.1").lookup("ForumServer");
            
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
