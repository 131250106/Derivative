package GUI;

import java.text.SimpleDateFormat;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

import data.EorA;
import data.Option;
import data.Order;
import data.upORdown;

public class InsurePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public InsurePanel( Order order) {
		Option option = order.getOption();
		
		setLayout(null);
		
		this.setSize(300, 580);
		
		JLabel label_1 = new JLabel("下单信息确认");
		label_1.setBounds(125, 5, 117, 20);
		add(label_1);
		
		JLabel label_2 = new JLabel("期权名称：");
		label_2.setBounds(64, 100, 89, 20);
		add(label_2);
		
		String temp = option.getFirstClassName();
		if( option.getSecondClassName() != null ){
			temp = temp +  " - "+ option.getSecondClassName();
		}
		JLabel label_3 = new JLabel(temp);
		label_3.setBounds(183, 100, 171, 20);
		add(label_3);
		
		JLabel label_4 = new JLabel("期权类型：");
		label_4.setBounds(64, 140, 70, 15);
		add(label_4);
		temp = "";
		if(option.getUpordown() == upORdown.up){
			temp = temp + "看涨";
		}else{
			temp = temp + "看跌";
		}
		if(option.getEora() == EorA.A){
			temp = temp + " - " + "美式";
		}else{
			temp = temp + " - " + "欧式";
		}
		JLabel label_5 = new JLabel(temp);
		label_5.setBounds(183, 140, 117, 15);
		add(label_5);
		
		JLabel label_6 = new JLabel("交易类型：");
		label_6.setBounds(64, 180, 70, 15);
		add(label_6);
		
		if( order.isOpen() == true){
			temp = "开仓";
		}else{
			temp = "平仓";
		}
		JLabel label_7 = new JLabel( temp );
		label_7.setBounds(183, 180, 54, 15);
		add(label_7);
		
		JLabel label_8 = new JLabel("交易形式：");
		label_8.setBounds(64, 220, 70, 15);
		add(label_8);
		
		if(order.getNumber()<0){
			temp = "卖出";
		}else{
			temp = "买入";
		}
		JLabel label_9 = new JLabel( temp );
		label_9.setBounds(183, 220, 54, 15);
		add(label_9);
		
		JLabel label_10 = new JLabel("执行价格： ");
		label_10.setBounds(64, 260, 70, 15);
		add(label_10);
		
		JLabel label_11 = new JLabel(""+order.getExecuteprice());
		label_11.setBounds(183, 260, 54, 15);
		add(label_11);
		
		int delta = 0;
		if(option.getObstacleRate() == -1){
			delta = -40;
		}else{
		JLabel label_12 = new JLabel("障碍水平：");
		label_12.setBounds(64, 300, 70, 15);
		add(label_12);
		}
		
		JLabel label_13 = new JLabel(""+option.getObstacleRate());
		label_13.setBounds(183, 300, 54, 15);
		add(label_13);
		
		JLabel lblid = new JLabel("订单编号：");
		lblid.setBounds(64, 35, 70, 15);
		add(lblid);
		
		JLabel lblOd = new JLabel("OD000001");
		lblOd.setBounds(183, 35, 54, 15);
		add(lblOd);
		
		JLabel label = new JLabel("客户账号：");
		label.setBounds(64, 68, 70, 15);
		add(label);
		
		JLabel label_14 = new JLabel(""+order.getClientid());
		label_14.setBounds(183, 68, 59, 15);
		add(label_14);
		
		JLabel label_15 = new JLabel("交易价格：");
		label_15.setBounds(64, 340+delta, 70, 15);
		add(label_15);
		
		JLabel label_16 = new JLabel(""+order.getDealprice());
		label_16.setBounds(183, 340+delta, 158, 15);
		add(label_16);
		
		JLabel label_17 = new JLabel("交易手数：");
		label_17.setBounds(64, 380+delta, 72, 15);
		add(label_17);
		
		JLabel label_18 = new JLabel(""+Math.abs(order.getNumber()));
		label_18.setBounds(183, 380+delta, 73, 15);
		add(label_18);
		
		JLabel label_19 = new JLabel("交易时间：");
		label_19.setBounds(64, 420+delta, 73, 15);
		add(label_19);
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		temp =sdf.format(order.getBuyDate());  
		JLabel label_20 = new JLabel(temp);
		label_20.setBounds(183, 420+delta, 54, 15);
		add(label_20);
		
		JLabel label_21 = new JLabel("截止时间：");
		label_21.setBounds(64, 460+delta, 70, 15);
		add(label_21);
		
		sdf=new SimpleDateFormat("yyyy-MM-dd");  
		temp =sdf.format(order.getDeadline()); 
		JLabel label_22 = new JLabel(temp);
		label_22.setBounds(183, 460+delta, 54, 15);
		add(label_22);
		
		JLabel label_23 = new JLabel("到期时间：");
		label_23.setBounds(64, 500+delta, 70, 15);
		add(label_23);
		
		JLabel label_24 = new JLabel(order.getDeadTime()+"天");
		label_24.setBounds(183, 500+delta, 54, 15);
		add(label_24);
		
		JButton button = new JButton("确认");
		button.setBounds(40, 538+delta, 93, 23);
		add(button);
		
		JButton button_1 = new JButton("取消");
		button_1.setBounds(183, 538+delta, 93, 23);
		add(button_1);
	}
}
