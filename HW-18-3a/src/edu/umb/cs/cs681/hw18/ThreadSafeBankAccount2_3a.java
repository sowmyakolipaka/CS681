package edu.umb.cs.cs681.hw18;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class ThreadSafeBankAccount2_3a {
	private double balance = 0;
	private ReentrantLock lock;
	private Condition sufficientFundsCondition, belowUpperLimitFundsCondition;
	private ThreadSafeBankAccount2_3a account;
	private double accountNumber;
	private volatile boolean done = false;

	
	//SOlution for 3a
	public ThreadSafeBankAccount2_3a(double accountNumber){
		lock = new ReentrantLock();
		sufficientFundsCondition = lock.newCondition();
		belowUpperLimitFundsCondition = lock.newCondition();
		account =  this;
		this.accountNumber = accountNumber;
	}
	
	public  double getAcctNum() {
		return accountNumber;
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
	
	
	public ReentrantLock getLock() {
		return lock;
	}
	
	public static void main(String[] args){
		System.out.println("-------------------SOLUTION 3A-----------------------");
		ThreadSafeBankAccount2_3a AccountA = new ThreadSafeBankAccount2_3a(1);
		ThreadSafeBankAccount2_3a AccountB = new ThreadSafeBankAccount2_3a(2);
		
		DepositRunnable DA = AccountA.new DepositRunnable();
		WithdrawRunnable WA = AccountA.new WithdrawRunnable();
		DepositRunnable DB = AccountB.new DepositRunnable();
		WithdrawRunnable WB = AccountB.new WithdrawRunnable();
		TransferRunnable TA = AccountA.new TransferRunnable(AccountB);
		TransferRunnable TB = AccountB.new TransferRunnable(AccountA);
	
		
		Thread threadDA = new Thread(DA);
		Thread threadWA = new Thread(WA);
		Thread threadDB = new Thread(DB);
		Thread threadWB = new Thread(WB);
		
		Thread threadTB = new Thread(TB);
		Thread threadTA = new Thread(TA);
		threadDA.start();
		threadWA.start();
		threadDB.start();
		threadWB.start();
		threadTB.start();
		threadTA.start();
		
		
		
		
		
	}



	private class DepositRunnable implements Runnable{
		public void run(){
			try{
				for(int i = 0; i < 10; i++){
					account.deposit(120);
					Thread.sleep(2);
				}
			}
			catch(InterruptedException exception){}
		}
	}
	
	private class WithdrawRunnable implements Runnable{
		public void run(){
			try{
				for(int i = 0; i < 10; i++){
					account.withdraw(100);
					Thread.sleep(2);
				}
			}
			catch(InterruptedException exception){}
		}
	}
	public void transfer(ThreadSafeBankAccount2_3a source, ThreadSafeBankAccount2_3a destination, double amount) {	
		int sourceID = System.identityHashCode(source);
		int destID = System.identityHashCode(destination);
		if( sourceID < destID ){ 
			source.getLock().lock(); 
			destination.getLock().lock();
		
							if( source.getBalance() < amount) {
								System.out.println("Not Enough funds");
							}
							else {
								source.withdraw(amount);
								destination.deposit(amount);
								System.out.println("Funds Transfered");
							}
			destination.getLock().unlock(); 
			source.getLock().unlock();
		}
		else if (sourceID > destID ){ 
					destination.getLock().lock(); 
					source.getLock().lock();
							if( destination.getBalance() < amount) {
								System.out.println("Not Enough funds");
							}
							else {
								destination.withdraw(amount);
								source.deposit(amount);
								System.out.println("Funds Transfered");
							}
					source.getLock().unlock(); 
					destination.getLock().unlock(); 
				}

		
	}
	
	
	public class TransferRunnable implements Runnable {
		private ThreadSafeBankAccount2_3a destination;

		TransferRunnable(ThreadSafeBankAccount2_3a destination) {
			this.destination = destination;
		}
		
		
		public void setDone() {
			boolean done = true;
		}
		@Override
		public void run() {
			while (!done) { 
				System.out.println(
						Thread.currentThread().getId() + " t - ");
				account.transfer(account, destination, 100.00);
																

			}
			System.out.println(Thread.currentThread().getId() + " done = true");

		}
	}

	public double getBalance() {
	return balance;
	}
	
}