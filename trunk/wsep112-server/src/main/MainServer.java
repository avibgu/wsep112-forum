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

import common.network.ForumServer;

import server.network.ForumServerImpl;

import domain.ForumController;


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
		
		//	TODO: change to log..
		System.out.println("ForumServer Starts..");
		
		if (System.getSecurityManager() == null){
			
			System.setSecurityManager(new RMISecurityManager());
		}
		
		String name = "ForumServer";
		
		try{

			ForumServer server = new ForumServerImpl(forumController);
            
			ForumServer stub = (ForumServer) UnicastRemoteObject.exportObject(server, 0);
            
			Registry registry = LocateRegistry.getRegistry();
        	
			registry.rebind(name, stub);
            
			//	TODO: change to log..
			System.out.println(name + " bound");
		}
		catch (Exception e){
        	
			System.err.println("ForumServer exception:");
			e.printStackTrace();
		}
	}
}
