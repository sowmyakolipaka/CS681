package edu.umb.cs.cs681.hw12;

public class CancellablePrimeGenerator extends PrimeGenerator 
{
    private boolean done = false;

    CancellablePrimeGenerator(long from, long to) 
    {
        super(from, to);
    }

    public void setDone() 
    {
        done = true;
    }

    public void generatePrimes() 
    {
        for (long num = from; num <= to; num++) 
        {
            if (done)
            {
                this.primeNumbers.clear();
                break;
            }

            if (isPrime(num)) 
            {
                this.primeNumbers.add(num);
            }
        } System.out.println("Stopped Generating Primes ");
    }
}
