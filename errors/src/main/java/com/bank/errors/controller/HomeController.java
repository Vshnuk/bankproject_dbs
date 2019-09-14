package com.bank.errors.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bank.errors.BankWebApp.exception.BankTransactionException;
import com.bank.errors.BankWebApp.form.SendMoneyForm;
import com.bank.errors.model.BankAccount;
import com.bank.errors.repository.BankAccountDao;


@Controller
public class HomeController {
	@Autowired
	private BankAccountDao bankAccountDAO;
	@RequestMapping(value="/u",method=RequestMethod.GET)
	public String showBankAccounts(Model model)
	{
		List<BankAccount> list=bankAccountDAO.listBankAccountInfo();
		model.addAttribute("accountInfos",list);
		return "accountPage";
		
	}
	@RequestMapping(value="/sendMoney",method=RequestMethod.GET)
	public String viewSendMoneyPage(Model model)
	{
		SendMoneyForm form=new SendMoneyForm();
		model.addAttribute("sendMoneyForm",form);
		return "sendMoneyPage";
		
	}
	@RequestMapping(value="/sendMoney",method=RequestMethod.POST)
	public String processSendMoney(Model model,SendMoneyForm sendMoneyForm)
	{
		System.out.println("Send Money: "+sendMoneyForm.getAmount());
		try {
			bankAccountDAO.sendMoney(sendMoneyForm.getFromAccountId(),sendMoneyForm.getToAccountId(), sendMoneyForm.getAmount());
		}
		catch(BankTransactionException e)
		{
			model.addAttribute("errorMessage","Error:"+e.getMessage());
			return "/sendMoneyPage";
		}
		
		return "redirect:/";
		
	}
	
	
	

}
