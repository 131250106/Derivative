package dataservice;

import java.util.Date;

import data.Order;
import data.OrderOFholdings;
import data.User;

public interface DBService {
	
	   //验证密码
       public boolean hasUser(String account, String password);
       //增加用户
       public boolean addUser(User user);
       //查找用户
       public User findUser (String account);
       //添加订单
       public boolean addOrder (Order order);
       //获得所有订单
       public Order[] getAllOrder();
       //通过客户编号查找所有订单 不作为查找条件的参数，传null
       public Order[] findOrder (String account, Date date, Date ddl);
       //根据单据编号查找
       public Order findOrderById (String id);
       //获得某日起最大股指数
       public double getMaxPrice(Date date);
       //获得某日起最小股指数
       public double getMinPrice(Date date);
       //获得某个用户的持仓记录
       public OrderOFholdings[] getHoldingOrdersByClientId(String account);
       //获得所有用户的持仓记录
       public OrderOFholdings[] getHoldingOrders();
       //获得订单号
       public String getOneOrderId();
       
       
}
