package edu.umb.cs.cs681.hw17;

import java.util.ArrayList;

public class ClientCode {

	public static void main(String[] args) {
		AdmissionControl adControl = new AdmissionControl();		
		int tcount = 4;
		
		ArrayList<Thread> threads = new ArrayList<Thread>(tcount);
		 for (int i = 0; i <3 ; i++) 
		 {
			 Thread t = new Thread(adControl.new EntranceHandler());
			 threads.add(t);
			 t.start();
			 
		 }
		 
			 Thread tm = new Thread(adControl.new MonitorHandler());
			 threads.add(tm);
			 tm.start();
			 try {
						Thread.sleep(10);
					} catch (InterruptedException e) {			
						e.printStackTrace();
					}
			 
		
		 for (int i = 0; i <1 ; i++) 
		 {
			 Thread texit = new Thread(adControl.new ExitHandler());
			 threads.add(texit);
			 texit.start();
			 
		 }
				 try {
						Thread.sleep(10);
					} catch (InterruptedException e) {			
						e.printStackTrace();
					}	
		 for (int i = 0; i < tcount; i++) 
		 {
			
			 threads.get(i).interrupt();
		 }
		 				adControl.setDone();
		 				
		 for (int i = 0; i < tcount; i++) 
		 {
			 try {								
					 threads.get(i).join();
				 }
			catch (Exception e) {
			}
		}
		
		 adControl.resetDone();
		Thread tt = new Thread(adControl.new MonitorHandler());		
		 tt.start();
		 try {
			Thread.sleep(1);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}		 
		// adControl.setDone();
				
	}

}
