package org.anotherclass.colortherock.domain.live.exception;

import org.anotherclass.colortherock.global.error.GlobalBaseException;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;

public class RecordingStartBadRequestException extends GlobalBaseException {

    public RecordingStartBadRequestException() {
        super(GlobalErrorCode.RECORDING_START_BAD_REQUEST);

    }
}
