package edu.umb.cs.cs681.hw09;

import java.util.concurrent.locks.ReentrantLock;

public class File {
	
	private boolean changed = false;
	ReentrantLock lock = new ReentrantLock();
	private String name;
	
	public File (String name){
		this.lock = new ReentrantLock();
		this.name = name;
	}
	
	public File() {};
	
	@Override
	public String toString(){
		return name;
		
	}
	
	public void change() {
		try {
			lock.lock();
			System.out.println("\nFile Content Changed");
			changed = true;
		} finally {
			lock.unlock();
		}
	}
	
	public void save() {
		try {
			lock.lock();
			if(!changed) {
				return;
			}
			if(changed = true) {
				System.out.println("\n" +Thread.currentThread().getName() + ": ");
				System.out.print("**Saved**");
				changed = false;
			}
		} finally {
			lock.unlock();
		}
	}
}

