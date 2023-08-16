package com.wanted.cxxxtxxyxx.domain.member.code;

import com.wanted.cxxxtxxyxx.common.response.code.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum TokenErrorCode implements ErrorCode {
    INVALID_SIGNATURE_IN_ACCESS_TOKEN(401, "TF001","해당 엑세스 토큰의 시그니처가 올바르지 않습니다."),
    INVALID_ACCESS_TOKEN(401, "TF002", "해당 엑세스 토큰이 유효하지 않은 토큰입니다"),
    UNKNOWN_ACCESS_TOKEN_ERROR(401, "TF003", "엑세스 토큰이 존재하지 않습니다."),
    WRONG_TYPE_ACCESS_TOKEN(401, "TF004", "해당 엑세스 토큰은 변조되었습니다."),
    EXPIRED_ACCESS_TOKEN(401, "TF005", "해당 엑세스 토큰은 만료되었습니다."),
    NOT_EXIST_TOKEN(401, "TF006","토큰은 필수 값입니다.");

    private final int status;
    private final String code;
    private final String message;
}
