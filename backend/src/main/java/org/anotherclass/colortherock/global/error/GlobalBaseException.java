package org.anotherclass.colortherock.global.error;


public class GlobalBaseException extends RuntimeException {
    private GlobalErrorCode errorCode;

    public GlobalBaseException(String message, GlobalErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public GlobalBaseException(GlobalErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public GlobalErrorCode getErrorCode() {
        return errorCode;
    }

}
