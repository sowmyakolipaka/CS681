package edu.umb.cs.cs681.hw13;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class RequestHandler implements Runnable{
	
	private Path path;
	private AccessCounter accessCounter;
	private ArrayList<String> files;
	private boolean done = false;
	ReentrantLock lock = null;
	
	public RequestHandler(Path path, AccessCounter accessCounter){
		this.accessCounter = accessCounter;
		this.path = path;
		this.files = new ArrayList <> ();
		lock = new ReentrantLock();
	}
	
	public void setDone(){
		lock.lock();
		
		try{
			done = true;
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void run() {
		
		String path = this.path.toAbsolutePath().toString();
		
		File dir = new File (path);
				
		if (dir.listFiles() == null){
			
			System.out.println("No files found.");

		} else if (dir.listFiles() != null) {
		
			for (File file : dir.listFiles()){
			
				try {
					files.add(file.getName());
				} catch(Exception e) { 
				}
			}
			
			
			lock.lock();
			try{

				if(done==true){				
					System.out.println(Thread.currentThread().getName()+ "  It is terminated");
					this.files.clear();
					
				}
			}finally{
				lock.unlock();
			}
		
				
			if (done==false){
								
				int rand = new Random().nextInt(files.size());
				
				String randFile = files.get(rand);
				try {
					
					Path path2 =  Objects.requireNonNull(Paths.get(randFile));
				
					accessCounter.increment(path2);
					accessCounter.getCount(path2); 
					
				} catch (Exception e){
					System.out.println(e);
				}
				
				}
		}
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			}	
		
	}

}