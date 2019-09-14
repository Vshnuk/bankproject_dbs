package com.bank.errors.repository;

import java.util.List;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bank.errors.BankWebApp.exception.BankTransactionException;
import com.bank.errors.model.BankAccount;



@Repository
public class BankAccountDao {
	
	@Autowired
	private EntityManager entityManager;
	
	public BankAccountDao() {
		
	}
	
	public BankAccount findById(Long id) {
		return this.entityManager.find(BankAccount.class, id);
	}
	
	public List<BankAccount> listBankAccountInfo(){
		String sql = "select new " + BankAccount.class.getName() + "(e.id,e.fullName,e.balance)" + " from " + BankAccount.class.getName()+" e ";
		Query query = entityManager.createQuery(sql,BankAccount.class);
		return query.getResultList();
	}
	
	//MANDATORY:Transaction must be created before.
	@Transactional(propagation = Propagation.MANDATORY)
	public void addAmount(Long id,double amount) throws BankTransactionException{
		BankAccount account = this.findById(id);
		if(account==null) {
			throw new BankTransactionException("Account not found"+id);
		}
		
		double newBalance = account.getBalance()+amount;
		if(account.getBalance()+amount<0) {
			throw new BankTransactionException("The money in account"+id+"is not enough("+account.getBalance()+")");
		}
		account.setBalance(newBalance);
	}
		
		@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = BankTransactionException.class)
		public void sendMoney(Long fromAccountId, Long toAccountId, double amount) throws BankTransactionException{
//			addAmount(toAccountId,amount);
//			addAmount(fromAccountId,-amount);
			String sql="INSERT INTO transactions (from_acc,to_acc,type,amount,trans_date) VALUES (?,?,?,?,?)";
			Query query=entityManager.createNativeQuery(sql);
			query.setParameter(1,fromAccountId);
			query.setParameter(2,toAccountId);
			query.setParameter(3,"debit");
			query.setParameter(4,amount);
			query.setParameter(5,"2019-09-21");
			query.executeUpdate();
//			sql="INSERT INTO transactions (from_acc,to_acc,type,amount,trans_date) VALUES (?,?,?,?,?)";
//			query=entityManager.createNativeQuery(sql);
			query.setParameter(1,toAccountId);
			query.setParameter(2,fromAccountId);
			query.setParameter(3,"credit");
			query.setParameter(4,amount);
			query.setParameter(5,"2019-09-21");
			query.executeUpdate();
			addAmount(toAccountId,amount);
			addAmount(fromAccountId,-amount);
		}

}
