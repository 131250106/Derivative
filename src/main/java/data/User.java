package data;

import java.io.Serializable;

import java.util.ArrayList;

/**
 * Created by Nifury on 8/13/2015.
 */
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name; // 用户姓名
	private String account; // 用户账号
	private String password; // 用户密码
	private String phone; // 联系方式
	private String address; // 联系地址
	private ArrayList<Order> listofOrders = new ArrayList<>(); // 用户所持有的所有订单

	public User(String name, String user, String password, String phone,
			String address) {
		super();
		this.name = name;
		this.account = user;
		this.password = password;
		this.phone = phone;
		this.address = address;
	}

	public User(String name, String user, String phone, String address) {
		super();
		this.name = name;
		this.account = user;
		this.phone = phone;
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}

	public String getName() {
		return name;
	}

	public String getAccount() {
		return account;
	}

	public String getPassword() {
		return password;
	}
	
	public String toString()
	{
		return "account:\t"+account+"\tname:\t"+name+"\tphone:\t"+phone+"\taddress:\t"+address;
	}
}
