/**
 * 
 */
package domain;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import common.network.ForumServer;
import common.network.messages.AddFriendMessage;
import common.network.messages.LoginMessage;
import common.network.messages.LogoutMessage;
import common.network.messages.MessageType;
import common.network.messages.OKMessage;
import common.network.messages.RegMessage;

/**
 * @author Avi Digmi
 *
 */
public class ClientControllerTests {

	private ForumServer forumServerStub;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		String serverName = "ForumServer";
		
		Registry registry  = null;
		
        try {

        	registry = LocateRegistry.getRegistry("127.0.0.1");
        	
        	forumServerStub = (ForumServer) registry.lookup(serverName);
        }
        catch (Exception e){
        	
            System.err.println("ForumServer exception:");
            e.printStackTrace();
        }
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
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
	 */
	@Test
	public void testRegister() {
		
		RegMessage rm = new RegMessage("Avi", "Digmi", "digmia", "Aa1234", "digmia@bgu.ac.il");
		
		try {

			assertEquals(MessageType.OK, forumServerStub.setInformation(rm).getMessageType());
		}
		catch (RemoteException e) { e.printStackTrace(); }
	}

	/**
	 * Test method for {@link domain.ClientController#login(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testLogin() {

		RegMessage regMessage = new RegMessage("Shiran", "Gabay", "gshir", "Aa1234", "gshir@bgu.ac.il");
		LoginMessage loginMessage = new LoginMessage("gshir", "Aa1234");
		
		try {

			assertEquals(MessageType.OK, forumServerStub.setInformation(regMessage).getMessageType());
			assertEquals(MessageType.OK, forumServerStub.setInformation(loginMessage).getMessageType());
		}
		catch (RemoteException e) { e.printStackTrace(); }
	}

	/**
	 * Test method for {@link domain.ClientController#logout(java.lang.String)}.
	 */
	@Test
	public void testLogout() {

		RegMessage regMessage = new RegMessage("Miri", "Peretz", "miripe", "Miri1234", "miripe@bgu.ac.il");
		LoginMessage loginMessage = new LoginMessage("miripe", "Miri1234");
		LogoutMessage logoutMessage = new LogoutMessage("miripe");
		
		try {
			
			assertEquals(MessageType.OK, forumServerStub.setInformation(regMessage).getMessageType());
			assertEquals(MessageType.OK, forumServerStub.setInformation(loginMessage).getMessageType());
			assertEquals(MessageType.OK, forumServerStub.setInformation(logoutMessage).getMessageType());
		}
		catch (RemoteException e) { e.printStackTrace(); }
	}

	/**
	 * Test method for {@link domain.ClientController#AddFriend(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testAddFriend() {

		RegMessage regMessage1 = new RegMessage("ghi", "ghi", "ghi", "1234", "ghi@bgu.ac.il");
		RegMessage regMessage2 = new RegMessage("jkl", "jkl", "jkl", "1234", "jkl@bgu.ac.il");
		LoginMessage loginMessage = new LoginMessage("ghi", "1234");
		AddFriendMessage addFriendMessage = new AddFriendMessage("ghi", "jkl");
		LogoutMessage logoutMessage = new LogoutMessage("ghi");
		
		try {
			
			assertEquals(MessageType.OK, forumServerStub.setInformation(regMessage1).getMessageType());
			assertEquals(MessageType.OK, forumServerStub.setInformation(regMessage2).getMessageType());
			assertEquals(MessageType.OK, forumServerStub.setInformation(loginMessage).getMessageType());
			assertEquals(MessageType.OK, forumServerStub.setInformation(addFriendMessage).getMessageType());
			assertEquals(MessageType.OK, forumServerStub.setInformation(logoutMessage).getMessageType());
		}
		catch (RemoteException e) { e.printStackTrace(); }
	}

	/**
	 * Test method for {@link domain.ClientController#RemoveFriend(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testRemoveFriend() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link domain.ClientController#replyToThread(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testReplyToThread() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link domain.ClientController#addThread(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testAddThread() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link domain.ClientController#getForumsList(common.network.messages.SeeForumsListMessage)}.
	 */
	@Test
	public void testGetForumsList() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link domain.ClientController#getThreadsList(java.lang.String, common.network.messages.SeeForumThreadsMessage)}.
	 */
	@Test
	public void testGetThreadsList() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link domain.ClientController#getPostsList(java.lang.String, common.network.messages.SeeThreadPostsMessage)}.
	 */
	@Test
	public void testGetPostsList() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link domain.ClientController#setForumServerStub(common.network.ForumServer)}.
	 */
	@Test
	public void testSetForumServerStub() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link domain.ClientController#getForumServerStub()}.
	 */
	@Test
	public void testGetForumServerStub() {
		fail("Not yet implemented");
	}

}
