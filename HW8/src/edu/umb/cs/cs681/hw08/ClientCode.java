package edu.umb.cs.cs681.hw08;

import java.util.ArrayList;

public class ClientCode {
			
	public static void main(String[] args) throws Exception {

		ArrayList<Thread> threads = new ArrayList<Thread>();
				
				RunnableCancellablePrimeGenerator gen = new RunnableCancellablePrimeGenerator(1L, 25L);
				
			    final int nThread = 4;
			   
			    
			    for (int i = 0; i < nThread; i++) {
			        Thread t = new Thread(gen);
			       
			        threads.add(t);
			        t.start();
			       
			      }
			    
			      
			    
			      threads.forEach(
			    		  (Thread thread)->{
			    			  try {
			    			  		thread.join();
			    			  } catch (Exception e) {
			    				  	e.printStackTrace();}
			    		  }
			      );
			      
			      System.out.println("\n\nTotal of " + gen.getPrimes().size() + " prime numbers were found from (1 to 25), by running " + 
			    	       + nThread + " threads");
			      
			    
			   
			}

		


	}