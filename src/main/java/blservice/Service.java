package blservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Nifury on 8/13/2015.
 */
public interface Service extends Remote {
    boolean login(String username, String password) throws RemoteException;
    void placeholder() throws RemoteException;
}
