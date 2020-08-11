package com.banking.beans;


public class Loan extends Account {
	

	private int loanId;
	private int loanAmount;
	private String loanType;
	
	
	public long getLoanId() {
		return loanId;
	}
	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}
	public int getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(int loanAmount) {
		this.loanAmount = loanAmount;
	}
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	
	public void showLoanDetails() {
		System.out.println("Loan Id = "+getLoanId());
		System.out.println("Loan amount = "+getLoanAmount());
		System.out.println("Loan Type = "+getLoanType());
	}
	
	
}
