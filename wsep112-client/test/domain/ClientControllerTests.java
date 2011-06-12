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
		//client.register("Shiran", "Gabay", "gshiran", "Aa1234", "digmia@bgu.ac.il", "true");
	}
	
	@Test
	public void testLogin() {
		assertTrue(client.login("digmiav", "Aa1234"));
	}
	
	@Test
	public void testAddFriend() {
		assertTrue(client.login("digmiav", "Aa1234"));
		assertTrue(client.AddFriend("gshiran"));//we can only test this case only once because its affects the database
		assertFalse(client.AddFriend("gshiran"));
	}
	
	@Test
	public void testsearchFriendsByInput() {
		assertTrue(client.login("digmiav", "Aa1234"));
		assertNotNull(client.searchFriendsByInput("gshiran"));
	}
	
	@Test
	public void testRemoveFriend() {
		assertTrue(client.login("digmiav", "Aa1234"));
		assertTrue(client.RemoveFriend("gshiran"));
	}
	
	@Test
	public void testGetForumsList() {
		assertNotNull(client.getForumsList());
		assertEquals(client.getForumsList().get(0).getName(), "Cooking forum");
	}
	
	@Test
	public void testAddThread() {
		assertTrue(client.login("digmiav", "Aa1234"));
		assertTrue(client.addThread("1", "new test thread", "body"));
		assertTrue(client.getThreadByName("1", "new test thread"));
	}
	
	@Test
	public void testReplyToThread() {
		assertTrue(client.login("digmiav", "Aa1234"));
		//this test is depend on the number of thread in the database , you can change the thread number to 1
		assertTrue(client.replyToThreadTest("1", "replay to thread", "my replay body", "new test thread")); 
		assertTrue(client.getPostByName("new test thread","replay to thread" ));
	}
	
	@Test
	public void testGetThreadsList() {
		assertNotNull(client.getThreadsList("1"));
		//assertEquals(client.getThreadByName("1", "new test thread"));
	}
	
	
	
	
	
	
	
	@Test
	public void testEditPost() {
		assertTrue(client.login("digmiav", "Aa1234"));
		//this test is depend on the number of thread in the database , you can change the thread number to 1
		assertTrue(client.editPost("1", "edit Post", "edit post body", "1", "1")); 
		//assertTrue(client.getPostByName("new test thread","edit Post" ));
	}
	
	
	//@Test
//	public void testGetPostsList() {
	//	assertNotNull(client.getPostsList("2"));
	//	assertEquals(client.getPostsList("2").get(0).get_title(), "edit Post");
//	}
	
	
	
	//@Test
//	public void testRemovePost() {
		//assertTrue(client.login("digmiav", "Aa1234"));
		//this test is depend on the number of thread in the database , you can change the thread number to 1
	//	assertTrue(client.RemovePostByName("1", "edit Post")); 
		//assertFalse(client.getPostByName("1","edit Post" ));
	//}
	
	

	@Test
	public void testRemoveThread() {
		assertTrue(client.login("digmiav", "Aa1234"));
		//this test is depend on the number of thread in the database , you can change the thread number to 1
		//assertTrue(client.RemoveThreadByName("new test thread", "1")); 
	}
	
	
	
	

	
/*	@Test
	public void testChangePass(){
		assertTrue(client.login("digmiav", "Aa1234"));
		assertFalse(client.changePass("1234"));//chaeck why it fails
		assertFalse(client.login("digmiav", "Aa1234"));
	}
	
	@Test
	public void testLogout(){
		assertTrue(client.login("digmiav", "Aa1234"));
		assertTrue(client.logout());//chaeck why it fails
		
	}
*/
	
	@Override
	public void update(Object observable, Object arg) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	
}