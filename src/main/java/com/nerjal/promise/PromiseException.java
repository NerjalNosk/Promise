package com.nerjal.promise;

public final class PromiseException extends RuntimeException {
    public PromiseException() {
        super();
    }

    public PromiseException(String message) {
        super(message);
    }

    public PromiseException(String messsage, Throwable cause) {
        super(messsage, cause);
    }

    public PromiseException(Throwable cause) {
        super(cause);
    }

    public PromiseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
