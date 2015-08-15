package GUI;

import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import GUI.PVoptions.PVoptionsPanel;
import GUI.myswing.MyColor;
import GUI.myswing.NTable;
import GUI.myswing.ViewTm;

public class ViewPanel extends JPanel implements ActionListener{
	private JButton buttonOption,buttonView;;
	private JButton mini,close,back;
	private NTable table;
	private ViewTm tableRow = new ViewTm();
	DefaultTableCellRenderer render;
	
	public ViewPanel(){
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
		this.add(buttonView);
		
		
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
		table = new NTable(tableRow, 711,383);
		for (int i = 0; i <table.getColumnCount(); i++) {
			table.getColumn(table.getColumnName(i))
			.setCellRenderer(render);
			
		}
		
		table.setLocation(200,174);
		Vector test = new Vector();
		test.add(1);
		test.add(1);
		test.add(1);
		test.add(1);
		test.add(1);
		test.add(1);
		tableRow.addRow(test);
		Vector test2 = new Vector();
		test.add(1);
		test.add(1);
		test.add(1);
		test.add(1);
		test.add(1);
		test.add(1);
		tableRow.addRow(test2);
		Vector test3 = new Vector();
		test.add(1);
		test.add(1);
		test.add(1);
		test.add(1);
		test.add(1);
		test.add(1);
		tableRow.addRow(test3);
		table.revalidate();
		table.repaint();
		this.add(table);
		
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
		}else if(e.getSource().equals(buttonView)){
			GraphicController.changeToPanel(new ViewPanel());
		}
	}
}
