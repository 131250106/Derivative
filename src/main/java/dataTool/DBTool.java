package dataTool;

import data.*;
import dataservice.DBService;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

public class DBTool implements DBService {
	private String url = "jdbc:mysql://127.0.0.1:3306/derivative?useUnicode=true&characterEncoding=utf8";
	private String driver = "com.mysql.jdbc.Driver";
	private String user = "root";
	private String pwd = "";
	private Connection conn;
	private static DBTool dbTool;
	private final static String[] optionNames = { "普通期权", "二元期权 现金或无价值期权","二元期权 资产或无价值期权", "回望期权 浮动执行价格期权",
			"回望期权 固定执行价格期权", "亚式期权 平均价格期权", "亚式期权 平均执行价格期权", "障碍期权 向上敲出期权",
			"障碍期权 向下敲出期权", "障碍期权 向上敲入期权", "障碍期权 向下敲入期权" };

	private DBTool() throws ClassNotFoundException, SQLException,
			ParseException {
		Class.forName(driver);
		conn = DriverManager.getConnection(url, user, pwd);
		dbTool = this;
	}

	public static DBService getInstance() {
		if (dbTool == null) {
			try {
				dbTool = new DBTool();
			} catch (ClassNotFoundException | SQLException | ParseException e) {
				e.printStackTrace();
			}
		}
		return dbTool;
	}


	public boolean hasUser(String account, String password) {
		String sql = "select * from user where account = ? and password = ?";
		boolean result = false;
		try (PreparedStatement statement = conn.prepareStatement(sql)) {
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
		String sql = "insert into user(account,password,name,phone,address) values (?,?,?,?,?)";
		String name = user.getName(); // 用户姓名
		String account = user.getAccount(); // 用户账号
		String password = user.getPassword(); // 用户密码
		String phone = user.getPhone();
		String address = user.getAddress();
		boolean result = false;
		try (PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setString(1, account);
			statement.setString(2, password);
			statement.setString(3, name);
			statement.setString(4, phone);
			statement.setString(5, address);
			statement.execute();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public User findUser(String account) {
		String sql = "select * from user where account = ?";
		User user = null;
		try (PreparedStatement statement = conn.prepareStatement(sql)){
			statement.setString(1, account);
			ResultSet userInfo = statement.executeQuery();
			if (userInfo.next()) {
				user = toUser(userInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	private User toUser(ResultSet resultSet) throws SQLException {
		String account = resultSet.getString("account");
		String name = resultSet.getString("name");
		String phone = resultSet.getString("phone");
		String address = resultSet.getString("address");
		return new User(name, account, phone, address);
	}

	public Order[] getAllOrder() {
		String sql = "select * from `order`";
		Order[] orders = null;
		try (PreparedStatement statement = conn.prepareStatement(sql)) {
			ResultSet results = statement.executeQuery();
			ArrayList<Order> list = new ArrayList<>();
			while (results.next()) {
				list.add(toOrder(results));
			}
			int orderNum = list.size();
			if (orderNum != 0) {
				orders = new Order[orderNum];
				list.toArray(orders);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orders;
	}

	public boolean addOrder(Order order) {
		boolean result = false;
		Option option = order.getOption();
		String client_account = order.getClientid();
		long deadLine = this.toDeadlineTime(order.getDeadline());
		long date = new Date().getTime();
		double dealPrice = order.getDealprice();
		double executePrice = order.getExecuteprice();
		byte updown = (byte) (option.getUpordown() == upORdown.down ? 0 : 1);
		byte area = (byte) (option.getEora() == EorA.A ? 0 : 1);
		byte type = 0;
		byte open = (byte)(order.isOpen() ? 1 : 0);
		String first = option.getFirstClassName();
		String second = option.getSecondClassName();
		double payOff = option.getPayOff();
		double obstacleRate = option.getObstacleRate();
		String orderId = order.getOrderId();
		String temp = second == null ? first : first + " "+second;
        for (String s : optionNames)		
        {
        	if (temp.equals(s))
        		break;
        	++type;
        }
		
		int num = order.getNumber();
		String sql = "insert into `order` (order_id,client_account,deadLine,dealPrice,date,executePrice,updown,area,"
				+ "type,num,payOff,obstacleRate,open)"
				+ " values (?,?,?,?,?,? ,?,?,?,?,?,?,?)";
		try (PreparedStatement statement = conn.prepareStatement(sql)) {
			int index = 1;
			statement.setLong(index++, Long.parseLong(orderId));
			statement.setString(index++, client_account);
			statement.setLong(index++, deadLine);
			statement.setDouble(index++, dealPrice);
			statement.setLong(index++, date);
			statement.setDouble(index++, executePrice);
			statement.setByte(index++, updown);
			statement.setByte(index++, area);
			statement.setByte(index++, type);
			statement.setInt(index++, num);
			statement.setDouble(index++, payOff);
			statement.setDouble(index++, obstacleRate);
			statement.setByte(index++, open);
			statement.execute();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public Order[] findOrder(String account, Date date, Date ddl) {
		String sql = "select * from `order` where ";
		String sql1 = " client_account = ? ";
		String sql2 = " date = ? ";
		String sql3 = " deadLine = ? ";
		String join = " and ";
		Order[] orders = null;
		if (account != null) {
			sql += sql1;
		}

		if (date != null) {
			if (account != null) {
				sql += join;
			}
			sql += sql2;
		}
		if (ddl != null) {
			if (account != null || date != null) {
				sql += join;
			}
			sql += sql3;
		}

		try  {
			PreparedStatement statement = conn.prepareStatement(sql);
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			int index = 1;
			if (account != null) {
				statement.setString(index++, account);
			}
			if (date != null) {
				statement.setString(index++, format.format(date));
			}
			if (ddl != null) {
				statement.setString(index++, format.format(ddl));
			}
			ResultSet results = statement.executeQuery();
			ArrayList<Order> list = new ArrayList<Order>();
			while (results.next()) {
				list.add(toOrder(results));
			}
			int orderSize = list.size();
			if (orderSize != 0) {
				orders = new Order[orderSize];
				list.toArray(orders);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orders;
	}


	private Order toOrder(ResultSet results) {
		Order order = null;
		try {
			Long order_id = results.getLong("order_id");
			String client_account = results.getString("client_account");
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			Date deadLine = format.parse(results.getString("deadLine"));
			double dealPrice = results.getDouble("dealPrice");
			Date date = format.parse(results.getString("date"));
			double executePrice = results.getDouble("executePrice");
			int num = results.getInt("num");
			double payOff = results.getDouble("payOff");
			double obstacleRate = results.getDouble("obstacleRate");
		    byte isOpen = results.getByte("open");
			Option option = toOption(results);
			option.setPayOff(payOff);
			option.setObstacleRate(obstacleRate);
			order = new Order(String.valueOf(order_id),client_account, option, deadLine, executePrice,
					dealPrice, num, isOpen == 0 ? false : true);
			order.setBuyDate(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return order;
	}

	@Override
	public Order findOrderById(String id) {
		String sql = "select * from `order` where order_id = ?";
		Order order = null;
		try (PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setString(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				order = toOrder(resultSet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return order;
	}

	public LinkedList<Price> getPrice(int n) {
		String sql = "select * from price_record order by `time` limit ?";
		LinkedList<Price> prices = new LinkedList<Price>();
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, n);
			ResultSet results = statement.executeQuery();
			while (results.next()) {
				prices.add(toPrice(results));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prices;
	}

	public Price[] getPriceByTime(long time1, long time2) {
		String sql = "select * from price_record where time >= ? and price < ? ";
		Price[] prices = null;
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setLong(1, time1);
			statement.setLong(2, time2);
			ResultSet results = statement.executeQuery();
			ArrayList<Price> priceList = null;
			if (results.next()) {
				priceList = new ArrayList<Price>();
				priceList.add(toPrice(results));
				while (results.next()) {
					priceList.add(toPrice(results));
				}
				prices = new Price[priceList.size()];
				priceList.toArray(prices);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return prices;
	}

	private Price toPrice(ResultSet resultSet) throws SQLException {
		long time = resultSet.getLong("time");
		double price = resultSet.getDouble("price");
		return new Price(time, price);
	}

	public void addPrice(Price price) {
		String sql = "insert into price_record(`time`,price)  values(?,?)";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setLong(1, price.getTime());
			statement.setDouble(2, price.getPrice());
			statement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testDate() {
		String sql = "select date from `order` ";
		Date date = null;
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				date = new Date(resultSet.getLong(1));
			}
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd hh:mm:ss");
			String dateStr = format.format(date);
			System.out.println(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public double getMaxPrice(Date date) {
		String sql = "select max(price) from price_record where time >= ?";
		return getOneDoubleResult(sql, date.getTime());
	}

	public double getMinPrice(Date date) {
		String sql = "select min(price) from price_record where time >= ?";
		return this.getOneDoubleResult(sql, date.getTime());
	}

	private double getOneDoubleResult(String sql, long arg) {
		double result = -1;
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setLong(1, arg);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				result = resultSet.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	

	public OrderOFholdings[] getHoldingOrdersByClientId(String account) {
		String sql = "select client_account,type,updown,area,deadLine,sum(num) as total,sum(num*dealPrice)/sum(num* dealPrice/abs(dealPrice)) as cost, executePrice,obstacleRate from `order` where client_account = ? group by `type`,updown,area,deadLine,executePrice,obstacleRate;";
		OrderOFholdings[] holdingOrders = null;
		try
		{
			PreparedStatement statement  = conn.prepareStatement(sql);
			statement.setString(1, account);
			ResultSet results = statement.executeQuery();
			ArrayList<OrderOFholdings> orderList = new ArrayList<OrderOFholdings>();
			while (results.next())
			{
				orderList.add(toHoldingOrder(results));
			}
			if (orderList.size() != 0)
			{
				holdingOrders = new OrderOFholdings[orderList.size()];
				orderList.toArray(holdingOrders);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return  holdingOrders;
	} 
    
	private OrderOFholdings toHoldingOrder(ResultSet result) throws SQLException
	{
		
		Option option = toOption(result);
		int sum = result.getInt("total");
		double cost = result.getDouble("cost");
		Date deadLine = new Date(result.getLong("deadLine"));
		return new OrderOFholdings(result.getString("client_account"),option,deadLine,sum,cost,result.getDouble("executePrice"));
	}
	
	private Option toOption(ResultSet results) throws SQLException
	{
		byte updown = results.getByte("updown");
		byte area = results.getByte("area");
		byte type = results.getByte("type");
		double obstacleRate = results.getDouble("obstacleRate");
		String firstClassName = null;
		String secondClassName = null;
		EorA eora = null;
		String className = optionNames[(int)type];
        String[] classNameArray = className.split(" ");
        firstClassName = classNameArray[0];
        if (classNameArray.length == 2)
        {
        	secondClassName = classNameArray[1];
        }
		EorA[] eoras = { EorA.A, EorA.E };
		upORdown[] upORdowns = { upORdown.down, upORdown.up };
		eora = eoras[area];
		upORdown upordown = upORdowns[updown];
		Option option = new Option(firstClassName, secondClassName, eora,
				upordown);
		option.setObstacleRate(obstacleRate);
		return option;
	}

	public OrderOFholdings[] getHoldingOrders() {
		String sql = "select client_account,type,updown,area,deadLine,sum(num) as total,sum(num*dealPrice)/sum(num* dealPrice/abs(dealPrice)) as cost , executePrice, obstacleRate from `order`  group by `type`,updown,area,deadLine,client_account,executePrice,obstacleRate;";
		OrderOFholdings[] holdingOrders = null;
		try
		{
			PreparedStatement statement  = conn.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			ArrayList<OrderOFholdings> orderList = new ArrayList<OrderOFholdings>();
			while (results.next())
			{
				orderList.add(toHoldingOrder(results));
			}
			if (orderList.size() != 0)
			{
				holdingOrders = new OrderOFholdings[orderList.size()];
				orderList.toArray(holdingOrders);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return  holdingOrders;
	}

	public  synchronized String getOneOrderId() {
		String updateSql = "update order_id_store set id = id + 1";
		String selectSql =  "select id from order_id_store";
		long id = -1;
		try
		{
			PreparedStatement statement = conn.prepareStatement(updateSql);
			ResultSet results = statement.executeQuery(selectSql);
			if (results.next())
			{
				id = results.getLong("id");
			}
			statement.execute();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return String.valueOf(id);
	}
	
	   public  long toDeadlineTime(Date date) 
	   {
		   try{
		   SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
		   SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMdd HHmmss");
		   String dateStr = format1.format(date);
		   Date newDate;
		   newDate = format2.parse(String.valueOf(Long.parseLong(dateStr)+1)+" 000000");
		   System.out.println(format2.format(newDate));
		   return newDate.getTime();
		   }catch (Exception e )
		   {
			   e.printStackTrace();
		   }
		   return -1;
	   }
	
}
