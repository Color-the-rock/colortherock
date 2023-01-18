package org.anotherclass.colortherock.global.error;

import lombok.extern.slf4j.Slf4j;
import org.anotherclass.colortherock.global.common.BaseResponse;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExControllerAdvice {

    /**
     * Valid 검증 실패시 오류 발생
     * 주로 @RequestBody, @RequestPart 어노테이션에서 발생
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse<?> InvalidArgumentValidResponse(MethodArgumentNotValidException e) {
        log.error("Exception : {}, 입력값 : {}", e.getBindingResult().getFieldError(), e.getBindingResult().getFieldError());
        return new BaseResponse<>(GlobalErrorCode.VALID_EXCEPTION, e.getBindingResult().getFieldError().getDefaultMessage());
    }


    /**
     * 변수 Binding시 발생하는 오류
     **/
    @ExceptionHandler(BindException.class)
    public BaseResponse<?> InvalidArgumentBindResponse(BindException e) {
        log.error("Exception : {}, 입력값 : {}", e.getBindingResult().getFieldError(), e.getBindingResult().getFieldError());
        return new BaseResponse<>(GlobalErrorCode.VALID_EXCEPTION, e.getBindingResult().getFieldError().getDefaultMessage());
    }


    /**
     * 지원하지 않은 HTTP method 호출 할 경우 발생
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected BaseResponse<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException", e);

        return new BaseResponse<>(GlobalErrorCode.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(GlobalBaseException.class)
    protected BaseResponse<?> handleGlobalBaseException(final GlobalBaseException e) {
        log.error("handleEntityNotFoundException", e);
        return new BaseResponse<>(e.getErrorCode());
    }


    @ExceptionHandler(Exception.class)
    protected BaseResponse<?> handleException(Exception e) {
        log.error("Exception : {}", GlobalErrorCode.OTHER.getMessage(), e);
        return new BaseResponse<>(GlobalErrorCode.OTHER);
    }
}