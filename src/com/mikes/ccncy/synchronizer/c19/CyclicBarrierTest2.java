package com.mikes.ccncy.synchronizer.c19;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest2 {

	public static void main(String[] args) throws Exception {
		int N = 5;
		CyclicBarrier cb = new CyclicBarrier(N, new Runnable() {
			@Override
			public void run() {
				System.out.println("****** �����˶�Ա��׼����ϣ�����ǹ���ܣ�******");
			}
		});

		List<Thread> list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			Thread t = new Thread(new PrepareWork(cb), "�˶�Ա[" + i + "]");
			list.add(t);
			t.start();
			if (i == 3) {
				t.interrupt();
			}
		}

		Thread.sleep(2000);
		System.out.println("Barrier�Ƿ��𻵣�" + cb.isBroken());
	}

	private static class PrepareWork implements Runnable {

		private CyclicBarrier cb;

		PrepareWork(CyclicBarrier cb) {
			this.cb = cb;
		}

		@Override
		public void run() {
			try {
				System.out.println(Thread.currentThread().getName() + ": ׼�����");
				cb.await();
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getName() + ": ���ж�");
			} catch (BrokenBarrierException e) {
				System.out.println(Thread.currentThread().getName() + ": �׳�BrokenBarrierException");
			}
		}

	}

}
