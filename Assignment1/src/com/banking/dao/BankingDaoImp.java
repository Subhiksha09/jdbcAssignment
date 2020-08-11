package com.banking.dao;

import com.banking.beans.Account;
import com.banking.beans.Loan;
import com.banking.beans.Transaction;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BankingDaoImp implements BankingDao {
	int amount = 0;

	
	String url="jdbc:mysql://localhost:3306/banking";
	String username="root";
	String password="Subhiksha";
	
	
	Connection connection = DriverManager.getConnection(url,username,password);

	
	public BankingDaoImp() throws Exception {
		
		
		@Override
		public int withdrawAmount(String accountId, int amount) throws SQLException {
		
	
		String getAmount = "select depositAmount from account_01 where accountId = ?";
		PreparedStatement pstatement = connection.prepareStatement(getAmount);
		
		pstatement.setString(1, accountId);
		ResultSet result = pstatement.executeQuery();
		result.next();
		
		int amount = result.getInt(1)-amount;
		
		String depositUpdate =  "update account_01 set depositAmount = ? where accountId = ?";
		PreparedStatement pstate = connection.prepareStatement(depositUpdate);
		pstate.setInt(1, amount);
		pstate.setString(2, accountId);
		pstate.executeUpdate();
		
		
		
		return amount;
	}
		@Override
	    public int depositAmount(String accountId, int amount) throws SQLException {
		
			String getAmount = "select depositAmount from account_01 where accountId = ?";
		    
			PreparedStatement pstatement = connection.prepareStatement(getAmount);
		    
		    pstatement.setString(1, accountId);
		    ResultSet result = pstatement.executeQuery();
		    result.next();
		
		    int amt = result.getInt(1)+amount;
		
			String depositeUpdate =  "update account_01 set depositAmount = ? where accountId = ?";
			PreparedStatement pstate = connection.prepareStatement(depositeUpdate);
			pstate.setInt(1, amt);
			pstate.setString(2, accountId);
			pstate.executeUpdate();
			
		
		return amt;
	}


	@Override
	public Account showAccountDetails(String accountNo) throws SQLException {

		
		String getAccDetails = "select accountId,accountName,address,depositAmount from account_01 where accountId = ?";
		
		PreparedStatement pstatement = connection.prepareStatement(getAccDetails);
		pstatement.setString(1, accountNo);
		ResultSet result = pstatement.executeQuery();
		result.next();
		
		Account account =new Account();
		account.setAccountId(result.getString(1));
		account.setAccountName(result.getString(2));
		account.setAddres(result.getString(3));
		account.setDepositAmount(result.getDouble(4));
		
		return account;
	}
	
	@Override
	public void createAccount(Account account) throws SQLException {

		Transaction t = (Transaction) account;
		String insertSql = "insert into Account_01 "+
							"(accountId,accountName,address,depositAmount,loanAmount)"+"values(?,?,?,?,?)";
																																				/*+ t.getAccountId()+","+t.getAccountName()+","+t.getAddres()+","+t.getDepositAmount()+","+t.getLoanId()+","+t.getLoanAmount()+","+t.getLoanType()+")";*/
		PreparedStatement pstatement = connection.prepareStatement(insertSql);
		
		pstatement.setString(1, t.getAccountId());
		pstatement.setString(2, t.getAccountName());
		pstatement.setString(3, t.getAddress());
		pstatement.setDouble(4, t.getDepositAmount());
		pstatement.setInt(5, t.getLoanAmount());

		int i = pstatement.executeUpdate();
		System.out.println(i+" rows affected ");
	}

	@Override
	public int getLoan(int loanId, int loanAmount) throws SQLException {
		
		String getLoan = "select loanAmount from account_01 where loanID = ?";
		PreparedStatement pstatement = connection.prepareStatement(getLoan);
		pstatement.setInt(1, loanId);
		ResultSet result = pstatement.executeQuery();
		result.next();
		int loanBal = result.getInt(1);
		loanBal += loanAmount;
		
		String updateLoan = "update account_01 set loanAmount = ? where loanID = ?";
		PreparedStatement pstate = connection.prepareStatement(updateLoan);
		pstate.setInt(1, loanBal);
		pstate.setInt(2, loanId);
		pstate.executeUpdate();
		
		return loanBal;
	}

	@Override
	public Loan showLoanDetails(int loanId) throws SQLException {

		PreparedStatement pstatement = connection.prepareStatement("select loanID,loanAmount,loanType from account_01 where loanID = ?");
		pstatement.setInt(1, loanId);
		ResultSet rs = pstatement.executeQuery();
		rs.next();
		Loan l = new Loan();
		l.setLoanId(rs.getInt(1));
		l.setLoanAmount(rs.getInt(2));
		
		return l;
				
	}

	@Override
	public int payLoan(int loanId, int loanAmount) throws SQLException {
		int loanBal = 0;

		
		String getLoan = "select loanAmount from account_01 where loanID = ?";
		PreparedStatement pstatement = connection.prepareStatement(getLoan);
		pstatement.setInt(1, loanId);
		ResultSet rs = pstatement.executeQuery();
		rs.next();
		loanBal = rs.getInt(1);
		loanBal -= loanAmount;
		
		String updateLoan = "update account_01 set loanAmount = ? where loanID = ?";
		PreparedStatement pstate = connection.prepareStatement(updateLoan);
		pstate.setInt(1, loanBal);
		pstate.setInt(2, loanId);
		pstate.executeUpdate();
		
		return loanBal;
		
	}

		
}