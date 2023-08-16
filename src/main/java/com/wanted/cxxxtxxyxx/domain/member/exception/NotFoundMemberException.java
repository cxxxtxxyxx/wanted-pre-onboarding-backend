package com.wanted.cxxxtxxyxx.domain.member.exception;

import com.wanted.cxxxtxxyxx.common.error.exception.BusinessException;
import com.wanted.cxxxtxxyxx.common.response.code.ErrorCode;

public class NotFoundMemberException extends BusinessException {
    public NotFoundMemberException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public NotFoundMemberException(ErrorCode errorCode) {
        super(errorCode);
    }
}
