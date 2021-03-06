package com.mikes.ccncy.synchronizer.c22;

import java.util.concurrent.Phaser;

public class PhaserTest4 {

	private static final int TASKS_PER_PHASER = 4;

	public static void main(String[] args) {
		int repeats = 3; // 指定任务最多执行的次数
		Phaser phaser = new Phaser() {
			@Override
			protected boolean onAdvance(int phase, int registeredParties) {
				System.out.println("---------------PHASE[" + phase + "],Parties[" + registeredParties
						+ "] ---------------");
				return phase + 1 >= repeats || registeredParties == 0;
			}
		};

		Task4[] taskers = new Task4[10];
		build(taskers, 0, taskers.length, phaser); // 根据任务数,为每个任务分配Phaser对象

		for (int i = 0; i < taskers.length; i++) { // 执行任务
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
		while (!phaser.isTerminated()) { // 只要Phaser没有终止, 各个线程的任务就会一直执行
			int i = phaser.arriveAndAwaitAdvance(); // 等待其它参与者线程到达
			// do something
			System.out.println(Thread.currentThread().getName() + ": 执行完任务");
		}
	}
}
