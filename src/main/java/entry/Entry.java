package entry;

import bl.ServiceImpl;
import blservice.Service;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Nifury on 8/13/2015.
 */
public class Entry {
    public static void main(String[] args) {
        try {
            Service service = (Service) UnicastRemoteObject.exportObject(new ServiceImpl(), 0);
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("service", service);
        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
