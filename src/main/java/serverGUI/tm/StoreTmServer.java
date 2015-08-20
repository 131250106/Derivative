package serverGUI.tm;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class StoreTmServer extends AbstractTableModel{
	Vector columnNames;
	public Vector rows;
	public StoreTmServer(){
		columnNames = new Vector();
		columnNames.add("用户ID");
		columnNames.add("期权种类");
		columnNames.add("类型");
		columnNames.add("买/卖");
		columnNames.add("期限");
		columnNames.add("成本");
		columnNames.add("数量");
		columnNames.add("现实当前价格");
		columnNames.add("alpha");
		columnNames.add("gamma");
		columnNames.add("theta");
		columnNames.add("vega");		
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
