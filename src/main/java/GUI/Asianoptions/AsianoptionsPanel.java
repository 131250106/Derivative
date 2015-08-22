package GUI.Asianoptions;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;

import GUI.GraphicController;
import GUI.MenuPanel;
import GUI.myswing.MyColor;

public class AsianoptionsPanel extends MenuPanel{

	protected JButton avgexcpriceoption,avgpriceoption;
	protected JLabel tag;
	public AsianoptionsPanel(String name) {
		super("Asianoptions");
		super.asianoption.setBackground(MyColor.deepblue3);
		super.buttonOption.setBackground(MyColor.deepblue2);
		Font font = new Font("微软雅黑",Font.PLAIN,17);
		

		 tag = new JLabel("亚式期权");
			tag.setSize(175,70);
			tag.setLocation(830,127);
			tag.setFont(font);
			tag.setBackground(MyColor.white);
			tag.setForeground(MyColor.deepblue);
			tag.setVisible(true);
			this.add(tag);
		
		avgexcpriceoption = new JButton("平均执行价格期权");
		avgexcpriceoption.setSize(175, 70);
		avgexcpriceoption.setLocation(0,197);
		avgexcpriceoption.setBackground(MyColor.deepblue);
		avgexcpriceoption.setForeground(MyColor.lightblue);
		avgexcpriceoption.setFocusPainted(false);
		avgexcpriceoption.setBorderPainted(false);
		avgexcpriceoption.setFont(font);
		avgexcpriceoption.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				GraphicController.changeToPanel(new AvgExcPricePanel("AvgExcPrice"));
			}
		});
		this.add(avgexcpriceoption);
		
		font = new Font("微软雅黑",Font.PLAIN,20);
		
		avgpriceoption = new JButton("平均价格期权");
		avgpriceoption.setSize(175, 70);
		avgpriceoption.setLocation(0,127);
		avgpriceoption.setBackground(MyColor.deepblue);
		avgpriceoption.setForeground(MyColor.lightblue);
		avgpriceoption.setFocusPainted(false);
		avgpriceoption.setBorderPainted(false);
		avgpriceoption.setFont(font);
		avgpriceoption.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				GraphicController.changeToPanel(new AvgPricePanel("AvgPrice"));
			}
		});
		this.add(avgpriceoption);
	}

}
