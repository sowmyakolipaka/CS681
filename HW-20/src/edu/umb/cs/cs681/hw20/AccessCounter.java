package edu.umb.cs.cs681.hw20;


import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

import java.nio.file.Path;

public class AccessCounter {
	ConcurrentHashMap<Path, AtomicInteger> counter = new ConcurrentHashMap<Path, AtomicInteger>();
	private AccessCounter() {};
	private static AccessCounter instance = null;
	//private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
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
		rlock.lock();
		try {
			
			counter.putIfAbsent(path, new AtomicInteger(0));
			counter.get(path).incrementAndGet();		
			
			
			
		} finally {
			rlock.unlock();
		}
		
	}

	AtomicInteger getCount(Path path) {		
		return counter.get(path);
}
	
public ConcurrentHashMap<Path, AtomicInteger> getAccessCounter() {
		
		return counter;
		
	}
}