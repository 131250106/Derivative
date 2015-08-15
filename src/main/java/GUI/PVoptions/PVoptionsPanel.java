package GUI.PVoptions;


import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.invoke.MethodHandles.Lookup;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import GUI.GraphicController;
import GUI.MenuPanel;
import GUI.myswing.MyColor;

public class PVoptionsPanel extends MenuPanel{
	
	private JButton pvoption;
	private JLabel Name;
	private ButtonGroup LookUpAndDown;
	private JRadioButton LookUp;
	private JRadioButton LookDown;
	private ButtonGroup EAndA;
	private JRadioButton Europe;
	private JRadioButton America;
	
	public PVoptionsPanel(String name) {
		super("PVoptions");
		
		Font font = new Font("微软雅黑",Font.PLAIN,20);
		
		pvoption = new JButton("普通期权");
		pvoption.setSize(175, 70);
		pvoption.setLocation(0,127);
		pvoption.setBackground(MyColor.deepblue);
		pvoption.setForeground(MyColor.lightblue);
		pvoption.setFocusPainted(false);
		pvoption.setBorderPainted(false);
		pvoption.setFont(font);
		pvoption.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				GraphicController.changeToPanel(new PVoptionsPanel("PVoptions"));
			}
		});
		this.add(pvoption);
		
		
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
