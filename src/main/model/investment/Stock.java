package model.investment;

import java.io.IOException;
import java.util.Objects;

public class Stock {

    private String stockCode;
    private double currentPrice;

    public Stock(String stockCode, double currentPrice) {
        this.stockCode = stockCode;
        this.currentPrice = currentPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Stock stock = (Stock) o;
        return stockCode.equals(stock.stockCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stockCode);
    }

    public String getStockCode() {
        return stockCode;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public void updatePrice() throws IOException, NoSuchStockOnInternetException {
        setCurrentPrice(StockBroker.getStockPrice(stockCode));
    }
}