package com.example.koszyk.exceptions;

public class NoKoszykInfoExcepption extends RuntimeException {
    public NoKoszykInfoException() {
        super();
    }

    public NoKoszykInfoException(String message) {
        super(message);
    }

    public NoKoszykInfoException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoKoszykInfoException(Throwable cause) {
        super(cause);
    }
}
