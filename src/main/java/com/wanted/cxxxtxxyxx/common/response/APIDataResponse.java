package com.wanted.cxxxtxxyxx.common.response;

import com.wanted.cxxxtxxyxx.common.response.code.Code;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class APIDataResponse<T> {

    private T data;
    private int status;
    private String code;
    private String message;

    public static <T> ResponseEntity<?> of(T data, Code code) {
        APIDataResponse<T> response = new APIDataResponse<>(data, code.getStatus(), code.getCode(), code.getMessage());
        return ResponseEntity.status(code.getStatus()).body(response);
    }

    public static ResponseEntity<?> empty(Code code) {
        APIDataResponse<?> response = new APIDataResponse<>(null, code.getStatus(), code.getCode(), code.getMessage());
        return ResponseEntity.status(code.getStatus()).body(response);
    }

}
