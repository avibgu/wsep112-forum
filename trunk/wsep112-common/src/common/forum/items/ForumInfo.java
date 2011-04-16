package common.forum.items;

import java.io.Serializable;

public class ForumInfo implements Serializable{

	private static final long serialVersionUID = -6633426066766746088L;
	
	private String _name;
	private int _id;
	
	public ForumInfo(String name, int id) {
		_name = name;
		_id = id;
	}
	
	public String getName(){
		return _name;
	}
	
	public int getForumId(){
		return _id;
	}
}
