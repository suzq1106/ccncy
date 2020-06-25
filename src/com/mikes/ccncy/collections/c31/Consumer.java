package com.mikes.ccncy.collections.c31;

public class Consumer implements Runnable {

	private final Channel channel;

	public Consumer(Channel channel) {
		this.channel = channel;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Data data = channel.take();
				System.out.println(Thread.currentThread().getName() + " consume :" + data);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			Thread.yield();
		}
	}

}
