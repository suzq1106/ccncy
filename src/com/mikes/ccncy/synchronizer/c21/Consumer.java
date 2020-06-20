package com.mikes.ccncy.synchronizer.c21;

import java.util.List;
import java.util.concurrent.Exchanger;

public class Consumer implements Runnable {

	private Exchanger<List<String>> exchanger;

	public Consumer(Exchanger<List<String>> exchanger) {
		this.exchanger = exchanger;
	}

	@Override
	public void run() {
		List<String> list;
		while (true) {
			try {
				list = exchanger.exchange(null);
				System.out.println(Thread.currentThread().getName() + ": 消费了数据[" + list + "]");
				Thread.sleep(1000);
				if (list == null) {
					break;
				}
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}

}
