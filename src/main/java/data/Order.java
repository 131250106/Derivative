package data;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	private String Clientid;			//客户ID
	private Option option;			//期权
	private Date deadline;			//到期时间
	private double executeprice;			//执行价格
	private double dealprice;					//买卖价格，买价为正，卖价为负。
	private int number;				//数量,相对于客户为负的
	private double delta;
	private double gamma;
	private double theta;
	private double vega;

    public Order(String clientid, Option option, Date deadline, double executeprice, double
            dealprice, int number, double delta, double gamma, double theta, double vega) {
        Clientid = clientid;
        this.option = option;
        this.deadline = deadline;
        this.executeprice = executeprice;
        this.dealprice = dealprice;
        this.number = number;
        this.delta = delta;
        this.gamma = gamma;
        this.theta = theta;
        this.vega = vega;
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
}
