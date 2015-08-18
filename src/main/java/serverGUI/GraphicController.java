package serverGUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphicController {
	private static JFrame frame = new JFrame();
	private static JPanel panel = new JPanel();
	private static Stack<JPanel> stackOfPanels = new Stack<JPanel>();
	private static boolean isFirstIn = true;
	private static int x,y;
	
	static{
		frame.setLayout(null);
		frame.setUndecorated(true);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
			x = e.getX();
			y = e.getY();
			}
		});
		frame.addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseDragged(MouseEvent e) {
			int xOnScreen = e.getXOnScreen();
			int yOnScreen = e.getYOnScreen();
			int newx = xOnScreen - x;
			int newy = yOnScreen - y;
			frame.setLocation(newx, newy);
		}
		});
	}
		public static void changeToPanel(JPanel nextPanel){
			try{
			if(nextPanel.getClass().toString().equals(panel.getClass().toString()))
				return;
			panel.setVisible(false);
			stackOfPanels.push(nextPanel);
			panel = nextPanel;
			setPanel();
			}catch(Exception e){
				//I don't know why
			}
			
		}
		
		public static void setPanel(){
			frame.getContentPane().removeAll();
			panel.setVisible(true);
			frame.getContentPane().add(panel);
			frame.setSize(panel.getSize());
			if(isFirstIn)
				frame.setLocationRelativeTo(null);
			isFirstIn = false;

		}
		
		public static void back(){
			try {
				if(stackOfPanels.size()==1){
					return;
				}
				panel.setVisible(false);
				stackOfPanels.pop();
				panel = stackOfPanels.peek();
				setPanel();
			} catch (Exception e) {
			}
		}
		
		public static void mini(){
			frame.setExtendedState(JFrame.ICONIFIED);
		}
	
}
