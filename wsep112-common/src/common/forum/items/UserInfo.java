package common.forum.items;

import java.io.Serializable;

public class UserInfo implements Serializable {

	private static final long serialVersionUID = -1262119404847181191L;
	
	private String _status;
	private String _name;
	
	/**
	 * 
	 * @param status
	 * @param name
	 */
	public UserInfo(String status, String name) {
		
		super();
		this._status = status;
		this._name = name;
	}
	
	public String getStatus(){
		return _status;
	}
	public String getUserName(){
		return _name;
	}
}
