package by.isethesal.app.controller.handler;

import by.isethesal.app.service.error.RestError;
import by.isethesal.app.service.error.exception.EntityAlreadyExistsException;
import by.isethesal.app.service.error.exception.EntityNotFoundException;
import by.isethesal.app.service.error.exception.IncorrectPageException;
import by.isethesal.app.service.error.exception.ValidationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Handle exceptions
 *
 * @author Illia Aheyeu
 */
@RestControllerAdvice
@Log4j2
public class RestExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<RestError> applicationErrorHandler(EntityNotFoundException exception) {
        RestError error = new RestError("Entity not found " + exception.getMessage(), exception.getErrorCode());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(error, httpHeaders, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<RestError> applicationErrorHandler(EntityAlreadyExistsException exception) {
        RestError error = new RestError("Entity already exists " + exception.getMessage(), exception.getErrorCode());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(error, httpHeaders, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<RestError> applicationErrorHandler(ValidationException exception) {
        RestError error = new RestError(exception.getMessage(), exception.getErrorCode());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(error, httpHeaders, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnsupportedOperationException.class)
    public ResponseEntity<RestError> applicationErrorHandler() {
        RestError error = new RestError("Unsupported operation exception", HttpStatus.BAD_REQUEST.value());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(error, httpHeaders, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IncorrectPageException.class)
    public ResponseEntity<RestError> pageErrorHandler() {
        RestError error = new RestError("Incorrect page ", HttpStatus.BAD_REQUEST.value());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(error, httpHeaders, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class, NoHandlerFoundException.class})
    public ResponseEntity<RestError> applicationErrorHandler(Exception exception) {
        RestError error = new RestError(exception.getMessage(), HttpStatus.BAD_REQUEST.value());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(error, httpHeaders, HttpStatus.BAD_REQUEST);
    }
}
