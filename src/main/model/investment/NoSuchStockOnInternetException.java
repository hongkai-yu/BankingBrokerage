package model.investment;

public class NoSuchStockOnInternetException extends Exception {
    public String response() {
        return "No such stock";
    }
}
