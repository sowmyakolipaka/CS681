package edu.umb.cs.cs681.hw21;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

public class DJIAQuoteObservable extends Observable{
	
	private float quote;
	private Set<Float> data = new HashSet<Float>();
	private ReentrantLock lock = new ReentrantLock();
	
	public Set<Float> getData() {

		lock.lock();
		try {
			return data;
		}finally {
			lock.unlock();
		}
	}

	public void changeQuote(float q)
	{  
		lock.lock();
		try {
		notifyObservers(new DJIAEvent(q));		
		this.setQuote(q);
		data.add(q);
		setChanged();
		}finally {
			lock.unlock();
		}	
	}

	public float getQuote() {
		return quote;
	}

	public void setQuote(float quote) {
		this.quote = quote;
	}
}