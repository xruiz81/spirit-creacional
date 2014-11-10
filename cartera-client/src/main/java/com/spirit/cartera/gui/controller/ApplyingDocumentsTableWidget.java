package com.spirit.cartera.gui.controller;

import com.spirit.client.SpiritConstants;

public class ApplyingDocumentsTableWidget {
	private String transaction;
	private Double balance;
	private Double valueToApply;
	private java.util.Date dateToApply;
	
	public ApplyingDocumentsTableWidget() {
		this.transaction = SpiritConstants.getEmptyCharacter();
		this.balance = new Double(0D);
		this.valueToApply = new Double(0D);
		this.dateToApply = new java.util.Date();
	}
	public String getTransaction() {
		return transaction;
	}
	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getValueToApply() {
		return valueToApply;
	}
	public void setValueToApply(double valueToApply) {
		this.valueToApply = valueToApply;
	}
	public java.util.Date getDateToApply() {
		return dateToApply;
	}
	public void setDateToApply(java.util.Date dateToApply) {
		this.dateToApply = dateToApply;
	}
}
