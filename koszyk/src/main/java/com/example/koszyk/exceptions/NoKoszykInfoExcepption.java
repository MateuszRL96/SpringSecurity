package com.example.koszyk.exceptions;

public class NoKoszykInfoExcepption extends RuntimeException {
    public NoKoszykInfoExcepption() {
        super();
    }

    public NoKoszykInfoExcepption(String message) {
        super(message);
    }

    public NoKoszykInfoExcepption(String message, Throwable cause) {
        super(message, cause);
    }

    public NoKoszykInfoExcepption(Throwable cause) {
        super(cause);
    }

}
