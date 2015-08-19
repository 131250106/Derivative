package data;

import java.util.Date;

public class OrderOFholdings {
	private Option option; // 期权
	private Date deadline; // 到期时间
	private int number; // 数量,购买记录相加就行
	private double cost;//成本，是买卖价格的加权平均
	/**
	 * 如果一开始买了A100个，买价为50，那么成本就是50，如果后来有卖了A120个，卖价为60，那么成本就是（50*100-60*120）/（100-120）
	 * */
	private int deadTime; // 距离到期时间（给界面用的，数据库那边不用管）
	
	
	public OrderOFholdings(Option option, Date deadline, int number, double cost) {
		super();
		this.option = option;
		this.deadline = deadline;
		this.number = number;
		this.cost = cost;
	}
	
	public Option getOption() {
		return option;
	}
	public Date getDeadline() {
		return deadline;
	}
	public int getNumber() {
		return number;
	}
	public double getCost() {
		return cost;
	}
	
	public int getDeadTime() {
		Date now = new Date();
		deadTime = (int) (deadline.getTime() - now.getTime())
				/ (24 * 60 * 60 * 1000);
		return this.deadTime;
	}
	
}
