package org.anotherclass.colortherock.global.error;

import lombok.extern.slf4j.Slf4j;
import org.anotherclass.colortherock.global.common.BaseResponse;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * 로컬에서 처리하는 핸들링하기 위한 ContollerAdvide
 */
@RestControllerAdvice(annotations = RestController.class)
@Slf4j
@Profile("local")
public class GlobalExControllerAdvice {

    /**
     * Valid 검증 실패시 오류 발생
     * 주로 @RequestBody, @RequestPart 어노테이션에서 발생
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<Object> invalidArgumentValidResponse(MethodArgumentNotValidException e) {
        log.error("Exception : {}, 입력값 : {}", e.getBindingResult().getFieldError(), e.getBindingResult().getFieldError());
        return new BaseResponse<>(GlobalErrorCode.VALID_EXCEPTION, Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }


    /**
     * 변수 Binding시 발생하는 오류
     **/
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<Object> invalidArgumentBindResponse(BindException e) {
        log.error("Exception : {}, 입력값 : {}", e.getBindingResult().getFieldError(), e.getBindingResult().getFieldError());
        return new BaseResponse<>(GlobalErrorCode.VALID_EXCEPTION, Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }


    /**
     * 지원하지 않은 HTTP method 호출 할 경우 발생
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected BaseResponse<Object> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException", e);

        return new BaseResponse<>(GlobalErrorCode.METHOD_NOT_ALLOWED);
    }

    /**
     * 프로젝트내 설정한 예외가 발생할때 처리하는 부분
     * @param e 발생한 예외
     * @return 예외를 처리해서 반환한다.
     */
    @ExceptionHandler(GlobalBaseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected BaseResponse<Object> handleGlobalBaseException(final GlobalBaseException e) {
        log.error("{} Exception {}: {}", e.getErrorCode(), e.getErrorCode().getCode(), e.getErrorCode().getMessage());
        return new BaseResponse<>(e.getErrorCode());
    }


    /**
     * 처리되지 않은 에러를 여기서 처리 한다.
     * @param e 발생한 에러
     * @return BaseResponse로 메시지를 감춰서 반환한다.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected BaseResponse<Object> handleException(Exception e) {
        log.error("Exception : {}", GlobalErrorCode.OTHER.getMessage(), e);
        return new BaseResponse<>(GlobalErrorCode.OTHER);
    }
}