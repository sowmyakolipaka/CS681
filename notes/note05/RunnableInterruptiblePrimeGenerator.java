package edu.umb.cs.threads.primes;

public class RunnableInterruptiblePrimeGenerator
	extends InterruptiblePrimeGenerator 
	implements Runnable {

	public RunnableInterruptiblePrimeGenerator(long from, long to) {
		super(from, to);
	}
	
	public void run(){
		generatePrimes();
	}

	public static void main(String[] args) {
		RunnableInterruptiblePrimeGenerator gen = new RunnableInterruptiblePrimeGenerator(1,100);
		Thread thread = new Thread(gen);
		thread.start();
		thread.interrupt();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		gen.getPrimes().forEach( (Long prime)-> System.out.print(prime + ", ") );
		System.out.println("\n" + gen.getPrimes().size() + " prime numbers are found.");
	}
}
