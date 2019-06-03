package edu.umb.cs.cs681.hw14;

import java.util.ArrayList;

public class ClientCode {
	public static void main(String[] args) {
		int tcount = 3;		
		Address add = new Address("address 33 middlesex","Waltham","MA",02452);
		Customer r1 = new Customer(add);
		ArrayList<Thread> threads = new ArrayList<Thread>();
		
		 for (int i = 0; i < tcount; i++) 
		 {
			 Thread t1 = new Thread(()->{
				 Address newAddress = new Address(Thread.currentThread().getName(),"Vegas","NV",05742);				
				 r1.getAddress().toString();
				 r1.setAddress(newAddress);
				 r1.setAddress(r1.getAddress().change("BellagioRD", "Lowell",Thread.currentThread().getName(),06742));							 
			 });
			 threads.add(t1);
			 t1.start();		 
		 }					
		 for (int i = 0; i < tcount; i++) 
		 {
			 try {								
					 threads.get(i).join();
				 }
			catch (Exception e) {
				System.out.println(e);
			}
		}	
	}

}
