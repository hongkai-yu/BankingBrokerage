package model.investment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class InvestmentAccountTest {

    InvestmentAccount investmentAccount;
    Stock stock1;
    Stock stock2;

    @BeforeEach
    void setUp() {
        investmentAccount = new InvestmentAccount("InvAcct", 1000);
        stock1 = new Stock("AAAA", 100);
        stock2 = new Stock("BBBB", 200);
    }

    @Test
    void testAddStock() {
        investmentAccount.addStock(stock1, 1);
        assertEquals(1, investmentAccount.getStocksBook().size());
        assertEquals(1, investmentAccount.getStocksBook().get(stock1));
        assertEquals(100, investmentAccount.getStocksValue());
    }

    @Test
    void testBuyAndSellStock() {
        assertEquals("Account Type: Investment\n"
                        + "Account Name: InvAcct\n"
                        + "Balance: 1000.0\n"
                        + "Stock holdings: \n"
                        + "Code Current Price Share\n"
                        + "none\n"
                        + "Total account value: 1000.0",
                investmentAccount.accountInformation());


        assertTrue(investmentAccount.buyStock(stock1, 1));
        assertEquals(1, investmentAccount.getStocksBook().size());
        assertEquals(1, investmentAccount.getStocksBook().get(stock1));
        assertEquals(100, investmentAccount.getStocksValue());
        assertEquals(1000, investmentAccount.getAccountValue());

        stock1.setCurrentPrice(200);
        assertEquals(200, investmentAccount.getStocksValue());

        assertTrue(investmentAccount.buyStock(stock2, 2));
        assertEquals(2, investmentAccount.getStocksBook().size());
        assertEquals(2, investmentAccount.getStocksBook().get(stock2));
        assertEquals(600, investmentAccount.getStocksValue());

        assertFalse(investmentAccount.buyStock(stock1, 100));

        assertTrue(investmentAccount.sellStock(stock2, 1));
        assertEquals(2, investmentAccount.getStocksBook().size());
        assertEquals(1, investmentAccount.getStocksBook().get(stock2));
        assertEquals(400, investmentAccount.getStocksValue());

        assertTrue(investmentAccount.sellStock(stock2, 1));
        assertEquals(1, investmentAccount.getStocksBook().size());
        assertFalse(investmentAccount.getStocksBook().containsKey(stock2));
        assertEquals(200, investmentAccount.getStocksValue());

        assertFalse(investmentAccount.sellStock(stock2, 1));

        assertEquals("Account Type: Investment\n"
                        + "Account Name: InvAcct\n"
                        + "Balance: 900.0\n"
                        + "Stock holdings: \n"
                        + "Code Current Price Share\n"
                        + "AAAA 200.0 1\n"
                        + "Total account value: 1100.0",
                investmentAccount.accountInformation());
    }

    @Test
    void testDuplicateStocks() {
        Stock stock3 = new Stock("AAAA", 10);
        investmentAccount.addStock(stock3, 2);
        investmentAccount.addStock(stock1, 3);
        assertEquals(stock1, stock3);
        assertEquals(1, investmentAccount.numberOfStocks());
        assertEquals(50, investmentAccount.getStocksValue());
    }

    @Test
    void testBuySellInternet() {
        Stock apple = new Stock("AAPL", 20);
        assertTrue(investmentAccount.buyStock(apple, 2));

        try {
            assertTrue(investmentAccount.buyStock(apple.getStockCode(), 2));
        } catch (IOException | NoSuchStockOnInternetException e) {
            fail("Should be successful");
        }

        try {
            assertTrue(investmentAccount.buyStock("PPPP", 2));
            fail();
        } catch (IOException | NoSuchStockOnInternetException e) {
            //expected
        }


        assertEquals(1, investmentAccount.numberOfStocks());
        assertEquals(80, investmentAccount.getStocksValue());

        try {
            investmentAccount.updateStocksPrice();
        } catch (IOException | NoSuchStockOnInternetException e) {
            fail("Should be successful");
        }

        assertNotEquals(80, investmentAccount.getStocksValue());

        try {
            assertTrue(investmentAccount.sellStock("AAPL", 4));
        } catch (IOException | NoSuchStockOnInternetException e) {
            fail("Should be successful");
        }

        assertEquals(0, investmentAccount.numberOfStocks());
        assertTrue(investmentAccount.getBalance() >= 1000 - 40); // if apple stock's price is greater than 20
    }

    @Test
    void testSellAllStock() {
        investmentAccount.addStock(stock1, 2);
        investmentAccount.addStock(stock2, 2);
        assertEquals(2, investmentAccount.numberOfStocks());

        investmentAccount.sellAllStocks();
        assertEquals(0, investmentAccount.numberOfStocks());

        assertEquals(1000 + 2 * 100 + 2 * 200, investmentAccount.getBalance());
    }

    @Test
    void testNotExistedStock() {
        try {
            investmentAccount.buyStock("QWERTYUIOP", 2);
            fail();
        } catch (IOException e) {
            fail();
        } catch (NoSuchStockOnInternetException e) {
            //expected
        }

        try {
            investmentAccount.sellStock("QWERTYUIOP", 2);
            fail();
        } catch (IOException e) {
            fail();
        } catch (NoSuchStockOnInternetException e) {
            //expected
        }
    }

    @Test
    void testUpdateStocksPrice() {
    }

    @Test
    void testSaveAccountLine() {
    }

    @Test
    void testAccountInformation() {
    }
}