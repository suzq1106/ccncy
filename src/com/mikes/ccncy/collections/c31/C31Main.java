package com.mikes.ccncy.collections.c31;

public class C31Main {

	public static void main(String[] args) {

		final Channel channel = new Channel();

		Producer p = new Producer(channel);
		Consumer c1 = new Consumer(channel);
		Consumer c2 = new Consumer(channel);

		new Thread(p).start();
		new Thread(c1).start();
		new Thread(c2).start();
	}

}
