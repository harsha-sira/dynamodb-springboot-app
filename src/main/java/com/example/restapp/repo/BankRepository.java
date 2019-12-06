package com.example.restapp.repo;

import com.example.restapp.model.Banks;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface BankRepository extends CrudRepository<Banks,String> {

}
