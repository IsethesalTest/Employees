package by.isethesal.app.service.error.exception;

import org.springframework.http.HttpStatus;

/**
 * Entity already exists exception
 *
 * @author Illia Aheyeu
 */
public class EntityAlreadyExistsException extends ApplicationRuntimeException {
    public EntityAlreadyExistsException(String message) {
        super(message, HttpStatus.CONFLICT.value());
    }
}
