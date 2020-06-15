package com.mikes.ccncy.c10;

/**
 * Channel类可用来接受、传送工作请求，并保存工人线程。
 */
public class Channel {

	private static final int MAX_REQUEST = 100;// 最大请求数
	private final Request[] requestQueue; // 请求队列
	private int tail;
	private int head;
	private int count;
	private final WorkerThread[] threadPool;

	public Channel(int threads) {
		this.requestQueue = new Request[MAX_REQUEST];
		this.tail = 0;
		this.head = 0;
		this.count = 0;
		threadPool = new WorkerThread[threads];
		for (int i = 0; i < threadPool.length; i++) {
			threadPool[i] = new WorkerThread("Worker-" + i, this);
		}
	}

	public void startWorkers() {
		for (int i = 0; i < threadPool.length; i++) {
			threadPool[i].start();
		}
	}

	public synchronized void putRequest(Request request) {
		while (count >= threadPool.length) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		requestQueue[tail] = request;
		tail = (tail + 1) % requestQueue.length;
		count++;
		notifyAll();

	}

	public synchronized Request takeRequest() {
		while (count <= 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Request request = requestQueue[head];
		head = (head + 1) % requestQueue.length;
		count--;
		notifyAll();
		return request;
	}
}
