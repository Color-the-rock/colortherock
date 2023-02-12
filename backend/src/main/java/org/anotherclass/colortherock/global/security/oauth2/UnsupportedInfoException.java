package org.anotherclass.colortherock.global.security.oauth2;

import org.anotherclass.colortherock.global.error.GlobalBaseException;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;

public class UnsupportedInfoException extends GlobalBaseException {
    public UnsupportedInfoException(GlobalErrorCode errorCode) {
        super(errorCode);
    }
}
