package com.example.restapp.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class PriceCalculatorTest {

    private PriceCalculator priceCalculator = new PriceCalculator() ;

    @Test
    void getFinalPrice() {
        double price = priceCalculator.getFinalPrice(-1);
        assertEquals(4.93, price);
    }
}