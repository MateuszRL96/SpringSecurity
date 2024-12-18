package com.example.qualifications.exception;

public class BasketDontExistException extends RuntimeException{
    public BasketDontExistException() {
        super();
    }

    public BasketDontExistException(String message) {
        super(message);
    }

    public BasketDontExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public BasketDontExistException(Throwable cause) {
        super(cause);
    }
}

