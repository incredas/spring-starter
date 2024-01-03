package dev.incredas.spring.starter.exception;

/**
 * The EntityNotFoundException class is an exception that is thrown when an entity is not found in the repository.
 *
 * <p>
 * This class extends the HttpServiceException class which is a runtime exception. It adds an additional constructor
 * to specify a custom message and status code for the exception.
 * </p>
 */
public class EntityNotFoundException extends HttpServiceException {

    /**
     * The EntityNotFoundException class is an exception that is thrown when an entity is not found in the repository.
     *
     * <p>
     * This class extends the HttpServiceException class which is a runtime exception. It adds an additional constructor
     * to specify a custom message and status code for the exception.
     * </p>
     */
    public EntityNotFoundException(String message, int code) {
        super(message, code);
    }

    /**
     * Constructs a new EntityNotFoundException with the specified message.
     *
     * @param message the detail message of the exception
     */
    public EntityNotFoundException(String message) {
        super(message, 404);
    }

}
