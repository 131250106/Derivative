package team.Derivative;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import data.User;
import dataTool.DBTool;
import dataTool.DataTool;
import dataTool.NoDataException;

public class main {
       public static void main(String[] args) throws ParseException, ClassNotFoundException, SQLException, InterruptedException
       {
    	   DBTool db = (DBTool) DBTool.getInstance();
    	   DataTool dataTool = new DataTool();
    	   SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd hhmmss");
    	   while (true)
    	   {
    		   TimeUnit.SECONDS.sleep(2);
    		   
    		   try {
				System.out.println("price "+ dataTool.getGeometricalMean(format.parse("20150817 140000")));
			} catch (NoDataException e) {
				e.printStackTrace();
			}
    	   }
    		   
       }
       
       public static BigDecimal  newTonItr(BigDecimal decimal,int count)
       {
    	   BigDecimal temp = new BigDecimal(2);
    	   BigDecimal one = new BigDecimal(1);
    	   BigDecimal result = one;
    	   for (int i = 0; i < count; ++i)
    	   {
    		   result = (result.add(decimal.divide(result,500,BigDecimal.ROUND_HALF_UP))).divide(temp,500,BigDecimal.ROUND_HALF_UP);
    	   }
    	   return result;
       }
}
