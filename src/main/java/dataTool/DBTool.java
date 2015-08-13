package dataTool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import data.EorA;
import data.Option;
import data.Order;
import data.User;
import data.upORdown;
import dataservice.DBService;

public class DBTool implements DBService {
	private String url = "jdbc:mysql://127.0.0.1:3306/derivative?useUnicode=true&characterEncoding=utf8";
	private String driver = "com.mysql.jdbc.Driver";
	private String user = "root";
	private String pwd = "";
	private Connection conn;
  
	public DBTool() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		conn = DriverManager.getConnection(url, user, pwd);
	}

	public boolean hasUser(String account, String password) {
		String sql = "select from user where account = ? and password = ?";
		boolean result = false;
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, account);
			statement.setString(2, password);
			ResultSet results = statement.executeQuery();
			if (results.next()) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean addUser(User user) {
		String sql = "insert into user(account,password,name) values (?,?,?)";
		String name = user.getName(); // 用户姓名
		String account = user.getUser(); // 用户账号
		String password = user.getPassword(); // 用户密码
		boolean result = false;
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, account);
			statement.setString(2, password);
			statement.setString(3, name);
			result = statement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public User findUser(String account) {
		String sql = "select * from user where account = ?";
		User user = null;
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, account);
			ResultSet userInfo = statement.executeQuery();
			if (userInfo.next()) {
                user  =  toUser(userInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	private User toUser(ResultSet resultSet) throws SQLException 
	{
     String account = resultSet.getString("account");
     String name = resultSet.getString("name");
     return new User(null,name,account,null);
	}

	
	public Order[] getAllOrder() {
		String sql = "select * from order";
		Order[] orders =  null;
		try
		{
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			ArrayList<Order> list  = new ArrayList<Order>();
			while (results.next())
			{
				list.add(toOrder(results));
			}
			int orderNum = list.size();
			if (orderNum != 0)
			{
				orders = new Order[orderNum];
				list.toArray(orders);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return orders;
	}
	
	public boolean addOrder(Order order) {
		boolean result = false;
		Option option = order.getOption();
		String order_id = this.generateOrderId();
		String client_account = order.getClientid();
		java.sql.Date deadLine = convertDate(order.getDeadline());
		double dealPrice  = order.getDealprice();
		java.sql.Date date  = new java.sql.Date(new Date().getTime());
		double executePrice = order.getExecuteprice();
		byte updown  = (byte) (option.getUpordown() == upORdown.down ? 0 : 1);
		byte area = (byte) (option.getEora() == EorA.A ? 0 : 1);
		byte type = 0;
		String first = option.getFirstClassName();
		String second = option.getSecondClassName();
		//确定type的数值
		if (first != null)
		{
			switch (first)
			{
			
			}
		}
		else 
		{
			switch (second)
			{
			
			}
		}
		int num = order.getNumber();
       String sql = "insert into order (order_id,client_account,deadLine,dealPrice,date,executePrice,updown,area,type,num )"
       		+ " values (?,?,?,?,? ,?,?,?,?,?)";
       try
       {
    	   PreparedStatement statement = conn.prepareStatement(sql);
    	   int index = 1;
    	   statement.setString(index++, order_id);
    	   statement.setString(index++, client_account);
    	   statement.setDate(index++, deadLine);
    	   statement.setDouble(index++, dealPrice);
    	   statement.setDate(index++, date);
    	   statement.setDouble(index++, executePrice);
    	   statement.setByte(index++, updown);;
    	   statement.setByte(index++, area);
    	   statement.setByte(index++, type);
    	   statement.setInt(index++, num);
    	   result = statement.execute();
       }
       catch (Exception e)
       {
    	   e.printStackTrace();
       }
		
		return result;
	}
	
	

	public Order[] findOrder(String account, Date date, Date ddl) {
		String sql = "select * from order where ";
		String sql1 = "account = ? ";
		String sql2 = " date = ? ";
		String sql3 = " deadLine = ? ";
		String join = " and ";
		Order[] orders = null;
		if (account != null)
		{
			sql += sql1;
		}
		
		if (date != null)
		{
			if (account != null)
			{
				sql += join;
			}
			sql += sql2;
		}
		if (ddl != null)
		{
			if (account != null || date != null)
			{
				sql += join;
			}
			sql += sql3;
		}
		
		try 
		{
			PreparedStatement statement = conn.prepareStatement(sql);
			int index = 1;
			if (account != null)
			{
				statement.setString(index++, account);
			}
			if (date != null)
			{
				statement.setDate(index++, convertDate(date));
			}
			if (ddl != null)
			{
				statement.setDate(index++, convertDate(ddl));
			}
			ResultSet results = statement.executeQuery();
			ArrayList<Order> list = new ArrayList<Order>();
			while (results.next())
			{
				list.add(toOrder(results));
			}
			int orderSize = list.size();
			if (orderSize != 0 )
			{
				orders = new Order[orderSize];
				list.toArray(orders);
			}
		}
		catch (Exception e )
		{
			e.printStackTrace();
		}
		return orders;
	}
	
	
	private java.sql.Date convertDate ( Date date)
	{
		return new java.sql.Date(date.getTime());
	}

	private Order toOrder (ResultSet results )
	{
		Order order = null;
		try {
		String order_id = results.getString("order_id");
		String client_account = results.getString("client_account");
		java.sql.Date deadLine = results.getDate("deadLine");
		double dealPrice = results.getDouble("dealPrice");
		java.sql.Date date = results.getDate("date");
		double executePrice = results.getDouble("executePrice");
		int updown = results.getString("updown").charAt(0);
		int area = results.getString("area").charAt(0);
		int type  = results.getString("type").charAt(0);
		int num = results.getInt("num");
		String firstClassName = null; 
		String secondClassName = null; 
		EorA eora = null;
		upORdown upordown = null;
		//负的表示第一类
		//正的表示第二类
		String [] firsts = {};
		String [] seconds = {};
		if (type > 0)
		{
			firstClassName = firsts[type - 1];
		}
		else 
		{
			secondClassName = seconds[type - 1];
		}
        EorA[] eoras = {EorA.A,EorA.E};
        upORdown[] upORdowns = {upORdown.down,upORdown.up};
        eora = eoras[area];
        upordown =  upORdowns[updown];
        Option option = new Option(firstClassName,secondClassName,eora,upordown);
        order = new Order (client_account, option, deadLine,  executePrice,
                dealPrice,  num,  0,  0,  0,  0);
		}catch(Exception e )
		{
			e.printStackTrace();
		}
		return order;
	}
	
	
    private String generateOrderId()
    {
    	String orderId = null;
    	Date utilDate = new Date();
    	java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-");
    	String dateStr = format.format(utilDate);
    	String sql = "select count(*) from order where date = ?";
    	int count = -1;
    	try
    	{
    		PreparedStatement statement  = conn.prepareStatement(sql);
    		statement.setDate(1, sqlDate);
    		ResultSet result = statement.executeQuery();
    		if (result.next())
    		{
    			count = result.getInt(1);
    		}
    		if (count != -1)
    		{
    			orderId = dateStr;
    			orderId += String.valueOf(count);
    		}
    	}
    	catch (Exception e )
    	{
    		e.printStackTrace();
    	}
    	return orderId;
    }


}
