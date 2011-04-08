/**
 *
 */
package presentation.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;
import java.util.logging.Logger;

import common.network.messages.ErrorMessage;
import common.network.messages.Message;
import common.network.messages.MessageType;
import common.network.messages.SeeForumThreadsMessage;
import common.network.messages.SeeForumsListMessage;
import common.network.messages.SeeThreadPostsMessage;

import domain.ClientController;

/**
 * @author Miri Peretz
 *
 */
public class CLI {

	private ClientController clientController;
	private Logger logger;
	InputStreamReader inp = new InputStreamReader(System.in);
    BufferedReader buf = new BufferedReader(inp);

	public CLI(ClientController clientController, Logger logger) {

		setClientController(clientController);
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
	        	Message answer= register();
	        	if (answer.getMessageType() == MessageType.ERROR){
	    			System.out.println( ((ErrorMessage)answer).getReason() );
	    		}
	        	else subMenu();
	        }
	        else {
	        	if (key.equals("2")){
	        		Message answer= login();
	        		if (answer.getMessageType() == MessageType.ERROR){
	        			System.out.println( ((ErrorMessage)answer).getReason() );
	        		}
	        		else subMenu();
	        	}
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
		Message answer = clientController.getForumsList();
		if (answer.getMessageType() == MessageType.ERROR){
			System.out.println( ((ErrorMessage)answer).getReason() );
			return;
		}
		Vector<String> forumList = ((SeeForumsListMessage)answer).getListOfForums(); 
		int length = forumList.size();
		int n = 0;
		int i = 1;
		String strArr="";
		while (n!=i) { //loop until press 'Back'
			System.out.println(((char) 27)+"[2J"); //clear screen
			while ((n>length+1) | (n<=0)) {
				System.out.println("Please choose one of the following fourms:");
				for (i=1; i<=length; i++){
					System.out.println(i + ". " + forumList.get(i-1));
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
       	Message answer = clientController.addThread(forumID,title, body);
		if (answer.getMessageType() == MessageType.ERROR){
			System.out.println( ((ErrorMessage)answer).getReason() );
			return;
		}
	}

	/**
	 * View all threads
	 * @param forumID
	 * @throws IOException
	 */
	private void ViewThreads(String forumID) throws IOException {
		String str = "";
		Message answer = clientController.getThreadsList(forumID);
		if (answer.getMessageType() == MessageType.ERROR){
			System.out.println( ((ErrorMessage)answer).getReason() );
			return;
		}
		Vector<String> threadList = ((SeeForumThreadsMessage)answer).getListOfThreads();
		int length = threadList.size();
		int n = 0;
		int i = 1;
		String strArr="";
		while (n!=i) { //loop until press 'Back'
			System.out.println(((char) 27)+"[2J"); //clear screen
			while ((n>length+1) | (n<=0)) {
				System.out.println("Please choose one of the following threads:");
				for (i=1; i<=length; i++){
					System.out.println(i + ". " + threadList.get(i-1));
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
		Message answer = clientController.replyToThread(forumID,title, body, threadId);
		if (answer.getMessageType() == MessageType.ERROR){
			System.out.println( ((ErrorMessage)answer).getReason() );
		}
		
	}

	/**
	 * View all posts
	 * @param forumID
	 * @param threadID
	 * @throws IOException
	 */
	private void ViewPosts(String forumID,String threadID) throws IOException {
		String str = "";
		Message answer = clientController.getPostsList(forumID,threadID);
		if (answer.getMessageType() == MessageType.ERROR){
			System.out.println( ((ErrorMessage)answer).getReason() );
			return;
		}
		Vector<String> postsList = ((SeeThreadPostsMessage)answer).getListOfPosts();
		int length = postsList.size();
		int n = 0;
		int i = 1;
		while (n!=i) { //loop until press 'Back'
			System.out.println(((char) 27)+"[2J"); //clear screen
				for (i=1; i<=length; i++){
					System.out.println(i + "." + postsList.get(i-1));
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
	public Message register() throws IOException {
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
		return clientController.register(firstName, lastName, username, password, email);
	}

	/**
	 * Login menu
	 * @return
	 * @throws IOException
	 */
	public Message login() throws IOException {
		System.out.println("Please insert your username");
		String username = buf.readLine();
		System.out.println("Please insert your password");
		String password = buf.readLine();
		return clientController.login(username, password);
	}

	/**
	 * Logout menu
	 * @throws IOException
	 */
	public void logout() throws IOException {
		Message answer = clientController.logout();
		if (answer.getMessageType() == MessageType.ERROR){
			System.out.println( ((ErrorMessage)answer).getReason() );
		}
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
				Message answer = clientController.AddFriend(friendUsername);
				if (answer.getMessageType() == MessageType.ERROR){
					System.out.println( ((ErrorMessage)answer).getReason() );
					return;
				}
				System.out.println("Friend "+friendUsername+ " added successfully.");
				str = "";
			}

			if (str.equals("2")){//remove friend
				System.out.println("Please insert friend username");
				String friendUsername = buf.readLine();
				Message answer = clientController.RemoveFriend(friendUsername);
				if (answer.getMessageType() == MessageType.ERROR){
					System.out.println( ((ErrorMessage)answer).getReason() );
					return;
				}
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
}
