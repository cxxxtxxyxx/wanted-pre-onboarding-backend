package com.wanted.cxxxtxxyxx.domain.member.exception;

import com.wanted.cxxxtxxyxx.common.error.exception.BusinessException;
import com.wanted.cxxxtxxyxx.common.response.code.ErrorCode;

public class InvalidJwtTokenException extends BusinessException {
    public InvalidJwtTokenException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public InvalidJwtTokenException(ErrorCode errorCode) {
        super(errorCode);
    }
}
