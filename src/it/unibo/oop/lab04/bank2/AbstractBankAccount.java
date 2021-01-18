package it.unibo.oop.lab04.bank2;

import it.unibo.oop.lab04.bank.BankAccount;
import it.unibo.oop.lab04.bank.SimpleBankAccount;

public abstract class AbstractBankAccount implements BankAccount {
	private int userID;
	private double balance;
	private int nTransaction;
	
	public AbstractBankAccount(int userID, double balance) {
		super();
		this.userID = userID;
		this.balance = balance;
	}

	protected boolean checkUser(final int id) {
		return this.userID == id;
	}
	
    protected void incTransactions() {
    	this.nTransaction++;
    }
    
    protected void resetTransactions() {
    	this.nTransaction = 0;
    }
    
    protected void setBalance(final double amount) {
    	this.balance = amount;
    }
    
    protected void transactionOp(final int usrID, final double amount) {
    	if (checkUser(usrID)) {
    		this.balance += amount;
            this.incTransactions();
    	}
    }
    
    
	@Override
	public abstract void computeManagementFees(int usrID);

	@Override
	public void deposit(int usrID, double amount) {
		this.transactionOp(usrID, amount);

	}

	@Override
	public void depositFromATM(int usrID, double amount) {
		this.deposit(usrID, amount - SimpleBankAccount.ATM_TRANSACTION_FEE);

	}

	@Override
	public double getBalance() {
		return this.balance;
	}

	@Override
	public int getNTransactions() {
		return this.nTransaction;
	}

	@Override
	public abstract void withdraw(int usrID, double amount);

	@Override
	public void withdrawFromATM(int usrID, double amount) {
		this.withdraw(usrID, amount + SimpleBankAccount.ATM_TRANSACTION_FEE);

	}
	protected abstract boolean isWithDrawAllowed(double amount); 
	protected abstract double computeFee();

}
