package com.mikes.ccncy.abc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintABC2 {

	public static void main(String[] args) {

		Lock lock = new ReentrantLock();
		Condition conditionA = lock.newCondition();
		Condition conditionB = lock.newCondition();
		Condition conditionC = lock.newCondition();

		new Thread(new Runnable() {

			@Override
			public void run() {

				try {
					lock.lock();
					for (int i = 0; i < 10; i++) {
						System.out.print("A");
						conditionB.signal();
						conditionA.await();
					}

					conditionB.signal();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}

			}
		}).start();
		
		new Thread(new Runnable() {

			@Override
			public void run() {

				try {
					lock.lock();
					for (int i = 0; i < 10; i++) {
						System.out.print("B");
						conditionC.signal();
						conditionB.await();
					}

					conditionC.signal();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}

			}
		}).start();
		
		new Thread(new Runnable() {

			@Override
			public void run() {

				try {
					lock.lock();
					for (int i = 0; i < 10; i++) {
						System.out.print("C");
						conditionA.signal();
						conditionC.await();
					}

					conditionA.signal();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}

			}
		}).start();

	}

}
