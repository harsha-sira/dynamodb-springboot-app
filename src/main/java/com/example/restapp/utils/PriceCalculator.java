package com.example.restapp.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;

@Component
public class PriceCalculator {

    @Value("${DOCKER_PER_SMS_CHARGE:0.12921}")
    private String PER_SMS_CHARGE = "" ;
    @Value(("${DOCKER_MARKUP_PERCENTAGE:50}"))
    private String MARKUP_PERCENTAGE = "" ;
    @Value(("${DOCKER_FIXED_PAYMENT_CHARGE:0.3}"))
    private String FIXED_PAYMENT_CHARGE = "" ;
    @Value(("${DOCKER_PAYMENT_PERCENTAGE:0.029}"))
    private String PAYMENT_PERCENTAGE = "";
    @Value(("${DOCKER_USD_TO_GBP_RATE:0.76}"))
    private String USD_TO_GBP_RATE = "";

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
                return getPER_SMS_CHARGE() * 31 ;
        }
        return -1;
    }

    private double markupedPrice( double initial)
    {
        return initial + (initial * getMARKUP_PERCENTAGE())/100 ;
    }

    private double priceWithPaymentCharges( double markupvalue)
    {
        return (markupvalue + getFIXED_PAYMENT_CHARGE()) * (1+ getPAYMENT_PERCENTAGE());
    }

    private double USDtoGBP(double usd) {
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(usd * getUSD_TO_GBP_RATE()));
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

    public double getPER_SMS_CHARGE() {
        return Double.parseDouble(PER_SMS_CHARGE);
    }

    public double getMARKUP_PERCENTAGE() {
        return Double.parseDouble(MARKUP_PERCENTAGE);
    }

    public double getFIXED_PAYMENT_CHARGE() {
        return Double.parseDouble(FIXED_PAYMENT_CHARGE);
    }

    public double getPAYMENT_PERCENTAGE() {
        return Double.parseDouble(PAYMENT_PERCENTAGE);
    }

    public double getUSD_TO_GBP_RATE() {
        return Double.parseDouble(USD_TO_GBP_RATE);
    }

    public void setPER_SMS_CHARGE(String PER_SMS_CHARGE) {
        this.PER_SMS_CHARGE = PER_SMS_CHARGE;
    }

    public void setMARKUP_PERCENTAGE(String MARKUP_PERCENTAGE) {
        this.MARKUP_PERCENTAGE = MARKUP_PERCENTAGE;
    }

    public void setFIXED_PAYMENT_CHARGE(String FIXED_PAYMENT_CHARGE) {
        this.FIXED_PAYMENT_CHARGE = FIXED_PAYMENT_CHARGE;
    }

    public void setPAYMENT_PERCENTAGE(String PAYMENT_PERCENTAGE) {
        this.PAYMENT_PERCENTAGE = PAYMENT_PERCENTAGE;
    }

    public void setUSD_TO_GBP_RATE(String USD_TO_GBP_RATE) {
        this.USD_TO_GBP_RATE = USD_TO_GBP_RATE;
    }
}
