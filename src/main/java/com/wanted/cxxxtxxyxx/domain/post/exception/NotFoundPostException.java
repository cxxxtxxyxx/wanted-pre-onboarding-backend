package com.wanted.cxxxtxxyxx.domain.post.exception;

import com.wanted.cxxxtxxyxx.common.error.exception.BusinessException;
import com.wanted.cxxxtxxyxx.common.response.code.ErrorCode;

public class NotFoundPostException extends BusinessException {
    public NotFoundPostException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public NotFoundPostException(ErrorCode errorCode) {
        super(errorCode);
    }
}
