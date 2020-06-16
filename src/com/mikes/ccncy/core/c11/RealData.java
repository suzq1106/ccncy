package com.mikes.ccncy.core.c11;

public class RealData implements Data {
	private final String content;
	private final int count;
	private final char c;

	public RealData(int count, char c) {
		this.count = count;
		this.c = c;
		content = makeContent();
	}

	public String makeContent() {
		System.out.println("        making RealData(" + count + ", " + c + ") BEGIN");
		char[] buffer = new char[count];
		for (int i = 0; i < buffer.length; i++) {
			buffer[i] = c;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("        making RealData(" + count + ", " + c + ") END");
		return new String(buffer);
	}

	@Override
	public String getContent() {
		return this.content;
	}

}
