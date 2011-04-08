/**
 * 
 */
package main;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import presentation.cli.CLI;

import common.logging.VerySimpleLogFormatter;
import common.network.ForumServer;

import domain.ClientController;

/**
 * @author Avi Digmi
 *
 */
public class MainClient {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		// creating logger called ClientLog 
		Logger logger = Logger.getLogger("ClientLog");
		
		// creating log file
		Handler logFileHandler = null;
		
		int tError = 10;
		
		while(true){
			
			try{
				
				logFileHandler = new FileHandler("client.log");
				
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
		
		logFileHandler.setFormatter(new VerySimpleLogFormatter());

		// logger output is written to a file in logFileHandler handler - client.log
	    logger.addHandler(logFileHandler);

		// Set the log level specifying which message levels will be logged by this logger
	    logger.setLevel(Level.INFO);

	    logger.info("Client is Starting..");
		
		//	creating security policy manager
		if (System.getSecurityManager() == null) {
			
			System.setSecurityManager(new SecurityManager());
		}
		
		String serverName = "ForumServer";
		
		Registry registry  = null;
		
		ForumServer forumServerStub = null;
		
		tError = 10;

		while(true){
			
		    try {
		
		    	registry = LocateRegistry.getRegistry(args[0]);
		    	
		    	forumServerStub = (ForumServer) registry.lookup(serverName);
		    	
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
        
        ClientController clientController = new ClientController(forumServerStub, logger);
        
        new CLI(clientController, logger).start();
	}
}
