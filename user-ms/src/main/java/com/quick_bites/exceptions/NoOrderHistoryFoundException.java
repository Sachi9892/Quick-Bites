package com.quick_bites.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoOrderHistoryFoundException extends RuntimeException{

    public NoOrderHistoryFoundException(String message) {
        super(message);
    }

}
