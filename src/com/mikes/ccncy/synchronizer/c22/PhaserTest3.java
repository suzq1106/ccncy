package com.mikes.ccncy.synchronizer.c22;

import java.util.concurrent.Phaser;

public class PhaserTest3 {

	public static void main(String[] args) {
		int repeats = 5; // ָ���������ִ�еĴ���

		Phaser phaser = new Phaser() {
			@Override
			protected boolean onAdvance(int phase, int registeredParties) {
				System.out.println("---------------PHASE[" + phase + "],Parties[" + registeredParties
						+ "] ---------------");
				return phase + 1 >= repeats || registeredParties == 0;
			}
		};
		for (int i = 0; i < 10; i++) {
			phaser.register(); // ע������������߳�
			new Thread(new Task3(phaser), "Thread-" + i).start();
		}
	}
}

class Task3 implements Runnable {
	private final Phaser phaser;

	Task3(Phaser phaser) {
		this.phaser = phaser;
	}

	@Override
	public void run() {
		while (!phaser.isTerminated()) { // ֻҪPhaserû����ֹ, �����̵߳�����ͻ�һֱִ��
			int i = phaser.arriveAndAwaitAdvance(); // �ȴ������������̵߳���
			// do something
			System.out.println(Thread.currentThread().getName() + ": ִ��������");
		}
	}
}