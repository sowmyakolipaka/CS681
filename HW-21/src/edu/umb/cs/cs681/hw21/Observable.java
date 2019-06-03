package edu.umb.cs.cs681.hw21;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class Observable {
	
	private ConcurrentLinkedQueue<Observer> observers;
    private boolean oChange = false;
    private ReentrantLock lock = new ReentrantLock();
    
    public Observable() {
		this.observers = new ConcurrentLinkedQueue<Observer>();
		
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
		if (this.hasChanged() == false) {
			System.out.println(Thread.currentThread().getId()+ " - It has not changed");
			return;	}
		}finally {
			lock.unlock();
		}
		observers.forEach((Observer k)
				->{k.update(this, arg);} );		
		clearChanged();	
	}
}
