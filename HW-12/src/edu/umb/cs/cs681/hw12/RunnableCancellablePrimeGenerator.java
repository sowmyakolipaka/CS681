package edu.umb.cs.cs681.hw12;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeGenerator extends CancellablePrimeGenerator implements Runnable 
{
    private boolean done = false;
    private final ReentrantLock reentrantLock = new ReentrantLock();

    RunnableCancellablePrimeGenerator(long from, long to) 
    {
        super(from, to);
    }

    @Override
    public void run() 
    {
        generatePrimes();
    }

    public void setDone() 
    {
        reentrantLock.lock();
        try 
        {
            done = true;
        } 
        finally 
        {
            reentrantLock.unlock();
        }
    }

    public void generatePrimes() 
    {
        System.out.println("Start Generating primes");
        for (long n = from; n <= to; n++) 
        {
            reentrantLock.lock();
            try 
            {
                if (done) 
                {
                   
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
}
