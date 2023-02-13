package org.anotherclass.colortherock.domain.video.exception;

import org.anotherclass.colortherock.global.error.GlobalBaseException;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;

public class VideoNotFoundException extends GlobalBaseException {
    public VideoNotFoundException(GlobalErrorCode errorCode) {
        super(errorCode);
    }
}
