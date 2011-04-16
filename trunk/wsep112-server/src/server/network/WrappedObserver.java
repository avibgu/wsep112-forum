package server.network;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Observable;
import java.util.Observer;

import common.observation.RemoteObserver;

public class WrappedObserver implements Observer, Serializable {

	private static final long serialVersionUID = -6143589076085825397L;

	private RemoteObserver _remoteObserver;

    public WrappedObserver(RemoteObserver ro) {
        this.setRemoteObserver(ro);
    }

    @Override
    public void update(Observable o, Object arg) {

    	try {
    		
    		getRemoteObserver().update(null, arg);
        }
    	catch (RemoteException e) {

    		System.out.println("Remote exception removing observer:" + this);
            o.deleteObserver(this);
        }
    }
    
    @Override
    public boolean equals(Object obj){
    	
    	if ( !(obj instanceof WrappedObserver))
    		return false;
    	
    	return ((WrappedObserver)obj).getRemoteObserver().equals(_remoteObserver);
    }

	public void setRemoteObserver(RemoteObserver _remoteObserver) {
		this._remoteObserver = _remoteObserver;
	}

	public RemoteObserver getRemoteObserver() {
		return _remoteObserver;
	}
}
