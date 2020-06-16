package com.mikes.ccncy.core.c13;

public class ClientThread extends Thread {

	public ClientThread(String name) {
		super(name);
	}

	@Override
	public void run() {
		System.out.println(getName() + " BEGIN");
		for (int i = 0; i < 20; i++) {
			Log.println("i=" + i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
		Log.close();
		System.out.println(getName() + " END");
	}

}
