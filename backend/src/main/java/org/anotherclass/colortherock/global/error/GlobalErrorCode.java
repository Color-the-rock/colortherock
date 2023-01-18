package org.anotherclass.colortherock.global.error;

import lombok.Getter;

@Getter
public enum GlobalErrorCode {
    /**
     * 에러코드 규칙 :
     * 1. 코드 맨 앞에는 연관된 Entity의 첫글자의 대문자를 적는다 ex)  Member -> M
     * 2. 에러 코드와 이름 , 메시지가 최대한 모호하지 않게 작성합니다.
     * 3. 공통으로 발생하는 에러에 대해서는 Global -> G를 붙여서 작성 합니다.
     */
    SUCCESS(200, "G000", "요청에 성공하였습니다."),
    OTHER(500, "G100", "서버에 오류가 발생했습니다"),
    METHOD_NOT_ALLOWED(405, "G200", "허용되지 않은 메서드입니다"),
    MALFORMED_DATE(400, "R100", "잘못된 날짜 형식입니다."),
    VIDEO_NOT_FOUND(404, "V100", "해당 id에 해당하는 영상이 없습니다."),
    ACCESS_DENIED(401, "G400", "허용되지 않은 사용자입니다"),


    // 멤버와 관련된 Exception
    DUPLICATE_NICKNAME(400, "M100", "중복 닉네임 입니다");

    private final String code;
    private final String message;
    private final int status;

    GlobalErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
