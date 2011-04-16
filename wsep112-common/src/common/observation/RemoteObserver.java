package common.observation;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteObserver extends Remote {

    void update(Object observable, Object arg) throws RemoteException;
}