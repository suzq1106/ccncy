package com.mikes.ccncy.atomic.c16;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class Account {

	private volatile int money;
	private static final AtomicIntegerFieldUpdater<Account> updater = AtomicIntegerFieldUpdater.newUpdater(
			Account.class, "money");

	Account(int initial) {
		this.money = initial;
	}

	public void cashIn(int amount) {
		updater.addAndGet(this, amount);
	}

	public int getMoney() {
		return money;
	}

	@Override
	public String toString() {
		return "Account{" + "money=" + money + '}';
	}

}
