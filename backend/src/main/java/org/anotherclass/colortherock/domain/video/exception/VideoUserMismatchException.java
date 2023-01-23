package org.anotherclass.colortherock.domain.video.exception;

import org.anotherclass.colortherock.global.error.GlobalBaseException;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;

public class VideoUserMismatchException extends GlobalBaseException {

    public VideoUserMismatchException(GlobalErrorCode errorCode) {
        super(errorCode);
    }
}