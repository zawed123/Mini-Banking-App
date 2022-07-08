package com.monocept.model;

public class Transaction {

	private String username;
	private int amount;
	private String type;
	private String date;
	
	public Transaction(String username, int balance, String type, String date) {
		this.username = username;
		this.amount = balance;
		this.type = type;
		this.date = date;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int balance) {
		this.amount = balance;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
