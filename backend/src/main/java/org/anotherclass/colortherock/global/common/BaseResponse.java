package org.anotherclass.colortherock.global.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;

import static org.anotherclass.colortherock.global.error.GlobalErrorCode.SUCCESS;

@Getter
@NoArgsConstructor
// AllArgsConstructor : 모든 필드값을 받는 생성자 생성
@AllArgsConstructor
// @JsonPropertyOrder : json serialization 순서를 정의
@JsonPropertyOrder({"status", "code", "message", "result"})
public class BaseResponse<T> { // 모든 return은 BaseResponse 포맷으로 전달되며, success / error 를 모두 다룹니다.

    private int status;
    private String message;
    private String code;
    @JsonInclude(JsonInclude.Include.NON_NULL) // 결과값이 공백일 경우 json에 포함하지 않도록
    private T result;

    @Override
    public String toString() {
        return "BaseResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", code='" + code + '\'' +
                ", result=" + result +
                '}';
    }

    // 요청에 성공한 경우
    public BaseResponse(T result) {
        this.status = SUCCESS.getStatus();
        this.message = SUCCESS.getMessage();
        this.code = SUCCESS.getCode();
        this.result = result;
    }

    // 요청에 실패한 경우
    public BaseResponse(GlobalErrorCode status) {
        this.status = status.getStatus();
        this.message = status.getMessage();
        this.code = status.getCode();
    }

    // GlobalExControllerAdvice에서 오류 설정
    public BaseResponse(GlobalErrorCode status, String message) {
        this.status = status.getStatus();
        this.message = message;
        this.code = status.getCode();
    }
}