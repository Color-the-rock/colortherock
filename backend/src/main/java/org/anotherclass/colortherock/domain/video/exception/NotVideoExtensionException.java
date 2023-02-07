package org.anotherclass.colortherock.domain.video.exception;

import org.anotherclass.colortherock.global.error.GlobalBaseException;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;

public class NotVideoExtensionException extends GlobalBaseException {

    public NotVideoExtensionException(String message, GlobalErrorCode errorCode) {
        super(message, errorCode);
    }

    public NotVideoExtensionException(GlobalErrorCode errorCode) {
        super(errorCode);
    }
}
