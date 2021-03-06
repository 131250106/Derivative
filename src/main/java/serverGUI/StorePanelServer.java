package serverGUI;

import java.awt.Component;
import java.awt.Font;
import java.math.BigDecimal;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import serverGUI.tm.StoreTmServer;
import bl.CombinationManage;
import bl.OrderManage;
import data.EorA;
import data.Order;
import data.OrderOFholdings;
import data.upORdown;
import GUI.myswing.MyColor;
import GUI.myswing.NTable;

public class StorePanelServer extends MenuPanel{
	private OrderManage orderservice;
	private CombinationManage combinationmanage;
	DefaultTableCellRenderer render;
	private NTable table;
	StoreTmServer tableRow = new StoreTmServer();
	OrderOFholdings[] orderlist;
	private JLabel tag;
	public StorePanelServer(String name) {
		super("Store");
		super.buttonStore.setBackground(MyColor.deepblue2);
		Font font = new Font("微软雅黑",Font.PLAIN,18);
		/**
		 * 
		 */
		//*****************************
		orderservice = serverLoader.orderservice;
		combinationmanage = serverLoader.combinationmanage;
		//*****************************
		
		tag = new JLabel("持仓记录");
		tag.setSize(175,70);
		tag.setLocation(40,60);
		tag.setFont(font);
		tag.setBackground(MyColor.white);
		tag.setForeground(MyColor.deepblue);
		tag.setVisible(true);
		//this.add(tag);
		
		render = new DefaultTableCellRenderer() {
			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				if (hasFocus)
					setBorder(null);
				{
					if (row % 2 == 1) {
						setBackground(MyColor.white); // 设置奇数行底色
					} else if (row % 2 == 0) {
						setBackground(MyColor.lightblue); // 设置偶数行底色
					}
				}
				return super.getTableCellRendererComponent(table, value,
						isSelected, hasFocus, row, column);
			}
		};
		render.setHorizontalAlignment(SwingConstants.CENTER);
		table = new NTable(tableRow, 900,383);
		table.setLocation(30,120);
		for (int i = 0; i <table.getColumnCount(); i++) {
			table.getColumn(table.getColumnName(i))
			.setCellRenderer(render);
			
		}
		/*list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);*/
		//table.revalidate();
		//table.repaint();
		this.add(table);
		
		orderlist = orderservice.getAllOrderOFholdings();
		filltable(orderlist);
	}
	private void filltable(OrderOFholdings[] list) {
		if(list!=null){
			for (int i = tableRow.getRowCount(); i > 0; i--) {
				tableRow.removeRow(0);
			}
			for(OrderOFholdings order:list){
				Vector v = new Vector();
				v.add(order.getAccount());
				if(order.getOption().getFirstClassName().equals("普通期权")){
					v.add(order.getOption().getFirstClassName());	
				}else{
					v.add(order.getOption().getFirstClassName()+order.getOption().getSecondClassName());
				}
				EorA eora = order.getOption().getEora();
				upORdown upordown = order.getOption().getUpordown();	
				if(upordown == upORdown.up){
					v.add("看涨");
				}else{
					v.add("看跌");
				}
				//v.add(order.getOption().getEora().toString()+order.getOption().getEora().toString());
				v.add(order.getExecuteprice());
				v.add(order.getDeadline());
				if(order.getNumber()>=0){
					v.add("买");
				}else{
					v.add("卖");
				}
				
				v.add(Math.abs(order.getNumber()));
				BigDecimal bigcost = new BigDecimal(order.getCost());
				double cost = bigcost.setScale(4,BigDecimal.ROUND_HALF_UP).doubleValue();
				v.add(cost);
				//v.add(order.getCost());
				double [] arrayOfrate;
				arrayOfrate = getNowPrice(order);
				v.add(arrayOfrate[0]);
				v.add(arrayOfrate[1]);
				v.add(arrayOfrate[2]);
				v.add(arrayOfrate[3]);
				v.add(arrayOfrate[4]);
				v.add(arrayOfrate[5]);

				tableRow.addRow(v);
			}
			/*for(Integer a:list){
				Vector v = new Vector();
				v.add(a);
				tableRow.addRow(v);
			}*/
			table.revalidate();
			table.repaint();
		}
			/*int l =  123;
			Vector v = new Vector();
			v.add(l);
			v.add(l);
			v.add(l);
			v.add(l);
			v.add(l);
			v.add(l);
			v.add(l);
			v.add(l);
			v.add(l);
			v.add(l);
			v.add(l);
			v.add(l);
			v.add(l);
			v.add(l);
			tableRow.addRow(v);
			tableRow.addRow(v);
			tableRow.addRow(v);
			tableRow.addRow(v);
			tableRow.addRow(v);*/
		
	}
	
	public double[] getNowPrice(OrderOFholdings list){
		String nameF = list.getOption().getFirstClassName();
		String nameS = list.getOption().getSecondClassName();
		if(nameF.equals("普通期权")){
			return combinationmanage.getCommonPurchaseValue(list.getOption().getEora(),
					list.getOption().getUpordown(), list.getExecuteprice(), list.getDeadline());
		}
		if(nameF.equals("二元期权")){
			if(nameS.equals("资产或无价值期权")){
				return combinationmanage.getBinaryAONValue(list.getOption().getEora(),
						list.getOption().getUpordown(), list.getExecuteprice(),list.getDeadline());
			}
			if(nameS.equals("现金或无价值期权")){
				return combinationmanage.getBinaryCONValue(list.getOption().getEora()
						,list.getOption().getUpordown(),list.getExecuteprice(),list.getDeadline());
			}
		}
		if(nameF.equals("回望期权")){
			if(nameS.equals("固定执行价格期权")){
				return combinationmanage.getlookbackfixedValue(list.getOption().getEora(),
						list.getOption().getUpordown(),list.getExecuteprice(), list.getDeadline());
			}
			if(nameS.equals("浮动执行价格期权")){
				return combinationmanage.getlookbackfloatValue(list.getOption().getEora(), 
						list.getOption().getUpordown(), list.getExecuteprice(),list.getDeadline());
				
			}
		}
		if(nameF.equals("亚式期权")){
			if(nameS.equals("平均执行价格期权")){
				return combinationmanage.getSubtypeAverageStrikeValue(list.getOption().getEora(),
						list.getOption().getUpordown(), list.getExecuteprice()
						, list.getDeadline());
			}
			if(nameS.equals("平均价格期权")){
				return combinationmanage.getSubtypeAveragePriceValue(list.getOption().getEora(), 
						list.getOption().getUpordown(), list.getExecuteprice(),list.getDeadline());
			}
		}
		if(nameF.equals("障碍期权")){
			if(nameS.equals("向上敲入期权")){
				return combinationmanage.getObstaclePurchaseupandinValue(list.getOption().getEora(),
						list.getOption().getUpordown(),list.getExecuteprice(),list.getOption().getObstacleRate(), 
						list.getDeadline());
			}
			if(nameS.equals("向上敲出期权")){
				return combinationmanage.getObstaclePurchaseupandoutValue(list.getOption().getEora(),
						list.getOption().getUpordown(),list.getExecuteprice(),list.getOption().getObstacleRate(), 
						list.getDeadline());
			}
			if(nameS.equals("向下敲入期权")){
				return combinationmanage.getObstaclePurchasedownandinValue(list.getOption().getEora(),
						list.getOption().getUpordown(),list.getExecuteprice(),list.getOption().getObstacleRate(), 
						list.getDeadline());
			}
			if(nameS.equals("向下敲出期权")){
				return combinationmanage.getObstaclePurchasedownandoutValue(list.getOption().getEora(),
						list.getOption().getUpordown(),list.getExecuteprice(),list.getOption().getObstacleRate(), 
						list.getDeadline());
			}
		}
		return null;
	}
}
