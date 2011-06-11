/**
 * 
 */
package domain;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import common.logging.VerySimpleLogFormatter;
import common.network.ForumServer;
import common.network.messages.AddFriendMessage;
import common.network.messages.LoginMessage;
import common.network.messages.LogoutMessage;
import common.network.messages.Message;
import common.network.messages.MessageType;
import common.network.messages.OKMessage;
import common.network.messages.RegMessage;
import common.network.messages.RemoveFriendMessage;
import common.network.messages.AddThreadMessage;
import common.network.messages.AddPostMessage;
import common.network.messages.SeeForumThreadsMessage;
import common.network.messages.SeeForumsListMessage;
import common.network.messages.SeeThreadPostsMessage;
import common.observation.RemoteObserver;


/**
 * @author Avi Digmi
 *
 */
public class ClientControllerTests implements RemoteObserver, Serializable{

	private static final long serialVersionUID = 7654546365982223288L;

	private ForumServer forumServerStub = null;
	private ClientController client;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
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
		
		String serverName = "ForumServer";
		
		Registry registry  = null;

		while(true){
			
		    try {
		    	registry = LocateRegistry.getRegistry("127.0.0.1");
		    	forumServerStub = (ForumServer) registry.lookup(serverName);
		    	break;
		    }
		    catch (Exception e){
		    	System.err.println("ForumServer exception:");
		    }
		}
        client =  new ClientController(forumServerStub, logger);
        //System.out.println(client.getFriendList().size());
	}

	/**
	 * Test method for {@link domain.ClientController#ClientController(common.network.ForumServer)}.
	 */
	@Test
	public void testClientController() {

		try {
			assertNotNull(forumServerStub.getInformation(new OKMessage()));
			assertNotNull(forumServerStub.setInformation(new OKMessage()));
		}
		catch (RemoteException e) { e.printStackTrace(); }
	}
	
	/**
	 * Test method for {@link domain.ClientController#register(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws RemoteException 
	 */
	@Test
	public void testRegister(){
		//assertTrue(client.register("Avi", "Digmi", "digmiav", "Aa1234", "digmia@bgu.ac.il", "true"));
	}
	
	public void testLogin() {
		assertTrue(client.login("digmiav", "Aa1234"));
	}
	
	public void testAddFriend() {
		client.register("Shiran", "Gabay", "gshiran", "Aa1234", "digmia@bgu.ac.il", "true");
		assertTrue(client.AddFriend("gshiran"));
		assertFalse(client.AddFriend("gshiran"));
	}
	
	public void testGetForumsList() {
		assertNotNull(client.getForumsList());
		assertEquals(client.getForumsList().get(0).getName(), "Cooking forum");
	}

	@Override
	public void update(Object observable, Object arg) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
}