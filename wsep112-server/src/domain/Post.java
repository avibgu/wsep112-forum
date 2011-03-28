package domain;

public class Post {
private int _post_id;
private int thread_id;
private String _title;
private String _body;
private User owner;

public Post(int thread_id,int id ,String title ,String body,User owner){
	this._post_id=id;
	this._title=title;
	this._body=body;
	this.owner=owner;
	this.thread_id=thread_id;
}





/* 
 * editing only the title
 * */
public void edit_titel(String title){
	set_title(title);

}



/*
 * editing only the body
 * */
public void edit_body(String body){
	set_body(body);
}



/*
 * editing the whole post
 * */
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
	return owner;
}



public void setOwner(User owner) {
	this.owner = owner;
}

public int getThread_id() {
	return thread_id;
}





public void setThread_id(int thread_id) {
	this.thread_id = thread_id;
}



}
