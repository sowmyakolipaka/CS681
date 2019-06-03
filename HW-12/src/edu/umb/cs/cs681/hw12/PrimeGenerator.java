package edu.umb.cs.cs681.hw12;

import java.util.ArrayList;
import java.util.List;


public class PrimeGenerator 
{
    long from, to;
    List<Long> primeNumbers = new ArrayList<>();

    boolean isPrime(long num) 
    {
        if (num == 0 || num == 1) return false;
        for (int i = 2; i <= num / 2; ++i) 
        {
            if (num % i == 0)
                return false;
        }
        return true;
    }
    
    PrimeGenerator(long from, long to) 
    {
        if (from >= 1 && to >= from) 
        {
            this.from = from;
            this.to = to;
        } 
        else 
        {
            throw new RuntimeException("Error at " + from + " to = " + to);
        }
    }


    public void generatePrimes() 
    {
        for (long n = from; n <= to; n++) 
        {
            if (isPrime(n)) 
            {
                primeNumbers.add(n);
            }
        }
    }
}
