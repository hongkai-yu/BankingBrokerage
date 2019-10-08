package model;

public class DebitAccount extends Account implements UsingInterestRate {

    public static final String ACCOUNT_TYPE = "Debit";

    double interestRate;

//    public static final List<String> options = Collections.unmodifiableList(Arrays.asList(
//            "Change Name", "Deposit", "Withdraw"));

    //EFFECTS: create a new BankAccount
    public DebitAccount() {
        super();
        interestRate = 0;
    }

    // Overload
    public DebitAccount(String n, double b, double ir) {
        super(n, b);
        interestRate = ir;
    }

    @Override
    public String accountInformation() {
        return "Account Type: " + ACCOUNT_TYPE + "\n"
                + "Account Name: " + getName() + "\n"
                + "Balance:" + getBalance() + "\n";
    }


    //MODIFIES: this, account
    //EFFECTS: transfer money from this to account, true if succeeded, false otherwise.
    public boolean transferMoney(DebitAccount debitAccount, Double amount) {
        if (amount > balance) {
            return false;
        } else {
            this.subBalance(amount);
            debitAccount.addBalance(amount);
            return true;
        }
    }

    @Override
    public String optionsOfAccount() {
        return "[1] Change name [2] Deposit [3] Withdraw";
    }

    @Override
    public String saveAccountLine() {
        return "Debit " + getName() + " " + getBalance() + " " + getInterestRate();
    }

    @Override
    public double getInterestRate() {
        return interestRate;
    }

    @Override
    public void setInterestRate(double ir) {
        interestRate = ir;

    }

    @Override
    public void updateNextPeriod() {
        balance *= (1 + interestRate);
    }

    @Override
    public double getValue() {
        return getBalance();
    }

}

