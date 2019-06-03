package edu.umb.cs.cs681.hw21;

public class StockEvent {
	
	private String ticker;
	private float quote;
	
	public void setQuote(float quote) {
		this.quote = quote;
	}
	public float getQuote() {
		return quote;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}	
	public String getTicker() {
		return ticker;
	}
	public StockEvent(String ticker, float quote) {
		this.ticker = ticker;
		this.quote = quote;
	}
	
}
