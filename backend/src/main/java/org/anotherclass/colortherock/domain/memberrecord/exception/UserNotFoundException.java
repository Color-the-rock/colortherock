package org.anotherclass.colortherock.domain.memberrecord.exception;

import org.anotherclass.colortherock.global.error.GlobalBaseException;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;

public class UserNotFoundException extends GlobalBaseException {

    public UserNotFoundException() {
        super(GlobalErrorCode.USER_NOT_FOUND);
    }
}
