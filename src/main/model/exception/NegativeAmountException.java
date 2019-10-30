package model.exception;

import model.exception.CannotWithdraw;

public class NegativeAmountException extends CannotWithdraw {

    @Override
    public String response() {
        return "Negative amount!";
    }
}
