package data;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	private String Clientid; // 客户ID
	private Option option; // 期权
	private Date deadline; // 到期时间
	private Date buyDate; // 购买日期
	private double executeprice; // 执行价格
	private double dealprice; // 买卖价格，买价为正，卖价为负。
	private int number; // 数量,对于客户来说买为正，卖为负（市商相对于客户为负的）
	private double delta;
	private double gamma;
	private double theta;
	private double vega;
	private boolean isOpen;				//如果为true，则为开仓，否则为平仓；注：如果以前有这个期权，做相反操作为平仓，否则为开仓
	private int deadTime; // 距离到期时间
	private String id;

	public Order(String clientid, Option option, Date deadline,
			double executeprice, double dealprice, int number,boolean isOpen) {
		Clientid = clientid;
		this.option = option;
		this.deadline = deadline;
		this.executeprice = executeprice;
		this.dealprice = dealprice;
		this.number = number;
		this.isOpen = isOpen;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public Date getBuyDate() {
		return this.buyDate;
	}

	public void setOrderId(String id) {
		this.id = id;
	}

	public String getOrderId() {
		return id;
	}

	public String getClientid() {
		return Clientid;
	}

	public Option getOption() {
		return option;
	}

	public Date getDeadline() {
		return deadline;
	}

	public double getExecuteprice() {
		return executeprice;
	}

	public double getDealprice() {
		return dealprice;
	}

	public int getNumber() {
		return number;
	}

	public double getDelta() {
		return delta;
	}

	public double getGamma() {
		return gamma;
	}

	public double getTheta() {
		return theta;
	}

	public double getVega() {
		return vega;
	}

	public int getDeadTime() {
		Date now = new Date();
		deadTime = (int) (deadline.getTime() - now.getTime())
				/ (24 * 60 * 60 * 1000);
		return this.deadTime;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	public String toString() {
		return "\tClientid:\t" + Clientid + "\toption:\t" + option
				+ "\tdeadline:\t" + deadline + "\texecuteprice:\t"
				+ executeprice + "\tdealprice:\t" + dealprice + "\tnumber:\t"
				+ number + "\tdelta:\t" + delta + "\tgamma:\t" + gamma
				+ "\ttheta:\t" + theta + "\tvega:\t" + vega + "\t"
				+"\topen:\t"+isOpen;
	}

}
