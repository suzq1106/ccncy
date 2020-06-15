package com.mikes.ccncy.core.c07;

public class Table {

	private final String[] buffer;// 实际存放蛋糕的容器
	private int tail;// 下次put的位置
	private int head;// 下次take的位置
	private int count;// buffer中蛋糕的个数

	public Table(int count) {
		buffer = new String[count];
		this.tail = 0;
		this.head = 0;
		this.count = 0;
	}

	public synchronized void put(String cake) throws InterruptedException {
		while (count >= buffer.length) {
			wait();
		}
		System.out.println(Thread.currentThread().getName() + " put " + cake);
		buffer[tail] = cake;
		tail = (tail + 1) % buffer.length;
		count++;
		notifyAll();
	}

	public synchronized String take() throws InterruptedException {
		while (count <= 0) {
			wait();
		}
		String cake = buffer[head];
		head = (head + 1) % buffer.length;
		count--;
		System.out.println(Thread.currentThread().getName() + " takes " + cake);
		notifyAll();
		return cake;
	}

}
