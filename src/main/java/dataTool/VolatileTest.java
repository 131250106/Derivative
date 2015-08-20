package dataTool;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;

public class VolatileTest {
        double on  = 1;
        public double isOn()
        {
        	return on;
        }
       public void beginTest()
       {
    	   
    	   
    	   Timer timer = new Timer();
    	   timer.schedule(new TimerTask()
    	   {

			public void run() {
				on = 3;
			}
    		   
    	   }, 0,2000);
    	   
    	   try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
       }
       public static void main(String[] args)
       {
    	   new VolatileTest().beginTest();
       }
}
