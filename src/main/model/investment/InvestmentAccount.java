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

    public void addStock(Stock stock, int n) {
        if (stocksBook.containsKey(stock)) {
            stocksBook.put(stock, stocksBook.get(stock) + n);
        } else {
            stocksBook.put(stock, n);
        }
    }

    public boolean buyStock(Stock stock, int n) {
        if (balance < stock.getCurrentPrice() * n) {
            return false;
        } else {
            balance -= stock.getCurrentPrice() * n;
            addStock(stock, n);
            return true;
        }
    }

    public boolean buyStock(String stockCode, int n) throws IOException, NoSuchStockOnInternetException {
        return buyStock(StockBroker.getStock(stockCode), n);
    }

    public boolean sellStock(Stock stock, int n) {
        if (stocksBook.containsKey(stock) && stocksBook.get(stock) >= n) {
            balance += stock.getCurrentPrice() * n;
            stocksBook.put(stock, stocksBook.get(stock) - n);
            if (stocksBook.get(stock) == 0) {
                stocksBook.remove(stock);
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean sellStock(String stockCode, int n) throws IOException, NoSuchStockOnInternetException {
        return sellStock(StockBroker.getStock(stockCode), n);
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
        //TODO
        return null;
    }

}
