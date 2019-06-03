package edu.umb.cs.cs681.hw08;

import java.util.ArrayList;

public class PrimeGenerator implements Runnable{
	protected long from, to;
	protected ArrayList<Long> primes;
	
	public ArrayList<Long> getPrimes(){
		return primes; };
	
	protected boolean isEven(long n)
	{
		if(n%2 == 0)
			 return true; 
		else 
			 return false; 
	}
	
	public  PrimeGenerator(long from, long to){
		if(from >= 1 && to >= from){
			this.primes = new ArrayList<Long>();
			this.from = from;
			this.to = to;
		}else{
			throw new RuntimeException("Invalid input given:\n from = " + from + "\n to=" + to);
		}
	}
	

	protected boolean isPrime(long num)
	{

		if(num == 1) 
			return false;
		if(num > 2 && isEven(num))
			return false; 
		long x;
        for (x = (long) Math.sqrt(num); num % x != 0 && x >= 1; x--){}
        if (x == 1)
        	return true; 
        else 
        	return false;
	}

	public void run(){
		for (long n = from; n <= to; n++) {
			if( isPrime(n) )
				((ArrayList<Long>) primes).add(n); 
        }
	}
}