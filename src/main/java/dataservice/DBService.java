package dataservice;

import java.util.Date;

import data.Order;
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
       //通过客户编号查找所有订单
       public Order[] findOrder (String account, Date date, Date ddl);
}
