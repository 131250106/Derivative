package serverGUI;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import bl.ServiceImpl;
import blservice.Service;

public class serverUImain {
	public static void main(String[]args){
		try {
            Service service = (Service) UnicastRemoteObject.exportObject(new ServiceImpl(), 0);
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("service", service);
        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        }
		new Thread(new Load()).start();;
		new Thread(new GUI()).start();;
	}
	static class Load implements Runnable{

		@Override
		public void run() {
			new serverLoader();
		}
		
	}
	static class GUI implements Runnable{

		@Override
		public void run() {
			GraphicControllerServer.changeToPanel(new MenuPanel("menu"));
		}
		
	}
}
