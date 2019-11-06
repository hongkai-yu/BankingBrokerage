package model;

public class IdentityChecker {

    private String password;

    public IdentityChecker() {
        password = "";
    }

    public IdentityChecker(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean checkPassword(String input) {
        return getPassword().equals(input);
    }
}
