package model.exception;

import model.exception.CannotWithdraw;

public class InsufficientFundException extends CannotWithdraw {

    @Override
    public String response() {
        return "Not enough fund!";
    }
}
