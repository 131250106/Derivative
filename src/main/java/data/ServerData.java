package data;

import java.util.Date;

public class ServerData {
	private static double risk_free_Rate;						//无风险率
	private static double fluctuation_Rate;						//波动率
	private static double float_Rate = 0.02;						//价格浮动
	
	public static double getFloat_Rate() {
		return float_Rate;
	}

	public static void setFloat_Rate(double float_Rate) {
		ServerData.float_Rate = float_Rate;
	}

	public static void setRisk_free_Rate(double rate){
		risk_free_Rate = rate;
	}
	
	public static double getRisk_free_Rate(){
		return risk_free_Rate;
	}
	
	public static void setFluctuation_Rate(double rate){
		fluctuation_Rate = rate;
	}
	
	public static double getFluctuation_Rate(){
		return fluctuation_Rate;
	}

	public static double getDeadTime(Date deadline) {
		// TODO Auto-generated method stub
		Date now = new Date();
		double deadTime =(double)(deadline.getTime() - now.getTime())
				/ ((24 * 60 * 60 *1000 ))/365;
		return deadTime;
	}
	
}
