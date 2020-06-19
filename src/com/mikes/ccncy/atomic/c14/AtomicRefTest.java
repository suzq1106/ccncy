package com.mikes.ccncy.atomic.c14;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicRefTest {

	public static void main(String[] args) throws Exception {
		AtomicReference<Integer> ref = new AtomicReference<Integer>(0);
		AtomicInteger count = new AtomicInteger(0);

		List<Thread> list = new ArrayList<Thread>();
		for (int i = 0; i < 1000; i++) {
			Thread t = new Thread(new Task(ref, count), "Thread " + i);
			list.add(t);
			t.start();
		}

		for (Thread t : list) {
			t.join();
		}

		System.out.println(count.get());
		System.out.println(ref.get());
	}

	static class Task implements Runnable {
		AtomicReference<Integer> ref;
		AtomicInteger count;

		Task(AtomicReference<Integer> ref, AtomicInteger count) {
			this.ref = ref;
			this.count = count;
		}

		@Override
		public void run() {
			for (;;) {
				count.incrementAndGet();
				Integer oldV = ref.get();
				if (ref.compareAndSet(oldV, oldV + 1)) {
					break;
				}
			}
		}

	}
}
