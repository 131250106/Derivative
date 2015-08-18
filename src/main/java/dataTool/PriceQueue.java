package dataTool;

import gemomitricMeam.MyMath;

import java.math.BigDecimal;
import java.util.Arrays;
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
    	   queue  = ((DBTool)DBTool.getInstance()).getPrice(dataNum);
    	   if (queue.size() != 0 )
    	   earliestTime  = queue.getLast().getTime();
       }
       
       
       public void reInit()
       {
    	   synchronized (queue)
    	   {
    		   queue = ((DBTool)DBTool.getInstance()).getPrice(dataNum);
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
    	   ((DBTool)DBTool.getInstance()).addPrice(price);
       }
       
       public double getGeometricalMean(long time) throws NoDataException
       {
    	 int size  = 0;
    	 BigDecimal[] total = new BigDecimal[10];
    	 int[] count = new int[10];
    	 Arrays.fill(count, 0);
    	 for (int i = 0; i < 10; ++i)
    	 {
    	  total[i] = new BigDecimal(1);	 
    	 }
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
    		  ++num;
    	 }
    	 double p  = 70.0 / num;
    	 priceItr = queue.iterator();
    	 boolean first = true;
    	 
    	 while (priceItr.hasNext())
    	 {
    		  temp = priceItr.next();
    		  if (temp.getTime() < time)
    		  {
    			  break;
    		  }
    		  for (int i = 0; i < 10; ++i)
    		  {
    			  if (num <= 60 || p > rand.nextDouble()||first)
    		      {
    			  total[i]  = total[i].multiply(new BigDecimal(temp.getPrice()));
    			  count[i]++;
    			  if (first)
    			  {
    				  first = false;
    			  }
    		  }
    		  }
    	 }
    	 }
    	 double result = 0;
    	 double temp = -1;
    	 int errorNum = 0;
    	 for (int i = 0; i < 10; ++i)
    	 {
    		 temp = getGeometricalMean(total[i],1.0/count[i]);
    		 if (temp == -1 ) 
    			 {
    			 errorNum++;
    			 continue;
    			 }
    		 else 
    		 {
    			 result += temp;
    		 }
    	 }
    	 return result / (10 - errorNum);
       }
       
       
       public static  double getGeometricalMean(BigDecimal decimal,double power)
       {
      	 MWNumericArray y = new MWNumericArray(power,MWClassID.DOUBLE);
      	 MyMath math =null;
      	 double result = -1;
      	 double max = Double.MAX_VALUE;
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
       
       
       public static BigDecimal  newTonItr(BigDecimal decimal,int count)
       {
    	   BigDecimal temp = new BigDecimal(2);
    	   BigDecimal one = new BigDecimal(1);
    	   BigDecimal result = one;
    	   for (int i = 0; i < count; ++i)
    	   {
    		   result = (result.add(decimal.divide(result,1000,BigDecimal.ROUND_DOWN))).divide(temp,1000,BigDecimal.ROUND_DOWN);
    	   }
    	   return result;
       }
       
       public static void main(String[] args)
       {
    	   System.out.println(getGeometricalMean(new BigDecimal(2),0.5));
       }
       
       
       
}
