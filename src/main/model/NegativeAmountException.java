package model;

public class NegativeAmountException extends CannotWithdraw {

    @Override
    public String response() {
        return "Negative amount!";
    }
}
