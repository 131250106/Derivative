package bl;

import java.util.ArrayList;
import java.util.Date;

import data.Option;
import data.Order;

public class OrderManage { // 所有客户订单的管理

	public ArrayList<Order> getAllOrders() {
		return null;
	}

	public boolean addOrder(Option option, int number, String ClientID,
			Date deadline, double executeprice, double dealprice) {// 参数：option为卖出的期权类型，number为卖出的数量，ClientID用户唯一标识符
																	// ，deadline
																	// 截止日期，executeprice为执行价格，dealprice为买卖价

		return false;
	}
}
