package by.isethesal.app.service.error.exception;

/**
 * Custom abstract runtime exception class
 *
 * @author Illia Aheyeu
 */
public abstract class ApplicationRuntimeException extends RuntimeException {
    protected int errorCode;

    public ApplicationRuntimeException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}