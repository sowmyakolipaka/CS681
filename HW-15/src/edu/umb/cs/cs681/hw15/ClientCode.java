package edu.umb.cs.cs681.hw15;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ClientCode {
	public static void main(String[] args) throws InterruptedException {

		AccessCounter accessCounter = AccessCounter.getInstance();
		HashMap<Path,Integer> hash = new HashMap<Path, Integer>();
		int NUM_THREADS = 18;
		RequestHandler[] req= new RequestHandler[NUM_THREADS];
		Thread[] threads = new Thread[NUM_THREADS]; 
		
		Path dir = Paths.get("src/edu/umb/cs/cs681/hw15/file");
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
		 for( Map.Entry<Path, Integer> entry : hash.entrySet()) {
			 System.out.println("[ " + entry.getKey().getFileName() + ", " + entry.getValue() + " ]");

		 }
		 
	}
}