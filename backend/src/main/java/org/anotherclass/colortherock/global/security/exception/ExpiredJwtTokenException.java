package org.anotherclass.colortherock.global.security.exception;

import org.anotherclass.colortherock.global.error.GlobalBaseException;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;

public class ExpiredJwtTokenException extends GlobalBaseException {

    public ExpiredJwtTokenException(GlobalErrorCode errorCode) {
        super(errorCode);
    }

}
