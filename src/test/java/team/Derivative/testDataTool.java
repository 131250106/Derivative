package team.Derivative;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import dataTool.DBTool;
import dataTool.DataTool;

public class testDataTool {
       public static void main(String[] args)
       {
    	   Runnable run1 = new Runnable()
    	   {
    		   public void run()
    		   {
    			   while(true)
    			   {
				    DBTool.getInstance();
				    DataTool dataTool = new DataTool();
				    System.out.println("price : " + dataTool.getHuShen300Price());
				    try {
						TimeUnit.SECONDS.sleep(4);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
    			   }
    		   }
    	   };
    	   Executors.newCachedThreadPool().execute(run1);
       }
}
