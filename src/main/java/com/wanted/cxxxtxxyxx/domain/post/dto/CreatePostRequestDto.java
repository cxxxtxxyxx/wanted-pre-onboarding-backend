package com.wanted.cxxxtxxyxx.domain.post.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CreatePostRequestDto {

    @NotNull
    @NotEmpty
    private String title;


    @NotNull
    @NotEmpty
    private String content;
}
