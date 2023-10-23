package dev.incredas.spring.starter.exception;

import java.util.Set;

public abstract class HttpServiceException extends RuntimeException {

    protected final int status;

    protected final String message;

    protected final Set<String> errors;

    protected HttpServiceException(String message, int status, String... errors) {
        super(message);
        this.status = status;
        this.message = message;
        this.errors = Set.of(errors);
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Set<String> getErrors() {
        return errors;
    }

}
