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
    VALID_EXCEPTION(400, "G300", ""),
    ACCESS_DENIED(401, "G400", "허용되지 않은 사용자입니다"),
    TOKEN_EXPIRED(401, "G500", "토큰이 만료되었습니다."),
    // 멤버와 관련된 Exception
    DUPLICATE_NICKNAME(400, "M100", "중복 닉네임 입니다"),
    INCORRECT_ADMIN_INFO(401, "M200", "관리자 로그인 정보가 틀렸습니다."),
    // 영상과 관련된 Exception
    VIDEO_NOT_FOUND(404, "V100", "해당 id에 해당하는 영상이 없습니다."),
    NOT_VIDEO_OWNER(400, "V200", "영상의 주인과 요청한 멤버가 다릅니다."),
    // 완등 영상과 관련된 Exception
    POST_NOT_FOUND(404, "VB100", "해당하는 완등 영상 글을 찾을 수 없습니다."),
    NOT_WRITER(403, "VB200", "사용자와 작성자가 일치하지 않습니다."),
    // 댓글과 관련된 Exception
    COMMENT_NOT_FOUND(404, "C100", "해당하는 댓글을 찾을 수 없습니다."),
    // 라이브와 관련된 Exception
    SESSION_NOT_FOUND(400, "L100", "세션을 찾을 수 없습니다."),
    RECORDING_START_BAD_REQUEST(400, "L101", "잘못된 녹화 요청입니다"),
    RECORDING_DELETE_ERROR(400, "L200", "녹화 삭제 중 오류가 발생하였습니다."),
    // 기록과 관련된 Exception
    MALFORMED_DATE(400, "R100", "잘못된 날짜 형식입니다."),
    ;

    private final String code;
    private final String message;
    private final int status;

    GlobalErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
