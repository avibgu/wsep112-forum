package common.forum.items;

import java.io.Serializable;

public class PostInfo implements Serializable  {

	private static final long serialVersionUID = 879935809622702616L;
	
	private int _post_id;
	private String _title;
	private String _body;
	private UserInfo _owner;
	private int _thread_id;
	private String _dateTime;
	
	
	
	/**
	 * @param _post_id
	 * @param _title
	 * @param _body
	 * @param _owner
	 * @param _thread_id
	 * @param _dateTime
	 */
	public PostInfo(int _post_id, String _title, String _body, UserInfo _owner,
			int _thread_id, String _dateTime) {
		
		super();
		this._post_id = _post_id;
		this._title = _title;
		this._body = _body;
		this._owner = _owner;
		this._thread_id = _thread_id;
		this._dateTime = _dateTime;
	}

	public int get_post_id(){
		return _post_id;
	}
	
	public String get_title(){
		return _title;
	}
	
	public String get_body(){
		return _body;
	}
	
	public UserInfo getOwner(){
		return _owner;
	}
	
	public int getThread_id(){
		return _thread_id;
	}
	
	public String getDateTime(){
		return _dateTime;
	}
}
