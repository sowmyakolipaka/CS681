package edu.umb.cs.cs681.hw01;

import java.util.LinkedList;

public abstract class Observable {
	
    LinkedList<Observer> observers;
    private boolean oChange = false;
    
    public Observable() {
		this.observers = new LinkedList<Observer>();
		
	}
    public void addObserver(Observer o) {
    	if (o == null) { throw new NullPointerException("null observers can't be added");}
    	else observers.add(o);
    }
	public void deleteObserver(Observer o) {
		observers.remove(o);
	}
	protected void setChanged() {
		oChange = true;
	}
	protected void clearChanged() {
		oChange = false;
	}
	public boolean hasChanged() {
		return oChange;
	}
	public void notifyObservers(Object arg) {
		if (this.hasChanged() == true) {
			for (Observer obsr: observers) {
				obsr.update(this, arg);
			}
			clearChanged();
		}
	}
}
