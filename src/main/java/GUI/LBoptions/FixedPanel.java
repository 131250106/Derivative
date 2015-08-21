package GUI.LBoptions;

import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import GUI.myswing.MyColor;

public class FixedPanel extends LBoptionsPanel{

	private ButtonGroup LookUpAndDown;
	private JRadioButton LookUp;
	private JRadioButton LookDown;
	private ButtonGroup EAndA;
	private JRadioButton Europe;
	private JRadioButton America;
	private JLabel tag;
	public FixedPanel(String name) {
		super("Fixed");

		super.tag.setVisible(false);;
		Font font = new Font("微软雅黑",Font.PLAIN,15);
		
		tag = new JLabel("浮动执行价格期权");
		tag.setSize(175,70);
		tag.setLocation(830,127);
		tag.setFont(font);
		tag.setBackground(MyColor.white);
		tag.setForeground(MyColor.deepblue);
		tag.setVisible(true);
		this.add(tag);
		
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
