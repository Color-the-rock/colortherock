package org.anotherclass.colortherock.domain.memberrecord.exception;

import org.anotherclass.colortherock.global.error.GlobalBaseException;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;

public class MalformedDateException extends GlobalBaseException{

    public MalformedDateException(GlobalErrorCode errorCode) {
        super(errorCode);
    }

}
