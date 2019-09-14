package com.bank.errors.service;

import java.util.List;

import com.bank.errors.BankWebApp.exception.BankTransactionException;
import com.bank.errors.model.Customer;
import com.bank.errors.model.CustomerProfiles;



public interface CustomerService {
	public boolean registerUser(Customer c);
	public boolean registerUserProfile(CustomerProfiles c);
	public boolean logina_check(String username, String password);
	public boolean loginu_check(String username, String password);
	public String profileFill(Customer newuser);
	public List<CustomerProfiles> getProfiles();
	public boolean modifyAccount(String username, int accno, int adhaarno, String accounttype, String address);
	boolean deleteAccount(int accno);
	public List<CustomerProfiles> getProfile(int accno);
	public void sendMoney(int fromAccountId, int toAccountId, double amount) throws BankTransactionException;
}
