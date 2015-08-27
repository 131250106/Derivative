package GUI;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

import GUI.myswing.MyColor;
import data.EorA;
import data.Option;
import data.Order;
import data.upORdown;

public class DetailPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public JButton ensure;

	public DetailPanel(Order order) {
		Option option = order.getOption();

		setLayout(null);

		this.setSize(400, 620);

		Font font = new Font("微软雅黑", Font.PLAIN, 20);

		JLabel label_2 = new JLabel("期权名称：");
		label_2.setBounds(64, 160, 120, 30);
		label_2.setFont(font);
		add(label_2);

		String temp = option.getFirstClassName();
		if (option.getSecondClassName() != null) {
			temp = temp + " - " + option.getSecondClassName();
		}
		JLabel label_3 = new JLabel(temp);
		label_3.setBounds(183, 160, 270, 30);
		label_3.setFont(font);
		add(label_3);

		JLabel label_4 = new JLabel("期权类型：");
		label_4.setBounds(64, 200, 120, 30);
		label_4.setFont(font);
		add(label_4);
		temp = "";
		if (option.getUpordown() == upORdown.up) {
			temp = temp + "看涨";
		} else {
			temp = temp + "看跌";
		}
		if (option.getEora() == EorA.A) {
			temp = temp + " - " + "美式";
		} else {
			temp = temp + " - " + "欧式";
		}
		JLabel label_5 = new JLabel(temp);
		label_5.setBounds(183, 200, 270, 30);
		label_5.setFont(font);
		add(label_5);

		JLabel label_6 = new JLabel("交易类型：");
		label_6.setBounds(64, 240, 120, 30);
		label_6.setFont(font);
		add(label_6);

		if (order.isOpen() == true) {
			temp = "开仓";
		} else {
			temp = "平仓";
		}
		JLabel label_7 = new JLabel(temp);
		label_7.setBounds(183, 240, 120, 30);
		label_7.setFont(font);
		add(label_7);

		JLabel label_8 = new JLabel("交易形式：");
		label_8.setBounds(64, 280, 120, 30);
		label_8.setFont(font);
		add(label_8);

		if (order.getNumber() < 0) {
			temp = "卖出";
		} else {
			temp = "买入";
		}
		JLabel label_9 = new JLabel(temp);
		label_9.setBounds(183, 280, 120, 30);
		label_9.setFont(font);
		add(label_9);

		JLabel label_10 = new JLabel("执行价格： ");
		label_10.setBounds(64, 320, 120, 30);
		label_10.setFont(font);
		add(label_10);

		JLabel label_11 = new JLabel("" + order.getExecuteprice());
		label_11.setBounds(183, 320, 120, 30);
		label_11.setFont(font);
		add(label_11);

		int delta = 0;
		if (option.getObstacleRate() == -1) {
			delta = -40;
		} else {
			JLabel label_12 = new JLabel("障碍水平：");
			label_12.setBounds(64, 360, 120, 30);
			label_12.setFont(font);
			add(label_12);

			JLabel label_13 = new JLabel("" + option.getObstacleRate());
			label_13.setBounds(183, 360, 120, 30);
			label_13.setFont(font);
			add(label_13);
		}
		
		JLabel lblid = new JLabel("订单编号：");
		lblid.setBounds(64, 80, 120, 30);
		lblid.setFont(font);
		add(lblid);

		JLabel lblOd = new JLabel("OD000"+ order.getOrderId());
		lblOd.setBounds(183, 80, 120, 30);
		lblOd.setFont(font);
		add(lblOd);

		JLabel label = new JLabel("客户账号：");
		label.setBounds(64, 120, 120, 30);
		label.setFont(font);
		add(label);

		JLabel label_14 = new JLabel("" + order.getClientid());
		label_14.setBounds(183, 120, 120, 30);
		label_14.setFont(font);
		add(label_14);

		JLabel label_15 = new JLabel("交易价格：");
		label_15.setBounds(64, 400 + delta, 120, 30);
		label_15.setFont(font);
		add(label_15);

		JLabel label_16 = new JLabel(order.getDealprice() + "");
		label_16.setBounds(183, 400 + delta, 158, 30);
		label_16.setFont(font);
		add(label_16);

		JLabel label_17 = new JLabel("交易手数：");
		label_17.setBounds(64, 440 + delta, 120, 30);
		label_17.setFont(font);
		add(label_17);

		JLabel label_18 = new JLabel("" + Math.abs(order.getNumber()));
		label_18.setBounds(183, 440 + delta, 120, 30);
		label_18.setFont(font);
		add(label_18);

		JLabel label_19 = new JLabel("交易时间：");
		label_19.setBounds(64, 480 + delta, 120, 30);
		label_19.setFont(font);
		add(label_19);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		temp = sdf.format(order.getBuyDate());
		JLabel label_20 = new JLabel(temp);
		label_20.setBounds(183, 480 + delta, 270, 30);
		label_20.setFont(font);
		add(label_20);

		JLabel label_21 = new JLabel("截止时间：");
		label_21.setBounds(64, 520 + delta, 120, 30);
		label_21.setFont(font);
		add(label_21);

		sdf = new SimpleDateFormat("yyyy-MM-dd");
		temp = sdf.format(order.getDeadline());
		JLabel label_22 = new JLabel(temp);
		label_22.setBounds(183, 520 + delta, 270, 30);
		label_22.setFont(font);
		add(label_22);

		JLabel label_23 = new JLabel("到期时间：");
		label_23.setBounds(64, 560 + delta, 120, 30);
		label_23.setFont(font);
		add(label_23);

		JLabel label_24 = new JLabel(order.getDeadTime() + "天");
		label_24.setBounds(183, 560 + delta, 120, 30);
		label_24.setFont(font);
		add(label_24);

		ensure = new JButton("确认");
		ensure.setFont(font);
		ensure.setBackground(MyColor.deepblue);
		ensure.setForeground(MyColor.lightblue);
		ensure.setFocusPainted(false);
		ensure.setBorderPainted(false);
		ensure.setVisible(true);
		ensure.setBounds(120, 615 + delta, 120, 30);
		add(ensure);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		ImageIcon icon = new ImageIcon("GUI\\Detail.png");
		Image img = icon.getImage();
		g.drawImage(img, 0, 0, icon.getIconWidth(), icon.getIconHeight(),
				icon.getImageObserver());
	}
}
