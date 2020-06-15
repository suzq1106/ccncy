package com.mikes.ccncy.core.c05;

public class Request {
	
	private final String name;

	public Request(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return "[ Request " + name + " ]";
	}
	
}
