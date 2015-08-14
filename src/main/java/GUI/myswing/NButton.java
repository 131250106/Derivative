package GUI.myswing;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class NButton extends JPanel implements MouseListener{

	private JButton button;
	ImageIcon before;
	ImageIcon pressedim;
	ImageIcon focusim;
	
	JPanel pressed;
	JPanel focus;
	
	Color black = new Color(0,0,0,0.0f);
	
	public NButton(int width,int height,String content) {
		super();
		this.setSize(width,height);
		this.setLayout(null);
		this.setOpaque(false);
		JButton s;
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
