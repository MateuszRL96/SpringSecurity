package com.example.qualifications.exception;

public class QualificationDontExistException extends RuntimeException{
    public QualificationDontExistException() {
        super();
    }

    public QualificationDontExistException(String message) {
        super(message);
    }

    public QualificationDontExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public QualificationDontExistException(Throwable cause) {
        super(cause);
    }
}
