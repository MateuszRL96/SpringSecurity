package com.example.koszyk.exceptions;

public class KoszykItemDontExistException extends RuntimeException{
    public KoszykItemDontExistException() {
        super();
    }

    public KoszykItemDontExistException(String message) {
        super(message);
    }

    public KoszykItemDontExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public KoszykItemDontExistException(Throwable cause) {
        super(cause);
    }

}
