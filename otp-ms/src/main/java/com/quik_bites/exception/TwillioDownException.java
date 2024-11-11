package com.quik_bites.exception;

public class TwillioDownException extends RuntimeException{

    public TwillioDownException(String mess) {
        super(mess);
    }

}
