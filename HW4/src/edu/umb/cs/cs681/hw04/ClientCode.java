package edu.umb.cs.cs681.hw04;

import java.util.ArrayList;

public class ClientCode {
	
			
			public static void main(String[] args) 
			{
				
				PrimeGenerator primeGen1 = new PrimeGenerator(1L, 1000000L);
				PrimeGenerator primeGen2 = new PrimeGenerator(1000001L, 2000000L);
				Thread t1 = new Thread(primeGen1);
				Thread t2 = new Thread (primeGen2);
				
				try {
					System.out.println("\nGenerating Prime numbers from 1 to 1000000 with a Stream. ");
					t1.start(); 
					System.out.println("\nGenerating Primes from 1000001 to 2000000 with a Stream.");
					t2.start();
				
				    t1.join();
				    t2.join();

			} catch (InterruptedException e) {
				System.out.println("Interrupted Exception: " + e);
			}

			ArrayList<Long> arrlist = new ArrayList<>();
			primeGen1.getPrimes().forEach(x -> arrlist.add(x));
			primeGen2.getPrimes().forEach(x -> arrlist.add(x));
				
				System.out.println("\nThe STREAM Prime numbers have been added to a big List. \nTotal number of primes is =  " + arrlist.size());
				// Print numbers
				System.out.println("\nThe first prime number generated ");
				
				arrlist.stream()
		        .filter(x -> x.equals(2L))
		        .forEach(System.out::println);
				
				System.out.println("\nThe Last prime number generated  ");
				arrlist.stream()
		        .filter(x -> x.equals(1999993L))
		        .forEach(System.out::println);
}
}