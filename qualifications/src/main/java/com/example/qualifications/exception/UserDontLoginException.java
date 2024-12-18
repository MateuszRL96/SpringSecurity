package com.example.qualifications.exception;

public class UserDontLoginException extends RuntimeException{
    public UserDontLoginException() {
        super();
    }

    public UserDontLoginException(String message) {
        super(message);
    }

    public UserDontLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserDontLoginException(Throwable cause) {
        super(cause);
    }
}

