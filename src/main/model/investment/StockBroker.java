package model.investment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class StockBroker {

    //EFFECTS: give the price of the stock of the given stock code
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

    //EFFECTS: return a stock with current price given a stock code
    public static Stock getStock(String code) throws IOException, NoSuchStockOnInternetException {
        return new Stock(code, getStockPrice(code));
    }
}


