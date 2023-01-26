package org.anotherclass.colortherock.domain.videocomment.exception;

import org.anotherclass.colortherock.global.error.GlobalBaseException;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;

public class CommentNotFoundException extends GlobalBaseException {
    public CommentNotFoundException(GlobalErrorCode errorCode) {
        super(errorCode);
    }
}
