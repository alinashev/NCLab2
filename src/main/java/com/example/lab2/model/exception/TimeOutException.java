package com.example.lab2.model.exception;

import org.springframework.stereotype.Component;

@Component
public class TimeOutException extends Exception{
    public TimeOutException(String errorMessage){
        super(errorMessage);
    }
}
