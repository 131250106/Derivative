package dataTool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IdTEST {
   public static void main(String[] args) throws ParseException
   {
	   toDeadlineTime(new Date());
//	   DBTool db = (DBTool) DBTool.getInstance();
//	   new Thread()
//	   {
//		   public void run()
//		   {
//			   while (true)
//			   {
//				   System.out.println("thread1 : "+db.getOneOrderId());
////				   try {
////					Thread.sleep(200);
////				} catch (InterruptedException e) {
////					// TODO Auto-generated catch block
////					e.printStackTrace();
////				}
//			   }
//		   }
//	   }.start();
//	   new Thread()
//	   {
//		   public void run()
//		   {
//			   while (true)
//			   {
//				   System.out.println("thread2 : "+db.getOneOrderId());
////				   try {
////					Thread.sleep(100);
////				} catch (InterruptedException e) {
////					// TODO Auto-generated catch block
////					e.printStackTrace();
////				}
//			   }
//		   }
//	   }.start();
//	   new Thread()
//	   {
//		   public void run()
//		   {
//			   while (true)
//			   {
//				   System.out.println("thread3 : "+db.getOneOrderId());
////				   try {
////					Thread.sleep(100);
////				} catch (InterruptedException e) {
////					// TODO Auto-generated catch block
////					e.printStackTrace();
////				}
//			   }
//		   }
//	   }.start();
	   
   }
   
   public static void dateTest() throws ParseException
   {
	   String dateStr = "19941202";
	   SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd HHmmss");
	   Date date = format.parse(String.valueOf(Long.parseLong(dateStr)+1)+" 000000");
	   SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMdd HHmmss");
	   System.out.println(format2.format(date));
   }
   
   public static long toDeadlineTime(Date date) throws NumberFormatException, ParseException
   {
	   SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	   SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMdd HHmmss");
	   String dateStr = format1.format(date);
	   Date newDate = format2.parse(String.valueOf(Long.parseLong(dateStr)+1)+" 000000");
	   System.out.println(format2.format(newDate));
	   return newDate.getTime();
   }
}
