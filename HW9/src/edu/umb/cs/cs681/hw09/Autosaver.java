package edu.umb.cs.cs681.hw09;

import java.util.concurrent.locks.ReentrantLock;

public class Autosaver implements Runnable {
	private File aFile = new File();
	ReentrantLock lock = new ReentrantLock();
	ReentrantLock lock2 = new ReentrantLock();
	private boolean done = false;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			lock.lock();
			try {
				if (done == true) {
					System.out.println("\nAUTO-SAVER THREAD COMPLETE!");
					break;
				}
				aFile.save();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} finally {
				lock.unlock();
			}
		}
	}

	public void setDone() {
		try {
			lock2.lock();
			done = true;
		} finally {
			lock2.unlock();
		}
	}
}