package GUI.Login;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import GUI.GraphicController;
import GUI.Loader;
import GUI.MenuPanel;
import GUI.PVoptions.PVoptionsPanel;

public class LoginPanel extends JPanel{
	
	//背景
	private JLabel bg=new JLabel();
	//按钮
	private JButton signIn=new JButton();
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
		bg.setIcon(new ImageIcon("image/background_name_changed.jpg"));
		bg.setBounds(0, -20, 960, 600);
		

		//按钮
		signIn.setSize(129, 48);
		signIn.setIcon(new ImageIcon("image/sign in.png") );
		signIn.setLocation(350, 470);
		signIn.addMouseListener(new MouseAdapter()  {
			public void mouseClicked(MouseEvent e) {
				new Thread(new Load()).start();;
				new Thread(new GUI()).start();;
			}
		});
		
		//文本框

		username.setBounds(372, 306, 250, 30);
		username.setBorder(new EmptyBorder(0,0,0,0));//文本框无边框
		username.setOpaque(false);//文本框透明
		username.setForeground(Color.white);//前景色
		passwords.setBounds(372, 378, 250, 30);
		passwords.setBorder(new EmptyBorder(0,0,0,0));
		passwords.setOpaque(false);
		passwords.setForeground(Color.white);
		
		
//		pvoption.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				GraphicController.changeToPanel(new PVoptionsPanel("PVoptions"));
//			}
//		});
		
		this.add(signIn,0);
		this.add(username,1);
		this.add(passwords,2);
		this.add(bg,3);
	}
    

	static class Load implements Runnable{

		@Override
		public void run() {
			new Loader();
		}
	
	}
	static class GUI implements Runnable{

		@Override
		public void run() {
			GraphicController.changeToPanel(new MenuPanel("MenuPanel"));
		}
	
	}

}
