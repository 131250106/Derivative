package GUI.Asianoptions;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import GUI.MenuPanel;
import GUI.myswing.MyColor;

public class AsianoptionsPanel extends MenuPanel{

	private JButton avgexcpriceoption,avgpriceoption;
	
	public AsianoptionsPanel(String name) {
		super("Asianoptions");
		Font font = new Font("΢���ź�",Font.PLAIN,17);
		
		avgexcpriceoption = new JButton("ƽ��ִ�м۸���Ȩ");
		avgexcpriceoption.setSize(175, 70);
		avgexcpriceoption.setLocation(0,197);
		avgexcpriceoption.setBackground(MyColor.deepblue);
		avgexcpriceoption.setForeground(MyColor.lightblue);
		avgexcpriceoption.setFocusPainted(false);
		avgexcpriceoption.setBorderPainted(false);
		avgexcpriceoption.setFont(font);
		avgexcpriceoption.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			}
		});
		this.add(avgexcpriceoption);
		
		font = new Font("΢���ź�",Font.PLAIN,20);
		
		avgpriceoption = new JButton("ƽ���۸���Ȩ");
		avgpriceoption.setSize(175, 70);
		avgpriceoption.setLocation(0,127);
		avgpriceoption.setBackground(MyColor.deepblue);
		avgpriceoption.setForeground(MyColor.lightblue);
		avgpriceoption.setFocusPainted(false);
		avgpriceoption.setBorderPainted(false);
		avgpriceoption.setFont(font);
		avgpriceoption.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			}
		});
		this.add(avgpriceoption);
	}

}
