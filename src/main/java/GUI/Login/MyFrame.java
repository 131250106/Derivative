package GUI.Login;

import java.awt.Container;
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
		this.setSize(WIDTH,HEIGHT);
		this.setLocation(100, 100);
		//设置背景图片
		
		this.container.add(loginPanel);
		System.out.println("done!");
	}
	
	
}
