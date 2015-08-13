package data;

import java.util.ArrayList;

/**
 * Created by Nifury on 8/13/2015.
 */
public class User {
	private String id;				//用户唯一标识符
	private String name;			//用户姓名
	private String user;			//用户账号
	private String password; 	//用户密码
	
	private ArrayList<Order> listofOrders;			//用户所持有的所有订单

	
	public User(String id, String name, String user, String password,
			ArrayList<Order> listofOrders) {
		super();
		this.id = id;
		this.name = name;
		this.user = user;
		this.password = password;
		this.listofOrders = listofOrders;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<Order> getListofOrders() {
		return listofOrders;
	}

	public void setListofOrders(ArrayList<Order> listofOrders) {
		this.listofOrders = listofOrders;
	}


}
