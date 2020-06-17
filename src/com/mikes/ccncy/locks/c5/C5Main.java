package com.mikes.ccncy.locks.c5;


public class C5Main {

	public static void main(String[] args) throws Exception {
		FIFOMutex fifo = new FIFOMutex();
		
		MikeThread alice = new MikeThread("Alice", fifo);
		MikeThread bobby = new MikeThread("Bobby", fifo);
		MikeThread chris = new MikeThread("Chris", fifo);
		
		alice.start();
		bobby.start();
		chris.start();
		
		alice.interrupt();
		
		alice.join();
		bobby.join();
		chris.join();
		
		System.out.print("Finished");
		
	}
	
}
