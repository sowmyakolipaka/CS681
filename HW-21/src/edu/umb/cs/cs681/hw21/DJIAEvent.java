package edu.umb.cs.cs681.hw21;

public class DJIAEvent {

	private Float quote;
	
	public DJIAEvent(float quote) {
		this.quote = quote;
	}

	public void setDJIA(float djia) {
		this.quote  = djia;
	}
	public float getDJIA() {
		return quote;
	}
}