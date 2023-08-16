package com.wanted.cxxxtxxyxx.domain.member.code;

import com.wanted.cxxxtxxyxx.common.response.code.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements ErrorCode {

    ALREADY_EXIST_EMAIL(400, "MF001", "이미 존재하는 이메일입니다."),
    NOT_FOUND_MEMBER_BY_ID(400, "MF003", "해당 id의 멤버가 존재하지 않습니다"),
    NOT_FOUND_MEMBER_BY_EMAIL(400, "MF002", "이메일 혹은 패스워드가 올바르지 않습니다."),
    INVALID_PAYLOAD(400, "MF003", "이메일 혹은 패스워드가 올바르지 않습니다.");

    private final int status;
    private final String code;
    private final String message;
}
