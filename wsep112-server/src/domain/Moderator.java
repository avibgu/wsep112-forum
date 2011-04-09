 package domain;

import java.io.Serializable;

public class Moderator extends User implements Serializable{

	private static final long serialVersionUID = 2600587664509975934L;

	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @param username
	 * @param password
	 * @param email
	 */
	public Moderator(String firstName, String lastName, String username,
		    String password, String email){
		super(firstName, lastName, username, password, email);
	}
	
}
