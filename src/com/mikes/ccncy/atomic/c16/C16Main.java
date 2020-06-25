package com.mikes.ccncy.atomic.c16;

import java.util.ArrayList;
import java.util.List;

public class C16Main {

	public static void main(String[] args) throws Exception {
		Account account = new Account(0);

		List<Thread> list = new ArrayList<Thread>();
		for (int i = 1; i <= 100; i++) {
			Thread t = new Thread(new Task(account, i));
			list.add(t);
			t.start();
		}

		for (Thread t : list) {
			t.join();
		}

		System.out.println(account.toString());
	}

	private static class Task implements Runnable {
		private Account account;
		private int amount;

		Task(Account account, int amount) {
			this.account = account;
			this.amount = amount;
		}

		@Override
		public void run() {
			try {
				Thread.sleep(100);
				account.cashIn(amount);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}

}
