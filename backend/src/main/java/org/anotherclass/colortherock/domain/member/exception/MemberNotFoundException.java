package org.anotherclass.colortherock.domain.member.exception;

import org.anotherclass.colortherock.global.error.GlobalBaseException;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;

public class MemberNotFoundException extends GlobalBaseException {

    public MemberNotFoundException(GlobalErrorCode errorCode) {
        super(errorCode);
    }
}
