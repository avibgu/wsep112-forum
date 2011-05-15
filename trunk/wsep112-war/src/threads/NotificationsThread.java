package threads;

import java.io.Serializable;

public class NotificationsThread implements Runnable, Serializable{

	private static final long serialVersionUID = -5349801330127850171L;

	public int i = 0;
	
	@Override
	public void run() {
		
		while (true){
			
			i++;
			
			try {
				
				Thread.sleep(2000);
			}
			catch (InterruptedException e) { }
		}
	}
}
