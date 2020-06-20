package com.mikes.ccncy.synchronizer.c21;

import java.util.List;
import java.util.concurrent.Exchanger;

public class C21Main {

	public static void main(String[] args) {
		Exchanger<List<String>> exchanger = new Exchanger<>();
		Thread t1 = new Thread(new Producer(exchanger), "生产者-t1");
		Thread t2 = new Thread(new Consumer(exchanger), "消费者-t2");
		//Thread t3 = new Thread(new Consumer(exchanger), "消费者-t3");
		

		t1.start();
		t2.start();
		//t3.start();
	}

}
