package edu.umb.cs.cs681.hw07;

public class RunnableInterruptiblePrimeGenerator extends PrimeGenerator{
	public RunnableInterruptiblePrimeGenerator(long from, long to) {
		super(from, to);
	}
			
	public void run(){
		// Stop generating prime numbers if Thread.interrupted()==true
		for (long n = from; n <= to; n++) {
			if( isPrime(n) ){ this.primes.add(n); }
			if(Thread.interrupted()==true){
				System.out.println("Stopped generating prime numbers.");
				this.primes.clear();
				break;
			}
		}
	}	
}