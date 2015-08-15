package GUI.PVoptions;


import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.invoke.MethodHandles.Lookup;
import java.nio.file.SecureDirectoryStream;
import java.util.function.DoubleToLongFunction;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import GUI.GraphicController;
import GUI.MenuPanel;
import GUI.myswing.CommonTextPanel;
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
	private CommonTextPanel commonTextPanel;
	
	static int PANEL_WIDTH = 960;
	static int PANEL_HEIGHT = 600;
	static int LABEL_WIDTH = 70;
	static int LABEL_HEIGHT = 25;
	static int TEXTFIELD_WIDTH = 70;
	static int TEXTFIELD_HEIGHT = 22;
	
	JLabel executePriceLabel,noRiskRateLabel,deadlineLabel;
	JTextField executePriceField,noRiskRateField,deadlineField;
	JButton submitButton;
	
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
		
		executePriceLabel = new JLabel("执行价格:");
		executePriceLabel.setFont(font);
		executePriceLabel.setBounds(200, 240, LABEL_WIDTH, LABEL_HEIGHT);
		executePriceLabel.setVisible(true);
		this.add(executePriceLabel);
		
		executePriceField = new JTextField();
		executePriceField.setBounds(280, 240, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		executePriceField.setVisible(true);
		executePriceField.setFont(font);
		this.add(executePriceField);
		
		noRiskRateLabel = new JLabel("无风险利率:");
		noRiskRateLabel.setFont(font);
		noRiskRateLabel.setBounds(200, 280, LABEL_WIDTH, LABEL_HEIGHT);
		noRiskRateLabel.setVisible(true);
		this.add(noRiskRateLabel);
		
		noRiskRateField = new JTextField();
		noRiskRateField.setFont(font);
		noRiskRateField.setBounds(280, 280, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		noRiskRateField.setVisible(true);
		this.add(noRiskRateField);
		
		//需要处理计算一下
		deadlineLabel = new JLabel("截止日期:");
		deadlineLabel.setFont(font);
		deadlineLabel.setBounds(200,320, LABEL_WIDTH, LABEL_HEIGHT);
		deadlineLabel.setVisible(true);
		this.add(deadlineLabel);
		
		deadlineField = new JTextField();
		deadlineField.setFont(font);
		deadlineField.setBounds(280, 320, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		deadlineField.setVisible(true);
		this.add(deadlineField);
		
		submitButton = new JButton("查询");
		submitButton.setFont(font);
		submitButton.setSize(80, 30);
		submitButton.setLocation(200,400);
		submitButton.setBackground(MyColor.deepblue);
		submitButton.setForeground(MyColor.lightblue);
		submitButton.setFocusPainted(false);
		submitButton.setBorderPainted(false);
		submitButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//这里显示输出的两个框
				double executePrice = Double.parseDouble(executePriceField.getText());
				
				System.out.println("submitButton has been clicked!");
			}
		});
		this.add(submitButton);
	}

	
}
