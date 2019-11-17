package com.example.restapp.repo;

import com.example.restapp.model.Author;
import com.example.restapp.model.Category;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface AuthorRepository extends CrudRepository<Author, String> {


}
