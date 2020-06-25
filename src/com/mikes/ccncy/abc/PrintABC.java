package com.mikes.ccncy.abc;

public class PrintABC {

	public static void main(String[] args) {
		Thread pa = new PrintA();
		Thread pb = new PrintB();
		Thread pc = new PrintC();

		pa.start();
		pb.start();
		pc.start();
	}

	private static int cycle = 0;
	private static final Object obj = new Object();

	static class PrintA extends Thread {
		@Override
		public void run() {
			synchronized (obj) {
				for (int i = 0; i < 10;) {
					if (cycle == 0) {
						// 代表轮到当前线程打印
						System.out.print("A");
						// 下一个轮到B打印，所以把startB置为true，其它为false
						cycle++;
						cycle %= 3;
						// 唤醒其他线程
						obj.notifyAll();
						// 在这里对i进行增加操作
						i++;
					} else {
						try {
							obj.wait();

						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	static class PrintB extends Thread {
		@Override
		public void run() {
			synchronized (obj) {
				for (int i = 0; i < 10;) {
					if (cycle == 1) {
						// 代表轮到当前线程打印
						System.out.print("B");
						// 下一个轮到B打印，所以把startB置为true，其它为false
						cycle++;
						cycle %= 3;
						// 唤醒其他线程
						obj.notifyAll();
						// 在这里对i进行增加操作
						i++;
					} else {
						try {
							obj.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	static class PrintC extends Thread {
		@Override
		public void run() {
			synchronized (obj) {
				for (int i = 0; i < 10;) {
					if (cycle == 2) {
						// 代表轮到当前线程打印
						System.out.print("C");
						// 下一个轮到B打印，所以把startB置为true，其它为false
						cycle++;
						cycle %= 3;
						// 唤醒其他线程
						obj.notifyAll();
						// 在这里对i进行增加操作
						i++;
					} else {
						try {
							obj.wait();

						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

}
