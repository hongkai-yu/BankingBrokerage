package model.exception;

public class NegativeAmountException extends Exception {

    public String response() {
        return "Negative amount!";
    }
}
