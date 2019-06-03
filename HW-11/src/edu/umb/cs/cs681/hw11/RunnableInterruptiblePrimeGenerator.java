package edu.umb.cs.cs681.hw11;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableInterruptiblePrimeGenerator extends InterruptiblePrimeGenerator implements Runnable 
{
    private final ReentrantLock reentrantLock = new ReentrantLock();

    RunnableInterruptiblePrimeGenerator(long from, long to) 
    {
        super(from, to);
    }

    public ReentrantLock getLock() 
    {
        return reentrantLock;
    }

    public void run() 
    {	
        generatePrimes();
    }

    public void generatePrimes() 
    {
        System.out.println("Starts generating primes");

        for (long num = from; num <= to; num++) 
        {	
            reentrantLock.lock();

            if (Thread.interrupted()) 
            {
                System.out.println("Stopped Generating Primes.");
                this.primeNumbers.clear();
                break;
            }

            reentrantLock.unlock();

            if (isPrime(num)) 
            {
                this.primeNumbers.add(num);
            }
        }
    }


}