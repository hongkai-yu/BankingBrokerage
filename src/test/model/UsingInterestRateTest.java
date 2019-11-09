package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class UsingInterestRateTest {

    private UsingInterestRate d0;
    private UsingInterestRate d1;
    private UsingInterestRate w0;
    private UsingInterestRate w1;

    @BeforeEach
    void setup() {
        d0 = new DebitAccount("d0");
        d1 = new DebitAccount("d1", 200, 0.05);
        w0 = new WeirdFinancialProduct(0,0);
        w1 = new WeirdFinancialProduct(100,0.05);
    }

    @Test
    void testUpdateNextPeriod() {
        d0.updateNextPeriod();
        assertEquals(0,d0.getValue());

        d1.updateNextPeriod();
        assertEquals(200 * 1.05,d1.getValue());
        d1.updateNextPeriod();
        assertEquals(200 * 1.05 * 1.05,d1.getValue());

        w0.updateNextPeriod();
        assertEquals(0,w0.getValue());

        w1.updateNextPeriod();
        assertEquals(50 * 1.05,w1.getValue());
        w1.updateNextPeriod();
        assertEquals((50 * 1.05) / 2 * 1.05,w1.getValue());
    }

}
