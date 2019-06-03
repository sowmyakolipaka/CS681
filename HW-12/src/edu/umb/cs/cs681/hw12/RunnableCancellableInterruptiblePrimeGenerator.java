package edu.umb.cs.cs681.hw12;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellableInterruptiblePrimeGenerator extends RunnableCancellablePrimeGenerator implements Runnable 
{
    private boolean done = false;
        

    private final ReentrantLock reentrantLock = new ReentrantLock();

    RunnableCancellableInterruptiblePrimeGenerator(long from, long to) 
    {
        super(from, to);
    }
    

    public void generatePrimes() 
    {
        System.out.println("Start generating primes");
        for (long n = from; n <= to; n++) 
        {
            reentrantLock.lock();
            try {
                if (Thread.interrupted() || done) 
                {
                    System.out.println("Generating primes stopped");
                    this.primeNumbers.clear();
                    break;
                }
            } 
            finally 
            {
                reentrantLock.unlock();
            }

            if (isPrime(n)) 
            {
                this.primeNumbers.add(n);
            }
        } System.out.println("Stopped Generating Primes ");
    }
	
    public ReentrantLock getLock() 
    {
        return reentrantLock;
    }

    @Override
    public void run() 
    {
        generatePrimes();
    } 
}
