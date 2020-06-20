package com.mikes.ccncy.collections.c31;

import java.util.concurrent.ThreadLocalRandom;

public class Producer implements Runnable {

	private final Channel channel;

	public Producer(Channel channel) {
		this.channel = channel;
	}

	@Override
	public void run() {
		int count = 0;
		while (true) {
			String v = String.valueOf(ThreadLocalRandom.current().nextInt());
			Data data = new Data(v);
			
			try {
				System.out.println(Thread.currentThread().getName() + " produce :" + data);
				channel.put(data);
				count++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(count >= 10){
				break;
			}
			Thread.yield();
		}
	}

}
