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
	
	public String getFirstName() {
		return _firstName;
	}
	
	public void setFirstName(String _firstName) {
		this._firstName = _firstName;
	}
	
	public void setLastName(String _lastName) {
		this._lastName = _lastName;
	}

	public String getLastName() {
		return _lastName;
	}

	public void setUserName(String username) {
		this._username = username;
	}
	
	public String getUserName(){
		return _username;
	}
	
	public void setPassword(String password) {
		this._password = password;
	}
	
	public String getPassword(){
		return _password;
	}

	public void setEmail(String _email) {
		this._email = _email;
	}

	public String getEmail() {
		return _email;
	}
}
