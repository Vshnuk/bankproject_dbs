package com.bank.errors.service;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bank.errors.repository.CustomerDaoRepository;
import com.bank.errors.repository.CustomerProfileDaoRepository;
import com.bank.errors.BankWebApp.exception.BankTransactionException;
import com.bank.errors.model.BankEmployee;
import com.bank.errors.model.Customer;
import com.bank.errors.model.CustomerProfiles;


@Service
public class CustomerServiceImpl implements CustomerService {

	static int trans_id=1;

	@Autowired
	private CustomerDaoRepository cusRepo;
	
	@Autowired
	private CustomerProfileDaoRepository cusprorepo;
	@Autowired
	private EntityManager entitymanager;
	
	@Override
	public boolean registerUser(Customer c) {
		if(cusRepo.findById(c.getAccno())!=null)
		{
			cusRepo.save(c);
			return true;
		}
		else
			return false;
		
	}
	
	@Override
	public boolean registerUserProfile(CustomerProfiles c) {
		if(cusprorepo.findById(c.getAccno())!=null)
		{
			cusprorepo.save(c);
			return true;
		}
		else
			return false;
		
	}

	@Override
	public boolean loginu_check(String username, String password) {
		boolean flag=true;
		String sql="select accno from "+Customer.class.getName()+" where username=:username and password=:password";
		Query query=entitymanager.createQuery(sql);
		query.setParameter("username",username);
		query.setParameter("password",password);
		System.out.println("-----------------------------");
		try {
		query.getSingleResult();
		}
		catch(NoResultException e)
		{
			flag=false;
		}
		return flag;		
		
	}

	@Override
	public String profileFill(Customer newuser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean logina_check(String username, String password) {
		boolean flag=true;
		String sql="select accno from "+BankEmployee.class.getName()+" where username=:username and password=:password";
		Query query=entitymanager.createQuery(sql);
		query.setParameter("username",username);
		query.setParameter("password",password);
		System.out.println("-----------------------------");
		try {
		query.getSingleResult();
		}
		catch(NoResultException e)
		{
			flag=false;
		}
		return flag;	
	}

	@Override
	public List<CustomerProfiles> getProfiles() {
		return cusprorepo.findAll();
	}

	@Transactional
	@Override
	public boolean modifyAccount(String username, int accno, int adhaarno, String accounttype, String address) {
		
		try {
			System.out.println(cusprorepo.getOne(accno)+"w88888888888888888888888888888888888888888888888888888888888888888888888888");
			cusprorepo.getOne(accno);
		}
		catch(Exception e)
		{
			return false;
		}
		if(cusprorepo.findById(accno)!=null)
		{
			boolean flag=true;
			String sql="update customer_profiles set address=:address,adhaarno=:adhaarno,accounttype=:accounttype,accountname=:accountname where accno=:accno";
			System.out.println(sql);
			Query query=entitymanager.createNativeQuery(sql);
			System.out.println("in modify account");
			query.setParameter("address", address);
			query.setParameter("adhaarno", adhaarno);
			query.setParameter("accounttype", accounttype);
			query.setParameter("accountname", username);
			query.setParameter("accno", accno);
			System.out.println("update query");
			entitymanager.joinTransaction();
			try {
				query.executeUpdate();
			}
			catch(Exception e)
			{
				flag=false;
				System.out.println(e);
			}
			return flag;
		}
		else
			return false;
		
		
	}


	@Transactional
	@Override
	public boolean deleteAccount(int accno) {
		try {
			cusprorepo.getOne(accno);
		}
		catch(NoSuchElementException e)
		{
			return false;
		}
		if(cusprorepo.findById(accno)!=null)
		{
			boolean flag=true;
			String sql="delete from customer_profiles where accno=:accno";
			System.out.println(sql);
			Query query=entitymanager.createNativeQuery(sql);
			System.out.println("in delete account");
			query.setParameter("accno", accno);
			System.out.println("delete query");
			entitymanager.joinTransaction();
			try {
				query.executeUpdate();
			}
			catch(Exception e)
			{
				flag=false;
				System.out.println(e);
			}
			return flag;
		}
		else
			return false;
	}

	@Override
	public List<CustomerProfiles> getProfile(int accno) {
//		String sql="select accno,adhaarno,createdon from customer_profiles where accno=:accno";
//		Query query=entitymanager.createNativeQuery(sql);
//		query.setParameter("accno",accno);
//		System.out.println("search box");	
//		try {
//			CustomerProfiles cp=(CustomerProfiles)query.getSingleResult();
//			System.out.print(cp);
//			}
//			catch(NoResultException e)
//			{
//				System.out.println(e);
//			}
//		return null;
		
		List<CustomerProfiles> li=new LinkedList<>();
		try {
			System.out.println(cusprorepo.getOne(accno)+"w88888888888888888888888888888888888888888888888888888888888888888888888888");
			cusprorepo.getOne(accno);
		}
		catch(Exception e)
		{
			return li;
		}
		li.add(cusprorepo.getOne(accno));
		return li;
	}
	
	
	public void addAmount(int accno,double amount ) throws BankTransactionException{
		CustomerProfiles account=null;
		try {
			 account=cusprorepo.getOne(accno);
		}
		catch(Exception e)
		{
			throw new BankTransactionException("Account not found");
		}
		double newBalance = account.getBalance()+amount;
		if(account.getBalance()+amount<0) {
			throw new BankTransactionException("The money in account is not enough("+account.getBalance()+")");
		}
		account.setBalance(newBalance);
	}
		
		@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = BankTransactionException.class)
		public void sendMoney(int fromAccountId, int toAccountId, double amount) throws BankTransactionException{
//			addAmount(toAccountId,amount);
//			addAmount(fromAccountId,-amount);
//			Date date= new Date();
//			long time = date.getTime();
//			Timestamp ts = new Timestamp(time);
			String sql="INSERT INTO transactions (from_acc,to_acc,type,amount,trans_id) VALUES(?,?,?,?,?)";
			Query query=entitymanager.createNativeQuery(sql);
			query.setParameter(1,fromAccountId);
			query.setParameter(2,toAccountId);
			query.setParameter(3,"Debited From");
			query.setParameter(4,amount);
			query.setParameter(5,trans_id);
			addAmount(toAccountId,amount);
//			query.setParameter(6, ts);
					query.executeUpdate();
//					sql="INSERT INTO transactions (from_acc,to_acc,type,amount,trans_date) VALUES (?,?,?,?,?)";
//					query=entityManager.createNativeQuery(sql);
					query.setParameter(1,toAccountId);
					query.setParameter(2,fromAccountId);
					query.setParameter(3,"Credited To");
					query.setParameter(4,amount);
					query.setParameter(5,trans_id);
				//	query.setParameter(6, ts);
					query.executeUpdate();
					trans_id++;
			addAmount(fromAccountId,-amount);
		}

	
	
}
