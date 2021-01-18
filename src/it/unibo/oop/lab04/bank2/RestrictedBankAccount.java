package it.unibo.oop.lab04.bank2;

import it.unibo.oop.lab04.bank.StrictBankAccount;

public class RestrictedBankAccount extends AbstractBankAccount {
    public RestrictedBankAccount(int userID, double balance) {
		super(userID, balance);
		// TODO Auto-generated constructor stub
	}

	private static final double MANAGEMENT_FEE = 5;
    private static final double TRANSACTION_FEE = 0.1;
    

	@Override
	public void computeManagementFees(int usrID) {
		final double feeAmount = MANAGEMENT_FEE + (getNTransactions() * TRANSACTION_FEE);
        if (checkUser(usrID) && isWithDrawAllowed(feeAmount)) {
        	setBalance(getBalance() - feeAmount);
            resetTransactions();
            
        }

	}

	@Override
	public void withdraw(int usrID, double amount) {
		if (isWithDrawAllowed(amount)) {
            this.transactionOp(usrID, -amount);
        }

	}

	@Override
	protected boolean isWithDrawAllowed(double amount) {
		// TODO Auto-generated method stub
		return getBalance() > amount;
	}

	@Override
	protected double computeFee() {
		// TODO Auto-generated method stub
		return MANAGEMENT_FEE + (getNTransactions() * TRANSACTION_FEE);
	}

}
