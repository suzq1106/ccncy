package com.mikes.ccncy.core.c11;

public class Host {

	public Data request(final int count, final char c) {
		final FutureData future = new FutureData();
		System.out.println("    request(" + count + ", " + c + ") BEGIN");
		new Thread() {
			@Override
			public void run() {
				RealData realData = new RealData(count, c);
				future.setRealData(realData);
			}
		}.start();
		System.out.println("    request(" + count + ", " + c + ") END");
		return future;
	}

}
