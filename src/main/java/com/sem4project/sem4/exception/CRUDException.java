package com.sem4project.sem4.exception;

import java.io.Serial;

public class CRUDException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 9206987140880520886L;

    public CRUDException() {
    }

    public CRUDException(String message) {
        super(message);
    }

    public CRUDException(String message, Throwable cause) {
        super(message, cause);
    }

    public CRUDException(Throwable cause) {
        super(cause);
    }

    public CRUDException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
