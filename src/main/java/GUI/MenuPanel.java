package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import GUI.Asianoptions.AsianoptionsPanel;
import GUI.BARoptions.BARoptionsPanel;
import GUI.Boptions.BoptionsPanel;
import GUI.LBoptions.LBoptionsPanel;
import GUI.PVoptions.PVoptionsPanel;
import GUI.myswing.MyColor;

public class MenuPanel extends JPanel implements ActionListener{
	private String panelCategory;
	protected int whereTo;
	protected JButton buttonOption,buttonView,buttonStore;;
	
	protected JButton pvoption,lboption,boption,baroption,asianoption;
	private JButton mini,close,back;
	
	private Image image1 = new ImageIcon("GUI\\background.png").getImage();
	public MenuPanel(String name){
		super();
		panelCategory = name;
		

		Font font = new Font("微软雅黑",Font.PLAIN,18);
		
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
			Color now;
			public void mouseClicked(MouseEvent e) {
				GraphicController.changeToPanel(new PVoptionsPanel("PVoptions"));
			}
			public void mouseEntered(MouseEvent e){
				now = buttonOption.getBackground();
				if(now.equals(MyColor.deepblue2)){
				}else{
				buttonOption.setBackground(MyColor.deepblue3);
				}
			}
			public void mouseExited(MouseEvent e){				
					buttonOption.setBackground(now);
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
			Color now;
			public void mouseClicked(MouseEvent e) {
				GraphicController.changeToPanel(new ViewPanel());
			}
			public void mouseEntered(MouseEvent e){
				now = buttonView.getBackground();
				if(now.equals(MyColor.deepblue2)){
				}else{
				buttonView.setBackground(MyColor.deepblue3);
				}
			}
			public void mouseExited(MouseEvent e){				
					buttonView.setBackground(now);
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
			Color now;
			public void mouseClicked(MouseEvent e) {
				GraphicController.changeToPanel(new StorePanel());
			}
			public void mouseEntered(MouseEvent e){
				now = buttonStore.getBackground();
				if(now.equals(MyColor.deepblue2)){
				}else{
					buttonStore.setBackground(MyColor.deepblue3);
				}
			}
			public void mouseExited(MouseEvent e){				
				buttonStore.setBackground(now);
			}
		});
		this.add(buttonStore);
		
		font = new Font("微软雅黑",Font.PLAIN,20);
		
		pvoption = new JButton("普通期权");
		pvoption.setSize(170, 50);
		pvoption.setLocation(0,76);
		pvoption.setBackground(MyColor.lightblue);
		pvoption.setForeground(MyColor.white);
		pvoption.setFocusPainted(false);
		pvoption.setBorderPainted(false);
		pvoption.setFont(font);
		pvoption.addMouseListener(new MouseAdapter() {
			Color now;
			public void mouseClicked(MouseEvent e) {
				GraphicController.changeToPanel(new PVoptionsPanel("PVoptions"));
			}
			public void mouseEntered(MouseEvent e){
				now = pvoption.getBackground();
				if(now.equals(MyColor.deepblue3)){
				}else{
					pvoption.setBackground(MyColor.deepblue4);
				}
			}
			public void mouseExited(MouseEvent e){				
				pvoption.setBackground(now);
			}
			
		});
		this.add(pvoption);
		
		lboption = new JButton("回望期权");
		lboption.setSize(170, 50);
		lboption.setLocation(340,76);
		lboption.setBackground(MyColor.lightblue);
		lboption.setForeground(MyColor.white);
		lboption.setFocusPainted(false);
		lboption.setBorderPainted(false);
		lboption.setFont(font);
		lboption.addMouseListener(new MouseAdapter() {
			Color now;
			public void mouseClicked(MouseEvent e) {
				GraphicController.changeToPanel(new LBoptionsPanel("LBoptions"));
			}
			public void mouseEntered(MouseEvent e){
				now = lboption.getBackground();
				if(now.equals(MyColor.deepblue3)){
				}else{
					lboption.setBackground(MyColor.deepblue4);
				}
			}
			public void mouseExited(MouseEvent e){				
				lboption.setBackground(now);
			}
		});
		this.add(lboption);
		
		boption = new JButton("二元期权");
		boption.setSize(170, 50);
		boption.setLocation(170,76);
		boption.setBackground(MyColor.lightblue);
		boption.setForeground(MyColor.white);
		boption.setFocusPainted(false);
		boption.setBorderPainted(false);
		boption.setFont(font);
		boption.addMouseListener(new MouseAdapter() {
			Color now;
			public void mouseClicked(MouseEvent e) {
				GraphicController.changeToPanel(new BoptionsPanel("Boptions"));
			}
			public void mouseEntered(MouseEvent e){
				now = boption.getBackground();
				if(now.equals(MyColor.deepblue3)){
				}else{
					boption.setBackground(MyColor.deepblue4);
				}
			}
			public void mouseExited(MouseEvent e){				
				boption.setBackground(now);
			}
		});
		this.add(boption);
		
		
		asianoption = new JButton("亚式期权");
		asianoption.setSize(170, 50);
		asianoption.setLocation(510,76);
		asianoption.setBackground(MyColor.lightblue);
		asianoption.setForeground(MyColor.white);
		asianoption.setFocusPainted(false);
		asianoption.setBorderPainted(false);
		asianoption.setFont(font);
		asianoption.addMouseListener(new MouseAdapter() {
			Color now;
			public void mouseClicked(MouseEvent e) {
				GraphicController.changeToPanel(new AsianoptionsPanel("Asianoptions"));
			}
			public void mouseEntered(MouseEvent e){
				now = asianoption.getBackground();
				if(now.equals(MyColor.deepblue3)){
				}else{
					asianoption.setBackground(MyColor.deepblue4);
				}
			}
			public void mouseExited(MouseEvent e){				
				asianoption.setBackground(now);
			}
		});
		this.add(asianoption);
		
		baroption = new JButton("障碍期权");
		baroption.setSize(170, 50);
		baroption.setLocation(680,76);
		baroption.setBackground(MyColor.lightblue);
		baroption.setForeground(MyColor.white);
		baroption.setFocusPainted(false);
		baroption.setBorderPainted(false);
		baroption.setFont(font);
		baroption.addMouseListener(new MouseAdapter() {
			Color now;
			public void mouseClicked(MouseEvent e) {
				GraphicController.changeToPanel(new BARoptionsPanel("BARoptions"));
			}
			public void mouseEntered(MouseEvent e){
				now = baroption.getBackground();
				if(now.equals(MyColor.deepblue3)){
				}else{
					baroption.setBackground(MyColor.deepblue4);
				}
			}
			public void mouseExited(MouseEvent e){				
				baroption.setBackground(now);
			}
		});
		
		this.add(baroption);
		
		
		
		
		this.setLayout(null);
		this.setSize(960,600);
		this.setVisible(true);
		
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
}
