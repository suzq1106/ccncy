package com.mikes.ccncy.synchronizer.c22;

import java.util.concurrent.Phaser;

public class PhaserTest1 {

	public static void main(String[] args) throws Exception {
		Phaser phaser = new Phaser();
		for (int i = 0; i < 5; i++) {
			phaser.register();
			new Thread(new Task(phaser), "Thread-" + i).start();
		}
	}
}

class Task implements Runnable {

	private final Phaser phaser;

	Task(Phaser phaser) {
		this.phaser = phaser;
	}

	@Override
	public void run() {
		int i_1 = phaser.getPhase();
		System.out.println(Thread.currentThread().getName() + ": 开始执行完任务,i_1=" + i_1);
		int i = phaser.arriveAndAwaitAdvance();

		System.out.println(Thread.currentThread().getName() + ": 执行完任务，当前phase =" + i);
	}

}