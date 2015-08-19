package data;

public class ServerData {
	private static double Risk_free_Rate;						//无风险率
	private static double fluctuation_Rate;						//波动率
	
	public static void setRisk_free_Rate(double rate){
		Risk_free_Rate = rate;
	}
	
	public static double getRisk_free_Rate(){
		return Risk_free_Rate;
	}
	
	public static void setFluctuation_Rate(double rate){
		fluctuation_Rate = rate;
	}
	
	public static double getFluctuation_Rate(){
		return fluctuation_Rate;
	}
	
}
