package bl;

import java.util.Date;

import data.Option;
import data.Order;
import dataTool.DBTool;
import dataservice.DBService;

public class OrderManage { // 所有客户订单的管理
	private static OrderManage orderManage;
	private DBService dbtool;
	
	private OrderManage() {
			dbtool = DBTool.getInstance();
	}

	public static OrderManage getInstance() {
		if (orderManage != null) {
			return orderManage;
		} else {
			orderManage = new OrderManage();
			return orderManage;
		}
	}
	
	public Order[] getAllOrders() {												//得到所有订单
		return dbtool.getAllOrder();
	}
	
	public Order[] getOrdersByAccount(String account) {				//根据客户账号得到订单
		return dbtool.findOrder(account, null, null);
	}

	public boolean addOrder(Option option, int number, String ClientID,
			Date deadline, double executeprice, double dealprice) {// 参数：option为卖出的期权类型，number为卖出的数量，ClientID用户唯一标识符
																	// ，deadline
																	// 截止日期，executeprice为执行价格，dealprice为买卖价
		boolean isOpen = true;
		Order order = new Order(ClientID, option, deadline, executeprice, dealprice, number,isOpen);
		return dbtool.addOrder(order);
	}
}
