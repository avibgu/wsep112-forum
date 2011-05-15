package threads;

public class NotificationsThread extends Thread {

	@Override
	public void run() {

		while (true){
			
			//TODO:	check about notifications
			
			try {
				sleep(30000);
			} catch (InterruptedException e) { }
		}
	}
}
