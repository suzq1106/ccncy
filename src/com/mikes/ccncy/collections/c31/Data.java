package com.mikes.ccncy.collections.c31;

public class Data<T> {

	private T data;

	public Data(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Data{" + "data=" + data + '}';
	}

}
