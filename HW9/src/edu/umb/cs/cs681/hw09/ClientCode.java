package edu.umb.cs.cs681.hw09;

public class ClientCode {

	public static void main(String[] args) throws InterruptedException {
		Editor editor1 = new Editor();
		Autosaver autoSave1 = new Autosaver();
		
		Thread editorThread  = new Thread(editor1);
		Thread autoSaverThread = new Thread(autoSave1);
		
		editorThread.start();
		autoSaverThread.start();

		Thread.sleep(5000);
		
		autoSave1.setDone();
		editor1.setDone();

		
	}
}
