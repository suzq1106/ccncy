package com.mikes.ccncy.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {

	public static void main(String[] args) throws Exception {
		/*
		 * ExecutorService pool = Executors.newCachedThreadPool();
		 * FutureTask<Double> futureTask = new FutureTask<Double>(new
		 * MyCallable());
		 * 
		 * pool.submit(futureTask); System.out.println(futureTask.get());
		 * 
		 * Future<Double> future = pool.submit(new MyCallable());
		 * System.out.println(future.get());
		 */

		ExecutorService pool = Executors.newCachedThreadPool();
		Integer result = 20200625;
		Future<Integer> future = pool.submit(new Task(), result);
		System.out.println(future.get());

	}

	private static class Task implements Runnable {

		@Override
		public void run() {
			System.out.println("task begin.");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("task end.");
		}

	}

	private static class MyCallable implements Callable<Double> {

		@Override
		public Double call() throws Exception {
			System.out.println("call begin.");
			Thread.sleep(3000);
			System.out.println("call end.");
			return Math.random();
		}

	}
}
