package com.mikes.ccncy.executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest {

	public static void main(String[] args) {
		ThreadPoolExecutor pool = new ThreadPoolExecutor(3, 3, 10, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(4));
		
		pool.allowCoreThreadTimeOut(true);
		
		Task task1 = new Task(1, "Task 1");
		Task task2 = new Task(2, "Task 2");
		Task task3 = new Task(3, "Task 3");
		Task task4 = new Task(4, "Task 4");
		Task task5 = new Task(5, "Task 5");
		Task task6 = new Task(6, "Task 6");
		
		pool.submit(task1);
		pool.submit(task2);
		pool.submit(task3);
		pool.submit(task4);
		pool.submit(task5);
		pool.submit(task6);
		
	}

	private static class Task implements Runnable {

		private int taskId;
		private String taskName;

		Task(int taskId, String taskName) {
			this.taskId = taskId;
			this.taskName = taskName;
		}

		@Override
		public void run() {
			try {
				System.out.println("run taskId =" + this.taskId + "   Thread " + Thread.currentThread().getId());
				Thread.sleep(5 * 1000);
				// System.out.println("end taskId =" + this.taskId);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		public String toString() {
			return Integer.toString(this.taskId);
		}

	}

}
