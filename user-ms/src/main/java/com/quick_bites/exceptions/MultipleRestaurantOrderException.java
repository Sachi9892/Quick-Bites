package com.quick_bites.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MultipleRestaurantOrderException extends RuntimeException {

    public MultipleRestaurantOrderException() {

    }

    public MultipleRestaurantOrderException(String message) {
        super(message);
    }

}
