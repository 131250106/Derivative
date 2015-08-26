package team.Derivative;

import java.util.Date;

import data.EorA;
import data.Option;
import data.Order;
import data.upORdown;
import dataTool.DBTool;

public class main2 {
 public static void main(String[] args)
 {
	 DBTool dbTool = (DBTool) DBTool.getInstance();
	 dbTool.getAllOrder();
	 Option option = new Option("普通期权", null, EorA.A, upORdown.down);
		Order order = new Order("100","dingfeng",  option, new Date(), 1, 2, 3,true);
		dbTool.addOrder(order);
 }
}
