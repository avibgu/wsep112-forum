package common.forum.items;

public interface PostInfo {

	int get_post_id();
	String get_title();
	String get_body();
	UserInfo getOwner();
	int getThread_id();
	String getDateTime();
}
