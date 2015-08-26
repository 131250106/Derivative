package dataTool;

public class IdTEST {
   public static void main(String[] args)
   {
	   DBTool db = (DBTool) DBTool.getInstance();
	   new Thread()
	   {
		   public void run()
		   {
			   while (true)
			   {
				   System.out.println("thread1 : "+db.getOneOrderId());
//				   try {
//					Thread.sleep(200);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			   }
		   }
	   }.start();
	   new Thread()
	   {
		   public void run()
		   {
			   while (true)
			   {
				   System.out.println("thread2 : "+db.getOneOrderId());
//				   try {
//					Thread.sleep(100);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			   }
		   }
	   }.start();
	   new Thread()
	   {
		   public void run()
		   {
			   while (true)
			   {
				   System.out.println("thread3 : "+db.getOneOrderId());
//				   try {
//					Thread.sleep(100);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			   }
		   }
	   }.start();
	   
   }
}
