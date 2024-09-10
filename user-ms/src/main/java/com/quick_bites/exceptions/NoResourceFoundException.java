package com.quick_bites.exceptions;

public class NoResourceFoundException extends RuntimeException {

    public NoResourceFoundException() {

    }


    public NoResourceFoundException(String message) {
        super(message);
    }

}
