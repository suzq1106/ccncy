package com.mikes.ccncy.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class ScheduleExecutorTest {

	public static void main(String[] args) {
		 final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		 
		// ÿ��1s����һ��
		 final ScheduledFuture scheduledFuture= scheduler.scheduleAtFixedRate(new BeepTask(), 0, 2, TimeUnit.SECONDS);

		scheduler.schedule(new Runnable() {
			@Override
			public void run() {
				scheduledFuture.cancel(true);
				scheduler.shutdown();
			}
			
		}, 10, TimeUnit.SECONDS);// 10���, ȡ����������
		
		
		
		
	}

	private static class BeepTask implements Runnable {

		@Override
		public void run() {
			System.out.println("beep!");
		}

	}
}
