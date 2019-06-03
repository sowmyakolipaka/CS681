package edu.umb.cs.cs681.hw01;

import java.util.HashSet;
import java.util.Set;

public class DJIAQuoteObservable extends Observable{
	
	private float quote;
	private Set<Float> data = new HashSet<Float>();
	
	public Set<Float> getData() {
		return data;
	}

	public void changeQuote(float q)
	{
		notifyObservers(new DJIAEvent(q));		
		this.setQuote(q);
		data.add(q);
		setChanged();
		
	}

	public float getQuote() {
		return quote;
	}

	public void setQuote(float quote) {
		this.quote = quote;
	}
}