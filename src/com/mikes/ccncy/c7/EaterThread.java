package com.mikes.ccncy.c7;

import java.util.Random;

public class EaterThread extends Thread {

	private final Table table;
	private final Random random;

	public EaterThread(String name, Table table, long seed) {
		super(name);
		this.table = table;
		this.random = new Random(seed);
	}

	@Override
	public void run() {
		try {
			while (true) {
				String cake = table.take();
				Thread.sleep(random.nextInt(1000));
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}
