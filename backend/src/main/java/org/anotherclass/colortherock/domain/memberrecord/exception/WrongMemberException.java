package org.anotherclass.colortherock.domain.memberrecord.exception;

import org.anotherclass.colortherock.global.error.GlobalBaseException;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;

public class WrongMemberException extends GlobalBaseException {
    public WrongMemberException(GlobalErrorCode errorCode) {
        super(errorCode);
    }
}
