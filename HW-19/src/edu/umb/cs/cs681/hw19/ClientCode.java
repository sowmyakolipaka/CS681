package edu.umb.cs.cs681.hw19;

import java.util.ArrayList;


public class ClientCode {

	public static void main(String[] args) {
		Observer PieChartObserver = (Observable obs, Object arg) -> {
			if (arg instanceof DJIAEvent) {
				DJIAEvent dJIAEvent = (DJIAEvent) arg;
				System.out.println(Thread.currentThread().getId()+ "   PieChartObserver_update  DJIAEvent.\n");
				System.out.println(Thread.currentThread().getId()+ "   DJIA :" + dJIAEvent.getDJIA() + " \n");
			} else if (arg instanceof StockEvent) {
				StockEvent stockevent = (StockEvent) arg;
				System.out.println(Thread.currentThread().getId()+ "  PieChartObserver_update  StockEvent "+stockevent.getTicker() + "  " + stockevent.getQuote());
			}
		};
		Observer TableObserver = (Observable obs, Object arg) -> {
			if (arg instanceof DJIAEvent) {
				DJIAEvent dJIAEvent = (DJIAEvent) arg;
				System.out.println(Thread.currentThread().getId()+ "   TableObserver_update  DJIAEvent.\n");
				System.out.println(Thread.currentThread().getId()+ "   DJIA :" + dJIAEvent.getDJIA() + " \n");
			} else if (arg instanceof StockEvent) {
				StockEvent stockevent = (StockEvent) arg;
				
				System.out.println(Thread.currentThread().getId()+ "  StockEvent "+stockevent.getTicker() + "  " + stockevent.getQuote());
			}
		};
		Observer ThreeDObserver = (Observable obs, Object arg) -> {
			if (arg instanceof DJIAEvent) {
				DJIAEvent dJIAEvent = (DJIAEvent) arg;
				System.out.println(Thread.currentThread().getId()+ "    DJIAEvent.\n");
				System.out.println(Thread.currentThread().getId()+ "   DJIA :" + dJIAEvent.getDJIA() + " \n");
			} else if (arg instanceof StockEvent) {
				StockEvent stockevent = (StockEvent) arg;
				System.out.println(+Thread.currentThread().getId()+ "  StockEvent "+stockevent.getTicker() + "  " + stockevent.getQuote());
				
			}
		};

		StockQuoteObservable stockObservable = new StockQuoteObservable();
		stockObservable.addObserver(PieChartObserver);
		
		ArrayList<Thread> threads = new ArrayList<Thread>();
		for (int i = 0; i < 15; i++) {
			Thread t = new Thread(() -> {
				stockObservable.changeQuote(155 + Thread.currentThread().getId(), "Starbucks");
				stockObservable.changeQuote(98 + Thread.currentThread().getId(), "Coke");
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			threads.add(t);
			t.start();
		}
		try {
			Thread.sleep(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("TableObserver");
		stockObservable.addObserver(TableObserver);
		try {
			Thread.sleep(1);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("ThreeDObserver");
		stockObservable.addObserver(ThreeDObserver);
		try {
			Thread.sleep(1);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("PiechartObserver");
		stockObservable.deleteObserver(PieChartObserver);
		
		for (int i = 0; i < 10; i++) 
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