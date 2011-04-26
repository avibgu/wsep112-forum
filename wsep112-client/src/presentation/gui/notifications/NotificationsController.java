/**
 * 
 */
package presentation.gui.notifications;

import java.util.Observer;

import common.forum.items.ThreadInfo;
import common.forum.items.UserInfo;
import common.network.messages.ErrorMessage;
import common.notifications.FriendAddedPostNotification;
import common.notifications.PostAddedToYourThreadNotification;
import common.notifications.ThreadChangedNotification;
import common.observation.Observable;

/**
 * @author Avi Digmi
 *
 */
public class NotificationsController implements Observer {

	private Observable _observable;
	
	public NotificationsController(Observable observable) {

		set_observable(observable);
		get_observable().addObserver(this);
	}
	
	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
    public void update(java.util.Observable o, Object arg){
		// AVID remove it..
		System.out.println("NotificationsController got update..");
		
		if (arg instanceof ErrorMessage)
			nofity((ErrorMessage)arg);
		
		else if (arg instanceof ThreadChangedNotification)
			nofity((ThreadChangedNotification)arg);
		
		else if (arg instanceof FriendAddedPostNotification)
			nofity((FriendAddedPostNotification)arg);
		
		else if (arg instanceof PostAddedToYourThreadNotification)
			nofity((PostAddedToYourThreadNotification)arg);
    }

	private void nofity(final ErrorMessage em) {
		java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	new TempNotification(em, em.getReason());
            }
        });
	}
	
	private void nofity(final ThreadChangedNotification tcn) {

		ThreadInfo tInfo = tcn.getThreadInfo();
		
		final String msg =	"Thread " + tInfo.getThread_id() +
						" (Forum " + tInfo.get_forumId() +") : \"" +
						tInfo.getTitle() + "\" has been changed";
        
		java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	new TempNotification(tcn, msg);
            }
        });
	}
	
	private void nofity(final FriendAddedPostNotification fapn) {

		ThreadInfo tInfo = fapn.getThreadInfo();
		UserInfo uInfo = fapn.getUserInfo();
		
		final String msg =	"Your friend " + uInfo.getUserName() +
						" added post to Thread " + tInfo.getThread_id() +
						" (Forum " + tInfo.get_forumId() +") : \"" +
						tInfo.getTitle() + "\"";
		
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	new TempNotification(fapn, msg);
            }
        });
	}
	
	private void nofity(final PostAddedToYourThreadNotification patytn) {

		ThreadInfo tInfo = patytn.getThreadInfo();
					
		final String msg =	"Your Thread " + tInfo.getThread_id() +
						" (Forum " + tInfo.get_forumId() +") : \"" +
						tInfo.getTitle() + "\" has been changed";

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	new TempNotification(patytn, msg);
            }
        });
	}

	public void set_observable(Observable _observable) {
		this._observable = _observable;
	}

	public Observable get_observable() {
		return _observable;
	}
}
