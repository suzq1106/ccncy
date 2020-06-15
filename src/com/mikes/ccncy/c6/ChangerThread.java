package com.mikes.ccncy.c6;

import java.util.Random;

public class ChangerThread extends Thread {

	private Data data;
	private Random random = new Random();

	public ChangerThread(String name, Data data) {
		super(name);
		this.data = data;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; true; i++) {
				data.change("No." + i);
				Thread.sleep(random.nextInt(1000));
				data.save();//手动保存
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
