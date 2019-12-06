package com.example.restapp.utils;

import org.springframework.stereotype.Component;

import java.text.DecimalFormat;

@Component
public class PriceCalculator {

    private double PER_SMS_CHARGE = 0.12921 ;
    private double MARKUP_PERCENTAGE = 50 ;
    private double FIXED_PAYMENT_CHARGE = 0.3 ;
    private double PAYMENT_PERCENTAGE = 0.029;
    private double USD_TO_GBP_RATE = 0.76;

    /*
    gets initial AWS SMS costs based on the sms count and type
     */
    private double getInitialCost(int type)
    {
        switch (type)
        {
            case 1: break;
            default:
                // all days
                return PER_SMS_CHARGE * 31 ;
        }
        return -1;
    }

    private double markupedPrice( double initial)
    {
        return initial + (initial * MARKUP_PERCENTAGE)/100 ;
    }

    private double priceWithPaymentCharges( double markupvalue)
    {
        return (markupvalue + FIXED_PAYMENT_CHARGE) * (1+ PAYMENT_PERCENTAGE);
    }

    private double USDtoGBP(double usd) {
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(usd * USD_TO_GBP_RATE));
    }

    public double getFinalPrice(int type)
    {
        switch (type)
        {
            case 1:break;
            default:
                return USDtoGBP( priceWithPaymentCharges(markupedPrice(getInitialCost(type))));
        }
        return -1;
    }


}
