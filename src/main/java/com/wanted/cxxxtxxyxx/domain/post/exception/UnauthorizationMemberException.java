package com.wanted.cxxxtxxyxx.domain.post.exception;

import com.wanted.cxxxtxxyxx.common.error.exception.BusinessException;
import com.wanted.cxxxtxxyxx.common.response.code.ErrorCode;

public class UnauthorizationMemberException extends BusinessException {
    public UnauthorizationMemberException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public UnauthorizationMemberException(ErrorCode errorCode) {
        super(errorCode);
    }
}