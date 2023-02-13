package org.anotherclass.colortherock.domain.live.exception;

import org.anotherclass.colortherock.global.error.GlobalBaseException;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;

public class RecordingDeleteException extends GlobalBaseException {

    public RecordingDeleteException() {
        super(GlobalErrorCode.RECORDING_DELETE_ERROR);
    }
}
