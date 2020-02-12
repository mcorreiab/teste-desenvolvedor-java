package com.desafiobackend.backend.exception;

public class ApplicationsException extends RuntimeException {

    public ApplicationsException() {
        super();
    }

    public ApplicationsException(String message) {
        super(message);
    }

    public ApplicationsException(String message, Throwable cause) {
        super(message, cause);
    }

}
