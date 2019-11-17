package com.example.restapp.controller;

import com.amazonaws.util.StringUtils;
import com.example.restapp.model.Category;
import com.example.restapp.repo.CategoryRepository;
import com.example.restapp.utils.ResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/modify/category")
public class CategoryController {


    private CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @RequestMapping("/all")
    public ResponseWrapper<List<Category>> getAllCategory() {
        Iterable<Category> categories = categoryRepository.findAll();
        List<Category> list = new ArrayList<>();
        for (Category category : categories) {
            list.add(category);
        }
        return new ResponseWrapper<>(list, HttpStatus.OK);
    }

    @RequestMapping("/{id}")
    public ResponseWrapper<Category> getCategory(@PathVariable(value = "id") String id) {

        Optional<Category> option = categoryRepository.findById(id);
        return new ResponseWrapper<>(option.orElse(null), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseWrapper<Category> addCategory(@RequestBody Category category) {
        return new ResponseWrapper<>(categoryRepository.save(category), HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseWrapper<Category> editCategory(@RequestBody Category category) {

        if(!StringUtils.isNullOrEmpty(category.getCategoryId()))
        {
            Optional<Category> option = categoryRepository.findById(category.getCategoryId());
            option.ifPresent(value -> value.setCategoryName(category.getCategoryName()));
            return new ResponseWrapper<>(categoryRepository.save(option.orElse(null)), HttpStatus.OK);
        }
        return new ResponseWrapper<>(null, HttpStatus.INTERNAL_SERVER_ERROR, "Category Id is not found");

    }

    @DeleteMapping("/delete/{id}")
    public ResponseWrapper deleteCategory(@PathVariable(value = "id") String id) {
        try {
            categoryRepository.deleteById(id);
        } catch (Exception e) {
            return new ResponseWrapper<>(e, HttpStatus.OK);
        }
        return new ResponseWrapper<>(null, HttpStatus.OK);
    }

}
