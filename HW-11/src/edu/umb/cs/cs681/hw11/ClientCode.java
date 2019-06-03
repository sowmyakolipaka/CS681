package edu.umb.cs.cs681.hw11;

import java.util.Arrays;
import java.util.List;

public class ClientCode {
	 public static void main(String[] args) throws InterruptedException 
	    {
	        RunnableInterruptiblePrimeGenerator gen1 = new RunnableInterruptiblePrimeGenerator(1, 1000);
	        RunnableInterruptiblePrimeGenerator gen2 = new RunnableInterruptiblePrimeGenerator(1001, 2000);
	        List<Thread> tList= Arrays.asList(new Thread(gen1),new Thread(gen2));

	        for (Thread t: tList) 
	        {
	            t.start();
	        }

	        gen1.getLock().lock();
	        tList.get(0).interrupt();
	        gen1.getLock().unlock();

	        gen2.getLock().lock();
	        tList.get(1).interrupt();
	        gen2.getLock().unlock();

	        for (Thread th: tList) 
	        {
	            th.join();
	        }
	    }
	}