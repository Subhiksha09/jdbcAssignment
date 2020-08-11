package com.banking.service;

import java.sql.SQLException;

import com.banking.beans.Account;
import com.banking.beans.Loan;
import com.banking.dao.BankingDao;
import com.banking.dao.BankingDaoImp;

public class BankingServiceImp implements BankingService{
	
    BankingDao dao;
	
	public BankingServiceImp() throws Exception {
		dao= new BankingDaoImp();
	}

	@Override
	public boolean validateAccountId(String id) {
		String regexId = "^[0-9]{7}[A-Za-z]{4}$";
		return id.matches(regexId);
	}

	@Override
	public boolean validateAccountName(String name) {
		String regexName = "^[A-Z]{1}[a-z]{2,30}$";
		return name.matches(regexName);
	}

	@Override
	public int depositAmount(String accountId, int amount) throws SQLException {
		// TODO Auto-generated method stub
		return dao.depositAmount(accountId, amount);
	}

	@Override
	public int withdrawAmount(String accountId, int amount) throws SQLException {
		// TODO Auto-generated method stub
		return dao.withdrawAmount(accountId, amount);
	}

	@Override
	public Account showAccountDetails(String accountNo) throws SQLException {
		return dao.showAccountDetails(accountNo);
		
	}

	@Override
	public void createAccount(Account account) throws SQLException {
		dao.createAccount(account);
		
	}
	
	public int getLoan(int loanId,int loanamt) throws SQLException {
		return dao.getLoan(loanId, loanamt);
	}

	@Override
	public Loan showLoanDetails(int loanId) throws SQLException {
		return dao.showLoanDetails(loanId);
		
	}

	@Override
	public int payLoan(int loanId, int loanamt) throws SQLException {
		return dao.payLoan(loanId, loanamt);
	}
	
}