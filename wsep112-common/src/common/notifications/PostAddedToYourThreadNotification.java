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
public class PostAddedToYourThreadNotification implements Notification,
		Serializable {

	private static final long serialVersionUID = -4951735747232030364L;

	private ThreadInfo _threadInfo;
	private String _forWho;
	private String _newPostOwner;
	
	/**
	 * 
	 * @param threadInfo
	 */
	public PostAddedToYourThreadNotification(ThreadInfo threadInfo,
			String forWho, String newPostOwner){
	
		super();
		setThreadInfo(threadInfo);
		setForWho(forWho);
		setNewPostOwner(newPostOwner);
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

	public void setForWho(String _forWho) {
		this._forWho = _forWho;
	}

	public String getForWho() {
		return _forWho;
	}

	public String getNewPostOwner() {
		return _newPostOwner;
	}
	
	public void setNewPostOwner(String newPostOwner) {
		this._newPostOwner = newPostOwner;
	}
}
