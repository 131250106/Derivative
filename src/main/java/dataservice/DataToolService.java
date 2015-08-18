package dataservice;

import java.util.Date;

import dataTool.NoDataException;

public interface DataToolService {

	//获得当前沪深300股指
	public double getHuShen300Price();
	
    //获得几个平均数 输入 购买日期 用户亚式期权
	public double getGeometricalMean(Date date)throws NoDataException ;
	
	//须有外界 根据时间设置 判断 是否正在开盘 9：50 到 11：30 ；13：00 到15：00 有节假日休息 
	//开盘期间有数据存入数据库，若非在开盘时期数据不存入数据库
	public void setOnTime(boolean onTime);
	
	//闭盘 
	public boolean getOnTime();
}
