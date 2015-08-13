package blservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import data.User;

/**
 * Created by Nifury on 8/13/2015.
 */
public interface Service extends Remote {
	/**
	 * 登录功能，传入用户账号密码，以User的形式返回该用户所有信息（若登陆失败，返回的User为null）
	 * */
    User login(String username, String password) throws RemoteException;
    /**
     * 注册功能，传入注册用户的用户名username，密码password，姓名name，返回结果为String
     * 若返回值为“success”，则表示注册成功，若为“Username already exists.”，则表示该用户已存在，注册失败
     * */
    String register(String username, String password,String name);										
    /**
     * 获取期权买价
     * */
    double getPurchasePrice()  throws RemoteException;
    /**
     * 获取期权卖价
     * */
    double getPrice()  throws RemoteException;
}
