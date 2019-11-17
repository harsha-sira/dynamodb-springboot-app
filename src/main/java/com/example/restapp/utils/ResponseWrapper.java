package com.example.restapp.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseWrapper<T> extends ResponseEntity<T> {

    public ResponseWrapper(T t, HttpStatus status) {
        super((T) new ResultSet<>(t, status), status);
    }

    public ResponseWrapper(T t, HttpStatus status, String message) {
        super((T) new ResultSet<>(t, status, message), status);
    }
}
