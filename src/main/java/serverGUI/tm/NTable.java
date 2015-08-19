package serverGUI.tm;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import GUI.myswing.MyColor;


public class NTable extends JPanel{
	private AbstractTableModel tableModel;
	private JTable table;
	private JScrollPane scrollPane;
	private JTableHeader tableHead;
	public NTable(AbstractTableModel m ,int x ,int y){
		super();
		this.setLayout(null);
		tableModel = m;
		this.setSize(x,y);
		table = new JTable(tableModel);
		
		table.setForeground(MyColor.deepblue);
		table.setBackground(MyColor.white);
		table.setGridColor(MyColor.black);
		
		tableHead = table.getTableHeader();
		tableHead.setDefaultRenderer(new headerRender());
		tableHead.setPreferredSize(new Dimension(tableHead.getWidth(), 24));
		tableHead.setBackground(MyColor.deepblue);
		
		setRenderer();
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setFocusable(false);
		table.setRowHeight(23);
		table.setShowGrid(false);
		this.setBackground(new Color(50, 50, 55));
		scrollPane = new JScrollPane(table);
		scrollPane.setSize(x + 4, y + 4);
		scrollPane.setBackground(MyColor.deepblue);
		scrollPane.getViewport().setBackground(MyColor.white);
		scrollPane
		.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		scrollPane.setLocation(-2, -2);
		this.add(scrollPane);

	}
	
	class headerRender extends DefaultTableCellRenderer {
		JLabel label = new JLabel("", JLabel.CENTER);

		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			label.setText(value.toString());
			label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
			label.setForeground(MyColor.white);
			Dimension d = label.getSize();
			d.height = 36;
			label.setSize(d);
			return label;
		}

	}
	
	public void setRenderer() {
		DefaultTableCellRenderer render = new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				if (hasFocus)
					setBorder(null);
				if (row % 2 == 1) {
					setBackground(MyColor.white); // 设置奇数行底色
				} else if (row % 2 == 0) {
					setBackground(MyColor.lightblue); // 设置偶数行底色
				}
				return super.getTableCellRendererComponent(table, value,
						isSelected, hasFocus, row, column);

			}
		};
		render.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumn(table.getColumnName(i)).setCellRenderer(render);
		}
	}
	public AbstractTableModel getModel() {
		return tableModel;
	}

	public JTable getTable() {
		return table;
	}

	public void TableRefresh() {
		table.revalidate();
		table.repaint();

	}

	public void Setmodel(AbstractTableModel ATM) {
		table.setModel(ATM);
		setRenderer();
	}

	public void setAutoResizeMode(int autoResizeOff) {
		table.setAutoResizeMode(autoResizeOff);

	}

	public void setColumnWidth(int column, int width) {
		table.getColumnModel().getColumn(column).setPreferredWidth(width);

	}


	public int getColumnCount() {
		return table.getColumnCount();
	}

	public String getColumnName(int i) {
		return table.getColumnName(i);
	}

	public TableColumn getColumn(Object identifier) {
		return table.getColumn(identifier);
	}
}
