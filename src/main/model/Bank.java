package model;

import model.exception.UserNameOrPasswordWrongException;

import java.io.*;
import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

public class Bank extends Observable implements Serializable {

    private double interestRate;

    private Set<Customer> customers;

    public Bank() {
        customers = new HashSet<>();
        interestRate = 0;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
        setChanged();
        notifyObservers(interestRate);
    }

    public boolean addCustomer(Customer customer) {
        if (!customers.contains(customer)) {
            customers.add(customer);
            customer.setBank(this);
            addObserver(customer);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeCustomer(Customer customer) {
        if (customers.contains(customer)) {
            customers.remove(customer);
            deleteObserver(customer);
            customer.removeBank();
            return true;
        } else {
            return false;
        }
    }

    public boolean signUpCustomer(String userName, String password) {
        return addCustomer(new Customer(userName, password));
    }

    public Customer logInCustomer(String userName, String password) throws UserNameOrPasswordWrongException {
        for (Customer customer : customers) {
            if (customer.getUserName().equals(userName) && customer.checkPassword(password)) {
                return customer;
            }
        }
        throw new UserNameOrPasswordWrongException();
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void save(String filePath) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
        oos.writeObject(this);
    }

    public static Bank load(String filePath) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
        return (Bank) ois.readObject();
    }


}
