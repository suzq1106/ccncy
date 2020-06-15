package com.mikes.ccncy.core.c08;

public class ReadWriteLock {
	
	private int readingReaders = 0;
	private int writingWriters = 0;

	public synchronized void readLock() throws InterruptedException {
		while (writingWriters > 0) {
			wait();
		}
		readingReaders++;
	}

	public synchronized void readUnlock() {
		readingReaders--;
		notifyAll();
	}

	public synchronized void writeLock() throws InterruptedException {
		while (readingReaders > 0 || writingWriters > 0) {
			wait();
		}
		writingWriters++;
	}

	public synchronized void writeUnlock() {
		writingWriters--;
		notifyAll();
	}
}
