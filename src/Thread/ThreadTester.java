package Thread;

class MyRunnable implements Runnable{
	public void run() {
		go();
	}
	
	public void go() {
		System.out.println("top o' the stack");
	}
}
public class ThreadTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Runnable threadJob=new MyRunnable();
		Thread myThread=new Thread(threadJob);
		myThread.start();
		
		System.out.println("back in main");
	}

}
