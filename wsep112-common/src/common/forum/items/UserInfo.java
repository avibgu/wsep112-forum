package common.forum.items;

import java.io.Serializable;

public interface UserInfo extends Serializable {

	String getStatusAsString();
	String getUserName();
}
