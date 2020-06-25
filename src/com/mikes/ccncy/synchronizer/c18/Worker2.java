package com.mikes.ccncy.synchronizer.c18;

import java.util.concurrent.CountDownLatch;

public class Worker2 implements Runnable {

	private final CountDownLatch compsignal;
	private final String name;

	public Worker2(String name, CountDownLatch compsignal) {
		this.name = name;
		this.compsignal = compsignal;
	}

	@Override
	public void run() {
		compsignal.countDown();
		doWork();
	}

	private void doWork() {
		System.out.println(name + ": do my work hardly.");
	}

}
