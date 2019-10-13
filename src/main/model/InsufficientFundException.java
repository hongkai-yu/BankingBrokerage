package model;

public class InsufficientFundException extends CannotWithdraw {

    @Override
    public String response() {
        return "Not enough fund!";
    }
}
