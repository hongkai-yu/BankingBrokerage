package model;

public interface UsingInterestRate {

    double getInterestRate();

    //EFFECTS: change interest rate
    void setInterestRate(double ir);

    //MODIFIES: this
    //EFFECTS: update the financial product / account
    void updateNextPeriod();

    //EFFECTS: get the value of the product
    double getValue();
}
