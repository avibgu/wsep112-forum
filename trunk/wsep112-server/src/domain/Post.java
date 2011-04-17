package domain;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Post implements Serializable{
	
	private static final long serialVersionUID = 729804992404516644L;
	
	private int _post_id;
	private int _threadID;
	private String _title;
	private String _body;
	private User _owner;
	private Date _date;
	
	public Post(){
		
	}
	public Post(int thread_id ,String title ,String body,User owner){
		this._title=title;
		this._body=body;
		this._owner=owner;
		this._threadID=thread_id;
		this._date = new Date();
	}
	
	/**
	 * editing only the title
	 * @param title the title to be edited
	 */
	public void edit_titel(String title){
		set_title(title);
		
		// TODO: database
	
	}
	/**
	 * editing only the body
	 * @param body the body of the post to be edited
	 */
	public void edit_body(String body){
		set_body(body);
		// TODO: database
	}
	
	/**
	 * editing the whole post
	 * @param title  the title to be edited
	 * @param body the body of the post to be edited
	 */
	public void edit_post(String title, String body){
		set_title(title);
		set_body(body);
	}
	
	//********************** SETTERS AND GETTERS *******************************//
	
	public int get_post_id() {
		return _post_id;
	}
	
	public void set_post_id(int _post_id) {
		this._post_id = _post_id;
	}
	
	public String get_title() {
		return _title;
	}
	
	public void set_title(String _title) {
		this._title = _title;
	}
	
	public String get_body() {
		return _body;
	}
	
	public void set_body(String _body) {
		this._body = _body;
	}
	
	public User getOwner() {
		return _owner;
	}
	
	public void setOwner(User owner) {
		this._owner = owner;
	}
	
	public int getThread_id() {
		return _threadID;
	}
	
	public void setThread_id(int thread_id) {
		this._threadID = thread_id;
	}
	
	public void setDate(Date _date) {
		this._date = _date;
	}
	
	public String getDateTime() {
	    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    return dateFormat.format(_date);
	}
}
