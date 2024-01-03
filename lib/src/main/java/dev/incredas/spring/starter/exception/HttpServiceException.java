package dev.incredas.spring.starter.exception;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

/**
 * The HttpServiceException class is an abstract base class for all HTTP service-related exceptions.
 *
 * <p>
 * This class extends the RuntimeException class and provides common properties and behavior for HTTP service exceptions.
 * It defines the status code, message, and set of errors associated with the exception.
 * </p>
 *
 * <p>
 * The HttpServiceException class cannot be directly instantiated. Instead, it should be extended by concrete exception
 * classes for specific types of HTTP service errors.
 * </p>
 */
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
