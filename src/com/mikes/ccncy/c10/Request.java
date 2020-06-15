package com.mikes.ccncy.c10;

public class Request {

	private final String name;
	private final int number;

	public Request(String name, int number) {
		this.name = name;
		this.number = number;
	}

	public String toString() {
		return "[ Request from " + name + " No." + number + " ]";
	}
}
