package org.anotherclass.colortherock.domain.video.exception;

import org.anotherclass.colortherock.global.error.GlobalBaseException;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;

public class
VideoFileNameHasNotExtensionException extends GlobalBaseException {


    public VideoFileNameHasNotExtensionException(GlobalErrorCode errorCode) {
        super(errorCode);
    }
}
