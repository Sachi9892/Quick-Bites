package com.quick_bites.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoPaymentFoundException extends RuntimeException{

    public NoPaymentFoundException() {

    }

    public NoPaymentFoundException(String message) {
        super(message);
    }

}
