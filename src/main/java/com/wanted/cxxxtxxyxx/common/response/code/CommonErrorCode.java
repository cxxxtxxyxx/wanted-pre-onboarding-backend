package com.wanted.cxxxtxxyxx.common.response.code;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonErrorCode implements ErrorCode{
    INVALID_INPUT_VALUE(400, "CF001", "올바르지 않은 입력값 입니다."),
    METHOD_NOT_ALLOWED(405, "CF002", "지원되지 않는 Http Method입니다."),
    HANDLE_UNAUTHORIZED(401, "CF005", "인가되지 않은 요청입니다"),
    HANDLE_ACCESS_DENIED(403, "CF006", "권한이 존재하지 않습니다."),
    INTERNAL_SERVER_ERROR(500, "CF999", "알 수 없는 서버 에러입니다.");

    private final int status;
    private final String code;
    private final String message;
}
