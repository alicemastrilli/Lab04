package it.unibo.oop.lab04.bank;

public class ExtendedStrictBankAccount extends StrictBankAccount {
	public final double MANAGEMENT_FEE = 5;
	private int usrID;
	private double balance;
	private int nTransactions;
	
	

	public ExtendedStrictBankAccount(int usrID, double balance) {
		super(usrID, balance);
		// TODO Auto-generated constructor stub
	}
	
	public void computeManagmentFees(final int usrID) {
		if(this.usrID == usrID) {
			this.balance -= this.MANAGEMENT_FEE;
		}
	}
	
	public void resetTransactions() {
	    this.nTransactions = 0;
	}
	
	public void setBalance(final double amount) {
	    this.balance = amount;
	}
	
    private void transactionOp(final int usrID, final double amount) {
        if (this.usrID == usrID) {
            this.balance += amount;
            this.nTransactions++;
        }
    }
    public void withdraw(final int usrID, final double amount) {
        this.transactionOp(usrID, -amount);
    }


}
