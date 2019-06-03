package edu.umb.cs.cs681.hw19;
import java.util.concurrent.locks.ReentrantLock;

public class StockQuoteObservable extends Observable {
	private ReentrantLock lock = new ReentrantLock();
	private StockEvent stockEvent;

	public void changeQuote(float quote, String ticker) {
		
		lock.lock();
		try {
			this.stockEvent = new StockEvent(ticker, quote);
		this.setChanged();
		this.notifyObservers(stockEvent);
		}finally {
			lock.unlock();
		}
	}

	public void changeQuote(StockEvent stockEvent) {
		lock.lock();
		try {
		this.stockEvent = stockEvent;
		this.setChanged();
		this.notifyObservers(stockEvent);
	} finally {
		lock.unlock();
	}
}
}