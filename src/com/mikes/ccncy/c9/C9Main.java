package com.mikes.ccncy.c9;

public class C9Main {

	public static void main(String[] args) {
		System.out.println("main BEGIN");
		Host host = new Host();
		host.request(10, 'A');
		host.request(20, 'B');
		host.request(30, 'C');
		System.out.println("main END");
	}

}
