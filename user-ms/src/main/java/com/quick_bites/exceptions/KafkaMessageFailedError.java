package com.quick_bites.exceptions;

public class KafkaMessageFailedError extends RuntimeException {

    public KafkaMessageFailedError(String message) {
        super(message);
    }

}
