package by.isethesal.app.service.error.exception;

import org.springframework.http.HttpStatus;

/**
 * Validation exception
 *
 * @author Illia Aheyeu
 */
public class ValidationException extends ApplicationRuntimeException {
    public ValidationException(String message) {
        super(message, HttpStatus.CONFLICT.value());
    }
}
