package common.forum.items;

import java.io.Serializable;

public class UserInfo implements Serializable {

	private static final long serialVersionUID = -1262119404847181191L;
	
	private String _status;
	private String _username;
	
	/**
	 * 
	 * @param status
	 * @param name
	 */
	public UserInfo(String status, String username) {
		
		super();
		this._status = status;
		this._username = username;
	}
	
	public String getStatus(){
		return _status;
	}
	public String getUserName(){
		return _username;
	}
}
