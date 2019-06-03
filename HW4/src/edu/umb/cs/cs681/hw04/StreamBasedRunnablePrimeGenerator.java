package edu.umb.cs.cs681.hw04;

import java.util.ArrayList;
import java.util.stream.LongStream;

public class StreamBasedRunnablePrimeGenerator extends PrimeGenerator {

	public StreamBasedRunnablePrimeGenerator(long from, long to) {
		super(from, to);
	}

	@SuppressWarnings("unchecked")
	public void run()
	{
		this.primes = (ArrayList<Long>) LongStream.rangeClosed(this.from, this.to)
	            .filter(num -> isPrime(num));
	}
} 