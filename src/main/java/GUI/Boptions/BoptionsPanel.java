package GUI.Boptions;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import GUI.GraphicController;
import GUI.MenuPanel;
import GUI.myswing.MyColor;

public class BoptionsPanel extends MenuPanel{

	private JButton boption;
	
	public BoptionsPanel(String name) {
		super("Boptions");
		
		Font font = new Font("微软雅黑",Font.PLAIN,20);
		
		boption = new JButton("二元期权");
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
		this.add(boption);
	}

}
