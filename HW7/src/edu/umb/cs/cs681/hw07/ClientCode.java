package edu.umb.cs.cs681.hw07;

public class ClientCode {
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println("For RunnableCancellablePrimeGenerator:");
		RunnableCancellablePrimeGenerator gen1 = new RunnableCancellablePrimeGenerator(1L, 200L);
			Thread thread1 = new Thread(gen1);
			thread1.start();
			
			gen1.setDone();
			thread1.join();
			gen1.getPrimes().forEach((element)->System.out.println(element));
		    System.out.println("Size = " + gen1.getPrimes().size());
			 
		    System.out.println("\nFor RunnableInterruptiblePrimeGenerator:");
		    RunnableInterruptiblePrimeGenerator gen2 = new RunnableInterruptiblePrimeGenerator(1L, 200L);
			Thread thread2 = new Thread(gen2);
			thread2.start();
			
			thread2.interrupt();
			thread2.join();
			gen2.getPrimes().forEach((element)->System.out.println(element));
		    System.out.println("Size = " + gen2.getPrimes().size());
			 
		}
	}