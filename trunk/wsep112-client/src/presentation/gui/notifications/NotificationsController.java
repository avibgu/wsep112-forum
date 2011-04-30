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
import javax.swing.JPanel;

import domain.ClientController;

import presentation.gui.PostsViewPanel;
import presentation.gui.StartWindow;

/**
 * @author Avi Digmi
 *
 */
public class NotificationsController implements Observer {

	private Observable _observable;
	private ClientController _clientController;
	private StartWindow _startWindow;
	
	public NotificationsController(Observable observable,
			ClientController clientController, StartWindow startWindow) {

		setObservable(observable);
		getObservable().addObserver(this);
		setClientController(clientController);
		setStartWindow(startWindow);
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

		final ThreadInfo tInfo = tcn.getThreadInfo();
		
        java.awt.EventQueue.invokeLater(new Runnable() {
            
        	public void run() {
            	
        		JPanel currentPanel = getStartWindow().getForum().getMainPanel();

        		if (currentPanel instanceof PostsViewPanel)
        			
        			getStartWindow().getForum().displayForum(
        					new PostsViewPanel(getClientController(),
        							String.valueOf(tInfo.get_forumId()),
        							String.valueOf(tInfo.getThread_id()),
        							getStartWindow()) );
            }
        });
		
//		String msg =	"Thread " + tInfo.getThread_id() +
//						" (Forum " + tInfo.get_forumId() +") : \"" +
//						tInfo.getTitle() + "\" has been changed";
//        
//		genPopUp(msg);
	}
	
	private void nofity(FriendAddedPostNotification fapn) {

		ThreadInfo tInfo = fapn.getThreadInfo();
		UserInfo uInfo = fapn.getUserInfo();
		
		String msg =	"Your friend " + uInfo.getUserName() +
						" added post to Thread " + tInfo.getThread_id() +
						" (Forum " + tInfo.get_forumId() +") : \"" +
						tInfo.getTitle() + "\"";
		
		genPopUp(msg);
	}
	
	private void nofity(PostAddedToYourThreadNotification patytn) {

		ThreadInfo tInfo = patytn.getThreadInfo();
					
		String msg =	"Your Thread " + tInfo.getThread_id() +
						" (Forum " + tInfo.get_forumId() +") : \"" +
						tInfo.getTitle() + "\" has been changed";

		genPopUp(msg);
	}

	public void genPopUp(String message){

            final NotificationForm popup = new NotificationForm(message);
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                	popup.setLocation(900, 670);
                    popup.setVisible(true);
                }
            });
            try { Thread.sleep(3000); } catch (Exception ex) {}
            popup.dispose();
	}

	public void setObservable(Observable _observable) {
		this._observable = _observable;
	}

	public Observable getObservable() {
		return _observable;
	}

	public void setClientController(ClientController _clientController) {
		this._clientController = _clientController;
	}

	public ClientController getClientController() {
		return _clientController;
	}

	public void setStartWindow(StartWindow _startWindow) {
		this._startWindow = _startWindow;
	}

	public StartWindow getStartWindow() {
		return _startWindow;
	}
}
