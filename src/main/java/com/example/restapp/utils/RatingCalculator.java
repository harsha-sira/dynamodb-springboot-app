package com.example.restapp.utils;

import org.springframework.stereotype.Component;

@Component
public class RatingCalculator {

    public float calculateNewRating(float oldRating, int ratingCount, float newRating) {
        float calculatedValue = ((oldRating * ratingCount) + newRating) / (ratingCount + 1);
        return Math.round(calculatedValue/0.01)/100F;
    }

    public float calculateNewDisplayRating(float newRating) {
        return  ((int) Math.floor(newRating / 0.5))*0.5F ;
    }
}
