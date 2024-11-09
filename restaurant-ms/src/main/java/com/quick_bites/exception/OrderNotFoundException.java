package com.quick_bites.exception;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(String orderNotFound) {
        super(orderNotFound);
    }
}
