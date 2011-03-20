package domain;

public class User {
	
	private String _firstName;
	private String _lastName;
	private String _username;
	private String _password;
	private String _email;
	
	public User(String firstName, String lastName, String username,
			    String password, String email){
		
	}
	
	public String getUserName(){
		return _username;
	}
	
	public String getPassword(){
		return _password;
	}

}
