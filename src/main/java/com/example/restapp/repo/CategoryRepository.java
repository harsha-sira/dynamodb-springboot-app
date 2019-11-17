package com.example.restapp.repo;

import com.example.restapp.model.Category;
import com.example.restapp.model.Customer;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface CategoryRepository extends CrudRepository<Category, String> {


}
