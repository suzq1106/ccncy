package com.mikes.ccncy.synchronizer.c18;

import java.util.concurrent.CountDownLatch;

public class C18Main2 {

	private static final int N = 10;

	public static void main(String[] args) throws Exception {

		CountDownLatch compsignal = new CountDownLatch(N);

		for (int i = 0; i < N; i++) {
			new Thread(new Worker2("Thread " + (i + 1), compsignal)).start();
		}

		compsignal.await();

		System.out.println("fighting...");
	}
}
