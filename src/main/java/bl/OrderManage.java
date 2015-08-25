package bl;

import java.util.Date;

import data.Option;
import data.Order;
import data.OrderOFholdings;
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

	public Order[] getAllOrders() { // 得到所有订单
		return dbtool.getAllOrder();
	}

	public Order[] getOrdersByAccount(String account) { // 根据客户账号得到订单
		return dbtool.findOrder(account, null, null);
	}

	public OrderOFholdings[] getAllOrderOFholdings() { // 得到所有客户的持仓记录
		// TODO Auto-generated method stub
		return dbtool.getHoldingOrders();
	}

	public OrderOFholdings[] getOrderOFholdingsByAccount(String account) { // 根据客户账号得到持仓记录
		// TODO Auto-generated method stub
		return dbtool.getHoldingOrdersByClientId(account);
	}

	public boolean addOrder(Option option, int number, String ClientID,
			Date deadline, double executeprice, double dealprice) {// 参数：option为卖出的期权类型，number为卖出的数量，ClientID用户唯一标识符
																	// ，deadline
																	// 截止日期，executeprice为执行价格，dealprice为买卖价
		boolean isOpen = true;
		OrderOFholdings[] temp = getOrderOFholdingsByAccount(ClientID);
		if (temp != null) {
			for (int i = 0; i < temp.length; i++) {
				if (temp[i].getOption().isequal(option)
						&& temp[i].getDeadline() == deadline
						&& temp[i].getExecuteprice() == executeprice) {
					if (temp[i].getNumber() * number < 0) { // 若为相反类型操作，则为平仓
						isOpen = false;
						break;
					}
				}
			}
		}
		Order order = new Order(ClientID, option, deadline, executeprice,
				dealprice, number, isOpen);
		return dbtool.addOrder(order);
	}

}
