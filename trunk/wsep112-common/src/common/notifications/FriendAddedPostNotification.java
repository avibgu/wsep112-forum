/**
 * 
 */
package common.notifications;

import java.io.Serializable;

import common.forum.items.ThreadInfo;
import common.forum.items.UserInfo;

/**
 * @author Avi Digmi
 *
 */
public class FriendAddedPostNotification implements Notification, Serializable {

	private static final long serialVersionUID = 4148258366216891054L;
	
	private ThreadInfo _threadInfo;
	private UserInfo _userInfo;
	private String _forWho;

	public FriendAddedPostNotification(ThreadInfo threadInfo,
			UserInfo userInfo, String forWho) {

		super();
		setThreadInfo(threadInfo);
		setUserInfo(userInfo);
		setForWho(forWho);
	}

	/* (non-Javadoc)
	 * @see common.notifications.Notification#getNotificationType()
	 */
	@Override
	public NotificationType getNotificationType() {
		return NotificationType.FRIEND_ADDED_POST;
	}

	public void setThreadInfo(ThreadInfo _threadInfo) {
		this._threadInfo = _threadInfo;
	}

	public ThreadInfo getThreadInfo() {
		return _threadInfo;
	}

	public void setUserInfo(UserInfo _userInfo) {
		this._userInfo = _userInfo;
	}

	public UserInfo getUserInfo() {
		return _userInfo;
	}

	public void setForWho(String _forWho) {
		this._forWho = _forWho;
	}

	public String getForWho() {
		return _forWho;
	}
}
