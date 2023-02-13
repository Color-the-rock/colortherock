package org.anotherclass.colortherock.domain.report.exception;

import org.anotherclass.colortherock.global.error.GlobalBaseException;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;

public class ReportOneselfException extends GlobalBaseException {

    public ReportOneselfException() {
        super(GlobalErrorCode.REPORT_ONESELF);
    }
}
