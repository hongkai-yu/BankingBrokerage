package model;

public interface UsingInterestRate {

    //EFFECT: get interest rate
    double getInterestRate();

    //EFFECT: change interest rate
    void setInterestRate(double ir);

    //EFFECT: update the financial product / account
    void updateNextPeriod();

    double getValue();
}
