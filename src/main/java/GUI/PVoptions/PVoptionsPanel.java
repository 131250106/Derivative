package GUI.PVoptions;


import java.awt.Font;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.invoke.MethodHandles.Lookup;
import java.nio.file.SecureDirectoryStream;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.function.DoubleToLongFunction;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.xml.crypto.Data;

import org.junit.experimental.theories.Theories;

import GUI.GraphicController;
import GUI.Loader;
import GUI.MenuPanel;
import GUI.myswing.CommonTextPanel;
import GUI.myswing.MyColor;
import blservice.Service;
import data.EorA;
import data.upORdown;

public class PVoptionsPanel extends MenuPanel{
	
	Service service;
	
	private JButton pvoption;
	private JLabel Name;
	private ButtonGroup LookUpAndDown;
	private JRadioButton LookUp;
	private JRadioButton LookDown;
	private ButtonGroup EAndA;
	private JRadioButton Europe;
	private JRadioButton America;
	private ButtonGroup BorA;
	private JRadioButton bidButton;
	private JRadioButton askButton;
	
	static int PANEL_WIDTH = 960;
	static int PANEL_HEIGHT = 600;
	static int LABEL_WIDTH = 70;
	static int LABEL_HEIGHT = 25;
	static int TEXTFIELD_WIDTH = 70;
	static int TEXTFIELD_HEIGHT = 22;
	
	double[] PurchasePrice;
	
	JLabel executePriceLabel,noRiskRateLabel,deadlineLabel,bidPriceLabel,askPriceLabel,dealNumLabel;
	JTextField executePriceField,noRiskRateField,deadlineField,bidPriceField,askPriceField,dealNumField;
	JButton submitButton,dealButton;
	
	public PVoptionsPanel(String name) {
		super("PVoptions");
		
		service = Loader.service;
		
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
		
		JLabel warningLabel = new JLabel("请输入完整信息");
		Font font2 = new Font("微软雅黑",Font.PLAIN,12);
		warningLabel.setFont(font2);
		warningLabel.setBounds(220,360,100,25);
		warningLabel.setVisible(false);
		this.add(warningLabel);
		
		bidPriceLabel = new JLabel("买价:");
		bidPriceLabel.setFont(font);
		bidPriceLabel.setBounds(500,163,LABEL_WIDTH,LABEL_HEIGHT);
		bidPriceLabel.setVisible(false);
		this.add(bidPriceLabel);
		
		bidPriceField = new JTextField();
		bidPriceField.setFont(font);
		bidPriceField.setBounds(580, 163, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		bidPriceField.setVisible(false);
		this.add(bidPriceField);
		
		askPriceLabel = new JLabel("卖价:");
		askPriceLabel.setFont(font);
		askPriceLabel.setBounds(500,200,LABEL_WIDTH,LABEL_HEIGHT);
		askPriceLabel.setVisible(false);
		this.add(askPriceLabel);
		
		askPriceField = new JTextField();
		askPriceField.setFont(font);
		askPriceField.setBounds(580, 200, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		askPriceField.setVisible(false);
		this.add(askPriceField);
		
		BorA = new ButtonGroup();
		bidButton = new JRadioButton("买入");
		bidButton.setFont(font);
		askButton = new JRadioButton("卖出");
		askButton.setFont(font);
		BorA.add(bidButton);
		BorA.add(askButton);
		
		bidButton.setFocusPainted(false);
		bidButton.setBorderPainted(false);
		askButton.setFocusPainted(false);
		askButton.setBorderPainted(false);
		bidButton.setBackground(MyColor.white);
		askButton.setBackground(MyColor.white);
		bidButton.setForeground(MyColor.black);
		askButton.setForeground(MyColor.black);
		
		bidButton.setBounds(500,240, 64, 30);
		askButton.setBounds(580,240,64,30);		
		askButton.setVisible(false);
		bidButton.setVisible(false);
		this.add(bidButton);
		this.add(askButton);
		
		dealNumLabel = new JLabel("交易数量:");
		dealNumLabel.setFont(font);
		dealNumLabel.setBounds(500,275,LABEL_WIDTH,LABEL_HEIGHT);
		dealNumLabel.setVisible(false);
		this.add(dealNumLabel);
		
		dealNumField = new JTextField();
		dealNumField.setFont(font);
		dealNumField.setBounds(580, 275, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		dealNumField.setVisible(false);
		this.add(dealNumField);
		
		submitButton = new JButton("查询");
		submitButton.setFont(font);
		submitButton.setSize(80, 30);
		submitButton.setLocation(200,400);
		submitButton.setBackground(MyColor.deepblue);
		submitButton.setForeground(MyColor.lightblue);
		submitButton.setFocusPainted(false);
		submitButton.setBorderPainted(false);
		submitButton.setVisible(true);
		submitButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//这里显示输出的两个框
				if(executePriceField.getText().equals("")||deadlineField.getText().equals("")||
						noRiskRateField.getText().equals("")||
						EAndA.getSelection()==null||LookUpAndDown.getSelection()==null){
					warningLabel.setVisible(true);
				}else{
					//画出显示框
					warningLabel.setVisible(false);
					double executePrice = Double.parseDouble(executePriceField.getText());
					double noRiskRate = Double.parseDouble(noRiskRateField.getText());
					Date deadline =new Date(deadlineField.getText());
					EorA eora = Europe.isSelected()?EorA.E:EorA.A;
					upORdown upordown = LookDown.isSelected()?upORdown.down:upORdown.up;
					
					try {
						PurchasePrice = service.getCommonPurchasePrice(eora, upordown, executePrice, deadline, "131250131");
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
					System.out.println(executePrice);
					//这里调用getPurchasePrice
					bidPriceField.setText(Double.toString(PurchasePrice[0]));
					askPriceField.setText(Double.toString(PurchasePrice[1]));
					bidPriceField.setVisible(true);
					bidPriceLabel.setVisible(true);
					askPriceLabel.setVisible(true);
					askPriceField.setVisible(true);
					bidButton.setVisible(true);
					askButton.setVisible(true);
					dealNumLabel.setVisible(true);
					dealNumField.setVisible(true);
					dealButton.setVisible(true);
				}			
				System.out.println("submitButton has been clicked!");
			}
		});
		this.add(submitButton);
		
		dealButton = new JButton("交易");
		dealButton.setFont(font);
		dealButton.setSize(80, 30);
		dealButton.setLocation(520,310);
		dealButton.setBackground(MyColor.deepblue);
		dealButton.setForeground(MyColor.lightblue);
		dealButton.setFocusPainted(false);
		dealButton.setBorderPainted(false);
		dealButton.setVisible(false);
		dealButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//这里显示输出的两个框
				if(dealNumField.getText().equals("")||BorA.getSelection()==null){
					System.out.println("没填完");
				}else{
					
					System.out.println("交易完成");
				}			
				System.out.println("submitButton has been clicked!");
			}
		});
		
		this.add(dealButton);
	}
	
}
