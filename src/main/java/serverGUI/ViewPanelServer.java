package serverGUI;

import java.awt.Component;
import java.rmi.RemoteException;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import serverGUI.tm.ViewTmServer;
import blservice.Service;
import data.Order;
import data.OrderOFholdings;
import GUI.myswing.MyColor;
import GUI.myswing.NTable;

public class ViewPanelServer extends MenuPanel{
	private Service service;
	private Order[] orderlist;
	//ArrayList<Integer> list;
	DefaultTableCellRenderer render;
	private NTable table;
	private ViewTmServer tableRow = new ViewTmServer();
	public ViewPanelServer(String name) {
		super("View");
		//----------------------------------------------------------------------------------------
		
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
				table.setLocation(30,100);
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
				
				//orderlist = service.getOrdersByAccount("0");
				filltable(orderlist);

	}
	private void filltable(Order[] list) {
		if(list!=null){
			for (int i = tableRow.getRowCount(); i > 0; i--) {
				tableRow.removeRow(0);
			}
			for(Order order:list){
				Vector v = new Vector();
				v.add(order.getClientid());
				v.add(order.getBuyDate());
				v.add(order.getOption().toString());
				v.add(order.getOption().getEora().toString()+order.getOption().getEora().toString());
				if(order.getNumber()>=0){
					v.add("买");
				}else{
					v.add("卖");
				}
				v.add(order.getDeadline());
				v.add(order.getDealprice());
				v.add(Math.abs(order.getNumber()));
				if(order.isOpen()){
					v.add("开仓");
				}else{
					v.add("平仓");
				}
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
			int l =  123;
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
			tableRow.addRow(v);
			tableRow.addRow(v);
			tableRow.addRow(v);
			tableRow.addRow(v);
			tableRow.addRow(v);
		
	}
	
	/*public double[] getNowPrice(OrderOFholdings list){
		String name = list.getOption().getFirstClassName();
		try{
			if(name.equals("普通期权")){
				return service.getCommonPurchasePrice(list.getOption().getEora(),
						list.getOption().getUpordown(), 
						list.getExecuteprice(),list.getDeadline(),list.getClientid());
			}else if(name.equals("二元期权")){
				return service.getBinaryPurchasePrice1(list.getOption().getEora(),
						list.getOption().getUpordown(), 
						list.getExecuteprice(),list.getDeadline(),list.getClientid());
				//

			}else if(name.equals("回望期权")){
				return service.getRetrospectPurchasePrice(list.getOption().getEora(),
						list.getOption().getUpordown(), 
						list.getExecuteprice(),list.getDeadline(),list.getClientid());
			}else if(name.equals("亚式期权")){
				return service.getSubtypePurchasePrice(list.getOption().getEora(),
						list.getOption().getUpordown(), 
						list.getExecuteprice(),list.getDeadline(),list.getClientid());
			}else if(name.equals("障碍期权")){
				return service.getObstaclePurchasePrice(list.getOption().getEora(),
						list.getOption().getUpordown(), 
						list.getExecuteprice(),list.getDeadline(),
						-1,
						list.getClientid());
			}
		}catch(RemoteException e){
			e.printStackTrace();
		}
		return null;
	}*/
}