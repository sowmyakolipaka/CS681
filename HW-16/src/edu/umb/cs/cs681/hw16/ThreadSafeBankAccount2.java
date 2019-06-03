package edu.umb.cs.cs681.hw16;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class ThreadSafeBankAccount2{
	private double balance = 0;
	private ReentrantLock lock;
	private Condition sufficientFundsCondition, belowUpperLimitFundsCondition;
	private ThreadSafeBankAccount2 account;
	public ThreadSafeBankAccount2(){
		lock = new ReentrantLock();
		new ReentrantLock();
		new ReentrantLock();
		sufficientFundsCondition = lock.newCondition();
		belowUpperLimitFundsCondition = lock.newCondition();
		account =  this;
	}
	
	public void deposit(double amount){
		lock.lock();
		try{
			System.out.println("Lock obtained");
			System.out.println(Thread.currentThread().getId() + 
					" (d): current balance: " + balance);
			while(balance >= 300){
				System.out.println(Thread.currentThread().getId() + 
						" (d): await(): Balance exceeds the upper limit.");
				belowUpperLimitFundsCondition.await();
			}
			balance += amount;
			System.out.println(Thread.currentThread().getId() + 
					" (d): new balance: " + balance);
			sufficientFundsCondition.signalAll();
		}
		catch (InterruptedException exception){
			exception.printStackTrace();
		}
		finally{
			lock.unlock();
			System.out.println("Lock released");
		}
	}
	
	public void withdraw(double amount){
		lock.lock();
		try{
			System.out.println("Lock obtained");
			System.out.println(Thread.currentThread().getId() + 
					" (w): current balance: " + balance);
			while(balance <= 0){
				System.out.println(Thread.currentThread().getId() + 
						" (w): await(): Insufficient funds");
				sufficientFundsCondition.await();
			}
			balance -= amount;
			System.out.println(Thread.currentThread().getId() + 
					" (w): new balance: " + balance);
			belowUpperLimitFundsCondition.signalAll();
		}
		catch (InterruptedException exception){
			exception.printStackTrace();
		}
		finally{
			lock.unlock();
			System.out.println("Lock released");
		}
	}
	
	public static void main(String[] args)
	{
		ThreadSafeBankAccount2 bankAccount = new ThreadSafeBankAccount2();
		
	try {	
		Thread t1 =	new Thread( bankAccount.new DepositRunnable() );
		t1.start();
		Thread t2 = new Thread( bankAccount.new WithdrawRunnable() );
		t1.start();	
		
		t1.interrupt();
		t2.interrupt();
		
		} catch (Exception e) {
			System.out.println(e);
		}
}

	private class DepositRunnable implements Runnable{
		public void run(){
			try{
				lock.lock();
				for(int i = 0; i < 10; i++){
					account.deposit(100);
					Thread.sleep(2);
					//break;
				}
			}
			catch(InterruptedException exception)
			{
				System.out.println(Thread.currentThread().getName() + " Thread interrupted");
			} finally {
				lock.unlock();
			}
			
		}
	}
	
	private class WithdrawRunnable implements Runnable{
		public void run(){
			try{
				lock.lock();
				for(int i = 0; i < 10; i++){
					account.withdraw(100);
					Thread.sleep(2);
				}
			}
			catch(InterruptedException exception)
			{
				Thread.currentThread().interrupt();
		           // return;
			}
			finally {
					lock.unlock();
				}
			}
		}
	}