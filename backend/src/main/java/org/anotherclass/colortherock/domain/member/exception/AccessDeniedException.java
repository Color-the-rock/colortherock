package org.anotherclass.colortherock.domain.member.exception;

import org.anotherclass.colortherock.global.error.GlobalBaseException;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;

public class AccessDeniedException extends GlobalBaseException {

    public AccessDeniedException(GlobalErrorCode errorCode) {
        super(errorCode);
    }
}
