/**
 * 
 */
package main;

import java.io.IOException;

import java.net.MalformedURLException;

import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import common.logging.VerySimpleLogFormatter;
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
		
		// creating logger called ServerLog
		Logger logger = Logger.getLogger("ServerLog");
		
		// creating log file
		Handler logFileHandler = null;
		
		int tError = 10;
		
		while(true){
			
			try{
				
				logFileHandler = new FileHandler("server.log");
				
				break;
			}
			catch(IOException e){
				
		    	if (tError == 10)
		    		System.err.println("unable to open file for logging, will try again..");

		    	else if (tError == 0){
		    		
		    		System.err.println("giving up.. exiting..");
		    		return;
		    	}
		    	
	    		tError--;
			}
		}
		
		logFileHandler.setFormatter(new VerySimpleLogFormatter() );

		// logger output is written to a file in logFileHandler handler - server.log
	    logger.addHandler(logFileHandler);

		// Set the log level specifying which message levels will be logged by this logger
	    logger.setLevel(Level.INFO);

		ForumController forumController = new ForumController(logger);
		
		logger.info("ForumServer Starts..");

		if (System.getSecurityManager() == null){
			
			System.setSecurityManager(new RMISecurityManager());
		}
		
		String name = "ForumServer";
		
		tError = 10;
		
		while(true){

			try{
		
				ForumServer server = new ForumServerImpl(forumController, logger);
		        
				ForumServer stub = (ForumServer) UnicastRemoteObject.exportObject(server, 0);
		        
				Registry registry = LocateRegistry.getRegistry();
		    	
				registry.rebind(name, stub);
		        
				logger.info(name + " bound");
				
				break;
			}
			catch (Exception e){
		    	
				if (tError == 10)
					logger.severe("ForumServer exception: cannot connect to the server, will try again..");

		    	else if (tError == 0){
		    		
		    		logger.severe("giving up.. exiting..");
		    		return;
		    	}
		    	
	    		tError--;
			}
		}
	}
}
