package GUI.myswing;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CommonTextPanel extends JPanel{
	static int PANEL_WIDTH = 960;
	static int PANEL_HEIGHT = 600;
	static int LABEL_WIDTH = 60;
	static int LABEL_HEIGHT = 30;
	static int TEXTFIELD_WIDTH = 60;
	static int TEXTFIELD_HEIGHT = 30;
	
	JLabel executePriceLabel,noRiskRateLabel,deadlineLabel;
	JTextField executePriceField,noRiskRateField,deadlineField;
	
	public CommonTextPanel(){
		this.setLocation(0,0);
		this.setSize(PANEL_WIDTH, PANEL_HEIGHT);
		
		//213 290 
		executePriceLabel = new JLabel("执行价格:");
		executePriceLabel.setBounds(213, 240, LABEL_WIDTH, LABEL_HEIGHT);
		executePriceLabel.setVisible(true);
		this.add(executePriceLabel);
		
		executePriceField = new JTextField();
		executePriceField.setBounds(290, 240, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		this.add(executePriceField);
		
		noRiskRateLabel = new JLabel("无风险利率:");
		noRiskRateLabel.setBounds(213, 280, LABEL_WIDTH, LABEL_HEIGHT);
		noRiskRateLabel.setVisible(true);
		this.add(noRiskRateLabel);
		
		noRiskRateField = new JTextField();
		noRiskRateField.setBounds(290, 280, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		noRiskRateField.setVisible(true);
		this.add(noRiskRateField);
		
		//需要处理计算一下
		deadlineLabel = new JLabel("截止日期:");
		deadlineLabel.setBounds(213,320, LABEL_WIDTH, LABEL_HEIGHT);
		deadlineLabel.setVisible(true);
		this.add(deadlineLabel);
		
		deadlineField = new JTextField();
		deadlineField.setBounds(290, 320, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		deadlineField.setVisible(true);
		this.add(deadlineField);
		
		this.setOpaque(false);
		this.setVisible(true);
	}
	
}
