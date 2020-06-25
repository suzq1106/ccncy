package com.mikes.ccncy.synchronizer.c22;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.Phaser;

public class PhaserTest2 {

	public static void main(String[] args) throws Exception {
		Phaser phaser = new Phaser(2); // ע�����߳�,���ⲿ��������ʱ,�����̴߳򿪿���
		for (int i = 0; i < 10; i++) {
			phaser.register(); // ע������������߳�
			new Thread(new Task2(phaser), "Thread-" + i).start();
		}

		// �ⲿ����:�ȴ��û���������
		System.out.println("Press ENTER to continue");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		reader.readLine();

		// �򿪿���
		phaser.arriveAndDeregister();
		phaser.arriveAndDeregister();
		System.out.println("���̴߳��˿���");
	}
}

class Task2 implements Runnable {

	private final Phaser phaser;

	Task2(Phaser phaser) {
		this.phaser = phaser;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + ": ��ʼִ������");
		int i = phaser.arriveAndAwaitAdvance(); // �ȴ������������̵߳���
		// do something
		System.out.println(Thread.currentThread().getName() + ": ִ�������񣬵�ǰphase =" + i + "");
	}

}
