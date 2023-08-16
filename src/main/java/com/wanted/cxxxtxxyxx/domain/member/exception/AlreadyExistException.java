package com.wanted.cxxxtxxyxx.domain.member.exception;

import com.wanted.cxxxtxxyxx.common.error.exception.BusinessException;
import com.wanted.cxxxtxxyxx.common.response.code.ErrorCode;

public class AlreadyExistException extends BusinessException {
    public AlreadyExistException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public AlreadyExistException(ErrorCode errorCode) {
        super(errorCode);
    }
}
