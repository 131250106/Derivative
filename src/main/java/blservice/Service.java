package blservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Nifury on 8/13/2015.
 */
public interface Service extends Remote {
    boolean login(String username, String password) throws RemoteException;//µÇÂ¼
    String register(String username, String password,String name);
    void placeholder() throws RemoteException;								
}
