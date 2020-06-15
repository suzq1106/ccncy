package com.mikes.ccncy.core.c06;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Data {

	private String filename; // 文件名
	private String content; // 数据内容
	private boolean changed;

	public Data(String filename, String content) {
		this.filename = filename;
		this.content = content;
		this.changed = true;
	}

	public synchronized void change(String newContent) {
		this.content = newContent;
		this.changed = true;
	}

	public synchronized void save() throws IOException {
		if (!changed) {
			System.out.println(Thread.currentThread().getName() + " balks");
			return;
		}
		doSave();
		changed = false;
	}

	private void doSave() throws IOException {
		System.out.println(Thread.currentThread().getName() + " calls doSave, content = " + content);
		Writer writer = new FileWriter(filename);
		writer.write(content);
		writer.close();
	}
}
