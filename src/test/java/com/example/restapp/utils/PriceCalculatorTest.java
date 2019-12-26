package com.example.restapp.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PriceCalculatorTest {

    private PriceCalculator priceCalculator = new PriceCalculator() ;

    @Test
    void getFinalPrice() {

        priceCalculator.setFIXED_PAYMENT_CHARGE("0.3");
        priceCalculator.setMARKUP_PERCENTAGE("50");
        priceCalculator.setPAYMENT_PERCENTAGE("0.029");
        priceCalculator.setPER_SMS_CHARGE("0.12921");
        priceCalculator.setUSD_TO_GBP_RATE("0.76");

        double price = priceCalculator.getFinalPrice(-1);
        assertEquals(4.93, price);
    }
}