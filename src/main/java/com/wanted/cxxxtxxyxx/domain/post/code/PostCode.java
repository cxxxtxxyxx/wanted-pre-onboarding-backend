package com.wanted.cxxxtxxyxx.domain.post.code;

import com.wanted.cxxxtxxyxx.common.response.code.Code;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PostCode implements Code {
    CREATED(201, "PS001", "게시글 생성에 성공하였습니다"),
    READ_SPECIFIC_POST(200, "PS002", "해당 게시물 조회에 성공하였습니다."),
    UPDATED(200, "PS003", "해당 게시물 수정에 성공하였습니다"),
    DELETED(200, "PS004", "게시글 삭제에 성공하였습니다"),
    READ_PAGE(200, "PS005", "해당 페이지의 게시물 조회에 성공했습니다");

    private final int status;
    private final String code;
    private final String message;
}
