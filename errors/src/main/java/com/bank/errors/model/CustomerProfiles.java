package com.bank.errors.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Component
@Entity
@Table(name="customer_profiles")
@JsonIgnoreProperties ({"hibernateLazyInitializer", "handler"})
public class CustomerProfiles  implements Serializable{
	
	@Id
	private int accno;
	
	private String accountname;
	private String accounttype;
	private String address;
	private String email;
	
	private int adhaarno;
	
	private String createdon;
	
	private double balance;

	public int getAccno() {
		return accno;
	}

	public void setAccno(int accno) {
		this.accno = accno;
	}

	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}

	public String getAccounttype() {
		return accounttype;
	}

	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAdhaarno() {
		return adhaarno;
	}

	public void setAdhaarno(int adhaarno) {
		this.adhaarno = adhaarno;
	}

	public String getCreatedon() {
		return createdon;
	}

	public void setCreatedon(String createdon) {
		this.createdon = createdon;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Customer_Profiles [accno=" + accno + ", accountname=" + accountname + ", accounttype=" + accounttype
				+ ", address=" + address + ", email=" + email + ", adhaarno=" + adhaarno + ", createdon=" + createdon
				+ ", balance=" + balance + "]";
	}
	
	
	
}
