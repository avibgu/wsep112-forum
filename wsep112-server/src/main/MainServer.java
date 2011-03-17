/**
 * 
 */
package main;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import domain.ForumController;

import network.ForumServer;
import network.ForumServerImpl;

/**
 * @author Avi Digmi
 *
 */
public class MainServer {

	/**
	 * @param args
	 * @throws MalformedURLException 
	 * @throws RemoteException 
	 */
	public static void main(String[] args) throws RemoteException, MalformedURLException {

		ForumController forumController = new ForumController();
		
		System.out.println("ForumServer Starts..");
		
//		if (System.getSecurityManager() == null){
//			
//            System.setSecurityManager(new SecurityManager());
//        }
		
        try{
            
        	ForumServer server = new ForumServerImpl(forumController);
            
        	ForumServer stub = (ForumServer) UnicastRemoteObject.exportObject(server, 17170);
            
        	LocateRegistry.getRegistry().rebind("ForumServer", stub);
            
            System.out.println("ForumServer bound");
            
        }
        catch (Exception e){
        	
            System.err.println("ForumServer exception:");
            e.printStackTrace();
        }
	}

}
