package com.wanted.cxxxtxxyxx.domain.post.dto;

import lombok.*;


@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ReadPostByIdResponstDto {

    private String title;
    private String content;
}
