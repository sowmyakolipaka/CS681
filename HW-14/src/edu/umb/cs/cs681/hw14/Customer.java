package edu.umb.cs.cs681.hw14;
import java.util.concurrent.locks.ReentrantLock;

public class Customer {
	private Address address; 
	ReentrantLock lock = new ReentrantLock();
	
	
	
	public Customer(Address address){ 
		this.address = address; 
	}

	public Address getAddress() {
		lock.lock();
		try {
			System.out.println("new changed "+address.toString()+"- "+Thread.currentThread().getName());
			return address;
		}finally {
			lock.unlock();
		}
	}


	public void setAddress(Address address) {
		lock.lock();
		try {			
			this.address = address;
			System.out.println("new "+address.toString()+" - "+Thread.currentThread().getName());
		}finally {
			lock.unlock();
		}
	}
	
}