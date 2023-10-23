package dev.incredas.spring.starter.exception;

public class EntityNotFoundException extends HttpServiceException {

    public EntityNotFoundException(String message, int code) {
        super(message, code);
    }

    public EntityNotFoundException(String message) {
        super(message, 404);
    }

}
