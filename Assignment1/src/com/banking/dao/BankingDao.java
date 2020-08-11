package com.banking.dao;

import java.sql.SQLException;

import com.banking.beans.Account;
import com.banking.beans.Loan;

public interface BankingDao {
	
	public Account showAccountDetails(String accountNo) throws SQLException;
	
	public void createAccount(Account account) throws SQLException;
	
	public int depositAmount(String accountId,int amount) throws SQLException;
	
	public int withdrawAmount(String accountId,int amount) throws SQLException;
	
	public int getLoan(int loanId,int loanAmount) throws SQLException;

	public Loan showLoanDetails(int loanId) throws SQLException;
	
	public int payLoan(int loanId,int loanAmount) throws SQLException;
	
}
