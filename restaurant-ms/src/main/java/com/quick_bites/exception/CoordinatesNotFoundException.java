package com.quick_bites.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CoordinatesNotFoundException extends RuntimeException{

    public CoordinatesNotFoundException(String mess) {
        super(mess);
    }

}
