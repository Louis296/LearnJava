package Thread;

public class RunThreadsTest implements Runnable{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Runnable runner=new RunThreadsTest();
		Thread alpha=new Thread(runner);
		Thread beta=new Thread(runner);
		alpha.setName("Alpha thread");
		beta.setName("Beta thread");
		alpha.start();
		beta.start();
	}
	
	public void run() {
		for(int i=0;i<25;i++) {
			synchronized (this) {
				String threadName=Thread.currentThread().getName();
				System.out.println(threadName+" is running!");
			}
			
		}
	}
	
}
