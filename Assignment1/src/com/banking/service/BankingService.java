package com.banking.service;

import java.sql.SQLException;

import com.banking.beans.Account;
import com.banking.beans.Loan;


public interface BankingService {
	
	public boolean validateAccountId(String id);
	public boolean validateAccountName(String name);
	
	public int depositAmount(String accountId,int amount) throws SQLException;
	
	public int withdrawAmount(String accounId,int amount) throws SQLException;
	
	public Account showAccountDetails(String accountNo) throws SQLException;
	
	public void createAccount(Account account) throws SQLException;
	
	public int getLoan(int loanId,int loanamt) throws SQLException;
	
	public int payLoan(int loanId,int loanamt) throws SQLException;
	
	public Loan showLoanDetails(int loanId) throws SQLException;

}
