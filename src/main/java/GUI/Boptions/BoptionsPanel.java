package GUI.Boptions;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;

import GUI.GraphicController;
import GUI.MenuPanel;
import GUI.myswing.MyColor;

public class BoptionsPanel extends MenuPanel{

	private JButton boption;
	private ButtonGroup LookUpAndDown;
	private JRadioButton LookUp;
	private JRadioButton LookDown;
	private ButtonGroup EAndA;
	private JRadioButton Europe;
	private JRadioButton America;
	
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
		
		 font = new Font("微软雅黑",Font.PLAIN,15);
			
			LookUpAndDown = new ButtonGroup();
			LookUp = new JRadioButton("看涨");
			LookUp.setFont(font);
			LookDown = new JRadioButton("看跌");
			LookDown.setFont(font);
			LookUpAndDown.add(LookUp);
			LookUpAndDown.add(LookDown);
			
			LookUp.setFocusPainted(false);
			LookUp.setBorderPainted(false);
			LookDown.setFocusPainted(false);
			LookDown.setBorderPainted(false);
			LookUp.setBackground(MyColor.white);
			LookDown.setBackground(MyColor.white);
			LookUp.setForeground(MyColor.black);
			LookDown.setForeground(MyColor.black);
			
			LookUp.setBounds(290, 163, 64, 30);
			LookDown.setBounds(213,163,64,30);		
			LookDown.setVisible(true);
			LookUp.setVisible(true);
			this.add(LookUp);
			this.add(LookDown);
			
			font = new Font("微软雅黑",Font.PLAIN,15);
			
			EAndA = new ButtonGroup();
			Europe = new JRadioButton("欧式");
			Europe.setFont(font);
			America = new JRadioButton("美式");
			America.setFont(font);
			EAndA.add(Europe);
			EAndA.add(America);
			
			Europe.setFocusPainted(false);
			Europe.setBorderPainted(false);
			America.setFocusPainted(false);
			America.setBorderPainted(false);
			Europe.setBackground(MyColor.white);
			America.setBackground(MyColor.white);
			Europe.setForeground(MyColor.black);
			America.setForeground(MyColor.black);
			
			Europe.setBounds(290, 200, 64, 30);
			America.setBounds(213,200,64,30);		
			America.setVisible(true);
			Europe.setVisible(true);
			this.add(Europe);
			this.add(America);
		
	}

}
