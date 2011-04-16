package common.forum.items;

import java.io.Serializable;

public interface PostInfo extends Serializable  {

	int get_post_id();
	String get_title();
	String get_body();
	UserInfo getOwner();
	int getThread_id();
	String getDateTime();
}
