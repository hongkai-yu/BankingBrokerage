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

    public void addStock(Stock stock, int share) {
        if (stocksBook.containsKey(stock)) {
            stocksBook.put(stock, stocksBook.get(stock) + share);
        } else {
            stocksBook.put(stock, share);
        }
    }

    public boolean buyStock(Stock stock, int share) {
        if (balance < stock.getCurrentPrice() * share) {
            return false;
        } else {
            balance -= stock.getCurrentPrice() * share;
            addStock(stock, share);
            return true;
        }
    }

    public boolean buyStock(String stockCode, int share) throws IOException, NoSuchStockOnInternetException {
        return buyStock(StockBroker.getStock(stockCode), share);
    }

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

    public boolean sellStock(String stockCode, int share) throws IOException, NoSuchStockOnInternetException {
        return sellStock(StockBroker.getStock(stockCode), share);
    }

    public void sellAllStocks() {
        Set<Stock> stocks = new HashSet<>(stocksBook.keySet());
        for (Stock stock : stocks) {
            sellStock(stock, stocksBook.get(stock));
        }
    }

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

    public double getStocksValue() {
        double value = 0;
        for (Stock stock : stocksBook.keySet()) {
            value += stock.getCurrentPrice() * stocksBook.get(stock);
        }
        return value;
    }

    public double getAccountValue() {
        return getStocksValue() + getBalance();
    }

    @Override
    public List<String> getOptions() {
        List<String> options = new ArrayList<>();
        options.add("Change Name");
        options.add("Buy Stocks");
        options.add("Sell Stocks");
        return options;
    }

    @Override
    public String getType() {
        return "Investment";
    }

    @Override
    public String accountInformation() {
        return "Account Type: " + getType() + "\n"
                + "Account Name: " + getName() + "\n"
                + "Balance: " + getBalance() + "\n"
                + "Stock holdings: \n"
                + "Code Current Price Share: \n"
                + stockInformation();
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
            return "none";
        } else {
            return result.toString();
        }
    }

}
