package dataTool;

import gemomitricMeam.MyMath;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.LinkedList;

import com.mathworks.toolbox.javabuilder.MWClassID;
import com.mathworks.toolbox.javabuilder.MWNumericArray;

public class PriceQueue {
	   LinkedList<Price> queue ;
	   
       public PriceQueue()
       {
    	   queue  = DBTool.dbTool.getPrice(100000);
       }
       
       public void addPrice( Price price)
       {
    	   synchronized (queue)
    	   {
    		   queue.addFirst(price);
    	   }
    	   DBTool.dbTool.addPrice(price);
       }
       
       public double getGeometricalMean(long time) throws NoDataException
       {
    	 int size  = 0;
    	 BigDecimal total = new BigDecimal(1);
    	 int num = 0;
    	 synchronized(queue)
    	 {
    		 size = this.queue.size();
    	 if (size == 0 || time > queue.getFirst().getTime())
    		 throw new NoDataException();
    	 Iterator<Price> priceItr = queue.iterator();
    	 Price temp = null;
    	 while (priceItr.hasNext())
    	 {
    		  temp = priceItr.next();
    		  if (temp.getTime() < time)
    		  {
    			  break;
    		  }
    		  total  = total.multiply(new BigDecimal(temp.getPrice()));
    		  ++num;
    	 }
    	 }
    	 return Math.pow(total.doubleValue(), (1.0 / num));
       }
       
       public static  double getGeometricalMean(BigDecimal decimal,double power)
       {
      	 MWNumericArray y = new MWNumericArray(power,MWClassID.DOUBLE);
      	 MyMath math =null;
      	 double result = -1;
      	 try
      	 {
      		 math = new MyMath();
      		 result = Double.parseDouble(((Object[])math.getGeomitricMean(1,decimal.toString(),y))[0].toString());
      	 }
      	 catch(Exception e)
      	 {
      		 e.printStackTrace();
      	 }
      	 return result;
       }
       
       
       
}
