package GUI.PVoptions;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import GUI.GraphicController;
import GUI.MenuPanel;
import GUI.myswing.MyColor;

public class PVoptionsPanel extends MenuPanel{
	
	private JButton pvoption;
	
	public PVoptionsPanel(String name) {
		super("PVoptions");
		
		Font font = new Font("Î¢ÈíÑÅºÚ",Font.PLAIN,20);
		
		pvoption = new JButton("ÆÕÍ¨ÆÚÈ¨");
		pvoption.setSize(175, 70);
		pvoption.setLocation(0,127);
		pvoption.setBackground(MyColor.deepblue);
		pvoption.setForeground(MyColor.lightblue);
		pvoption.setFocusPainted(false);
		pvoption.setBorderPainted(false);
		pvoption.setFont(font);
		pvoption.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//GraphicController.changeToPanel(new PVoptionsPanel("PVoptions"));
			}
		});
		this.add(pvoption);
		
		
	}
	
}
