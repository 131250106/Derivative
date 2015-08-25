package GUI.Login;

import java.awt.Container;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class MyFrame extends JFrame{
	static int WIDTH = 960;
	static int HEIGHT = 600;
	LoginPanel loginPanel = new LoginPanel();
	//背景图片强行设置
	Container container =  this.getContentPane();
	
	public MyFrame(){
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		// 取得屏幕的高度
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		//设置窗口大小
		this.setSize(960, 600);
		//设置无边框
		setUndecorated(true);
		// 设置窗体出现位置
		this.setLocation((width - 960) / 2, (height - 600) / 2);
		//设置背景图片
		
		this.container.add(loginPanel);
		System.out.println("done!");
	}
	
	
}
