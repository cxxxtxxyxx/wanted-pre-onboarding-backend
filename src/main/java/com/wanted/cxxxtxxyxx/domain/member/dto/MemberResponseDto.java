package com.wanted.cxxxtxxyxx.domain.member.dto;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResponseDto {

    private Long id;
    private String email;
}
