package com.example.restapp.controller;

import com.amazonaws.util.StringUtils;
import com.example.restapp.model.Author;
import com.example.restapp.model.Category;
import com.example.restapp.repo.AuthorRepository;
import com.example.restapp.repo.CategoryRepository;
import com.example.restapp.utils.RatingCalculator;
import com.example.restapp.utils.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/modify/author")
public class AuthorController {


    @Autowired
    RatingCalculator ratingCalculator;

    private AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @RequestMapping("/all")
    public ResponseWrapper<List<Author>> getAllCategory() {
        Iterable<Author> authors = authorRepository.findAll();
        List<Author> list = new ArrayList<>();
        for (Author author : authors) {
            list.add(author);
        }
        return new ResponseWrapper<>(list, HttpStatus.OK);
    }

    @RequestMapping("/{id}")
    public ResponseWrapper<Author> getAuthor(@PathVariable(value = "id") String id) {

        Optional<Author> option = authorRepository.findById(id);
        return new ResponseWrapper<>(option.orElse(null), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseWrapper<Author> addAuthor(@RequestBody Author author) {
        return new ResponseWrapper<>(authorRepository.save(author), HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseWrapper<Author> editAuthor(@RequestBody Author author) {

        if (!StringUtils.isNullOrEmpty(author.getAuthorId())) {
            Optional<Author> option = authorRepository.findById(author.getAuthorId());
            option.ifPresent(value -> value.setFirstname(author.getFirstname()));
            option.ifPresent(value -> value.setLastname(author.getLastname()));
            option.ifPresent(value -> value.setDescription(author.getDescription()));
            option.ifPresent(value -> value.setImage(author.getImage()));
            return new ResponseWrapper<>(authorRepository.save(option.orElse(null)), HttpStatus.OK);
        }
        return new ResponseWrapper<>(null, HttpStatus.INTERNAL_SERVER_ERROR, "Author Id is not found");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseWrapper deleteAuthor(@PathVariable(value = "id") String id) {
        try {
            authorRepository.deleteById(id);
        } catch (Exception e) {
            return new ResponseWrapper<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseWrapper<>(null, HttpStatus.OK);
    }

    @PostMapping("/addRating")
    public ResponseWrapper addRating(@RequestBody Author author) {
        if (!StringUtils.isNullOrEmpty(author.getAuthorId())) {
            System.out.println(":passing ....");
            //rating validation
            if(author.getRating() > 5.0 )
            {
                return new ResponseWrapper<>(null, HttpStatus.INTERNAL_SERVER_ERROR, "Rating can not be greater than 5");
            }

            Optional<Author> option = authorRepository.findById(author.getAuthorId());
            if (option.isPresent()) {
                float rating = option.get().getRating();
                int ratingCount = option.get().getRatingCount();
                System.out.println(":passing ....");
                float newRating = ratingCalculator.calculateNewRating(option.get().getRating(), option.get().getRatingCount(),author.getRating());
                option.get().setRating(newRating);
                option.get().setDisplayRating(ratingCalculator.calculateNewDisplayRating(newRating));
                // need to increase rating count as well
                option.get().setRatingCount(option.get().getRatingCount() + 1);
                authorRepository.save(option.get());
                return new ResponseWrapper<>(null, HttpStatus.OK,"Rating added successfully");
            }
            return new ResponseWrapper<>(null, HttpStatus.INTERNAL_SERVER_ERROR, "Author Id is not found");
        }
        return new ResponseWrapper<>(null, HttpStatus.INTERNAL_SERVER_ERROR, "Author Id is not found");
    }

}
