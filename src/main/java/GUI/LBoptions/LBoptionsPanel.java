package GUI.LBoptions;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import GUI.GraphicController;
import GUI.MenuPanel;
import GUI.myswing.MyColor;

public class LBoptionsPanel extends MenuPanel{

	private JButton fixedoption,floatoption;
	
	public LBoptionsPanel(String name) {
		super("LBoptions");
		
		Font font = new Font("微软雅黑",Font.PLAIN,17);
		
		fixedoption = new JButton("固定执行价格期权");
		fixedoption.setSize(175, 70);
		fixedoption.setLocation(0,197);
		fixedoption.setBackground(MyColor.deepblue);
		fixedoption.setForeground(MyColor.lightblue);
		fixedoption.setFocusPainted(false);
		fixedoption.setBorderPainted(false);
		fixedoption.setFont(font);
		fixedoption.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				GraphicController.changeToPanel(new FixedPanel("Fixed"));
			}
		});
		this.add(fixedoption);
		
		floatoption = new JButton("浮动执行价格期权");
		floatoption.setSize(175, 70);
		floatoption.setLocation(0,127);
		floatoption.setBackground(MyColor.deepblue);
		floatoption.setForeground(MyColor.lightblue);
		floatoption.setFocusPainted(false);
		floatoption.setBorderPainted(false);
		floatoption.setFont(font);
		floatoption.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				GraphicController.changeToPanel(new FloatPanel("Float"));
			}
		});
		this.add(floatoption);
		
	}

}
