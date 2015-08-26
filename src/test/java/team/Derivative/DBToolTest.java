package team.Derivative;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import data.EorA;
import data.Option;
import data.Order;
import data.User;
import data.upORdown;
import dataTool.DBTool;
import dataservice.DBService;

public class DBToolTest {
	DBService dbTool;

	@Before
	public void setUp() {
			dbTool = DBTool.getInstance();
	}

	@Test
	public void testHasUser() {
//		fail("Not yet implemented");
		String account = "dingfeng";
		String password = "01";
		boolean result = false;
		if (dbTool.hasUser(account, password)) {
			result = true;
		}
		assertEquals(result, true);
	}

	@Test
	public void testAddUser() {
//		fail("Not yet implemented");
		String account = "dingfeng";
		String password = "01";
		String name = "丁峰";
		String address = "南京";
		String phone = "885364321";
		User user = new User(name, account, password, phone, address);
		boolean result = dbTool.addUser(user);
        assertEquals(result,true);
	}

	@Test
	public void testFindUser() {
//		fail("Not yet implemented");
		String account = "dingfeng";
		User user = dbTool.findUser(account);
	    System.out.println(user);
	    assertEquals(true,true);
	}

	@Test
	public void testGetAllOrder() {
//		fail("Not yet implemented");
		Order[] orders = dbTool.getAllOrder();
		if (orders != null)
		for (Order r : orders)
		{
			System.out.println(r);
		}
		assertEquals(true,true);
	}

	@Test
	public void testAddOrder() {
//		fail("Not yet implemented");
		Option option = new Option("普通期权", null, EorA.A, upORdown.down);
		Order order = new Order("100","dingfeng",  option, new Date(), 1, 2, 3,true);
		dbTool.addOrder(order);
	}

	@Test
	public void testFindOrder() {
//		fail("Not yet implemented");
		Order[] orders = dbTool.findOrder("dingfeng", null, null);
		if (orders != null)
		{
			for (Order r : orders)
			{
				System.out.println(r);
			}
		}
		assertEquals(true,true);
	}
	
	@Test 
	public void testFindOrderById()
	{
		
	}
    
	@Test
	public void testMax() throws ParseException
	{
		SimpleDateFormat format  = new SimpleDateFormat("yyyyMMdd hhmmss");
		String dateStr = "19941201 120000";
			DBTool dbTool = (DBTool) DBTool.getInstance();
			System.out.println("max price : "+ dbTool.getMaxPrice(format.parse(dateStr)));
		
	}
	
	@Test 
	public void testMin() throws ParseException
	{
		SimpleDateFormat format  = new SimpleDateFormat("yyyyMMdd hhmmss");
		String dateStr = "19941201 120000";
			DBService dbTool = (DBTool) DBTool.getInstance();
			System.out.println("min price : "+ dbTool.getMinPrice(format.parse(dateStr)));
	}
	
	@Test
	public void testHoldingOrders()
	{
		System.out.println(Arrays.toString(dbTool.getHoldingOrdersByClientId("dingfeng")));
	}
	
	@Test 
	public void testAllHoldings()
	{
		System.out.println("all orders : ");
		System.out.println(Arrays.toString(dbTool.getHoldingOrders()));
	}
	@Test 
	public void testAlldHoldings()
	{
		dbTool.findOrder("131250131", null, null);
	}
}
