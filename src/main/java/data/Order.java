package data;

import java.util.Date;

public class Order {
	private String Clientid;			//客户ID
	private Option option;			//期权
	private Date deadline;			//到期时间
	private double executeprice;			//执行价格
	private double presentprice;			//当前价格
	private double dealprice;					//买卖价格，买价为正，卖价为负。
	private int number;				//数量,相对于客户为负的
	private double delta;
	private double gamma;
	private double theta;
	private double vega;
}
