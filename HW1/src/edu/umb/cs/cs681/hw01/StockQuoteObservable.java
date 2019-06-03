package edu.umb.cs.cs681.hw01;

public class StockQuoteObservable extends Observable {

	private StockEvent stockEvent;

	public void changeQuote(float quote, String ticker) {
		this.stockEvent = new StockEvent(ticker, quote);
		this.setChanged();
		this.notifyObservers(stockEvent);
	}

	public void changeQuote(StockEvent stockEvent) {
		this.stockEvent = stockEvent;
		this.setChanged();
		this.notifyObservers(stockEvent);
	}
}