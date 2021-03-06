package blservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

import data.EorA;
import data.Option;
import data.Order;
import data.OrderOFholdings;
import data.User;
import data.upORdown;

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
	String register(String username, String password, String name) throws RemoteException;

	/**
	 * 获取普通期权买卖价,传入参数：executeprice（执行价格），deadline（距离到期日时间），ClientID用户唯一标识符 
	 * 返回值，double数组，第一个为买价，第二个为卖价
	 * */
	double[] getCommonPurchasePrice(EorA eora, upORdown upordown,double executeprice, Date deadline,String ClientID)
			throws RemoteException;

	/**
	 * 获取二元期权(资产或无价值期权)买卖价,传入参数：executeprice（执行价格），deadline（距离到期日时间），ClientID用户唯一标识符
	 * 返回值，double数组，第一个为买价，第二个为卖价
	 * */
	double[] getBinaryPurchasePrice1(EorA eora, upORdown upordown,double executeprice,  Date deadline,String ClientID)
			throws RemoteException;
	
	/**
	 * 获取二元期权(现金或无价值期权)买卖价,传入参数：executeprice（执行价格），deadline（距离到期日时间），ClientID用户唯一标识符
	 * 返回值，double数组，第一个为买价，第二个为卖价
	 * */
	double[] getBinaryPurchasePrice2(EorA eora, upORdown upordown,double executeprice,  Date deadline,String ClientID)
			throws RemoteException;
	
	/**
	 * 获取回望固定期权买卖价,传入参数：executeprice（执行价格），deadline（距离到期日时间），ClientID用户唯一标识符 
	 * 返回值，double数组，第一个为买价，第二个为卖价
	 * */
	double[] getlookbackfixedPrice(EorA eora, upORdown upordown,double executeprice, Date deadline,String ClientID)
			throws RemoteException;
	
	/**
	 * 获取回望浮动期权买卖价,传入参数：executeprice（执行价格），deadline（距离到期日时间），ClientID用户唯一标识符 
	 * 返回值，double数组，第一个为买价，第二个为卖价
	 * */
	double[] getlookbackfloatPrice(EorA eora, upORdown upordown,double executeprice, Date deadline,String ClientID)
			throws RemoteException;
	
	/**
	 * 获取亚式期权(平均价格期权)买卖价,传入参数：executeprice（执行价格），deadline（距离到期日时间），ClientID用户唯一标识符 
	 * 返回值，double数组，第一个为买价，第二个为卖价
	 * */
	double[] getSubtypeAveragePricePrice(EorA eora, upORdown upordown,double executeprice, Date deadline,String ClientID)
			throws RemoteException;
	
	/**
	 * 获取亚式期权(平均执行价格期权)买卖价,传入参数：executeprice（执行价格），deadline（距离到期日时间），ClientID用户唯一标识符 
	 * 返回值，double数组，第一个为买价，第二个为卖价
	 * */
	double[] getSubtypeAverageStrikePrice(EorA eora, upORdown upordown,double executeprice,  Date deadline,String ClientID)
			throws RemoteException;
	
	/**
	 * 获取障碍期权（向下敲入）买卖价,传入参数：executeprice（执行价格），deadline（距离到期日时间），ClientID用户唯一标识符 ，rate（障碍水平）
	 * 返回值，double数组，第一个为买价，第二个为卖价
	 * */
	double[] getObstaclePurchasedownandinPrice(EorA eora, upORdown upordown,double executeprice, Date deadline,double rate,String ClientID)
			throws RemoteException;
	
	/**
	 * 获取障碍期权（向上敲入）买卖价,传入参数：executeprice（执行价格），deadline（距离到期日时间），ClientID用户唯一标识符 ，rate（障碍水平）
	 * 返回值，double数组，第一个为买价，第二个为卖价
	 * */
	double[] getObstaclePurchaseupandinPrice(EorA eora, upORdown upordown,double executeprice, Date deadline,double rate,String ClientID)
			throws RemoteException;
	
	/**
	 * 获取障碍期权（向下敲出）买卖价,传入参数：executeprice（执行价格），deadline（距离到期日时间），ClientID用户唯一标识符 ，rate（障碍水平）
	 * 返回值，double数组，第一个为买价，第二个为卖价
	 * */
	double[] getObstaclePurchasedownandoutPrice(EorA eora, upORdown upordown,double executeprice, Date deadline,double rate,String ClientID)
			throws RemoteException;
	
	/**
	 * 获取障碍期权（向上敲出）买卖价,传入参数：executeprice（执行价格），deadline（距离到期日时间），ClientID用户唯一标识符 ，rate（障碍水平）
	 * 返回值，double数组，第一个为买价，第二个为卖价
	 * */
	double[] getObstaclePurchaseupandoutPrice(EorA eora, upORdown upordown,double executeprice, Date deadline,double rate,String ClientID)
			throws RemoteException;
	
	
	/**
	 * 购买期权，参数：option为购买的期权类型，number为购买的数量,ClientID用户唯一标识符，deadline 截止日期，executeprice为执行价格，dealprice为买价
	 *  返回值一张Order订单的所有信息
	 * */
	Order purchaseOption(Option option, int number, String ClientID,
			Date deadline, double executeprice, double dealprice)
			throws RemoteException;

	/**
	 * 确认购买期权
	 * 返回值为true，表明购买成功
	 * */
	boolean InsurePurchase(Order order)throws RemoteException;
	
	
	/**
	 * 卖出期权，参数：option为卖出的期权类型，number为卖出的数量，ClientID用户唯一标识符 ，deadline 截止日期，executeprice为执行价格，dealprice为卖价
	 * 返回值一张Order订单的所有信息
	 * */
	Order sellOption(Option option, int number, String ClientID,
			Date deadline, double executeprice, double dealprice)
			throws RemoteException;

	
	/**
	 * 确认购卖期权
	 * 返回值为true，表明卖出成功
	 * */
	boolean InsureSell(Order order) throws RemoteException;
	
	
	/**
	 * 根据期权得到该期权的当前价格(股指)；参数：期权类型,ClientID用户唯一标识符 
	 * 返回值，该期权当前价格
	 * */
	double getPresentPriceByOption(Option option)
			throws RemoteException;
	
	/**
	 * 通过客户ID(账号)得到该用户所有订单
	 * */
	public Order[] getOrdersByAccount(String account) throws RemoteException;
	
	/**
	 * 通过客户ID(账号)得到该用户所有持仓记录
	 * */
	public OrderOFholdings[] getOrderOFholdingsByAccount(String account) throws RemoteException;
	
}
