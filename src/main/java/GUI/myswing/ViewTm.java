package GUI.myswing;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class ViewTm extends AbstractTableModel{
	Vector columnNames;
	public Vector rows;
	public ViewTm(){
		columnNames = new Vector();
		columnNames.add("用户");
		columnNames.add("购买日期");
		columnNames.add("距离到期日时间");
		columnNames.add("执行价格");
		columnNames.add("无风险利率");
		rows = new Vector();
	}
	
	public void addRow(Vector v){
		 rows.add(v); 
	 }
	
	public void removeRow(int r){
		 rows.remove(r); 
	 }
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return (String)this.columnNames.get(column);
	}

	public Class getColumnClass(int c) {  
		return getValueAt(0, c).getClass();  
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
	public Object getValueAt(int row, int column) {
		Vector v = (Vector) this.rows.get(row);
		return v.get(column);
		//return v;
	}
	 public void setValueAt(Object value, int row, int column) {  
		 ((Vector)this.rows.get(row)).set(column, value);
		 fireTableCellUpdated(row, column);  
		 
	 }  
	 
	 public boolean isCellEditable(int row, int col) {  
		 return false;  
	 }  
}
