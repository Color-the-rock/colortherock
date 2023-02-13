package org.anotherclass.colortherock.domain.videoboard.exception;

import org.anotherclass.colortherock.global.error.GlobalBaseException;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;

public class WriterMismatchException extends GlobalBaseException {
    public WriterMismatchException(GlobalErrorCode errorCode) {
        super(errorCode);
    }
}
