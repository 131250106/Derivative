package dataTool;

import gemomitricMeam.MyMath;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import com.mathworks.toolbox.javabuilder.MWClassID;
import com.mathworks.toolbox.javabuilder.MWNumericArray;

public class PriceQueue {
	   LinkedList<Price> queue ;
	   long earliestTime = 0;
	   final static int dataNum = 1000000;
	   Random rand = new Random();
       public PriceQueue()
       {
    	   queue  = DBTool.dbTool.getPrice(dataNum);
    	   if (queue.size() != 0 )
    	   earliestTime  = queue.getLast().getTime();
       }
       
       
       public void reInit()
       {
    	   synchronized (queue)
    	   {
    		   queue = DBTool.dbTool.getPrice(dataNum);
    		   if (queue.size() != 0)
    		   earliestTime = queue.getLast().getTime();
    	   }
       }
       
       public void addPrice( Price price)
       {
    	   synchronized (queue)
    	   {
    		   queue.addFirst(price);
    	   }
    	   if (earliestTime == 0)
    	   {
    		   earliestTime = price.getTime();
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
    	 //由于数字过大分成 几份 没份 1000个
//    	 ArrayList<BigDecimal> decimalList = new ArrayList<BigDecimal>();
    	 while (priceItr.hasNext())
    	 {
    		  temp = priceItr.next();
    		  if (temp.getTime() < time)
    		  {
    			  break;
    		  }
    		  ++num;
    	 }
    	 double p  = 1000.0 / num;
    	 priceItr = queue.iterator();
    	 while (priceItr.hasNext())
    	 {
    		  temp = priceItr.next();
    		  if (temp.getTime() < time)
    		  {
    			  break;
    		  }
    		  if (num <= 1000 || p > rand.nextDouble())
    		  {
    			  total  = total.multiply(new BigDecimal(temp.getPrice()));
    		  }
    	 }
    	 }
    	 return getGeometricalMean(total,1.0/num);
       }
       
       
       public static  double getGeometricalMean(BigDecimal decimal,double power)
       {
      	 MWNumericArray y = new MWNumericArray(power,MWClassID.DOUBLE);
      	 MyMath math =null;
      	 double result = -1;
      	 try
      	 {
      		 math = new MyMath();
      		 result = Double.parseDouble((((Object[])math.getGeomitricMean(1,decimal.toString(),y))[0].toString()));
      	 }
      	 catch(Exception e)
      	 {
      		 e.printStackTrace();
      	 }
      	 return result;
       }
       
       public static void main(String[] args)
       {
    	   
       }
       
       
       
}
