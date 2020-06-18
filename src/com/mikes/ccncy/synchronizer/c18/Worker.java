package com.mikes.ccncy.synchronizer.c18;

import java.util.concurrent.CountDownLatch;

public class Worker implements Runnable {

	private final CountDownLatch startSignal;
	private final String name;

	public Worker(String name, CountDownLatch startSignal) {
		this.name = name;
		this.startSignal = startSignal;
	}

	@Override
	public void run() {
		try {
			startSignal.await();
			doWork();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void doWork() {
		System.out.println(name + ": do my work hardly.");
	}

}
