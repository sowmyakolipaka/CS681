package edu.umb.cs.cs681.hw15;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import java.nio.file.Path;

public class AccessCounter {
	private HashMap<Path, Integer> counter = new HashMap<Path, Integer>();
	private AccessCounter() {};
	private static AccessCounter instance = null;
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	private static ReentrantLock rlock = new ReentrantLock();
	
	public static AccessCounter getInstance() {
		rlock.lock();
		try {
			if (instance == null) {
				instance = new AccessCounter();
			}
		} finally {
			rlock.unlock();
		}
		return instance;
	}
	
	public void increment(Path path){
		lock.writeLock().lock();
		try {
			
			if (counter.containsKey(path)){
				counter.put(path, counter.get(path) + 1);	
			} else {
				counter.put(path, 1);
			}	
			
			
		} finally {
			lock.writeLock().unlock();
		}
		
	}

	public int getCount(Path path){
		lock.readLock().lock();
		try {
			int count = -1;
			
			if (counter.containsKey(path)){
				count = counter.get(path);
			
			} else {
				count = 0;
			}
			
			System.out.println(Thread.currentThread().getName() + 
					": "+ path+", "+ count);

			
			return count;
			
		} finally {
			lock.readLock().unlock();
		}
	}
	public HashMap<Path, Integer> getAccessCounter() {
		
		return counter;
		
	}
	

}