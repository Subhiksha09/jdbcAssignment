package com.banking.ui;

import java.util.Scanner;

import com.banking.beans.Account;
import com.banking.beans.Address;
import com.banking.beans.Loan;
import com.banking.beans.Transaction;
import com.banking.service.BankingService;
import com.banking.service.BankingServiceImp;

public class Banking {

	public static void main(String[] args) throws Exception{
		
Transaction account =null;
		
		BankingService service = null;
		
		service = new BankingServiceImp();
		
		Scanner scan=new Scanner(System.in);
		
		while(true) {
			System.out.println("Choose any one");
			System.out.println(" 1.Create account");
			System.out.println(" 2.Withdraw");
			System.out.println(" 3.Deposit");
			System.out.println(" 4.Show Account Details");
			System.out.println(" 5.Show Loan Details");
			System.out.println(" 6.GetLoan");
			System.out.println(" 7.PayLoan");
			
			System.out.println(" 8.Exit");
			
			switch(scan.nextInt()) {
			
			case 1 :
					account = new Transaction();
					
					while(true) {
						
					System.out.println("Enter account Id");
					String accountId = scan.next();
					if(service.validateAccountId(accountId)) {	
						account.setAccountId(accountId);
						break;
						}else {
							System.out.println("Enter valid accountId");
							return;
						}
					}		
					
					while(true) {
						System.out.println("Enter user name");
						String username = scan.next();
						if(service.validateAccountName(username)) {
							account.setAccountName(username);
							break;
						}else {
							System.out.println("Enter valid username");
							return;	
						}
					}		
					
					System.out.println("Enter address: ");

					account.setAddres(scan.next());
					
					System.out.println("Enter deposit amount");
					account.setDepositAmount(scan.nextInt());
					
					System.out.println(" Applying Loans...");
					System.out.println("1 for Yes \n 2 for No :");
					int ch = scan.nextInt();
					
					if(ch==1) {
						System.out.println("Enter loanId");
						account.setLoanId(scan.nextInt());
						
						System.out.println("Enter loan amount");
						account.setLoanAmount(scan.nextInt());			
					}
					
					System.out.println("Account is created");
					service.createAccount(account);
					break;
					
			case 2:
				
				System.out.println("Enter accountId :");
				String accIdwithdraw = scan.next();
				System.out.println("Enter amount :");
				int amount = scan.nextInt();
				
				int balanceWithdraw = service.withdrawAmount(accIdwithdraw, amount);
				System.out.println("New balance :"+balanceWithdraw);
				break;
					
			case 3:
					System.out.println("Enter accountId :");
					String accountId = scan.next();
					
					System.out.println("Enter amount :");
					int amt = scan.nextInt();
					
					int balance=service.depositAmount(accountId, amt);
					System.out.println("New balance :"+balance);
					break;
					
			
					
			case 4:
				
					System.out.println("Enter AccountId :");
					String accountid = scan.next();
					Account accounts = service.showAccountDetails(accountid);
					
					if(accounts != null) {
						System.out.println("Account Id : "+accounts.getAccountId());
						System.out.println("Account Name : "+accounts.getAccountName());
						System.out.println("Account holder address : "+accounts.getAddress());
						System.out.println("Deposit Amount : "+accounts.getDepositAmount());System.out.println();
						break;
					}
					else 
					{
						System.out.println("Not found");
						break;
					}
					
			case 5:
				
				System.out.println("Enter loanId : ");
				int loanId = scan.nextInt();
				Loan loan = service.showLoanDetails(loanId);
				
				if(loan!=null)
					System.out.println("Loan Id = "+loan.getLoanId()+" Loan type = "+loan.getLoanType()+" Loan Amount = "+loan.getLoanAmount());
				else
					System.out.println("Enter correct LoanId");
				
				break;
					
			case 6:
					System.out.println("Enter amount of loan: ");
					int loanAmt = scan.nextInt();
					
					System.out.println("Enter loanId : ");
					int loanid = scan.nextInt();
					
					int loanbalance = service.getLoan(loanid, loanAmt);
					System.out.println(loanbalance+" loan amount got");
					break;
			
			case 7:
					System.out.println("Enter amount : ");
					int payAmt = scan.nextInt();
					
					System.out.println("Enter loanId : ");
					int payLoanId = scan.nextInt();
					
					int loanBalance = service.payLoan(payLoanId, payAmt);
					System.out.println("paid loan amount "+loanBalance);
					break;
					
			case 8:
					System.out.println("Thank you");
					scan.close();
					break;
			}
		}

	}
}