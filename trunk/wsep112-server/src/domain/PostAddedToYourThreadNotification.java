/**
 * 
 */
package domain;

import java.io.Serializable;

import common.forum.items.ThreadInfo;
import common.notifications.Notification;
import common.notifications.NotificationType;

/**
 * @author Avi Digmi
 *
 */
public class PostAddedToYourThreadNotification implements Notification,
		Serializable {

	private static final long serialVersionUID = -4951735747232030364L;

	private ThreadInfo _threadInfo;
	
	/**
	 * 
	 * @param threadInfo
	 */
	public PostAddedToYourThreadNotification(ThreadInfo threadInfo){
	
		super();
		setThreadInfo(threadInfo);
	}
	
	/* (non-Javadoc)
	 * @see common.notifications.Notification#getNotificationType()
	 */
	@Override
	public NotificationType getNotificationType() {
		return NotificationType.POST_ADDED_TO_YOUR_THREAD;
	}

	public void setThreadInfo(ThreadInfo _threadInfo) {
		this._threadInfo = _threadInfo;
	}

	public ThreadInfo getThreadInfo() {
		return _threadInfo;
	}
}
