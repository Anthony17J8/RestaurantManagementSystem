package ru.icoltd.rvs.exception;

public class UnacceptablePropertyValueException extends RuntimeException {

    public UnacceptablePropertyValueException() {
        super();
    }

    public UnacceptablePropertyValueException(String message) {
        super(message);
    }

    public UnacceptablePropertyValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnacceptablePropertyValueException(Throwable cause) {
        super(cause);
    }

    protected UnacceptablePropertyValueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
