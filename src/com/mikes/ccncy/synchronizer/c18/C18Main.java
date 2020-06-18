package com.mikes.ccncy.synchronizer.c18;

import java.util.concurrent.CountDownLatch;

public class C18Main {

	private static final int N = 10;

	public static void main(String[] args) throws Exception{

		CountDownLatch startSignal = new CountDownLatch(1);

		for (int i = 0; i < N; i++) {
			new Thread(new Worker("Thread " + i, startSignal)).start();;
		}
		
		System.out.println("fighting...");
		Thread.sleep(10000);
		
		startSignal.countDown();
	}
}
