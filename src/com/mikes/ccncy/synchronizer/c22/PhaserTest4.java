package com.mikes.ccncy.synchronizer.c22;

import java.util.concurrent.Phaser;

public class PhaserTest4 {

	private static final int TASKS_PER_PHASER = 4;

	public static void main(String[] args) {
		int repeats = 3; // ָ���������ִ�еĴ���
		Phaser phaser = new Phaser() {
			@Override
			protected boolean onAdvance(int phase, int registeredParties) {
				System.out.println("---------------PHASE[" + phase + "],Parties[" + registeredParties
						+ "] ---------------");
				return phase + 1 >= repeats || registeredParties == 0;
			}
		};

		Task4[] taskers = new Task4[10];
		build(taskers, 0, taskers.length, phaser); // ����������,Ϊÿ���������Phaser����

		for (int i = 0; i < taskers.length; i++) { // ִ������
			Thread thread = new Thread(taskers[i]);
			thread.start();
		}
	}

	private static void build(Task4[] taskers, int lo, int hi, Phaser phaser) {
		if (hi - lo > TASKS_PER_PHASER) {
			for (int i = lo; i < hi; i += TASKS_PER_PHASER) {
				int j = Math.min(i + TASKS_PER_PHASER, hi);
				build(taskers, i, j, new Phaser(phaser));
			}
		} else {
			for (int i = lo; i < hi; i++) {
				taskers[i] = new Task4(phaser);
			}
		}
	}
}

class Task4 implements Runnable {
	private final Phaser phaser;

	Task4(Phaser phaser) {
		this.phaser = phaser;
		this.phaser.register();
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
