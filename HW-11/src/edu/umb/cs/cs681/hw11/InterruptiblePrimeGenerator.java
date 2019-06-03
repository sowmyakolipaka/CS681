package edu.umb.cs.cs681.hw11;

public class InterruptiblePrimeGenerator extends PrimeGenerator 
{
    InterruptiblePrimeGenerator(long from, long to) 
    {
        super(from, to);
    }

    public void generatePrimes() 
    {
        for (long n = from; n <= to; n++) 
        {
            if (Thread.interrupted()) 
            {
                System.out.println("Primes generation stopped");
                this.primeNumbers.clear();
                return;
            }

            if (isPrime(n)) 
            {
                this.primeNumbers.add(n);
            }
        }
    }
}
