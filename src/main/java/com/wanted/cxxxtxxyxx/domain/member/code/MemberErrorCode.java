package com.wanted.cxxxtxxyxx.domain.member.code;

import com.wanted.cxxxtxxyxx.common.response.code.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements ErrorCode {

    Already_Exist_Email(400, "MF001", "이미 존재하는 이메일입니다.");

    private final int status;
    private final String code;
    private final String message;
}
