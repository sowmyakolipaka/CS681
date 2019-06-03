package edu.umb.cs.cs681.hw01;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ClientCode {

	public static void main(String[] args) {
		
		Observer PieChartObserver = (Observable obs, Object arg) -> {
			if (arg instanceof DJIAEvent) {
				DJIAEvent DJIAEvent = (DJIAEvent) arg;
				System.out.println("Piechart Observer of DJIA Event \n " + DJIAEvent.getDJIA() + "\n");
			} else if (arg instanceof StockEvent) {
				StockEvent stockevent = (StockEvent) arg;
				System.out.println("Piechart Observer of Stock Event.\n" + stockevent.getTicker() + "  " + stockevent.getQuote() + " " + " \n");
			}
		};
		Observer TableObserver = (Observable obs, Object arg) -> {
			if (arg instanceof DJIAEvent) {
				DJIAEvent DJIAEvent = (DJIAEvent) arg;
				System.out.println("TableObserver of DJIA Event\n" + DJIAEvent.getDJIA() + "\n");			
			} else if (arg instanceof StockEvent) {
				StockEvent stockevent = (StockEvent) arg;
				System.out.println("TableObserver of StockEvent class.\n" + stockevent.getTicker() + "  " + stockevent.getQuote() +"  " + " \n" );
			}
		};
		Observer ThreeDObserver = (Observable obs, Object arg) -> {
			if (arg instanceof DJIAEvent) {
				DJIAEvent DJIAEvent = (DJIAEvent) arg;
				System.out.println("ThreeDObserver  of DJIA Event\n" + DJIAEvent.getDJIA() + "\n");
			} else if (arg instanceof StockEvent) {
				StockEvent stockevent = (StockEvent) arg;
				System.out.println("ThreeDObserver of StockEvent class." + stockevent.getTicker() + "  " + stockevent.getQuote() + " "+"\n");
			}
		};
		
		
		StockQuoteObservable observable = new StockQuoteObservable();
		HashMap<String, Float> map = new HashMap<>();
		
		
		observable.addObserver(PieChartObserver);
		observable.addObserver(TableObserver);
		observable.addObserver(ThreeDObserver);
		
		observable.changeQuote(155, "Starbucks");
		observable.changeQuote(37, "Apple");
		observable.changeQuote(98, "Coke");

		map.put("Starbucks", (float) 80);
		map.put("Apple", (float) 92);
		map.put("Coke", (float) 90);

		
		DJIAQuoteObservable DJIAobservable = new DJIAQuoteObservable();
		Set<Float> data = new HashSet<Float>();

		
		DJIAobservable.addObserver(PieChartObserver);
		DJIAobservable.addObserver(TableObserver);
		DJIAobservable.addObserver(ThreeDObserver);
		
		
		DJIAobservable.changeQuote(70);
		DJIAobservable.changeQuote(80);
		DJIAobservable.changeQuote(90);
		
		data.add((float) 70);
		data.add((float) 80);
		data.add((float) 90);

	}
}

