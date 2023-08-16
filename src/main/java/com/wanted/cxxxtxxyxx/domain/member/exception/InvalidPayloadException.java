package com.wanted.cxxxtxxyxx.domain.member.exception;

import com.wanted.cxxxtxxyxx.common.error.exception.BusinessException;
import com.wanted.cxxxtxxyxx.common.response.code.ErrorCode;

public class InvalidPayloadException extends BusinessException {
    public InvalidPayloadException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public InvalidPayloadException(ErrorCode errorCode) {
        super(errorCode);
    }
}
