package edu.umb.cs.cs681.hw20;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ClientCode {
	public static void main(String[] args) throws InterruptedException {

		AccessCounter accessCounter = AccessCounter.getInstance();
		ConcurrentHashMap<Path,AtomicInteger> hash = new ConcurrentHashMap<Path,AtomicInteger>();
		int NUM_THREADS = 18;
		RequestHandler[] req= new RequestHandler[NUM_THREADS];
		Thread[] threads = new Thread[NUM_THREADS]; 
		
		Path dir = Paths.get("src/edu/umb/cs/cs681/hw20/file");
		for (int j = 0; j < NUM_THREADS; j++) {
            req[j] = new RequestHandler(dir, accessCounter);
			threads[j] = new Thread(req[j]);
          
        }
		/*
		 * Start all the threads
		 */
		  for (int i = 0; i < NUM_THREADS; i++) {
	            threads[i].start(); 
	   
	      }
		  /*
		   * Two step thread termination.
		   */
		  for (int i = 0; i < NUM_THREADS; i++) {
			  	threads[i].interrupt();
			  	req[i].setDone();
			  	
		  }
	   
		 hash = accessCounter.getAccessCounter(); 
		 Thread.sleep(3000);
		 System.out.println("Access Counts");
		 for( Entry<Path, AtomicInteger> entry : hash.entrySet()) {
			 System.out.println("[ " + entry.getKey().getFileName() + ", " + entry.getValue() + " ]");

		 }
		 
	}
}