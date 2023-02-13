package org.anotherclass.colortherock.domain.member.exception;

import org.anotherclass.colortherock.global.error.GlobalBaseException;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;

public class IncorrectAdminInfoException extends GlobalBaseException {
    public IncorrectAdminInfoException() {
        super(GlobalErrorCode.INCORRECT_ADMIN_INFO);
    }
}
