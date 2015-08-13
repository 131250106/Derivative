package blservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import data.User;

/**
 * Created by Nifury on 8/13/2015.
 */
public interface Service extends Remote {
	/**
	 * 登录功能，传入用户账号密码
	 * */
    User login(String username, String password) throws RemoteException;
    
    String register(String username, String password,String name);										
    void placeholder() throws RemoteException;								
}
