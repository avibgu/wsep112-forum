/**
 *
 */
package presentation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
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
 * @author Avi Digmi
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
		while (key!="3"){//loop until user press '3. Exit'
			System.out.println(((char) 27)+"[2J"); //clear screen
			System.out.println("****************************************");
			System.out.println("***** Welcome to Forum System 2011 *****");
			System.out.println("****************************************");
			System.out.println();
			key = readFromUser();
	        if (key.equals("1")) {
	        	register();
	        	subMenu();
	        }
	        else {
	        	if (key.equals("2")){
	        		login();
	        		subMenu();
	        	}
	        }
		}
       }

	public void subMenu() throws IOException{
		String str="";
        while (!str.equals("4") || !str.equals("3")){ //loop until press '4. Back' or logout
        	System.out.println(((char) 27)+"[2J"); //clear screen
        	str = forumSystem();
        	if (str.equals("1")){ //manage friends list
            	manageFriends();
            }
            else if (str.equals("2")){ //view list of forums
            	viewForums();
            }
            else if (str.equals("3")){ //logout from the system
            	logout();
            }
        }
	}

	private void viewForums() throws IOException {
		String str = "";

		Message answer = clientController.getForumsList();

		if (answer.getMessageType() == MessageType.ERROR){
			System.out.println( ((ErrorMessage)answer).getReason() );
			return;
		}

		Vector<String> forumList = ((SeeForumsListMessage)answer).getListOfForums(); ////********************************

		int length = forumList.size();
		int n = 0;
		int i = 1;
		while (n!=i) { //loop until press 'Back'
			System.out.println(((char) 27)+"[2J"); //clear screen
			while ((n>length+1) | (n<=0)) {
				if (str!="") System.out.println("Incorrect input!");
				System.out.println("Please choose one of the following options:");
				for (i=1; i<=length; i++){
					System.out.println(i + "." + forumList.get(i));
				}
				System.out.println(i + ". Back");
				str = buf.readLine();
				n = Integer.parseInt(str);
			}
	    	if ((n<=length) | (n>0)) { //choose valid forum
				forumOption(str);
			}
		}
	}

	public void forumOption(String fourmID) throws IOException {
		String str = "";
		while (!(str.equals("3"))) { //loop until press '3. Back'
			System.out.println(((char) 27)+"[2J"); //clear screen
			while ( !(str.equals("1")) &  !(str.equals("2")) & !(str.equals("3")) ){
				if (str!="") System.out.println("Incorrect input!");
				System.out.println("Please choose one of the following options:");
				System.out.println("1. View threads");
				System.out.println("2. Add new thread");
				System.out.println("3. Back");
				str = buf.readLine();
			}
	    	if (str.equals("1")){ //view threads
	    		ViewThreads(fourmID);
	        }
	        else if (str.equals("2")){ //add new thread
	        	addThread();
	        }
		}
	}

	public void addThread() throws IOException {
		System.out.println("Please insert the title of the thread");
		String title = buf.readLine();
		System.out.println("Please insert the body of the thread");
		String body = buf.readLine();
     	clientController.addThread(title, body);
	}

	private void ViewThreads(String fourmID) throws IOException {
		String str = "";
		Message answer = clientController.getThreadsList(fourmID);

		if (answer.getMessageType() == MessageType.ERROR){
			System.out.println( ((ErrorMessage)answer).getReason() );
			return;
		}

		Vector<String> threadList = ((SeeForumThreadsMessage)answer).getListOfThreads(); ////********************************

		int length = threadList.size();
		int n = 0;
		int i = 1;
		while (n!=i) { //loop until press 'Back'
			System.out.println(((char) 27)+"[2J"); //clear screen
			while ((n>length+1) | (n<=0)) {
				if (str!="") System.out.println("Incorrect input!");
				System.out.println("Please choose one of the following options:");
				for (i=1; i<=length; i++){
					System.out.println(i + "." + threadList.get(i));
				}
				System.out.println(i + ". Back");
				str = buf.readLine();
				n = Integer.parseInt(str);
			}
			if ((n<=length) | (n>0)) { //choose valid forum
				ThreadOption(str);
			}
		}
	}

	public void ThreadOption(String threadID) throws IOException {
		String str = "";
		while (!(str.equals("3"))) { //loop until press '3. Back'
			System.out.println(((char) 27)+"[2J"); //clear screen
			while ( !(str.equals("1")) &  !(str.equals("2")) & !(str.equals("3")) ){
				if (str!="") System.out.println("Incorrect input!");
				System.out.println("Please choose one of the following options:");
				System.out.println("1. View posts");
				System.out.println("2. Add new post");
				System.out.println("3. Back");
				str = buf.readLine();
			}
	    	if (str.equals("1")){ //view threads
	    		ViewPosts(threadID);
	        }
	        else if (str.equals("2")){ //add new thread
	        	addPost(threadID);
	        }
		}
	}

	public void addPost(String threadId) throws IOException {
		System.out.println("Please insert the title of the post");
		String title = buf.readLine();
		System.out.println("Please insert the body of the post");
		String body = buf.readLine();
     	clientController.replyToThread(title, body, threadId);
	}

	private void ViewPosts(String threadID) throws IOException {
		String str = "";
		Message answer = clientController.getPostsList(threadID);

		if (answer.getMessageType() == MessageType.ERROR){
			System.out.println( ((ErrorMessage)answer).getReason() );
			return;
		}

		Vector<String> postsList = ((SeeThreadPostsMessage)answer).getListOfPosts(); ////********************************

		int length = postsList.size();
		int n = 0;
		int i = 1;
		while (n!=i) { //loop until press 'Back'
			System.out.println(((char) 27)+"[2J"); //clear screen
			while ((n>length+1) | (n<=0)) {
				if (str!="") System.out.println("Incorrect input!");
				System.out.println("Please choose one of the following options:");
				for (i=1; i<=length; i++){
					System.out.println(i + "." + postsList.get(i));
				}
				System.out.println(i + ". Back");
				str = buf.readLine();
				n = Integer.parseInt(str);
			}
			if ((n<=length) | (n>0)) { //choose valid forum
				//end?!
			}
		}
	}

	public String readFromUser() throws IOException {
		String str ="";
		while ( !(str.equals("1")) &  !(str.equals("2")) &  !(str.equals("3"))){
			if (str!="") System.out.println("Incorrect input!");
			System.out.println("Please choose one of the following options:");
			System.out.println("1. Register to the forum system");
			System.out.println("2. Login to the forum system");
			System.out.println("3. Exit");
			str = buf.readLine();
		}
		return str;
	}

	public void register() throws IOException {
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
     	clientController.register(firstName, lastName, username, password, email);
	}

	public void login() throws IOException {
		System.out.println("Please insert your username");
		String username = buf.readLine();
		System.out.println("Please insert your password");
		String password = buf.readLine();
     	clientController.login(username, password);
     	//need to save the current user?!
	}

	public void logout() throws IOException {
     	clientController.logout();
	}

	public String forumSystem() throws IOException {
		String str = "";
		while ( !(str.equals("1")) &  !(str.equals("2")) & !(str.equals("3")) & !(str.equals("4")) ){
			if (str!="") System.out.println("Incorrect input!");
			System.out.println("Please choose one of the following options:");
			System.out.println("1. Manage friends list");
			System.out.println("2. View list of forums");
			System.out.println("3. Logout from the system");
			System.out.println("4. Back");
			str = buf.readLine();
		}
		return str;
    }

	public void manageFriends() throws IOException {
		String str = "";
		while (!(str.equals("3"))) { //loop until press '3. Back'
			System.out.println(((char) 27)+"[2J"); //clear screen
			while ( !(str.equals("1")) &  !(str.equals("2")) & !(str.equals("3"))){
				if (str!="") System.out.println("Incorrect input!");
				System.out.println("Please choose one of the following options:");
				System.out.println("1. Add friend");
				System.out.println("2. Remove friend");
				System.out.println("3. Back");
				str = buf.readLine();
			}

			if (str.equals("1")){//add friend
				System.out.println("Please insert friend username");
				String friendUsername = buf.readLine();
				clientController.AddFriend(friendUsername);
			}

			if (str.equals("2")){//remove friend
				System.out.println("Please insert friend username");
				String friendUsername = buf.readLine();
				clientController.RemoveFriend(friendUsername);
			}
		}
    }

	public void setClientController(ClientController clientController) {
		this.clientController = clientController;
	}

	public ClientController getClientController() {
		return clientController;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public Logger getLogger() {
		return logger;
	}

	public void log(String msg){
		getLogger().info(msg);
	}
}
