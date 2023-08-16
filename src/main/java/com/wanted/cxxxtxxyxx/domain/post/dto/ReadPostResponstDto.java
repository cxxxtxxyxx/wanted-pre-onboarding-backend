package com.wanted.cxxxtxxyxx.domain.post.dto;

import com.wanted.cxxxtxxyxx.domain.member.dto.MemberResponseDto;
import lombok.*;


@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ReadPostResponstDto {

    private MemberResponseDto member;
    private PostResponseDto post;
}
