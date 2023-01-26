package org.anotherclass.colortherock.domain.videoboard.exception;

import org.anotherclass.colortherock.global.error.GlobalBaseException;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;

public class PostNotFoundException extends GlobalBaseException {

    public PostNotFoundException(GlobalErrorCode errorCode) {
        super(errorCode);
    }
}
