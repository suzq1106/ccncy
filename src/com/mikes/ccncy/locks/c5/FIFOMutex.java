package com.mikes.ccncy.locks.c5;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

public class FIFOMutex {

	private final AtomicBoolean locked = new AtomicBoolean(false);
	private final Queue<Thread> waiters = new ConcurrentLinkedQueue<Thread>();

	public void lock() {
		Thread current = Thread.currentThread();
		waiters.add(current);

		while (waiters.peek() != current || !locked.compareAndSet(false, true)) {
			LockSupport.park(this);
		}
		
		waiters.remove();
	}

	public void unlock() {
		locked.set(false);
		LockSupport.unpark(waiters.peek());
		
	}

}
