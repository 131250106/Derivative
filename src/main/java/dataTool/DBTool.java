package dataTool;

import data.*;
import dataservice.DBService;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class DBTool implements DBService {
	private String url = "jdbc:mysql://127.0.0.1:3306/derivative?useUnicode=true&characterEncoding=utf8";
	private String driver = "com.mysql.jdbc.Driver";
	private String user = "root";
	private String pwd = "";
	private Connection conn;
	private static AtomicInteger id;
	private static DBTool dbTool;
	private final static String[] optionNames = { "普通期权", "二元期权", "回望期权 浮动执行价格期权",
			"回望期权 固定执行价格期权", "亚式期权 平均价格期权", "亚式期权 平均执行价格期权", "障碍期权 向上敲出期权",
			"障碍期权 向下敲出期权", "障碍期权 向上敲入期权", "障碍期权 向下敲入期权", "障碍期权 双重障碍期权","障碍期权 多次触及障碍水平期权" };

	private DBTool() throws ClassNotFoundException, SQLException,
			ParseException {
		Class.forName(driver);
		conn = DriverManager.getConnection(url, user, pwd);
		initId();
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

	private void initId() throws ParseException {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String currentDateStr = format.format(date);
		String start = currentDateStr + " 000000";
		String end = currentDateStr + " 240000";
		SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMdd hhmmss");
		Date date_start = format2.parse(start);
		Date date_end = format2.parse(end);
		String sql = "select count(*) from `order` where date >= ? and date < ?";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setLong(1, date_start.getTime());
			statement.setLong(2, date_end.getTime());
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				id = new AtomicInteger(resultSet.getInt(1) + 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		try (PreparedStatement statement = conn.prepareStatement(sql)) {
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
		String order_id = this.generateOrderId();
		String client_account = order.getClientid();
		long deadLine = order.getDeadline().getTime();
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
				+ " values (?,?,?,?,? ,?,?,?,?,?,?,?,?)";
		try (PreparedStatement statement = conn.prepareStatement(sql)) {
			int index = 1;
			statement.setString(index++, order_id);
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

		try (PreparedStatement statement = conn.prepareStatement(sql)) {
			int index = 1;
			if (account != null) {
				statement.setString(index++, account);
			}
			if (date != null) {
				statement.setDate(index++, convertDate(date));
			}
			if (ddl != null) {
				statement.setDate(index++, convertDate(ddl));
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

	private java.sql.Date convertDate(Date date) {
		return new java.sql.Date(date.getTime());
	}

	private Order toOrder(ResultSet results) {
		Order order = null;
		try {
			String order_id = results.getString("order_id");
			String client_account = results.getString("client_account");
			Date deadLine = new Date(results.getLong("deadLine"));
			double dealPrice = results.getDouble("dealPrice");
			Date date = new Date(results.getLong("date"));
			double executePrice = results.getDouble("executePrice");
			int num = results.getInt("num");
			double payOff = results.getDouble("payOff");
			double obstacleRate = results.getDouble("obstacleRate");
		    byte isOpen = results.getByte("open");
			Option option = toOption(results);
			option.setPayOff(payOff);
			option.setObstacleRate(obstacleRate);
			order = new Order(client_account, option, deadLine, executePrice,
					dealPrice, num, isOpen == 0 ? false : true);
			order.setOrderId(order_id);
			order.setBuyDate(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return order;
	}

	private String generateOrderId() {

		String orderId = null;
		Date utilDate = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-");
		String dateStr = format.format(utilDate);
		orderId = dateStr + id.incrementAndGet();
		return orderId;
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
		String sql = "select type,updown,area,deadLine,sum(num) as total,sum(num*dealPrice)/sum(num* dealPrice/abs(dealPrice)) as cost from `order` where client_account = ? group by `type`,updown,area,deadLine;";
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
		return new OrderOFholdings(option,deadLine,sum,cost);
	}
	private Option toOption(ResultSet results) throws SQLException
	{
		byte updown = results.getByte("updown");
		byte area = results.getByte("area");
		byte type = results.getByte("type");
		String firstClassName = null;
		String secondClassName = null;
		EorA eora = null;
		String className = optionNames[type];
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
		return new Option(firstClassName, secondClassName, eora,
				upordown);
	}
}
