package common.forum.items;

import java.io.Serializable;

public interface ThreadInfo extends Serializable  {

	int getThread_id();
	String getTitle();
	int get_forumId();
}
