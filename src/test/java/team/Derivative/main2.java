package team.Derivative;

import dataTool.DBTool;

public class main2 {
 public static void main(String[] args)
 {
	 DBTool dbTool = (DBTool) DBTool.getInstance();
	 dbTool.getAllOrder();
 }
}
