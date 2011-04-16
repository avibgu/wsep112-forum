package common.forum.items;

import java.io.Serializable;

public class ThreadInfo implements Serializable  {

	private static final long serialVersionUID = 6243423726451029710L;
	
	private int _threadId;
	private String _title;
	private int _forumId;

	/**
	 * @param _threadId
	 * @param _title
	 * @param _forumId
	 */
	public ThreadInfo(int _threadId, String _title, int _forumId) {
		
		super();
		this._threadId = _threadId;
		this._title = _title;
		this._forumId = _forumId;
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
}
