package org.anotherclass.colortherock.global.error;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.anotherclass.colortherock.global.common.BaseResponse;
import org.anotherclass.colortherock.global.mattermost.NotificationManager;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@RestControllerAdvice(annotations = RestController.class)
@Slf4j
@Profile("prod")
@RequiredArgsConstructor
public class ProdGlobalExControllerAdvice {

    private final NotificationManager notificationManager;

    /**
     * Valid 검증 실패시 오류 발생
     * 주로 @RequestBody, @RequestPart 어노테이션에서 발생
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<?> InvalidArgumentValidResponse(MethodArgumentNotValidException e) {
        log.error("Exception : {}, 입력값 : {}", e.getBindingResult().getFieldError(), e.getBindingResult().getFieldError());
        return new BaseResponse<>(GlobalErrorCode.VALID_EXCEPTION, e.getBindingResult().getFieldError().getDefaultMessage());
    }


    /**
     * 변수 Binding시 발생하는 오류
     **/
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<?> InvalidArgumentBindResponse(BindException e) {
        log.error("Exception : {}, 입력값 : {}", e.getBindingResult().getFieldError(), e.getBindingResult().getFieldError());
        return new BaseResponse<>(GlobalErrorCode.VALID_EXCEPTION, e.getBindingResult().getFieldError().getDefaultMessage());
    }


    /**
     * 지원하지 않은 HTTP method 호출 할 경우 발생
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected BaseResponse<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException", e);

        return new BaseResponse<>(GlobalErrorCode.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(GlobalBaseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected BaseResponse<?> handleGlobalBaseException(final GlobalBaseException e) {
        log.error("{} Exception {}: {}", e.getErrorCode(), e.getErrorCode().getCode(), e.getErrorCode().getMessage());
        return new BaseResponse<>(e.getErrorCode());
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected BaseResponse<?> handleException(Exception e, HttpServletRequest req) {
        log.error("Exception : {}", GlobalErrorCode.OTHER.getMessage(), e);
        notificationManager.sendNotification(e, req.getRequestURI(), getParams(req));
        return new BaseResponse<>(GlobalErrorCode.OTHER);
    }

    private String getParams(HttpServletRequest req) {
        StringBuilder params = new StringBuilder();
        Enumeration<String> keys = req.getParameterNames();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            params.append("- ").append(key).append(" : ").append(req.getParameter(key)).append('\n');
        }

        return params.toString();
    }
}
