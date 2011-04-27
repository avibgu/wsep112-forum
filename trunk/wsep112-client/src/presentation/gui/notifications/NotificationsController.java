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

import javax.swing.JOptionPane;

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

		if (arg instanceof ErrorMessage)
			nofity((ErrorMessage)arg);
		
		else if (arg instanceof ThreadChangedNotification)
			nofity((ThreadChangedNotification)arg);
		
		else if (arg instanceof FriendAddedPostNotification)
			nofity((FriendAddedPostNotification)arg);
		
		else if (arg instanceof PostAddedToYourThreadNotification)
			nofity((PostAddedToYourThreadNotification)arg);
    }
	
    private void nofity(ErrorMessage em){

    	JOptionPane.showMessageDialog(null, em.getReason(), "Error", 0);
    }
	
	private void nofity(ThreadChangedNotification tcn) {

		ThreadInfo tInfo = tcn.getThreadInfo();
		
		final String msg =	"Thread " + tInfo.getThread_id() +
						" (Forum " + tInfo.get_forumId() +") : \"" +
						tInfo.getTitle() + "\" has been changed";
        
		genPopUp(msg);
	}
	
	private void nofity(final FriendAddedPostNotification fapn) {

		ThreadInfo tInfo = fapn.getThreadInfo();
		UserInfo uInfo = fapn.getUserInfo();
		
		final String msg =	"Your friend " + uInfo.getUserName() +
						" added post to Thread " + tInfo.getThread_id() +
						" (Forum " + tInfo.get_forumId() +") : \"" +
						tInfo.getTitle() + "\"";
		
                genPopUp(msg);
	}
	
	private void nofity(final PostAddedToYourThreadNotification patytn) {

		ThreadInfo tInfo = patytn.getThreadInfo();
					
		final String msg =	"Your Thread " + tInfo.getThread_id() +
						" (Forum " + tInfo.get_forumId() +") : \"" +
						tInfo.getTitle() + "\" has been changed";

                genPopUp(msg);
	}

	public void genPopUp(final String message){

            final NotificationForm popup = new NotificationForm(message);
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                	popup.setLocation(930, 670);
                    popup.setVisible(true);
                }
            });
            try { Thread.sleep(3000); } catch (Exception ex) {}
            popup.dispose();
	}

	public void set_observable(Observable _observable) {
		this._observable = _observable;
	}

	public Observable get_observable() {
		return _observable;
	}
}
