/**
 * 
 */
package main;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

import network.NetController;

/**
 * @author Avi Digmi
 *
 */
public class Main {

	/**
	 * @param args
	 * @throws MalformedURLException 
	 * @throws RemoteException 
	 */
	public static void main(String[] args) throws RemoteException, MalformedURLException {

		System.out.println("Forum Starts..");
		
		NetController netController = new NetController();
	}

}
