package common.forum.items;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadInfo implements Serializable  {

	private static final long serialVersionUID = 6243423726451029710L;
	
	private int _threadId;
	private String _title;
	private int _forumId;
	private String _owner;
	private Date _lastModifiedDate;
	private String _lastModifiedUser; 

	/**
	 * @param _threadId
	 * @param _title
	 * @param _forumId
	 */
	public ThreadInfo(int _threadId, String _title, int _forumId,String owner,Date lastModifyDate,String lastModifyUser) {
		
		super();
		this._threadId = _threadId;
		this._title = _title;
		this._forumId = _forumId;
		this._owner = owner;
		this._lastModifiedDate = lastModifyDate;
		this._lastModifiedUser = lastModifyUser;
	}

	public int getThread_id(){
		return _threadId;
	}
	
	public String getTitle(){
		return _title;
	}
	
	public int get_forumId(){
		return _forumId;
	}

	public void set_lastModifiedDate(Date _lastModifiedDate) {
		this._lastModifiedDate = _lastModifiedDate;
	}

	public Date get_lastModifiedDate() {
		return _lastModifiedDate;
	}

	public void set_lastModifiedUser(String _lastModifiedUser) {
		this._lastModifiedUser = _lastModifiedUser;
	}

	public String get_lastModifiedUser() {
		return _lastModifiedUser;
	}

	public void set_owner(String _owner) {
		this._owner = _owner;
	}

	public String get_owner() {
		return _owner;
	}
	
	public String getDateTime() {
	    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    return dateFormat.format(_lastModifiedDate);
	}
}
