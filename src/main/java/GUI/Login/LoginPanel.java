package GUI.Login;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginPanel extends JPanel implements ActionListener{
	
	//背景
	private JLabel bg=new JLabel();
	//按钮
	private JLabel signIn=new JLabel();
	private JLabel register=new JLabel();
	
	// 用户名输入框
	private JTextField username=new JTextField(16);  
	// 密码输入框
    private JPasswordField passwords=new JPasswordField(16); 
	
public LoginPanel(){
    	
		//设置窗口大小
		this.setSize(960, 600);
		//设置布局
		this.setLayout(null);
		

		//背景
		bg.setIcon(new ImageIcon("src/image/loginBackgroud.png"));
		bg.setBounds(0, -20, 960, 600);
		

		//按钮
		signIn.setSize(129, 48);
		signIn.setIcon(new ImageIcon("src/image/sign in.png") );
		signIn.setLocation(350, 470);
		
		//文本框

		username.setBounds(372, 306, 250, 30);
		username.setBorder(new EmptyBorder(0,0,0,0));//文本框无边框
		username.setOpaque(false);//文本框透明
		username.setForeground(Color.white);//前景色
		passwords.setBounds(372, 378, 250, 30);
		passwords.setBorder(new EmptyBorder(0,0,0,0));
		passwords.setOpaque(false);
		passwords.setForeground(Color.white);
		
		
		this.add(signIn,0);
		this.add(username,1);
		this.add(passwords,2);
		this.add(bg,3);
	}
    
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
	}

}
