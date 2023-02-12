package org.anotherclass.colortherock.global.security.oauth2;

import org.anotherclass.colortherock.global.error.GlobalBaseException;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;

/**
 * 지원하지 않는 oauth 로그인타입
 * @author suker80
 */
public class UnsupportedInfoException extends GlobalBaseException {
    public UnsupportedInfoException(GlobalErrorCode errorCode) {
        super(errorCode);
    }
}
