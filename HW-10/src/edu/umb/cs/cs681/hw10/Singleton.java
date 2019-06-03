package edu.umb.cs.cs681.hw10;

import java.util.concurrent.locks.ReentrantLock;

public class Singleton {

	private static Singleton instance = null;
	private static ReentrantLock lock = new ReentrantLock();
	private static int counter = 0;	
	
	private Singleton()
	{
		if (this.getClass() == Singleton.class) 
		{
			lock.lock();
			try {
				counter++;
				} finally 
						{
						lock.unlock();
						}
		}
		
	};
	
		public static Singleton getInstance(){
			
			lock.lock();
			
			try {
				
				if(instance==null){
					instance = new Singleton();
					
					System.out.println("A new instance of singleton created by a thread\n ");
				}
				
				System.out.println(Thread.currentThread().getName() + " is running.");
				System.out.println("\nThe running instance "+instance+ "\n");
				
			} finally {
				lock.unlock();
						}
			
			
			return instance;
		}
		
		

		public static String getCounter() {
			// TODO Auto-generated method stub
			return "\nTotal instances "+ counter;
			
		}
	
}