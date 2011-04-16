/**
 * 
 */
package common.notifications;

import java.io.Serializable;

import common.forum.items.ThreadInfo;


/**
 * @author Avi Digmi
 *
 */
public class ThreadChangedNotification implements Notification, Serializable {

	private static final long serialVersionUID = 9020144219294062030L;

	private ThreadInfo _threadInfo;

	/**
	 * 
	 * @param threadInfo
	 */
	public ThreadChangedNotification(ThreadInfo threadInfo) {

		super();
		setThreadInfo(threadInfo);
	}

	/* (non-Javadoc)
	 * @see common.notifications.Notification#getNotificationType()
	 */
	@Override
	public NotificationType getNotificationType() {
		return NotificationType.THREAD_HAS_BEEN_CHANGED;
	}

	public void setThreadInfo(ThreadInfo _threadInfo) {
		this._threadInfo = _threadInfo;
	}

	public ThreadInfo getThreadInfo() {
		return _threadInfo;
	}
}
