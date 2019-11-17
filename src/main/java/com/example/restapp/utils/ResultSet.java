package com.example.restapp.utils;

import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ResultSet<T>
{
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private T data;
    private String message;

    private ResultSet()
    {
        this.timestamp = LocalDateTime.now();
        this.message = "";
    }

    public ResultSet( T o, HttpStatus status ) throws ResourceNotFoundException
    {
        this();
        this.status = status;
        this.data = o;

    }

    public ResultSet( T o, HttpStatus status, String message ) throws ResourceNotFoundException
    {
        this();
//        if ( o == null || ( o instanceof List && ( ( List ) o ).isEmpty() ) )
//            throw new ResourceNotFoundException( "No Content Found" );
        this.status = status;
        this.data = o;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}