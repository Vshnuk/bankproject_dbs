package com.bank.errors.BankWebApp.form;

public class SendMoneyForm {
	
	private Long fromAccountId;
	private Long toAccountId;
	private double amount;
	
	public Long getFromAccountId() {
		return fromAccountId;
	}
	public void setFromAccountId(Long fromAccountId) {
		this.fromAccountId = fromAccountId;
	}
	public Long getToAccountId() {
		return toAccountId;
	}
	public void setToAccountId(Long toAccountId) {
		this.toAccountId = toAccountId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public SendMoneyForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SendMoneyForm(Long fromAccountId, Long toAccountId, double amount) {
		super();
		this.fromAccountId = fromAccountId;
		this.toAccountId = toAccountId;
		this.amount = amount;
	}
	
}
