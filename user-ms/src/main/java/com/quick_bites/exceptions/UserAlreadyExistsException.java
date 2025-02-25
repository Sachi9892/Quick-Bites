package com.quick_bites.exceptions;


public class UserAlreadyExistsException  extends RuntimeException{

    public UserAlreadyExistsException(String message) {
        super(message);
    }

}
