package com.quick_bites.exceptions;

public class DishNotFoundException extends RuntimeException{

    public DishNotFoundException(String message) {
        super((message));
    }

}
