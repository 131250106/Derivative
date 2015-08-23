package serverGUI;

import bl.CombinationManage;
import bl.OrderManage;

public class serverLoader {
	public static OrderManage orderservice = OrderManage.getInstance();
	public static CombinationManage combinationmanage = CombinationManage.getInstance();
}
