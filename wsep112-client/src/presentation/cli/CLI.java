/**
 *
 */
package presentation.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;
import java.util.logging.Logger;

import common.forum.items.ForumInfo;
import common.forum.items.PostInfo;
import common.forum.items.ThreadInfo;
import common.forum.items.UserInfo;
import common.network.messages.ErrorMessage;
import common.notifications.FriendAddedPostNotification;
import common.notifications.Notification;
import common.notifications.PostAddedToYourThreadNotification;
import common.notifications.ThreadChangedNotification;

import domain.ClientController;

/**
 * @author Miri Peretz
 *
 */
public class CLI implements Observer{

	private ClientController clientController;
	private Logger logger;
	InputStreamReader inp = new InputStreamReader(System.in);
    BufferedReader buf = new BufferedReader(inp);

	public CLI(ClientController clientController, Logger logger) {

		setClientController(clientController);
		getClientController().addObserver(this);
		setLogger(logger);
	}

	/**
	 * This is the main method of the CLI.
	 * Should while forever, which do:
	 * 1. shows options to user,
	 * 2. get his input,
	 * 3. redirect the queries to clientController.
	 * @throws IOException
	 */
	public void start() throws IOException {
		String key="";
		while (!(key.equals("3"))){//loop until user press '3. Exit'
			System.out.println(((char) 27)+"[2J"); //clear screen
			System.out.println("****************************************");
			System.out.println("*                                      *");
			System.out.println("*     Welcome to Forum System 2011     *");
			System.out.println("*                                      *");
			System.out.println("****************************************");
			System.out.println();
			key = readFromUser();
	        if (key.equals("1")) {
	        	if(register()) subMenu();
	        }
	        else if (key.equals("2")){
	        	if (login()) subMenu();
	        }
		}
       }

	/**
	 * Reads data from the user- Main menu
	 * @return
	 * @throws IOException
	 */
	public String readFromUser() throws IOException {
		String str ="";
		while ( !(str.equals("1")) &  !(str.equals("2")) &  !(str.equals("3"))){
			if (!(str.equals(""))) System.out.println("Incorrect input!");
			System.out.println("Please choose one of the following options:");
			System.out.println("1. Register to the forum system");
			System.out.println("2. Login to the forum system");
			System.out.println("3. Exit");
			str = buf.readLine();
		}
		return str;
	}
	
	/**
	 * Sub Menu
	 * @throws IOException
	 */
	public void subMenu() throws IOException{
		String str="";
        while (!str.equals("4")){ //loop until press '4. Back'
        	System.out.println(((char) 27)+"[2J"); //clear screen
        	str = forumSystem();
        	if (str.equals("1")){ //manage friends list
            	manageFriends();
            	str="";
            }
            else if (str.equals("2")){ //view list of forums
            	viewForums();
            	str="";
            }
            else if (str.equals("3")){ //logout from the system
            	logout();
            	break; //return to main menu
            }
        }
	}
	
	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public String forumSystem() throws IOException {
		String str = "";
		while ( !(str.equals("1")) &  !(str.equals("2")) & !(str.equals("3")) ){
			if (!(str.equals(""))) System.out.println("Incorrect input!");
			System.out.println("Please choose one of the following options:");
			System.out.println("1. Manage friends list");
			System.out.println("2. View list of forums");
			System.out.println("3. Logout from the system");
			str = buf.readLine();
		}
		return str;
    }

	/**
	 * View all forums
	 * @throws IOException
	 */
	private void viewForums() throws IOException {
		
		String str = "";
		
		Vector<ForumInfo> forumList = getClientController().getForumsList();
		if (forumList == null) return;
		 
		int length = forumList.size();
		int n = 0;
		int i = 1;
		String strArr="";
		while (n!=i) { //loop until press 'Back'
			System.out.println(((char) 27)+"[2J"); //clear screen
			while ((n>length+1) | (n<=0)) {
				System.out.println("Please choose one of the following fourms:");
				for (i=1; i<=length; i++){
					System.out.println(i + ". " + forumList.get(i-1).getName());
				}
				System.out.println(i + ". Back");
				str = buf.readLine();
				if (!str.equals("")){
					if (validInput(i,str)){
						n = Integer.parseInt(str);
						int tnumInArray= n-1;
						strArr= Integer.toString(tnumInArray);
						if (n==i) break;
					}
					else System.out.println("Incorrect input!");
					
				}
			}
	    	if ((n<=length) | (n>0) && (n!=i)) { //choose valid forum
				forumOption(strArr);
				n = 0;
				str="";
			}
		}
	}
	
	/**
	 * 
	 * @param num
	 * @param input
	 * @return true if the str input is valid in menu according to num choice
	 */
	public boolean validInput(int num, String input){
		for (int i=1; i<=num; i++){
			if (input.equals(Integer.toString(i)))
				return true;
		}
		return false;
	}
	
	/**
	 * Forum Menu
	 * @param forumID
	 * @throws IOException
	 */
	public void forumOption(String forumID) throws IOException {
		String str = "";
		while (!(str.equals("3"))) { //loop until press '3. Back'
			System.out.println(((char) 27)+"[2J"); //clear screen
			while ( !(str.equals("1")) &  !(str.equals("2")) & !(str.equals("3")) ){
				if (!(str.equals(""))) System.out.println("Incorrect input!");
				System.out.println("Please choose one of the following options:");
				System.out.println("1. View threads");
				System.out.println("2. Add new thread");
				System.out.println("3. Back");
				str = buf.readLine();
			}
			if (str.equals("1")){ //view threads
	    		ViewThreads(forumID);
	    		str="";
	        }
	        else if (str.equals("2")){ //add new thread
	        	addThread(forumID);
	        	str="";
	        	}
		}
	}

	/**
	 * Add Thread menu
	 * @param forumID
	 * @throws IOException
	 */
	public void addThread(String forumID) throws IOException {
		System.out.println("Please insert the title of the thread");
		String title = buf.readLine();
		System.out.println("Please insert the body of the thread");
		String body = buf.readLine();
       	getClientController().addThread(forumID,title, body);
	}

	/**
	 * View all threads
	 * @param forumID
	 * @throws IOException
	 */
	private void ViewThreads(String forumID) throws IOException {
		String str = "";
		Vector<ThreadInfo> threadList = getClientController().getThreadsList(forumID);
		if (threadList == null) return;		
		int length = threadList.size();
		int n = 0;
		int i = 1;
		String strArr="";
		while (n!=i) { //loop until press 'Back'
			System.out.println(((char) 27)+"[2J"); //clear screen
			while ((n>length+1) | (n<=0)) {
				System.out.println("Please choose one of the following threads:");
				for (i=1; i<=length; i++){
					System.out.println(i + ". " + threadList.get(i-1).getTitle());
				}
				System.out.println(i + ". Back");
				str = buf.readLine();
				if (!str.equals("")){
					if (validInput(i,str)){
						n = Integer.parseInt(str);
						int tnumInArray= n-1;
						strArr= Integer.toString(tnumInArray+1);
						if (n==i) break;
					}
					else System.out.println("Incorrect input!");
				}
			}
			if ((n<=length) | (n>0) && n!=i) { //choose valid forum
				ThreadOption(forumID,strArr);
				n=0;
				str="";
			}
		}
	}

	/**
	 * 
	 * @param forumID
	 * @param threadID
	 * @throws IOException
	 */
	public void ThreadOption(String forumID, String threadID) throws IOException {
		String str = "";
		 while (!(str.equals("3"))){ //loop until press '3. Back'
			System.out.println(((char) 27)+"[2J"); //clear screen
			while ( !(str.equals("1")) &  !(str.equals("2")) & !(str.equals("3")) ){
				if (!(str.equals(""))) System.out.println("Incorrect input!");
				System.out.println("Please choose one of the following options:");
				System.out.println("1. View posts");
				System.out.println("2. Add new post");
				System.out.println("3. Back");
				str = buf.readLine();
			}
	    	if (str.equals("1")){ //view threads
	    		ViewPosts(forumID,threadID);
		    	str="";
	        }
	        else if (str.equals("2")){ //add new thread
	        	addPost(forumID,threadID);
		    	str="";
	        }
	 	}
	}

	/**
	 * Adding posts menu
	 * @param forumID
	 * @param threadId
	 * @throws IOException
	 */
	public void addPost(String forumID,String threadId) throws IOException {
		System.out.println("Please insert the title of the post");
		String title = buf.readLine();
		System.out.println("Please insert the body of the post");
		String body = buf.readLine();
		getClientController().replyToThread(forumID,title, body, threadId);
	}

	/**
	 * View all posts
	 * @param forumID
	 * @param threadID
	 * @throws IOException
	 */
	private void ViewPosts(String forumID,String threadID) throws IOException {
		String str = "";
		
		Vector<PostInfo> postsList = getClientController().getPostsList(threadID);
		
		if (postsList == null) return;

		int length = postsList.size();
		int n = 0;
		int i = 1;
		while (n!=i) { //loop until press 'Back'
			System.out.println(((char) 27)+"[2J"); //clear screen
				for (i=1; i<=length; i++){
					System.out.println(	i + "." + "Title:   " +
										postsList.get(i-1).get_title() +
										"\n  Date:    " +
										postsList.get(i-1).getDateTime() +
										"\n  Message: " +
										postsList.get(i-1).get_body() );
				}
				System.out.println(i + ". Back");
				str = buf.readLine();
				if (!str.equals("")){
					if (validInput(i,str)){
						 n = Integer.parseInt(str);
					}
					else System.out.println("Incorrect input!");
				}
		}
	}

	/**
	 * Registration menu
	 * @return
	 * @throws IOException
	 */
	public boolean register() throws IOException {
		System.out.println("Please insert your firstName");
		String firstName = buf.readLine();
		System.out.println("Please insert your lastName");
		String lastName = buf.readLine();
		System.out.println("Please choose your username");
		String username = buf.readLine();
		System.out.println("Please choose your password");
		String password = buf.readLine();
		System.out.println("Please insert your email");
		String email = buf.readLine();
		String checkbox = buf.readLine();
		return getClientController().register(firstName, lastName, username, password, email, checkbox);
	}

	/**
	 * Login menu
	 * @return
	 * @throws IOException
	 */
	public boolean login() throws IOException {
		System.out.println("Please insert your username");
		String username = buf.readLine();
		System.out.println("Please insert your password");
		String password = buf.readLine();
		return getClientController().login(username, password);
	}

	/**
	 * Logout menu
	 * @throws IOException
	 */
	public void logout() throws IOException {
		getClientController().logout();
	}

	/**
	 * Manage friends menu
	 * @throws IOException
	 */
	public void manageFriends() throws IOException {
		String str = "";
		while (!(str.equals("3"))) { //loop until press '3. Back'
			System.out.println(((char) 27)+"[2J"); //clear screen
			while ( !(str.equals("1")) &  !(str.equals("2")) & !(str.equals("3"))){
				if (!(str.equals(""))) System.out.println("Incorrect input!");
				System.out.println("Please choose one of the following options:");
				System.out.println("1. Add friend");
				System.out.println("2. Remove friend");
				System.out.println("3. Back");
				str = buf.readLine();
			}

			if (str.equals("1")){//add friend
				System.out.println("Please insert friend username");
				String friendUsername = buf.readLine();
				
				if(!getClientController().AddFriend(friendUsername)) return;
				
				System.out.println("Friend "+friendUsername+ " added successfully.");
				str = "";
			}

			if (str.equals("2")){//remove friend
				System.out.println("Please insert friend username");
				String friendUsername = buf.readLine();

				if(!getClientController().RemoveFriend(friendUsername)) return;
				
				System.out.println("Friend "+friendUsername+ " removed successfully.");
				str = "";
			}
		}
    }
	
	/**
	 * 
	 * @param clientController
	 */
	public void setClientController(ClientController clientController) {
		this.clientController = clientController;
	}

	/**
	 * 
	 * @return clientController
	 */
	public ClientController getClientController() {
		return clientController;
	}

	/**
	 * Sets logger
	 * @param logger
	 */
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	
	/**
	 * 
	 * @return logger
	 */
	public Logger getLogger() {
		return logger;
	}

	/**
	 * Logs message
	 * @param msg
	 */
	public void log(String msg){
		getLogger().info(msg);
	}

	@Override
	public void update(Observable o, Object arg) {
		
		// notification about some error
		if (arg instanceof ErrorMessage)
			System.err.println(((ErrorMessage)arg).getReason());
		
		if (arg instanceof Notification){
			
			switch (((Notification) arg).getNotificationType() ){
			
				case THREAD_HAS_BEEN_CHANGED:
				
					ThreadChangedNotification tcn = (ThreadChangedNotification)arg;
					ThreadInfo tInfo1 = tcn.getThreadInfo();
					
					System.out.println(	"Thread " + tInfo1.getThread_id() +
										" (Forum " + tInfo1.get_forumId() +") : \"" +
										tInfo1.getTitle() + "\" has been changed");
					break;
				
				case FRIEND_ADDED_POST:
					
					FriendAddedPostNotification fapn = (FriendAddedPostNotification)arg;
					ThreadInfo tInfo2 = fapn.getThreadInfo();
					UserInfo uInfo2 = fapn.getUserInfo();
					
					System.out.println(	"Your friend " + uInfo2.getUserName() +
										" added post to Thread " + tInfo2.getThread_id() +
										" (Forum " + tInfo2.get_forumId() +") : \"" +
										tInfo2.getTitle() + "\"");
					break;
					
				case POST_ADDED_TO_YOUR_THREAD:
					
					PostAddedToYourThreadNotification patytn =
						(PostAddedToYourThreadNotification)arg;
					
					ThreadInfo tInfo3 = patytn.getThreadInfo();
					
					System.out.println(	"Your Thread " + tInfo3.getThread_id() +
										" (Forum " + tInfo3.get_forumId() +") : \"" +
										tInfo3.getTitle() + "\" has been changed");
					break;
			}
		}
	}
}
