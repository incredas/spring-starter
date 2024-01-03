package dev.incredas.spring.starter.exception;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public abstract class HttpServiceException extends RuntimeException {

    protected final int status;

    protected final String message;

    protected final HashSet<String> errors;

    protected HttpServiceException(String message, int status, String... errors) {
        super(message);
        this.status = status;
        this.message = message;
        this.errors = new HashSet<>(Set.of(errors));
    }

}
