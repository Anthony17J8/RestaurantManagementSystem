package ru.icoltd.rvs.exception;

public class ObjNotFoundException extends RuntimeException {

    public ObjNotFoundException() {
        super();
    }

    public ObjNotFoundException(String message) {
        super(message);
    }

    public ObjNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjNotFoundException(Throwable cause) {
        super(cause);
    }
}
