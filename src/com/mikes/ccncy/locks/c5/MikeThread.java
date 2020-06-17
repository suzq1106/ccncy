package com.mikes.ccncy.locks.c5;

public class MikeThread extends Thread {

	private FIFOMutex fifo;
	public static int count;

	public MikeThread(String name, FIFOMutex fifo) {
		super(name);
		this.fifo = fifo;
	}

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			fifo.lock();
			count++;
			System.out.println("name:" + getName() + "  count:" + count);
			fifo.unlock();
		}
	}

}
