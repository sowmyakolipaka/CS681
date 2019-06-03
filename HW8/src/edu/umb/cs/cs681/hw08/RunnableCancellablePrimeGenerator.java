package edu.umb.cs.cs681.hw08;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeGenerator extends PrimeGenerator {
	private boolean done = false;
	ReentrantLock lock = new ReentrantLock();
	int count = 1;
	
	public RunnableCancellablePrimeGenerator(long from, long to) {
		super(from, to);
		lock = new ReentrantLock();
	}
		
	public void setDone(){
		lock.lock();
		
		try{
			done = true;
		} finally {
			lock.unlock();
		}
	}

	public void run(){
		System.out.println("\n\nPrime numbers generated Under Thread-"+count++ );
		for (long n = from; n <= to; n++) {
			
			try {
				lock.lock();
				
				if( isPrime(n) ){ 
					 
					this.primes.add(n); 
					
					System.out.print(n+", ");
				} 
				
				if(done==true){
					
					System.out.println("Stopped generating prime numbers.");
					this.primes.clear();
					break; 
				}
				
			} finally {
				lock.unlock();
			}
			
		}
	}
}
