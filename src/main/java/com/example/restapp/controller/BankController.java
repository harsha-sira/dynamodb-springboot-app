package com.example.restapp.controller;

import com.amazonaws.util.StringUtils;
import com.example.restapp.model.Banks;
import com.example.restapp.repo.BankRepository;
import com.example.restapp.utils.ResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/banks/")
public class BankController {

    private BankRepository bankRepository;

    public BankController(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    /*
    getting all bank details
     */
    @RequestMapping("/all")
    public ResponseWrapper<List<Banks>> getAllBanks()
    {
        Iterable<Banks> allBanks = bankRepository.findAll();
        List<Banks> banks = new ArrayList<>();
        for (Banks bank:allBanks) {
            banks.add(bank);
        }
        return new ResponseWrapper<>(banks, HttpStatus.OK);
    }

    /*
    adding bank details
     */
    @PostMapping("/add")
    public ResponseWrapper<Banks> addBank(@RequestBody Banks bank)
    {
        Banks savedBank = bankRepository.save(bank);
        return  new ResponseWrapper<>(savedBank,HttpStatus.OK);
    }

    /*
    edit bank details
     */
    @PutMapping("/edit/")
    public ResponseWrapper<Banks> editBank(@RequestBody Banks bank)
    {
        if(!StringUtils.isNullOrEmpty(bank.getBankName()))
        {
            Optional<Banks> bankName = bankRepository.findById(bank.getBankName());
            bankName.ifPresent(value -> value.setUrl(bank.getUrl()));
            if(bankName.isPresent())
            {
                return new ResponseWrapper<>(bankRepository.save(bankName.get()), HttpStatus.OK);
            }
            return new ResponseWrapper<>(null, HttpStatus.INTERNAL_SERVER_ERROR, "Bank name is not matching any of existing record");

        }
        return new ResponseWrapper<>(null, HttpStatus.INTERNAL_SERVER_ERROR, "Bank name is not found");
    }

    /*
    delete a bank
     */
    @DeleteMapping("/delete/{id}")
    public ResponseWrapper<Banks> deleteBank(@PathVariable(value = "id") String bankName)
    {
        try {
            bankRepository.deleteById(bankName);
        } catch (Exception e) {
            return new ResponseWrapper<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseWrapper<>(null, HttpStatus.OK, "Successfully deleted");
    }

    /*
    gets the bank counts;
     */
    @CrossOrigin
    @RequestMapping("/count")
    public ResponseWrapper<String> getBankCount()
    {
        long count = bankRepository.count();
        return new ResponseWrapper<>(String.valueOf(count),HttpStatus.OK);
    }

    /*
    gets the bank name list only
     */
    @CrossOrigin
    @RequestMapping("/listBanks")
    public ResponseWrapper<List<String>> getAllBanksNamesOnly()
    {
        Iterable<Banks> allBanks = bankRepository.findAll();
        List<String> banks = new ArrayList<>();
        for (Banks bank:allBanks) {
            banks.add(bank.getBankName());
        }
        return new ResponseWrapper<>(banks, HttpStatus.OK);
    }


}
