package model;

import model.exception.NegativeAmountException;

public abstract class TransferableAccount extends Account {

    public TransferableAccount(String name) {
        super(name);
    }

    public TransferableAccount(String name, double balance) {
        super(name, balance);
    }

    //MODIFIES: this
    //EFFECTS: withdraw money from the debit account
    public boolean withdrawDeposit(Double amount) throws NegativeAmountException {
        if (amount < 0) {
            throw new NegativeAmountException();
        }
        if (amount > getBalance()) {
            return false;
        } else {
            subBalance(amount);
            return true;
        }
    }

    //MODIFIES: this, account
    //EFFECTS: transfer money from this to account, true if succeeded, false otherwise.
    public boolean transferMoney(Account payee, Double amount) {
        if (amount > balance) {
            return false;
        } else {
            this.subBalance(amount);
            if (payee.getType().equals("Credit")) {
                payee.subBalance(amount);
            } else {
                payee.addBalance(amount);
            }
            return true;
        }
    }
}
