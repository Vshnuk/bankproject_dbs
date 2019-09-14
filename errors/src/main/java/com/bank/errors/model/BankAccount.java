package com.bank.errors.model;

import javax.persistence.*;

@Entity
@Table(name="bank_account")
public class BankAccount {
	
	@Id
	@GeneratedValue
	@Column(name="id", nullable=false)
	private long id;
	
	@Column(name="Full_Name",length=50,nullable=false)
	private String fullName;
	
	@Column(name="Balance", nullable=false)
	private double balance;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "BankAccount [id=" + id + ", fullName=" + fullName + ", balance=" + balance + "]";
	}

	public BankAccount(long id, String fullName, double balance) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.balance = balance;
	}

	public BankAccount() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
