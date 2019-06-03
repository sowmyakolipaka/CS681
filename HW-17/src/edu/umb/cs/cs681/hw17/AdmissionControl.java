package edu.umb.cs.cs681.hw17;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class AdmissionControl {
	private int currentVisitors;
	private  volatile boolean done = false;
	private ReentrantLock lock;
	private Condition sufficientVisitorsCondition;
	private AdmissionControl control;
	 public AdmissionControl()
	 {
		 lock = new ReentrantLock();
		 sufficientVisitorsCondition = lock.newCondition();
		 currentVisitors = 0;
		 control = this;
	 }
	 public void setDone() {
			done = true; 
		}
	 public void resetDone() {
		 done = false; 
	 }
	public void enter() {	
		lock.lock();
		System.out.println("Lock obtained");
		try{
			System.out.println(Thread.currentThread().getId() + 
					" (IN): current Visitors: " + currentVisitors);
			while(currentVisitors >= 5){
				System.out.println(Thread.currentThread().getId() + 
						" (IN): await(): Visitors exceeds the upper limit.");
				sufficientVisitorsCondition.await();
			}
			currentVisitors ++;
			System.out.println(Thread.currentThread().getId() + 
					" (IN): new Visitors: " + currentVisitors);
			
		}
		catch (InterruptedException exception){
			exception.printStackTrace();
		}
		finally{
			lock.unlock();
			System.out.println("Lock released");
		}		
		
	}

	public void exit() {
		lock.lock();
		try{
			System.out.println("Lock obtained");
			System.out.println(Thread.currentThread().getId() + 
					" (OT): current Visitors: " + currentVisitors);			
			currentVisitors --;
			System.out.println(Thread.currentThread().getId() + 
					" (OT): new Visitors: " + currentVisitors);
			sufficientVisitorsCondition.signalAll();
		}		
		finally{
			lock.unlock();
			System.out.println("Lock released");
		}
	}

	public int countCurrentVisitors() {
		lock.lock();
		try{
			System.out.println("Lock obtained");
			System.out.println(Thread.currentThread().getId() + 
					" (RE): current Visitors: " + currentVisitors);			
			return currentVisitors;			
		}		
		finally{
			lock.unlock();
			System.out.println("Lock released");
		}
	
		
	}
	/*public static void main(String[] arg) 
	{
			EntranceHandler enterh = new EntranceHandler();
			ExitHandler  exith = new ExitHandler();
			MonitorHandler monitorh = new MonitorHandler();
	}*/
	public class EntranceHandler implements Runnable {
		
		public void run()
		{
			while(!done)
			{ 
				control.enter();				
			}
			System.out.println(Thread.currentThread().getId() + " done = true");
			
		}
		
	}

	public class ExitHandler implements Runnable {
		

		public void run()
		{
			while(!done)
			{  
				control.exit();					
			}
			System.out.println(Thread.currentThread().getId() + " done = true");
			
		}
	}

	public class MonitorHandler implements Runnable {
		
		public void run()
		{			
				control.countCurrentVisitors();
		}
	}
}