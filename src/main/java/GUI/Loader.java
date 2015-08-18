package GUI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import bl.ServiceImpl;
import blservice.Service;
import GUI.PVoptions.PVoptionsPanel;

public class Loader {
	//public static MenuPanel pvpanel= new PVoptionsPanel("PVoptions");
	public static Service service;
	static{
		try {
			service = (Service) Naming.lookup("service");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			System.out.println("Network Wrong");
			e.printStackTrace();
		}
	}
}
