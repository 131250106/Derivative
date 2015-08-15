package GUI.myswing;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class ViewTm extends AbstractTableModel{
	Vector columnNames;
	public Vector rows;
	public ViewTm(){
		columnNames = new Vector();
		columnNames.add("购买日期");
		columnNames.add("购买日期");
		columnNames.add("购买日期");
		columnNames.add("购买日期");
		columnNames.add("购买日期");
		columnNames.add("购买日期");
		rows = new Vector();
	}
	
	public void addRow(Vector v){
		 rows.add(v); 
	 }
	
	public void removeRow(int r){
		 rows.remove(r); 
	 }
	@Override
	public int getRowCount() {
		return this.rows.size();
	}
	@Override
	public int getColumnCount() {
		return this.columnNames.size();
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}
}
