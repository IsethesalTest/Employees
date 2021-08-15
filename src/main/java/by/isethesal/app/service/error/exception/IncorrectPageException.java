package by.isethesal.app.service.error.exception;

import org.springframework.http.HttpStatus;

/**
 * Incorrect page exception
 *
 * @author Illia Aheyeu
 */
public class IncorrectPageException extends ApplicationRuntimeException {
    public IncorrectPageException() {
        super("Invalid page or amount", HttpStatus.BAD_REQUEST.value());
    }
}
