package com.mikes.ccncy.core.c13;

public class Log {

	private static final ThreadLocal<TSLog> tsLogCollection = new ThreadLocal<TSLog>();

	private static TSLog getTSLog() {
		TSLog tsLog = tsLogCollection.get();
		if (tsLog == null) {
			tsLog = new TSLog(Thread.currentThread().getName() + "-log.txt");
			tsLogCollection.set(tsLog);
		}
		return tsLog;
	}

	public static void println(String content) {
		getTSLog().println(content);
	}

	public static void close() {
		getTSLog().close();
	}

}
