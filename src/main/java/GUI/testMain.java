package GUI;

public class testMain {
	public static void main(String[]args){
		new Thread(new Load()).start();;
		new Thread(new GUI()).start();;
	}
	static class Load implements Runnable{

		@Override
		public void run() {
			new Loader();
		}
		
	}
	static class GUI implements Runnable{

		@Override
		public void run() {
			GraphicController.changeToPanel(new MenuPanel("MenuPanel"));
		}
		
	}
}
