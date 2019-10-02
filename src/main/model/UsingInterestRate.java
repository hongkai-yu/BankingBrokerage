package model;

public interface UsingInterestRate {
    //EFFECT: get interest rate
    public double getInterestRate();

    //EFFECT: change interest rate
    public void setInterestRate(double ir);

    //EFFECT: update the financial product / account
    public void updateNextPeriod();

    public double getBalance();
}
