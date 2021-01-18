package it.unibo.oop.lab04.bank2;

import it.unibo.oop.lab04.bank.SimpleBankAccount;

public class ClassicBankAccount extends AbstractBankAccount {

	   public ClassicBankAccount(final int usrID, final double balance) {
	        super(usrID, balance);
	    }

	@Override
	public void computeManagementFees(int usrID) {
        if (checkUser(usrID)) {
        	setBalance(getBalance() - SimpleBankAccount.MANAGEMENT_FEE);
            
        }

	}
	
	@Override
	public void withdraw(int usrID, double amount) {
		this.transactionOp(usrID, -amount);

	}

	@Override
	protected boolean isWithDrawAllowed(double amount) {
		
		return getBalance() > amount + SimpleBankAccount.ATM_TRANSACTION_FEE;
	}

	@Override
	protected double computeFee() {
		// TODO Auto-generated method stub
		return SimpleBankAccount.MANAGEMENT_FEE;
	}

}
