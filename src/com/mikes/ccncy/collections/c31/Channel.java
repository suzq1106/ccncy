package com.mikes.ccncy.collections.c31;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Channel {

	private final BlockingQueue<Data> blockingQueue = new ArrayBlockingQueue<Data>(100);

	public Data take() throws InterruptedException {
		return blockingQueue.take();
	}

	public void put(Data o) throws InterruptedException {
		blockingQueue.put(o);
	}

}
