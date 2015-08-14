package GUI;

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
	private JButton buttonOption,buttonView;;
	
	private JButton pvoption,lboption,boption,baroption,asianoption;
	private JButton mini,close,back;
	
	private Image image1 = new ImageIcon("GUI\\background.png").getImage();
	public MenuPanel(String name){
		super();
		panelCategory = name;
		

		Font font = new Font("΢���ź�",Font.PLAIN,20);
		
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
		
		buttonOption = new JButton("����");
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
		
		buttonView = new JButton("�鿴");
		buttonView.setBounds(110,30, 110,45);
		buttonView.setForeground(MyColor.white);
		buttonView.setBackground(MyColor.deepblue);
		buttonView.setFocusPainted(false);
		buttonView.setBorderPainted(false);
		buttonView.setFont(font);
		this.add(buttonView);
		
		pvoption = new JButton("��ͨ��Ȩ");
		pvoption.setSize(170, 50);
		pvoption.setLocation(0,76);
		pvoption.setBackground(MyColor.lightblue);
		pvoption.setForeground(MyColor.white);
		pvoption.setFocusPainted(false);
		pvoption.setBorderPainted(false);
		pvoption.setFont(font);
		pvoption.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				GraphicController.changeToPanel(new PVoptionsPanel("PVoptions"));
			}
		});
		this.add(pvoption);
		
		lboption = new JButton("������Ȩ");
		lboption.setSize(170, 50);
		lboption.setLocation(340,76);
		lboption.setBackground(MyColor.lightblue);
		lboption.setForeground(MyColor.white);
		lboption.setFocusPainted(false);
		lboption.setBorderPainted(false);
		lboption.setFont(font);
		lboption.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				GraphicController.changeToPanel(new LBoptionsPanel("LBoptions"));
			}
		});
		this.add(lboption);
		
		boption = new JButton("��Ԫ��Ȩ");
		boption.setSize(170, 50);
		boption.setLocation(170,76);
		boption.setBackground(MyColor.lightblue);
		boption.setForeground(MyColor.white);
		boption.setFocusPainted(false);
		boption.setBorderPainted(false);
		boption.setFont(font);
		boption.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				GraphicController.changeToPanel(new BoptionsPanel("Boptions"));
			}
		});
		this.add(boption);
		
		
		asianoption = new JButton("��ʽ��Ȩ");
		asianoption.setSize(170, 50);
		asianoption.setLocation(510,76);
		asianoption.setBackground(MyColor.lightblue);
		asianoption.setForeground(MyColor.white);
		asianoption.setFocusPainted(false);
		asianoption.setBorderPainted(false);
		asianoption.setFont(font);
		asianoption.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				GraphicController.changeToPanel(new AsianoptionsPanel("Asianoptions"));
			}
		});
		this.add(asianoption);
		
		baroption = new JButton("�ϰ���Ȩ");
		baroption.setSize(170, 50);
		baroption.setLocation(680,76);
		baroption.setBackground(MyColor.lightblue);
		baroption.setForeground(MyColor.white);
		baroption.setFocusPainted(false);
		baroption.setBorderPainted(false);
		baroption.setFont(font);
		baroption.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				GraphicController.changeToPanel(new BARoptionsPanel("BARoptions"));
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
			GraphicController.changeToPanel(Loader.pvpanel);
		}else if(e.getSource().equals(back)){
			GraphicController.back();
		}else if(e.getSource().equals(close)){
			System.exit(0);
		}else if(e.getSource().equals(mini)){
			GraphicController.mini();
		}
	}
}
