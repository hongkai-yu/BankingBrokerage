package model;

public class WeirdFinancialProduct implements UsingInterestRate {
    public double amount;
    public double interestRate;

    public WeirdFinancialProduct(double amount, double interestRate) {
        this.amount = amount;
        this.interestRate = interestRate;
    }

    @Override
    public double getInterestRate() {
        return interestRate;
    }

    @Override
    public void setInterestRate(double ir) {
        interestRate = ir;
    }

    //MODIFIES: this
    //EFFECT: using a weird formula, calculating the value of the product in the next interest rate period
    @Override
    public void updateNextPeriod() {
        amount = (amount / 2) * (1 + interestRate);
    }

    @Override
    public double getValue() {
        return amount;
    }
}
