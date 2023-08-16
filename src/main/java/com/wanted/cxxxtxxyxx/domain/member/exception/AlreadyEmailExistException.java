package com.wanted.cxxxtxxyxx.domain.member.exception;

import com.wanted.cxxxtxxyxx.common.error.exception.BusinessException;
import com.wanted.cxxxtxxyxx.common.response.code.ErrorCode;

public class AlreadyEmailExistException extends BusinessException {
    public AlreadyEmailExistException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public AlreadyEmailExistException(ErrorCode errorCode) {
        super(errorCode);
    }
}
