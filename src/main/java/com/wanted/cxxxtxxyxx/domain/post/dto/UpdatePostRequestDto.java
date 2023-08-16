package com.wanted.cxxxtxxyxx.domain.post.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class UpdatePostRequestDto {

    @NotNull
    @NotEmpty
    private String title;

    @NotNull
    @NotEmpty
    private String content;
}