package GUI.Boptions;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import GUI.GraphicController;
import GUI.MenuPanel;
import GUI.Asianoptions.AvgExcPricePanel;
import GUI.Asianoptions.AvgPricePanel;
import GUI.myswing.MyColor;

public class BoptionsPanel extends MenuPanel{

	private JButton boption;
	private JButton cashoption,assetoption;
	protected JLabel tag;
	public BoptionsPanel(String name) {
		super("Boptions");
		
		
		Font font = new Font("微软雅黑",Font.PLAIN,20);
		
		/*boption = new JButton("二元期权");
		boption.setSize(175,70);
		boption.setLocation(0,127);
		boption.setBackground(MyColor.deepblue);
		boption.setForeground(MyColor.lightblue);
		boption.setFocusPainted(false);
		boption.setBorderPainted(false);
		boption.setFont(font);
		boption.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				GraphicController.changeToPanel(new BoptionsPanel("Boptions"));
			}
		});
		this.add(boption);*/
		
	    tag = new JLabel("二元期权");
				tag.setSize(175,70);
				tag.setLocation(830,127);
				tag.setFont(font);
				tag.setBackground(MyColor.white);
				tag.setForeground(MyColor.deepblue);
				tag.setVisible(true);
				this.add(tag);
		
		font = new Font("微软雅黑",Font.PLAIN,17);
		
		cashoption = new JButton("现金或无价值期权");
		cashoption.setSize(175, 70);
		cashoption.setLocation(0,197);
		cashoption.setBackground(MyColor.deepblue);
		cashoption.setForeground(MyColor.lightblue);
		cashoption.setFocusPainted(false);
		cashoption.setBorderPainted(false);
		cashoption.setFont(font);
		cashoption.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				tag.setVisible(false);
				GraphicController.changeToPanel(new cashPanel("cash"));
			}
		});
		this.add(cashoption);
		
		
		assetoption = new JButton("资产或无价值期权");
		assetoption.setSize(175, 70);
		assetoption.setLocation(0,127);
		assetoption.setBackground(MyColor.deepblue);
		assetoption.setForeground(MyColor.lightblue);
		assetoption.setFocusPainted(false);
		assetoption.setBorderPainted(false);
		assetoption.setFont(font);
		assetoption.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				GraphicController.changeToPanel(new AssetPanel("asset"));
			}
		});
		this.add(assetoption);
		
		 font = new Font("微软雅黑",Font.PLAIN,15);
			
			
		
	}

}
