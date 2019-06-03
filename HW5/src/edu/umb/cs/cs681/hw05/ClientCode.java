package edu.umb.cs.cs681.hw05;

import java.util.ArrayList;

public class ClientCode {
	
			
	public static void main(String[] args) 
	{
		System.out.println("\nGenerating Prime numbers from 1 to 1000000 \n");
		
		PrimeGenerator generator = new PrimeGenerator(1L, 1000000L);
		
		Thread thread = new Thread(generator);
		try 
		{
		
			thread.start();
			
			thread.join();
			
		} 
		catch (InterruptedException e) 
		{
			System.out.println("Interrupted Exception  : "+e);
		}
		
		ArrayList<Long> primeList = new ArrayList<> ();
		generator.getPrimes().forEach(x -> primeList.add(x));
		System.out.println("\nTotal number of primes in the list is = " + primeList.size());
		
	}

}