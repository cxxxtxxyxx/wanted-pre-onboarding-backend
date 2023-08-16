package com.wanted.cxxxtxxyxx.domain.post.code;

import com.wanted.cxxxtxxyxx.common.response.code.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PostErrorCode implements ErrorCode {

    NOT_FOUND_POST(404, "PF001", "존재하지 않는 게시물입니다."),
    FORBIDDEN(403, "PF002", "해당 게시물에 대한 권한이 존재하지 않습니다"),
    ;

    private final int status;
    private final String code;
    private final String message;
    }
