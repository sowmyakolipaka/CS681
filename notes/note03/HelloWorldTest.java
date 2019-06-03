package edu.umb.cs.threads.basics;
 
public class HelloWorldTest{
	public static void main(String[] args){
		GreetingRunnable runnable1 = new GreetingRunnable("Hello World");
		Thread thread1 = new Thread(runnable1);
		thread1.start();
		
		try {
			thread1.join();
			thread1 = new Thread( ()->{System.out.println("Goodbye World");} );
			thread1.start();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
