package GUI;

import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import data.Order;
import blservice.Service;
import GUI.Asianoptions.AsianoptionsPanel;
import GUI.Asianoptions.AvgExcPricePanel;
import GUI.Asianoptions.AvgPricePanel;
import GUI.BARoptions.BARoptionsPanel;
import GUI.BARoptions.DoubleBARPanel;
import GUI.BARoptions.DownAndInPanel;
import GUI.BARoptions.DownAndOutPanel;
import GUI.BARoptions.MultiLevelPanel;
import GUI.BARoptions.UpAndInPanel;
import GUI.BARoptions.UpAndOutPanel;
import GUI.Boptions.BoptionsPanel;
import GUI.LBoptions.FixedPanel;
import GUI.LBoptions.FloatPanel;
import GUI.LBoptions.LBoptionsPanel;
import GUI.PVoptions.PVoptionsPanel;
import GUI.myswing.MyColor;
import GUI.myswing.NTable;
import GUI.myswing.ViewTm;

public class ViewPanel extends JPanel implements ActionListener{
	private JButton buttonOption,buttonView,buttonStore;;
	private JButton pvoption,lboption,boption,baroption,asianoption,alloption;
	private JButton pvoptionDetail,fixedoption,floatoption,boptionDetail;
	private JButton downandoutoption,downandinoption,upandoutoption,upandinoption,
	doublebaroption,multileveloption;
	private JButton avgexcpriceoption,avgpriceoption;
	private JButton mini,close,back;
	private NTable table;
	private ViewTm tableRow = new ViewTm();
	private Service service;
	private Order[] orderlist;
	//ArrayList<Integer> list;
	DefaultTableCellRenderer render;
	
	public ViewPanel(){
		Font font = new Font("微软雅黑",Font.PLAIN,18);
		
		service = Loader.service;
		
		mini = new JButton("");
		mini.setBounds(890,0,34,30);
		mini.setContentAreaFilled(false);
		mini.setFocusPainted(false);
		mini.setBorderPainted(false);
		mini.addActionListener(this);
		this.add(mini);
		
		close = new JButton("");
		close.setBounds(926,0,34,30);
		close.setContentAreaFilled(false);
		close.setFocusPainted(false);
		close.setBorderPainted(false);
		close.addActionListener(this);
		this.add(close);
		
		back = new JButton("");
		back.setBounds(890,30,60, 45);
		back.setContentAreaFilled(false);
		back.setFocusPainted(false);
		back.setBorderPainted(false);
		back.addActionListener(this);
		this.add(back);
		
		buttonOption = new JButton("买卖期权");
		buttonOption.setBounds(0, 30, 110,45);
		//buttonOption.setContentAreaFilled(false);
		buttonOption.setForeground(MyColor.white);
		buttonOption.setBackground(MyColor.deepblue);
		buttonOption.setFocusPainted(false);
		buttonOption.setBorderPainted(false);
		buttonOption.setFont(font);
		buttonOption.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				GraphicController.changeToPanel(new PVoptionsPanel("PVoptions"));
			}
		});
		this.add(buttonOption);
		buttonView = new JButton("交易记录");
		buttonView.setBounds(110,30, 110,45);
		buttonView.setForeground(MyColor.white);
		buttonView.setBackground(MyColor.deepblue);
		buttonView.setFocusPainted(false);
		buttonView.setBorderPainted(false);
		buttonView.setFont(font);
		buttonView.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				GraphicController.changeToPanel(new ViewPanel());
			}
		});
		this.add(buttonView);
		

		buttonStore = new JButton("持仓记录");
		buttonStore.setBounds(220,30, 110,45);
		buttonStore.setForeground(MyColor.white);
		buttonStore.setBackground(MyColor.deepblue);
		buttonStore.setFocusPainted(false);
		buttonStore.setBorderPainted(false);
		buttonStore.setFont(font);
		buttonStore.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				GraphicController.changeToPanel(new StorePanel());
			}
		});
		this.add(buttonStore);
		
		//--------------------------------------------------------------------------------------------
		
		font = new Font("微软雅黑",Font.PLAIN,20);
		
		pvoption = new JButton("普通期权");
		pvoption.setSize(170, 50);
		pvoption.setLocation(0,76);
		pvoption.setBackground(MyColor.lightblue);
		pvoption.setForeground(MyColor.white);
		pvoption.setFocusPainted(false);
		pvoption.setBorderPainted(false);
		pvoption.setFont(font);

		this.add(pvoption);
		
		lboption = new JButton("回望期权");
		lboption.setSize(170, 50);
		lboption.setLocation(340,76);
		lboption.setBackground(MyColor.lightblue);
		lboption.setForeground(MyColor.white);
		lboption.setFocusPainted(false);
		lboption.setBorderPainted(false);
		lboption.setFont(font);
		this.add(lboption);
		
		boption = new JButton("二元期权");
		boption.setSize(170, 50);
		boption.setLocation(170,76);
		boption.setBackground(MyColor.lightblue);
		boption.setForeground(MyColor.white);
		boption.setFocusPainted(false);
		boption.setBorderPainted(false);
		boption.setFont(font);
		this.add(boption);
		
		
		asianoption = new JButton("亚式期权");
		asianoption.setSize(170, 50);
		asianoption.setLocation(510,76);
		asianoption.setBackground(MyColor.lightblue);
		asianoption.setForeground(MyColor.white);
		asianoption.setFocusPainted(false);
		asianoption.setBorderPainted(false);
		asianoption.setFont(font);
		this.add(asianoption);
		
		baroption = new JButton("障碍期权");
		baroption.setSize(170, 50);
		baroption.setLocation(680,76);
		baroption.setBackground(MyColor.lightblue);
		baroption.setForeground(MyColor.white);
		baroption.setFocusPainted(false);
		baroption.setBorderPainted(false);
		baroption.setFont(font);
		this.add(baroption);
		
		alloption = new JButton("全部");
		alloption.setSize(100, 50);
		alloption.setLocation(850,76);
		alloption.setBackground(MyColor.lightblue);
		alloption.setForeground(MyColor.white);
		alloption.setFocusPainted(false);
		alloption.setBorderPainted(false);
		alloption.setFont(font);
		this.add(alloption);
		
		
		//---------------------------------------------------------------------------------------
		pvoptionDetail = new JButton("普通期权");
		pvoptionDetail.setSize(175, 70);
		pvoptionDetail.setLocation(0,127);
		pvoptionDetail.setBackground(MyColor.deepblue);
		pvoptionDetail.setForeground(MyColor.lightblue);
		pvoptionDetail.setFocusPainted(false);
		pvoptionDetail.setBorderPainted(false);
		pvoptionDetail.setFont(font);
		this.add(pvoptionDetail);
		
		boptionDetail = new JButton("二元期权");
		boptionDetail.setSize(175,70);
		boptionDetail.setLocation(0,127);
		boptionDetail.setBackground(MyColor.deepblue);
		boptionDetail.setForeground(MyColor.lightblue);
		boptionDetail.setFocusPainted(false);
		boptionDetail.setBorderPainted(false);
		boptionDetail.setFont(font);
		this.add(boptionDetail);
		
		downandoutoption = new JButton("向下敲出期权");
		downandoutoption.setSize(175, 70);
		downandoutoption.setLocation(0,197);
		downandoutoption.setBackground(MyColor.deepblue);
		downandoutoption.setForeground(MyColor.lightblue);
		downandoutoption.setFocusPainted(false);
		downandoutoption.setBorderPainted(false);
		downandoutoption.setFont(font);
		this.add(downandoutoption);
		
		downandinoption = new JButton("向下敲入期权");
		downandinoption.setSize(175, 70);
		downandinoption.setLocation(0,337);
		downandinoption.setBackground(MyColor.deepblue);
		downandinoption.setForeground(MyColor.lightblue);
		downandinoption.setFocusPainted(false);
		downandinoption.setBorderPainted(false);
		downandinoption.setFont(font);
		this.add(downandinoption);
		
		upandoutoption = new JButton("向上敲出期权");
		upandoutoption.setSize(175, 70);
		upandoutoption.setLocation(0,127);
		upandoutoption.setBackground(MyColor.deepblue);
		upandoutoption.setForeground(MyColor.lightblue);
		upandoutoption.setFocusPainted(false);
		upandoutoption.setBorderPainted(false);
		upandoutoption.setFont(font);
		this.add(upandoutoption);
		
		upandinoption = new JButton("向上敲入期权");
		upandinoption.setSize(175, 70);
		upandinoption.setLocation(0,267);
		upandinoption.setBackground(MyColor.deepblue);
		upandinoption.setForeground(MyColor.lightblue);
		upandinoption.setFocusPainted(false);
		upandinoption.setBorderPainted(false);
		upandinoption.setFont(font);
		this.add(upandinoption);
		
		doublebaroption = new JButton("双重障碍期权");
		doublebaroption.setSize(175, 70);
		doublebaroption.setLocation(0,407);
		doublebaroption.setBackground(MyColor.deepblue);
		doublebaroption.setForeground(MyColor.lightblue);
		doublebaroption.setFocusPainted(false);
		doublebaroption.setBorderPainted(false);
		doublebaroption.setFont(font);
		this.add(doublebaroption);
		
		
		multileveloption = new JButton("<html>多次触及<br/>障碍水平期权</html>");
		multileveloption.setSize(175, 70);
		multileveloption.setLocation(0,477);
		multileveloption.setBackground(MyColor.deepblue);
		multileveloption.setForeground(MyColor.lightblue);
		multileveloption.setFocusPainted(false);
		multileveloption.setBorderPainted(false);
		multileveloption.setFont(font);
		this.add(multileveloption);
		
		font = new Font("微软雅黑",Font.PLAIN,17);
		
		avgexcpriceoption = new JButton("平均执行价格期权");
		avgexcpriceoption.setSize(175, 70);
		avgexcpriceoption.setLocation(0,197);
		avgexcpriceoption.setBackground(MyColor.deepblue);
		avgexcpriceoption.setForeground(MyColor.lightblue);
		avgexcpriceoption.setFocusPainted(false);
		avgexcpriceoption.setBorderPainted(false);
		avgexcpriceoption.setFont(font);
		this.add(avgexcpriceoption);
		
		
		
		avgpriceoption = new JButton("平均价格期权");
		avgpriceoption.setSize(175, 70);
		avgpriceoption.setLocation(0,127);
		avgpriceoption.setBackground(MyColor.deepblue);
		avgpriceoption.setForeground(MyColor.lightblue);
		avgpriceoption.setFocusPainted(false);
		avgpriceoption.setBorderPainted(false);
		avgpriceoption.setFont(font);
		this.add(avgpriceoption);
		
		fixedoption = new JButton("固定执行价格期权");
		fixedoption.setSize(175, 70);
		fixedoption.setLocation(0,197);
		fixedoption.setBackground(MyColor.deepblue);
		fixedoption.setForeground(MyColor.lightblue);
		fixedoption.setFocusPainted(false);
		fixedoption.setBorderPainted(false);
		fixedoption.setFont(font);
		this.add(fixedoption);
		
		floatoption = new JButton("浮动执行价格期权");
		floatoption.setSize(175, 70);
		floatoption.setLocation(0,127);
		floatoption.setBackground(MyColor.deepblue);
		floatoption.setForeground(MyColor.lightblue);
		floatoption.setFocusPainted(false);
		floatoption.setBorderPainted(false);
		floatoption.setFont(font);
		this.add(floatoption);
	
		
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
		table = new NTable(tableRow, 751,383);
		table.setLocation(190,174);
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

//-----------------------------------------------------------------------------------------------------------
		pvoption.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				choose(0);
			}
		});
		boption.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				choose(1);
			}
		});
		lboption.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				choose(2);
			}
		});
		baroption.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				choose(4);
			}
		});
		asianoption.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				choose(3);
			}
		});
		alloption.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				choose(5);
			}
		});
		buttonView.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				choose(5);
			}
		});
		
		choose(5);
		this.setLayout(null);
		this.setSize(960,600);
		this.setVisible(true);
	}
	
	
	private void filltable(Order[] list){
		if(list!=null){
			for (int i = tableRow.getRowCount(); i > 0; i--) {
				tableRow.removeRow(0);
			}
			for(Order order:list){
				Vector v = new Vector();
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
			/*v.add(l);
			v.add(l);
			v.add(l);*/
			tableRow.addRow(v);
			tableRow.addRow(v);
			tableRow.addRow(v);
			tableRow.addRow(v);
			tableRow.addRow(v);
			
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		ImageIcon icon = new ImageIcon("GUI\\background.png");
		Image img = icon.getImage();
		g.drawImage(img, 0, 0, icon.getIconWidth(), icon.getIconHeight(),
				icon.getImageObserver());
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(buttonOption)){
			//GraphicController.changeToPanel(Loader.pvpanel);
			GraphicController.changeToPanel(new PVoptionsPanel("PVoptions"));
		}else if(e.getSource().equals(back)){
			GraphicController.back();
		}else if(e.getSource().equals(close)){
			System.exit(0);
		}else if(e.getSource().equals(mini)){
			GraphicController.mini();
		}else if(e.getSource().equals(buttonView)){
			GraphicController.changeToPanel(new ViewPanel());
		}
	}
	/*
	public double[] getNowPrice(Order list){
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
	}
	*/
	private void choose(int type){
		/*pvoptionDetail,fixedoption,floatoption,boptionDetail;
			downandoutoption,downandinoption,upandoutoption,upandinoption,
			doublebaroption,multileveloption;
			avgexcpriceoption,avgpriceoption;*/
		pvoptionDetail.setVisible(false);
		fixedoption.setVisible(false);
		floatoption.setVisible(false);
		boptionDetail.setVisible(false);
		downandinoption.setVisible(false);
		downandoutoption.setVisible(false);
		upandoutoption.setVisible(false);
		upandinoption.setVisible(false);
		doublebaroption.setVisible(false);
		multileveloption.setVisible(false);
		avgexcpriceoption.setVisible(false);
		avgpriceoption.setVisible(false);
		switch(type){
		case 0:
			//PVoption
			pvoptionDetail.setVisible(true);
			break;
		case 1:
			//Boption
			boptionDetail.setVisible(true);
			break;
		case 2:
			//LBoption
			fixedoption.setVisible(true);
			floatoption.setVisible(true);
			break;
		case 3:
			//Asianoption
			avgexcpriceoption.setVisible(true);
			avgpriceoption.setVisible(true);
			break;
		case 4:
			//BARoption
			doublebaroption.setVisible(true);
			downandinoption.setVisible(true);
			downandoutoption.setVisible(true);
			upandinoption.setVisible(true);
			upandoutoption.setVisible(true);
			multileveloption.setVisible(true);
			break;
		default:
			//all
			break;
		}
	}
}
