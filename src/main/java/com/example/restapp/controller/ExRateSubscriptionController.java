package com.example.restapp.controller;

import com.example.restapp.model.ExRateSubscription;
import com.example.restapp.repo.ExRateSubscriptionRepository;
import com.example.restapp.utils.ResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/")
public class ExRateSubscriptionController {

    private ExRateSubscriptionRepository exRateSubscriptionRepository;

    public ExRateSubscriptionController(ExRateSubscriptionRepository exRateSubscriptionRepository) {
        this.exRateSubscriptionRepository = exRateSubscriptionRepository;
    }

    /*
        adding user details to exRate table
         */
    @CrossOrigin
    @PostMapping("/rateAdd")
    public ResponseWrapper<ExRateSubscription> addBank(@RequestBody ExRateSubscription exRateSubscription)
    {
        ExRateSubscription user = exRateSubscriptionRepository.save(exRateSubscription);
        return  new ResponseWrapper<>(user,HttpStatus.OK);
    }



}
