package com.mikes.ccncy.core.c10;

import java.util.Random;

public class WorkerThread extends Thread {

	private static final Random random = new Random();
	private final Channel channel;

	public WorkerThread(String name, Channel channel) {
		super(name);
		this.channel = channel;
	}

	@Override
	public void run() {
		try {
			while (true) {
				Request request = channel.takeRequest();
				System.out.println(Thread.currentThread().getName() + " executes " + request);

				Thread.sleep(random.nextInt(1000));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
