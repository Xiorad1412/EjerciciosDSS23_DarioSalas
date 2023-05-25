package $package;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
package es.uca.creditcard;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import static org.junit.Assert.*;

public class CreditCardTest {
    private CreditCard creditCard;

    @Before
    public void setUp() {
        creditCard = new CreditCard("0123456701234567", "Abril Sueños", "07/24", 012, 10000.0);
    }

    @Test
    public void testInitialBalance() {
        assertEquals(0.0, creditCard.getBalance(), 0.001);
    }

    @Test
    public void testChargeValidAmount() {
        assertTrue(creditCard.charge(500.0));
        assertEquals(500.0, creditCard.getBalance(), 0.001);
    }

    @Test
    public void testChargeZeroAmount() {
        assertFalse(creditCard.charge(0.0));
        assertEquals(0.0, creditCard.getBalance(), 0.001);
    }

    @Test
    public void testChargeNegativeAmount() {
        assertFalse(creditCard.charge(-100.0));
        assertEquals(0.0, creditCard.getBalance(), 0.001);
    }

    @Test
    public void testChargeExceedsCreditLimit() {
        assertFalse(creditCard.charge(1500.0));
        assertEquals(0.0, creditCard.getBalance(), 0.001);
    }

    @Test
    public void testMakePaymentValidAmount() {
        creditCard.charge(500.0);
        assertTrue(creditCard.makePayment(200.0));
        assertEquals(300.0, creditCard.getBalance(), 0.001);
    }

    @Test
    public void testMakePaymentExceedsBalance() {
        creditCard.charge(500.0);
        assertFalse(creditCard.makePayment(700.0));
    }

    @Test
    public void testMakePaymentZeroAmount() {
        creditCard.charge(500.0);
        assertFalse(creditCard.makePayment(0.0));
        assertEquals(500.0, creditCard.getBalance(), 0.001);
    }

    @Test
    public void testMakePaymentNegativeAmount() {
        creditCard.charge(500.0);
        assertFalse(creditCard.makePayment(-10.0));
        assertEquals(500.0, creditCard.getBalance(), 0.001);
    }

}