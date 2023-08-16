package com.wanted.cxxxtxxyxx.domain.post.code;

import com.wanted.cxxxtxxyxx.common.response.code.Code;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PostCode implements Code {
    CREATED(201, "PS001", "게시글 생성에 성공하였습니다");

    private final int status;
    private final String code;
    private final String message;
}
