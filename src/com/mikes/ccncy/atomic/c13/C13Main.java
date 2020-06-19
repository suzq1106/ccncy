package com.mikes.ccncy.atomic.c13;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class C13Main {

	public static void main(String[] args) throws Exception {

		AtomicInteger ai = new AtomicInteger();

		List<Thread> list = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			Thread t = new Thread(new Accumlator(ai));
			list.add(t);
			t.start();
		}

		for (Thread t : list) {
			t.join();
		}

		System.out.println(ai.get());
	}

	static class Accumlator implements Runnable {

		private AtomicInteger ai;

		public Accumlator(AtomicInteger ai) {
			this.ai = ai;
		}

		@Override
		public void run() {
			for (int i = 1, len = 1000; i <= len; i++) {
				ai.addAndGet(i);
				//ai.incrementAndGet();
			}
		}

	}
}
