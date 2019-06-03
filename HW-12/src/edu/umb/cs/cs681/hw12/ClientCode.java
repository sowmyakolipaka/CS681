package edu.umb.cs.cs681.hw12;

import java.util.Arrays;
import java.util.List;

public class ClientCode 
{
    public static void main(String[] args) 
    {
        RunnableCancellableInterruptiblePrimeGenerator gen1=new RunnableCancellableInterruptiblePrimeGenerator(1,570);
        RunnableCancellableInterruptiblePrimeGenerator gen2=new RunnableCancellableInterruptiblePrimeGenerator(570,1440);

        List<Thread> threadList=Arrays.asList(new Thread(gen1),new Thread(gen2));

        for (Thread th: threadList) 
        {
            th.start();
        }

        try 
        {
            Thread.sleep(3000);
        } 
        catch (InterruptedException e) 
        {
            System.out.println("Thread interrupted "+e.getMessage());
        }

        gen1.setDone();
        gen2.getLock().lock();
        threadList.get(1).interrupt();
        gen2.getLock().unlock();

     
        for (Thread th: threadList) 
        {
            try 
            {
                th.join();
            } 
            catch (InterruptedException e) 
            {
                System.out.println("Thread interrupted in the process of"+e.getMessage());
            }
        }
    }
}
