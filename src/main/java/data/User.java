package data;

import java.io.Serializable;

/**
 * Created by Nifury on 8/13/2015.
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
	private String id;				//用户唯一标识符
	private String name;			//用户姓名
	private String user;			//用户账号
	private String password; 	//用户密码

	public User(String id, String name, String user, String password) {
		this.id = id;
		this.name = name;
		this.user = user;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}
}
