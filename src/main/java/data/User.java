package data;

/**
 * Created by Nifury on 8/13/2015.
 */
public class User {
	private String id;				//�û�Ψһ��ʶ��
	private String name;			//�û�����
	private String user;			//�û��˺�
	private String password; 	//�û�����

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
