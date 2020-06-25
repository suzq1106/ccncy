package com.mikes.ccncy.synchronizer.c21;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

public class Producer implements Runnable {

	private Exchanger<List<String>> exchanger;

	public Producer(Exchanger<List<String>> exchanger) {
		this.exchanger = exchanger;
	}

	@Override
	public void run() {
		List<String> list = null;
		try {
			for (int i = 0; i < 10; i++) {

				list = new ArrayList<String>();
				list.add("Number" + i + ": " + String.valueOf(Math.random()));
				list.add("Number" + i + ": " + String.valueOf(Math.random()));
				list.add("Number" + i + ": " + String.valueOf(Math.random()));

				System.out.println(Thread.currentThread().getName() + ": 生产了数据[" + list + "]");
				list = exchanger.exchange(list);

				if (list != null) {
					System.out.println("系统异常");
				}
			}
			exchanger.exchange(null);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

}
