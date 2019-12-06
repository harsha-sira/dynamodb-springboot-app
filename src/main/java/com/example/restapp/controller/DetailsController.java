package com.example.restapp.controller;

import com.amazonaws.util.StringUtils;
import com.example.restapp.model.Banks;
import com.example.restapp.model.ExRateSubscription;
import com.example.restapp.model.UserDetails;
import com.example.restapp.repo.BankRepository;
import com.example.restapp.repo.ExRateSubscriptionRepository;
import com.example.restapp.repo.UserDetailsRepository;
import com.example.restapp.utils.ResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users/")
public class DetailsController {

    private UserDetailsRepository userDetailsRepository;

    public DetailsController(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    /*
        getting all user details
         */
    @RequestMapping("/all")
    public ResponseWrapper<List<UserDetails>> getAllUserDetails()
    {
        Iterable<UserDetails> allBanks = userDetailsRepository.findAll();
        List<UserDetails> users = new ArrayList<>();
        for (UserDetails user:allBanks) {
            users.add(user);
        }
        return new ResponseWrapper<>(users, HttpStatus.OK);
    }

    /*
    adding user details
     */
    @PostMapping("/add")
    public ResponseWrapper<UserDetails> addBank(@RequestBody UserDetails userDetails)
    {
        UserDetails user = userDetailsRepository.save(userDetails);
        return  new ResponseWrapper<>(user,HttpStatus.OK);
    }

    /*
    gets the bank counts;
     */
    @RequestMapping("/count")
    public ResponseWrapper<String> getUsersCount()
    {
        long count = userDetailsRepository.count();
        return new ResponseWrapper<>(String.valueOf(count),HttpStatus.OK);
    }


}
