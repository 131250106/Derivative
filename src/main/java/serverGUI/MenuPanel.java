 package serverGUI;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import GUI.myswing.MyColor;

public class MenuPanel extends JPanel implements ActionListener{
	private String panelCategory;
	protected int whereTo;
	protected JButton buttonOption,buttonView,buttonStore;;;
	
	private JButton pvoption,lboption,boption,baroption,asianoption;
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
		
		buttonOption = new JButton("参数输入");
		buttonOption.setBounds(0, 30, 110,45);
		//buttonOption.setContentAreaFilled(false);
		buttonOption.setForeground(MyColor.white);
		buttonOption.setBackground(MyColor.deepblue);
		buttonOption.setFocusPainted(false);
		buttonOption.setBorderPainted(false);
		buttonOption.setFont(font);
		buttonOption.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				GraphicControllerServer.changeToPanel(new ArgumentPanel("argument"));
			}
			public void mouseEntered(MouseEvent e){
				buttonOption.setBackground(MyColor.deepblue3);
			}
			public void mouseExited(MouseEvent e){
				buttonOption.setBackground(MyColor.deepblue);
			}
		});
		buttonOption.addMouseMotionListener(new MouseAdapter() {
			
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
				GraphicControllerServer.changeToPanel(new ViewPanelServer("View"));
			}
			public void mouseEntered(MouseEvent e){
				buttonOption.setBackground(MyColor.deepblue3);
			}
			public void mouseExited(MouseEvent e){
				buttonOption.setBackground(MyColor.deepblue);
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

				GraphicControllerServer.changeToPanel(new StorePanelServer("Store"));
			}
			public void mouseEntered(MouseEvent e){
				buttonOption.setBackground(MyColor.deepblue3);
			}
			public void mouseExited(MouseEvent e){
				buttonOption.setBackground(MyColor.deepblue);
			}
		});
		this.add(buttonStore);
		
		this.setLayout(null);
		this.setSize(960,600);
		this.setVisible(true);
		
	}
	@Override
	protected void paintComponent(Graphics g) {
		ImageIcon icon = new ImageIcon("GUI\\backgroundserver.png");
		Image img = icon.getImage();
		g.drawImage(img, 0, 0, icon.getIconWidth(), icon.getIconHeight(),
				icon.getImageObserver());
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(buttonOption)){
			GraphicControllerServer.changeToPanel(new ArgumentPanel("argument"));
		}else if(e.getSource().equals(back)){
			GraphicControllerServer.back();
		}else if(e.getSource().equals(close)){
			System.exit(0);
		}else if(e.getSource().equals(mini)){
			GraphicControllerServer.mini();
		}else if(e.getSource().equals(buttonView)){
			GraphicControllerServer.changeToPanel(new ViewPanelServer("View"));
		}else if(e.getSource().equals(buttonStore)){
			GraphicControllerServer.changeToPanel(new StorePanelServer("Store"));
		}
	}
}
