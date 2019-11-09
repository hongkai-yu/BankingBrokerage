package model.investment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class StockBroker {

    public static double getStockPrice(String code) throws IOException, NoSuchStockOnInternetException {

        URL url = new URL("https://financialmodelingprep.com/api/v3/stock/real-time-price/" + code);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
        StringBuilder jsonText = new StringBuilder();

        for (String line; (line = reader.readLine()) != null; ) {
            jsonText.append(line);
            jsonText.append("\n");
        }
        reader.close();

        JSONObject jsonObject = new JSONObject(jsonText.toString());

        try {
            return jsonObject.getDouble("price");
        } catch (JSONException e) {
            throw new NoSuchStockOnInternetException();
        }
    }

    public static Stock getStock(String code) throws IOException, NoSuchStockOnInternetException {
        return new Stock(code, getStockPrice(code));
    }

    public static void main(String[] args) throws IOException, NoSuchStockOnInternetException {
        System.out.println(getStockPrice("MSFT"));
        System.out.println(getStockPrice("QWERTYU"));
    }
}


