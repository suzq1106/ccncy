package com.mikes.ccncy.core.c13;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

//实际执行记录日志的类，每个线程都会拥有一个该类的实例
public class TSLog {

	private PrintWriter writer = null;

	public TSLog(String fileName) {
		try {
			writer = new PrintWriter(new FileWriter(fileName));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void println(String content) {
		writer.println(content);
	}

	public void close() {
		writer.println("==== End of log ====");
		writer.close();
	}

}
