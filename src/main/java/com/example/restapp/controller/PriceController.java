package com.example.restapp.controller;

import com.example.restapp.utils.PriceCalculator;
import com.example.restapp.utils.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/price")
public class PriceController {

    @Autowired
    private PriceCalculator priceCalculator;

    @CrossOrigin
    @RequestMapping("")
    public ResponseWrapper<Double> getPrice()
    {
        return new ResponseWrapper<>(priceCalculator.getFinalPrice(-1), HttpStatus.OK);
    }
}
