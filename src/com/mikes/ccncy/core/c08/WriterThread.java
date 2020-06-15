package com.mikes.ccncy.core.c08;

import java.util.Random;

public class WriterThread extends Thread {

	private final static Random random = new Random();
	private final Data data;
	private final String filler;
	private int index = 0;

	public WriterThread(Data data, String filler) {
		this.data = data;
		this.filler = filler;
	}

	@Override
	public void run() {
		try {
			while (true) {
				char c = nextChar();
				data.write(c);
				Thread.sleep(random.nextInt(3000));
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	private char nextChar() {
		char c = filler.charAt(index);
		index++;
		if (index >= filler.length()) {
			index = 0;
		}
		return c;
	}

}
