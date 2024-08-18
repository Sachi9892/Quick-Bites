package com.quick_bites.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceAlreadyPresent extends RuntimeException {

    public ResourceAlreadyPresent() {

    }

    public  ResourceAlreadyPresent(String message) {
        super(message);
    }

}
