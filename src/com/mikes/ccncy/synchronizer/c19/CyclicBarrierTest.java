package com.mikes.ccncy.synchronizer.c19;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

	public static void main(String[] args) {

		int N = 5;// �˶�Ա����
		CyclicBarrier cb = new CyclicBarrier(N, new Runnable() {
			@Override
			public void run() {
				System.out.println("****** �����˶�Ա��׼����ϣ�����ǹ���ܣ�******");
			}
		});

		for (int i = 0; i < N; i++) {
			Thread t = new Thread(new PrepareWork(cb), "�˶�Ա[" + i + "]");
			t.start();
		}
	}

	private static class PrepareWork implements Runnable {

		private CyclicBarrier cb;

		PrepareWork(CyclicBarrier cb) {
			this.cb = cb;
		}

		@Override
		public void run() {
			try {
				Thread.sleep(500);
				System.out.println(Thread.currentThread().getName() + ": ׼�����");
				cb.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
		}

	}

}
