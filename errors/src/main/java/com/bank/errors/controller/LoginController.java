package com.bank.errors.controller;



import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bank.errors.BankWebApp.exception.BankTransactionException;
import com.bank.errors.model.BankEmployee;
import com.bank.errors.model.Customer;
import com.bank.errors.model.CustomerProfiles;
import com.bank.errors.model.FromToDate;
import com.bank.errors.model.Transactions;
import com.bank.errors.repository.CustomerProfileDaoRepository;
import com.bank.errors.repository.TransactionDaoRepository;
import com.bank.errors.service.CustomerService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping({ "/customers" })
public class LoginController {
	
	@Autowired
	private CustomerService service;
	
	
	@Autowired
	private CustomerProfileDaoRepository cusprorepo;
	@Autowired
	private TransactionDaoRepository transrepo;
	
//	@GetMapping(path = { "/{id}" })
//	public Employee delete(@PathVariable("id") int id) {
//		Employee deletedEmp = null;
//		for (Employee emp : employees) {
//			if (emp.getEmpId().equals(id)) {
//				employees.remove(emp);
//				deletedEmp = emp;
//				break;
//			}
//		}
//		return deletedEmp;
//	}
	
	
	@PostMapping(value="/allaccounts")
	public List<CustomerProfiles> AllAccounts(@RequestBody CustomerProfiles cp)
	{	
		String username=cp.getAccountname();
		List<CustomerProfiles> list=cusprorepo.getallAccounts(username);
		System.out.println(list);
		//model.addAttribute("accountInfos",list);
		return list;
		
	}
	
	
	@PostMapping(value="/register")
	public Customer createUser(@RequestBody CustomerProfiles cp)
	{
		Customer cus=new Customer();
		System.out.println("sirish rajuri");
		String password=cp.getCreatedon();
		String username=cp.getAccountname();
		String address=cp.getAddress();
		int accno=cp.getAccno();
		String email=cp.getEmail();
		int adhaarno=cp.getAdhaarno();
		String accounttype=cp.getAccounttype();
		String createdon=null;
		cp.setCreatedon(null);
		//cp.setBalance(0);
		cus.setAccno(accno);
		cus.setPassword(password);
		cus.setUsername(username);
		if(service.registerUser(cus))
		{
			if(service.registerUserProfile(cp))
			{
				cus.setUsername(username);
				return cus;
			}
			else
			{
				cus.setUsername("");
				return cus;
			}	
		}
		else	
		{
			cus.setUsername("");
			return cus;
		}
	}
	

	@PostMapping(value="/sendMoney1")
	public Transactions viewSendMoneyPage(@RequestBody Transactions t)
	{   int accno=t.getFromAccountId();
		Date d=new Date(0);
		String date1=d.toString();
		String date2=date1+" 23:59:59";
		long sum=0;
		if(transrepo.getSumOfBalance(accno, date1, date2)!=null)
		{sum=transrepo.getSumOfBalance(accno, date1, date2);
		}
		
		if(sum>=10000)
		{
			t.setFromAccountId(-1);
			t.setToAccountId(0);
			return t;
		}
		System.out.println("Send Money: "+t.getFromAccountId());
		try {
			service.sendMoney(t.getFromAccountId(),t.getToAccountId(), t.getAmount());
		}
		catch(BankTransactionException e)
		{
			System.out.println("errorMessage"+"Error:"+e.getMessage());
			t.setFromAccountId(0);
			t.setToAccountId(-1);
			return  t;
		}
		return t;
	}
	
	
	@PostMapping(value="/display")
	public List<Transactions> DisplayTransactions(@RequestBody FromToDate f)
	{	int accno=f.getAccno();
		String date1=f.getFromdate().toString();
		String date2=f.getTodate().toString()+" 23:59:59";
		System.out.println(accno+"---"+date1+"-------"+date2);
		List<Transactions> list=transrepo.getAllTransactions(accno, date1, date2);
		System.out.println(list);
		return list;
	}
	
	@RequestMapping(value="/")
	public String HomeUser(HttpServletRequest request)
	{
//		System.out.println("***********************************************");
//		Object val1=request.getSession().getAttribute("logina");
//		Object val2=request.getSession().getAttribute("loginu");
//		System.out.println(val1+"-----"+val2);
//		if(val1==null && val2==null)
//			return "redirect:index.html";
//		else if(val1==null)
//			return "company_user";
//		else
//			return "company_admin";

		return "redirect:index.html";
	}
	
//	@RequestMapping(value="/customer/register")
//	public String createUser(Customer newuser)
//	{
//		System.out.println(this.getClass().getSimpleName() + " - Create new Customer method is invoked.");
//		System.out.println(service.registerUser(newuser));
//		return service.registerUser(newuser);
//
//	}
//	
//	@RequestMapping(value="/register",method=RequestMethod.GET)
//	public String registration()
//	{
//		return "register";
//
//	}
//	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpServletRequest request)
	{
//		request.getSession().invalidate();
		return "redirect:index.html";

	}
	
//	@RequestMapping(value="/login",method=RequestMethod.GET)
//	public String loggingin(HttpServletRequest request)
//	{
//		System.out.println("*********************login**************************");
//		Object val1=request.getSession().getAttribute("logina");
//		Object val2=request.getSession().getAttribute("loginu");
//		System.out.println(val1+"-----"+val2);
//		if(val1==null && val2==null)
//			return "redirect:index.html";
//		else if(val1==null)
//			return "company_user";
//		else
//			return "company_admin";
//
//	}
	
	
	
//	@RequestMapping(value="/company_admin.html",method=RequestMethod.GET)
//	public String lohtml(HttpServletRequest request)
//	{
//		System.out.println("*********************login**************************");
//		Object val1=request.getSession().getAttribute("logina");
//		Object val2=request.getSession().getAttribute("loginu");
//		System.out.println(val1+"-----"+val2);
//		if(val1==null && val2==null)
//			return "redirect:index.html";
//		else if(val1==null)
//			return "company_user";
//		else
//			return "";
//
//
//	}
//	
//	@RequestMapping(value="/company_user.html",method=RequestMethod.GET)
//	public String lginahtml(HttpServletRequest request)
//	{
//		System.out.println("*********************login**************************");
//		Object val1=request.getSession().getAttribute("logina");
//		Object val2=request.getSession().getAttribute("loginu");
//		System.out.println(val1+"-----"+val2);
//		if(val1==null && val2==null)
//			return "redirect:index.html";
//		else if(val1==null)
//			return "";
//		else
//			return "company_admin";
//
//	}
//	
//	@RequestMapping(value="/company_admin",method=RequestMethod.GET)
//	public String adminhtml(HttpServletRequest request)
//	{
//		System.out.println("*********************comapny_admin**************************");
//		Object val1=request.getSession().getAttribute("logina");
//		Object val2=request.getSession().getAttribute("loginu");
//		System.out.println(val1+"-----"+val2);
//		if(val1==null && val2==null)
//			return "redirect:index.html";
//		else if(val1==null)
//			return "company_user";
//		else
//			return "company_admin";
//	}
//	@RequestMapping(value="/company_user",method=RequestMethod.GET)
//	public String userhtml(HttpServletRequest request)
//	{
//		System.out.println("*********************company_user**************************");
//		Object val1=request.getSession().getAttribute("logina");
//		Object val2=request.getSession().getAttribute("loginu");
//		System.out.println(val1+"-----"+val2);
//		if(val1==null && val2==null)
//			return "redirect:index.html";
//		else if(val1==null)
//			return "company_user";
//		else
//			return "company_admin";
//	}
//	
//	
//	
//	
//	@RequestMapping(value="/profile/check")
//	public String customer_profile(Customer newuser)
//	{
//		System.out.println(this.getClass().getSimpleName() + " - Create new Customer method is invoked.");
//		System.out.println(service.registerUser(newuser));
//		return service.profileFill(newuser);
//
//	}
//	
	
	
	@PostMapping(path="/loginucheck")
	public Customer loginu(@RequestBody Customer cus)
	{

			String username=cus.getUsername();
			String password=cus.getPassword();	
			System.out.println("ss");
			System.out.println(username+"---"+password);
			System.out.println(service.loginu_check(username,password));
			if(service.loginu_check(username,password))
			{
				System.out.println("true correct user");
//				request.getSession().setAttribute("loginu",1);
				return cus;
			}
			else
			{
				cus.setUsername("");
				cus.setPassword("");
				return cus;
			}		
	}
	
	
	@RequestMapping(value="/loginacheck",method=RequestMethod.POST)
	public BankEmployee logina(@RequestBody BankEmployee be)
	{
//		Object val1=request.getSession().getAttribute("logina");
//		Object val2=request.getSession().getAttribute("loginu");
//		if(val1==null && val2==null)
//		{
		String username=be.getUsername();
		String password=be.getPassword();	
			System.out.println(username+"---"+password);
			System.out.println(service.loginu_check(username,password));
			if(service.logina_check(username,password))
			{
//				request.getSession().setAttribute("logina",1);
				System.out.println("true correct user");
				return be;
			}
			else
			{
				be.setUsername("");
				be.setPassword("");
				return be;
			}
//		}
//		else if(val1==null)
//			return "company_user";
//		else
//			return "company_admin";
					
	}
	
	
	@RequestMapping(value="/getprofiles",method=RequestMethod.GET)
	public List<CustomerProfiles> getProfiles(){
		System.out.println(service.getProfiles());
		return service.getProfiles();
	}
	
	@PostMapping(path="/getaccount")
	public List<CustomerProfiles> getProfile(@RequestBody CustomerProfiles cp){
		System.out.println(service.getProfile(cp.getAccno()));
		return service.getProfile(cp.getAccno());
	}
	
	@PostMapping(value="/modpro")
	public CustomerProfiles modifyProfiles(@RequestBody CustomerProfiles cp){
		System.out.println("modify called");
		
		String username=cp.getAccountname();
		
		int accno=cp.getAccno();
		int adhaarno=cp.getAdhaarno();
		String accounttype=cp.getAccounttype();
		String address=cp.getAddress();
		System.out.println(username+"---"+address);
		if(service.modifyAccount(username,accno,adhaarno,accounttype,address))
				{
			System.out.println("true");
			return cp;
				}
		else
		{
			System.out.println("false");
			cp.setAccountname("");
			return cp;
		}
		//System.out.println(service.modifyAccount(username,accno,adhaarno,accounttype,address));
	}
	
	@PostMapping(value="/delpro")
	public CustomerProfiles deleteProfiles(@RequestBody CustomerProfiles cp){
		System.out.println("delete called");
		int accno=cp.getAccno();
		System.out.println("---"+accno);
		if(service.deleteAccount(accno))
		{
			return cp;
		}
		else
		{
			cp.setAccountname("");
			return cp;
		}
		//System.out.println(service.deleteAccount(accno));
	}
	
	
	
	
}




































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































