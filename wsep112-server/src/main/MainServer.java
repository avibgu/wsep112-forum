/**
 * 
 */
package main;

import java.net.MalformedURLException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
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
		
		if (System.getSecurityManager() == null){
			
            System.setSecurityManager(new RMISecurityManager());
        }
		
        try{
            
        	String name = "ForumServer";
        		
        	ForumServer server = new ForumServerImpl(forumController);
            
        	ForumServer stub = (ForumServer) UnicastRemoteObject.exportObject(server, 0);
            
        	Registry registry = LocateRegistry.getRegistry();
        	
        	registry.rebind(name, stub);
            
            System.out.println(name + " bound");
            
        }
        catch (Exception e){
        	
            System.err.println("ForumServer exception:");
            e.printStackTrace();
        }
	}

}
