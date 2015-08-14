package GUI.BARoptions;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import GUI.GraphicController;
import GUI.MenuPanel;
import GUI.myswing.MyColor;

public class BARoptionsPanel extends MenuPanel{

	private JButton downandoutoption,downandinoption,upandoutoption,upandinoption,
	doublebaroption,multileveloption;
	
	public BARoptionsPanel(String name) {
		super("BARoptionsPanel");
		Font font = new Font("微软雅黑",Font.PLAIN,20);
		
		downandoutoption = new JButton("向下敲出期权");
		downandoutoption.setSize(175, 70);
		downandoutoption.setLocation(0,197);
		downandoutoption.setBackground(MyColor.deepblue);
		downandoutoption.setForeground(MyColor.lightblue);
		downandoutoption.setFocusPainted(false);
		downandoutoption.setBorderPainted(false);
		downandoutoption.setFont(font);
		downandoutoption.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				GraphicController.changeToPanel(new DownAndOutPanel("DownAndOut"));
			}
		});
		this.add(downandoutoption);
		
		downandinoption = new JButton("向下敲入期权");
		downandinoption.setSize(175, 70);
		downandinoption.setLocation(0,337);
		downandinoption.setBackground(MyColor.deepblue);
		downandinoption.setForeground(MyColor.lightblue);
		downandinoption.setFocusPainted(false);
		downandinoption.setBorderPainted(false);
		downandinoption.setFont(font);
		downandinoption.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				GraphicController.changeToPanel(new DownAndInPanel("DownAndIn"));
			}
		});
		this.add(downandinoption);
		
		upandoutoption = new JButton("向上敲出期权");
		upandoutoption.setSize(175, 70);
		upandoutoption.setLocation(0,127);
		upandoutoption.setBackground(MyColor.deepblue);
		upandoutoption.setForeground(MyColor.lightblue);
		upandoutoption.setFocusPainted(false);
		upandoutoption.setBorderPainted(false);
		upandoutoption.setFont(font);
		upandoutoption.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				GraphicController.changeToPanel(new UpAndOutPanel("UpAndOut"));
			}
		});
		this.add(upandoutoption);
		
		upandinoption = new JButton("向上敲入期权");
		upandinoption.setSize(175, 70);
		upandinoption.setLocation(0,267);
		upandinoption.setBackground(MyColor.deepblue);
		upandinoption.setForeground(MyColor.lightblue);
		upandinoption.setFocusPainted(false);
		upandinoption.setBorderPainted(false);
		upandinoption.setFont(font);
		upandinoption.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				GraphicController.changeToPanel(new UpAndInPanel("UpAndIn"));
			}
		});
		this.add(upandinoption);
		
		doublebaroption = new JButton("双重障碍期权");
		doublebaroption.setSize(175, 70);
		doublebaroption.setLocation(0,407);
		doublebaroption.setBackground(MyColor.deepblue);
		doublebaroption.setForeground(MyColor.lightblue);
		doublebaroption.setFocusPainted(false);
		doublebaroption.setBorderPainted(false);
		doublebaroption.setFont(font);
		doublebaroption.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				GraphicController.changeToPanel(new DoubleBARPanel("DoubleBAR"));
			}
		});
		this.add(doublebaroption);
		
		font = new Font("微软雅黑",Font.PLAIN,20);
		
		multileveloption = new JButton("<html>多次触及<br/>障碍水平期权</html>");
		multileveloption.setSize(175, 70);
		multileveloption.setLocation(0,477);
		multileveloption.setBackground(MyColor.deepblue);
		multileveloption.setForeground(MyColor.lightblue);
		multileveloption.setFocusPainted(false);
		multileveloption.setBorderPainted(false);
		multileveloption.setFont(font);
		multileveloption.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				GraphicController.changeToPanel(new MultiLevelPanel("MultiLevel"));
			}
		});
		this.add(multileveloption);
	}
	
}
