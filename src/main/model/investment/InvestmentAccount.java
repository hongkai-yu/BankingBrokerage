package model.investment;

import model.TransferableAccount;

import java.io.IOException;
import java.util.*;

public class InvestmentAccount extends TransferableAccount {

    private Map<Stock, Integer> stocksBook;

    public InvestmentAccount(String name) {
        super(name);
        stocksBook = new HashMap<>();
    }

    public InvestmentAccount(String name, double balance) {
        super(name, balance);
        stocksBook = new HashMap<>();
    }

    //MODIFIES: this.stocksBook
    //EFFECTS: add a stock to the stocksBook, if the stock is already held, increase the number of it
    public void addStock(Stock stock, int share) {
        if (stocksBook.containsKey(stock)) {
            stocksBook.put(stock, stocksBook.get(stock) + share);
        } else {
            stocksBook.put(stock, share);
        }
    }

    //REQUIRES: the stock's price is a valid non-negative number
    //MODIFIES: this.stocksBook
    //EFFECTS: decrease balance, add stock, and return true if has sufficient fund; return false otherwise
    public boolean buyStock(Stock stock, int share) {
        if (balance < stock.getCurrentPrice() * share) {
            return false;
        } else {
            balance -= stock.getCurrentPrice() * share;
            addStock(stock, share);
            return true;
        }
    }

    //MODIFIES: this.stocksBook2yy
    //EFFECTS: buy a stock using stock code, using Internet, throw an exception if no such stock on the Internet
    public boolean buyStock(String stockCode, int share) throws IOException, NoSuchStockOnInternetException {
        return buyStock(StockBroker.getStock(stockCode), share);
    }

    //MODIFIES: this.stocksBook
    //EFFECTS: sell a stock at its current price and return true if has sufficient fund, return false otherwise
    public boolean sellStock(Stock stock, int share) {
        if (stocksBook.containsKey(stock) && stocksBook.get(stock) >= share) {
            balance += stock.getCurrentPrice() * share;
            stocksBook.put(stock, stocksBook.get(stock) - share);
            if (stocksBook.get(stock) == 0) {
                stocksBook.remove(stock);
            }
            return true;
        } else {
            return false;
        }
    }

    //MODIFIES: this.stocksBook
    //EFFECTS: sell a stock using stock code, using Internet, throw an exception if no such stock on the Internet
    public boolean sellStock(String stockCode, int share) throws IOException, NoSuchStockOnInternetException {
        return sellStock(StockBroker.getStock(stockCode), share);
    }

    //MODIFIES: this.stocksBook
    //EFFECTS: sell all the stocks at their current prices
    public void sellAllStocks() {
        Set<Stock> stocks = new HashSet<>(stocksBook.keySet());
        for (Stock stock : stocks) {
            sellStock(stock, stocksBook.get(stock));
        }
    }

    //REQUIRES: all stocks on the stocksBook are valid stocks
    //MODIFIES: this
    //EFFECTS: update all the price of the stocks using the Internet, throw an exception if stock code not found
    public void updateStocksPrice() throws IOException, NoSuchStockOnInternetException {
        for (Stock stock : stocksBook.keySet()) {
            stock.updatePrice();
        }
    }

    public Map<Stock, Integer> getStocksBook() {
        return stocksBook;
    }

    public int numberOfStocks() {
        return getStocksBook().size();
    }

    //REQUIRES: all stocks on the stocksBook are valid stocks
    //EFFECTS: get the current value of all the stocks
    public double getStocksValue() {
        double value = 0;
        for (Stock stock : stocksBook.keySet()) {
            value += stock.getCurrentPrice() * stocksBook.get(stock);
        }
        return value;
    }

    //EFFECTS: get the sum of the stock value and balance of the account
    public double getAccountValue() {
        return getStocksValue() + getBalance();
    }

    @Override
    public String getType() {
        return "Investment";
    }

    //EFFECTS: get an overview of the account
    @Override
    public String accountInformation() {
        return "Account Type: " + getType() + "\n"
                + "Account Name: " + getName() + "\n"
                + "Balance: " + getBalance() + "\n"
                + "Stock holdings: \n"
                + "Code Current Price Share\n"
                + stockInformation()
                + "Total account value: " + getAccountValue();
    }

    private String stockInformation() {
        StringBuilder result = new StringBuilder();
        for (Stock stock : stocksBook.keySet()) {
            result.append(stock.getStockCode());
            result.append(" ");
            result.append(stock.getCurrentPrice());
            result.append(" ");
            result.append(stocksBook.get(stock));
            result.append("\n");
        }
        if (result.toString().equals("")) {
            return "none\n";
        } else {
            return result.toString();
        }
    }

}
