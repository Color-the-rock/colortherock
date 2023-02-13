package org.anotherclass.colortherock.domain.live.exception;

import org.anotherclass.colortherock.global.error.GlobalBaseException;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;

public class SessionNotFountException extends GlobalBaseException {
    public SessionNotFountException() {
        super(GlobalErrorCode.SESSION_NOT_FOUND);
    }
}
