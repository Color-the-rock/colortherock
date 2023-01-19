package org.anotherclass.colortherock.domain.member.exception;

import org.anotherclass.colortherock.global.error.GlobalBaseException;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;

public class DuplicateNicknameException extends GlobalBaseException {


    public DuplicateNicknameException() {
        super(GlobalErrorCode.DUPLICATE_NICKNAME);
    }
}
