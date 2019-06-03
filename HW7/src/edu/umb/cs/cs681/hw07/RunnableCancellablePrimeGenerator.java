package edu.umb.cs.cs681.hw07;

public class RunnableCancellablePrimeGenerator extends PrimeGenerator {
	private boolean done = false;

	public RunnableCancellablePrimeGenerator(long from, long to) {
		super(from, to);
	}
		
	public void setDone(){
		done = true;
	}

	public void run(){
		// Stop generating prime numbers if done==true
		for (long n = from; n <= to; n++) {
			if( isPrime(n) ){ this.primes.add(n); }
			if(done==true){
				System.out.println("Stopped generating prime numbers.");
				this.primes.clear();
				break;
			}
		}
	}
}