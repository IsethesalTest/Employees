package by.isethesal.app.service.error.exception;

import org.springframework.http.HttpStatus;

/**
 * Entity not found exception
 *
 * @author Illia Aheyeu
 */
public class EntityNotFoundException extends ApplicationRuntimeException {
    public EntityNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND.value());
    }
}
