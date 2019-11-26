package model;

import model.exception.DuplicationException;
import model.exception.UsernameOrPasswordWrongException;

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

    public Customer signUpCustomer(String userName, String password) throws DuplicationException {
        Customer customer = new Customer(userName, password);
        if (addCustomer(customer)) {
            return customer;
        } else {
            throw new DuplicationException();
        }
    }

    public Customer logInCustomer(String userName, String password) throws UsernameOrPasswordWrongException {
        for (Customer customer : customers) {
            if (customer.getUserName().equals(userName) && customer.checkPassword(password)) {
                return customer;
            }
        }
        throw new UsernameOrPasswordWrongException();
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void save(String filePath) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
        oos.writeObject(this);
        oos.close();
    }

    public static Bank load(String filePath) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
        Bank bank = (Bank) ois.readObject();
        ois.close();
        return bank;
    }
}
