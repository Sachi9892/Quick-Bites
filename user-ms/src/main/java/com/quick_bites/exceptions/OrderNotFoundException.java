package com.quick_bites.exceptions;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(String orderNotFound) {
        super(orderNotFound);
    }
}