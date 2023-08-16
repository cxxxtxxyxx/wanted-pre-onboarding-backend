package com.wanted.cxxxtxxyxx.common.response;

import com.wanted.cxxxtxxyxx.common.response.code.CommonErrorCode;
import com.wanted.cxxxtxxyxx.common.response.code.ErrorCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class APIErrorResponse {

    private String message;
    private int status;
    private List<Errors> errors;
    private String code;


    private APIErrorResponse(final ErrorCode code, final List<Errors> errors) {
        this.message = code.getMessage();
        this.status = code.getStatus();
        this.errors = errors;
        this.code = code.getCode();
    }

    private APIErrorResponse(final ErrorCode code) {
        this.message = code.getMessage();
        this.status = code.getStatus();
        this.code = code.getCode();
        this.errors = new ArrayList<>();
    }


    public static APIErrorResponse of(final ErrorCode code, final BindingResult bindingResult) {
        return new APIErrorResponse(code, Errors.of(bindingResult));
    }

    public static APIErrorResponse of(final ErrorCode code) {
        return new APIErrorResponse(code);
    }

    public static APIErrorResponse of(final ErrorCode code, final List<Errors> errors) {
        return new APIErrorResponse(code, errors);
    }

    public static APIErrorResponse of(MethodArgumentTypeMismatchException e) {
        final String value = e.getValue() == null ? "" : e.getValue().toString();
        final List<Errors> errors = Errors.of(e.getName(), value, e.getErrorCode());
        return new APIErrorResponse(CommonErrorCode.INVALID_INPUT_VALUE, errors);
    }


    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Errors {
        private String field;
        private String value;
        private String reason;

        public static List<Errors> of(final String field, final String value, final String reason) {
            List<Errors> errors = new ArrayList<>();
            errors.add(new Errors(field, value, reason));
            return errors;
        }

        private static List<Errors> of(final BindingResult bindingResult) {
            return bindingResult.getFieldErrors().stream()
                    .map(error -> new Errors(
                            error.getField(),
                            error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                    .collect(Collectors.toList());
        }
    }
}
