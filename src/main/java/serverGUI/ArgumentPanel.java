package serverGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import data.ServerData;
import GUI.myswing.MyColor;

public class ArgumentPanel extends MenuPanel{

	private JLabel noRiskRateLabel;
	private JLabel rateLabel;
	private JTextField rateField;
	private JTextField noRiskRateField;
	private JButton changeArgument;
	JLabel successLabel;
	private JLabel tag;
	public ArgumentPanel(String name) {
		super("argument");
		super.buttonOption.setBackground(MyColor.deepblue2);
		
		Font font = new Font("微软雅黑",Font.PLAIN,18);
		
		tag = new JLabel("参数调整");
		tag.setSize(175,70);
		tag.setLocation(40,60);
		tag.setFont(font);
		tag.setBackground(MyColor.white);
		tag.setForeground(MyColor.deepblue);
		tag.setVisible(true);
		//this.add(tag);
		
		font = new Font("微软雅黑",Font.PLAIN,20);
		
		noRiskRateLabel = new JLabel("无风险利率");
		noRiskRateLabel.setFont(font);
		noRiskRateLabel.setBounds(100, 160,150,40);
		noRiskRateLabel.setVisible(true);
		this.add(noRiskRateLabel);
		
		noRiskRateField = new JTextField();
		noRiskRateField.setFont(font);
		noRiskRateField.setBounds(300,160,150,40);
		noRiskRateField.setVisible(true);
		this.add(noRiskRateField);
		
		rateLabel = new JLabel("波动率");
		rateLabel.setFont(font);
		rateLabel.setBounds(100,220,150,40);
		rateLabel.setVisible(true);
		this.add(rateLabel);
		
		rateField = new JTextField();
		rateField.setFont(font);
		rateField.setBounds(300,220,150,40);
		rateField.setVisible(true);
		this.add(rateField);
		
		JLabel warningLabel = new JLabel("请输入完整信息");
		warningLabel.setFont(font);
		warningLabel.setForeground(Color.red);
		warningLabel.setBounds(100,360,150,40);
		warningLabel.setVisible(false);
		this.add(warningLabel);
		
		successLabel = new JLabel("修改成功!");
		successLabel.setFont(font);
		successLabel.setForeground(MyColor.success);
		successLabel.setBounds(100,360,150,40);
		successLabel.setVisible(false);
		this.add(successLabel);
		
		changeArgument = new JButton("确认更改");
		changeArgument.setSize(175, 70);
		changeArgument.setLocation(100,400);
		changeArgument.setBackground(MyColor.deepblue);
		changeArgument.setForeground(MyColor.lightblue);
		changeArgument.setFocusPainted(false);
		changeArgument.setBorderPainted(false);
		changeArgument.setFont(font);
		changeArgument.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(rateField.getText().equals("")||noRiskRateField.getText().equals("")){
					warningLabel.setVisible(true);
				}else{
					ServerData.setFluctuation_Rate(Double.parseDouble(rateField.getText()));
					ServerData.setRisk_free_Rate(Double.parseDouble(noRiskRateField.getText()));
					successLabel.setVisible(true);
					
				}
			}
		});
		this.add(changeArgument);
	}

	 class TimerThread extends Thread{
	    	public void run(){
	      	  long time = 1 * 5 ;// 自定义倒计时时间
	      	  while (time >= 0){
	              try {
	                	this.sleep(1000);
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	                time--;
	            }
	      	  successLabel.setVisible(false);
	    	}  	
	    }
}
