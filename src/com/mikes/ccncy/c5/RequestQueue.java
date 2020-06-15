package com.mikes.ccncy.c5;

import java.util.LinkedList;

public class RequestQueue {

	private final LinkedList<Request> queue = new LinkedList<Request>();

	// Guarded method
	public synchronized Request getRequest() {
		while (queue.size() <= 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return queue.removeFirst();
	}

	// state changing method
	public synchronized void putRequest(Request request) {
		queue.addLast(request);
		notifyAll();
	}

}
