package org.anotherclass.colortherock.domain.videocomment.exception;

import org.anotherclass.colortherock.global.error.GlobalBaseException;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;

public class NotWriterException extends GlobalBaseException {
    public NotWriterException(GlobalErrorCode errorCode) {
        super(errorCode);
    }
}
