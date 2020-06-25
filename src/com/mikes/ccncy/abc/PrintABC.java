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
						System.out.print("A");
						cycle++;
						cycle %= 3;
						obj.notifyAll();
						i++;
					} else {
						try {
							obj.wait();

						} catch (InterruptedException e) {
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
						System.out.print("B");
						cycle++;
						cycle %= 3;
						obj.notifyAll();
						i++;
					} else {
						try {
							obj.wait();
						} catch (InterruptedException e) {
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
						System.out.print("C");
						cycle++;
						cycle %= 3;
						obj.notifyAll();
						i++;
					} else {
						try {
							obj.wait();

						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

}
