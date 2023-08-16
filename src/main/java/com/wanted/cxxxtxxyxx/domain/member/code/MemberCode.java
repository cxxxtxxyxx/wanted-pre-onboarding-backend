package com.wanted.cxxxtxxyxx.domain.member.code;

import com.wanted.cxxxtxxyxx.common.response.code.Code;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberCode implements Code {
    CREATED(201, "MS001", "유저 생성에 성공하였습니다");

    private final int status;
    private final String code;
    private final String message;
}
