package edu.umb.cs.cs681.hw19;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Observable {
	
    LinkedList<Observer> observers;
    private boolean oChange = false;
    private ReentrantLock lock = new ReentrantLock();
    
    public Observable() {
		this.observers = new LinkedList<Observer>();
		
	}
    public void addObserver(Observer o) {
    	lock.lock();
		try {
			if (o == null)
				throw new NullPointerException("can't add null observer");
			observers.add(o);
		}finally {
			lock.unlock();
		}
		
	}
	public void deleteObserver(Observer o) {
		lock.lock();
		try {
			observers.remove(o);
		}finally {
			lock.unlock();
		}
		
	}
	protected void setChanged() {
		lock.lock();
		try {
		oChange = true;
		}finally {
			lock.unlock();
		}
	}
	protected void clearChanged() {
		lock.lock();
		try {
			oChange = false;
		}finally {
			lock.unlock();
		}
		
	}
	public boolean hasChanged() {
		lock.lock();
		try {
			return oChange;
		}finally {
			lock.unlock();
		}
		
	}
	public void notifyObservers(Object arg) {
		lock.lock();
		try {
		if (this.hasChanged() == true) {
			for (Observer obsr: observers) {
				obsr.update(this, arg);
			}
			clearChanged();
		} 
		}finally {
			lock.unlock();
		}
	}
}
