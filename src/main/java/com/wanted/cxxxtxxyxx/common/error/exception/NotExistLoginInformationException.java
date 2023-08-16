package com.wanted.cxxxtxxyxx.common.error.exception;

import com.wanted.cxxxtxxyxx.common.response.code.ErrorCode;

public class NotExistLoginInformationException extends BusinessException{
    public NotExistLoginInformationException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public NotExistLoginInformationException(ErrorCode errorCode) {
        super(errorCode);
    }
}
