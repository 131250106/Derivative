package blservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import data.Option;
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
     * 获取期权买卖价,传入参数：executeprice（执行价格），rate（无风险利率），deadline（距离到期日时间），其他（暂时没有）
     * 返回值，double数组，第一个为买价，第二个为卖价
     * */
    double[]  getPurchasePrice(double executeprice,double rate,double deadline)  throws RemoteException;
    
    /**
     * 购买期权，参数：option为购买的期权类型，number为购买的数量
     * 返回值，true代表购买成功，false代表失败
     * */
    boolean  purchaseOption(Option option,int number)  throws RemoteException;
    
    /**
     * 卖出期权，参数：option为卖出的期权类型，number为卖出的数量
     * 返回值，true代表卖出成功，false代表失败
     * */
    boolean  sellOption(Option option,int number)  throws RemoteException;
    
    /**
     * 根据期权得到该期权的当前价格；参数：期权类型
     * 返回值，该期权当前价格
     * */
    double getPresentPriceByOption(Option option)  throws RemoteException;
}
