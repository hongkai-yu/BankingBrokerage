package test;

import model.DebitAccount;
import model.UsingInterestRate;
import model.WeirdFinancialProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class UsingInterestRateTest {

    public UsingInterestRate d0;
    public UsingInterestRate d1;
    public UsingInterestRate w0;
    public UsingInterestRate w1;

    @BeforeEach
    public void setup() {
        d0 = new DebitAccount();
        d1 = new DebitAccount("d1", 200, 0.05);
        w0 = new WeirdFinancialProduct(0,0);
        w1 = new WeirdFinancialProduct(100,0.05);
    }

    @Test
    public void testUpdateNextPeriod() {
        d0.updateNextPeriod();
        assertEquals(d0.getBalance(),0);

        d1.updateNextPeriod();
        assertEquals(d1.getBalance(),200 * 1.05);
        d1.updateNextPeriod();
        assertEquals(d1.getBalance(),200 * 1.05 * 1.05);

        w0.updateNextPeriod();
        assertEquals(w0.getBalance(),0);

        w1.updateNextPeriod();
        assertEquals(w1.getBalance(),50 * 1.05);
        w1.updateNextPeriod();
        assertEquals(w1.getBalance(),(50 * 1.05) / 2 * 1.05);
    }

}
