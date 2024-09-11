package com.quick_bites.exceptions;

public class PlaceOrderException extends RuntimeException{

    public PlaceOrderException(String message) {
        super((message));
    }
}
